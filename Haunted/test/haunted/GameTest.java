/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.util.Timer;
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
public class GameTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFloorAmount method, of class Game.
     */
    @Test
    public void testGetFloorAmount() {
        System.out.println("getFloorAmount");
        Game instance = null;
        int expResult = 0;
        int result = instance.getFloorAmount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFloorAmount method, of class Game.
     */
    @Test
    public void testSetFloorAmount() {
        System.out.println("setFloorAmount");
        int floorAmount = 0;
        Game instance = null;
        instance.setFloorAmount(floorAmount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentRound method, of class Game.
     */
    @Test
    public void testGetCurrentRound() {
        System.out.println("getCurrentRound");
        Game instance = null;
        int expResult = 0;
        int result = instance.getCurrentRound();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentRound method, of class Game.
     */
    @Test
    public void testSetCurrentRound() {
        System.out.println("setCurrentRound");
        int currentRound = 0;
        Game instance = null;
        instance.setCurrentRound(currentRound);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentFloor method, of class Game.
     */
    @Test
    public void testGetCurrentFloor() {
        System.out.println("getCurrentFloor");
        Game instance = null;
        int expResult = 0;
        int result = instance.getCurrentFloor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentFloor method, of class Game.
     */
    @Test
    public void testSetCurrentFloor() {
        System.out.println("setCurrentFloor");
        int currentFloor = 0;
        Game instance = null;
        instance.setCurrentFloor(currentFloor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTickTimer method, of class Game.
     */
    @Test
    public void testGetTickTimer() {
        System.out.println("getTickTimer");
        Game instance = null;
        Timer expResult = null;
        Timer result = instance.getTickTimer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTickTimer method, of class Game.
     */
    @Test
    public void testSetTickTimer() {
        System.out.println("setTickTimer");
        Timer tickTimer = null;
        Game instance = null;
        instance.setTickTimer(tickTimer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextLevel method, of class Game.
     */
    @Test
    public void testNextLevel() {
        System.out.println("nextLevel");
        int floor = 0;
        Game instance = null;
        instance.nextLevel(floor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startRound method, of class Game.
     */
    @Test
    public void testStartRound() {
        System.out.println("startRound");
        Game instance = null;
        instance.startRound();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endRound method, of class Game.
     */
    @Test
    public void testEndRound() {
        System.out.println("endRound");
        Game instance = null;
        instance.endRound();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endGame method, of class Game.
     */
    @Test
    public void testEndGame() {
        System.out.println("endGame");
        Game instance = null;
        instance.endGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leaveGame method, of class Game.
     */
    @Test
    public void testLeaveGame() {
        System.out.println("leaveGame");
        Game instance = null;
        instance.leaveGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tick method, of class Game.
     */
    @Test
    public void testTick() {
        System.out.println("tick");
        Game instance = null;
        instance.tick();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
