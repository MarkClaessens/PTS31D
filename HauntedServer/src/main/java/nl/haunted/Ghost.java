/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike Evers
 */
public class Ghost extends Character implements Serializable {

    private boolean vulnerable, isGhost, dead;
    private Calendar stationaryTime, timeOfDeath;
    private IPlayer bestuurder;
    private Game game;

    public boolean isVulnerable() {
        return vulnerable;
    }

    public void setVulnerable(boolean isVulnerable) {
        this.vulnerable = isVulnerable;
    }

    public void setDead(boolean isDead) {
        this.dead = isDead;
    }

    public boolean getDead() {
        return this.dead;
    }

    public Calendar getTimeOfDeath() {
        return timeOfDeath;
    }

    public void setTimeOfDeath() {
        this.timeOfDeath = Calendar.getInstance();
    }

    public Game getGame() {
        return game;
    }

    /**
     * Sets the stationary time to now when the Ghost started standing still.
     * Started standing still means pressing no moving keys.
     *
     */
    public void setStationaryTime() {
        this.stationaryTime = Calendar.getInstance();
    }

    /**
     *
     * @return the time when the Ghost started standing still.
     */
    public Calendar getStationaryTime() {
        return this.stationaryTime;
    }

    /**
     * Constructor for Ghost sets vulnerable and isGhost to true and sets the
     * super variables in Character
     *
     * @param position, the spawn Point2D position of the Ghost on the map
    '
     * @param bestuurder the player that owns this object
     */
    public Ghost(Point2D position, IPlayer bestuurder) {
        super(position);
        this.isGhost = true;
        this.vulnerable = true;
        this.dead = false;
        this.stationaryTime = null;
        this.timeOfDeath = null;
        this.bestuurder = bestuurder;
    }

    public void reset(){
        this.dead = false;
        this.vulnerable = true;
        this.isGhost = true;
        this.stationaryTime.clear();
        this.timeOfDeath.clear();
        this.moving = false;
    }
    
    /**
     * This possesses a human, this ghost becomes the human, the previous human
     * becomes a ghost (with his own coloured sprites).
     */
    public void possess(Game game) {
        List<IPlayer> players = game.getPlayers();
        boolean humanFound = false;

        while (!humanFound) {
            for (IPlayer player : players) {
                try {
                    if (player.getCharacter() instanceof Human) {
                        Character human = player.getCharacter();

                        player.setCharacter(this);
                        this.bestuurder.setCharacter(human);

                        humanFound = true;
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Changes the appearance of the ghost, it becomes a wall or a ghost. If the
     * isGhost true and it will become "a wall" then the stationaryTime has to
     * be at least 1,5 seconds ago. So the ghost object can't become "a wall"
     * immediately.
     */
    public void changeAppearance() {
        if (this.isGhost && System.currentTimeMillis() >= stationaryTime.getTimeInMillis() + 1500 && !getMoving()) {
            this.vulnerable = false;
            this.isGhost = false;
        } else if (this.isGhost == false && getMoving()) {
            this.vulnerable = true;
            this.isGhost = true;
            this.stationaryTime.clear();
        }
        
        int x = (((int) this.getPosition().getX()) + 50) / 100 * 100;   
        int y = (((int) this.getPosition().getY()) + 50) / 100 * 100;
        Point2D fixedPosition = new Point2D.Double(x, y);
        this.setPosition(fixedPosition);
    }

    /**
     * Respawns the ghost when hit by the flashlight. If there aren't remaining
     * lifes for the ghost then the ghost dead attribute will be set to true.
     * The ghost whil respawn when the timeOfDeath is 3 seconds ago.
     */
    public void vanish(Game game) {
        // Get the remaining lifes that the ghost has.
        int remainingGhostLifes = game.getLevel().getGhostLifePool();

        // If there are remaining lifes then respawn the ghost on the map.
        if (remainingGhostLifes > 0) {
            // Vanish the Ghost by setting in off the screen
            super.setPosition(new Point2D.Double(-800, -800));
            game.getLevel().setGhostLifePool(remainingGhostLifes - 1);
        } else {
            this.dead = true;
        }
    }

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
