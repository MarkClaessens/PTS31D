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

    private boolean vulnerable, isGhost, dead, rip = false;
    private Calendar stationaryTime, timeOfDeath;
    private IPlayer controllingPlayer;
    private Game game;
    private static int id = 0;

    public boolean isVulnerable() {
        return vulnerable;
    }

    public int getID() {
        return this.id;
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
    
    public boolean getRip(){
        return this.rip;
    }
    public void setRip(boolean bool){
        this.rip = bool;
    }

    public IPlayer getControllingPlayer() {
        return this.controllingPlayer;
    }

    public Calendar getTimeOfDeath() {
        return timeOfDeath;
    }

    public void clearTimeOfDeath() {
        this.timeOfDeath = null;
    }

    public void setTimeOfDeath() {
        this.timeOfDeath = Calendar.getInstance();
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void setPosition(Point2D position) {
        super.setPosition(position);

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
     * @param bestuurder the player that owns this object
     */
    public Ghost(IPlayer bestuurder) {
        super(null);
        this.isGhost = true;
        this.vulnerable = true;
        this.dead = false;
        this.stationaryTime = Calendar.getInstance();
        this.stationaryTime.clear();
        this.timeOfDeath = null;
        this.controllingPlayer = bestuurder;
        this.movementSpeed = 8;
        Ghost.id += 1;
    }

    public void reset() {
        this.dead = false;
        this.vulnerable = true;
        this.isGhost = true;
        this.stationaryTime.clear();
        this.timeOfDeath = null;
        this.moving = false;
    }

    /**
     * This possesses a human, this ghost becomes the human, the previous human
     * becomes a ghost (with his own coloured sprites).
     *
     * @param game
     */
    public void possess(Game game) {
        List<IPlayer> players = game.getPlayers();
        boolean humanFound = false;

        System.out.println("game gethuman() " + game.getHuman().toString());
        System.out.println("players " + game.getPlayers().toString());
        System.out.println("ghosts " + game.getGhosts().toString());
        while (!humanFound) {
            for (IPlayer player : players) {
                try {
                    if (player.getCharacter() instanceof Human) {
                        Character human = game.getHuman();
                        player.setCharacter(this);
                        this.controllingPlayer.setCharacter(human);
                        game.setCurrentHuman(this.controllingPlayer);
                        //set control of ghost to previous human
                        this.controllingPlayer = player;
                        humanFound = true;
                        break;
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (humanFound) {
            this.reset();
            this.setPosition(game.pickGhostSpawnPoint(false));
        }
        System.out.println("after");
        System.out.println("game gethuman() " + game.getHuman().toString());
        System.out.println("players " + game.getPlayers().toString());
        System.out.println("ghosts " + game.getGhosts().toString());

    }

    /**
     * Changes the appearance of the ghost, it becomes a wall or a ghost. If the
     * isGhost true and it will become "a wall" then the stationaryTime has to
     * be at least 1,5 seconds ago. So the ghost object can't become "a wall"
     * immediately.
     */
    public void changeAppearance() {
        if (this.isGhost && System.currentTimeMillis() >= stationaryTime.getTimeInMillis() + 350 && !getMoving()) {
            this.vulnerable = false;
            this.isGhost = false;

            int x = (((int) this.getPosition().getX()) + 50) / 100 * 100;
            int y = (((int) this.getPosition().getY()) + 50) / 100 * 100;
            Point2D fixedPosition = new Point2D.Double(x, y);
            this.setPosition(fixedPosition);
        } else if (this.isGhost == false && getMoving()) {
            this.vulnerable = true;
            this.isGhost = true;
            this.stationaryTime.clear();
        }
    }

    /**
     * Respawns the ghost when hit by the flashlight. If there aren't remaining
     * lifes for the ghost then the ghost dead attribute will be set to true.
     * The ghost whil respawn when the timeOfDeath is 3 seconds ago.
     *
     * @param game
     */
    public void vanish(Game game) {
        // Get the remaining lifes that the ghost has.
        int remainingGhostLifes = game.getLevel().getGhostLifePool();

        // If there are remaining lifes then respawn the ghost on the map.
        if (remainingGhostLifes > 0) {
            // Vanish the Ghost by setting in off the screen
            this.rip = true;
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
