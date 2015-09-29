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
        GameLobby lobby = null;
        Lobby instance = new Lobby();
        instance.joinGameLobby(lobby);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refresh method, of class Lobby.
     */
    @Test
    public void testRefresh() {
        System.out.println("refresh");
        Lobby instance = new Lobby();
        instance.refresh();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changePlayerName method, of class Lobby.
     */
    @Test
    public void testChangePlayerName() {
        System.out.println("changePlayerName");
        String name = "";
        Lobby instance = new Lobby();
        instance.changePlayerName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exit method, of class Lobby.
     */
    @Test
    public void testExit() {
        System.out.println("exit");
        Lobby instance = new Lobby();
        instance.exit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createGameLobby method, of class Lobby.
     */
    @Test
    public void testCreateGameLobby() {
        System.out.println("createGameLobby");
        String name = "";
        String password = "";
        Lobby instance = new Lobby();
        instance.createGameLobby(name, password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
