package controller;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.LoginView;
import view.View;
import model.*;

public class MainController {

	private LoginView loginView;
	private View view;
	private UserDAO userDAO;
	private ProductDAO productDAO;
	
	public MainController(LoginView loginView, View view) {
		this.loginView = loginView;
		this.view = view;
		userDAO = new UserDAO();
		productDAO = new ProductDAO();
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
				for (char c : pass) {
					c = 0;
				}
				initView(userDAO.getUserName(user));
			} else {
				JOptionPane.showMessageDialog(loginView.frame, "Nom d'utilisateur ou mot de passe incorrects");
			}
		}
	}
	
	public void initView(String userName) {
		view.frame.setVisible(true);
		loginView.frame.dispose();
		view.getWelcomeLabel().setText("Bienvenue sur GeStock, "+userName+" !");
		view.getLogoutBtn().addActionListener(e -> disconnection());
		view.getProductTable().setModel(productDAO.productTableModel());
		changeColumnWidth(0, 50);
		changeColumnWidth(1, 200);
		changeColumnWidth(2, 300);
		changeColumnWidth(3, 100);
		changeColumnWidth(4, 100);
		changeColumnWidth(5, 200);
		changeColumnWidth(6, 150);
		changeColumnWidth(7, 100);
		changeColumnWidth(8, 100);
	}
	
	public void changeColumnWidth(int index, int size) {
		view.getProductTable().getColumnModel().getColumn(index).setPreferredWidth(size);
	}
	
	public void disconnection() {
		view.frame.dispose();
		loginView.getUserNameField().setText("");
		loginView.getPasswordField().setText("");
		loginView.frame.setVisible(true);
	}
	
}
