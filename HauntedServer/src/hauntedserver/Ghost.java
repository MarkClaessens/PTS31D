/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Calendar;

/**
 *
 * @author jvdwi
 */
public class Ghost extends Character {
    private boolean vulnerable, isGhost;
    private Calendar stationaryTime, timeOfDeath;
    
    public boolean isVulnerable(){
        return vulnerable;
    }
    
    public void setVulnerable(boolean isVulnerable){
        this.vulnerable = isVulnerable;
    }
    
    public Calendar getTimeOfDeath(){
        return timeOfDeath;
    }
    
    /**
     * Constructor for Ghost sets vulnerable and isGhost to true and sets the
     * super variables in Character
     *
     * @param position, the Point2D position of the Ghost on the map
     * @param color, color of the Ghost
     * @param spritesUp, up sprites of the Ghost
     * @param spritesDown, down sprites of the Ghost
     * @param spritesLeft, left sprites of the Ghost
     * @param spritesRight, right sprites of the Ghost
     * @param game, the game in which the Ghost is active
     */
    public Ghost(Point2D position, Color color, String[] spritesUp, String[] spritesDown, String[] spritesLeft, String[] spritesRight, Game game) {
        super(position, spritesUp, spritesDown, spritesLeft, spritesRight, game);
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
