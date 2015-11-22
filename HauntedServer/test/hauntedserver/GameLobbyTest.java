/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mark
 */
public class GameLobbyTest {

    private GameLobby globby;
    private Player host;

    public GameLobbyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        host = new Player("host", "ipadres");
        globby = new GameLobby("lobby1", "pass1", host);
    }

    @After
    public void tearDown() {
    }

    
    @Test
    public void testGetName() {
        System.out.println("getName");
        GameLobby instance = globby;
        String expResult = "lobby1";
        String result = instance.getName();
        assertEquals(expResult, result);
    }
   
   

    /**
     * Test of getMaxPlayers method, of class GameLobby.
     */
    @Test
    public void testGetMaxPlayers() {
        System.out.println("getMaxPlayers");
        GameLobby instance = globby;
        int expResult = 4;
        int result = instance.getMaxPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFloorAmount method, of class GameLobby.
     */
    @Test
    public void testGetFloorAmount() {
        System.out.println("getFloorAmount");
        GameLobby instance = globby;
        int expResult = 4;
        int result = instance.getMaxFloors();
        assertEquals(expResult, result);
    }

    
   

    /**
     * Test of startGame method, of class GameLobby.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        GameLobby instance = globby;
        instance.startGame();
        boolean expResult = true;
        boolean result = true;
        assertEquals(expResult, result);
    }
   
    /**
     * Test of removePlayer method, of class GameLobby.
     */
    @Test
    public void testRemovePlayer() {
        System.out.println("removePlayer");
        GameLobby instance = globby;
        Player p1 = new Player("player1", "ipadres");
        globby.addPlayer(p1);
        instance.removePlayer(p1);
        boolean expResult = true;
        boolean result = true;
        for (Player P : instance.getPlayers()) {
            if (P.getName().equals(p1.getName())) {
                result = false;
            }
        }
        assertEquals(expResult, result);

    }

    /**
     * Test of sendMessage method, of class GameLobby.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        String message = "message1";
        GameLobby instance = globby;
        instance.sendMessage(message);
        boolean expResult = true;
        boolean result = false;        
        assertEquals(expResult, result);
    }

    /**
     * Test of addPlayer method, of class GameLobby.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");
        Player player = new Player("player2", "ipadres");
        GameLobby instance = globby;
        instance.addPlayer(player);
        boolean expResult = true;
        boolean result = false;
        for (Player P : instance.getPlayers()) {
            if (P.equals(player)) {
                result = true;
            }
        }
        assertEquals(expResult, result);
    }
}
