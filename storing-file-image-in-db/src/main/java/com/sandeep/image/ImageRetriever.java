package com.sandeep.image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class ImageRetriever {
    public static void main(String[] args) {
    	 String dbURL = "jdbc:postgresql://localhost:5432/project";
         String user = "postgres";
         String password = "root";

        String sql = "SELECT image_data FROM images WHERE filename = ?";
        String outputPath = "J:\\extra\\example1\\test.jpg";  // Path where you want to save the retrieved image

        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "photo_6118286217616407051_y.jpg");  // The filename you want to retrieve
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                byte[] imageBytes = rs.getBytes("image_data");

                // Save the image to your desired location
                try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                    fos.write(imageBytes);
                }

                System.out.println("Image has been retrieved and saved to: " + outputPath);
            } else {
                System.out.println("No image found with the specified filename.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

