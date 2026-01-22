package DAO;

import models.Image;
import utils.DBConnection;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ImageDAO {
    public Image getImageById(int id) {
        Image result = null;
        String sql = "SELECT * FROM image WHERE id = ? ";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result = new Image(rs.getInt("id"), rs.getString("imgName"), rs.getString("imgLink"), rs.getString("imgStatus"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
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
        INSERT INTO Image (imgName, imgLink, imgStatus)
        VALUES (?, ?, ?)
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, image.getImgName());
            ps.setString(2, image.getImgLink());
            ps.setString(3, image.getImgStatus());

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

    public static void main(String[] args) {
        ImageDAO imgDAO = new ImageDAO();
        System.out.println(imgDAO.getImageById(1).getImgLink());
    }
}
