/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.awt.geom.Point2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mike Evers
 */
public class ObstacleTest {

    public ObstacleTest() {
    }
    Level level;
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        level = new Level(3);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPosition method, of class Obstacle.
     */
    @Test
    public void testGetPosition() {
        Obstacle instance = new Obstacle(ObstacleType.WALL, "obstacleWall", new Point2D.Double(500, 500), level);
        Point2D expResult = new Point2D.Double(500, 500);
        Point2D result = instance.getPosition();
        assertEquals("Obstacle's getPosition returns something unexpected", expResult, result);
    }

    /**
     * Test of setPosition method, of class Obstacle.
     */
    @Test
    public void testSetPosition() {
        Point2D position = new Point2D.Double(600, 600);
        Obstacle instance = new Obstacle(ObstacleType.WALL, "obstacleWall", new Point2D.Double(500, 500), level);
        instance.setPosition(position);
        
        assertEquals("Obstacle setPosition isn't correct", position, instance.getPosition());
    }
}
