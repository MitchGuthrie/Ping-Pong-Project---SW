package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ContactControl implements ActionListener {

	private JPanel container;

	// Constructor
	public ContactControl(JPanel container) {
		this.container = container;
	}

	// Handle button clicks. For Later Lab
	public void actionPerformed(ActionEvent actionEvent) {

		String command = actionEvent.getActionCommand();

		if (command.equals("Delete Contact")) {

		} else if (command.equals("Add Contact")) {

		} else if (command.equals("Log Out")) {

		}
	}

	// Method for later lab
	public void displayError(String error) {

	}
}
