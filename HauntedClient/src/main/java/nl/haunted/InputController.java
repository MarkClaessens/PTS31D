/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mal
 */
public class InputController {

    private Socket inputSocket, srvSocket;
    private ArrayList<String> messages;
    private DirectionType direction;
//    Socket inputSocket;
//    DirectionType direction;

    public InputController(String groupID) throws IOException {
        this.inputSocket = new Socket();
        this.srvSocket = new Socket();
        this.inputSocket.socketSetup(groupID, 9877);
        this.srvSocket.socketSetup(groupID, 9876);
    }

    public Socket getInputSocket() {
        return this.inputSocket;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }

    public void sendMessage(Message m) throws IOException {
        this.inputSocket.sendMessage(m);
    }

    public void sendInput() throws IOException {
        this.srvSocket.sendInput(this.direction.toString(), 9876);
    }
    
    public void setSrvSocket(Socket s){
        this.inputSocket = s;
    }
    
    public Message getMessage() throws IOException, ClassNotFoundException{
       return this.inputSocket.receiveMessage();
    }

}
