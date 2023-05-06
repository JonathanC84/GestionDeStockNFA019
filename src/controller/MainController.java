package controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import view.LoginView;
import view.View;
import model.*;

public class MainController {

	private LoginView loginView;
	private View view;
	private UserDAO userDAO;
	private ProductDAO productDAO;
	private ProductController productController;
	// taille des colonnes de la table Produits
	static int COLUMN_SIZES[] = {50, 200, 300, 100, 100, 200, 200, 75, 75};
	
	public MainController() {
		
	}
	
	public MainController(LoginView loginView, View view) {
		this.loginView = loginView;
		this.view = view;
		userDAO = new UserDAO();
		productDAO = new ProductDAO();
		productController = new ProductController();
		initController();
	}
	
	// initalisation des méthodes pour la fenêtre login
	public void initController() {
		loginView.getUserNameField().addActionListener(e -> authentification());
		loginView.getPasswordField().addActionListener(e -> authentification());
		loginView.getLoginButton().addActionListener(e -> authentification());
	}
	
	
	// méthode pour authentifier l'utilisateur, appelle la méthode initView
	private void authentification() {
		String user = loginView.getUserNameField().getText();
		char[] pass = loginView.getPasswordField().getPassword();
		String password = "";
		for (int i = 0; i < pass.length; i++) {
			password += pass[i];
		}
				
		if(user.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(loginView.frame, "Veuillez renseigner tous les champs");
		} else {
			if (userDAO.getAuthUser(user, password)) {
				password = "";
				for (char c : pass) {
					c = 0;
				}
				initView(userDAO.getUserDetails(user));
			} else {
				JOptionPane.showMessageDialog(loginView.frame, "Nom d'utilisateur ou mot de passe incorrects");
			}
		}
	}
	
	/* si l'authentification est réussie, méthode qui cache la fenêtre login
	   et ouvre la fenêtre principale avec affichage des données */
	public void initView(HashMap<String,String> userDetails) {
		view.frame.setVisible(true);
		loginView.frame.dispose();
		// récupère et affiche le prénom et le rôle de l'utilisateur
		view.getWelcomeLabel().setText("Bienvenue "+userDetails.get("prenom")+" ("+userDetails.get("role")+")");
		// le bouton logout appelle la méthode de déconnexion de l'utilisateur
		view.getLogoutBtn().addActionListener(e -> disconnection());
		
		// affichage de la table Produits en utilisant la classe ProductTableModel et deux colonnes Boutons
		// pour éditer et supprimer une ligne (classe ButtonColumn)
		JTable productTable = view.getProductTable();
		productTable.setModel(productDAO.productTableModel());
		ButtonColumn editButton = new ButtonColumn(productTable, productController.editProduct(), 7);
		editButton.setMnemonic(KeyEvent.VK_E);
		ButtonColumn deleteButton = new ButtonColumn(productTable, productController.deleteProduct(), 8);
		deleteButton.setMnemonic(KeyEvent.VK_DELETE);
		
		// on personnalise la taille des colonnes
		changeColumnWidth(productTable, COLUMN_SIZES);
	}
	
	// méthode qui permet de régler la taille des colonnes individuellement avec le tableau fourni
	public void changeColumnWidth(JTable table, int[] COLUMN_SIZES) {
		for (int i = 0; i < COLUMN_SIZES.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(COLUMN_SIZES[i]);
		}
	}
	
	// méthode qui déconnecte l'utilisateur actuel et qui retourne à la page login (appelée par le bouton logout)
	public void disconnection() {
		view.frame.dispose();
		loginView.getUserNameField().setText("");
		loginView.getPasswordField().setText("");
		loginView.frame.setVisible(true);
	}
	
}
