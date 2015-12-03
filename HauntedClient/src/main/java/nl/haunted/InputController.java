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

    private Socket inputSocket;
    private ArrayList<String> messages;
    private DirectionType direction;
//    Socket inputSocket;
//    DirectionType direction;

    public InputController() {
        this.inputSocket = new Socket();
    }

    public Socket getInputSocket() {
        return this.inputSocket;
    }
    
    public void setDirection(DirectionType direction){
        this.direction = direction;
    }

    public void sendMessage(String s) throws IOException {
        this.inputSocket.sendInput(s, 9877);
    }

    public void sendInput() throws IOException{
        this.inputSocket.sendInput(this.direction.toString(), 9876);
    }
    

}
