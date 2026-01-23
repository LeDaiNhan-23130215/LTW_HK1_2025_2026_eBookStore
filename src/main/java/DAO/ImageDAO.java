package DAO;

import models.Image;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageDAO {
    public void insert(Image image) {
        String sql = """
            INSERT INTO image (ebookID, imgName, imgLink, imgStatus)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, image.getEbookID());
            ps.setString(2, image.getImgName());
            ps.setString(3, image.getImgLink());
            ps.setString(4, image.getImgStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getImageById(int id) {
        Image result = null;
        String sql = "SELECT * FROM image WHERE id = ? ";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result = new Image(rs.getInt("id"), rs.getInt("ebookID"),rs.getString("imgName"), rs.getString("imgLink"), rs.getString("imgStatus"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Image getFirstImageByEbookID(int ebookID) {
        String sql = """
        SELECT * FROM image
        WHERE ebookID = ? AND imgStatus = 'ACTIVE'
        ORDER BY id ASC
        LIMIT 1
    """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, ebookID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Image(
                        rs.getInt("id"),
                        rs.getInt("ebookID"),
                        rs.getString("imgName"),
                        rs.getString("imgLink"),
                        rs.getString("imgStatus")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Map<Integer, Image> getImagesMap() {
        Map<Integer, Image> imageMap = new HashMap<Integer, Image>();
        String sql = "SELECT * FROM image";
        try(Connection connection = DBConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                Image image = new Image(id);
                image.setEbookID(rs.getInt("ebookID"));
                image.setImgLink(rs.getString("imgLink"));
                image.setImgName(rs.getString("imgName"));
                image.setImgStatus(rs.getString("imgStatus"));
                imageMap.put(image.getId(), image);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return imageMap;
    }

    public int insertAndReturnId(Image image) {
        String sql = """
        INSERT INTO Image (imgName, ebookID, imgLink, imgStatus)
        VALUES (?, ?, ?, ?)
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, image.getImgName());
            ps.setInt(2, image.getEbookID());
            ps.setString(3, image.getImgLink());
            ps.setString(4, image.getImgStatus());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Image> getByEbookID(int ebookID) {
        List<Image> list = new ArrayList<>();

        String sql = """
            SELECT * FROM image
            WHERE ebookID = ? AND imgStatus = 'ACTIVE'
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ebookID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Image(
                        rs.getInt("id"),
                        rs.getInt("ebookID"),
                        rs.getString("imgName"),
                        rs.getString("imgLink"),
                        rs.getString("imgStatus")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void main(String[] args) {
        ImageDAO imgDAO = new ImageDAO();
        System.out.println(imgDAO.getImageById(1).getImgLink());
    }
}
