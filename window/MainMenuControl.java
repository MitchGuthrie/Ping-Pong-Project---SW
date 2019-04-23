package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import game.Game;
import game.GameLauncher;

public class MainMenuControl implements ActionListener {

	private JPanel container;
	public GameLauncher gameLauncher;
	public Game game;

	// Constructor
	public MainMenuControl(JPanel container) {
		this.container = container;
		this.gameLauncher = new GameLauncher();
		game = new Game();
	}

	// Handle button clicks. For Later Lab
	public void actionPerformed(ActionEvent actionEvent) {

		String command = actionEvent.getActionCommand();

		if (command.equals("Play Game")) {
			GameLauncher.Main(null);

		} else if (command.equals("Add Contact")) {

		} else if (command.equals("Log Out")) {
			System.exit(0);
		} else if (command.equals("Level A")) {
			game.setLevel(1);
		} else if (command.equals("Level B")) {
			game.setLevel(2);
		}
	}

	// Method for later lab
	public void displayError(String error) {

	}
}
