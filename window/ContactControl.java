package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import game.GameLauncher;

public class ContactControl implements ActionListener {

	private JPanel container;
	public GameLauncher gameLauncher;

	// Constructor
	public ContactControl(JPanel container) {
		this.container = container;
		this.gameLauncher = new GameLauncher();
	}

	// Handle button clicks. For Later Lab
	public void actionPerformed(ActionEvent actionEvent) {

		String command = actionEvent.getActionCommand();

		if (command.equals("Play Game")) {
			GameLauncher.Main(null);

		} else if (command.equals("Add Contact")) {

		} else if (command.equals("Log Out")) {

		}
	}

	// Method for later lab
	public void displayError(String error) {

	}
}
