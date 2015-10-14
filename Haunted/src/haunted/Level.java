package haunted;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mal
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
     * Generates the location of the key on the current level, this location is
     * random. This is done after the door location is generated.
     */
    public void generateKeyLocation() {
        // TODO - implement Level.generateKeyLocation
        throw new UnsupportedOperationException();
    }

    /**
     * Generates the location of the door on the current level, this location is
     * random.
     */
    public void generateDoorLocation() {
        // TODO - implement Level.generateDoorLocation
        throw new UnsupportedOperationException();
    }

    /**
     * Generate the level's layout. It happens in this order: obstacles, door,
     * key, characters.
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
     *
     * @param floorNr
     */
    public Level(int floorNr) {
        // TODO - implement Level.Level
        throw new UnsupportedOperationException();
    }

}
