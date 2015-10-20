package haunted;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Mike Evers
 */
public class Level {

    private int floorNr;
    private int ghostLifePool; // standard is 3
    private int width;
    private int height;
    private String theme;
    private List<Obstacle> obstacles = new ArrayList<>();

    /**
     * 
     * @return the list with obstacles
     */
    public List<Obstacle> getObstacles(){
        return obstacles;
    }
    
    /**
     * Sets the list of obstacles
     * @param obstacles 
     */
    public void setObstacles(List<Obstacle> obstacles){
        this.obstacles = obstacles;
    }
    
    /**
     * Returns the current floor number
     *
     * @return current floor number
     */
    public int getFloorNr() {
        return this.floorNr;
    }

    /**
     * Sets the floor number Floor, number is not a negative number.
     *
     * @param floorNr
     */
    public void setFloorNr(int floorNr) {
        this.floorNr = floorNr;
    }

    /**
     * Returns the current number of ghost lives for the game
     *
     * @return ghost life pool
     */
    public int getGhostLifePool() {
        return this.ghostLifePool;
    }

    /**
     * Sets the number of ghost lives. Ghost lives can not be null or a negative
     * number.
     *
     * @param ghostLifePool
     */
    public void setGhostLifePool(int ghostLifePool) {
        this.ghostLifePool = ghostLifePool;
    }

    /**
     * Returns the current level's width (x-axis)
     *
     * @return level's width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the current level's width (x-axis)
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Return the current level's height (y-axis)
     *
     * @return level's height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the level's height (y-axis)
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns the theme of the current level in a String
     *
     * @return level's theme
     */
    public String getTheme() {
        return this.theme;
    }

    /**
     * Sets the level's current theme, this determines which character sprites
     * are being used
     *
     * @param theme
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

        /**
     *
     * @param floorNr
     */
    public Level(int floorNr) {
        this.floorNr = floorNr;
        this.height = 1000;
        this.width = 1500;
        this.ghostLifePool = 3;
        this.theme = "";
    }
    
    /**
     * Generates the location of the key on the current level, this location is
     * random. This is done after the door location is generated.
     */
    public void generateKeyLocation() {
        Point2D keyLocation = generateRandomPoint2DLocation(ObstacleType.KEY);
        
        // Detect if the keyLocation collides with another object.
        keyLocation = checkNewObstacleForCollision(keyLocation, ObstacleType.KEY);
        
        // Create the new key obstacle and add it to the obstacles list.
        Obstacle key = new Obstacle(ObstacleType.KEY, "Key", keyLocation, this);
        obstacles.add(key);
    }

    /**
     * Generates the location of the door on the current level, this location is
     * random.
     */
    public void generateDoorLocation() {
        Point2D doorLocation = generateRandomPoint2DLocation(ObstacleType.DOOR);
        
        //Detect if the doorLocation collides with another object.
        doorLocation = checkNewObstacleForCollision(doorLocation, ObstacleType.DOOR);
        
        // Create the new door obstacle and add it to the obstacles list
        Obstacle door = new Obstacle(ObstacleType.DOOR, "Door", doorLocation, this);
        obstacles.add(door);
    }

    /**
     * Generate the level's layout. It happens in this order: obstacles, door,
     * key, characters 
     * [!] characters are placed and bind in the game class right now,
     * but we can replace this later [!] 
     */
    public void generateLayout() {
        // TODO - implement Level.generateLayout
        throw new UnsupportedOperationException();
    }

    /**
     * Places obstacles on the map, this happens when generating the level's
     * layout.
     */
    public void placeObstacles() {
        // TODO - implement Level.placeObstacles
        throw new UnsupportedOperationException();
    }
    
    /**
     * Generate a random Point2D location on the map.
     * This method can be used for placing the key, door or wall obstacles.
     * @param type obstacleType (used for min and max)
     * @return 
     */
    public Point2D generateRandomPoint2DLocation(ObstacleType type){
        int minX;
        int maxX;
        int minY;
        int maxY;
        switch(type){
            case KEY:
            case WALL: 
                minX = 1; maxX = 14; minY = 1; maxY = 9;
                break;
            default: // DOOR -> will be generated somewhere in the upper row of the map (x = [1/15]  && y = 0)
                minX = 0; maxX = 15; minY = 0; maxY = 0;
                break;
        }
        
        Random randomizer = new Random();
        int randomX = (randomizer.nextInt(maxX - minX + 1) + minX) * 100;
        int randomY = (randomizer.nextInt(maxY - minY + 1) + minY) * 100;
  
        Point2D randomPoint2D = new Point2D.Double(randomX, randomY);
        
        return randomPoint2D;
    }
    
    
    /**
     * Detect if a new obstacle collides with another obstacle
     */
    public Point2D checkNewObstacleForCollision(Point2D obstaclePointParameter, ObstacleType type){
        Point2D obstaclePoint = obstaclePointParameter;
        boolean hasCollision = false;
        
        for(Obstacle o : obstacles){
            if(o.getPosition() == obstaclePoint){
                hasCollision = true;
                obstaclePoint = generateRandomPoint2DLocation(type);
            }
            else{
                hasCollision = false;
            }
        }
        
        if(hasCollision){
            obstaclePoint = checkNewObstacleForCollision(obstaclePoint, type);
        }
        
        return obstaclePoint;    
    }
}
