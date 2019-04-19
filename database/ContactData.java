package database;

import java.io.Serializable;
import java.util.ArrayList;

public class ContactData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2171874252601791165L;

	private ArrayList<String> username;

	// Getters
	public ArrayList<String> getUsername() {
		return username;
	}

	// Setters
	public void addUsername(String username) {
		this.username.add(username);
	}

	public ContactData() {
		username = new ArrayList<String>();
	}
}