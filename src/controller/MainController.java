package controller;

import javax.swing.JOptionPane;

import view.LoginView;
import view.View;

public class MainController {

	private LoginView loginView;
	private View view;
	
	public MainController(LoginView loginView, View view) {
		this.loginView = loginView;
		this.view = view;
		initController();
	}
	
	public void initController() {
		loginView.getUserNameField().addActionListener(e -> auth());
		loginView.getPasswordField().addActionListener(e -> auth());
		loginView.getLoginButton().addActionListener(e -> auth());
	}
	
	private void auth() {
		String user = loginView.getUserNameField().getText();
		char[] pass = loginView.getPasswordField().getPassword();
		String password = "";
		for (int i = 0; i < pass.length; i++) {
			password += pass[i];
		}
				
		if(user.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(loginView.frame, "Veuillez renseigner tous les champs");
		} else {
			password = "";
			for (char c : pass) {
				c = 0;
			}
			
			initView();
		}
	}
	
	public void initView() {
		view.frame.setVisible(true);
		loginView.frame.dispose();
	}
	
	
}
