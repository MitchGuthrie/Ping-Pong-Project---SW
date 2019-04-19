package database;

import java.io.Serializable;

public class CreateAccountData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6257452341842856839L;

	private String username;
	private String password;

	// Constructor
	public CreateAccountData(String username, String password) {
		setUsername(username);
		setPassword(password);
	}

	// Getters
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	// Setters
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
