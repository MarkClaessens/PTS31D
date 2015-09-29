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
     * Test of getFlashlightRange method, of class Human.
     */
    @Test
    public void testGetFlashlightRange() {
        System.out.println("getFlashlightRange");
        Human instance = new Human();
        int expResult = 0;
        int result = instance.getFlashlightRange();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFlashlightRange method, of class Human.
     */
    @Test
    public void testSetFlashlightRange() {
        System.out.println("setFlashlightRange");
        int flashlightRange = 0;
        Human instance = new Human();
        instance.setFlashlightRange(flashlightRange);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFlashlightAngle method, of class Human.
     */
    @Test
    public void testGetFlashlightAngle() {
        System.out.println("getFlashlightAngle");
        Human instance = new Human();
        int expResult = 0;
        int result = instance.getFlashlightAngle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFlashlightAngle method, of class Human.
     */
    @Test
    public void testSetFlashlightAngle() {
        System.out.println("setFlashlightAngle");
        int flashlightAngle = 0;
        Human instance = new Human();
        instance.setFlashlightAngle(flashlightAngle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isHasKey method, of class Human.
     */
    @Test
    public void testIsHasKey() {
        System.out.println("isHasKey");
        Human instance = new Human();
        boolean expResult = false;
        boolean result = instance.isHasKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHasKey method, of class Human.
     */
    @Test
    public void testSetHasKey() {
        System.out.println("setHasKey");
        boolean hasKey = false;
        Human instance = new Human();
        instance.setHasKey(hasKey);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRotationSpeed method, of class Human.
     */
    @Test
    public void testGetRotationSpeed() {
        System.out.println("getRotationSpeed");
        Human instance = new Human();
        Double expResult = null;
        Double result = instance.getRotationSpeed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRotationSpeed method, of class Human.
     */
    @Test
    public void testSetRotationSpeed() {
        System.out.println("setRotationSpeed");
        Double rotationSpeed = null;
        Human instance = new Human();
        instance.setRotationSpeed(rotationSpeed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotateFlashlight method, of class Human.
     */
    @Test
    public void testRotateFlashlight() {
        System.out.println("rotateFlashlight");
        Point2D mousePosition = null;
        Human instance = new Human();
        instance.rotateFlashlight(mousePosition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of transferHuman method, of class Human.
     */
    @Test
    public void testTransferHuman() {
        System.out.println("transferHuman");
        Human instance = new Human();
        instance.transferHuman();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pickUpKey method, of class Human.
     */
    @Test
    public void testPickUpKey() {
        System.out.println("pickUpKey");
        Human instance = new Human();
        instance.pickUpKey();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enterDoor method, of class Human.
     */
    @Test
    public void testEnterDoor() {
        System.out.println("enterDoor");
        Human instance = new Human();
        instance.enterDoor();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
