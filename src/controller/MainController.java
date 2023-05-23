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
	private SupplierDAO supplierDAO;
	private ProductController productController;
	private MovementController movementController;
	private OrderController orderController;
	private SupplierController supplierController;
	private UserController userController;

	public MainController() {

	}

	public MainController(LoginView loginView, View view) {
		this.loginView = loginView;
		this.view = view;
		userDAO = new UserDAO();
		initController();
	}

	public void initController() {
		loginView.getUserNameField().addActionListener(e -> authentification());
		loginView.getPasswordField().addActionListener(e -> authentification());
		loginView.getLoginButton().addActionListener(e -> authentification());
	}


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
				userDAO.setLastConnectionDate(user);
			} else {
				JOptionPane.showMessageDialog(loginView.frame, "Nom d'utilisateur ou mot de passe incorrect");
			}
		}
	}

	public void initView(HashMap<String,String> userDetails) {

		loginView.frame.dispose();

		productController = new ProductController();
		movementController = new MovementController();
		orderController = new OrderController();
		supplierController = new SupplierController();
		userController = new UserController();
		supplierDAO = new SupplierDAO();

		String userName = userDetails.get("prenom");
		String userRole = userDetails.get("role");
		String ddn = userDetails.get("ddn");

		view.frame.setVisible(true);
		view.getMainTabs().setSelectedComponent(view.getProductPanel());
		view.getWelcomeLabel().setText("Bienvenue "+userName+" ("+userRole+"), dernière connexion : "+ddn);
		view.getLogoutBtn().addActionListener(e -> disconnection());
		// méthodes CRUD pour les produits
		view.getAddProductBtn().addActionListener(e -> productController.addProduct(view, userRole));
		view.getSearchField().addActionListener(e -> productController.searchProduct(view, userRole));
		view.getSearchBtn().addActionListener(e -> productController.searchProduct(view, userRole));

		if(userRole.equals("gestionnaire")) {
			view.getUsersPanel().setVisible(false);
			view.getMainTabs().remove(view.getUsersPanel());
		} else if(userRole.equals("commercial")) {
			view.getUsersPanel().setVisible(false);
			view.getSupplyPanel().setVisible(false);
			view.getAddProductBtn().setVisible(false);
			view.getMainTabs().remove(view.getUsersPanel());
			view.getMainTabs().remove(view.getSupplyPanel());
			view.getProductPanel().remove(view.getAddProductBtn());
		}
		
		// affichage des commandes
		view.getOrderList().setListData(orderController.getAllOrders().toArray());
		view.getOrderList().addListSelectionListener(e -> orderController.selectOrder(view));
		
		// affichage des fournisseurs
		view.getSupplierList().setListData(supplierDAO.getVisibleSuppliers().toArray());
		
		// affichage des utilisateurs
		view.getUserList().setListData(userDAO.getUsers().toArray());
		
		// méthodes CRUD pour les commandes
		view.getAddOrderBtn().addActionListener(e -> orderController.addOrder(view));
		view.getEditOrderBtn().addActionListener(e -> orderController.editOrder());
		view.getDeleteOrderBtn().addActionListener(e -> orderController.deleteOrder(view));
		
		// méthodes CRUD pour les fournisseurs
		view.getAddSupplierBtn().addActionListener(e -> supplierController.addSupplier(view));
		view.getEditSupplierBtn().addActionListener(
				e -> supplierController.editSupplier(view.getSupplierList().getSelectedIndex(), view));
		view.getDeleteSupplierBtn().addActionListener(
				e -> supplierController.deleteSupplier(view.getSupplierList().getSelectedIndex(), view));
		
		// méthodes CRUD pour les utilisateurs
		view.getAddUserBtn().addActionListener(e -> userController.addUser(view));
		view.getDeleteUserBtn().addActionListener(e -> userController.deleteUser(view));
		view.getEditUserBtn().addActionListener(e -> userController.editUser(view));


		JTable productTable = new JTable();
		productTable = view.getProductTable();
		productController.initializeProductTable(view, productTable, userRole);

		JTable entrieTable = new JTable();
		entrieTable = view.getEntrieTable();
		movementController.initializeEntrieTable(entrieTable);

		JTable removalTable = new JTable();
		removalTable = view.getRemovalTable();
		movementController.initializeRemovalTable(removalTable);	
	}	

	public void disconnection() {
		view.frame.dispose();
		loginView = new LoginView();
		view = new View();
		loginView.frame.setVisible(true);
		initController();
	}
}
