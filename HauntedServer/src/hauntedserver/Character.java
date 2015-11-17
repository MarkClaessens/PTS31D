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
    private Player controller;
    
    /**
     * 
     */
    public Character(){
        
    }
    
    /**
     * 
     * @param direction 
     */
    public void move(DirectionType direction){
        
    }
    
    /**
     * 
     */
    public void detectCollision(){
        
    }
    
    /**
     * 
     * @return 
     */
    public List<Point2D> getHitBoxPoints(){
        return null;
    }
    
    /**
     * 
     * @param i
     * @param min
     * @param max
     * @return 
     */
    public boolean betweenInclusive(int i, int min, int max){
        return false;
    }
}
