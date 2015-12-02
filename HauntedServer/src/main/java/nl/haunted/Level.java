package nl.haunted;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Mike Evers
 */
public class Level {

    private int floorNr, ghostLifePool, backgroundInt;
    private Point2D keyLocation, doorLocation;
    private DirectionType doorDirection;
    private BufferedImage collisionMap;

    public int getBackgroundInt(){
        return backgroundInt;
    }
    
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
        generateLayout();
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
     * Picks a random collisionMap (level background) and sets the background int.
     */
    private void generateLayout() {
        //Pick a number between 1 and 6 that represents the level (backgroundX)
        Random randomizer = new Random();
        this.backgroundInt =  randomizer.nextInt(6) + 1;
        
        // Load the random choosen collisionMap.
//        ImageIO.read("")
//        ImageIO.read("res/testMap.png");

//        this.collisionMap = new BufferedImage();
    }
}
