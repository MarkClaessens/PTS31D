package haunted;

import java.awt.geom.Point2D;

public class Obstacle {

    private Point2D position;
    private String sprite;

    public Point2D getPosition() {
        return this.position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public String getSprite() {
        return this.sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

}
