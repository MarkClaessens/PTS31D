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
import java.util.Observable;

/**
 *
 * @author jvdwi
 */
public class Chat extends Observable {

    private List<String> messages;
    private final InputController IC;

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

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void addMessage(String s) {
        this.messages.add(s);
    }

    public void sendMessage(String s, IPlayer p) throws IOException {
        String sb = "";
        DateFormat df = new SimpleDateFormat("HH:mm");
        sb = "[" + df.format(Calendar.getInstance().getTime()) + "][" + p.getName() + "]" + ": " + s;
        IC.sendMessage(sb);
    }
}
