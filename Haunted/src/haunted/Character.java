package haunted;

import java.awt.geom.Point2D;

/**
 *
 * @author Mal
 */
public abstract class Character {

    private Point2D position;
    private String color;
    private String sprite;
    private Double movementSpeed;

    /**
     *
     * @return character position on the map
     */
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * Sets the character position on the map
     *
     * @param position
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     *
     * @return the color of the character (Ghosts are completely colored, humans
     * only wear a colored hat).
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Sets the character color (Ghosts are completely colored, humans only wear
     * a colored hat)
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return the character's sprite (human/ghost/wall)
     */
    public String getSprite() {
        return this.sprite;
    }

    /**
     * Sets the character's sprite (human/ghost/wall)
     *
     * @param sprite
     */
    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    /**
     *
     * @return the character's movement speed
     */
    public Double getMovementSpeed() {
        return this.movementSpeed;
    }

    /**
     * Sets the character's movement speed
     *
     * @param movementSpeed
     */
    public void setMovementSpeed(Double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /**
     * Makes the character move in the pointed direction Be aware of collision!
     *
     * @param direction
     */
    public void move(String direction) {
        // TODO - implement Character.move
        throw new UnsupportedOperationException();
    }

    public Character(String color) {
        //TODO - implement Character.Character
        throw new UnsupportedOperationException();
    }
}
