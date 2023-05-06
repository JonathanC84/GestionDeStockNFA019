package model;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {

	private Connection connection;
	
	public ProductDAO() {
		connection = Connector.getConnection();
	}

	// récupération des données sous forme d'ArrayList
	public ArrayList<ProductModel> getAllProducts() {
		ArrayList<ProductModel> allProducts = new ArrayList<ProductModel>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select id_produit, ref_produit, nom_produit, desc_produit, qte_produit, duree_conservation, prix_unit, nom_cat, nom_fourn "
					+ "from Produit P, Categorie C, Fournisseur F "
					+ "where P.id_cat = C.id_cat and P.id_fourn = F.id_fourn "
					+ "order by id_produit;");
			while(resultSet.next()) {
				ProductModel product = new ProductModel();
				product.setId(resultSet.getInt("id_produit"));
				product.setProdRef(resultSet.getString("ref_produit"));
				product.setProdName(resultSet.getString("nom_produit"));
				product.setProdDesc(resultSet.getString("desc_produit"));
				product.setProdQuantity(resultSet.getInt("qte_produit"));
				product.setProdExpTime(resultSet.getInt("duree_conservation"));
				product.setProdUnitPrice(resultSet.getDouble("prix_unit"));
				product.setProdCategory(resultSet.getString("nom_cat"));
				product.setProdSupplier(resultSet.getString("nom_fourn"));
				allProducts.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allProducts;
	}
	
	public ProductTableModel productTableModel() {
		return new ProductTableModel(getAllProducts());
	}
	
	public void deleteProduct(int id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from produit where id_produit="+id);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
