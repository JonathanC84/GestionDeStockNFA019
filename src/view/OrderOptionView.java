package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.OrderModel;
import model.SupplierDAO;
import model.SupplierModel;

/**
 * Classe contenant un panel Commande
 * à appeler dans une boîte de dialoque (showConfirmDialog)
 * Utilisé par editOrder et addOrder
 * @author Jonathan Cayrol
 *
 */

public class OrderOptionView {

	private JPanel panel, datePanel;
	private JTextField refField;
	private JLabel refLabel, dateLabel, productsListLabel, supplierLabel, totalPriceLabel;
	private JComboBox<String> supplierField;
	private SupplierDAO supplierDAO = new SupplierDAO();
	private ArrayList<SupplierModel> suppliers = supplierDAO.getVisibleSuppliers();
	
	public JPanel getPanel(OrderModel order) {

		panel = new JPanel();
		datePanel = new JPanel();
		Font font = new Font("Arial", Font.PLAIN, 16);

		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.LINE_AXIS));
		
		refLabel = new JLabel("Référence commande");
		refLabel.setLabelFor(refField);
		refLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		refLabel.setFont(font);
		
		refField = new JTextField(20);
		refField.setText(order.getOrderRef());
		refField.setAlignmentX(Component.LEFT_ALIGNMENT);
		refField.setFont(font);
		
		dateLabel = new JLabel("Date commande");
		dateLabel.setLabelFor(datePanel);
		dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		dateLabel.setFont(font);
		
		supplierLabel = new JLabel("Fournisseur");
		supplierLabel.setLabelFor(supplierField);
		supplierLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		supplierLabel.setFont(font);
		
		supplierField = new JComboBox<String>();
		for (SupplierModel supplier : suppliers) {
			supplierField.addItem(supplier.getSupplierName());
		}
		supplierField.setBackground(Color.white);
		supplierField.setFont(font);
		supplierField.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panel.add(refLabel);
		panel.add(refField);
		//panel.add(dateLabel);
		//panel.add(datePanel);
		panel.add(supplierLabel);
		panel.add(supplierField);
				
		return panel;
	}

	public JTextField getRefField() {
		return refField;
	}

	public JComboBox<String> getSupplierField() {
		return supplierField;
	}

	public void setRefField(JTextField refField) {
		this.refField = refField;
	}
	
	
}
