package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import model.*;

/**
 * Classe contenant un panel Produit
 * à appeler dans une boîte de dialoque (showConfirmDialog)
 * Utilisé par editProduct et addProduct
 * @author Jonathan Cayrol
 *
 */

public class ProductOptionView {

	private JPanel panel;
	private JTextField refField, nameField, quantityField, expTimeField;
	private JSpinner unitPriceField;
	private JTextArea descField;
	private JComboBox<String> categoryField, supplierField;
    private JLabel refLabel, nameLabel, descLabel, quantityLabel, unitPriceLabel, expTimeLabel, categoryLabel, supplierLabel;
    private CategoryDAO categoryDAO = new CategoryDAO();
    private SupplierDAO supplierDAO = new SupplierDAO();
    private ArrayList<CategoryModel> categories = categoryDAO.getCategories();
    private ArrayList<SupplierModel> suppliers = supplierDAO.getSuppliers();
	
	public JPanel getPanel(ProductModel product) {
		
		String productCategory = "";
		String productSupplier = "";
		
		for (CategoryModel category : categories) {
			if(product.getProdCategory() == category.getId()) {
				productCategory = category.getCatName();
			}
		}
		
		for (SupplierModel supplier : suppliers) {
			if(product.getProdSupplier() == supplier.getId()) {
				productSupplier = supplier.getSupplierName();
			}
		}
		
		panel = new JPanel();
		
		Font font = new Font("Arial", Font.PLAIN, 16);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		refField = new JTextField(20);
		refField.setFont(font);
		refField.setText(product.getProdRef());
		refField.setAlignmentX(Component.LEFT_ALIGNMENT);

		refLabel = new JLabel("Référence");
		refLabel.setFont(font);
		refLabel.setLabelFor(refField);
		refLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		nameField = new JTextField(20);
		nameField.setText(product.getProdName());
		nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
		nameField.setFont(font);
						
		nameLabel = new JLabel("Nom");
		nameLabel.setFont(font);
		nameLabel.setLabelFor(nameField);
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		descField = new JTextArea(5, 20);
		descField.setLineWrap(true);
		descField.setAutoscrolls(true);
		descField.setFont(font);
		descField.setText(product.getProdDesc());
		descField.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		descLabel = new JLabel("Description");
		descLabel.setFont(font);
		descLabel.setLabelFor(descField);
		descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		quantityField = new JTextField(20);
		quantityField.setFont(font);
		quantityField.setText(String.valueOf(product.getProdQuantity()));
		quantityField.setAlignmentX(Component.LEFT_ALIGNMENT);

		quantityLabel = new JLabel("Quantité");
		quantityLabel.setFont(font);
		quantityLabel.setLabelFor(descField);
		quantityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		SpinnerModel priceModel = new SpinnerNumberModel(
			0.0,
			0.0,
			9999.99,
			0.01
		);
		
		unitPriceField = new JSpinner(priceModel);
		unitPriceField.setFont(font);
		unitPriceField.setValue(product.getProdUnitPrice());
		unitPriceField.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		unitPriceLabel = new JLabel("Prix/unité");
		unitPriceLabel.setFont(font);
		unitPriceLabel.setLabelFor(unitPriceField);
		unitPriceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		expTimeField = new JTextField(20);
		expTimeField.setFont(font);
		expTimeField.setText(String.valueOf(product.getProdExpTime()));
		expTimeField.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		expTimeLabel = new JLabel("Durée de conservation");
		expTimeLabel.setFont(font);
		expTimeLabel.setLabelFor(expTimeField);
		expTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		categoryField = new JComboBox<String>();
		for (CategoryModel category : categories) {
			categoryField.addItem(category.getCatName());
		}
		categoryField.setSelectedItem(productCategory);
		categoryField.setBackground(Color.white);
		categoryField.setFont(font);
		categoryField.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		categoryLabel = new JLabel("Catégorie");
		categoryLabel.setFont(font);
		categoryLabel.setLabelFor(categoryField);	
		categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

		supplierField = new JComboBox<String>();
		for (SupplierModel supplier : suppliers) {
			supplierField.addItem(supplier.getSupplierName());
		}
		supplierField.setSelectedItem(productSupplier);
		supplierField.setBackground(Color.white);
		supplierField.setFont(font);
		supplierField.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		supplierLabel = new JLabel("Fournisseur");
		supplierLabel.setFont(font);
		supplierLabel.setLabelFor(supplierField);
		supplierLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panel.add(refLabel); panel.add(refField);
		panel.add(nameLabel); panel.add(nameField);		
		panel.add(quantityLabel); panel.add(quantityField);
		panel.add(unitPriceLabel); panel.add(unitPriceField);
		panel.add(expTimeLabel); panel.add(expTimeField);
		panel.add(categoryLabel); panel.add(categoryField);
		panel.add(supplierLabel); panel.add(supplierField);
		panel.add(descLabel); panel.add(descField);
		
		return panel;
	}

	public String getRefField() {
		return refField.getText();
	}

	public String getNameField() {
		return nameField.getText();
	}

	public int getQuantityField() {
		return Integer.parseInt(quantityField.getText());
	}

	public double getUnitPriceField() {
		return Double.parseDouble(unitPriceField.getValue().toString());
		//return Double.parseDouble(unitPriceField.getText());
	}

	public int getExpTimeField() {
		return Integer.parseInt(expTimeField.getText());
	}

	public String getDescField() {
		return descField.getText();
	}

	public int getCategoryField() {
		int setCategory = 0;
		for (CategoryModel category : categories) {
			if(categoryField.getSelectedItem() == category.getCatName()) {
				setCategory = category.getId();
			}
		}
		return setCategory;
	}

	public int getSupplierField() {
		int setSupplier = 0;
		for (SupplierModel supplier : suppliers) {
			if(supplierField.getSelectedItem() == supplier.getSupplierName()) {
				setSupplier = supplier.getId();
			}
		}
		return setSupplier;
	}
}
