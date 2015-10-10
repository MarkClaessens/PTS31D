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
        Character human = new Human("blue");
    }

    /**
     * Test of getFlashlightRange method, of class Human.
     */
    @Test
    public void testGetFlashlightRange() {
        System.out.println("getFlashlightRange");
        Human human = new Human("blue");
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
        Human human = new Human("blue");
        
        human.setFlashlightRange(flashlightRange);
        assertEquals("the flashlight range is not changed", 10, human.getFlashlightRange());
    }

    /**
     * Test of getFlashlightAngle method, of class Human.
     */
    @Test
    public void testGetFlashlightAngle() {
        System.out.println("getFlashlightAngle");
        Human human = new Human("blue");
        
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
        Human human = new Human("blue");
        
        human.setFlashlightAngle(flashlightAngle);
        assertEquals("the flashlight angle isn't changed", 30, human.getFlashlightAngle());
    }

    /**
     * Test of isHasKey method, of class Human.
     */
    @Test
    public void testIsHasKey() {
        System.out.println("isHasKey");
        Human human = new Human("blue");
        
        assertFalse("human has key", human.isHasKey());
    }

    /**
     * Test of setHasKey method, of class Human.
     */
    @Test
    public void testSetHasKey() {
        System.out.println("setHasKey");
        boolean hasKey = true;
        Human human = new Human("blue");
        
        human.setHasKey(hasKey);
        assertTrue("human has key isn't changed", human.isHasKey());
    }

    /**
     * Test of getRotationSpeed method, of class Human.
     */
    @Test
    public void testGetRotationSpeed() {
        System.out.println("getRotationSpeed");
        Human human = new Human("blue");
        
        Double expResult = 23.4;
        Double result = human.getRotationSpeed();
        assertEquals("the result is not equal to 23.4", expResult, result);
    }

    /**
     * Test of setRotationSpeed method, of class Human.
     */
    @Test
    public void testSetRotationSpeed() {
        System.out.println("setRotationSpeed");
        Double rotationSpeed = 28.1;
        Human human = new Human("blue");
        
        human.setRotationSpeed(rotationSpeed);
        assertEquals("rotation speed isn't changed", String.valueOf(28.1), String.valueOf(human.getRotationSpeed()));
    }

    /**
     * Test of rotateFlashlight method, of class Human.
     */
    @Test
    public void testRotateFlashlight() {
        System.out.println("rotateFlashlight");
        Point2D mousePosition = null;
        mousePosition.setLocation(12.0, 24.0);
        Human human = new Human("blue");
        
        human.rotateFlashlight(mousePosition);
        //POSITIE WORDT NIET OPGESLAGEN !?
    }

    /**
     * Test of transferHuman method, of class Human.
     */
    @Test
    public void testTransferHuman() {
        System.out.println("transferHuman");
        Human human = new Human("blue");
        
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
        Human human = new Human("blue");
        
        human.pickUpKey();
        assertTrue("human has not the key", human.isHasKey());
    }

    /**
     * Test of enterDoor method, of class Human.
     */
    @Test
    public void testEnterDoor() {
        System.out.println("enterDoor");
        Human human = new Human("blue");
        
        human.pickUpKey();
        human.enterDoor();
    }

}
