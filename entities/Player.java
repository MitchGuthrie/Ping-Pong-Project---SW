package entities;

import game.InputHandler;
import gfx.Colors;
import gfx.Screen;
import level.Level;

public class Player extends Mob {

	private InputHandler input;
	private int color = Colors.get(-1, 111, 145, 543);
	private int scale = 1;
	protected boolean isSwimming = false;

	public Player(Level level, int x, int y, int speed, InputHandler input) {
		super(level, "Player", x, y, 1);
		this.input = input;
	}

	public void tick() {
		int xa = 0;
		int ya = 0;

		if (input.up.isPressed()) {
			ya -= 1;
		}
		if (input.down.isPressed()) {
			ya += 1;
		}
		if (input.left.isPressed()) {
			xa -= 1;
		}
		if (input.right.isPressed()) {
			xa += 1;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;
		} else {
			isMoving = true;
		}

		// check if swimming
		if (level.getTile(this.x >> 3, this.y >> 3).getId() == 3) {
			isSwimming = true;
		}
		if (isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 3) {
			isSwimming = false;
		}
		// Change value to change scale of player
		this.scale = 1;
	}

	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 28;
		// sets movment speed
		int walkingSpeed = 2;

		// divides by 2^4 ANDs its by 4
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		// 1 = north, 2 = south, 3 = east, 4 = west
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			// gives num between 0 and 1, if 0 then 0, if 1 then 2, for player direction
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
		}

		// scale
		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;

		// render each pixel of player
		// upper body
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, color, flipTop, scale);
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, color, flipTop,
				scale);

		// lower body
		if (!isSwimming) {
			screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, color,
					flipBottom, scale);
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier,
					(xTile + 1) + (yTile + 1) * 32, color, flipBottom, scale);
		}

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
