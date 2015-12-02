/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public class Chat {
    private List<String> messages;
    
    /**
     * Constructor for chat
     */
    public Chat(){
        this.messages = new ArrayList();
    }
    
    public void addMessage(String s){
        this.messages.add(s);
    }
}
