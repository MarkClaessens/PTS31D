/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
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

    Game game;
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
        List<Player> players = new ArrayList<>();
        players.add(new Player("testPlayer1"));
        players.add(new Player("testPlayer2"));
        
        game = new Game(players, 3);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Ghost constructor, of class Ghost.
     */
    @Test
    public void testMakeGhost() {                
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Ghost ghost1 = new Ghost(spawnPosition, Color.RED, "redGhost", game);
    }

    /**
     * Test of isVulnerable method, of class Ghost.
     */
    @Test
    public void testIsVulnerable() {
        System.out.println("isVulnerable");

        Point2D spawnPosition = new Point2D.Double(300, 500);
        Ghost ghost = new Ghost(spawnPosition, Color.RED, "redGhost", game);
        assertTrue("Ghost is not vulnerable at initiliazing", ghost.isVulnerable());
    }

    /**
     * Test of setVulnerable method, of class Ghost.
     */
    @Test
    public void testSetVulnerable() {
        System.out.println("setVulnerable");
        
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Ghost ghost = new Ghost(spawnPosition, Color.RED, "redGhost", game);
        ghost.setVulnerable(false);
        assertFalse("Ghost vulnerable was not correctly set to false", ghost.isVulnerable());
    }

    /**
     * Test of possess method, of class Ghost.
     */
    @Test
    public void testPossess() {
        System.out.println("possess");
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Ghost ghost = new Ghost(spawnPosition, Color.RED, "redGhost", game);
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
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Ghost ghost = new Ghost(spawnPosition, Color.RED, "redGhost", game);
        ghost.changeAppearance();
        assertTrue("ghost is still vulnerable", ghost.isVulnerable());
    }

    /**
     * Test of vanish method, of class Ghost.
     */
    @Test
    public void testVanish() {
        System.out.println("vanish");
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Ghost ghost = new Ghost(spawnPosition, Color.RED, "redGhost", game);
        ghost.vanish();
        assertTrue("ghost is still vulnerable", ghost.isVulnerable());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
