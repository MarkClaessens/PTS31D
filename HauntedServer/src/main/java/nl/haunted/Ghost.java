/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.geom.Point2D;
import java.util.Calendar;

/**
 *
 * @author jvdwi
 */
public class Ghost extends Character {
    private boolean vulnerable, isGhost;
    private Calendar stationaryTime, timeOfDeath;
    private Point2D spawnPosition;
    private Game game;
    
    public boolean isVulnerable(){
        return vulnerable;
    }
    
    public void setVulnerable(boolean isVulnerable){
        this.vulnerable = isVulnerable;
    }
    
    public Calendar getTimeOfDeath(){
        return timeOfDeath;
    }
    
    public Point2D getSpawnPosition(){
        return spawnPosition;
    }
    
    public Game getGame(){
        return game;
    }
    
    /**
     * Constructor for Ghost sets vulnerable and isGhost to true and sets the
     * super variables in Character
     *
     * @param position, the Point2D position of the Ghost on the map
     * @param game, the game in which the Ghost is active
     */
    public Ghost(Point2D position, Game game) {
        super(position, game);
        this.spawnPosition = position;
        this.game = game;
        this.isGhost = true;
        this.vulnerable = true;
    }
    
    /**
     * This possesses a human, this ghost becomes the human, the previous human
     * becomes the ghost object but has its own sprites. 
     */
    public void possess(){
        
    }
    
    /**
     * Changes the appearance of the ghost, it becomes a wall or a ghost. If the
     * isGhost true and it will become "a wall" then the stationaryTime has to
     * be at least 1,5 seconds ago. So the ghost object can't become "a
     * wall" immediately.
     */
    public void changeAppearance(){
        
    }
    
    /**
     * Respawns the ghost when hit by the flashlight. If there aren't remaining
     * lifes for the ghost then the level is over. The ghost whil respawn when the timeOfDeath is 
     * 3 seconds ago.
     */
    public void vanish(){
        
    }
}
