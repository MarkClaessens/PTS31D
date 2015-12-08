/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mal
 */
public class InputController {

    private Socket inputSocket, srvSocket;
    private ArrayList<String> messages;
    private DirectionType direction;
    private IGameLobby gameLobby;
//    Socket inputSocket;
//    DirectionType direction;

    public InputController(String groupID, IGameLobby GL) throws IOException {
        this.inputSocket = new Socket();
        this.srvSocket = new Socket();
        this.inputSocket.socketSetup(groupID, 9877);
        this.srvSocket.socketSetup(groupID, 9876);
        this.gameLobby = GL;
    }

    public Socket getInputSocket() {
        return this.inputSocket;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }

    public void sendMessage(Message m) throws IOException {
        StringBuilder sb = new StringBuilder();
        
        sb.append("[");
        if (m.getIsVisibleForEveryone()) {
            sb.append("1");
        } else {
            sb.append("0");
        }
        sb.append("]").append(m.toString().substring(m.toString().indexOf("]") + 1));
        this.inputSocket.sendMessage(sb.toString());
    }

    public void sendInput() throws IOException {
        this.srvSocket.sendInput(this.direction.toString(), 9876);
    }

    public void setSrvSocket(Socket s) {
        this.inputSocket = s;
    }

    public Message getMessage() throws IOException, ClassNotFoundException {
        String input = this.inputSocket.receiveMessage();
        if (input != null) {
            boolean visible = true;
            if("0".equals(input.substring(1,2))){
                visible = false;
            }
            String strPlayer = input.substring(4, input.substring(4).indexOf("]"));
            IPlayer player = null;
            for (IPlayer p : gameLobby.getPlayers()) {
                if (p.getName() == null ? strPlayer == null : p.getName().equals(strPlayer)) {
                    player = p;
                }
            }
            String text = input.substring(input.indexOf("]: ") + 3);
            if (player != null) {
                return new Message(text, player, visible);
            }
            return null;
        }
        return null;
    }

}
