package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 *
 * @author Mike Evers
 */

public abstract class Character {
    
    protected Game game; 
    private Point2D position;
    private Color color;
    private String sprite;
    private Double movementSpeed = 0.0; //TODO the movementSpeed isn't set to the correct value yet
    private DirectionType direction;

    /**
     *
     * @return character position on the map (Point2D object).
     */
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * Sets the character position on the map (Point2D object).
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
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the character color (Ghosts are completely colored, humans only wear
     * a colored hat)
     *
     * @param color
     */
    public void setColor(Color color) {
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
     * 
     * @return the direction in which the Character looks or is moving
     */
    public DirectionType getDirection(){
        return this.direction;
    }
    
    /** 
     * Set the direction in which the Character looks or is moving
     * @param direction 
     */
    public void setDirection(DirectionType direction){
        this.direction = direction;
    }
    
    public Character(Point2D position, Color color, String sprite, Game game){
        this.position = position;
        this.color = color;
        this.sprite = sprite;
        this.direction = DirectionType.DOWN; // Set a default direction
        this.game = game;
    }
    
    /**
     * Makes the character move in the pointed direction, be aware of collision!
     *
     * @param direction
     */
    public void move(String direction) {
        // TODO - implement Character.move
        throw new UnsupportedOperationException();
    }
    
    /**
     * Check if character collides with another object. 
     * Be aware of the private modifier!
     */
    private void detectCollision(){
        // TODO - implement Character.detectCollision
        throw new UnsupportedOperationException();
    }
}
