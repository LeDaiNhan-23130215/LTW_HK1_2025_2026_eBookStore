package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool instance; //Dùng cho Singleton Pattern

    private static final int INITIAL_POOL_SIZE= 2;
    private static final int MAX_POOL_SIZE = 5;

    private final List<Connection> availables = new ArrayList<Connection>();
    private final List<Connection> useds = new ArrayList<Connection>();

    private String URL = "jdbc:mysql://localhost:3306:/ebookstore";

        private final String USER = "root";
        private final String PASS = "";

        private volatile boolean closed = false;

        private ConnectionPool() throws SQLException {
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                availables.add(createConnection());
            }
        }
        public static synchronized utils.ConnectionPool getInstance() throws SQLException {
            if (instance == null) {
                instance = new utils.ConnectionPool();
            }
            return instance;
        }

        private Connection createConnection() throws SQLException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return DriverManager.getConnection(URL, USER, PASS);
        }

        public synchronized Connection getConnection() throws SQLException {
            if (availables.isEmpty()) {
                if (useds.size() < MAX_POOL_SIZE) {
                    availables.add(createConnection());
                } else {
                    throw new SQLException("Connection Pool Exhausted");
                }
            }

            Connection conn = availables.remove(availables.size() - 1);
            useds.add(conn);
            return new PooledConnection(conn, this);
        }

        protected synchronized void release(Connection conn) {
            System.out.println("Release: " + conn);
            useds.remove(conn);
            availables.add(conn);
        }

        public boolean isClosed() {
            return this.closed;
        }

        public synchronized void shutdown() {
            closed = true;

            System.out.println("Close pool");

            for(Connection conn : availables) {
                try {conn.close();} catch (Exception ignored){}
            }
            for(Connection conn : useds) {
                try {conn.close();} catch (Exception ignored){}
            }

            availables.clear();
            useds.clear();
        }
}
