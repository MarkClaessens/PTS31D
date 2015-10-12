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
     * Test of Ghost constructor, of class Ghost.
     */
    @Test
    public void testMakeGhost(){
        Character ghost1 = new Ghost("blue");
    }
    
    /**
     * Test of isVulnerable method, of class Ghost.
     */
    @Test
    public void testIsVulnerable() {
        System.out.println("isVulnerable");
        
        Ghost ghost = new Ghost("blue");
        assertTrue("Ghost is not vulnerable", ghost.isVulnerable());
    }

    /**
     * Test of setVulnerable method, of class Ghost.
     */
    @Test
    public void testSetVulnerable() {
        System.out.println("setVulnerable");
        Ghost ghost = new Ghost("blue");
        ghost.setVulnerable(false);
        assertFalse("Ghost is vulnerable", ghost.isVulnerable());
    }

    /**
     * Test of possess method, of class Ghost.
     */
    @Test
    public void testPossess() {
        System.out.println("possess");
        Ghost ghost = new Ghost("blue");
        ghost.possess();
        // TODO review the generated test code and remove the default call to fail.
        fail("the ghost has not been possessed to human");
    }

    /**
     * Test of changeAppearance method, of class Ghost.
     */
    @Test
    public void testChangeAppearance() {
        System.out.println("changeAppearance");
        Ghost ghost = new Ghost("blue");
        ghost.changeAppearance();
        assertTrue("ghost is still vulnerable", ghost.isVulnerable());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vanish method, of class Ghost.
     */
    @Test
    public void testVanish() {
        System.out.println("vanish");
        Ghost ghost = new Ghost("blue");
        ghost.vanish();
        assertTrue("ghost is still vulnerable", ghost.isVulnerable());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
