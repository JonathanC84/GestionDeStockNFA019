package model;

import java.sql.*;

public class UserDAO {

	private Connection connection;
	
	public UserDAO() {
		connection = Connector.getConnection();
	}
	
	private boolean authUser(String login, String password) {
		boolean checked = false;
		String query = "select * from Utilisateur where login_user = '"+login+"' and mdp_user = '"+password+"';";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				checked = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checked;
	}
	
	private String userName(String login) {
		String prenom = "";
		String nom = "";
		String query = "select * from Utilisateur where login_user = '"+login+"';";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				prenom = resultSet.getString("prenom_user");
				nom = resultSet.getString("nom_user");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (prenom+" "+nom);
	}
	
	public void addUser() {
		
	}
	
	public void editUser() {
		
	}
	
	public void deleteUser() {
		
	}
	
	public boolean getAuthUser(String login, String password) {
		return authUser(login, password);
	}
	
	public String getUserName(String login) {
		return userName(login);
	}
	
}
