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
     * Test of joinGameLobby method, of class Lobby.
     */
    @Test
    public void testJoinGameLobby() {
        System.out.println("joinGameLobby");        
        GameLobby gamelobby = new GameLobby("boe",null, player);        
        lobby.joinGameLobby(gamelobby);
        //optioneel om toch nog true of false terug te geven als room vol is en je niet meer kunt joinen.
        // TODO revie test case is a prototype.");w the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of refresh method, of class Lobby.
     */
    @Test
    public void testRefresh() {
        System.out.println("refresh");        
        lobby.refresh();
        // TODO review the generated test code and remove the default call to fail.
        
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
