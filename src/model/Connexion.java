package model;

import java.sql.*;

public class Connexion {

	private static String url = "jdbc:mysql://localhost:3306/bdStock?useSSL=false&serverTimezone=UTC";
	private static String user = "root";
	private static String password = "";
	private static Connection connection = null;
	
	public Connexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if (connection == null) {
			new Connexion();
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
