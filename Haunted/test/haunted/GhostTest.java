/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

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
public class GhostTest {

    public GhostTest() {
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
     * Test of isVulnerable method, of class Ghost.
     */
    @Test
    public void testIsVulnerable() {
        System.out.println("isVulnerable");
        Ghost instance = new Ghost();
        boolean expResult = false;
        boolean result = instance.isVulnerable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVulnerable method, of class Ghost.
     */
    @Test
    public void testSetVulnerable() {
        System.out.println("setVulnerable");
        boolean vulnerable = false;
        Ghost instance = new Ghost();
        instance.setVulnerable(vulnerable);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of possess method, of class Ghost.
     */
    @Test
    public void testPossess() {
        System.out.println("possess");
        Ghost instance = new Ghost();
        instance.possess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeAppearance method, of class Ghost.
     */
    @Test
    public void testChangeAppearance() {
        System.out.println("changeAppearance");
        Ghost instance = new Ghost();
        instance.changeAppearance();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vanish method, of class Ghost.
     */
    @Test
    public void testVanish() {
        System.out.println("vanish");
        Ghost instance = new Ghost();
        instance.vanish();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
