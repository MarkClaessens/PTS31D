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
        host = new Player("host");
        globby = new GameLobby("lobby1", "pass1", host);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getHost method, of class GameLobby.
     */
    @Test
    public void testGetHost() {
        System.out.println("getHost");
        GameLobby instance = globby;
        Player expResult = host;
        Player result = instance.getHost();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHost method, of class GameLobby.
     */
    @Test
    public void testSetHost() {
        System.out.println("setHost");
        Player Host = new Player("host2");
        GameLobby instance = globby;
        instance.setHost(Host);
        Player expResult = Host;
        Player result = instance.getHost();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class GameLobby.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        GameLobby instance = globby;
        String expResult = "lobby1";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class GameLobby.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "lobby2";
        GameLobby instance = globby;
        instance.setName(name);
        String expResult = "lobby2";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class GameLobby.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        GameLobby instance = globby;
        String expResult = "pass1";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class GameLobby.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "pass2";
        GameLobby instance = globby;
        instance.setPassword(password);
        String expResult = "pass2";
        String result = instance.getPassword();
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
        int result = instance.getMaxPlayers();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaxPlayers method, of class GameLobby.
     */
    @Test
    public void testSetMaxPlayers() {
        System.out.println("setMaxPlayers");
        int maxPlayers = 2;
        GameLobby instance = globby;
        instance.setMaxPlayers(maxPlayers);
        int expResult = 2;
        int result = instance.getMaxPlayers();
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
        int result = instance.getFloorAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFloorAmount method, of class GameLobby.
     */
    @Test
    public void testSetFloorAmount() {
        System.out.println("setFloorAmount");
        int floorAmount = 2;
        GameLobby instance = globby;
        instance.setFloorAmount(floorAmount);
        int expResult = 2;
        int result = instance.getFloorAmount();
        assertEquals(expResult, result);
    }

    /**
     * Test of startGame method, of class GameLobby.
     */
    @Test
    public void testStartGame() {
//        System.out.println("startGame");
//        GameLobby instance = globby;
//        instance.startGame();
//        boolean expResult = true;
//        //boolean result = instance.getTickTimerState();
//        assertEquals(expResult, result);
    }

    /**
     * Test of changeReadyStatus method, of class GameLobby.
     */
    @Test
    public void testChangeReadyStatus() {
        System.out.println("changeReadyStatus");
        GameLobby instance = globby;
        boolean expResult = !instance.getHost().getReady();
        instance.changeReadyStatus();
        boolean result = instance.getHost().getReady();
        assertEquals(expResult, result);
    }

    /**
     * Test of removePlayer method, of class GameLobby.
     */
    @Test
    public void testRemovePlayer() {
        System.out.println("removePlayer");
        GameLobby instance = globby;
        Player p1 = new Player("player1");
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
        instance.sendMessage(message, host);
        boolean expResult = true;
        boolean result = false;
        for (Message M : instance.getMessages()) {
            if (M.getText().equals(message)) {
                result = true;
            }
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of addPlayer method, of class GameLobby.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");
        Player player = new Player("player2");
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

    /**
     * Test of readyCheck method, of class GameLobby. result should be false
     */
    @Test
    public void testReadyCheckFalse() {
        System.out.println("readyCheckFalse");
        GameLobby instance = globby;
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        instance.addPlayer(p1);
        instance.addPlayer(p2);
        instance.changeReadyStatus();
        boolean expResult = false;
        boolean result = instance.readyCheck();
        assertEquals(expResult, result);
    }

    /**
     * Test of readyCheck method, of class GameLobby.
     */
    @Test
    public void testReadyCheckTrue() {
        System.out.println("readyCheckFalse");
        GameLobby instance = globby;
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        p1.setReady(true);
        p2.setReady(true);
        if (instance.getHost().getReady() == !true) {
            instance.changeReadyStatus();
        }
        instance.addPlayer(p1);
        instance.addPlayer(p2);
        boolean expResult = true;
        boolean result = instance.readyCheck();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTickTimerState method, of class GameLobby. should be false
     */
    @Test
    public void testGetTickTimerStateFalse() {
//        System.out.println("GetTickTimerState");
//        GameLobby instance = globby;
//        boolean expResult = false;
//        boolean result = instance.getTickTimerState();
//        assertEquals(expResult, result);
    }

    /**
     * Test of getTickTimerState method, of class GameLobby. should be true
     */
    @Test
    public void testGetTickTimerStateTrue() {
//        System.out.println("GetTickTimerState");
//        GameLobby instance = globby;
//        if (instance.getHost().getReady() == !true) {
//            instance.changeReadyStatus();
//        }
//        instance.startGame();
//        boolean expResult = true;
//        boolean result = instance.getTickTimerState();
//        assertEquals(expResult, result);
    }

}
