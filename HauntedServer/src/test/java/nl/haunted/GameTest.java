/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class GameTest {

    Game game;

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        /*  try {
         List<IPlayer> players = new ArrayList<>();
         players.add(new Player("mike", "127.0.0.1"));
         players.add(new Player("cyrill", "127.0.0.1"));
         players.add(new Player("tom", "127.0.0.1"));
            
         Lobby lobby = new Lobby();
         GameLobby gamelobby = new GameLobby("hoi", null, players.get(0), 5, 5, lobby);
         game = new Game(players, 5, "5", (IGameLobby)gamelobby);
         } catch (IOException ex) {
         Logger.getLogger(GameTest.class.getName()).log(Level.SEVERE, null, ex);
         }*/
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test() {
        /*   Point2D point = game.pickGhostSpawnPoint(true);
         assertNotNull(point);*/
    }
}
