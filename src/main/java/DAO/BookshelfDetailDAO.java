package DAO;

import models.Ebook;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookshelfDetailDAO {
    public boolean addBook(int bookshelfId, int ebookId) {
        String sql = """
            INSERT INTO bookshelfdetail (bsID, eBookID, addedAt)
            VALUES (?, ?, CURRENT_TIMESTAMP)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookshelfId);
            ps.setInt(2, ebookId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(int bookshelfId, int ebookId) {
        String sql = """
            SELECT 1 FROM bookshelfdetail
            WHERE id = ? AND eBookID = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookshelfId);
            ps.setInt(2, ebookId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Ebook> getBooksByUser(int userId) {
        List<Ebook> books = new ArrayList<>();

        String sql = """
                SELECT e.id, e.title, e.price
                FROM bookshelfdetail bd
                JOIN bookshelf b ON bd.bsID = b.id
                JOIN ebook e ON bd.ebookID = e.id
                WHERE b.userID = ?
                ORDER BY bd.addedAt DESC
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    Ebook ebook = new Ebook(id);
                    ebook.setTitle(rs.getString("title"));
                    ebook.setPrice(rs.getDouble("price"));

                    books.add(ebook);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
}
