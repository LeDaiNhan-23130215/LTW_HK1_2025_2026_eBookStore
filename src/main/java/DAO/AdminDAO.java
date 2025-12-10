package DAO;

import models.Category;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    public int countTotalEBook() {
        String sql = "SELECT COUNT(*) FROM ebook";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
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

    public double getMonthlyRevenue() {
        String sql =
                "SELECT COALESCE(SUM(amount), 0) " +
                        "FROM checkout " +
                        "WHERE MONTH(checkoutDate) = MONTH(CURDATE()) " +
                        "AND status = 'success'";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public int countSuccessOrder() {
        String sql = "SELECT COUNT(*) FROM checkout WHERE status = 'success'";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<Category>();
        String sql = "SELECT * FROM category";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Category c = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addCategory(Category category){
        String sql = "insert into category (name, description) values (?, ?)";
        try(Connection connection = DBConnection.getConnection();
        PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setString(1, category.getName());
            stm.setString(2, category.getDescription());
            int rows = stm.executeUpdate();
            return rows > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
