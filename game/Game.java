package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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

	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Game";

	public JFrame frame;

	public boolean running = false;
	public int tickCount = 0;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[6 * 6 * 6];

	private Screen screen;
	public InputHandler input;
	public Level level;
	public WindowHandler windowHandler;

	public Player player;

	public GameClient socketClient;
	public GameServer socketServer;

	// Sets up game frame/window
	public Game() {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// Initializes colors/graphics and players
	public void init() {
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
		windowHandler = new WindowHandler(this);
		level = new Level("/res/Levels/small_test_level.png");
		level = new Level("/res/Levels/water_test_level.png");

		// creates player, set pos on screen
		player = new PlayerMP(level, 100, 110, 1, input, JOptionPane.showInputDialog(this, "Please enter a username"),
				null, -1);
		// adds to level
		level.addEntity(player);

		Packet00Login loginPacket = new Packet00Login(player.getUsername());

		if (socketServer != null) {
			socketServer.addConnection((PlayerMP) player, loginPacket);
		}

		// socketClient.sendData("Ping".getBytes());
		loginPacket.writeData(socketClient);
	}

	public synchronized void start() {
		running = true;
		new Thread(this).start();

		if (JOptionPane.showConfirmDialog(this, "Run server?") == 0) {
			socketServer = new GameServer(this);
			socketServer.start();
		}

		socketClient = new GameClient(this, "localhost");
		socketClient.start();
	}

	public synchronized void stop() {
		running = false;
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
				frame.setTitle(ticks + " ticks , " + frames + " frames per second");
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

	// Main function
	public static void main(String[] args) {
		new Game().start();
	}

}
