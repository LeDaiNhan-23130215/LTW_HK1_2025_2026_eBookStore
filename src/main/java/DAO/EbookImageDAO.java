package DAO;

import models.EbookImage;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EbookImageDAO {
    public EbookImage getEbookImageById (int id) {
        EbookImage ebookImage = null;
        String query = "SELECT imgName, imgLink, imgStatus FROM image WHERE id = ? ";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            String name;
            String imgLink;
            String imgStatus;

            while(rs.next()) {
                name = rs.getString("name");
                imgLink = rs.getString("imgLink");
                imgStatus = rs.getString("imgStatus");

                ebookImage = new EbookImage(id, name, imgLink, imgStatus);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ebookImage;
    }
}
