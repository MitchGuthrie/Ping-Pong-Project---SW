package game;

import java.applet.Applet;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameLauncher extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1599928541473749839L;
	private static Game game = new Game();
	public static final boolean DEBUG = false;

	@Override
	public void init() {
		setLayout(new BorderLayout());
		add(game, BorderLayout.CENTER);
		setMaximumSize(Game.DIMENSIONS);
		setMinimumSize(Game.DIMENSIONS);
		setPreferredSize(Game.DIMENSIONS);
		game.debug = DEBUG;
		game.isApplet = false;
	}

	@Override
	public void start() {
		game.start();
	}

	@Override
	public void stop() {
		game.stop();
	}

	public static void Main(String[] args) {
		// Sets up game frame/window
		game.setMinimumSize(Game.DIMENSIONS);
		game.setMaximumSize(Game.DIMENSIONS);
		game.setPreferredSize(Game.DIMENSIONS);

		game.frame = new JFrame(Game.NAME);

		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLayout(new BorderLayout());

		game.frame.add(game, BorderLayout.CENTER);
		game.frame.pack();

		game.frame.setResizable(false);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.windowHandler = new WindowHandler(game);
		game.debug = DEBUG;

		game.start();
	}
}
