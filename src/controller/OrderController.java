package controller;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.OrderModel;
import model.ProductModel;
import model.SupplierDAO;
import model.SupplierModel;
import view.OrderOptionView;
import view.View;
import model.OrderDAO;


public class OrderController {

	private OrderDAO orderDAO = new OrderDAO();
	private SupplierDAO supplierDAO = new SupplierDAO();
	private ImageIcon editIcon = new ImageIcon(getClass().getClassLoader().getResource("modify32.png"));
	private ImageIcon deleteIcon = new ImageIcon(getClass().getClassLoader().getResource("delete32.png"));
	private static ZoneId zoneId = ZoneId.of("Europe/Paris");

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

		ArrayList<SupplierModel> suppliers = supplierDAO.getVisibleSuppliers();

		int option = JOptionPane.showConfirmDialog(null, oov.getPanel(newOrder) , "Création d'une "
				+ "commande", JOptionPane.OK_CANCEL_OPTION, 1, editIcon);

		if(option == JOptionPane.OK_OPTION) {
			try {
				if(oov.getRefField().getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Référence manquante");
					return;
				} else {
					String selectedSupplier = oov.getSupplierField().getSelectedItem().toString();
					int supplierId = 0;

					for (SupplierModel supplier : suppliers) {
						if(supplier.getSupplierName().equals(selectedSupplier)) {
							supplierId = supplier.getId();
						}
					}

					newOrder.setOrderRef(oov.getRefField().getText());
					newOrder.setOrderDate(OffsetDateTime.now().atZoneSimilarLocal(zoneId));
					newOrder.setOrderSupplierId(supplierId);
					orderDAO.addOrder(newOrder);
					view.getOrderList().setListData(getAllOrders().toArray());
				}
			} catch (Exception e) {
				return;
			}
		}
	}

	public void editOrder() {
		System.out.println("Modifie une commande");
	}

	public void deleteOrder(View view) {
		ArrayList<OrderModel> orders = getAllOrders();
		int index = view.getOrderList().getSelectedIndex();
		if(index < 0) {
			JOptionPane.showMessageDialog(null, "Sélectionnez une commande");
			return;
		} else {
			OrderModel orderToDelete = orders.get(index);
			int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer "
					+ "cette commande ?", "Suppression "
							+orderToDelete.getOrderRef(), JOptionPane.YES_NO_OPTION, 1, deleteIcon);

			if(option == JOptionPane.OK_OPTION) {
				try {
					orderDAO.deleteOrder(orderToDelete.getId());
					DefaultListModel<String> emptyList = new DefaultListModel<>();
					emptyList.clear();
					view.getOrderDetailList().setModel(emptyList);
					orders = getAllOrders();
					view.getOrderList().setListData(orders.toArray());
				} catch (Exception exception) {
					return;
				}
			}
		}
	}
}
