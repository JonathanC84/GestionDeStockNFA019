package model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Classe qui permet de transformer en TableModel l'ArrayList contenant les données
 * mouvements récupérées dans la base afin d'afficher les données sous forme de JTable
 * Utilisation de la classe ButtonColumn pour affichage
 */

@SuppressWarnings("serial")
public class MovementTableModel extends AbstractTableModel {

	private final String columns[] = {"Id","Date","Produit","Quantité"};
	private ArrayList<MovementModel> movements;
	private static DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy - HH:mm");
	
	public MovementTableModel(ArrayList<MovementModel> movements) {
		super();
		this.movements = movements;
	}
	
	@Override
	public int getRowCount() {
		return movements.size();
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
		return Object.class;
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		MovementModel movement = movements.get(row);
		ProductDAO productDAO = new ProductDAO();
		ArrayList<ProductModel> products = productDAO.getAllProducts();
		
		String formattedTime = DT_FORMATTER.format(movement.getMovementTime());
		
		switch(col) {
		case 0 :
			return movement.getId();
		case 1 :
			return formattedTime;
		case 2 :
			for(ProductModel product : products) {
				if(product.getId() == movement.getProductId()) return product.getProdName();
				//else return "Produit inconnu";
			}
		case 3 :
			return movement.getMovementQuantity();
		default :
				return null;
		}
	}

}
