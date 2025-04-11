package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection connection;
	
	public static Connection getConnection() {
		
		if(connection == null) {
			try {
				
				String connectionUrl = PropertyUtil.getPropertyString();
				
				if(connectionUrl != null) {
					connection = DriverManager.getConnection(connectionUrl);
					System.out.println("Database connected successfully!");
				}
				
				else {
					System.out.println("Connection string is null.");
				}
			}
			
			catch(SQLException e) {
				System.out.println("Failed to connect to database");
				e.printStackTrace();
			}
		}
		
		return connection;
		
	}
}
