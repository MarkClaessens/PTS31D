package haunted;

import java.awt.geom.Point2D;

public abstract class Character {

	private Point2D position;
	private String color;
	private String sprite;
	private Double movementSpeed;

	public Point2D getPosition() {
		return this.position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSprite() {
		return this.sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public Double getMovementSpeed() {
		return this.movementSpeed;
	}

	public void setMovementSpeed(Double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	/**
	 * 
	 * @param direction
	 */
	public void move(String direction) {
		// TODO - implement Character.move
		throw new UnsupportedOperationException();
	}

}