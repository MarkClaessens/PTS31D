/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public class Chat {
    private List<String> messages;
    private final InputController IC;
    
    /**
     * Constructor for chat
     */
    public Chat(){
        this.messages = new ArrayList();
        IC = new InputController();
    }
    
    public List<String> getMessages(){
        return Collections.unmodifiableList(messages);
    }
    
    public void addMessage(String s){
        this.messages.add(s);
    }
    
    public void sendMessage(String s, Player p) throws IOException{
        String sb = "";
        DateFormat df = new SimpleDateFormat("HH:mm");
        sb = "["+df.format(Calendar.getInstance())+"]["+p.getName()+"]"+": "+s;
        IC.sendMessage(sb);
    }
}
