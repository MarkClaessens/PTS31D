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
     * 
     * @param text
     * @param player 
     */
    public Message(String text, Player player){
        this.text = text;
        this.player = player;
    }
    
    public String toString(){
        return null;
    }
}
