/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mike Evers
 */
public class Level {

    private int floorNr, ghostLifePool;
    private Point2D keyLocation, doorLocation;
    private DirectionType doorDirection;
    private BufferedImage collisionMap, map;

    public int getCurrentFoor() {
        return this.floorNr;
    }

    public int getGhostLifePool() {
        return this.ghostLifePool;
    }

    public void setGhostLifePool(int ghostLifePool) {
        this.ghostLifePool = ghostLifePool;
    }

    public Point2D getKeyLocation() {
        return this.keyLocation;
    }

    public Point2D getDoorLocation() {
        return this.doorLocation;
    }
    
    public DirectionType getDoorDirection(){
        return doorDirection;
    }

    public BufferedImage getCollisionMap() {
        return collisionMap;
    }

    /**
     *
     * @param floorNr
     */
    public Level(int floorNr) {
        this.floorNr = floorNr;
    }

    /**
     * Generates the spawn location of the key.
     */
    private void generateKeyLocation() {

    }

    /**
     * Generates the spawn location of the door.
     */
    private void generateDoorLocation() {

    }

    /**
     * Generates the maze of the level. 
     */
    private void generateLayout() {

    }

    /**
     * Generates the collisionmap of the level.
     * @return the collisionmap of the level.
     */
    public BufferedImage generateMapFiles() {
        return null;
    }
}
