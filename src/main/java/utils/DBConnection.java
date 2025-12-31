package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        try{
            return ConnectionPool.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot get database connection from pool", e);
        }
    }
}
