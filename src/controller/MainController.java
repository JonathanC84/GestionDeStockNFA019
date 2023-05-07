package controller;

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
	@SuppressWarnings("unused")
	private ProductDAO productDAO;
	private ProductController productController;
	
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
				for (int i = 0; i < pass.length; i++) {
					pass[i] = 0;
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
		String userName = userDetails.get("prenom");
		String userRole = userDetails.get("role");
		loginView.frame.dispose();
		view.getMainTabs().setSelectedComponent(view.getProductPanel());
		view.frame.setVisible(true);

		// récupère et affiche le prénom et le rôle de l'utilisateur
		view.getWelcomeLabel().setText("Bienvenue "+userName+" ("+userRole+")");
		// le bouton logout appelle la méthode de déconnexion de l'utilisateur
		view.getLogoutBtn().addActionListener(e -> disconnection());
		view.getAddProductBtn().addActionListener(e -> productController.addProduct(view, userRole));
		
		// gère l'affichage en fonction des droits utilisateur
		if(userDetails.get("role").equals("gestionnaire")) {
			view.getUsersPanel().setVisible(false);
			view.getMainTabs().remove(view.getUsersPanel());
		} else if(userDetails.get("role").equals("commercial")) {
			view.getUsersPanel().setVisible(false);
			view.getSupplyPanel().setVisible(false);
			view.getAddProductBtn().setVisible(false);
			view.getMainTabs().remove(view.getUsersPanel());
			view.getMainTabs().remove(view.getSupplyPanel());
			view.getProductPanel().remove(view.getAddProductBtn());
		}
		
		
		JTable productTable = new JTable();
		productTable = view.getProductTable();
				
		productController.initializeProductTable(productTable, userDetails.get("role"));
		
	}	
	
	// méthode qui déconnecte l'utilisateur actuel et qui retourne à la page login (appelée par le bouton logout)
	public void disconnection() {
		view.frame.dispose();
		loginView = new LoginView();
		view = new View();
		loginView.frame.setVisible(true);
		initController();
	}
	
}
