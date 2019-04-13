package game;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccountPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4870006786214972137L;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField passwordCheck;
	private JLabel errorLabel;

	// Constructor
	public CreateAccountPanel(CreateAccountControl accountControl) {
		JPanel labelPanel = new JPanel(new GridLayout(3, 1, 5, 5));

		errorLabel = new JLabel("", JLabel.CENTER);
		errorLabel.setForeground(Color.RED);
		labelPanel.add(errorLabel);

		JLabel instructionLabel = new JLabel("Enter your username and password to create an account.", JLabel.CENTER);
		labelPanel.add(instructionLabel);

		JLabel instructionLabel2 = new JLabel("Your Password must be atleast 6 characters.", JLabel.CENTER);
		labelPanel.add(instructionLabel2);

		// Create a panel for the login information form.
		JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));

		JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
		username = new JTextField(10);

		JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
		password = new JPasswordField(10);

		JLabel passwordVerifyLabel = new JLabel("Verify Password:", JLabel.RIGHT);
		passwordCheck = new JPasswordField(10);

		// add panels to GUI
		loginPanel.add(usernameLabel);
		loginPanel.add(username);
		loginPanel.add(passwordLabel);
		loginPanel.add(password);
		loginPanel.add(passwordVerifyLabel);
		loginPanel.add(passwordCheck);

		// Create a panel for the buttons.
		JPanel buttonPanel = new JPanel();

		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(accountControl);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(accountControl);

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

	public String getUsername() {
		return username.getText();
	}

	public String getPassword() {
		return new String(password.getPassword());
	}

	public String getPasswordVerify() {
		return new String(passwordCheck.getPassword());
	}

	public void setError(String error) {
		errorLabel.setText(error);
	}

}
