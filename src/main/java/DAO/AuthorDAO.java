package DAO;

import models.Author;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthorDAO {
    public Author getAuthorByID (int id) {
        String query = "SELECT authorName,authorDetail FROM author WHERE id = ? LIMIT 1";
        Author author = null;

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            String authorName;
            String authorDetail;

            while(rs.next()) {
                authorName = rs.getString("authorName");
                authorDetail = rs.getString("authorDetail");
                author = new Author(id, authorName, authorDetail);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return author;
    }
}
