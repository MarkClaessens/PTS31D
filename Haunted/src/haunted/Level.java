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
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * Sets the list of obstacles
     *
     * @param obstacles
     */
    public void setObstacles(List<Obstacle> obstacles) {
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
     * key, characters [!] characters are placed and bind in the game class
     * right now, but we can replace this later [!]
     */
    public void generateLayout() {

        // <editor-fold defaultstate="collapsed" desc="row1">
        int[] row1 = new int[9];
        row1[0] = 1;
        row1[1] = 3;
        row1[2] = 4;
        row1[3] = 5;
        row1[4] = 6;
        row1[5] = 8;
        row1[6] = 10;
        row1[7] = 11;
        row1[8] = 13;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="row2">
        int[] row2 = new int[6];
        row2[0] = 1;
        row2[1] = 6;
        row2[2] = 8;
        row2[3] = 10;
        row2[4] = 11;
        row2[5] = 13;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="row3">
        int[] row3 = new int[5];
        row3[0] = 1;
        row3[1] = 2;
        row3[2] = 3;
        row3[3] = 6;
        row3[4] = 13;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="row4">
        int[] row4 = new int[7];
        row4[0] = 6;
        row4[1] = 7;
        row4[2] = 8;
        row4[3] = 9;
        row4[4] = 10;
        row4[5] = 12;
        row4[6] = 13;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="row5">
        int[] row5 = new int[3];
        row5[0] = 1;
        row5[1] = 4;
        row5[2] = 10;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="row6">
        int[] row6 = new int[9];
        row6[0] = 1;
        row6[1] = 4;
        row6[2] = 5;
        row6[3] = 6;
        row6[4] = 7;
        row6[5] = 8;
        row6[6] = 10;
        row6[7] = 11;
        row6[8] = 13;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="row7">
        int[] row7 = new int[3];
        row7[0] = 1;
        row7[1] = 8;
        row7[2] = 13;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="row8">
        int[] row8 = new int[3];
        row8[0] = 1;
        row8[1] = 2;
        row8[2] = 3;
        row8[3] = 4;
        row8[4] = 5;
        row8[5] = 6;
        row8[6] = 8;
        row8[7] = 9;
        row8[8] = 10;
        row8[9] = 11;
        row8[10] = 13;
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="add rows">
        ArrayList<int[]> rows = new ArrayList();
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        rows.add(row6);
        rows.add(row7);
        rows.add(row8);
        // </editor-fold>

        boolean[][] wallLayout = new boolean[15][10];
        int countX = 0;
        for (int[] x : rows) {
            countX++;
            for (int y : x) {
                wallLayout[countX][y] = true;
            }
        }
        System.out.println(wallLayout.toString());
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
     * Generate a random Point2D location on the map. This method can be used
     * for placing the key, door or wall obstacles.
     *
     * @param type obstacleType (used for min and max)
     * @return
     */
    public Point2D generateRandomPoint2DLocation(ObstacleType type) {
        int minX;
        int maxX;
        int minY;
        int maxY;
        switch (type) {
            case KEY:
            case WALL:
                minX = 1;
                maxX = 14;
                minY = 1;
                maxY = 9;
                break;
            default: // DOOR -> will be generated somewhere in the upper row of the map (x = [1/15]  && y = 0)
                minX = 0;
                maxX = 15;
                minY = 0;
                maxY = 0;
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
    public Point2D checkNewObstacleForCollision(Point2D obstaclePointParameter, ObstacleType type) {
        Point2D obstaclePoint = obstaclePointParameter;
        boolean hasCollision = false;

        for (Obstacle o : obstacles) {
            if (o.getPosition() == obstaclePoint) {
                hasCollision = true;
                obstaclePoint = generateRandomPoint2DLocation(type);
            } else {
                hasCollision = false;
            }
        }

        if (hasCollision) {
            obstaclePoint = checkNewObstacleForCollision(obstaclePoint, type);
        }

        return obstaclePoint;
    }
}
