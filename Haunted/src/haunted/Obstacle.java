package haunted;

import java.awt.geom.Point2D;

/**
 *
 * @author Mal
 */
public class Obstacle {

    private Point2D position;
    private String sprite;
    private ObstacleType behaviour;
    private Level level;

    public Obstacle(ObstacleType obstacleType, String sprite, Point2D position, Level level) {
        this.behaviour = obstacleType;
        this.sprite = sprite;
        this.position = position;
        this.level = level;
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
    public ObstacleType getBehaviour() {
        return this.behaviour;
    }
    
    /**
     * Sets the behaviour of the obstacle, must be an ObstacleType (door,key,wall)
     * @param obstacleType 
     */
    public void setBehaviour(ObstacleType behaviour) {
        this.behaviour = behaviour;
    }

    /**
     * Is called when the human collides with an obstacle. Further actions are determined by obstacleType.
     * @param human The human object that collides with the obstacle.
     * 
     */
    public void interact(Human human) {
        switch(this.behaviour){
            case KEY: 
                human.pickUpKey();
                break;
            case DOOR: 
                human.enterDoor();
                break;
        }
    }
}
