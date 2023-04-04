package model;

import java.sql.*;

public class Connexion {

	private static String url = "jdbc:mysql://localhost:3306/bdcontact?useSSL=false&serverTimezone=UTC";
	private static String user = "root";
	private static String password = "";
	private static Connection connexion = null;
	
	public Connexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connexion = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if (connexion == null) {
			new Connexion();
		}
		return connexion;
	}
	
	public static void stop() {
		if (connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
