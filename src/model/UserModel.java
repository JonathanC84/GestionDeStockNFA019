package model;

import java.time.ZonedDateTime;

public class UserModel {

	private int id;
	private String prenom;
	private String nom;
	private String login;
	private String password;
	private String role;
	private ZonedDateTime ddc;

	public UserModel() {
		this.id = 0;
		this.prenom = "";
		this.nom = "";
		this.login = "";
		this.password = "";
		this.role = "";
		this.ddc = null;
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

	public ZonedDateTime getDdc() {
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

	public void setDdc(ZonedDateTime ddc) {
		this.ddc = ddc;
	}

	@Override
	public String toString() {
		return id + " - "+ prenom + " " + nom + " - login : " + login + " / "
				+ password + " - " + role;
	}
}

