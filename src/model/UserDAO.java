package model;

import java.sql.*;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDAO {

	private Connection connection;
	private static ZoneId zoneId = ZoneId.of("Europe/Paris");
	private static DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy - HH:mm:ss");

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
			userDetails.put("ddn", getLastConnectionDate(login));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userDetails;
	}

	public ArrayList<UserModel> getUsers() {
		ArrayList<UserModel> users = new ArrayList<>();
		String query = "select * from Utilisateur;";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				UserModel user = new UserModel();
				user.setId(resultSet.getInt("id_user"));
				user.setPrenom(resultSet.getString("prenom_user"));
				user.setNom(resultSet.getString("nom_user"));
				user.setLogin(resultSet.getString("login_user"));
				user.setPassword(resultSet.getString("mdp_user"));
				user.setRole(resultSet.getString("role_user"));
				user.setDdc((resultSet.getObject("dern_conn", OffsetDateTime.class).atZoneSimilarLocal(zoneId)));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public void addUser(UserModel newUser) {
		String query = "insert into utilisateur (prenom_user, nom_user, login_user, mdp_user, role_user, dern_conn) "
				+ "values (?,?,?,?,?,now())";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, newUser.getPrenom());
			preparedStatement.setString(2, newUser.getNom());
			preparedStatement.setString(3, newUser.getLogin());
			preparedStatement.setString(4, newUser.getPassword());
			preparedStatement.setString(5, newUser.getRole());

			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void editUser(UserModel user) {
		String query = "update utilisateur set prenom_user = ?, nom_user = ?, login_user = ?, mdp_user = ?, role_user = ? "
				+ "where id_user = " + user.getId() + ";";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, user.getPrenom());
			preparedStatement.setString(2, user.getNom());
			preparedStatement.setString(3, user.getLogin());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getRole());

			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(int id) {
		String query = "delete from utilisateur where id_user = "+ id +";";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public boolean getAuthUser(String login, String password) {
		return authUser(login, password);
	}
	
	public String getLastConnectionDate(String login) {
		String query = "select dern_conn from utilisateur where login_user = '" + login + "';";
		String ddn = "";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if(resultSet.next()) {
				ddn = DT_FORMATTER.format(resultSet.getObject("dern_conn", OffsetDateTime.class).atZoneSimilarLocal(zoneId));
			}
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		return ddn;
	}
	
	public void setLastConnectionDate(String login) {
		String query = "update utilisateur set dern_conn = current_time() where login_user = '" + login + "';";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, String> getUserDetails(String login) {
		return userDetails(login);
	}

}
