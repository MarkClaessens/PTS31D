package nl.haunted;

import nl.haunted.Ghost;
import nl.haunted.Player;
import nl.haunted.Game;
import nl.haunted.IPlayer;
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
    List<IPlayer> players;

    public GhostTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        players = new ArrayList<>();
        players.add(new Player("testPlayer1", "192.68.132.13"));
        players.add(new Player("testPlayer2", "192.69.133.14"));
        game = new Game(players, 3, "234.56.78.90");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Ghost constructor, of class Ghost.
     */
    @Test
    public void testMakeGhost() {
        Ghost ghost1 = new Ghost(spawnPosition, game, (Player) players.get(0));
        assertEquals("spawnPosition wasn't set correctly at initializing", ghost1.getSpawnPosition(), spawnPosition);
        assertEquals("game wasn't set correctly at initializing", ghost1.getGame(), game);
    }

    /**
     * Test of isVulnerable method, of class Ghost.
     */
    @Test
    public void testIsVulnerable() {
        Ghost ghost = new Ghost(spawnPosition, game, (Player) players.get(0));
        assertTrue("Ghost is not vulnerable at initializing", ghost.isVulnerable());
    }

    /**
     * Test of setVulnerable method, of class Ghost.
     */
    @Test
    public void testSetVulnerable() {
        Ghost ghost = new Ghost(spawnPosition, game, (Player) players.get(0));
        ghost.setVulnerable(false);
        assertFalse("Ghost vulnerable was not correctly set to false", ghost.isVulnerable());
    }

    /**
     * Test of possess method, of class Ghost.
     */
    @Test
    public void testPossess() {
        Ghost ghost = new Ghost(spawnPosition, game, (Player) players.get(0));
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
        Ghost ghost = new Ghost(spawnPosition, game, (Player) players.get(0));
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
        Ghost ghost = new Ghost(spawnPosition, game, (Player) players.get(0));
        ghost.vanish();
        Calendar previousTimeOfDeath = ghost.getTimeOfDeath();
        ghost.possess();
        Calendar newTimeOfDeath = ghost.getTimeOfDeath();
        assertNotSame("The timeOfDeath didn't change after possing", previousTimeOfDeath, newTimeOfDeath);
    }

}
