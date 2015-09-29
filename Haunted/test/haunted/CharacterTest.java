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
public class CharacterTest {

    public CharacterTest() {
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
     * Test of getPosition method, of class Character.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Character instance = new CharacterImpl();
        Point2D expResult = null;
        Point2D result = instance.getPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosition method, of class Character.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Point2D position = null;
        Character instance = new CharacterImpl();
        instance.setPosition(position);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColor method, of class Character.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Character instance = new CharacterImpl();
        String expResult = "";
        String result = instance.getColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColor method, of class Character.
     */
    @Test
    public void testSetColor() {
        System.out.println("setColor");
        String color = "";
        Character instance = new CharacterImpl();
        instance.setColor(color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSprite method, of class Character.
     */
    @Test
    public void testGetSprite() {
        System.out.println("getSprite");
        Character instance = new CharacterImpl();
        String expResult = "";
        String result = instance.getSprite();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSprite method, of class Character.
     */
    @Test
    public void testSetSprite() {
        System.out.println("setSprite");
        String sprite = "";
        Character instance = new CharacterImpl();
        instance.setSprite(sprite);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMovementSpeed method, of class Character.
     */
    @Test
    public void testGetMovementSpeed() {
        System.out.println("getMovementSpeed");
        Character instance = new CharacterImpl();
        Double expResult = null;
        Double result = instance.getMovementSpeed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMovementSpeed method, of class Character.
     */
    @Test
    public void testSetMovementSpeed() {
        System.out.println("setMovementSpeed");
        Double movementSpeed = null;
        Character instance = new CharacterImpl();
        instance.setMovementSpeed(movementSpeed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of move method, of class Character.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        String direction = "";
        Character instance = new CharacterImpl();
        instance.move(direction);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CharacterImpl extends Character {
    }

}
