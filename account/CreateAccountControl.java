package account;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

public class CreateAccountControl implements ActionListener {
	private JPanel container;
	private PongClient chat;

	public CreateAccountControl(JPanel container, PongClient chat) {

		this.container = container;
		this.chat = chat;

		try {
			chat.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent actionEvent) {
		String command = actionEvent.getActionCommand();

		if (command.equals("Submit")) {
			// Get the username and password the user entered.
			CreateAccountPanel createAccountPanel = (CreateAccountPanel) container.getComponent(2);

			if (createAccountPanel.getPassword().length() < 6) {
				displayError("Your password must be atleast 6 characters long");
				return;
			}

			if (!createAccountPanel.getPassword().equals(createAccountPanel.getPasswordVerify())) {
				displayError("Your passwords do not match");
				return;
			}

			CreateAccountData data = new CreateAccountData(createAccountPanel.getUsername(),
					createAccountPanel.getPassword());

			try {
				chat.sendToServer(data);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (command.equals("Cancel")) {
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "1");
		}
	}

	public void CreateAccountSuccessful() {
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "2");
	}

	public void displayError(String error) {

		CreateAccountPanel createAccountPanel = (CreateAccountPanel) container.getComponent(2);
		createAccountPanel.setError(error);
	}
}
