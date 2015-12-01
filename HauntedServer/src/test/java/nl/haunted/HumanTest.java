/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import nl.haunted.Player;
import nl.haunted.Game;
import nl.haunted.Human;
import java.awt.geom.Point2D;
import java.io.IOException;
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
 * @author jvdwi
 */
public class HumanTest {

    private Human human;
    private Point2D spawnPosition = new Point2D.Double(300, 500);
    private Game game;

    public HumanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        List<IPlayer> players = new ArrayList<>();
        players.add(new Player("testPlayer1", "192.68.132.13"));
        players.add(new Player("testPlayer2", "192.69.133.14"));
        game = new Game(players, 3, "234.56.78.90");
        human = new Human(spawnPosition, game);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    /**
     * Test for adding a human
     */
    @Test
    public void testAddHuman() {
        Human h = new Human(spawnPosition, game);
    }

    /**
     * test for picking up key
     */
    @Test
    public void testPickupKey() {
        assertFalse("Human shouldn't have picked up the key yet", human.hasKey());
        human.pickUpKey();
        assertTrue("Human should have picked up the key now", human.hasKey());
    }

    /**
     * test for entering the door
     */
    @Test
    public void enterDoor() {
        human.enterDoor();
        human.pickUpKey();
        human.enterDoor();
    }

    /**
     * test if human interacts with something
     */
    @Test
    public void testCheckInteract() {
        assertFalse("Human shouldn't interact with something", human.checkInteract());
    }

    /**
     * test for getting flashlight polygon
     */
    @Test
    public void testGetFlashlightPolygon() {
        assertEquals("should return a polygon", new int[]{2, 2}, human.getFlashlightPolygon());
    }
}
