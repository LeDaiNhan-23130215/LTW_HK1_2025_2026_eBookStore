package DAO;

import at.favre.lib.crypto.bcrypt.BCrypt;
import models.User;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public boolean checkLogin(String usernameOrEmail, String password) {
        String sql = "SELECT password FROM user WHERE username = ? OR email = ? LIMIT 1";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, usernameOrEmail);
            stm.setString(2, usernameOrEmail);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");

                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);

                return result.verified;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean signUp(String userName, String email, String phoneNum, String password) {
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

    public boolean checkAvailableUserNameOrEmail(String userNameOrEmail) {
        String sql = "select userName, email from user where userName = ? or email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, userNameOrEmail);
            stm.setString(2, userNameOrEmail);
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByID(int id) {
        String sql = "select * from user where id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("phoneNum"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        String sql = "select * from user";
        try (Connection connection = DBConnection.getConnection()) {
            ResultSet rs = connection.prepareStatement(sql).executeQuery();
            List<User> list = new ArrayList<>();
            if (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("phoneNum"),
                        rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE user SET userName=?, email=?, phoneNum=? WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPhoneNum());
            stm.setInt(4, user.getId());
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE from user where id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkAdminLogin(String usernameOrEmail, String password) {
        String sql = "select password from user where (userName=? OR email=?) and role = 'admin'";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, usernameOrEmail);
            stm.setString(2, usernameOrEmail);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
                return result.verified;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUserNameByEmail(String userAndEmail) {
        String query = "SELECT userName FROM user WHERE (email = ? or userName = ?)";
        String username = "";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(query)){
            stm.setString(1, userAndEmail);
            stm.setString(2, userAndEmail);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                username = rs.getString(1);
            } else {username = "Error";}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    public int countTotalUser() {
        String sql = "SELECT COUNT(*) FROM `user` WHERE role = 'user'";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }
}
