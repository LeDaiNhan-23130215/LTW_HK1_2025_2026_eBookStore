package DAO;

import models.Ebook;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EbookDAO {
    public Ebook getEbookByID(int id) {
        Ebook ebook = null;
        return ebook;
    }
    public int countTotalEBook() {
        String sql = "SELECT COUNT(*) FROM ebook";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }
}
