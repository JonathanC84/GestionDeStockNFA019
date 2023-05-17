package model;

import java.sql.*;
import java.util.ArrayList;

public class SupplierDAO {

	private Connection connection;
	
	public SupplierDAO() {
		connection = Connector.getConnection();
	}
	
	public ArrayList<SupplierModel> getSuppliers() {
		ArrayList<SupplierModel> suppliers = new ArrayList<SupplierModel>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Fournisseur order by id_fourn");
					
			while(resultSet.next()) {
				SupplierModel supplier = new SupplierModel();
				supplier.setId(resultSet.getInt("id_fourn"));
				supplier.setSupplierName(resultSet.getString("nom_fourn"));
				supplier.setSupplierAddress(resultSet.getString("adresse_fourn"));
				supplier.setSupplierPhoneNumber(resultSet.getString("tel_fourn"));
				suppliers.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return suppliers;
	}
	
	public ArrayList<SupplierModel> getVisibleSuppliers() {
		ArrayList<SupplierModel> suppliers = new ArrayList<SupplierModel>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Fournisseur where est_visible > 0 order by id_fourn");
					
			while(resultSet.next()) {
				SupplierModel supplier = new SupplierModel();
				supplier.setId(resultSet.getInt("id_fourn"));
				supplier.setSupplierName(resultSet.getString("nom_fourn"));
				supplier.setSupplierAddress(resultSet.getString("adresse_fourn"));
				supplier.setSupplierPhoneNumber(resultSet.getString("tel_fourn"));
				suppliers.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return suppliers;
	}
	
	public void addSupplier(SupplierModel supplier) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into fournisseur ("
					+ "nom_fourn, adresse_fourn, tel_fourn) "
					+ "values (?,?,?);");
		
			preparedStatement.setString(1, supplier.getSupplierName());
			preparedStatement.setString(2, supplier.getSupplierAddress());
			preparedStatement.setString(3, supplier.getSupplierPhoneNumber());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editSupplier(SupplierModel supplier) {
		int id = supplier.getId();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update fournisseur set "
					+ "nom_fourn = ?, "
					+ "adresse_fourn = ?, "
					+ "tel_fourn = ? "
					+ "where id_fourn = "+id+";");
		
			preparedStatement.setString(1, supplier.getSupplierName());
			preparedStatement.setString(2, supplier.getSupplierAddress());
			preparedStatement.setString(3, supplier.getSupplierPhoneNumber());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSupplier(int id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update fournisseur set est_visible = 0 where id_fourn="+id);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
