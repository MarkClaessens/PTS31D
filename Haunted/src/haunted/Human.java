package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;



/**
 *
 * @author Mike Evers
 */
public class Human extends Character {

    private int flashlightRange = 100; // Mike: setted default range to 100
    private int flashlightAngle = 45; // Mike: setted default angle to 45
    private boolean hasKey;

    /**
     *
     * @return the flashlight range
     */
    public int getFlashlightRange() {
        return this.flashlightRange;
    }

    /**
     * Sets the flashlight range
     *
     * @param flashlightRange
     */
    public void setFlashlightRange(int flashlightRange) {
        this.flashlightRange = flashlightRange;
    }

    /**
     *
     * @return the flashlight angle
     */
    public int getFlashlightAngle() {
        return this.flashlightAngle;
    }

    /**
     * Sets the flashlight angle
     *
     * @param flashlightAngle
     */
    public void setFlashlightAngle(int flashlightAngle) {
        this.flashlightAngle = flashlightAngle;
    }

    /**
     *
     * @return true if the human has the key
     */
    public boolean getHasKey() {
        return this.hasKey;
    }

    /**
     * If human has the key, hasKey is true. Otherwise hasKey is false
     *
     * @param hasKey
     */
    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
    
    /**
     * Constructor of Human sets haskey to false and set the super variables in Character
     *
     * @param position, the Point2D position of the Ghost on the map
     * @param color, color of the Ghost
     * @param sprite, sprite of the Ghost
     */
    public Human(Point2D position, Color color, String sprite) {
        super(position, color, sprite);
        this.hasKey = false;
    }

    /**
     * The controls of the current ghost as human goes to the new ghost as human
     */
    public void transferHuman() {
        // TODO - implement Human.transferHuman
        throw new UnsupportedOperationException();
    }

    /**
     * Let the human pick up a key, only if the human touches the key
     */
    public void pickUpKey() {
        // TODO - implement Human.pickUpKey
        throw new UnsupportedOperationException();
    }

    /**
     * Let the human get into the door, but only if the human has a key and
     * touches the door.
     */
    public void enterDoor() {
        // TODO - implement Human.enterDoor
        throw new UnsupportedOperationException();
    }
}
