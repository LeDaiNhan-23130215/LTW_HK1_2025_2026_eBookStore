package DAO;

import models.File;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FileDAO {
    public int insertAndReturnId(File file) {
        String sql = """
        INSERT INTO File (fileName, fileFormat, fileSize, fileLink, fileStatus)
        VALUES (?, ?, ?, ?, ?)
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, file.getFileName());
            ps.setString(2, file.getFileFormat());
            ps.setLong(3, file.getFileSize());
            ps.setString(4, file.getFileLink());
            ps.setString(5, file.getFileStatus());

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
}
