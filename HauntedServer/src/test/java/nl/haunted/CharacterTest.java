/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import nl.haunted.Player;
import nl.haunted.Ghost;
import nl.haunted.Game;
import nl.haunted.DirectionType;
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
public class CharacterTest {

    private Human human;
    private Ghost ghost;
    private Point2D spawnPosition = new Point2D.Double(300, 500);
    private Game game;

    public CharacterTest() {
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
        game = new Game(players, 3);
        human = new Human(spawnPosition, game);
        ghost = new Ghost(spawnPosition, game, (Player) players.get(0));

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
     * Tests for moving character
     */
    @Test
    public void moveTest() {
        human.move(DirectionType.UP);
        human.move(DirectionType.DOWN);
        human.move(DirectionType.LEFT);
        human.move(DirectionType.RIGHT);
        ghost.move(DirectionType.UP);
        ghost.move(DirectionType.DOWN);
        ghost.move(DirectionType.LEFT);
        ghost.move(DirectionType.RIGHT);
    }

    //Verder zou ik niet weten of je de rest ook fatsoenlijk kan testen...
}
