/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import java.awt.geom.Point2D;

/**
 *
 * @author jvdwi
 */
public class Human extends Character {

    private int flashlightRange, flashlightAngle;
    private boolean hasKey;
    private double[][] flashlightPoints;

    /**
     * The Constructor for human.
     * This initializes the flashlightRange, flashlightAngle and the flashlightPoints by calling setFlashlight
     * Also initialize the base class Character!
     */
    public Human(Point2D position, Game game) {
        super(position, game);
        this.hasKey = false;
    }
    
    /**
     * if haskey == false, hasKey becomes true
     */
    public void pickUpKey(){
        
    }
    
    /**
     * if haskey == true, enterDoor will cause endround, so the game can continue to the next round or go to the victory screen.
     * Otherwise, this method won't do anything.
     */
    public void enterDoor(){
        
    }
    
    /**
     * check if the human interacts with ghost, key, door or wall
     */
    public void checkInteract(){
        
    }
    
    /**
     * Initializes the flashlight for the human.
     * With this, the human can see the things in the level, but only the things in range of the flashlight.
     * Flashlight is a triangle.
     */
    public void setFlashlight(){
        
    }
    
    /**
     * Returns the coordinates of the flashlight polygon
     * So the fx part can draw this for the human.
     * @return polygon of the flashlight.
     */
    public int[] getFlashlightPolygon(){
        return null;
    }
}
