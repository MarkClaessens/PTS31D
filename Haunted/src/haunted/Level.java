package haunted;

import java.awt.geom.Point2D;

public class Level {

	private int floorNr;
	private int ghostLifePool;
	private String shape;
	private int width;
	private int height;
	private Point2D keyLocation;
	private Point2D doorLocation;
	private String theme;

	public int getFloorNr() {
		return this.floorNr;
	}

	public void setFloorNr(int floorNr) {
		this.floorNr = floorNr;
	}

	public int getGhostLifePool() {
		return this.ghostLifePool;
	}

	public void setGhostLifePool(int ghostLifePool) {
		this.ghostLifePool = ghostLifePool;
	}

	public String getShape() {
		return this.shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Point2D getKeyLocation() {
		return this.keyLocation;
	}

	public void setKeyLocation(Point2D keyLocation) {
		this.keyLocation = keyLocation;
	}

	public Point2D getDoorLocation() {
		return this.doorLocation;
	}

	public void setDoorLocation(Point2D doorLocation) {
		this.doorLocation = doorLocation;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void generateKeyLocation() {
		// TODO - implement Level.generateKeyLocation
		throw new UnsupportedOperationException();
	}

	public void generateDoorLocation() {
		// TODO - implement Level.generateDoorLocation
		throw new UnsupportedOperationException();
	}

	public void generateLayout() {
		// TODO - implement Level.generateLayout
		throw new UnsupportedOperationException();
	}

	public void placeObstacles() {
		// TODO - implement Level.placeObstacles
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param floorNr
	 */
	public Level(int floorNr) {
		// TODO - implement Level.Level
		throw new UnsupportedOperationException();
	}

}