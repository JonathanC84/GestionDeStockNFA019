package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;

public class View {

	public JFrame frame;
	private JPanel header;
	private JLabel welcomeLabel;
	private JButton logoutBtn, addProductBtn;
	private JTabbedPane mainTabs;
	private JPanel productPanel, usersPanel, supplyPanel, movementPanel, entriePanel, removalPanel;
	private JScrollPane productScroll, entrieScroll, removalScroll;
	private JTable productTable, entrieTable, removalTable;
	private ImageIcon icon, logout;
		
	public View() {
		initialize();
	}

	private void initialize() {
		
		icon = new ImageIcon(getClass().getClassLoader().getResource("logo32.png"));
		logout = new ImageIcon(getClass().getClassLoader().getResource("logout.png"));
		
		Color backgroundColor = new Color(0xbfe1fc);
		
		frame = new JFrame();
		frame.setTitle("GeStock");
		frame.setSize(1400, 900);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(icon.getImage());
		frame.getContentPane().setBackground(backgroundColor);
		
		header = new JPanel();
		header.setSize(1350, 30);
		header.setLocation((frame.getWidth() - header.getWidth())/2 , 10);
		header.setLayout(new FlowLayout(FlowLayout.RIGHT));
		header.setOpaque(false);
		frame.add(header);
		
		welcomeLabel = new JLabel();
		welcomeLabel.setText("Bienvenue sur GeStock !");
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		welcomeLabel.setLayout(null);
		header.add(welcomeLabel);
		
		logoutBtn = new JButton();
		logoutBtn.setPreferredSize(new Dimension(24, 24));
		logoutBtn.setIcon(logout);
		logoutBtn.setToolTipText("Se déconnecter");
		header.add(logoutBtn);
		
		//creation des onglets
		mainTabs = new JTabbedPane(JTabbedPane.TOP);
		mainTabs.setForeground(new Color(0, 0, 0));
		mainTabs.setBackground(new Color(248, 248, 255));
		mainTabs.setFont(new Font("Arial", Font.PLAIN, 18));
		mainTabs.setSize(1350, 800);
		mainTabs.setLocation((frame.getWidth() - mainTabs.getWidth())/2 , 20);
		frame.getContentPane().add(mainTabs);
				
		//onglet produits
		productPanel = new JPanel();
		productPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));
		mainTabs.addTab("Produits en stock", null, productPanel, null);
		
		productTable = new JTable();
		productTable.setPreferredScrollableViewportSize(new Dimension(1300, 400));
		//productTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		productTable.setRowHeight(30);
		productTable.setFont(new Font("Arial", Font.PLAIN, 16));

		productScroll = new JScrollPane(productTable);
		productPanel.add(productScroll);

		// bouton ajouter produit
		addProductBtn = new JButton("Ajouter produit");
		addProductBtn.setToolTipText("Créer une ligne produit");
		addProductBtn.setPreferredSize(new Dimension(200,40));
		addProductBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		productPanel.add(addProductBtn);
		
		// panel mouvements
		movementPanel = new JPanel();
		movementPanel.setSize(new Dimension(1300, 200));
		productPanel.add(movementPanel);
		
		// panel entrees
		entriePanel = new JPanel();
		entriePanel.setBorder(BorderFactory.createTitledBorder("Entrées"));
		entrieTable = new JTable(); 
		entrieTable.setPreferredScrollableViewportSize(new Dimension(632,210));
		entrieTable.setRowHeight(20);
		entrieTable.setFont(new Font("Arial", Font.PLAIN, 14));
		entrieScroll = new JScrollPane(entrieTable);
		entriePanel.add(entrieScroll);
		movementPanel.add(entriePanel);
		
		// panel sorties
		removalPanel = new JPanel();
		removalPanel.setBorder(BorderFactory.createTitledBorder("Sorties"));
		removalTable = new JTable();
		removalTable.setPreferredScrollableViewportSize(new Dimension(632,210));
		removalTable.setRowHeight(20);
		removalTable.setFont(new Font("Arial", Font.PLAIN, 14));
		removalScroll = new JScrollPane(removalTable);
		removalPanel.add(removalScroll);
		movementPanel.add(removalPanel);
		
		//onglet commandes
		supplyPanel = new JPanel();
		mainTabs.addTab("Commandes fournisseurs", null, supplyPanel, null);
		
		//onglets utilisateurs
		usersPanel = new JPanel();
		mainTabs.addTab("Utilisateurs", null, usersPanel, null);

	}

	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}

	public JButton getLogoutBtn() {
		return logoutBtn;
	}
	
	public JTable getProductTable() {
		return productTable;
	}

	public JPanel getProductPanel() {
		return productPanel;
	}
	
	public JTable getEntrieTable() {
		return entrieTable;
	}

	public JTable getRemovalTable() {
		return removalTable;
	}

	public JPanel getUsersPanel() {
		return usersPanel;
	}

	public JPanel getSupplyPanel() {
		return supplyPanel;
	}
	
	public JTabbedPane getMainTabs() {
		return mainTabs;
	}
	
	public JButton getAddProductBtn() {
		return addProductBtn;
	}
	
}
