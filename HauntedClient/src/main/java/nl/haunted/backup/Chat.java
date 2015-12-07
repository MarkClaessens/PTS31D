/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jvdwi
 */
public class Chat extends Observable {

    private List<Message> messages;
    private final InputController IC;
    private Timer timer;

    /**
     * Constructor for chat
     *
     * @param groupID
     * @throws java.io.IOException
     */
    public Chat(String groupID) throws IOException {
        this.messages = new ArrayList();
        IC = new InputController(groupID);
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void addMessage(Message m) {
        this.messages.add(m);
    }

    public void sendMessage(Message m) throws IOException {
        IC.sendMessage(m);
    }
    
    public void startMessageClient(){
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {          
                try {
                    addMessage(IC.getMessage());
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 16);
    }
    
    public void stopMessageClient(){
        timer.cancel();
    }
    
}
