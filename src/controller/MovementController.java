package controller;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.JTable;

import model.MovementDAO;
import model.MovementModel;
import model.MovementTableModel;
import model.ProductModel;
import view.View;
import model.ProductDAO;

public class MovementController {

	static int COLUMN_SIZES[] = {50, 200, 300, 50};
	private MovementDAO movementDAO = new MovementDAO();
	private ProductDAO productDAO = new ProductDAO();
	private static ZoneId zoneId = ZoneId.of("Europe/Paris");

	public void initializeEntrieTable(JTable entrieTable) {
		entrieTable.setModel(new MovementTableModel(movementDAO.getEntries()));
		changeColumnWidth(entrieTable, COLUMN_SIZES);
	}

	public void initializeRemovalTable(JTable removalTable) {
		removalTable.setModel(new MovementTableModel(movementDAO.getRemovals()));
		changeColumnWidth(removalTable, COLUMN_SIZES);
	}

	public void changeColumnWidth(JTable table, int[] COLUMN_SIZES) {
		for (int i = 0; i < COLUMN_SIZES.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(COLUMN_SIZES[i]);
		}
	}

	public void entryMovement(ProductModel newProduct, View view, int movementQuantity) {
		JTable entrieTable = view.getEntrieTable();
		MovementModel newMovement = new MovementModel();
		ArrayList<ProductModel> allProducts = productDAO.getAllProducts();
		
		for(ProductModel product : allProducts) {
			if (
					product.getProdName().equals(newProduct.getProdName())
					&& product.getProdRef().equals(newProduct.getProdRef())
					&& product.getProdQuantity() == newProduct.getProdQuantity()
					&& product.getProdUnitPrice() == newProduct.getProdUnitPrice()
					&& product.getProdCategory() == newProduct.getProdCategory()
					&& product.getProdSupplier() == newProduct.getProdSupplier()
					)
			{
				newProduct.setId(product.getId());
			}
		}
		newMovement.setMovementType("E");
		newMovement.setMovementTime(OffsetDateTime.now().atZoneSimilarLocal(zoneId));
		newMovement.setMovementQuantity(movementQuantity);
		newMovement.setProductId(newProduct.getId());
		movementDAO.addMovement(newMovement);
		initializeEntrieTable(entrieTable);
	}
	
	public void removalMovement(ProductModel newProduct, View view, int movementQuantity) {
		JTable removalTable = view.getRemovalTable();
		MovementModel newMovement = new MovementModel();
		OffsetDateTime time = OffsetDateTime.now();
		
		newMovement.setMovementType("S");
		newMovement.setMovementTime(time.atZoneSimilarLocal(zoneId));
		newMovement.setMovementQuantity(movementQuantity);
		newMovement.setProductId(newProduct.getId());
		movementDAO.addMovement(newMovement);
		initializeRemovalTable(removalTable);
	}
}
