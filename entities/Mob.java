package entities;

import level.Level;
import level.tiles.Tile;

public abstract class Mob extends Entity {

	protected String name;
	protected int speed;
	protected int numSteps = 0;
	protected boolean isMoving;

	// tells which direction it's moving
	protected int movingDir = 3;

	protected int scale = 1;

	public Mob(Level level, String name, int x, int y, int speed) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
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

	public abstract boolean hasCollided(int xa, int ya);

	protected boolean isSolidTile(int xa, int ya, int x, int y) {
		if (level == null)
			return false;

		Tile lastTile = level.getTile((this.x + x) >> 3, (this.y + y) >> 3);
		Tile newTile = level.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);

		if (lastTile.equals(newTile) && newTile.isSolid())
			return true;

		return false;
	}

	public String getName() {
		return name;
	}

	public int getNumSteps() {
		return numSteps;
	}

	public void setNumSteps(int numSteps) {
		this.numSteps = numSteps;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public int getMovingDir() {
		return movingDir;
	}

	public void setMovingDir(int movingDir) {
		this.movingDir = movingDir;
	}
}
