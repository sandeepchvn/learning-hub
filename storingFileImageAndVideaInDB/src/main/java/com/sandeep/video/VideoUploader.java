package com.sandeep.video;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class VideoUploader {
    public static void main(String[] args) {
    	 String dbURL = "jdbc:postgresql://localhost:5432/project";
         String user = "postgres";
         String password = "root";

        String filePath = "J:/extra/example1/video1.mp4";  // Path to your video file

        String sql = "INSERT INTO files (filename, file_data) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Read the video file into a byte array
            File video = new File(filePath);
            FileInputStream fis = new FileInputStream(video);
            byte[] videoData = new byte[(int) video.length()];
            fis.read(videoData);
            fis.close();

            // Set the parameters for the PreparedStatement
            pstmt.setString(1, video.getName());  // Use the video's filename
            pstmt.setBytes(2, videoData);         // Set the video data as byte array

            // Execute the insert operation
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Video has been inserted successfully!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

