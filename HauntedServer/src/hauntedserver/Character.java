/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import java.awt.geom.Point2D;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public abstract class Character {
    private Point2D position;
    private double movementSpeed;
    private DirectionType direction;
    private boolean moving;

    
    /**
     * Constructor for character
     * Initialize position, movementSpeed, controller
     */
    public Character(){
        
    }
    
    /**
     * Moves the character into the given direction. 
     * But be aware of collision with another characters, key, walls or door.
     * @param direction 
     */
    public void move(DirectionType direction){
        
    }
    
    /**
     * Checks if there is a collision with another character, walls, key or door.
     */
    public void detectCollision(){
        
    }
    
    /**
     * Returns a list of hitbox points, the points where the character can be hitted on.
     * Important for the collisioncheck.
     * @return list of hitbox points.
     */
    public List<Point2D> getHitBoxPoints(){
        return null;
    }
    
    /**
     * returns true if i is between or equal to min and max
     * @param i
     * @param min
     * @param max
     * @return true if between or equal
     */
    public boolean betweenInclusive(int i, int min, int max){
        return false;
    }
}
