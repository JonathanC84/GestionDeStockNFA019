package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
