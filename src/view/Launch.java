package view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.MainController;

public class Launch {

	public static void main(String[] args) {

		System.setProperty("sun.java2d.uiScale", "1.0");
		
		 try {
	            // Set cross-platform Java L&F (also called "Metal")
			 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		
		LoginView loginView = new LoginView();
		View view = new View();
		
		@SuppressWarnings("unused")
		MainController controller1 = new MainController(loginView, view);
		
		// au lancement de l'application on appelle la fenêtre login
		try {
			loginView.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
