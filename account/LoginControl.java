package account;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

public class LoginControl implements ActionListener {
	private JPanel container;
	private PongClient chat;
	private User user;

	// Constructor
	public LoginControl(JPanel container, PongClient chat) {
		this.container = container;
		this.chat = chat;

		try {
			chat.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent actionEvent) {
		String command = actionEvent.getActionCommand();

		// Cancel button
		if (command == "Cancel") {
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "1");
		}

		// Submit button
		else if (command == "Submit") {
			LoginPanel loginPanel = (LoginPanel) container.getComponent(1);
			LoginData data = new LoginData(loginPanel.getUsername(), loginPanel.getPassword());

			if (data.getUsername().equals("") || data.getPassword().equals("")) {
				displayError("You must enter a username and password.");
				return;
			}

			try {
				chat.sendToServer(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void loginSuccess(String username, String password) {
		user = new User(username, password);
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "4");
	}

	public void displayError(String error) {
		LoginPanel loginPanel = (LoginPanel) container.getComponent(1);
		loginPanel.setError(error);

	}
}
