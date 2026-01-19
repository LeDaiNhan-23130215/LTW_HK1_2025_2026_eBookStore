package DAO;

import models.Author;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorDAO {
    public Author getAuthorByID (int id) {
        String query = "SELECT * FROM author WHERE id = ? LIMIT 1";
        Author author = null;

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                author = new Author(rs.getInt("id"), rs.getString("authorName"),
                        rs.getString("authorDetail"), rs.getInt("birthYear"), rs.getString("nationality")
                        , rs.getInt("numberOfBook"), rs.getString("awards"));
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
                author.setAuthorName(rs.getString("authorName"));
                author.setAuthorDetail(rs.getString("authorDetail"));

                authorMap.put(author.getId(), author);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return authorMap;
    }

    public boolean updateAuthor(Author author){
        String sql = "update author set authorName = ?, authorDetail = ?, birthYear = ?, nationality = ?, numberOfBook = ?, awards = ? where id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setString(1, author.getAuthorName());
            stm.setString(2, author.getAuthorDetail());
            stm.setInt(3, author.getBirthYear());
            stm.setString(4, author.getNationality());
            stm.setInt(5, author.getNumberOfBooks());
            stm.setString(6, author.getAwards());
            stm.setInt(7, author.getId());
            int rows = stm.executeUpdate();
            return rows > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAuthor(int id){
        String sql = "delete from author where id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setInt(1, id);
            int rows = stm.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addAuthor(Author author){
        String sql = "insert into author (authorName, authorDetail, birthYear, nationality, numberOfBook, awards) values (?, ?, ?, ?, ?, ?)";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setString(1, author.getAuthorName());
            stm.setString(2, author.getAuthorDetail());
            stm.setInt(3, author.getBirthYear());
            stm.setString(4, author.getNationality());
            stm.setInt(5, author.getNumberOfBooks());
            stm.setString(6, author.getAwards());
            int rows = stm.executeUpdate();
            return rows > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Author> getAllAuthors() {
        List<Author> list = new ArrayList<Author>();
        String sql = "SELECT * FROM author";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Author author = new Author(rs.getInt("id"), rs.getString("authorName"),
                        rs.getString("authorDetail"), rs.getInt("birthYear"), rs.getString("nationality")
                        , rs.getInt("numberOfBook"), rs.getString("awards"));
                list.add(author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
