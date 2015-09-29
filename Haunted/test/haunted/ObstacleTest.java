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
 * @author Mal
 */
public class ObstacleTest {
    
    public ObstacleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPosition method, of class Obstacle.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Obstacle instance = new Obstacle();
        Point2D expResult = null;
        Point2D result = instance.getPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosition method, of class Obstacle.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Point2D position = null;
        Obstacle instance = new Obstacle();
        instance.setPosition(position);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSprite method, of class Obstacle.
     */
    @Test
    public void testGetSprite() {
        System.out.println("getSprite");
        Obstacle instance = new Obstacle();
        String expResult = "";
        String result = instance.getSprite();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSprite method, of class Obstacle.
     */
    @Test
    public void testSetSprite() {
        System.out.println("setSprite");
        String sprite = "";
        Obstacle instance = new Obstacle();
        instance.setSprite(sprite);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
