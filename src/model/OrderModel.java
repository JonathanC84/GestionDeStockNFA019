package model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrderModel {

	private int id;
	private String orderRef;
	private ZonedDateTime orderDate;
	private double orderTotalPrice;
	private int orderSupplierId;
	private ArrayList<ProductModel> orderedProducts;
	private SupplierDAO supplierDAO;
	private static DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

	public OrderModel() {
		this.id = 0;
		this.orderRef = "";
		this.orderDate = null;
		this.orderTotalPrice = 0;
		this.orderSupplierId = 0;
		this.orderedProducts = new ArrayList<ProductModel>();
	}

	public int getId() {
		return id;
	}

	public String getOrderRef() {
		return orderRef;
	}

	public ZonedDateTime getOrderDate() {
		return orderDate;
	}

	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public int getOrderSupplierId() {
		return orderSupplierId;
	}

	public ArrayList<ProductModel> getOrderedProducts() {
		return orderedProducts;
	}

	public int getProductsCount() {
		return orderedProducts.size();
	}
	
	public int getTotalQuantity() {
		int totalQuantity = 0;
		for(ProductModel product : orderedProducts) {
			totalQuantity += product.getProdQuantity();
		}
		return totalQuantity;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
	}

	public void setOrderDate(ZonedDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public void setOrderSupplierId(int orderSupplierId) {
		this.orderSupplierId = orderSupplierId;
	}

	public void setOrderedProducts(ArrayList<ProductModel> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	
	@Override
	public String toString() {
		supplierDAO = new SupplierDAO();
		ArrayList<SupplierModel> suppliers = supplierDAO.getSuppliers();
		String supplierName = "";
		for(SupplierModel supplier : suppliers) {
			if(supplier.getId() == this.orderSupplierId)
				supplierName = supplier.getSupplierName();
		}
		
		String formattedDate = DT_FORMATTER.format(this.orderDate);
		int productsCount = this.getProductsCount();
		int totalQuantity = this.getTotalQuantity();
		
		return "N° commande : " + orderRef + " - Date : " + formattedDate + " - Nb produits : "+ productsCount + " - Qte totale : "+ totalQuantity +" - Total :  "
				+ orderTotalPrice + " € - Fournisseur : " + supplierName;
	}
	
	
}
