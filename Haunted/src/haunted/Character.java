package haunted;

import java.awt.geom.Point2D;

public abstract class Character {

	private Point2D position;
	private String color;
	private String sprite;
	private Double movementSpeed;

        /**
         * Character position on the map
         * @return position
         */
	public Point2D getPosition() {
		return this.position;
	}

        /**
         * Sets the character position on the map
         * @param position 
         */
	public void setPosition(Point2D position) {
		this.position = position;
	}

        /**
         * Returns the color of the character (Ghosts are completely colored, humans only wear a colored hat).
         * @return color
         */
	public String getColor() {
		return this.color;
	}

        /**
         * Sets the character color (Ghosts are completely colored, humans only wear a colored hat)
         * @param color 
         */
	public void setColor(String color) {
		this.color = color;
	}

        /**
         * Returns the character's sprite (human/ghost/wall)
         * @return sprite
         */
	public String getSprite() {
		return this.sprite;
	}

        /**
         * Sets the character's sprite (human/ghost/wall)
         * @param sprite 
         */
	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

        /**
         * Returns the character's movement speed
         * @return 
         */
	public Double getMovementSpeed() {
		return this.movementSpeed;
	}

        /**
         * Sets the character's movement speed
         * @param movementSpeed 
         */
	public void setMovementSpeed(Double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	/**
	 * Makes the character move in the pointed direction
	 * @param direction
	 */
	public void move(String direction) {
		// TODO - implement Character.move
		throw new UnsupportedOperationException();
	}

}