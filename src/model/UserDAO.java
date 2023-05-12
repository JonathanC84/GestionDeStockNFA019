package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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
	
	private HashMap<String, String> userDetails(String login) {
		HashMap<String, String> userDetails = new HashMap<>();
		String query = "select * from Utilisateur where login_user = '"+login+"';";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				userDetails.put("prenom", resultSet.getString("prenom_user"));
				userDetails.put("nom", resultSet.getString("nom_user"));
				userDetails.put("role", resultSet.getString("role_user"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userDetails;
	}
	
	public ArrayList<UserModel> getAllUsers(){
		ArrayList<UserModel> users = new ArrayList<UserModel>();
		String query = "select * from Utilisateur;";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				UserModel userModel = new UserModel();
				userModel.setPrenom(resultSet.getString("prenom_user"));
				userModel.setNom(resultSet.getString("nom_user"));
				userModel.setRole(resultSet.getString("role_user"));
				userModel.setPassword(resultSet.getString("mdp_user"));
				userModel.setLogin(resultSet.getString("login_user"));
				users.add(userModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return users;
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
	
	public HashMap<String, String> getUserDetails(String login) {
		return userDetails(login);
	}

	public static void addUser(UserModel newUser) {
		// TODO Auto-generated method stub
		
	}
	
}
