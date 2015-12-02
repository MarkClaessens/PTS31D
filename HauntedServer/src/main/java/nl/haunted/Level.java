package nl.haunted;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
        pickCollisionMap();
        generateDoorLocation();
        generateKeyLocation();
    }

    /**
     * Generates the spawn location of the key.
     */
    private void generateKeyLocation() {
        // TODO mike
    }

    /**
     * Generates the spawn location of the door.
     * Is always in the top/bottom/left/right row of the map.
     * This is why we need to set door direction. 
     */
    private void generateDoorLocation() {
        // Make an list with the points where the door is allowed to spawn.
        List<Point2D> spawnPointsTop = new ArrayList<Point2D>();
        List<Point2D> spawnPointsBottom = new ArrayList<Point2D>();
        List<Point2D> spawnPointsLeft = new ArrayList<Point2D>();
        List<Point2D> spawnPointsRight = new ArrayList<Point2D>();
        
        for(int i = 0; i < 1000; i += 100){
          spawnPointsLeft.add(new Point2D.Double(0, i));
        }
        for(int i = 0; i < 1000; i += 100){
          spawnPointsRight.add(new Point2D.Double(1400, i));
        }
        for(int i = 100; i < 1500; i += 100){
          spawnPointsTop.add(new Point2D.Double(i, 0));
        }
        for(int i = 100; i < 1500; i += 100){
          spawnPointsBottom.add(new Point2D.Double(i, 900));
        }
        
        // Random choose if we spawn the door at top/bottom/left/right
        Random randomizer = new Random();
        int randomInt = randomizer.nextInt(4) + 1;
        
        int index;
        switch(randomInt){
            case 1: 
                index = (int)(Math.random()*spawnPointsTop.size());
                this.doorLocation = spawnPointsTop.get(index);
                this.doorDirection = DirectionType.DOWN;
                break;
            case 2:
                index = (int)(Math.random()*spawnPointsBottom.size());
                this.doorLocation = spawnPointsBottom.get(index);
                this.doorDirection = DirectionType.UP;
                break;
            case 3:
                index = (int)(Math.random()*spawnPointsLeft.size());
                this.doorLocation = spawnPointsLeft.get(index);
                this.doorDirection = DirectionType.RIGHT;
                break;
            default: //4
                index = (int)(Math.random()*spawnPointsRight.size());
                this.doorLocation = spawnPointsRight.get(index);
                this.doorDirection = DirectionType.LEFT;
                break;
        }       
    }

    /**
     * Picks a random collisionMap (level background) and sets the background int.
     */
    private void pickCollisionMap() {
        //Pick a number between 1 and 6 that represents the level (backgroundX)
        Random randomizer = new Random();
        this.backgroundInt =  randomizer.nextInt(6) + 1;
        
        //Load the random choosen collisionMap.
        InputStream inputStream = getClass().getResourceAsStream("/collisionMap" + Integer.toString(backgroundInt) + ".png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(inputStream);
            this.collisionMap = img;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
