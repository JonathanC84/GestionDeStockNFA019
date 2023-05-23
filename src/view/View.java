package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Component;

public class View {

	public JFrame frame;
	private JLabel welcomeLabel;
	private JButton logoutBtn, addProductBtn, searchBtn,
	addOrderBtn, editOrderBtn, deleteOrderBtn,
	addSupplierBtn, editSupplierBtn, deleteSupplierBtn,
	addUserBtn, editUserBtn, deleteUserBtn;
	private JTextField searchField;
	private ArrayList<String> searchTypes;
	private JComboBox<String> searchTypeSelection;
	private JTabbedPane mainTabs;
	private JPanel header, productPanel, usersPanel, supplyPanel, movementPanel,
	entriePanel, removalPanel, searchPanel, orderPanel, supplierPanel,
	orderButtonsPanel, supplierButtonPanel, usersButtonPanel;
	private JScrollPane productScroll, entrieScroll, removalScroll, orderScroll, orderDetailScroll, supplierScroll, userScroll;
	private JTable productTable, entrieTable, removalTable;
	private JList orderList, orderDetailList, supplierList, userList;
	private ImageIcon icon, logout;
		
	public View() {
		initialize();
	}

	private void initialize() {
		
		icon = new ImageIcon(getClass().getClassLoader().getResource("logo32.png"));
		logout = new ImageIcon(getClass().getClassLoader().getResource("logout.png"));
		
		Font font12 = new Font("Arial", Font.PLAIN, 12);
		Font font14 = new Font("Arial", Font.PLAIN, 14);
		Font font16 = new Font("Arial", Font.PLAIN, 16);
		Font font18 = new Font("Arial", Font.PLAIN, 18);
		
		frame = new JFrame();
		frame.setTitle("GeStock");
		frame.setSize(1400, 900);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(icon.getImage());
		frame.getContentPane().setBackground(new Color(0xbfe1fc));
		
		header = new JPanel();
		header.setSize(1350, 30);
		header.setLocation((frame.getWidth() - header.getWidth())/2 , 10);
		header.setLayout(new FlowLayout(FlowLayout.RIGHT));
		header.setOpaque(false);
		frame.getContentPane().add(header);
		
		welcomeLabel = new JLabel();
		welcomeLabel.setText("Bienvenue sur GeStock !");
		welcomeLabel.setFont(font16);
		welcomeLabel.setLayout(null);
		header.add(welcomeLabel);
		
		logoutBtn = new JButton();
		logoutBtn.setPreferredSize(new Dimension(24, 24));
		logoutBtn.setIcon(logout);
		logoutBtn.setToolTipText("Se déconnecter");
		header.add(logoutBtn);
		
		/*
		 * Création des onglets
		 */
		mainTabs = new JTabbedPane(JTabbedPane.TOP);
		mainTabs.setForeground(new Color(0, 0, 0));
		mainTabs.setBackground(new Color(248, 248, 255));
		mainTabs.setFont(font18);
		mainTabs.setSize(1350, 800);
		mainTabs.setLocation((frame.getWidth() - mainTabs.getWidth())/2 , 20);
		frame.getContentPane().add(mainTabs);
				
		/*
		 * onglet produits
		 */
		productPanel = new JPanel();
		productPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));
		mainTabs.addTab("Produits en stock", null, productPanel, null);
		searchPanel = new JPanel();
		productPanel.add(searchPanel);
		
		// barre de recherche
		searchField = new JTextField(25);
		searchField.setFont(font16);
		
		searchTypes = new ArrayList<String>();
		searchTypes.add("par libellé");
		searchTypes.add("par référence");
		searchTypes.add("par fournisseur");
		searchTypes.add("par catégorie");

		searchTypeSelection = new JComboBox<String>();
		for(String type : searchTypes) {
			searchTypeSelection.addItem(type);
		}
		searchTypeSelection.setBackground(Color.white);
		searchTypeSelection.setFont(font16);
		
		// bouton rechercher
		searchBtn = new JButton("Rechercher produit");
		searchBtn.setToolTipText("Rechercher un produit");
		//searchBtn.setPreferredSize(new Dimension(200,40));
		searchBtn.setFont(font16);
		
		searchPanel.add(searchField);
		searchPanel.add(searchTypeSelection);
		searchPanel.add(searchBtn);
		
		// bouton ajouter produit
		addProductBtn = new JButton("Ajouter produit");
		addProductBtn.setToolTipText("Créer une ligne produit");
		//addProductBtn.setPreferredSize(new Dimension(200,40));
		addProductBtn.setFont(font16);
		productPanel.add(addProductBtn);
		
		productTable = new JTable();
		productTable.setPreferredScrollableViewportSize(new Dimension(1300, 400));
		productTable.setRowHeight(30);
		productTable.setFont(font16);
		productTable.getTableHeader().setFont(font16);
		productTable.getTableHeader().setBackground(Color.BLACK);
		
		productScroll = new JScrollPane(productTable);
		productPanel.add(productScroll);
		
		// panel mouvements
		movementPanel = new JPanel();
		movementPanel.setSize(new Dimension(1300, 200));
		productPanel.add(movementPanel);
		
		// panel entrees
		entriePanel = new JPanel();
		entriePanel.setBorder(BorderFactory.createTitledBorder(null, "Entrées", 0, 0, font14));
		entrieTable = new JTable(); 
		entrieTable.setPreferredScrollableViewportSize(new Dimension(632,210));
		entrieTable.setRowHeight(20);
		entrieTable.setFont(font14);
		entrieTable.getTableHeader().setFont(font12);
		entrieTable.getTableHeader().setBackground(Color.BLACK);
		entrieScroll = new JScrollPane(entrieTable);
		entriePanel.add(entrieScroll);
		movementPanel.add(entriePanel);
		
		// panel sorties
		removalPanel = new JPanel();
		removalPanel.setBorder(BorderFactory.createTitledBorder(null, "Sorties", 0, 0, font14));
		removalTable = new JTable();
		removalTable.setPreferredScrollableViewportSize(new Dimension(632,210));
		removalTable.setRowHeight(20);
		removalTable.setFont(font14);
		removalTable.getTableHeader().setFont(font12);
		removalTable.getTableHeader().setBackground(Color.BLACK);
		removalScroll = new JScrollPane(removalTable);
		removalPanel.add(removalScroll);
		movementPanel.add(removalPanel);
		
		/*
		 * onglet commandes
		 */
		supplyPanel = new JPanel();
		mainTabs.addTab("Commandes fournisseurs", null, supplyPanel, null);
		
		// Panneau Commandes
		orderPanel = new JPanel();
		orderPanel.setBorder(BorderFactory.createTitledBorder(null, "Commandes", 0, 0, font18));
		orderPanel.setPreferredSize(new Dimension(1300, 400));
		
		// Liste commandes
		orderList = new JList();
		orderList.setFont(font14);
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderScroll = new JScrollPane(orderList);
		orderScroll.setPreferredSize(new Dimension(830, 320));
		
		// Liste des détails commande (lignes produits)
		orderDetailList = new JList();
		orderDetailList.setFont(font14);
		orderDetailList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderDetailScroll = new JScrollPane(orderDetailList);
		orderDetailScroll.setPreferredSize(new Dimension(430, 320));
		
		orderButtonsPanel = new JPanel();
		
		addOrderBtn = new JButton("Créer une commande");
		addOrderBtn.setFont(font16);
		editOrderBtn = new JButton("Modifier une commande");
		editOrderBtn.setFont(font16);
		deleteOrderBtn = new JButton("Supprimer une commande");
		deleteOrderBtn.setFont(font16);
		
		orderButtonsPanel.add(addOrderBtn);
		orderButtonsPanel.add(editOrderBtn);
		orderButtonsPanel.add(deleteOrderBtn);
		
		orderPanel.add(orderButtonsPanel);
		orderPanel.add(orderScroll);
		orderPanel.add(orderDetailScroll);
		
		// Panneau Fournisseurs
		supplierPanel = new JPanel();
		supplierPanel.setBorder(BorderFactory.createTitledBorder(null, "Fournisseurs", 0, 0, font18));
		supplierPanel.setPreferredSize(new Dimension(1300, 340));
		
		supplierButtonPanel = new JPanel();
		supplierButtonPanel.setBounds(900, 30, 250, 300);
		
		addSupplierBtn = new JButton("Ajouter un fournisseur");
		addSupplierBtn.setBounds(0, 100, 225, 28);
		addSupplierBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		addSupplierBtn.setFont(font16);
		editSupplierBtn = new JButton("Modifier un fournisseur");
		editSupplierBtn.setBounds(0, 150, 225, 28);
		editSupplierBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		editSupplierBtn.setFont(font16);
		deleteSupplierBtn = new JButton("Supprimer un fournisseur");
		deleteSupplierBtn.setBounds(0, 200, 225, 28);
		deleteSupplierBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteSupplierBtn.setFont(font16);
		supplierPanel.setLayout(null);
		
		supplierList = new JList();
		supplierList.setFont(font14);
		
		supplierScroll = new JScrollPane(supplierList);
		supplierScroll.setBounds(200, 30, 600, 300);
		supplierScroll.setPreferredSize(new Dimension(700, 300));
		supplierPanel.add(supplierScroll);
		
		supplierButtonPanel.setLayout(null);
		
		supplierButtonPanel.add(addSupplierBtn);
		supplierButtonPanel.add(editSupplierBtn);
		supplierButtonPanel.add(deleteSupplierBtn);
		
		supplierPanel.add(supplierButtonPanel);
		
		supplyPanel.add(orderPanel);
		supplyPanel.add(supplierPanel);
	
		/*
		 * onglets utilisateurs
		 */
		usersPanel = new JPanel();
		mainTabs.addTab("Utilisateurs", null, usersPanel, null);
		
		usersPanel.setPreferredSize(new Dimension(1300, 640));
		
		usersButtonPanel = new JPanel();
		usersButtonPanel.setBounds(900, 30, 250, 300);
		
		addUserBtn = new JButton("Ajouter un utilisateur");
		addUserBtn.setBounds(0, 100, 225, 28);
		addUserBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		addUserBtn.setFont(font16);
		editUserBtn = new JButton("Modifier un utilisateur");
		editUserBtn.setBounds(0, 150, 225, 28);
		editUserBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		editUserBtn.setFont(font16);
		deleteUserBtn = new JButton("Supprimer un utilisateur");
		deleteUserBtn.setBounds(0, 200, 225, 28);
		deleteUserBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteUserBtn.setFont(font16);
		usersPanel.setLayout(null);
		
		userList = new JList();
		userList.setFont(font14);
		
		userScroll = new JScrollPane(userList);
		userScroll.setBounds(200, 30, 600, 300);
		userScroll.setPreferredSize(new Dimension(700, 300));
		usersPanel.add(userScroll);
		
		usersButtonPanel.setLayout(null);
		
		usersButtonPanel.add(addUserBtn);
		usersButtonPanel.add(editUserBtn);
		usersButtonPanel.add(deleteUserBtn);
		
		usersPanel.add(usersButtonPanel);
		
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public JComboBox<String> getSearchTypeSelection() {
		return searchTypeSelection;
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

	public JButton getAddOrderBtn() {
		return addOrderBtn;
	}

	public JButton getEditOrderBtn() {
		return editOrderBtn;
	}

	public JButton getDeleteOrderBtn() {
		return deleteOrderBtn;
	}

	public JButton getAddSupplierBtn() {
		return addSupplierBtn;
	}

	public JButton getEditSupplierBtn() {
		return editSupplierBtn;
	}

	public JButton getDeleteSupplierBtn() {
		return deleteSupplierBtn;
	}

	public JButton getAddUserBtn() {
		return addUserBtn;
	}

	public JButton getEditUserBtn() {
		return editUserBtn;
	}

	public JButton getDeleteUserBtn() {
		return deleteUserBtn;
	}

	public JList getOrderList() {
		return orderList;
	}

	public JList getOrderDetailList() {
		return orderDetailList;
	}
	
	public JList getSupplierList() {
		return supplierList;
	}
	
	public JList getUserList() {
		return userList;
	}

	public void setUserList(JList userList) {
		this.userList = userList;
	}

	public void setOrderList(JList orderList) {
		this.orderList = orderList;
	}

	public void setOrderDetailList(JList orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	
	public void setSupplierList(JList supplierList) {
		this.supplierList = supplierList;
	}
	
}
