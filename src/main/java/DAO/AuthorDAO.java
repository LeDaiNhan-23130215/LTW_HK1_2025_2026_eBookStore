package DAO;

import models.Author;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

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

    public Map<Integer, Author> getAuthorMap(){
        Map<Integer, Author> authorMap = new HashMap<Integer, Author>();
        String sql = "SELECT id,authorName,authorDetail FROM author";
        try(Connection connection = DBConnection.getConnection();
        PreparedStatement stm = connection.prepareStatement(sql)){
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                Author author = new Author(id);
                author.setName(rs.getString("authorName"));
                author.setAuthorDetail(rs.getString("authorDetail"));

                authorMap.put(author.getId(), author);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return authorMap;
    }
}
