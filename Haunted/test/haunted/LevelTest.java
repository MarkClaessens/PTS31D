/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.awt.Point;
import java.awt.geom.Point2D;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kevin
 */
public class LevelTest {
    Level current;
    
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
        current = new Level(1);
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
        
        assertEquals("getFloorNr incorrect, expected another floorNr", 5, current.getFloorNr());
    }

    /**
     * Test of setFloorNr method, of class Level.
     */
    @Test
    public void testSetFloorNr() {
        current.setFloorNr(2);                
        assertEquals(2, current.getFloorNr());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetFloorNrNegative(){
        current.setFloorNr(-10);
        Assert.fail("setFloorNr allows a negative number.");
    }
    

    /**
     * Test of getGhostLifePool method, of class Level.
     */
    @Test
    public void testGetGhostLifePool() {
        
        assertEquals(3, current.getGhostLifePool());
    }

    /**
     * Test of setGhostLifePool method, of class Level.
     */
    @Test
    public void testSetGhostLifePool() {
        current.setGhostLifePool(4);
        assertEquals(4, current.getGhostLifePool());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetGhostLifePoolNegative(){
        current.setGhostLifePool(-1);
        Assert.fail("setGhostLifePool allows a negative number");
    }
    
    /**
     * Test of getWidth method, of class Level.
     */
    @Test
    public void testGetWidth() {
        current.setWidth(50);
        
        assertEquals(50, current.getWidth());
    }

    /**
     * Test of setWidth method, of class Level.
     */
    @Test
    public void testSetWidth() {
        current.setWidth(100);      
        assertEquals(100, current.getWidth());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSetWidthNegative(){
        current.setWidth(-100);
        Assert.fail("setWidth allows a negative number");
    }

    /**
     * Test of getHeight method, of class Level.
     */
    @Test
    public void testGetHeight() {
        current.setHeight(65);
        assertEquals(65, current.getHeight());
    }

    /**
     * Test of setHeight method, of class Level.
     */
    @Test
    public void testSetHeight() {
        current.setHeight(120);  
        assertEquals(120, current.getHeight());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSetHeightNegative(){
        current.setHeight(-100);
        Assert.fail("setHeight allows a negative number");
    }

    /**
     * Test of getKeyLocation method, of class Level.
     */
    @Test
    public void testGetKeyLocation() {
        current.setKeyLocation(new Point(50,50));
        Point2D key = new Point(50,50);
        
        assertEquals(key, current.getKeyLocation());
    }

    /**
     * Test of setKeyLocation method, of class Level.
     */
    @Test
    public void testSetKeyLocation() {
        current.setKeyLocation(new Point(50,50));
        
        assertEquals(new Point(50,50), current.getKeyLocation());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSetKeyLocationX(){
        current.setKeyLocation(new Point(-5, 50));
        Assert.fail("setKeyLocation allows a negative x position");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSetKeyLocationY(){
        current.setKeyLocation(new Point(10,-60));
        Assert.fail("setKeyLocation allows a negative y position");
    }

    /**
     * Test of getDoorLocation method, of class Level.
     */
    @Test
    public void testGetDoorLocation() {
        current.setDoorLocation(new Point(100,100));
        
        assertEquals(new Point(100,100), current.getDoorLocation());
    }

    /**
     * Test of setDoorLocation method, of class Level.
     */
    @Test
    public void testSetDoorLocation() {
        current.setDoorLocation(new Point(50,50));
        assertEquals(new Point(50,50), current.getDoorLocation());  
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSetDoorLocationX(){
        current.setDoorLocation(new Point(-50,50));
        Assert.fail("setDoorLocation allows negative number x position");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSetDoorLocationY(){
        current.setDoorLocation(new Point(50, -50));
        Assert.fail("setDoorLocation allows negative number y position");
    }

    /**
     * Test of getTheme method, of class Level.
     */
    @Test
    public void testGetTheme() {
        current.setTheme("Test");       
        assertEquals("Test", current.getTheme());
    }

    /**
     * Test of setTheme method, of class Level.
     */
    @Test
    public void testSetTheme() {
        current.setTheme("Test");
        
        assertEquals("Test", current.getTheme());
    }

    /**
     * Test of generateKeyLocation method, of class Level.
     */
    @Test
    public void testGenerateKeyLocation() {
        current.generateKeyLocation();
        Assert.assertThat("generateKeyLocation doesn't create a Point2D object", 
                          current.getKeyLocation(), instanceOf(Point2D.class));
    }

    /**
     * Test of generateDoorLocation method, of class Level.
     */
    @Test
    public void testGenerateDoorLocation() {
        current.generateDoorLocation();
        Assert.assertThat("generateDoorLocation doesn't create a Point2D object",
                          current.getDoorLocation(), instanceOf(Point2D.class));
    }

    /**
     * Test of generateLayout method, of class Level.
     */
    @Test
    public void testGenerateLayout() {
        
        // TODO for kevin
        current.generateLayout();
        Assert.assertThat("generteLayout doesn't create a ", this, null);
        assertEquals(Point2D.class, current.getKeyLocation());
        assertEquals(Point2D.class, current.getDoorLocation());
    }

    /**
     * Test of placeObstacles method, of class Level.
     */
    @Test
    public void testPlaceObstacles() {
        // TODO for kevin, how to test this?
    }

}
