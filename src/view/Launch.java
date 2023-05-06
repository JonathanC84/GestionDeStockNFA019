package view;

import controller.MainController;

public class Launch {

	public static void main(String[] args) {

		System.setProperty("sun.java2d.uiScale", "1.0");
		
		LoginView loginView = new LoginView();
		View view = new View();
		MainController controller1 = new MainController(loginView, view);
		
		// au lancement de l'application on appelle la fenêtre login
		try {
			loginView.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
