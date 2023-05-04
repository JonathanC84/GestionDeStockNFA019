package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;

public class View {

	public JFrame frame;

	public View() {
		initialize();
	}

	private void initialize() {
		
		ImageIcon icon = new ImageIcon("img/logo32.png");
		
		Color backgroundColor = new Color(0xbfe1fc);
		Color blueColor = new Color(0x0088ff);
		Border border = BorderFactory.createLineBorder(blueColor, 1, true);
		
		frame = new JFrame();
		frame.setTitle("GeStock");
		frame.setSize(1400, 900);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(icon.getImage());
		frame.getContentPane().setBackground(backgroundColor);
		
		JPanel header = new JPanel();
		header.setSize(1350, 30);
		header.setLocation((frame.getWidth() - header.getWidth())/2 , 10);
		header.setBackground(backgroundColor);
		frame.add(header);
		
		JLabel welcomeLabel = new JLabel();
		welcomeLabel.setText("Bienvenue sur GeStock !");
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		welcomeLabel.setHorizontalTextPosition(JLabel.RIGHT);
		header.add(welcomeLabel);
		
		//creation des onglets
		JTabbedPane mainTabs = new JTabbedPane(JTabbedPane.TOP);
		mainTabs.setForeground(new Color(0, 0, 0));
		mainTabs.setBackground(new Color(248, 248, 255));
		mainTabs.setFont(new Font("Arial", Font.PLAIN, 17));
		mainTabs.setSize(1350, 800);
		mainTabs.setLocation((frame.getWidth() - mainTabs.getWidth())/2 , 40);
		frame.getContentPane().add(mainTabs);
				
		//onglet produits
		JPanel productsPanel = new JPanel();
		mainTabs.addTab("Produits en stock", null, productsPanel, null);
		productsPanel.setLayout(null);
		
		JScrollPane productsScroll = new JScrollPane();
		productsScroll.setSize(1350, 500);
		productsPanel.add(productsScroll);
		
		JList productsList = new JList();
		productsList.setFont(new Font("Arial", Font.PLAIN, 14));
		productsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productsScroll.setViewportView(productsList);
		
		//onglet commandes
		JPanel supplyPanel = new JPanel();
		mainTabs.addTab("Commandes fournisseurs", null, supplyPanel, null);
		
		//onglets utilisateurs
		JPanel usersPanel = new JPanel();
		mainTabs.addTab("Utilisateurs", null, usersPanel, null);
		
		//methode qui redimensionne la fenêtre en fonction des composants
		//frame.pack();

	}
}
