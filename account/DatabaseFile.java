package account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseFile {
	InputStream in;
	PrintWriter out;
	HashMap database;
	Integer idNum;
	String username;
	String password;

	public DatabaseFile() {
		// empty constructor
	}

	// Create Account
	public String createAccount(String username, String password) {
		InputStream in = null;
		PrintWriter out = null;

		ArrayList<String> array = new ArrayList<String>();

		try {
			out = new PrintWriter(new FileOutputStream(new File("userData.txt"), true));
			in = new FileInputStream("userData.txt");
			BufferedReader buf = new BufferedReader(new InputStreamReader(in));

			String line = buf.readLine();

			while (line != null) {
				array.add(line);
				line = buf.readLine();
			}
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < array.size(); i++) {
			String[] usernames = array.get(i).split(",");
			if (username.equals(usernames[1])) {
				return ("Username Taken");
			}
		}

		User user = new User(username, password);

		String output = user.getidNum() + "," + username + "," + password;
		out.println(output);
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ("Account Created");

	}

	// Verify Account
	public String verifyAccount(String username, String password) {
		System.out.println("Username/Password: " + username + " " + password);

		InputStream in = null;
		PrintWriter out = null;

		ArrayList<String> array = new ArrayList<String>();

		try {
			out = new PrintWriter(new FileOutputStream(new File("userData.txt"), true));
			out.close();
			in = new FileInputStream("userData.txt");
			BufferedReader buf = new BufferedReader(new InputStreamReader(in));

			String line = buf.readLine();

			while (line != null) {
				array.add(line);
				line = buf.readLine();
			}
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < array.size(); i++) {

			String[] usernameAndPassword = array.get(i).split(",");
			if (username.equals(usernameAndPassword[1]) && password.equals(usernameAndPassword[2])) {
				try {
					in.close();
					System.out.println("Login Successful \n");
					return ("Login Successful");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Login Unsuccessful \n");
		return ("Login Unsuccessful");
	}

	public void readFile() {

	}

	public void WriteFile() {

	}

}
