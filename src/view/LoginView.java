package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class LoginView {

	/**
	 * Fenêtre de connexion s'ouvrant au lancement de l'application
	 */
	public JFrame frame;
	private JLabel welcomeLabel, userNameLabel, passwordLabel;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private ImageIcon icon, logo;

	public LoginView() {
		initialize();
	}

	private void initialize() {
		
		icon = new ImageIcon(getClass().getClassLoader().getResource("logo32.png"));
		logo = new ImageIcon(getClass().getClassLoader().getResource("logo180.png"));
		
		Color backgroundColor = new Color(0xbfe1fc);
		Color blueColor = new Color(0x0088ff);
		
		Font titleFont = new Font("Arial",Font.PLAIN,64);
		Font fieldFont = new Font("Arial",Font.PLAIN,16);
		
		//fenêtre
		frame = new JFrame();
		frame.setTitle("GeStock");
		frame.setSize(1000, 800);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(icon.getImage());
		frame.getContentPane().setBackground(backgroundColor);
		
		//logo et texte d'introduction
		welcomeLabel = new JLabel(logo);
		welcomeLabel.setBounds(0, 40, 1000, 320);
		welcomeLabel.setLayout(null);
		welcomeLabel.setText("GeStock");
		welcomeLabel.setVerticalTextPosition(JLabel.CENTER);
		welcomeLabel.setHorizontalTextPosition(JLabel.RIGHT);
		welcomeLabel.setForeground(blueColor);
		welcomeLabel.setFont(titleFont);
		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		welcomeLabel.setVerticalAlignment(JLabel.CENTER);
		welcomeLabel.setIconTextGap(25);
		frame.add(welcomeLabel);
		
		//champ du nom d'utilisateur
		userNameLabel = new JLabel("Login :");
		userNameLabel.setBounds(350, 400, 300, 24);
		userNameLabel.setFont(fieldFont);
		userNameLabel.setLabelFor(userNameField);
		userNameLabel.setHorizontalAlignment(JLabel.LEFT);
		userNameLabel.setForeground(blueColor);
		frame.add(userNameLabel);
		
		userNameField = new JTextField();
		userNameField.setBounds(350, 430, 300, 32);
		userNameField.setFont(fieldFont);
		frame.add(userNameField);
		
		//champ du mot de passe utilisateur
		passwordLabel = new JLabel("Mot de passe :");
		passwordLabel.setBounds(350, 480, 300, 24);
		passwordLabel.setFont(fieldFont);
		passwordLabel.setLabelFor(passwordField);
		passwordLabel.setHorizontalAlignment(JLabel.LEFT);
		passwordLabel.setForeground(blueColor);
		frame.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(350, 510, 300, 32);
		passwordField.setFont(fieldFont);
		frame.add(passwordField);

		//bouton de validation
		loginButton = new JButton("Valider");
		loginButton.setBounds(350, 590, 300, 32);
		loginButton.addActionListener(null);
		loginButton.setFont(fieldFont);
		frame.add(loginButton);
		
	}
		
	public JTextField getUserNameField() {
		return userNameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

}
