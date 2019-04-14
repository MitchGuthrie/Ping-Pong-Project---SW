package entities;

import java.net.InetAddress;

import game.InputHandler;
import level.Level;

public class PlayerMP extends Player {

	public InetAddress ipAddress;
	public int port;

	public PlayerMP(Level level, int x, int y, int speed, InputHandler input, String username, InetAddress ipAddress,
			int port) {
		super(level, x, y, speed, input, username);
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public PlayerMP(Level level, int x, int y, int speed, String username, InetAddress ipAddress, int port) {
		super(level, x, y, speed, null, username);
		this.ipAddress = ipAddress;
		this.port = port;
	}

	// lets us know connection still exists
	@Override
	public void tick() {
		super.tick();
	}

}
