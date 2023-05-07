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
	private JButton logoutBtn;
	private JTabbedPane mainTabs;
	private JPanel productPanel;
	private JPanel usersPanel;
	private JPanel supplyPanel;
	private JScrollPane productScroll;
	private JTable productTable;
	private ImageIcon icon;
	private ImageIcon logout;
	
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
		mainTabs.addTab("Produits en stock", null, productPanel, null);
		
		productTable = new JTable();
		productTable.setPreferredScrollableViewportSize(new Dimension(1330, 400));
		productTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		productTable.setRowHeight(30);
		productTable.setFont(new Font("Arial", Font.PLAIN, 16));
		
		productScroll = new JScrollPane(productTable);
		productPanel.add(productScroll);
		
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
	
	public JPanel getUsersPanel() {
		return usersPanel;
	}

	public JPanel getSupplyPanel() {
		return supplyPanel;
	}
	
	public JTabbedPane getMainTabs() {
		return mainTabs;
	}
	
}
