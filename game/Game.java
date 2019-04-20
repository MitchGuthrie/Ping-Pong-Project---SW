package game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import entities.Ball;
import entities.Player;
import entities.PlayerMP;
import gfx.Colors;
import gfx.Font;
import gfx.Screen;
import gfx.SpriteSheet;
import level.Level;
import networking.GameClient;
import networking.GameServer;
import networking.packets.Packet00Login;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 260;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 4;
	public static final String NAME = "Game";
	public static Game game;
	public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	public JFrame frame;

	private Thread thread;

	public boolean running = false;
	public int tickCount = 0;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[6 * 6 * 6];

	public Screen screen;
	public InputHandler input;
	public Level level;
	public WindowHandler windowHandler;

	public Player player;

	public Ball ball;

	public GameClient socketClient;
	public GameServer socketServer;

	public boolean debug = true;
	public boolean isApplet = false;

	// Initializes colors/graphics and players
	public void init() {
		game = this;

		// initializes main color logic
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);

					colours[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}

		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/res/sprite_sheet.png"));
		input = new InputHandler(this);

		level = new Level("/res/Levels/water_test_level.png");

		// creates player, set pos on screen
		player = new PlayerMP(level, 197, 53, 1, input, JOptionPane.showInputDialog(this, "Please enter a nickname"),
				null, -1);
		// adds to level
		level.addEntity(player);

		// creates ball, sets pos on screen
		ball = new Ball(level, "ball", 197, 100, 1, true);

		// adds ball to level
		level.addEntity(ball);

		if (!isApplet) {
			Packet00Login loginPacket = new Packet00Login(player.getUsername(), player.x, player.y);
			if (socketServer != null) {
				socketServer.addConnection((PlayerMP) player, loginPacket);
			}
			// socketClient.sendData("Ping".getBytes());
			loginPacket.writeData(socketClient);
		}
	}

	public synchronized void start() {
		running = true;

		thread = new Thread(this, NAME + "_main");
		thread.start();

		if (JOptionPane.showConfirmDialog(this, "Host game ('No' for solo play)?") == 0) {
			socketServer = new GameServer(this);
			socketServer.start();
		}

		socketClient = new GameClient(this, "192.168.0.26");
		socketClient.start();
	}

	public synchronized void stop() {
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Handles game speed and performance
	public void run() {
		// Time variables
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		double nsPerTick = 1000000000D / 60D;
		double delta = 0;
		int ticks = 0;
		int frames = 0;

		// Initializes colors/graphics and players
		init();

		// Keeps game running at constant speed, even if performance is low
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				debug(DebugLevel.INFO, ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}
		}
	}

	// Updates game logic
	public void tick() {
		tickCount++;
		level.tick();
	}

	// Renders graphics to screen
	public void render() {

		// Triple buffering to reduce tearing
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		int xOffset = player.x - (screen.width / 2);
		int yOffset = player.y - (screen.height / 2);

		// Rendering background tiles
		level.renderTiles(screen, xOffset, yOffset);

		// Rendering fonts
		for (int x = 0; x < level.width; x++) {
			int colour = Colors.get(-1, -1, -1, 000);
			Font.render("This is text", screen, 30, 100, colour, 1);
		}

		// Rendering entities
		level.renderEntities(screen);

		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int ColourCode = screen.pixels[x + y * screen.width];
				if (ColourCode < 255) {
					pixels[x + y * WIDTH] = colours[ColourCode];

				}
			}
		}

		// Graphics handling
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static long fact(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return n * fact(n - 1);
		}
	}

	// set different information levels [for debugging purposes]
	public void debug(DebugLevel level, String msg) {
		switch (level) {
		default:
		case INFO:
			if (debug) {
				System.out.println("[" + NAME + "] " + msg);
			}
			break;
		case WARNING:
			System.out.println("[" + NAME + "] [WARNING] " + msg);
			break;
		case SEVERE:
			System.out.println("[" + NAME + "] [SEVERE]" + msg);
			this.stop();
			break;
		}
	}

	public static enum DebugLevel {
		INFO, WARNING, SEVERE;
	}

}
