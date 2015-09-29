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
        GameLobby instance = null;
        Player expResult = null;
        Player result = instance.getHost();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHost method, of class GameLobby.
     */
    @Test
    public void testSetHost() {
        System.out.println("setHost");
        Player Host = null;
        GameLobby instance = null;
        instance.setHost(Host);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class GameLobby.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        GameLobby instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class GameLobby.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        GameLobby instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class GameLobby.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        GameLobby instance = null;
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class GameLobby.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        GameLobby instance = null;
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxPlayers method, of class GameLobby.
     */
    @Test
    public void testGetMaxPlayers() {
        System.out.println("getMaxPlayers");
        GameLobby instance = null;
        int expResult = 0;
        int result = instance.getMaxPlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaxPlayers method, of class GameLobby.
     */
    @Test
    public void testSetMaxPlayers() {
        System.out.println("setMaxPlayers");
        int maxPlayers = 0;
        GameLobby instance = null;
        instance.setMaxPlayers(maxPlayers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFloorAmount method, of class GameLobby.
     */
    @Test
    public void testGetFloorAmount() {
        System.out.println("getFloorAmount");
        GameLobby instance = null;
        int expResult = 0;
        int result = instance.getFloorAmount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFloorAmount method, of class GameLobby.
     */
    @Test
    public void testSetFloorAmount() {
        System.out.println("setFloorAmount");
        int floorAmount = 0;
        GameLobby instance = null;
        instance.setFloorAmount(floorAmount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startGame method, of class GameLobby.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        GameLobby instance = null;
        instance.startGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeReadyStatus method, of class GameLobby.
     */
    @Test
    public void testChangeReadyStatus() {
        System.out.println("changeReadyStatus");
        GameLobby instance = null;
        instance.changeReadyStatus();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePlayer method, of class GameLobby.
     */
    @Test
    public void testRemovePlayer() {
        System.out.println("removePlayer");
        GameLobby instance = null;
        instance.removePlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class GameLobby.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        String message = "";
        GameLobby instance = null;
        instance.sendMessage(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPlayer method, of class GameLobby.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");
        Player player = null;
        GameLobby instance = null;
        instance.addPlayer(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readyCheck method, of class GameLobby.
     */
    @Test
    public void testReadyCheck() {
        System.out.println("readyCheck");
        GameLobby instance = null;
        boolean expResult = false;
        boolean result = instance.readyCheck();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
