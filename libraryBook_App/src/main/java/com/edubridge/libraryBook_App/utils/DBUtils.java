package com.edubridge.libraryBook_App.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	public static Connection getconnection() {
		
			
			Connection con = null;
			
			String driver ="com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/java_sql";
			String user = "root";
			String password = "root";
			
			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				con = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			return con;
		}

}
