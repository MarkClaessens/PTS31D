/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    String[] ghoSpritesUp = new String[]{"ghostRedUp1.png", "ghostRedUp2.png", "ghostRedUp3.png"};
    String[] ghoSpritesDown = new String[]{"ghostRedDown1.png", "ghostRedDown2.png", "ghostRedDown3.png"};
    String[] ghoSpritesLeft = new String[]{"ghostRedLeft1.png", "ghostRedLeft2.png", "ghostRedLeft3.png"};
    String[] ghoSpritesRight = new String[]{"ghostRedRight1.png", "ghostRedRight2.png", "ghostRedRight3.png"};
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

        try {
            game = new Game(players, 3);
        } catch (IOException ex) {
            Logger.getLogger(GhostTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Ghost ghost1 = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
    }

    /**
     * Test of isVulnerable method, of class Ghost.
     */
    @Test
    public void testIsVulnerable() {
        System.out.println("isVulnerable");

        Point2D spawnPosition = new Point2D.Double(300, 500);
        Ghost ghost = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
        assertTrue("Ghost is not vulnerable at initiliazing", ghost.isVulnerable());
    }

    /**
     * Test of setVulnerable method, of class Ghost.
     */
    @Test
    public void testSetVulnerable() {
        System.out.println("setVulnerable");

        Point2D spawnPosition = new Point2D.Double(300, 500);
        Ghost ghost = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
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
        Ghost ghost = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
        ghost.possess();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of changeAppearance method, of class Ghost.
     */
    @Test
    public void testChangeAppearance() {
        System.out.println("changeAppearance");
        Point2D spawnPosition = new Point2D.Double(300, 500);
        Ghost ghost = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
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
        Ghost ghost = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
        ghost.vanish();
        assertTrue("ghost is still vulnerable", ghost.isVulnerable());
        // TODO review the generated test code and remove the default call to fail.
    }

}
