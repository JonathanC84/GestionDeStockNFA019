package model;

public class UserModel {

	private int id;
	private String prenom;
	private String nom;
	private String login;
	private String password;
	private String role;
	private String ddc;
	
	public UserModel() {
		this.id = 0;
		this.prenom = "";
		this.nom = "";
		this.login = "";
		this.password = "";
		this.role = "";
		this.ddc = "";
	}

	public int getId() {
		return id;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getDdc() {
		return ddc;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setDdc(String ddc) {
		this.ddc = ddc;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", login=" + login + ", password="
				+ password + ", role=" + role + ", ddc=" + ddc + "]";
	}	
}

