package haunted;

import java.awt.geom.Point2D;

/**
 *
 * @author Mal
 */
public class Obstacle {

    private Point2D position;
    private String sprite;
    private ObstacleType obstacleType;

    public Obstacle(ObstacleType obstacleType, String sprite, Point2D position) {
        this.obstacleType = obstacleType;
        this.sprite = sprite;
        this.position = position;
    }
    
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
    
    /**
     * 
     * @return the behaviour of the obstacle 
     */
    public ObstacleType getObstacleType() {
        return this.obstacleType;
    }
    
    /**
     * Sets the behaviour of the obstacle, must be an ObstacleType (door,key,wall)
     * @param obstacleType 
     */
    public void setObstacleType(ObstacleType obstacleType) {
        this.obstacleType = obstacleType;
    }

    /**
     * Is called when a character collides with an obstacle. Further actions are determined by obstacleType.
     * For example: if obstacleType = wall > character can not move any further.
     */
    public void interact() {
        if(obstacleType == ObstacleType.KEY) {
            //Implementation needed
        }
        
        if(obstacleType == ObstacleType.DOOR) {
            //Implementation needed
        }
        
        if(obstacleType == ObstacleType.WALL) {
            //Implementation needed
        }
    }
}
