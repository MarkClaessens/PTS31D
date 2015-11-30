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
 * @author jvdwi
 */
public class Level {

    private int floorNr, ghostLifePool, width, height;
    private Point2D keyLocation, doorLocation;
    private BufferedImage collisionMap, map;

    public int getCurrentFoor() {
        return this.floorNr;
    }
    
    public int getGhostLifePool() {
        return this.ghostLifePool;
    }
    
    public void setGhostLifePool(int ghostLifePool){
        this.ghostLifePool = ghostLifePool;
    }

    public Point2D getKeyLocation() {
        return this.keyLocation;
    }

    public Point2D getDoorLocation() {
        return this.doorLocation;
    }
    
    public BufferedImage getCollisionMap(){
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
     *
     */
    private void generateKeyLocation() {

    }

    /**
     *
     */
    private void generateDoorLocation() {

    }

    /**
     *
     */
    private void generateLayout() {

    }

    /**
     *
     */
    private void placeObstacles() {

    }

    /**
     *
     * @return
     */
    public BufferedImage generateMapFiles() {
        return null;
    }
}
