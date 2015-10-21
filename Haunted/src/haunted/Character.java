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
    private String[] sprites;
    private Double movementSpeed = 25.0; // Mike: Don't know what the right value is for this is?
    private DirectionType direction;
    private boolean isMoving;

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
     * @return the character's sprites (human/ghost/wall)
     */
    public String[] getSprites() {
        return this.sprites;
    }

    /**
     * Sets the character's sprites (human/ghost/wall)
     *
     * @param sprite
     */
    public void setSprites(String[] sprites) {
        this.sprites = sprites;
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
    
    public Character(Point2D position, Color color, String[] sprites, Game game){
        this.position = position;
        this.color = color;
        this.sprites = sprites;
        this.direction = DirectionType.DOWN; // Set a default direction
        this.game = game;
        this.isMoving = false;
    }
    
    /**
     * Makes the character move in the pointed direction, be aware of collision!
     *
     * @param direction
     */
    public void move(DirectionType direction) {
        if(detectCollision()){
            return;
        }
        else{
            this.direction = direction;
            this.setIsMoving(true);
            Point2D oldPosition = position;
            
            switch(direction){
                case UP:
                    position.setLocation(oldPosition.getX(), oldPosition.getY() + movementSpeed );
                    break;
                case DOWN:
                    position.setLocation(oldPosition.getX(), oldPosition.getY() - movementSpeed );
                    break;
                case RIGHT:
                    position.setLocation(oldPosition.getX() + movementSpeed, oldPosition.getY());
                    break;
                case LEFT:
                    position.setLocation(oldPosition.getX() - movementSpeed, oldPosition.getY());
                    break;
            }
        }
    }
    
    /**
     * Check if character collides with another object. 
     * Be aware of the private modifier!
     * @return if there was collision or not
     */
    private boolean detectCollision(){
        // TODO - implement Character.detectCollision
        throw new UnsupportedOperationException();
    }

    /**
     * @return the isMoving
     */
    public boolean isIsMoving() {
        return isMoving;
    }

    /**
     * @param isMoving the isMoving to set
     */
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
}
