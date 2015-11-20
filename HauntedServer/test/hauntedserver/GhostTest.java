package hauntedserver;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mike Evers
 */
public class GhostTest {

    Point2D spawnPosition = new Point2D.Double(300, 500);
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
        players.add(new Player("testPlayer1", "192.68.132.13"));
        players.add(new Player("testPlayer2", "192.69.133.14"));
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
        Ghost ghost1 = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
    }

    /**
     * Test of isVulnerable method, of class Ghost.
     */
    @Test
    public void testIsVulnerable() {
        Ghost ghost = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
        assertTrue("Ghost is not vulnerable at initiliazing", ghost.isVulnerable());
    }

    /**
     * Test of setVulnerable method, of class Ghost.
     */
    @Test
    public void testSetVulnerable() {
        Ghost ghost = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
        ghost.setVulnerable(false);
        assertFalse("Ghost vulnerable was not correctly set to false", ghost.isVulnerable());
    }

    /**
     * Test of possess method, of class Ghost.
     */
    @Test
    public void testPossess() {
        Ghost ghost = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
        // after possing the ghost will respawn, so we can test if the timeOfDeath is set
        Calendar previousTimeOfDeath = ghost.getTimeOfDeath();
        ghost.possess();
        Calendar newTimeOfDeath = ghost.getTimeOfDeath();
        assertNotSame("The timeOfDeath didn't change after possing", previousTimeOfDeath, newTimeOfDeath);              
    }

    /**
     * Test of changeAppearance method, of class Ghost.
     */
    @Test
    public void testChangeAppearance() {
        Ghost ghost = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
        ghost.changeAppearance();
        assertFalse("ghost is still vulnerable when it becomes a wall", ghost.isVulnerable());
        
        ghost.changeAppearance();
        assertTrue("ghost is not vulnerable when it becomes back a ghost", ghost.isVulnerable());
    }

    /**
     * Test of vanish method, of class Ghost.
     */
    @Test
    public void testVanish() {
        Ghost ghost = new Ghost(spawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, game);
        ghost.vanish();
        Calendar previousTimeOfDeath = ghost.getTimeOfDeath();
        ghost.possess();
        Calendar newTimeOfDeath = ghost.getTimeOfDeath();
        assertNotSame("The timeOfDeath didn't change after possing", previousTimeOfDeath, newTimeOfDeath); 
    }

}
