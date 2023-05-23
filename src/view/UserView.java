package view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.*;

import model.UserModel;

public class UserView {
	
	private JPanel panel;
    private JLabel nomLabel, prenomLabel,userNameLabel, roleLabel, passwordLabel;
    private JTextField nomTextField, prenomTextField,userNameField, passwordField;
    private JComboBox<String> roleComboBox;
    
    public JPanel getPanel(UserModel user) {
        
    	panel = new JPanel();
        
    	Font font = new Font("Arial", Font.PLAIN, 16);
    	
    	// Initialisation des composants
        nomLabel = new JLabel("Nom");
        nomLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nomLabel.setFont(font);
        
        nomTextField = new JTextField(20);
        nomTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        nomTextField.setText(user.getNom());
        nomTextField.setFont(font);
        
        prenomLabel = new JLabel("Prénom");
        prenomLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        prenomLabel.setFont(font);
        
        prenomTextField = new JTextField(20);
        prenomTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        prenomTextField.setText(user.getPrenom());
        prenomTextField.setFont(font);
        
        userNameLabel = new JLabel("Pseudo");
        userNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        userNameLabel.setFont(font);
        
        userNameField = new JTextField(20);
        userNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        userNameField.setText(user.getLogin());
        userNameField.setFont(font);
        
        passwordLabel = new JLabel("Mot de passe");
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordLabel.setFont(font);
        
        passwordField= new JTextField();
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordField.setText(user.getPassword());
        passwordField.setFont(font);
        
        roleLabel = new JLabel("Rôle");
        roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        roleLabel.setFont(font);
        
        String[] roles = {"administrateur", "gestionnaire", "commercial"};
        roleComboBox = new JComboBox<String>(roles);
        roleComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        roleComboBox.setSelectedItem(user.getRole());
        roleComboBox.setFont(font);
        
        // Création du panneau de contenu
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(nomLabel);
        panel.add(nomTextField);
        panel.add(prenomLabel);
        panel.add(prenomTextField);
        panel.add(userNameLabel);
        panel.add(userNameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(roleLabel);
        panel.add(roleComboBox);
        
        
        return panel;
    }

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JTextField passwordField) {
		this.passwordField = passwordField;
	}

	public JLabel getNomLabel() {
		return nomLabel;
	}

	public void setNomLabel(JLabel nomLabel) {
		this.nomLabel = nomLabel;
	}

	public JLabel getPrenomLabel() {
		return prenomLabel;
	}

	public void setPrenomLabel(JLabel prenomLabel) {
		this.prenomLabel = prenomLabel;
	}

	public JLabel getUserNameLabel() {
		return userNameLabel;
	}

	public void setUserNameLabel(JLabel userNameLabel) {
		this.userNameLabel = userNameLabel;
	}

	public JLabel getRoleLabel() {
		return roleLabel;
	}

	public void setRoleLabel(JLabel roleLabel) {
		this.roleLabel = roleLabel;
	}

	public JTextField getNomTextField() {
		return nomTextField;
	}

	public void setNomTextField(JTextField nomTextField) {
		this.nomTextField = nomTextField;
	}

	public JTextField getPrenomTextField() {
		return prenomTextField;
	}

	public void setPrenomTextField(JTextField prenomTextField) {
		this.prenomTextField = prenomTextField;
	}

	public JTextField getUserNameField() {
		return userNameField;
	}

	public void setUserNameField(JTextField userNameField) {
		this.userNameField = userNameField;
	}

	public JComboBox<String> getRoleComboBox() {
		return roleComboBox;
	}

	public void setRoleComboBox(JComboBox<String> roleComboBox) {
		this.roleComboBox = roleComboBox;
	}  
}