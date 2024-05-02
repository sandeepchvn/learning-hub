package com.ty;

import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/school", "postgres", "root");
//			System.out.println(con);
			PreparedStatement ps = con.prepareStatement("insert into user_2 values(?,?)");
			ps.setInt(1, 108);
			ps.setString(2, "sandyy");
			int n = ps.executeUpdate();
			System.out.println(n);
//			Statement stm = con.createStatement();
//			System.out.println(stm.execute("select * from user_2 where id=99"));
//			ResultSet rs = stm.executeQuery("select * from user_2 where id=99");
//			ResultSet rs = stm.getResultSet();
//			while (rs.next()) {
//				System.out.println(rs.getInt(1));
//				System.out.println(rs.getString(2));
//			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
