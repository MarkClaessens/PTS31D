package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
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
     * Constructor of Human sets haskey to false and set the super variables in
     * Character
     *
     * @param position, the Point2D position of the Ghost on the map
     * @param color, color of the Ghost
     * @param sprite, sprite of the Ghost
     * @param game, the game in which the Ghost is active
     */
    public Human(Point2D position, Color color, String[] spritesUp, String[] spritesDown, String[] spritesLeft, String[] spritesRight, Game game) {
        super(position, color, spritesUp, spritesDown, spritesLeft, spritesRight, game);
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
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getBehaviour() == ObstacleType.KEY) {
                obstacles.remove(obstacle);
                break;
            }
        }
        game.getCurrentLevel().setObstacles(obstacles);

    }

    /**
     * Let the human get into the door. Timer tick will check if the human has
     * the key and touches the door.
     */
    public void enterDoor() {
        // First check if this entering was on the last floor (last level).
        if (game.getFloorAmount() - 1 == game.getCurrentRound()) {
            game.setIsRunning(false);
            game.getPlayers().stream().filter((player) -> (player.getCharacter() instanceof Human)).forEach((player) -> {
                game.endGame(player);
            });
        } else {
            game.setIsRunning(false);
            game.endRound();
        }
    }

    public void checkInteract() {
        Point2D door = this.game.getCurrentLevel().getDoorLocation();
        Point2D key = this.game.getCurrentLevel().getKeyLocation();

        //door collision
        if ((checkHitboxCollision(this.getPosition(), 10, 1, door, 10, 1)&& this.hasKey)) //key collision   
        {
            this.game.endRound();
        }
        if (checkHitboxCollision(this.getPosition(), 100, 100, key, 100, 100)) {
            this.pickUpKey();
        }

    }

    public Ghost checkGhostCollision() {
        //ghost collision
        for (Ghost g : this.game.getGhosts()) {
            if (checkHitboxCollision(this.getPosition(), 100, 100, g.getPosition(), 100, 100)) {
                return g;
            }
        }
        return null;
    }

    private boolean betweenInclusive(int i, int min, int max) {
        return i >= min && i <= max;
    }

    private boolean checkHitboxCollision(Point2D point1, int width1, int height1, Point2D point2, int width2, int height2) {
        //convert point1 in leftmost and rightmost X value and top and bottom Y value;
        int p1Xmax = (int) point1.getX();
        int p1Xmin = (int) p1Xmax + width1;
        int p1Ymin = (int) point1.getY();
        int p1Ymax = (int) p1Ymin + height1;

        //convert point2 in leftmost and rightmost X value and top and bottom Y value;
        int p2Xmax = (int) point2.getX();
        int p2Xmin = (int) p2Xmax + width2;
        int p2Ymin = (int) point2.getY();
        int p2Ymax = (int) p2Ymin + height2;

        return (betweenInclusive(p1Xmax, p2Xmin, p2Xmax) && (betweenInclusive(p1Ymin, p2Ymin, p2Ymax) || betweenInclusive(p1Ymax, p2Ymin, p2Ymax))) || (betweenInclusive(p1Xmin, p2Xmin, p2Xmax) && (betweenInclusive(p1Ymin, p2Ymin, p2Ymax) || betweenInclusive(p1Ymax, p2Ymin, p2Ymax)));

    }
}
