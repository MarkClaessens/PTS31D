/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;


import java.rmi.RemoteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mark Claessens
 */
public class PlayerTest {   
       
    Player player1;
    Player player2;
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws RemoteException {
        player1 = new Player("Mark","ipadres");
        player2 = new Player("Kees","ipadres");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetName() throws RemoteException {        
        String expResultaat = "Mark";
        String resultaat = player1.getName();
        assertEquals(expResultaat, resultaat);
        expResultaat = "Kees";
        resultaat = player2.getName();
        assertEquals(expResultaat, resultaat);
    }

    
    @Test
    public void testGetReady() throws RemoteException {      
        Boolean expResultaat = false;        
        assertEquals(expResultaat, player1.getReady());
        assertEquals(expResultaat, player2.getReady());    
    }
    
    @Test
    public void testSetReady() throws RemoteException {        
        Boolean ready = true;        
        player1.setReady(ready);
        assertEquals(ready, player1.getReady());
        assertEquals(false, player2.getReady());
    }
    
    @Test
    public void testgetCharacter() {        
        //geen idee hoe ik een character kan setten deze kan ook niet getten
    }
    
     @Test
    public void testtoggleready() throws RemoteException {
        assertEquals(false, player1.getReady());
        player1.toggleReady();
        assertEquals(true, player1.getReady());
        player1.toggleReady();
        assertEquals(false, player1.getReady());
        assertEquals(false, player2.getReady());
        player2.toggleReady();
        assertEquals(true, player2.getReady());
        player2.toggleReady();
        assertEquals(false, player2.getReady());
    }
    
    @Test
    public void testinput() throws RemoteException {        
        player1.setInput(DirectionType.UP);
        player1.setInput(DirectionType.DOWN);
        player1.setInput(DirectionType.LEFT);
        player1.setInput(DirectionType.RIGHT);
        player2.setInput(DirectionType.UP);
        player2.setInput(DirectionType.DOWN);
        player2.setInput(DirectionType.LEFT);
        player2.setInput(DirectionType.RIGHT);
    }
}
