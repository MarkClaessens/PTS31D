/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Mike Evers
 */
public class GameTest {

    public GameTest() {
    }
    List<Player> players;
    Player player1;
    Player player2;
    Game game;
    int floors;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        player1 = new Player("John Doe");
        player2 = new Player("Lucky Luck");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        floors = 5;

        game = new Game(players, floors);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGameConstructor() {
        /**
         * creates the game
         */
        Assert.assertEquals("Expected another list of players, check the constructor.", players, game.getPlayers());
        Assert.assertEquals("Expected another amount of floors, check the constructor,", floors, game.getFloorAmount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGameConstructorPlayers() {
        /**
         * @param players List can not be empty.
         */
        players = new ArrayList<>();
        game = new Game(players, floors);
        Assert.fail("Constructor doesn't throw exception when you create a game without players.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGameConstructorFloors() {
        /**
         * @param floors Cannot be 0.
         */
        floors = 0;
        game = new Game(players, floors);
        Assert.fail("Constructor doesn't throw excetion when you create a game with floorAmount set to 0");
    }

    @Test
    public void testNextLevel() {
        /**
         * sets the game to the next level Increases the currentRound with 1,
         * generates a new level object and calls the startRound method.
         */

        // First we create a level that is used bij floor 0.
        Level oldLevel = game.getCurrentLevel();

        game.nextLevel();
        Assert.assertEquals("nextLevel() did not increase the currentRound with 1", 0, game.getCurrentRound());
        Assert.assertNotSame("oldLevel is the same level after calling nextLevel() method", oldLevel, game.getCurrentLevel());

        // Let's test if the startRound has been called.
        Assert.assertEquals("nextLevel() method didn't call startRound() method, because game isn't running",
                true, game.getIsRunning());
    }

    @Test
    public void testStartRound() {
        /**
         * starts the next round at the current floor.
         */

        game.startRound();
        Assert.assertEquals("startRound() method isn't correct because the isRunnning state is still false",
                true, game.getIsRunning());

    }

    @Test
    public void testEndRound() {
        /**
         * ends the current round, after this the next level will be generated
         * and the current round will increase with one.
         */
        game.startRound();
        game.endRound();
        Assert.assertEquals("endRound() method isn't ccrrect because the isRunning state is still true",
                false, game.getIsRunning());
    }

    @Test
    public void testEndGame() {
        /**
         * Will be called when the game is done. There might be a victory
         * screen.
         */
        try {
            game.nextLevel(); // currentRound increases to 0. 
            game.endRound(); // isRunning state will be set to false
            game.endGame(); // Illegal call because the method was called before the game reached the last floor.
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testEndGameIsRunning() {
        /**
         * isRunning has to be false
         */
        int floors2 = 2;
        Game game2 = new Game(players, floors);
        game2.nextLevel(); // currentRound increases to 0
        game2.endRound(); // isRunning state will be set to false
        game2.nextLevel(); // currentRound increases to 1 (Legal for endGame() method)
        try {
            game.endGame(); // called on isRunning = true - which will throw an exception
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void testLeaveGame() {
        /**
         * player leaves the game First iteration doesn't need a parameter
         * because we only have two players.
         */
        game.leaveGame();
        Assert.assertEquals("leaveGame() method isn't correct because after calling this methode the isRunning "
                + "state should be false", false, game.getIsRunning());

    }

    @Test
    public void testTick() {
        /**
         * Engine of the game. Makes al calculations for the game and checks if
         * there are any changes to the game state.
         */

        // I DON'T KNOW HOW TO TEST THIS. 
        game.tick();
        Timer timer = game.getTickTimer();

    }
}
