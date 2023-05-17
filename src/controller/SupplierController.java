package controller;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.SupplierDAO;
import model.SupplierModel;
import view.SupplierOptionView;
import view.View;

public class SupplierController {

	private SupplierDAO supplierDAO = new SupplierDAO();
	private ImageIcon editIcon = new ImageIcon(getClass().getClassLoader().getResource("modify32.png"));
	private ImageIcon deleteIcon = new ImageIcon(getClass().getClassLoader().getResource("delete32.png"));

	public void addSupplier(View view) {
		SupplierModel newSupplier = new SupplierModel();
		SupplierOptionView sov = new SupplierOptionView();

		int option = JOptionPane.showConfirmDialog(null, sov.getPanel(newSupplier) , "Ajout d'un "
				+ "fournisseur", JOptionPane.OK_CANCEL_OPTION, 1, editIcon);

		if(option == JOptionPane.OK_OPTION) {
			try {
				newSupplier.setSupplierName(sov.getNameField());
				newSupplier.setSupplierAddress(sov.getAddressField());
				newSupplier.setSupplierPhoneNumber(sov.getPhoneField());
				supplierDAO.addSupplier(newSupplier);
				view.getSupplierList().setListData(supplierDAO.getVisibleSuppliers().toArray());
			} catch (Exception e) {
				return;
			}
		}
	}

	public void editSupplier(int index, View view) {
		if (index < 0) {
			JOptionPane.showMessageDialog(null, "Sélectionnez un fournisseur");
			return;
		} else {
			ArrayList<SupplierModel> suppliers = supplierDAO.getSuppliers();
			SupplierModel selectedSupplier = suppliers.get(index);	
			SupplierOptionView sov = new SupplierOptionView();

			int option = JOptionPane.showConfirmDialog(null, sov.getPanel(selectedSupplier) , "Modification d'un "
					+ "fournisseur", JOptionPane.OK_CANCEL_OPTION, 1, editIcon);

			if(option == JOptionPane.OK_OPTION) {
				try {
					selectedSupplier.setSupplierName(sov.getNameField());
					selectedSupplier.setSupplierAddress(sov.getAddressField());
					selectedSupplier.setSupplierPhoneNumber(sov.getPhoneField());
					supplierDAO.editSupplier(selectedSupplier);
					view.getSupplierList().setListData(supplierDAO.getVisibleSuppliers().toArray());
				} catch (Exception e) {
					return;
				}
			}
		}
	}

	public void deleteSupplier(int index, View view) {
		if (index < 0) {
			JOptionPane.showMessageDialog(null, "Sélectionnez un fournisseur");
			return;
		} else {
			ArrayList<SupplierModel> suppliers = supplierDAO.getSuppliers();
			SupplierModel selectedSupplier = suppliers.get(index);	


			int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer "
					+ "ce fournisseur ?", "Suppression "
							+selectedSupplier.getSupplierName(), JOptionPane.YES_NO_OPTION, 1, deleteIcon);

			if(option == JOptionPane.OK_OPTION) {
				try {
					supplierDAO.deleteSupplier(selectedSupplier.getId());
					view.getSupplierList().setListData(supplierDAO.getVisibleSuppliers().toArray());
				} catch (Exception e) {
					return;
				}
			}
		}
	}
}
