/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

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
     * @param position the spawn Point2D position of the Human on the map
     * @param game 
     */
    public Human(Point2D position, Game game) {
        super(position, game);
        this.hasKey = false;
    }
    
    /**
     * 
     * @return true if human has picked up the key
     */
    public boolean hasKey(){
        return hasKey;
    }
    
    /**
     * if haskey == false, hasKey becomes true
     */
    public void pickUpKey(){
        if(!hasKey){
            hasKey = true;
        }
    }
    
    /**
     * if haskey == true, enterDoor will cause endround, so the game can continue to the next round or go to the victory screen.
     * Otherwise, this method won't cause anything.
     */
    public void enterDoor(){
        // First check if this entering was on the last floor (last level).
//        if (game.getFloorAmount() - 1 == game.getCurrentFloor()) {
//            game.setRunning(false);
//            game.getPlayers().stream().filter((player) -> (player.getCharacter() instanceof Human)).forEach((player) -> {
//                game.endGame(player);
//            });
//        } else {
//            game.setIsRunning(false);
//            game.endRound();
//        }
    }
    
    /**
     * check if the human interacts with ghost, key, door or wall
     * @return true if human interacts with something
     */
    public boolean checkInteract(){
        return false;
    }
    
    /**
     * Initializes the flashlight for the human.
     * With this, the human can see the things in the level, but only the things in range of the flashlight.
     * Flashlight is a triangle.
     */
    private void setFlashlight(){
        
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
