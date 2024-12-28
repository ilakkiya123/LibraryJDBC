package com.library.database;

import java.sql.*;

public class Database {
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/library";
		String uname = "root";
		String password = "1234";
		
		Connection connection = null;
		return connection = DriverManager.getConnection(url, uname, password);
	}
}
