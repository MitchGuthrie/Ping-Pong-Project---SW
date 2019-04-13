package game;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2158485686320114680L;

	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel errorLabel;

	// Constructor
	public LoginPanel(LoginControl loginControl) {
		JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));

		errorLabel = new JLabel("", JLabel.CENTER);
		errorLabel.setForeground(Color.RED);
		labelPanel.add(errorLabel);

		JLabel instructionLabel = new JLabel("Enter your username and password to log in.", JLabel.CENTER);
		labelPanel.add(instructionLabel);

		// Create a panel for the login information form.
		JPanel loginPanel = new JPanel(new GridLayout(2, 2, 5, 5));

		JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
		usernameField = new JTextField(10);

		JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
		passwordField = new JPasswordField(10);

		// Add labels
		loginPanel.add(usernameLabel);
		loginPanel.add(usernameField);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordField);

		// Create a panel for the buttons.
		JPanel buttonPanel = new JPanel();

		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(loginControl);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(loginControl);

		// add buttons
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		// Arrange the three panels in a grid.
		JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
		grid.add(labelPanel);
		grid.add(loginPanel);
		grid.add(buttonPanel);
		this.add(grid);
	}

	// Getters
	public String getUsername() {
		return usernameField.getText();
	}

	public String getPassword() {
		return new String(passwordField.getPassword());
	}

	// Setters
	public void setError(String error) {
		errorLabel.setText(error);
	}

}
