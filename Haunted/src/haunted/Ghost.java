package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.sql.Time;

/**
 *
 * @author Mike Evers
 */
public class Ghost extends Character {

    private boolean vulnerable;
    private boolean isGhost;
    private Time stationaryTime = null;

    /**
     * 
     * @return the time when the Ghost started standing still.
     */
    public Time getStationaryTime(){
        return this.stationaryTime;
    }
   
    /**
     * Sets the time when the Ghost started standing still.
     * @param stationaryTime 
     */
    public void setStationaryTime(Time stationaryTime){
        this.stationaryTime = stationaryTime;
    }
    
    /**
     *
     * @return the state of vulnerability
     * If the Ghost is vulnerable it can be killed by the Human.
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
     * Constructor for Ghost sets vulnerable and isGhost to true and sets the super
     * variables in Character
     *
     * @param position, the Point2D position of the Ghost on the map
     * @param color, color of the Ghost
     * @param sprite, sprite of the Ghost
     */
    public Ghost(Point2D position, Color color, String sprite) {
        super(position, color, sprite);
        this.isGhost = true;
        this.vulnerable = true;
    }
    
    /**
     * This possesses a human, this ghost becomes the human, the previous human
     * becomes a ghost.
     */
    public void possess() {
        // TODO - implement Ghost.possess
        // TODO - posses the human, works with Game.
        throw new UnsupportedOperationException();
    }

    /**
     * Changes the appearance of the ghost, it becomes a wall or a ghost.
     */
    public void changeAppearance() {
        // TODO - implement Ghost.changeApperance
        if (this.isGhost) {
            this.vulnerable = false;
            this.setSprite("Wall");
            this.isGhost = false;
        } else {
            this.vulnerable = true;
            this.setSprite("Ghost");
            this.isGhost = true;
        }
    }

    /**
     * Removes the ghost when hit by the flashlight
     */
    public void vanish() {
        // TODO - implement Ghost.vanish
        throw new UnsupportedOperationException();
    }
}
