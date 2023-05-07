package model;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 * Classe qui permet de transformer l'ArrayList contenant les données récupérées en base
 * en TableModel afin d'afficher les données sous forme de JTable
 * @author Jonathan Cayrol *
 */

@SuppressWarnings("serial")
public class ProductTableModel extends AbstractTableModel {
	
	private	final String columns[] = {"Id", "Réf.", "Nom", "Qté", "PU", "Description", "Catégorie", "Fournisseur", "Editer", "Supprimer"};
	private ArrayList<ProductModel> allProducts;
	
	public ProductTableModel(ArrayList<ProductModel> allProducts) {
		super();
		this.allProducts = allProducts;
	}
	
	@Override
	public int getRowCount() {
		return allProducts.size();
	}
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public String getColumnName(int col) {
		return columns[col];
	}
	
	@Override
	public Class<?> getColumnClass(int col) {
		if (col == 8 || col == 9) {
			return ImageIcon.class;
		} else {
			return Object.class;
		}
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		ProductModel product = allProducts.get(row);
		CategoryDAO categoryDAO = new CategoryDAO();
		ArrayList<CategoryModel> categories = categoryDAO.getCategories();
		SupplierDAO supplierDAO = new SupplierDAO();
		ArrayList<SupplierModel> suppliers = supplierDAO.getSuppliers();
		switch(col) {
			case 0 : 
				return product.getId();
			case 1 :
				return product.getProdRef();
			case 2 :
				return product.getProdName();
			case 3 :
				return product.getProdQuantity();
			case 4 :
				return product.getProdUnitPrice();
			case 5 :
				return product.getProdDesc();
			case 6 :
				for(CategoryModel category : categories) {
					if(category.getId() == product.getProdCategory()) {
						return category.getCatName();
					}
				};
			case 7 :
				for(SupplierModel supplier : suppliers) {
					if(supplier.getId() == product.getProdSupplier()) {
						return supplier.getSupplierName();
					}
				};
			case 8 :
				return new ImageIcon(getClass().getClassLoader().getResource("modify16.png"));
			case 9 :
				return new ImageIcon(getClass().getClassLoader().getResource("delete16.png"));
			default : 
				return null;
		}
	
	}

	// les colonnes "éditer" et "supprimer" deviennent editables afin d'activer les ButtonColumn
	@Override
	public boolean isCellEditable(int row, int col) {
	     switch (col) {
	         case 8:
	         case 9:
	             return true;
	         default:
	             return false;
	      }
	}
	
	
}