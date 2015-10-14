/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.sql.Time;
import java.time.LocalTime;
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
public class MessageTest {

    Time timeStamp;
    Player player;
    Message bericht;

    public MessageTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        timeStamp = new Time(12, 12, 12);
        player = new Player("Tom");
        bericht = new Message("hallo", player);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTimeStamp method, of class Message.
     */
    @Test
    public void testGetTimeStamp() {
        System.out.println("getTimeStamp");        
        assertEquals(Time.valueOf(LocalTime.MIN), bericht.getTimeStamp());
        // TODO review the generated test code and remove the default call to fail.

    }   
    

    /**
     * Test of getText method, of class Message.
     */
    @Test
    public void testGetText() {
        System.out.println("getText");
        assertEquals("hallo", bericht.getText());
    }

    
    

}
