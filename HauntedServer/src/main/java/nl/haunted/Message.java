/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike Evers
 */
public class Message {

    private Date timestamp;
    private String text;
    private boolean visibleForEveryone = true;
    private IPlayer player;

    /**
     * Constructs the message object. This object represents a message inside
     * the chatbox.
     *
     * @param text the text that represents the message. text is not null, empty
     * or only containing whitespaces.
     * @param player the player that sends the message. player is not null.
     * @param visibleForEveryone
     * @exception IllegalArgumentException thrown when the text is null, empty
     * or only containing white spaces and when de player is null.
     * @throws java.rmi.RemoteException
     */
    public Message(String text, IPlayer player, boolean visibleForEveryone) throws IllegalArgumentException, RemoteException {
        if (text == null || player == null || text.isEmpty() || text.trim().length() == 0) {
            throw new IllegalArgumentException("The message was not created, "
                    + "check if the text and player are not null and if the text is not empty "
                    + "or only containing white spaces.");
        }

        this.text = text;
        this.player = player;
        this.timestamp = new Date();
        this.visibleForEveryone = visibleForEveryone;

        // If the player's character is a ghost then the message is not visibible for the human
    }
    
    /**
     * this function returns the text of the message.
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * this fumction returns the player that wrote the message.
     * @return
     */
    public IPlayer getPlayer() {
        return player;
    }

    /**
     * this function returns a boolean that indicates of this message is visible for the human player.
     * @return
     */
    public boolean isVisibleForEveryone() {
        return visibleForEveryone;
    }

    /**
     * Creates a string of the message object. Example -->
     * [15:34][Mike23HeroJeWeetZelf]: Hoi allemaal, veel succes!
     *
     * @return the string that represents a chat message.
     */
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String timeToString = formatter.format(timestamp);

        StringBuilder sb = new StringBuilder("[" + timeToString);
        sb.append("][");
        try {
            sb.append(player.getName());
        } catch (RemoteException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
        sb.append("]: ");
        sb.append(text);

        return sb.toString();
    }
}
