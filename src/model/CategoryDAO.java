package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryDAO {

	private Connection connection;
	
	public CategoryDAO() {
		connection = Connector.getConnection();
	}
	
	public ArrayList<CategoryModel> getCategories() {
		ArrayList<CategoryModel> categories = new ArrayList<CategoryModel>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Categorie order by id_cat");
					
			while(resultSet.next()) {
				CategoryModel category = new CategoryModel();
				category.setId(resultSet.getInt("id_cat"));
				category.setCatName(resultSet.getString("nom_cat"));
				category.setCatDesc(resultSet.getString("desc_cat"));
				
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return categories;
	}
}
