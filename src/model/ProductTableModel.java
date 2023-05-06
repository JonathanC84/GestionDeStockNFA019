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
	
	private	final String columns[] = {"Id", "Référence", "Nom", "Quantité", "PU", "Catégorie", "Fournisseur", "Editer", "Supprimer"};
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
		if (col == 7 || col == 8) {
			return ImageIcon.class;
		} else {
			return Object.class;
		}
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		ProductModel product = allProducts.get(row);
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
				return product.getProdCategory();
			case 6 :
				return product.getProdSupplier();
			case 7 :
				return new ImageIcon(getClass().getClassLoader().getResource("modify16.png"));
			case 8 :
				return new ImageIcon(getClass().getClassLoader().getResource("delete16.png"));
			default : 
				return null;
		}
	
	}

	// les colonnes 7 et 8 (éditer et supprimer) deviennent editables afin d'activer les ButtonColumn
	@Override
	public boolean isCellEditable(int row, int col) {
	     switch (col) {
	         case 7:
	         case 8:
	             return true;
	         default:
	             return false;
	      }
	}
	
	
}