package DAO;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean checkLogin(String usernameOrEmail, String password){
        String sql = "select * from user where (username = ? or email = ?) and password = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setString(1, usernameOrEmail);
            stm.setString(2, usernameOrEmail);
            stm.setString(3, password);

            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean signUp(String userName, String email, String phoneNum, String password){
        String sql = "insert into user(userName, email, phoneNum, password) values(?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, userName);
            stm.setString(2, email);
            stm.setString(3, phoneNum);
            stm.setString(4, password);

            int rows = stm.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
