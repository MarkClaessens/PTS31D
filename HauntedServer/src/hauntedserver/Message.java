/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import java.util.Calendar;

/**
 *
 * @author jvdwi
 */
public class Message {
    private Calendar timestamp;
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
     */
    public Message(String text, Player player){
        this.text = text;
        this.player = player;
    }
    
    /**
     * Creates a string of the message object.
     * Example --> Mike23Hero : Hoi allemaal, veel succes!
     * @return the string that represents a chat message. 
     * Returns always a string that is not null.
     */
    public String toString(){
        return null;
    }
}
