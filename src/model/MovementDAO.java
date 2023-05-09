package model;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class MovementDAO {

	private Connection connection;
	private static ZoneId zoneId = ZoneId.of("Europe/Paris");

	public MovementDAO() {
		connection = Connector.getConnection();
	}

	public ArrayList<MovementModel> getEntries() {
		ArrayList<MovementModel> entries = new ArrayList<MovementModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Mouvement where type_mouv = 'E' order by date_mouv desc");

			while(resultSet.next()) {
				MovementModel movement = new MovementModel();
				movement.setId(resultSet.getInt("id_mouv"));
				movement.setMovementType(resultSet.getString("type_mouv"));
				movement.setMovementQuantity(resultSet.getInt("qte_mouv"));
				movement.setMovementTime((resultSet.getObject("date_mouv", OffsetDateTime.class).atZoneSimilarLocal(zoneId)));
				movement.setProductId(resultSet.getInt("id_produit"));
				entries.add(movement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return entries;
	}
	
	public ArrayList<MovementModel> getRemovals() {
		ArrayList<MovementModel> removals = new ArrayList<MovementModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Mouvement where type_mouv = 'S' order by date_mouv desc");

			while(resultSet.next()) {
				MovementModel movement = new MovementModel();
				movement.setId(resultSet.getInt("id_mouv"));
				movement.setMovementType(resultSet.getString("type_mouv"));
				movement.setMovementQuantity(resultSet.getInt("qte_mouv"));
				movement.setMovementTime((resultSet.getObject("date_mouv", OffsetDateTime.class).atZoneSimilarLocal(zoneId)));
				movement.setProductId(resultSet.getInt("id_produit"));
				removals.add(movement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return removals;
	}

	public MovementModel getMovement(int id) {
		MovementModel movement = new MovementModel();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Produit where id_produit = "+id);
			while(resultSet.next()) {
				movement.setId(id);
				movement.setMovementType(resultSet.getString("type_mouv"));
				movement.setMovementQuantity(resultSet.getInt("qte_mouv"));
				movement.setMovementTime((resultSet.getObject("date_mouv", OffsetDateTime.class).atZoneSimilarLocal(zoneId)));
				movement.setProductId(resultSet.getInt("id_produit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movement;
	}
	
	public void addMovement(MovementModel movement) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into mouvement ("
					+ "type_mouv, qte_mouv, date_mouv, id_produit) "
					+ "values (?,?,now(),?);");
			
			preparedStatement.setString(1, movement.getMovementType());
			preparedStatement.setInt(2, movement.getMovementQuantity());
			preparedStatement.setInt(3, movement.getProductId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

