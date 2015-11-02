package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;
import static java.lang.Math.tan;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Mike Evers
 */
public class Human extends Character {

    private int flashlightRange = 100; // Mike: setted default range to 100
    private int flashlightAngle = 15; // Mike: setted default angle to 45
    private boolean hasKey;

    private double flX3 = 0, flY3 = 0, flX2 = 0, flY2 = 0, flX1, flY1;
    private double flY23, flX32, flY31, flX13;
    private double flDet, flMinD, flMaxD;

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
     * @param spritesUp, up sprites of the Ghost
     * @param spritesDown, down sprites of the Ghost
     * @param spritesLeft, left sprites of the Ghost
     * @param spritesRight, right sprites of the Ghost
     * @param game, the game in which the Ghost is active
     */
    public Human(Point2D position, Color color, String[] spritesUp, String[] spritesDown, String[] spritesLeft, String[] spritesRight, Game game) {
        super(position, color, spritesUp, spritesDown, spritesLeft, spritesRight, game);
        this.hasKey = false;
    }

    /**
     * Let the human pick up a key, called when the human touches the key
     */
    public void pickUpKey() {
        this.hasKey = true;
//        List<Obstacle> obstacles = game.getCurrentLevel().getObstacles();
//        for (Obstacle obstacle : obstacles) {
//            if (obstacle.getBehaviour() == ObstacleType.KEY) {
//                obstacles.remove(obstacle);
//                break;
//            }
//        }
//        game.getCurrentLevel().setObstacles(obstacles);

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

    /**
     * check the interaction between the human and the key, door
     */
    public void checkInteract() {
        Point2D door = new Point2D.Double(this.game.getCurrentLevel().getDoorLocation().getX()+40,this.game.getCurrentLevel().getDoorLocation().getY());
        Point2D key = this.game.getCurrentLevel().getKeyLocation();

        //door collision
        if ((checkHitboxCollision(new Point2D.Double(this.getPosition().getX()+45,this.getPosition().getY()+10), 10, 3, door, 20, 5) && this.hasKey)) //key collision   
        {
            this.enterDoor();
        }
        if (checkHitboxCollision(this.getPosition(), 80, 80, key, 80, 80)) {
            this.pickUpKey();
        }
        //flashlight and ghost collision
        if (this.checkGhostCollision() != null) {
            this.checkGhostCollision().possess();
        }
        setFlashLight();
        List<Ghost> deadghosts = new ArrayList();
        this.game.getGhosts().stream().forEach((g) -> {
            if (g.isVulnerable()) {
                boolean hit = false;
                for (Point2D p : g.getHitboxPoints()) {
                    if (flashlightCollision(p)) {
                        hit = true;
                        break;
                    }
                }
                if (hit) {
                    deadghosts.add(g);
                    g.setBeginSpawnTime(Calendar.getInstance());
                    g.vanish();
                }
            }
        });

    }

    /**
     * check if human collides with a ghost
     * @return the ghost where the human collides with
     */
    public Ghost checkGhostCollision() {
        //ghost collision
        for (Ghost g : this.game.getGhosts()) {
            if (checkHitboxCollision(this.getPosition(), 90, 90, g.getPosition(), 90, 90)) {
                return g;
            }
        }
        return null;
    }

    /**
     * sets and configures the flashlight for the human
     */
    private void setFlashLight() {
        flX1 = this.getPosition().getX() + 50;
        flY1 = this.getPosition().getY() + 50;
        switch (this.getDirection()) {
            case UP:
                flX2 = flX1 - tan(this.flashlightAngle) * this.flashlightRange;
                flY2 = flY1 - this.flashlightRange;
                flX3 = flX1 + tan(this.flashlightAngle) * this.flashlightRange;
                flY3 = flY1 - this.flashlightRange;
                break;
            case DOWN:
                flX2 = flX1 + tan(this.flashlightAngle) * this.flashlightRange;
                flX3 = flX1 - tan(this.flashlightAngle) * this.flashlightRange;
                flY2 = flY1 + this.flashlightRange;
                flY3 = flY2;
                break;
            case RIGHT:
                flX2 = flX1 + this.flashlightRange;
                flX3 = flX2;
                flY2 = flY1 + tan(this.flashlightAngle) * this.flashlightRange;
                flY3 = flY1 - tan(this.flashlightAngle) * this.flashlightRange;
                break;
            case LEFT:
                flX2 = flX1 - this.flashlightRange;
                flX3 = flX2;
                flY2 = flY1 + tan(this.flashlightAngle) * this.flashlightRange;
                flY3 = flY1 - tan(this.flashlightAngle) * this.flashlightRange;
                break;
        }
    }

    /**
     * get the flashlight polygon, so that you can see where the flashlight is.
     * @return the coordinates of the flashlight polygon
     */
    public int[] getFlashLightPolygon() {
        int[] i = new int[6];
        i[0] = (int) this.getPosition().getX() + 50;
        i[1] = (int) this.getPosition().getY() + 50;
        i[2] = (int) flX2;
        i[3] = (int) flY2;
        i[4] = (int) flX3;
        i[5] = (int) flY3;
        return i;

    }

    /**
     * check if the given position collides with the flashlight.
     * @param point
     * @return 
     */
    public boolean flashlightCollision(Point2D point) {

        flY23 = flY2 - flY3;
        flX32 = flX3 - flX2;
        flY31 = flY3 - flY1;
        flX13 = flX1 - flX3;
        flDet = flY23 * flX13 - flX32 * flY31;
        flMinD = Math.min(flDet, 0);
        flMaxD = Math.max(flDet, 0);

        double x = point.getX();
        double y = point.getY();
        double dx = x - flX3;
        double dy = y - flY3;
        double a = flY23 * dx + flX32 * dy;
        if (a < flMinD || a > flMaxD) {
            return false;
        }
        double b = flY31 * dx + flX13 * dy;
        if (b < flMinD || b > flMaxD) {
            return false;
        }
        double c = flDet - a - b;
        return !(c < flMinD || c > flMaxD);

    }

    /**
     * gives the hitboxes where the human can collide with.
     * @return list of hitbox points
     */
    @Override
    public List<Point2D> getHitboxPoints() {
        List<Point2D> hitboxes = new ArrayList();
        hitboxes.add(this.getPosition());
        hitboxes.add(new Point2D.Double(this.getPosition().getX() + 100, this.getPosition().getY()));
        hitboxes.add(new Point2D.Double(this.getPosition().getX(), this.getPosition().getY() + 100));
        hitboxes.add(new Point2D.Double(this.getPosition().getX() + 100, this.getPosition().getY() + 100));
        return hitboxes;
    }
}
