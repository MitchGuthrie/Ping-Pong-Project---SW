package entities;

import game.Game;
import gfx.Colors;
import gfx.Screen;
import level.Level;
import networking.packets.Packet02Move;

public class Ball extends Mob {

	private int color = Colors.get(-1, 111, 145, 555);
	private int scale = 1;
	private boolean moving = true;

	public Ball(Level level, String name, int x, int y, int speed, boolean isMoving) {
		super(level, name, x, y, speed);
		this.isMoving = true;
	}

	public void move(int xa, int ya) {

		// prevents moving diagonally
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(ya, 0);
			numSteps--;
			return;
		}

		// takes step
		numSteps++;

		if (!hasCollided(xa, ya)) {
			if (ya < 0)
				movingDir = 0; // north
			if (ya > 0)
				movingDir = 1; // south
			if (xa < 0)
				movingDir = 2; // east
			if (xa > 0)
				movingDir = 3; // west

			x += xa * speed;
			y += ya * speed;
		}

	}

	@Override
	public void tick() {
		int xa = 0;
		int ya = 0;

		// ball moves down
		ya += 1;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;
			if (!Game.game.isApplet) {
				Packet02Move packet = new Packet02Move("ball", this.x, this.y, this.numSteps, this.isMoving,
						this.movingDir);
				packet.writeData(Game.game.socketClient);
			}
		} else {
			isMoving = true;
		}

		this.scale = 1;
	}

	@Override
	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 27;

		// sets movment speed
		int walkingSpeed = 3;

		// divides by 2^4 ANDs its by 4
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		// scale
		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;

		// render each pixel of player
		// upper body
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, color, flipTop, scale);
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, color, flipTop,
				scale);

		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, color,
				flipBottom, scale);
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32,
				color, flipBottom, scale);

	}

	public boolean hasCollided(int xa, int ya) {
		// Set collision bounds on player
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;

		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin))
				return true;
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax))
				return true;
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y))
				return true;
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y))
				return true;
		}

		return false;
	}

}
