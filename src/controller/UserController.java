package controller;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.ProductModel;
import model.UserDAO;
import model.UserModel;
import view.ProductOptionView;
import view.UserView;
import view.View;

public class UserController {
	
	public void addUser(View view, String userRole) {
		JList<UserModel> userList = view.getUserList();
		UserModel newUser = new UserModel();
		ImageIcon editIcon = new ImageIcon(getClass().getClassLoader().getResource("modify32.png"));
		UserView uv = new UserView();
		
		// appel d'une boîte de dialogue implémentant le panel de la vue ProductOptionView
		int option = JOptionPane.showConfirmDialog(null, uv.getUserView() , "Ajout d'un utilisateur(trice) "
				, JOptionPane.OK_CANCEL_OPTION, 1, editIcon);
		
		if(option == JOptionPane.OK_OPTION) {
			try {
				
				newUser.setNom(uv.getNomTextField().getText());
				newUser.setPrenom(uv.getPrenomTextField().getText());
				newUser.setLogin(uv.getUserNameField().getText());
				newUser.setPassword(uv.getPasswordField().getText());
				newUser.setRole(uv.getRoleComboBox().getSelectedItem().toString());
				
				UserDAO.addUser(newUser);
				
				
			} catch (Exception exception) {
				return;
			}
		}

	}
	
	public void editUser() {
		
	}
	
	public void deleteUser() {
		
	}
	
}
