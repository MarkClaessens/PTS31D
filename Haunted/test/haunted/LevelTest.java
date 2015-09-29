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
        System.out.println("getFloorNr");
        Level instance = null;
        int expResult = 0;
        int result = instance.getFloorNr();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFloorNr method, of class Level.
     */
    @Test
    public void testSetFloorNr() {
        System.out.println("setFloorNr");
        int floorNr = 0;
        Level instance = null;
        instance.setFloorNr(floorNr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGhostLifePool method, of class Level.
     */
    @Test
    public void testGetGhostLifePool() {
        System.out.println("getGhostLifePool");
        Level instance = null;
        int expResult = 0;
        int result = instance.getGhostLifePool();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGhostLifePool method, of class Level.
     */
    @Test
    public void testSetGhostLifePool() {
        System.out.println("setGhostLifePool");
        int ghostLifePool = 0;
        Level instance = null;
        instance.setGhostLifePool(ghostLifePool);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShape method, of class Level.
     */
    @Test
    public void testGetShape() {
        System.out.println("getShape");
        Level instance = null;
        String expResult = "";
        String result = instance.getShape();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setShape method, of class Level.
     */
    @Test
    public void testSetShape() {
        System.out.println("setShape");
        String shape = "";
        Level instance = null;
        instance.setShape(shape);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidth method, of class Level.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Level instance = null;
        int expResult = 0;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWidth method, of class Level.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        int width = 0;
        Level instance = null;
        instance.setWidth(width);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class Level.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Level instance = null;
        int expResult = 0;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHeight method, of class Level.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        int height = 0;
        Level instance = null;
        instance.setHeight(height);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKeyLocation method, of class Level.
     */
    @Test
    public void testGetKeyLocation() {
        System.out.println("getKeyLocation");
        Level instance = null;
        Point2D expResult = null;
        Point2D result = instance.getKeyLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKeyLocation method, of class Level.
     */
    @Test
    public void testSetKeyLocation() {
        System.out.println("setKeyLocation");
        Point2D keyLocation = null;
        Level instance = null;
        instance.setKeyLocation(keyLocation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDoorLocation method, of class Level.
     */
    @Test
    public void testGetDoorLocation() {
        System.out.println("getDoorLocation");
        Level instance = null;
        Point2D expResult = null;
        Point2D result = instance.getDoorLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDoorLocation method, of class Level.
     */
    @Test
    public void testSetDoorLocation() {
        System.out.println("setDoorLocation");
        Point2D doorLocation = null;
        Level instance = null;
        instance.setDoorLocation(doorLocation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTheme method, of class Level.
     */
    @Test
    public void testGetTheme() {
        System.out.println("getTheme");
        Level instance = null;
        String expResult = "";
        String result = instance.getTheme();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTheme method, of class Level.
     */
    @Test
    public void testSetTheme() {
        System.out.println("setTheme");
        String theme = "";
        Level instance = null;
        instance.setTheme(theme);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateKeyLocation method, of class Level.
     */
    @Test
    public void testGenerateKeyLocation() {
        System.out.println("generateKeyLocation");
        Level instance = null;
        instance.generateKeyLocation();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateDoorLocation method, of class Level.
     */
    @Test
    public void testGenerateDoorLocation() {
        System.out.println("generateDoorLocation");
        Level instance = null;
        instance.generateDoorLocation();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateLayout method, of class Level.
     */
    @Test
    public void testGenerateLayout() {
        System.out.println("generateLayout");
        Level instance = null;
        instance.generateLayout();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of placeObstacles method, of class Level.
     */
    @Test
    public void testPlaceObstacles() {
        System.out.println("placeObstacles");
        Level instance = null;
        instance.placeObstacles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
