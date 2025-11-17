package com.sandeep.video;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class VideoRetriever {
	public static void main(String[] args) {
		String dbURL = "jdbc:postgresql://localhost:5432/project";
		String user = "postgres";
		String password = "root";
		
		String sql = "SELECT file_data FROM files WHERE filename = ?";
		String outputPath = "J:/extra/retrieved_video.mp4"; // Path where you want to save the retrieved video

		try (Connection conn = DriverManager.getConnection(dbURL, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, "video1.mp4"); // The filename of the video you want to retrieve
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				byte[] videoBytes = rs.getBytes("file_data");

				// Save the video to your desired location
				try (FileOutputStream fos = new FileOutputStream(outputPath)) {
					fos.write(videoBytes);
				}

				System.out.println("Video has been retrieved and saved to: " + outputPath);
			} else {
				System.out.println("No video found with the specified filename.");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
