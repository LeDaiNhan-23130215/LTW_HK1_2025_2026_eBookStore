package DAO;

import models.Category;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CategoryDAO {
    public Category getCategoryById (int id) {
        Category category = null;

        String query = "SELECT categoryName, description FROM category WHERE id = ?";

        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
}
