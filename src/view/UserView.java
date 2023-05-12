package view;

import javax.swing.*;

public class UserView {

    private JLabel nomLabel, prenomLabel,userNameLabel, roleLabel, passwordLabel;
    private JTextField nomTextField, prenomTextField,userNameField, passwordField;
    private JComboBox<String> roleComboBox;
    
    public JPanel getUserView( ) {
        
        // Initialisation des composants
        nomLabel = new JLabel("Nom :");
        prenomLabel = new JLabel("Prénom :");
        userNameLabel = new JLabel("Psoeudo");
        roleLabel = new JLabel("Rôle :");
        userNameField = new JTextField(20);
        nomTextField = new JTextField(20);
        prenomTextField = new JTextField(20);
        passwordLabel = new JLabel("Mot de passe");
        passwordField= new JTextField();
        roleComboBox = new JComboBox<String>(new String[]{"Administrateur", "Gestionnaire", "Commercial"});
        
        // Création du panneau de contenu
        JPanel contentPane = new JPanel();
        contentPane.add(nomLabel);
        contentPane.add(nomTextField);
        contentPane.add(prenomLabel);
        contentPane.add(prenomTextField);
        contentPane.add(userNameLabel);
        contentPane.add(userNameField);
        contentPane.add(passwordLabel);
        contentPane.add(passwordField);
        contentPane.add(roleLabel);
        contentPane.add(roleComboBox);
        
        
        return contentPane;
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



