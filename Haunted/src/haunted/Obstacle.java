package haunted;

import java.awt.geom.Point2D;

/**
 *
 * @author Mal
 */
public class Obstacle {

    private Point2D position;
    private String sprite;

    /**
     *
     * @return the obstacle's position on the map.
     */
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * Sets the obstacle's position on the map.
     *
     * @param position
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     *
     * @return the sprite of the obstacle
     */
    public String getSprite() {
        return this.sprite;
    }

    /**
     * Sets the sprite of the obstacle which must match the theme of the current
     * level.
     *
     * @param sprite
     */
    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

}
