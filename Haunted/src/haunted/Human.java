package haunted;

import java.awt.geom.Point2D;

public class Human extends Character {

    private int flashlightRange;
    private int flashlightAngle;
    private boolean hasKey;
    private Double rotationSpeed;

    /**
     * Returns the flashlight range
     *
     * @return flashlightRange
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
     * Returns the flashlight angle
     *
     * @return flashlightAngle
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
     * Returns true if the human has the key
     *
     * @return hasKey
     */
    public boolean isHasKey() {
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
     * Returns the rotation speed of the flashlight
     *
     * @return rotationSpeed
     */
    public Double getRotationSpeed() {
        return this.rotationSpeed;
    }

    /**
     * Sets the rotation speed of the flashlight
     *
     * @param rotationSpeed
     */
    public void setRotationSpeed(Double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    /**
     * Rotates the flashlight depending on the mouse position
     *
     * @param mousePosition
     */
    public void rotateFlashlight(Point2D mousePosition) {
        // TODO - implement Human.rotateFlashlight
        throw new UnsupportedOperationException();
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

    /**
     * Constructor of Human
     */
    public Human() {
        // TODO - implement Human.Human
        throw new UnsupportedOperationException();
    }

}
