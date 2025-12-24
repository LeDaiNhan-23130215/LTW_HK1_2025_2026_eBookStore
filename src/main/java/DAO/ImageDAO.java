package DAO;

import models.Image;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static void main(String[] args) {
        ImageDAO imgDAO = new ImageDAO();
        System.out.println(imgDAO.getImageById(1).getImgLink());
    }
}
