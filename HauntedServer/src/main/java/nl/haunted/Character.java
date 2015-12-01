package nl.haunted;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author Mike Evers
 */
public abstract class Character {

    private Point2D position;
    private double movementSpeed;
    private DirectionType direction;
    private boolean moving;
    protected Game game;

    public Point2D getPosition() {
        return this.position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     *
     * @return the character's movement speed
     */
    public Double getMovementSpeed() {
        return this.movementSpeed;
    }

    /**
     *
     * @return the direction in which the Character looks or is moving
     */
    public DirectionType getDirection() {
        return this.direction;
    }

    /**
     * @return the isMoving
     */
    public boolean getIsMoving() {
        return this.moving;
    }

    /**
     * Constructor for character Initialize position, movementSpeed, controller
     *
     * @param position
     * @param game
     */
    public Character(Point2D position, Game game) {
        this.direction = DirectionType.DOWN; // Set a default direction
        this.game = game;
        this.moving = false;
    }

    /**
     * Moves the character into the given direction. But checks if there is no
     * collision.
     *
     * @param direction
     */
    public void move(DirectionType direction) {
        Point2D proposedLocation = new Point2D.Double();
        Point2D oldPosition = position;

        switch (direction) {
            case UP:
                // <editor-fold defaultstate="collapsed" desc="UP">
                proposedLocation.setLocation(oldPosition.getX(), oldPosition.getY() - movementSpeed);
                if (detectCollision(proposedLocation)) {
                    if (this.moving) {
                        if (this instanceof Ghost) {
                            Ghost g = (Ghost) this;
                            g.setStationaryTime();
                        }
                    }
                    this.moving = false;
                    break;
                } else {
                    this.direction = direction;
                    if (!this.moving) {
                        if (this instanceof Ghost) {
                            Ghost g = (Ghost) this;
                            g.getStationaryTime().clear();
                        }
                    }
                    this.moving = true;
                    position.setLocation(oldPosition.getX(), oldPosition.getY() - movementSpeed);
                    //System.out.println("UP");
                    break;

                }
            // </editor-fold>
            case DOWN:
                // <editor-fold defaultstate="collapsed" desc="DOWN">
                proposedLocation.setLocation(oldPosition.getX(), (oldPosition.getY() + movementSpeed));
                if (detectCollision(proposedLocation)) {
                    if (this.moving) {
                        if (this instanceof Ghost) {
                            Ghost g = (Ghost) this;
                            g.setStationaryTime();
                        }
                    }
                    this.moving = false;
                    break;
                } else {
                    this.direction = direction;
                    if (!this.moving) {
                        if (this instanceof Ghost) {
                            Ghost g = (Ghost) this;
                            g.getStationaryTime().clear();
                        }
                    }
                    this.moving = true;
                    position.setLocation(oldPosition.getX(), (oldPosition.getY() + movementSpeed));
                    //System.out.println("DOWN");
                    break;
                }
            // </editor-fold>
            case RIGHT:
                // <editor-fold defaultstate="collapsed" desc="RIGHT">
                proposedLocation.setLocation(oldPosition.getX() + movementSpeed, oldPosition.getY());
                if (detectCollision(proposedLocation)) {
                    if (this.moving) {
                        if (this instanceof Ghost) {
                            Ghost g = (Ghost) this;
                            g.setStationaryTime();
                        }
                    }
                    this.moving = false;
                    break;
                } else {
                    this.direction = direction;
                    if (!this.moving) {
                        if (this instanceof Ghost) {
                            Ghost g = (Ghost) this;

                            g.getStationaryTime().clear();
                        }
                    }
                    this.moving = true;
                    position.setLocation(oldPosition.getX() + movementSpeed, oldPosition.getY());
                    //System.out.println("RIGHT");
                    break;
                }
            // </editor-fold>
            case LEFT:
                //        // <editor-fold defaultstate="collapsed" desc="LEFT">
                proposedLocation.setLocation((oldPosition.getX() - movementSpeed), oldPosition.getY());
                if (detectCollision(proposedLocation)) {
                    if (this.moving) {
                        if (this instanceof Ghost) {
                            Ghost g = (Ghost) this;
                            g.setStationaryTime();
                        }
                    }
                    this.moving = false;
                    break;
                } else {
                    this.direction = direction;
                    if (!this.moving) {
                        if (this instanceof Ghost) {
                            Ghost g = (Ghost) this;
                            g.getStationaryTime().clear();
                        }
                    }
                    this.moving = true;
                    position.setLocation((oldPosition.getX() - movementSpeed), oldPosition.getY());
                    //System.out.println("LEFT");
                    break;
                }
            // </editor-fold>
        }
    }

    /**
     * returns true if i is between or equal to min and max
     *
     * @param i
     * @param min
     * @param max
     * @return true if between or equal
     */
    public boolean betweenInclusive(int i, int min, int max) {
        return i >= min && i <= max;
    }

    /**
     * Check if character collides with another object.
     *
     * @param proposedLocation the location where the Character wants to move
     * to.
     * @return true if there is an obstacle
     */
    public boolean detectCollision(Point2D proposedLocation) {

        // First initialize the hitboxpoints.
        int pXl = (int) proposedLocation.getX() + 15;
        int pXr = pXl + 70;
        int pYt = (int) proposedLocation.getY() + 15;
        int pYb = pYt + 70;

        BufferedImage colImg = this.game.getLevel().getCollisionMap();
        if (0 <= pXl && 1000 >= pYb && 1500
                >= pXr && pYt >= 0) {
            if ((colImg.getRGB(pXl, pYt) + colImg.getRGB(pXr, pYt) + colImg.getRGB(pXr, pYb) + colImg.getRGB(pXl, pYb)) == -4) {
                return false;
            }
        } else {
            return true;
        }
        return true;
    }
}
