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

        /**
         * 
         * @return the floor number
         */
	public int getFloorNr() {
		return this.floorNr;
	}

        /**
         * Sets the floor number
         * Floor number is not null, 0 or a negative number.
         * @param floorNr 
         */
	public void setFloorNr(int floorNr) {
		this.floorNr = floorNr;
	}

        /**
         * 
         * @return the number of ghost lives
         */
	public int getGhostLifePool() {
		return this.ghostLifePool;
	}

        /**
         * Sets the number of ghost lives
         * Ghost live is not null or a negative number. 
         * @param ghostLifePool 
         */
	public void setGhostLifePool(int ghostLifePool) {
		this.ghostLifePool = ghostLifePool;
	}

        /**
         * 
         * @return the shape of the level in a string
         */
	public String getShape() {
		return this.shape;
	}

        /**
         * Sets the shape of the level
         * @param shape 
         */
	public void setShape(String shape) {
		this.shape = shape;
	}

        /**
         * 
         * @return the level's width (x-axis)
         */
	public int getWidth() {
		return this.width;
	}

        /**
         * Sets the level's width (x-axis)
         * @param width 
         */
	public void setWidth(int width) {
		this.width = width;
	}

        /**
         * 
         * @return the level's height (y-axis)
         */
	public int getHeight() {
		return this.height;
	}

        /**
         * Sets the level's height (y-axis)
         * @param height 
         */
	public void setHeight(int height) {
		this.height = height;
	}

        /**
         * 
         * @return the key's location on the map
         */
	public Point2D getKeyLocation() {
		return this.keyLocation;
	}

        /**
         * Sets the key's location on the map, this location is generated randomly
         * @param keyLocation 
         */
	public void setKeyLocation(Point2D keyLocation) {
		this.keyLocation = keyLocation;
	}

        /**
         * 
         * @return the door's location on the map
         */
	public Point2D getDoorLocation() {
		return this.doorLocation;
	}

        /**
         * Sets the door's location on the map, this location is generated randomly on one of the sides
         * @param doorLocation 
         */
	public void setDoorLocation(Point2D doorLocation) {
		this.doorLocation = doorLocation;
	}

        /**
         * 
         * @return the level's current theme
         */
	public String getTheme() {
		return this.theme;
	}

        /**
         * Sets the level's current theme, this determines which character sprites are used
         * @param theme 
         */
	public void setTheme(String theme) {
		this.theme = theme;
	}

        /**
         * Generates the location of the key on the map, this location is random.
         * This is done after the door location is generated.
         */
	public void generateKeyLocation() {
		// TODO - implement Level.generateKeyLocation
		throw new UnsupportedOperationException();
	}

        /**
         * Generates the location of the door on the map, this location is random.
         */
	public void generateDoorLocation() {
		// TODO - implement Level.generateDoorLocation
		throw new UnsupportedOperationException();
	}

        /**
         * Generate the level's layout. It happens in this order: obstacles, door, key, characters.
         */
	public void generateLayout() {
		// TODO - implement Level.generateLayout
		throw new UnsupportedOperationException();
	}

        /**
         * Places obstacles on the map, this happens when generating the level's layout.
         */
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