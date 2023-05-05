package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ProductTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private	String columns[];
	private ArrayList<ProductModel> allProducts;
	
	public ProductTableModel(ArrayList<ProductModel> allProducts) {
		super();
		this.allProducts = allProducts;
		columns = new String[]{"Id", "Référence", "Nom", "Quantité", "PU", "Catégorie", "Fournisseur", "Editer", "Supprimer"};
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
	public Object getValueAt(int row, int col) {
		ProductModel product = allProducts.get(row);
		switch(col) {
			case 0 : return product.getId();
			case 1 : return product.getProdRef();
			case 2 : return product.getProdName();
			case 3 : return product.getProdQuantity();
			case 4 : return product.getProdUnitPrice();
			case 5 : return product.getProdCategory();
			case 6 : return product.getProdSupplier();
			default : return null;
		}
	}
	
	public String getColumnName(int col) {
		return columns[col];
	}
	
	
}
