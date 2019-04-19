package window;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class InitialControl implements ActionListener {

	private JPanel container;

	// Constructor
	public InitialControl(JPanel container) {
		this.container = container;
	}

	// Handles button clicks.
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();

		// Login Button
		if (command.equals("Login")) {
			LoginPanel loginPanel = (LoginPanel) container.getComponent(1);
			loginPanel.setError("");
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "2");
		}

		// Create Button
		else if (command.equals("Create")) {
			CreateAccountPanel cac = (CreateAccountPanel) container.getComponent(2);
			cac.setError("");
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "3");
		}
	}
}
