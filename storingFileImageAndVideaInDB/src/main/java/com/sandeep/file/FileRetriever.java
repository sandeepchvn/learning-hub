package com.sandeep.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class FileRetriever {
    public static void main(String[] args) {
        String dbURL = "jdbc:postgresql://localhost:5432/project";
        String user = "postgres";
        String password = "root";

        String sql = "SELECT file_data FROM files WHERE filename = ?";
        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "The-Awakening-Of-Intelligence.pdf");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                byte[] fileBytes = rs.getBytes("file_data");

                // Save the file to your desired location
                try (FileOutputStream fos = new FileOutputStream("J:\\extra\\example1.pdf")) {
                    fos.write(fileBytes);
                }

                System.out.println("File has been retrieved and saved.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

