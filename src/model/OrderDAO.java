package model;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class OrderDAO {

	private Connection connection;
	private ProductDAO productDAO = new ProductDAO();
	private static ZoneId zoneId = ZoneId.of("Europe/Paris");

	public OrderDAO() {
		connection = Connector.getConnection();
	}

	public ArrayList<OrderModel> getOrders() {
		ArrayList<OrderModel> orders = new ArrayList<OrderModel>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Commande order by id_fourn");

			while(resultSet.next()) {
				OrderModel order = new OrderModel();
				order.setId(resultSet.getInt("id_commande"));
				order.setOrderRef(resultSet.getString("num_commande"));
				order.setOrderDate((resultSet.getObject("date_commande", OffsetDateTime.class).atZoneSimilarLocal(zoneId)));
				order.setOrderTotalPrice(resultSet.getDouble("cout_total"));
				order.setOrderSupplierId(resultSet.getInt("id_fourn"));

				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	public ArrayList<ProductModel> getOrderProducts(int id) {
		ArrayList<ProductModel> orderProducts = new ArrayList<ProductModel>();
		ArrayList<ProductModel> allProducts = productDAO.getAllProducts();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from LigneCommande where id_commande = "+id+";");

			while(resultSet.next()) {
				ProductModel newProduct = new ProductModel();
				for(ProductModel product : allProducts) {
					if(product.getId() == resultSet.getInt("id_produit")) {
						newProduct = product;
					}
				}
				newProduct.setProdQuantity(resultSet.getInt("qte_commande"));
				orderProducts.add(newProduct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderProducts;
	}
	
	public void addOrder(OrderModel newOrder) {
		System.out.println("Ajout de "+newOrder.getOrderRef()+" dans la base");
	}
}
