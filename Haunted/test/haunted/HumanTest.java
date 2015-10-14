/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.awt.Color;
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
public class HumanTest {

    public HumanTest() {
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
     * Test of making human in the constructor
     */
    @Test
    public void testMakeHuman() {
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Human human = new Human(spawnPosition, Color.BLUE, "humanSprite");
    }

    /**
     * Test of getFlashlightRange method, of class Human.
     */
    @Test
    public void testGetFlashlightRange() {
        System.out.println("getFlashlightRange");
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Human human = new Human(spawnPosition, Color.BLUE, "humanSprite");
        int expResult = 12;
        int result = human.getFlashlightRange();
        assertEquals("the result is not equal to 12", expResult, result);
    }

    /**
     * Test of setFlashlightRange method, of class Human.
     */
    @Test
    public void testSetFlashlightRange() {
        System.out.println("setFlashlightRange");
        int flashlightRange = 10;
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Human human = new Human(spawnPosition, Color.BLUE, "humanSprite");

        human.setFlashlightRange(flashlightRange);
        assertEquals("the flashlight range is not changed", 10, human.getFlashlightRange());
    }

    /**
     * Test of getFlashlightAngle method, of class Human.
     */
    @Test
    public void testGetFlashlightAngle() {
        System.out.println("getFlashlightAngle");
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Human human = new Human(spawnPosition, Color.BLUE, "humanSprite");

        int expResult = 45;
        int result = human.getFlashlightAngle();
        assertEquals("the result is not equal to 45", expResult, result);
    }

    /**
     * Test of setFlashlightAngle method, of class Human.
     */
    @Test
    public void testSetFlashlightAngle() {
        System.out.println("setFlashlightAngle");
        int flashlightAngle = 30;
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Human human = new Human(spawnPosition, Color.BLUE, "humanSprite");

        human.setFlashlightAngle(flashlightAngle);
        assertEquals("the flashlight angle isn't changed", 30, human.getFlashlightAngle());
    }

    /**
     * Test of isHasKey method, of class Human.
     */
    @Test
    public void testGetHasKey() {
        System.out.println("isHasKey");
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Human human = new Human(spawnPosition, Color.BLUE, "humanSprite");

        assertFalse("human has key", human.getHasKey());
    }

    /**
     * Test of setHasKey method, of class Human.
     */
    @Test
    public void testSetHasKey() {
        System.out.println("setHasKey");
        boolean hasKey = true;
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Human human = new Human(spawnPosition, Color.BLUE, "humanSprite");

        human.setHasKey(hasKey);
        assertTrue("human has key isn't changed", human.getHasKey());
    }

    /**
     * Test of transferHuman method, of class Human.
     */
    @Test
    public void testTransferHuman() {
        System.out.println("transferHuman");
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Human human = new Human(spawnPosition, Color.BLUE, "humanSprite");

        human.transferHuman();

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pickUpKey method, of class Human.
     */
    @Test
    public void testPickUpKey() {
        System.out.println("pickUpKey");
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Human human = new Human(spawnPosition, Color.BLUE, "humanSprite");

        human.pickUpKey();
        assertTrue("human has not the key", human.getHasKey());
    }

    /**
     * Test of enterDoor method, of class Human.
     */
    @Test
    public void testEnterDoor() {
        System.out.println("enterDoor");
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Human human = new Human(spawnPosition, Color.BLUE, "humanSprite");

        human.pickUpKey();
        human.enterDoor();
    }

}
