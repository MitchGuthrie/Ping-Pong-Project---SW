package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	Connection con;

	public static void main(String[] args) {
		Database pro = new Database();
		pro.createConnection();
	}

	public void createConnection() {
		try {
			// Loads driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");

			System.out.println("Database connected");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getUsername() {
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
			while (rs.next()) {
				String username = rs.getString("username");
				System.out.println(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void getPassword() {
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
			while (rs.next()) {
				String username = rs.getString("password");
				System.out.println(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addUser(String username, String password) {
		try {
			Statement stmt = con.createStatement();
			String dbop = "INSERT INTO USERS (username, password) VALUES('" + username + "'" + ", " + "'" + password
					+ "')";
			stmt.execute(dbop);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
