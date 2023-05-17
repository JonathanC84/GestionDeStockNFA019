package controller;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.OrderModel;
import model.ProductModel;
import model.SupplierModel;
import view.OrderOptionView;
import view.SupplierOptionView;
import view.View;
import model.OrderDAO;


public class OrderController {
	
	private OrderDAO orderDAO = new OrderDAO();
	private ImageIcon editIcon = new ImageIcon(getClass().getClassLoader().getResource("modify32.png"));
	private ImageIcon deleteIcon = new ImageIcon(getClass().getClassLoader().getResource("delete32.png"));
	
	public ArrayList<OrderModel> getAllOrders() {
		ArrayList<OrderModel> allOrders = orderDAO.getOrders();
		
		for(OrderModel order : allOrders) {
			order.setOrderedProducts(orderDAO.getOrderProducts(order.getId()));
		}
		
		return allOrders;		
	}
	
	public void selectOrder(View view) {
		ArrayList<OrderModel> orders = getAllOrders();
		int index = view.getOrderList().getSelectedIndex();
		ArrayList<ProductModel> products = orders.get(index).getOrderedProducts();
		if(index < 0)
			return;
		else {
			view.getOrderDetailList().setListData(products.toArray());
		}
	}

	public void addOrder(View view) {
		OrderModel newOrder = new OrderModel();
		OrderOptionView oov = new OrderOptionView();
		
		int option = JOptionPane.showConfirmDialog(null, oov.getPanel(newOrder) , "Création d'une "
				+ "commande", JOptionPane.OK_CANCEL_OPTION, 1, editIcon);
		
		if(option == JOptionPane.OK_OPTION) {
			try {
				newOrder.setOrderRef("NEW");
				
				orderDAO.addOrder(newOrder);
				view.getOrderList().setListData(orderDAO.getOrders().toArray());
			} catch (Exception e) {
				return;
			}
		}
	}
	
	public void editOrder() {
		System.out.println("Modifie une commande");
	}
	
	public void deleteOrder() {
		System.out.println("Supprime une commande");
	}
}
