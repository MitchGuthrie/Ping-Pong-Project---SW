package game;

import java.io.Serializable;

public class LoginData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7918565348383270901L;

	private String username;
	private String password;

	// Constructor
	public LoginData(String username, String password) {
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