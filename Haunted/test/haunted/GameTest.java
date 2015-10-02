/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.util.ArrayList;
import java.util.List;
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
    public void testGameConstructor(){
    /**
     * creates the game
     */
        Assert.assertEquals("Expected another list of players, check the constructor.", players, game.getPlayers());
        Assert.assertEquals("Expected another amount of floors, check the constructor,", floors, game.getFloorAmount());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGameConstructorPlayers(){
    /**
     * @param players List can not be empty.
     */
        players = new ArrayList<>();
        game = new Game(players, floors);
        Assert.fail("Constructor doesn't throw exception when you create a game without players.");
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testGameConstructorFloors(){
    /**
     * @param floors Cannot be 0.
     */
        floors = 0;
        game = new Game(players, floors);
        Assert.fail("Constructor doesn't throw excetion when you create a game with floorAmount set to 0");
    }
    
    @Test
    public void testNextLevel(){
    /**
     * sets the game to the next level
     */
        
        // First we create a level that is used bij floor 1.
        Level floor1 = new Level(1);
        
        // Could not finish this method cause specification is bad!
    }
    
    @Test 
    public void testNextLevelFloor(){
    /**
     * @param floor 
     */
        
        // Could not finish this method cause specification is bad!
    }
    
    @Test
    public void testStartRound(){
        // I think we need a state attribute for the game.. whether the game is in a round or not.
    }
    
    @Test
    public void testEndRound(){
        // I think we need a state attribute for the game.. whether the game is in a round or not.
    }
    
    @Test
    public void testEndGame(){
        // Ending a game is deleting the Game object (and so the Level object) and the GameLobby object.
        // We can use a destructor for deleting objects??
    }
    
    @Test
    public void testLeaveGame(){
    /**
     * player leaves the game
     * @param player the player who wants to leave the game.
     */
        
        // !! I added the param player in leaveGame method !! 
        
        players.remove(player1);
        game.leaveGame(player1);
        Assert.assertEquals("Expected another list of players, leaveGame() fails", players, game.getPlayers());
    }
    
    @Test
    public void testTick(){
    /**
     * everything that needs to be checked every tick from the timer
     */
        
        // Could not finish this method cause specification is bad!
        // Damn how to test this?
    }

}
