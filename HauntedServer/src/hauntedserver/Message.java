/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jvdwi
 */
public class Message {
    private Date timestamp;
    private String text;
    private boolean allChat;
    private Player player;
    
    /**
     * Constructs the message object. 
     * This object represents a message inside the chatbox.
     * @param text the text that represents the message. 
     * text is not null, empty or only containing white spaces.
     * @param player the player that sends the message.
     * player is not null.
     * @exception IllegalArgumentException thrown when the text is null, empty or only containing white spaces  
     * and when de player is null.
     */
    public Message(String text, Player player) throws IllegalArgumentException{
        if(text == null || player == null || text.isEmpty() || text.trim().length() == 0){
            throw new IllegalArgumentException("The message was not created, "
                    + "check if the text and player are not null and if the text is not empty "
                    + "or only containing white spaces.");
        }
        
        this.text = text;
        this.player = player;
        this.timestamp = new Date();
    }
    
    /**
     * Creates a string of the message object.
     * Example --> 15:34 - Mike23HeroJeWeetZelf : Hoi allemaal, veel succes!
     * @return the string that represents a chat message. 
     */
    public String toString(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String timeToString = formatter.format(timestamp);
        
        StringBuilder sb = new StringBuilder(timeToString);
        sb.append(" - ");
        sb.append(player.getName());
        sb.append(" : ");
        sb.append(text);
        
        return sb.toString();
    }
}
