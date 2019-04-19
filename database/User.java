package database;

import java.io.Serializable;
import java.util.Random;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9222331306831385802L;
	private int idNum;
	private String username;
	private String password;

	// Constructor
	public User(String username, String password) {
		Random randNum = new Random();
		Integer tempNum = -1;
		while (tempNum < 0) {
			tempNum = randNum.nextInt();
		}
		setidNum(tempNum);
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

	public int getidNum() {
		return idNum;
	}

	// Setters
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private void setidNum(int num) {
		idNum = num;
	}

}
