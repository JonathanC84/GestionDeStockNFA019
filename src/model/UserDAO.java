package model;

import java.sql.*;

/**
 * DATA ACCESS OBJECT
 * User
 * @author Jonathan Cayrol
 *
 */

public class UserDAO {

	private Connection conn;
	
	public UserDAO() {
		conn = Connexion.getConnection();
	}
	
}
