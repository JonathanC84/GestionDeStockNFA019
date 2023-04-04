package model;


/**
 * 
 * Objet User
 * @author Jonathan Cayrol
 *
 */
public class UserModel {

	private int id;
	private String login;
	private String password;

	public UserModel() {
		this.id = 0;
		this.login = "";
		this.password = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

