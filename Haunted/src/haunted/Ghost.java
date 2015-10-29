package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Mike Evers
 */
public class Ghost extends Character {

    private boolean vulnerable;
    private boolean isGhost;
    private Calendar stationaryTime = null;

    /**
     *
     * @return the time when the Ghost started standing still.
     */
    public Calendar getStationaryTime() {
        return this.stationaryTime;
    }

    /**
     * Sets the time when the Ghost started standing still. Started standing
     * still = pressing no moving keys.
     *
     * @param stationaryTime
     */
    public void setStationaryTime(Calendar stationaryTime) {
        this.stationaryTime = stationaryTime;
    }

    /**
     *
     * @return the state of vulnerability If the Ghost is vulnerable it can be
     * killed by the Human.
     */
    public boolean isVulnerable() {
        return this.vulnerable;
    }

    /**
     * this sets the vulnerability of the ghost object
     *
     * @param vulnerable
     */
    public void setVulnerable(boolean vulnerable) {
        this.vulnerable = vulnerable;
    }

    /**
     * Constructor for Ghost sets vulnerable and isGhost to true and sets the
     * super variables in Character
     *
     * @param position, the Point2D position of the Ghost on the map
     * @param color, color of the Ghost
     * @param sprite, sprite of the Ghost
     * @param game, the game in which the Ghost is active
     */
    public Ghost(Point2D position, Color color, String[] spritesUp, String[] spritesDown, String[] spritesLeft, String[] spritesRight, Game game) {
        super(position, color, spritesUp, spritesDown, spritesLeft, spritesRight, game);
        this.isGhost = true;
        this.vulnerable = true;
    }

    /**
     * This possesses a human, this ghost becomes the human, the previous human
     * becomes the ghost. Iteration 1: The players switch their characters.
     */
    public void possess() {
        List<Player> players = game.getPlayers();
        if (players.get(0).getCharacter() instanceof Human) {
            Character human = players.get(0).getCharacter();
            players.get(0).setCharacter(players.get(1).getCharacter());
            players.get(1).setCharacter(human);
        } else {
            Character human = players.get(1).getCharacter();
            players.get(1).setCharacter(players.get(0).getCharacter());
            players.get(0).setCharacter(human);
        }
    }

    /**
     * Changes the appearance of the ghost, it becomes a wall or a ghost. If the
     * isGhost true and it will become "a wall" then the stationaryTime has to
     * be at least 1,5 (?) seconds ago. So the ghost object can't become "a
     * wall" immediately.
     */
    public void changeAppearance() {
        if (this.isGhost && System.currentTimeMillis() >= stationaryTime.getTimeInMillis() + 1500) {
            this.vulnerable = false;
            this.setSpritesUp(new String[]{"ghostRedDown1.png", "ghostRedDown2.png", "ghostRedDown3.png"});
            this.isGhost = false;
        } else if (this.isGhost == false) {
            this.isGhost = true;
            this.setSpritesUp(new String[]{"Ghost"});
            this.vulnerable = true;
        }
    }

    /**
     * Respawns the ghost when hit by the flashlight. If the aren't remaining
     * lifes for the ghost then endGame(player) will be called.
     */
    public void vanish() {
        // Get the remaining lifes that the ghost has.
        int remainingGhostLifes = game.getCurrentLevel().getGhostLifePool();

        // If there are remaining lifes then respawn the ghost on the map.
        if (remainingGhostLifes > 0) {
            // Respawn the ghost random on the map 
            Point2D spawnPosition = game.pickRandomGhostSpawnPoint();
            super.setPosition(spawnPosition);
        } // If the ghost has no more lifes then end the game with the winning player as the parameter.
        else {
            game.getPlayers().stream().filter((player) -> (player.getCharacter() instanceof Human)).forEach((player) -> {
                game.endGame(player);
            });
        }
    }

    @Override
    public List<Point2D> getHitboxPoints() {
        // a  ab  b
        // ac    bd
        // c  cd  d

        List<Point2D> hitboxes = new ArrayList();
        hitboxes.add(this.getPosition());                                                               //a
        hitboxes.add(new Point2D.Double(this.getPosition().getX() + 50, this.getPosition().getY()));       //ab
        hitboxes.add(new Point2D.Double(this.getPosition().getX() + 100, this.getPosition().getY()));      //b
        hitboxes.add(new Point2D.Double(this.getPosition().getX() + 100, this.getPosition().getY() + 50));   //bd
        hitboxes.add(new Point2D.Double(this.getPosition().getX() + 100, this.getPosition().getY() + 100));  //d
        hitboxes.add(new Point2D.Double(this.getPosition().getX() + 50, this.getPosition().getY() + 100));   //cd
        hitboxes.add(new Point2D.Double(this.getPosition().getX(), this.getPosition().getY() + 100));      //c
        hitboxes.add(new Point2D.Double(this.getPosition().getX(), this.getPosition().getY() + 50));       //ac

        return hitboxes;
    }
}
