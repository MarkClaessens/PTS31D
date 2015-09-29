package haunted;

import java.awt.geom.Point2D;

public class Human extends Character {

    private int flashlightRange;
    private int flashlightAngle;
    private boolean hasKey;
    private Double rotationSpeed;

    public int getFlashlightRange() {
        return this.flashlightRange;
    }

    public void setFlashlightRange(int flashlightRange) {
        this.flashlightRange = flashlightRange;
    }

    public int getFlashlightAngle() {
        return this.flashlightAngle;
    }

    public void setFlashlightAngle(int flashlightAngle) {
        this.flashlightAngle = flashlightAngle;
    }

    public boolean isHasKey() {
        return this.hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public Double getRotationSpeed() {
        return this.rotationSpeed;
    }

    public void setRotationSpeed(Double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    /**
     *
     * @param mousePosition
     */
    public void rotateFlashlight(Point2D mousePosition) {
        // TODO - implement Human.rotateFlashlight
        throw new UnsupportedOperationException();
    }

    public void transferHuman() {
        // TODO - implement Human.transferHuman
        throw new UnsupportedOperationException();
    }

    public void pickUpKey() {
        // TODO - implement Human.pickUpKey
        throw new UnsupportedOperationException();
    }

    public void enterDoor() {
        // TODO - implement Human.enterDoor
        throw new UnsupportedOperationException();
    }

    public Human() {
        // TODO - implement Human.Human
        throw new UnsupportedOperationException();
    }

}
