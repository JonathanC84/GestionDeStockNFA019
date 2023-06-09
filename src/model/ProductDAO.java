package model;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {

	private Connection connection;
	
	public ProductDAO() {
		connection = Connector.getConnection();
	}

	public ProductTableModel defaultProductTableModel(String userRole) {
		ArrayList<ProductModel> allProducts = getAllProducts();
		ArrayList<ProductModel> visibleProducts = new ArrayList<ProductModel>();
		for(ProductModel product : allProducts) {
			if(product.getIsVisible() == true)
				visibleProducts.add(product);
		}
		return new ProductTableModel(visibleProducts, userRole);
	}
	
	public ProductTableModel specialProductTableModel(ArrayList<ProductModel> products, String userRole) {
		ArrayList<ProductModel> visibleProducts = new ArrayList<ProductModel>();
		for(ProductModel product : products) {
			if(product.getIsVisible() == true)
				visibleProducts.add(product);
		}
		return new ProductTableModel(visibleProducts, userRole);
	}

	// récupération des données sous forme d'ArrayList
	public ArrayList<ProductModel> getAllProducts() {
		ArrayList<ProductModel> allProducts = new ArrayList<ProductModel>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Produit order by id_produit");
					
			while(resultSet.next()) {
				ProductModel product = new ProductModel();
				product.setId(resultSet.getInt("id_produit"));
				product.setProdRef(resultSet.getString("ref_produit"));
				product.setProdName(resultSet.getString("nom_produit"));
				product.setProdDesc(resultSet.getString("desc_produit"));
				product.setProdQuantity(resultSet.getInt("qte_produit"));
				product.setProdExpTime(resultSet.getInt("duree_conservation"));
				product.setProdUnitPrice(resultSet.getDouble("prix_unit"));
				product.setProdCategory(resultSet.getInt("id_cat"));
				product.setProdSupplier(resultSet.getInt("id_fourn"));
				product.setIsVisible(resultSet.getBoolean("est_visible"));
				allProducts.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allProducts;
	}
	
	
	public ProductModel getProduct(int id) {
		ProductModel product = new ProductModel();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Produit where id_produit = "+id);
			while(resultSet.next()) {
				product.setId(id);
				product.setProdRef(resultSet.getString("ref_produit"));
				product.setProdName(resultSet.getString("nom_produit"));
				product.setProdDesc(resultSet.getString("desc_produit"));
				product.setProdQuantity(resultSet.getInt("qte_produit"));
				product.setProdExpTime(resultSet.getInt("duree_conservation"));
				product.setProdUnitPrice(resultSet.getDouble("prix_unit"));
				product.setProdCategory(resultSet.getInt("id_cat"));
				product.setProdSupplier(resultSet.getInt("id_fourn"));
				product.setIsVisible(resultSet.getBoolean("est_visible"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	public ArrayList<ProductModel> searchProductByName(String searchInput) {
		ArrayList<ProductModel> productsFound = new ArrayList<ProductModel>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Produit "
					+ "where nom_produit like '%"+searchInput+"%';");
			while (resultSet.next()) {
				ProductModel product = new ProductModel();
				product.setId(resultSet.getInt("id_produit"));
				product.setProdRef(resultSet.getString("ref_produit"));
				product.setProdName(resultSet.getString("nom_produit"));
				product.setProdDesc(resultSet.getString("desc_produit"));
				product.setProdQuantity(resultSet.getInt("qte_produit"));
				product.setProdExpTime(resultSet.getInt("duree_conservation"));
				product.setProdUnitPrice(resultSet.getDouble("prix_unit"));
				product.setProdCategory(resultSet.getInt("id_cat"));
				product.setProdSupplier(resultSet.getInt("id_fourn"));
				product.setIsVisible(resultSet.getBoolean("est_visible"));
				productsFound.add(product);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productsFound;
	}
	
	public ArrayList<ProductModel> searchProductByRef(String searchInput) {
		ArrayList<ProductModel> productsFound = new ArrayList<ProductModel>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Produit "
					+ "where ref_produit like '%"+searchInput+"%';");
			while (resultSet.next()) {
				ProductModel product = new ProductModel();
				product.setId(resultSet.getInt("id_produit"));
				product.setProdRef(resultSet.getString("ref_produit"));
				product.setProdName(resultSet.getString("nom_produit"));
				product.setProdDesc(resultSet.getString("desc_produit"));
				product.setProdQuantity(resultSet.getInt("qte_produit"));
				product.setProdExpTime(resultSet.getInt("duree_conservation"));
				product.setProdUnitPrice(resultSet.getDouble("prix_unit"));
				product.setProdCategory(resultSet.getInt("id_cat"));
				product.setProdSupplier(resultSet.getInt("id_fourn"));
				product.setIsVisible(resultSet.getBoolean("est_visible"));
				productsFound.add(product);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productsFound;
	}
	
	public ArrayList<ProductModel> searchProductBySupplier(String searchInput) {
		ArrayList<ProductModel> productsFound = new ArrayList<ProductModel>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Produit where id_fourn in "
					+ "(select id_fourn from fournisseur where nom_fourn like '%"+searchInput+"%');");
			while (resultSet.next()) {
				ProductModel product = new ProductModel();
				product.setId(resultSet.getInt("id_produit"));
				product.setProdRef(resultSet.getString("ref_produit"));
				product.setProdName(resultSet.getString("nom_produit"));
				product.setProdDesc(resultSet.getString("desc_produit"));
				product.setProdQuantity(resultSet.getInt("qte_produit"));
				product.setProdExpTime(resultSet.getInt("duree_conservation"));
				product.setProdUnitPrice(resultSet.getDouble("prix_unit"));
				product.setProdCategory(resultSet.getInt("id_cat"));
				product.setProdSupplier(resultSet.getInt("id_fourn"));
				product.setIsVisible(resultSet.getBoolean("est_visible"));
				productsFound.add(product);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productsFound;
	}
	
	public ArrayList<ProductModel> searchProductByCategory(String searchInput) {
		ArrayList<ProductModel> productsFound = new ArrayList<ProductModel>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Produit where id_cat in "
					+ "(select id_cat from categorie where nom_cat like '%"+searchInput+"%');");
			while (resultSet.next()) {
				ProductModel product = new ProductModel();
				product.setId(resultSet.getInt("id_produit"));
				product.setProdRef(resultSet.getString("ref_produit"));
				product.setProdName(resultSet.getString("nom_produit"));
				product.setProdDesc(resultSet.getString("desc_produit"));
				product.setProdQuantity(resultSet.getInt("qte_produit"));
				product.setProdExpTime(resultSet.getInt("duree_conservation"));
				product.setProdUnitPrice(resultSet.getDouble("prix_unit"));
				product.setProdCategory(resultSet.getInt("id_cat"));
				product.setProdSupplier(resultSet.getInt("id_fourn"));
				product.setIsVisible(resultSet.getBoolean("est_visible"));
				productsFound.add(product);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productsFound;
	}
	
	public void addProduct(ProductModel product) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into produit ("
					+ "ref_produit, nom_produit, desc_produit, qte_produit, duree_conservation, prix_unit, id_cat, id_fourn) "
					+ "values (?,?,?,?,?,?,?,?);");
			
			preparedStatement.setString(1, product.getProdRef());
			preparedStatement.setString(2, product.getProdName());
			preparedStatement.setString(3, product.getProdDesc());
			preparedStatement.setInt(4, product.getProdQuantity());
			preparedStatement.setInt(5, product.getProdExpTime());
			preparedStatement.setDouble(6, product.getProdUnitPrice());
			preparedStatement.setInt(7, product.getProdCategory());
			preparedStatement.setInt(8, product.getProdSupplier());			
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editProduct(int id, ProductModel product) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update produit set "
					+ "ref_produit = ?, "
					+ "nom_produit = ?, "
					+ "desc_produit = ?, "
					+ "qte_produit = ?, "
					+ "duree_conservation = ?, "
					+ "prix_unit = ?, "
					+ "id_cat = ?, "
					+ "id_fourn = ? "
					+ "where id_produit="+id+";");
			
			preparedStatement.setString(1, product.getProdRef());
			preparedStatement.setString(2, product.getProdName());
			preparedStatement.setString(3, product.getProdDesc());
			preparedStatement.setInt(4, product.getProdQuantity());
			preparedStatement.setInt(5, product.getProdExpTime());
			preparedStatement.setDouble(6, product.getProdUnitPrice());
			preparedStatement.setInt(7, product.getProdCategory());
			preparedStatement.setInt(8, product.getProdSupplier());			
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteProduct(int id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update produit set est_visible = 0 where id_produit="+id);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
