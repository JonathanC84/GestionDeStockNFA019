package controller;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.UserDAO;
import model.UserModel;
import view.UserView;
import view.View;

/**
 * Contrôleur pour les utilisateurs
 * Ajouter, modifier, supprimer
 * 
 *
 */
public class UserController {

	private UserDAO userDAO = new UserDAO();
	private ImageIcon editIcon = new ImageIcon(getClass().getClassLoader().getResource("modify32.png"));
	private ImageIcon deleteIcon = new ImageIcon(getClass().getClassLoader().getResource("delete32.png"));

	public void addUser(View view) {
		UserModel newUser = new UserModel();
		UserView uv = new UserView();

		// appel d'une boîte de dialogue implémentant le panel de la vue ProductOptionView
		int option = JOptionPane.showConfirmDialog(null, uv.getPanel(newUser) , "Ajout d'un utilisateur "
				, JOptionPane.OK_CANCEL_OPTION, 1, editIcon);

		if(option == JOptionPane.OK_OPTION) {
			try {

				newUser.setNom(uv.getNomTextField().getText());
				newUser.setPrenom(uv.getPrenomTextField().getText());
				newUser.setLogin(uv.getUserNameField().getText());
				newUser.setPassword(uv.getPasswordField().getText());
				newUser.setRole(uv.getRoleComboBox().getSelectedItem().toString());

				userDAO.addUser(newUser);

				view.getUserList().setListData(userDAO.getUsers().toArray());		

			} catch (Exception exception) {
				return;
			}
		}
	}

	public void editUser(View view) {
		ArrayList<UserModel> users = userDAO.getUsers();
		int index = view.getUserList().getSelectedIndex();

		if(index < 0) {
			JOptionPane.showMessageDialog(null, "Sélectionnez un utilisateur");
			return;
		} else {
			UserView uv = new UserView();

			Object selectedUser = view.getUserList().getSelectedValue();
			int userId = Integer.parseInt(selectedUser.toString().split(" ")[0]);
			UserModel userToEdit = new UserModel();
			for (UserModel user : users) {
				if (user.getId() == userId) {
					userToEdit = user;
				}
			}
			System.out.println(userToEdit);
			int option = JOptionPane.showConfirmDialog(null, uv.getPanel(userToEdit) , "Edition d'un utilisateur "
					, JOptionPane.OK_CANCEL_OPTION, 1, editIcon);

			if(option == JOptionPane.OK_OPTION) {
				try {
					userToEdit.setPrenom(uv.getPrenomTextField().getText());
					userToEdit.setNom(uv.getNomTextField().getText());
					userToEdit.setLogin(uv.getUserNameField().getText());
					userToEdit.setPassword(uv.getPasswordField().getText());
					userToEdit.setRole(uv.getRoleComboBox().getSelectedItem().toString());
					System.out.println(userToEdit);
					userDAO.editUser(userToEdit);
					view.getUserList().setListData(userDAO.getUsers().toArray());

				} catch (Exception exception) {
					return;
				}
			}
		}
	}

	public void deleteUser(View view) {
		ArrayList<UserModel> users = userDAO.getUsers();
		int index = view.getUserList().getSelectedIndex();

		if(index < 0) {
			JOptionPane.showMessageDialog(null, "Sélectionnez un utilisateur");
			return;
		} else {
			Object selectedUser = view.getUserList().getSelectedValue();
			int userId = Integer.parseInt(selectedUser.toString().split(" ")[0]);
			UserModel userToDelete = new UserModel();
			for (UserModel user : users) {
				if (user.getId() == userId) {
					userToDelete = user;
				}
			}
			// suppression d'un administrateur impossible
			if(userToDelete.getRole().equals("administrateur")) {
				JOptionPane.showMessageDialog(null, "Impossible de supprimer un administrateur");
			} else {

				int option = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer "
						+ "cet utilisateur ?", "Suppression "
								+userToDelete.getPrenom(), JOptionPane.YES_NO_OPTION, 1, deleteIcon);

				if(option == JOptionPane.OK_OPTION) {
					try {
						userDAO.deleteUser(userToDelete.getId());
						users = userDAO.getUsers();
						view.getUserList().setListData(users.toArray());
					} catch (Exception exception) {
						return;
					}
				}
			}
			return;
		}
	}

}
