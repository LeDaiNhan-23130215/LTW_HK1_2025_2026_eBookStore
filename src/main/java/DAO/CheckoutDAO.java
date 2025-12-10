package DAO;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckoutDAO {
    public double getMonthlyRevenue() {
        String sql =
                "SELECT COALESCE(SUM(totalAmount), 0) " +
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
}
