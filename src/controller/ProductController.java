package controller;

import model.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import view.ProductOptionView;
import view.View;

public class ProductController {

	private ProductDAO productDAO = new ProductDAO();
	// taille des colonnes de la table Produits
	static int COLUMN_SIZES[] = {50, 150, 200, 50, 75, 200, 150, 150, 75, 75};
	// version commercial
	static int COLUMN_SIZES_MIN[] = {50, 150, 200, 50, 75, 200, 150, 150};

	// méthode qui initialise la table (ou l'actualise après changement)
	public void initializeProductTable(JTable productTable, String userRole) {
		// affichage de la table Produits en utilisant la classe ProductTableModel et deux colonnes Boutons
		// pour éditer et supprimer une ligne (classe ButtonColumn) en fonction des droits utilisateurs
		productTable.setModel(productDAO.productTableModel(userRole));
		productTable.setAutoCreateRowSorter(true);

		if(!userRole.equals("commercial")) {
			@SuppressWarnings("unused")
			ButtonColumn editButton = new ButtonColumn(productTable, editProduct(userRole), 8);
			@SuppressWarnings("unused")
			ButtonColumn deleteButton = new ButtonColumn(productTable, deleteProduct(userRole), 9);
			changeColumnWidth(productTable, COLUMN_SIZES);
		} else changeColumnWidth(productTable, COLUMN_SIZES_MIN);
		
	}

	// méthode qui permet de régler la taille des colonnes individuellement avec le tableau fourni
	public void changeColumnWidth(JTable table, int[] COLUMN_SIZES) {
		for (int i = 0; i < COLUMN_SIZES.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(COLUMN_SIZES[i]);
		}
	}

	public void addProduct(View view, String userRole) {
		JTable tableProduct = view.getProductTable();
		ProductModel newProduct = new ProductModel();
		ImageIcon editIcon = new ImageIcon(getClass().getClassLoader().getResource("modify32.png"));
		ProductOptionView pov = new ProductOptionView();
		
		// appel d'une boîte de dialogue implémentant le panel de la vue ProductOptionView
		int option = JOptionPane.showConfirmDialog(null, pov.getPanel(newProduct) , "Ajout d'une ligne "
				+ "produit", JOptionPane.OK_CANCEL_OPTION, 1, editIcon);
		
		if(option == JOptionPane.OK_OPTION) {
			try {
				// récupération des valeurs entrées dans les champs de la boîte de dialogue
				newProduct.setProdRef(pov.getRefField());
				newProduct.setProdName(pov.getNameField());
				newProduct.setProdQuantity(pov.getQuantityField());
				newProduct.setProdUnitPrice(pov.getUnitPriceField());
				newProduct.setProdDesc(pov.getDescField());
				newProduct.setProdExpTime(pov.getExpTimeField());
				newProduct.setProdCategory(pov.getCategoryField());
				newProduct.setProdSupplier(pov.getSupplierField());
				productDAO.addProduct(newProduct);
				// réinitialisation de la table pour affichage
				initializeProductTable(tableProduct, userRole);
			} catch (Exception exception) {
				return;
			}
		}

	}

	@SuppressWarnings("serial")
	public AbstractAction editProduct(String userRole) {
		AbstractAction edit = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();

				int modelRow = Integer.valueOf(e.getActionCommand());
				int productId = (int) table.getModel().getValueAt(modelRow, 0);

				ProductModel product = productDAO.getProduct(productId);

				ImageIcon editIcon = new ImageIcon(getClass().getClassLoader().getResource("modify32.png"));
				ProductOptionView pov = new ProductOptionView();

				// appel d'une boîte de dialogue implémentant le panel de la vue ProductOptionView
				int option = JOptionPane.showConfirmDialog(null, pov.getPanel(product) , "Edition du produit \""
						+product.getProdName()+"\"" , JOptionPane.OK_CANCEL_OPTION, 1, editIcon);

				if(option == JOptionPane.OK_OPTION) {
					try {
						// récupération des valeurs entrées dans les champs de la boîte de dialogue
						product.setProdRef(pov.getRefField());
						product.setProdName(pov.getNameField());
						product.setProdQuantity(pov.getQuantityField());
						product.setProdUnitPrice(pov.getUnitPriceField());
						product.setProdDesc(pov.getDescField());
						product.setProdExpTime(pov.getExpTimeField());
						product.setProdCategory(pov.getCategoryField());
						product.setProdSupplier(pov.getSupplierField());
						productDAO.editProduct(productId, product);
						// réinitialisation de la table pour affichage
						initializeProductTable(table, userRole);
					} catch (Exception exception) {
						return;
					}
				}
			}
		};
		return edit;
	}

	@SuppressWarnings("serial")
	public AbstractAction deleteProduct(String userRole) {
		AbstractAction delete = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				int productId = (int) table.getModel().getValueAt(modelRow, 0);
				String productName = (String) table.getModel().getValueAt(modelRow, 2);
				ImageIcon deleteIcon = new ImageIcon(getClass().getClassLoader().getResource("delete32.png"));

				int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer "
						+ "cette ligne produit ?", "Suppression "+productName, JOptionPane.YES_NO_OPTION, 1, deleteIcon);

				if(option == JOptionPane.OK_OPTION) {
					try {
						productDAO.deleteProduct(productId);
						// réinitialisation de la table pour affichage
						initializeProductTable(table, userRole);
					} catch (Exception exception) {
						return;
					}
				}
			}
		};
		return delete;
	}
}
