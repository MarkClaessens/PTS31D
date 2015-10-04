/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.awt.Point;
import java.awt.geom.Point2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mal
 */
public class LevelTest {

    public LevelTest() {
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
     * Test of getFloorNr method, of class Level.
     */
    @Test
    public void testGetFloorNr() {
        Level current = new Level(5);
        
        assertEquals(5, current.getFloorNr());
    }

    /**
     * Test of setFloorNr method, of class Level.
     */
    @Test
    public void testSetFloorNr() {
        Level current = new Level(1);

        current.setFloorNr(-10);
        current.setFloorNr(2);              
        
        assertEquals(2, current.getFloorNr());
    }

    /**
     * Test of getGhostLifePool method, of class Level.
     */
    @Test
    public void testGetGhostLifePool() {
        Level current = new Level(1);
        
        assertEquals(3, current.getGhostLifePool());
    }

    /**
     * Test of setGhostLifePool method, of class Level.
     */
    @Test
    public void testSetGhostLifePool() {
        Level current = new Level(1);

        current.setGhostLifePool(-1);
        current.setGhostLifePool(4);
        
        assertEquals(4, current.getGhostLifePool());
    }

    /**
     * Test of getShape method, of class Level.
     */
    @Test
    public void testGetShape() {
        Level current = new Level(1);
        current.setShape("Test");
        
        assertEquals("Test", current.getShape());
    }

    /**
     * Test of setShape method, of class Level.
     */
    @Test
    public void testSetShape() {
        Level current = new Level(1);
        current.setShape("Test");
        
        assertEquals("Test", current.getShape());
    }

    /**
     * Test of getWidth method, of class Level.
     */
    @Test
    public void testGetWidth() {
        Level current = new Level(1);
        current.setWidth(50);
        
        assertEquals(50, current.getWidth());
    }

    /**
     * Test of setWidth method, of class Level.
     */
    @Test
    public void testSetWidth() {
        Level current = new Level(1);

        current.setWidth(-100);
        current.setWidth(100);
        
        assertEquals(100, current.getWidth());
    }

    /**
     * Test of getHeight method, of class Level.
     */
    @Test
    public void testGetHeight() {
        Level current = new Level(1);
        current.setHeight(65);
        
        assertEquals(65, current.getHeight());
    }

    /**
     * Test of setHeight method, of class Level.
     */
    @Test
    public void testSetHeight() {
        Level current = new Level(1);

        current.setHeight(-100);
        current.setHeight(120); 
        
        assertEquals(120, current.getHeight());
    }

    /**
     * Test of getKeyLocation method, of class Level.
     */
    @Test
    public void testGetKeyLocation() {
        Level current = new Level(1);
        current.setKeyLocation(new Point(50,50));
        Point2D key = new Point(50,50);
        
        assertEquals(key, current.getKeyLocation());
    }

    /**
     * Test of setKeyLocation method, of class Level.
     */
    @Test
    public void testSetKeyLocation() {
        Level current = new Level(1);
        
        current.setKeyLocation(new Point(-5, 50));
        current.setKeyLocation(new Point(-10,60));
        current.setKeyLocation(new Point(50,50));
        
        assertEquals(new Point(50,50), current.getKeyLocation());
    }

    /**
     * Test of getDoorLocation method, of class Level.
     */
    @Test
    public void testGetDoorLocation() {
        Level current = new Level(1);
        current.setDoorLocation(new Point(100,100));
        
        assertEquals(new Point(100,100), current.getDoorLocation());
    }

    /**
     * Test of setDoorLocation method, of class Level.
     */
    @Test
    public void testSetDoorLocation() {
        Level current = new Level(1);
        
        current.setDoorLocation(new Point(-50,50));
        current.setDoorLocation(new Point(50, -50));
        current.setDoorLocation(new Point(50,50));
        
        assertEquals(new Point(50,50), current.getDoorLocation());
         
    }

    /**
     * Test of getTheme method, of class Level.
     */
    @Test
    public void testGetTheme() {
        Level current = new Level(1);
        current.setTheme("Test");
        
        assertEquals("Test", current.getTheme());
    }

    /**
     * Test of setTheme method, of class Level.
     */
    @Test
    public void testSetTheme() {
        Level current = new Level(1);
        current.setTheme("Test");
        
        assertEquals("Test", current.getTheme());
    }

    /**
     * Test of generateKeyLocation method, of class Level.
     */
    @Test
    public void testGenerateKeyLocation() {
        Level current = new Level(1);
        current.generateKeyLocation();
        
        assertEquals(Point2D.class, current.getKeyLocation());
    }

    /**
     * Test of generateDoorLocation method, of class Level.
     */
    @Test
    public void testGenerateDoorLocation() {
        Level current = new Level(1);
        current.generateDoorLocation();
        
        assertEquals(Point2D.class, current.getDoorLocation());
    }

    /**
     * Test of generateLayout method, of class Level.
     */
    @Test
    public void testGenerateLayout() {
        Level current = new Level(1);
        current.generateLayout();
        
        assertEquals(Point2D.class, current.getKeyLocation());
        assertEquals(Point2D.class, current.getDoorLocation());
    }

    /**
     * Test of placeObstacles method, of class Level.
     */
    @Test
    public void testPlaceObstacles() {
        //Hoe kan je dit unit testen?
    }

}
