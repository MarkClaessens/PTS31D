package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;



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
     * @param game, the game in which the Ghost is active
     */
    public Human(Point2D position, Color color, String sprite, Game game) {
        super(position, color, sprite, game);
        this.hasKey = false;
    }

       // Mike: i commented this out because I programed the transformation in the Ghost class.
//    /**
//     * The controls of the current ghost as human goes to the new ghost as human.
//     */
//    public void transferHuman() {
//        
//        }
//    }

    /**
     * Let the human pick up a key, called when the human touches the key
     */
    public void pickUpKey() {
        this.hasKey = true;
        List<Obstacle> obstacles = game.getCurrentLevel().getObstacles();
        for(Obstacle obstacle : obstacles){
            if(obstacle.getBehaviour() == ObstacleType.KEY){
                obstacles.remove(obstacle);
            }
        }
        game.getCurrentLevel().setObstacles(obstacles);
                
    }

    /**
     * Let the human get into the door.
     * Timer tick will check if the human has the key and touches the door.
     */
    public void enterDoor() {
        // First check if this entering was on the last floor (last level).
        if(game.getFloorAmount() - 1 == game.getCurrentRound()){
            game.setIsRunning(false);
            game.endGame();
        }
        else{
            game.setIsRunning(false);
            game.endRound();
        }
    }
}
