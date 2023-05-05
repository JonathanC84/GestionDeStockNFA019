package model;

import java.sql.*;

public class Connector {

	private static String url = "jdbc:mysql://localhost:3306/bdStock?useSSL=false&serverTimezone=UTC";
	private static String user = "root";
	private static String password = "";
	private static Connection connection = null;
	
	public Connector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if (connection == null) {
			new Connector();
		}
		return connection;
	}
	
	public static void stop() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
