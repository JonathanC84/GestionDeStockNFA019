package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.ProductDAO;

public class ProductController extends MainController {

	private ProductDAO productDAO = new ProductDAO();
	
	public void addProduct() {
		
	}
	
	@SuppressWarnings("serial")
	public AbstractAction editProduct() {
		AbstractAction edit = new AbstractAction() {
			
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				int productId = (int) table.getModel().getValueAt(modelRow, 0);
				System.out.println("EDIT "+modelRow+" "+productId);
			}
		};
		return edit;
	}
	
	@SuppressWarnings("serial")
	public AbstractAction deleteProduct() {
		AbstractAction delete = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				int productId = (int) table.getModel().getValueAt(modelRow, 0);
				
				int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer la ligne produit (id "+productId+") ?");
			
				if(option == JOptionPane.OK_OPTION) {
					try {
						productDAO.deleteProduct(productId);
						table.setModel(productDAO.productTableModel());
						changeColumnWidth(table, COLUMN_SIZES);
					} catch (Exception exception) {
						return;
					}
				}
			}
		};
		return delete;
	}
}
