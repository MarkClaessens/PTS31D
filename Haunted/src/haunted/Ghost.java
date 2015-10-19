package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.sql.Time;
import java.util.List;

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
     * @param game, the game in which the Ghost is active
     */
    public Ghost(Point2D position, Color color, String sprite, Game game) {
        super(position, color, sprite, game);
        this.isGhost = true;
        this.vulnerable = true;
    }
    
    /**
     * This possesses a human, this ghost becomes the human, the previous human
     * becomes the ghost. Iteration 1: The players switch their characters.
     */
    public void possess() {
        List<Player> players = game.getPlayers();
        if(players.get(0).getCharacter() instanceof Human){
            Character human = players.get(0).getCharacter();
            players.get(0).setCharacter(players.get(1).getCharacter());
            players.get(1).setCharacter(human);
        }
        else{
            Character human = players.get(1).getCharacter();
            players.get(0).setCharacter(human);
            players.get(1).setCharacter(players.get(0).getCharacter());
        }
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
