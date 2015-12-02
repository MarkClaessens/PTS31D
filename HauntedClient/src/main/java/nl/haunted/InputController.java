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

    Socket inputSocket;
    ArrayList<String> messages;
    DirectionType direction;

    public InputController() {
        this.inputSocket = new Socket();
        messages = new ArrayList();
    }

    public Socket getInputSocket() {
        return this.inputSocket;
    }

    public void addMessage(String s) {
        this.messages.add(s);
    }

    private String[] compressInput() throws IOException {
        String[] sb = null;
        sb[0] = direction.toString();
        int i = 1;
        String ip = "["+this.inputSocket.getCurrentNIC().getInetAddresses().nextElement().getHostAddress()+"]:";
        for(String s: messages){
            sb[i] = ip+s;
            i++;
        }    
        return sb;
    }
    
    private void clearInput(){
        this.direction = null;
        this.messages.clear();
    }
    public void sendInput() throws IOException{
        this.inputSocket.sendInput(this.compressInput());
        
    }
    

}
