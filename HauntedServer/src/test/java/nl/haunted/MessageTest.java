package nl.haunted;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mike Evers
 */
public class MessageTest {

    Message message;
    Player player;
    String timeOfTestToString;

    public MessageTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws RemoteException {
        timeOfTestToString = new SimpleDateFormat("HH:mm").format(new Date());
        player = new Player("MikeEvers123", "94.219.136.19");
    //    message = new Message("this is a test", player);
    }

    @After
    public void tearDown() {
    }

    /**
     * Constructs the message object. This object represents a message inside
     * the chatbox.
     *
     * @param text the text that represents the message.
     * @param player the player that sends the message. player is not null.
     * @exception IllegalArgumentException thrown when the text is null, empty
     * or only containing whitespaces and when de player is null.
     */
    @Test
    public void testMessage() {
        assertEquals("The constructor doesn't set the text correctly", "this is a test", message.getText());
        assertEquals("The constructor doesn't set the player correctly", player, message.getPlayer());
    }

    /**
     * text is not null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTextNotNull() throws IllegalArgumentException, RemoteException {
    //    Message illegalMessage = new Message(null, player);
        fail("IllegalArgumentException not thrown when text is null");
    }

    /**
     * /**
     * text is not empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTextNotEmpty() throws IllegalArgumentException, RemoteException {
    //    Message illegalMessage = new Message("", player);
        fail("IllegalArgumentException not thrown when text is empty");
    }

    /**
     * /**
     * text doesn't only contain whitespaces
     *
     * @throws java.rmi.RemoteException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTextNotOnlyWhitespaces() throws IllegalArgumentException, RemoteException {
    //    Message illegalMessage = new Message("     ", player);
        fail("IllegalArgumentException not thrown when text only contains whitespaces");
    }

    /**
     * Creates a string of the message object. Example --> 15:34 -
     * Mike23HeroJeWeetZelf : Hoi allemaal, veel succes! returns the string that
     * represents a chat message.
     *
     * @throws java.rmi.RemoteException
     */
    @Test
    public void testToString() throws RemoteException {
        String expected = timeOfTestToString + " - " + message.getPlayer().getName() + " : " + message.getText();
        assertEquals("The message toString method returns something unexpected", expected, message.toString());
    }

}
