package DAO;

import DTO.AdminEbookView;
import DTO.EbookFilterView;
import DTO.EbookProductCardView;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Ebook> getNewBook() {
        List<Ebook> ebooks = new ArrayList<>();
        String sql = "SELECT id, title, authorID, price, imageID, description, categoryID, fullFileID, demoFileID, status " +
                "FROM ebook " +
                "WHERE status = 'ACTIVE' " +  // FIXED: Added space before ORDER BY
                "ORDER BY id DESC " +
                "LIMIT 15";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                ebooks.add(new Ebook(
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
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ebooks;
    }

    public List<Ebook> findAll() {
        List<Ebook> result = new ArrayList<>();
        String sql = "SELECT * FROM ebook";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result.add(new Ebook(
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
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<EbookProductCardView> findProductCards(
            int page, int pageSize, EbookFilterView filter) {

        List<EbookProductCardView> result = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
        SELECT 
            e.id,
            e.title,
            e.price,
            i.imgLink
        FROM ebook e
        JOIN image i ON e.imageID = i.id
        JOIN author a ON e.authorID = a.id
        WHERE e.status = 'ACTIVE'
        """);

        List<Object> params = new ArrayList<>();
        applyFilter(sql, params, filter);

        // ===== SORTING (ALWAYS APPLY) =====
        sql.append(" ORDER BY ");

        String sortBy = filter.getSortBy();
        String sortDir = filter.getSortDir();

        // Default values if not specified
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "created_at";
        }
        if (sortDir == null || sortDir.isEmpty()) {
            sortDir = "desc";
        }

        // Apply sort field
        switch (sortBy.toLowerCase()) {
            case "title":
                sql.append("e.title");
                break;
            case "price":
                sql.append("e.price");
                break;
            case "created_at":
                sql.append("e.id"); // Using id as proxy for created_at
                break;
            default:
                sql.append("e.id"); // Default sort by ID (newest first)
        }

        // Apply sort direction
        sql.append(" ");
        sql.append("desc".equalsIgnoreCase(sortDir) ? "DESC" : "ASC");

        // ===== PAGINATION =====
        sql.append(" LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add((page - 1) * pageSize);

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            bindParams(ps, params);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(new EbookProductCardView(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("price"),
                        rs.getString("imgLink")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int countProductCards(EbookFilterView filter) {

        StringBuilder sql = new StringBuilder("""
        SELECT COUNT(e.id)
        FROM ebook e
        JOIN author a ON e.authorID = a.id
        WHERE e.status = 'ACTIVE'
        """);

        List<Object> params = new ArrayList<>();
        applyFilter(sql, params, filter);

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            bindParams(ps, params);

            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AdminEbookView> findAllForAdmin() {

        List<AdminEbookView> list = new ArrayList<>();

        String sql = """
        SELECT 
            e.id,
            e.title,
            a.authorName,
            c.categoryName,
            e.price
        FROM ebook e
        JOIN author a ON e.authorID = a.id
        JOIN category c ON e.categoryID = c.id
        ORDER BY e.id DESC
    """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new AdminEbookView(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("authorName"),
                        rs.getString("categoryName"),
                        rs.getDouble("price")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public boolean insert(Ebook e) {

        String sql = """
        INSERT INTO ebook
        (title, authorID, price, imageID, description,
         categoryID, fullFileID, demoFileID, status)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'ACTIVE')
    """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getTitle());
            ps.setInt(2, e.getAuthorID());
            ps.setDouble(3, e.getPrice());
            ps.setInt(4, e.getImageID());
            ps.setString(5, e.getDescription());
            ps.setInt(6, e.getCategoryID());
            ps.setInt(7, e.getFullFileID());
            ps.setInt(8, e.getDemoFileID());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Ebook getByIdForAdmin(int id) {

        String sql = "SELECT * FROM ebook WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

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
            throw new RuntimeException(e);
        }

        return null;
    }

    public boolean update(Ebook e) {

        String sql = """
        UPDATE ebook
        SET title = ?,
            authorID = ?,
            price = ?,
            imageID = ?,
            description = ?,
            categoryID = ?,
            fullFileID = ?,
            demoFileID = ?
        WHERE id = ?
    """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getTitle());
            ps.setInt(2, e.getAuthorID());
            ps.setDouble(3, e.getPrice());
            ps.setInt(4, e.getImageID());
            ps.setString(5, e.getDescription());
            ps.setInt(6, e.getCategoryID());
            ps.setInt(7, e.getFullFileID());
            ps.setInt(8, e.getDemoFileID());
            ps.setInt(9, e.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean delete(int id) {

        String sql = "UPDATE ebook SET status = 'INACTIVE' WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void applyFilter(StringBuilder sql,
                             List<Object> params,
                             EbookFilterView f) {

        // ===== FREE/PAID FILTER =====
        if (f.getFree() != null) {
            sql.append(f.getFree() ? " AND e.price = 0 " : " AND e.price > 0 ");
        }

        // ===== CATEGORY FILTER =====
        if (f.getCategoryId() != null && !f.getCategoryId().isEmpty()) {
            sql.append(" AND e.categoryID IN (");
            sql.append("?,".repeat(f.getCategoryId().size() - 1)).append("?)");
            params.addAll(f.getCategoryId());
        }

        // ===== FORMAT FILTER (NEW) =====
        if (f.getFormats() != null && !f.getFormats().isEmpty()) {
            sql.append(" AND e.fat ormIN (");
            for (int i = 0; i < f.getFormats().size(); i++) {
                sql.append("?");
                if (i < f.getFormats().size() - 1) {
                    sql.append(",");
                }
            }
            sql.append(")");
            params.addAll(f.getFormats());
        }

        if (f.getKeywords() != null && !f.getKeywords().isEmpty()) {
            sql.append(" AND (e.title LIKE ? OR a.authorName LIKE ?)");
            params.add("%" + f.getKeywords() + "%");
            params.add("%" + f.getKeywords() + "%");
        }
    }

    public void bindParams(PreparedStatement ps, List<Object> params) throws SQLException {
        for(int i = 0; i < params.size(); i++){
            ps.setObject(i + 1, params.get(i));
        }
    }

    public static void main(String[] args) {
        // Test code here
    }
}