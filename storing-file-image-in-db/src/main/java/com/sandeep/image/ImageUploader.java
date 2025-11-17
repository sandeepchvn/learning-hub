package com.sandeep.image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class ImageUploader {
    public static void main(String[] args) {
        String dbURL = "jdbc:postgresql://localhost:5432/project";
        String user = "postgres";
        String password = "root";

        String filePath = "J:\\extra\\photo_6118286217616407051_y.jpg";  // Path to your image file

        String sql = "INSERT INTO images (filename, image_data) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Read the image file into a byte array
            File image = new File(filePath);
            FileInputStream fis = new FileInputStream(image);
            byte[] imageData = fis.readAllBytes();
            fis.close();

            // Set the parameters for the PreparedStatement
            pstmt.setString(1, image.getName());  // Use the image's filename
            pstmt.setBytes(2, imageData);         // Set the image data as byte array

            // Execute the insert operation
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Image has been inserted successfully!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
