package DAO;

import models.Ebook;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EbookDAO {
    public Ebook getEbookByID(int id) {
        String sql = "SELECT * FROM ebook WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Ebook(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("authorID"),
                        rs.getDouble("price"),
                        rs.getInt("imageID"),
                        rs.getString("description"),
                        rs.getInt("categoryID"),
                        rs.getInt("fullFileID"),
                        rs.getInt("demoFileID"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public List<Ebook> getNewBook() {
        List<Ebook> ebooks = new ArrayList<Ebook>();
        String sql = "SELECT id, title, authorID, price, imageID, description, categoryID, fullFileID, demoFileID, status " +
                     "FROM ebook " +
                     "WHERE status = 'ACTIVE'" +
                     "ORDER BY id DESC " +
                     "LIMIT 15;";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
        ) {
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                ebooks.add(new Ebook(rs.getInt("id"), rs.getString("title"), rs.getInt("authorID"), rs.getDouble("price"), rs.getInt("imageID"), rs.getString("description"), rs.getInt("categoryID"), rs.getInt("fullFileID"),rs.getInt("demoFileID"), rs.getString("status")));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ebooks;
    }

    public List<Ebook> findAll() {
        List<Ebook> result = new ArrayList<Ebook>();
        String sql = "SELECT * FROM ebook";
        try (Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result.add(new Ebook(rs.getInt("id"), rs.getString("title"), rs.getInt("authorID"), rs.getDouble("price"), rs.getInt("imageID"), rs.getString("description"), rs.getInt("categoryID"), rs.getInt("fullFileID"), rs.getInt("demoFileID"), rs.getString("status")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
