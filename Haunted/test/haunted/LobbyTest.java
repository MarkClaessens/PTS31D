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
public class LobbyTest {
    
    Player player;
    Lobby lobby;
    
    public LobbyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        player = new Player("Tom");
        lobby = new Lobby();
    }

    @After
    public void tearDown() {
    }
    /**
     * Test of changePlayerName method, of class Lobby.
     */
    @Test
    public void testChangePlayerName() {
        System.out.println("changePlayerName");        
        lobby.changePlayerName("Henkie");
        assertEquals("Henkie",player.getName());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exit method, of class Lobby.
     */
    @Test
    public void testExit() {
        System.out.println("exit");        
        lobby.exit();        
    }

    /**
     * Test of createGameLobby method, of class Lobby.
     */
    @Test
    public void testCreateGameLobby() {
        System.out.println("createGameLobby");
        String name = "powerhole";
        String password = "kaassoufle";        
        lobby.createGameLobby(name, password);
    }

}
