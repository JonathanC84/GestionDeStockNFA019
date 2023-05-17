package view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.SupplierModel;

/**
 * Classe contenant un panel Fournisseur
 * à appeler dans une boîte de dialoque (showConfirmDialog)
 * Utilisé par editSupplier et addSupplier
 * @author Jonathan Cayrol
 *
 */

public class SupplierOptionView {

	private JPanel panel;
	private JTextField nameField, addressField, phoneField;
	private JLabel nameLabel, addressLabel, phoneLabel;

	public JPanel getPanel(SupplierModel supplier) {
		
		panel = new JPanel();
		
		Font font = new Font("Arial", Font.PLAIN, 16);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		nameField = new JTextField(20);
		nameField.setText(supplier.getSupplierName());
		nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
		nameField.setFont(font);
						
		nameLabel = new JLabel("Nom");
		nameLabel.setFont(font);
		nameLabel.setLabelFor(nameField);
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		addressField = new JTextField(20);
		addressField.setText(supplier.getSupplierAddress());
		addressField.setAlignmentX(Component.LEFT_ALIGNMENT);
		addressField.setFont(font);
						
		addressLabel = new JLabel("Adresse");
		addressLabel.setFont(font);
		addressLabel.setLabelFor(addressField);
		addressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		phoneField = new JTextField(20);
		phoneField.setText(supplier.getSupplierPhoneNumber());
		phoneField.setAlignmentX(Component.LEFT_ALIGNMENT);
		phoneField.setFont(font);
						
		phoneLabel = new JLabel("Téléphone");
		phoneLabel.setFont(font);
		phoneLabel.setLabelFor(phoneField);
		phoneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panel.add(nameLabel); panel.add(nameField);
		panel.add(addressLabel); panel.add(addressField);
		panel.add(phoneLabel); panel.add(phoneField);		
		
		return panel;
	}

	public String getNameField() {
		return nameField.getText();
	}

	public String getAddressField() {
		return addressField.getText();
	}

	public String getPhoneField() {
		return phoneField.getText();
	}
}
