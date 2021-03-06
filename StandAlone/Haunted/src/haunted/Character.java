package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
//import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mike Evers
 */
public abstract class Character {

    /**
     * the game where the character is in
     */
    protected Game game;

    private Point2D position;
    private Color color;
    private String[] spritesUp;
    private String[] spritesDown;
    private String[] spritesLeft;
    private String[] spritesRight;
    private Double movementSpeed = 4.0;
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
     * @return the character's spritesUp (human/ghost/wall)
     */
    public String[] getSpritesUp() {
        return spritesUp;
    }

    /**
     * Sets the character's spritesUp (human/ghost/wall)
     *
     * @param spritesUp
     */
    public void setSpritesUp(String[] spritesUp) {
        this.spritesUp = spritesUp;
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
    public DirectionType getDirection() {
        return this.direction;
    }

    /**
     * Set the direction in which the Character looks or is moving
     *
     * @param direction
     */
    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }

    /**
     * the constructor for character
     *
     * @param position
     * @param color
     * @param spritesUp
     * @param spritesDown
     * @param spritesLeft
     * @param spritesRight
     * @param game
     */
    public Character(Point2D position, Color color, String[] spritesUp, String[] spritesDown, String[] spritesLeft, String[] spritesRight, Game game) {
        this.position = position;
        this.color = color;
        this.spritesUp = spritesUp;
        this.spritesDown = spritesDown;
        this.spritesLeft = spritesLeft;
        this.spritesRight = spritesRight;
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
        Point2D proposedLocation = new Point2D.Double(); // set to position just for init. 
        Point2D oldPosition = position;

        switch (direction) {
            case UP:
                // <editor-fold defaultstate="collapsed" desc="UP">
                proposedLocation.setLocation(oldPosition.getX(), oldPosition.getY() - movementSpeed);
                if (detectCollision(proposedLocation)) {                   
                    if(this.isMoving){
                        if(this instanceof Ghost){
                        Ghost g = (Ghost)this;
                        g.setStationaryTime();
                        }
                    }      
                    this.setIsMoving(false);
                    break;
                } else {
                    this.direction = direction;
                    if(!this.isMoving){
                        if(this instanceof Ghost){
                            Ghost g = (Ghost)this;
                            g.getStationaryTime().clear();
                        }                       
                    }
                    this.setIsMoving(true);                                
                    position.setLocation(oldPosition.getX(), oldPosition.getY() - movementSpeed);                 
                    //System.out.println("UP");
                    break;

                }
            // </editor-fold>
            case DOWN:
                // <editor-fold defaultstate="collapsed" desc="DOWN">
                proposedLocation.setLocation(oldPosition.getX(), (oldPosition.getY() + movementSpeed));
                if (detectCollision(proposedLocation)) { 
                    if(this.isMoving){
                        if(this instanceof Ghost){
                        Ghost g = (Ghost)this;
                        g.setStationaryTime();
                        }
                    }      
                    this.setIsMoving(false);
                    break;
                } else {
                    this.direction = direction;
                    if(!this.isMoving){
                        if(this instanceof Ghost){
                            Ghost g = (Ghost)this;
                            g.getStationaryTime().clear();
                        }
                    }
                    this.setIsMoving(true);
                    position.setLocation(oldPosition.getX(), (oldPosition.getY() + movementSpeed));
                    //System.out.println("DOWN");
                    break;
                }
            // </editor-fold>
            case RIGHT:
                // <editor-fold defaultstate="collapsed" desc="RIGHT">
                proposedLocation.setLocation(oldPosition.getX() + movementSpeed, oldPosition.getY());
                if (detectCollision(proposedLocation)) {
                    if(this.isMoving){
                        if(this instanceof Ghost){
                        Ghost g = (Ghost)this;
                        g.setStationaryTime();
                        }
                    }      
                    this.setIsMoving(false);
                    break;
                } else {
                    this.direction = direction;
                    if(!this.isMoving){
                        if(this instanceof Ghost){
                            Ghost g = (Ghost)this;

                            g.getStationaryTime().clear();
                        }
                    }
                    this.setIsMoving(true);
                    position.setLocation(oldPosition.getX() + movementSpeed, oldPosition.getY());
                    //System.out.println("RIGHT");
                    break;
                }
            // </editor-fold>
            case LEFT:
                //        // <editor-fold defaultstate="collapsed" desc="LEFT">
                proposedLocation.setLocation((oldPosition.getX() - movementSpeed), oldPosition.getY());
                if (detectCollision(proposedLocation)) {
                    if(this.isMoving){
                        if(this instanceof Ghost){
                        Ghost g = (Ghost)this;
                        g.setStationaryTime();
                        }
                    }      
                    this.setIsMoving(false);
                    break;
                } else {
                    this.direction = direction;                   
                    if(!this.isMoving){
                        if(this instanceof Ghost){
                            Ghost g = (Ghost)this;
                            g.getStationaryTime().clear();
                        }
                    }
                    this.setIsMoving(true);
                    position.setLocation((oldPosition.getX() - movementSpeed), oldPosition.getY());
                    //System.out.println("LEFT");
                    break;
                }
            // </editor-fold>
        }
    }

    /**
     * Check if character collides with another object. Be aware of the private
     * modifier!
     *
     * @param proposedLocation the location where the Character wants to move
     * to.
     * @return true if there is an obstacle
     */
    public boolean detectCollision(Point2D proposedLocation) {
        int pXl = (int) proposedLocation.getX() + 15;
        int pXr = pXl + 70;
        int pYt = (int) proposedLocation.getY() + 15;
        int pYb = pYt + 70;

        BufferedImage colImg = this.game.getCurrentLevel().getCollisionImage();
        if (0 <= pXl && 1000 >= pYb && 1500
                >= pXr && pYt >= 0) {
            if ((colImg.getRGB(pXl, pYt) + colImg.getRGB(pXr, pYt) + colImg.getRGB(pXr, pYb) + colImg.getRGB(pXl, pYb)) == -4) {
                return false;
            }
        } else {
            return true;}
        return true;
    }

    /**
     * @return the isMoving
     */
    public boolean getIsMoving() {
        return isMoving;
    }

    /**
     * @param isMoving the isMoving to set
     */
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    /**
     * @return the spritesDown
     */
    public String[] getSpritesDown() {
        return spritesDown;
    }

    /**
     * @param spritesDown the spritesDown to set
     */
    public void setSpritesDown(String[] spritesDown) {
        this.spritesDown = spritesDown;
    }

    /**
     * @return the spritesLeft
     */
    public String[] getSpritesLeft() {
        return spritesLeft;
    }

    /**
     * @param spritesLeft the spritesLeft to set
     */
    public void setSpritesLeft(String[] spritesLeft) {
        this.spritesLeft = spritesLeft;
    }

    /**
     * @return the spritesRight
     */
    public String[] getSpritesRight() {
        return spritesRight;
    }

    /**
     * @param spritesRight the spritesRight to set
     */
    public void setSpritesRight(String[] spritesRight) {
        this.spritesRight = spritesRight;
    }

    /**
     * returns true if i is between the min and max value including the min and
     * max value
     *
     * @param i
     * @param min
     * @param max
     * @return
     */
    public boolean betweenInclusive(int i, int min, int max) {
        return i >= min && i <= max;
    }

    /**
     *
     * @return the hitbox points of character
     */
    public List<Point2D> getHitboxPoints() {
        return null;
    }

    /**
     * check if hitbox collides with the points
     *
     * @param point1
     * @param width1
     * @param height1
     * @param point2
     * @param width2
     * @param height2
     * @return
     */
    public boolean checkHitboxCollision(Point2D point1, int width1, int height1, Point2D point2, int width2, int height2) {
        //convert point1 in leftmost and rightmost X value and top and bottom Y value;
        int p1Xmin = (int) point1.getX();
        int p1Xmax = (int) p1Xmin + width1 - 1;
        int p1Ymin = (int) point1.getY();
        int p1Ymax = (int) p1Ymin + height1 - 1;

        //convert point2 in leftmost and rightmost X value and top and bottom Y value;
        int p2Xmin = (int) point2.getX();
        int p2Xmax = (int) p2Xmin + width2 - 1;
        int p2Ymin = (int) point2.getY();
        int p2Ymax = (int) p2Ymin + height2 - 1;

        return (betweenInclusive(p1Xmax, p2Xmin, p2Xmax) && (betweenInclusive(p1Ymin, p2Ymin, p2Ymax) || betweenInclusive(p1Ymax, p2Ymin, p2Ymax))) || (betweenInclusive(p1Xmin, p2Xmin, p2Xmax) && (betweenInclusive(p1Ymin, p2Ymin, p2Ymax) || betweenInclusive(p1Ymax, p2Ymin, p2Ymax)));
    }
}
