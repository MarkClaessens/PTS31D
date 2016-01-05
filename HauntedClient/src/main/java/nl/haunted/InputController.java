/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mal
 */
public class InputController {

    private SocketMediator inputSocket, srvSocket;
    private DirectionType direction;
    private IGameLobby gameLobby;
    private Timer timer;
//    SocketMediator inputSocket;
//    DirectionType direction;

    public InputController(String groupID, IGameLobby GL) throws IOException {
        this.inputSocket = new SocketMediator();
        this.srvSocket = new SocketMediator();
        this.inputSocket.socketSetup(groupID, 9877, "TCPS");
        this.srvSocket.socketSetup(groupID, 9876, "UDP");
        this.gameLobby = GL;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    srvSocket.receiveObject();
                    inputSocket.receiveMessage();
                } catch (ClassNotFoundException | IOException ex) {
                    Logger.getLogger(InputController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 16);

    }

    public SocketMediator getInputSocket() {
        return this.inputSocket;
    }

    public SocketMediator getSrvSocket() {
        return this.srvSocket;
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
        if(this.direction != null){
            this.srvSocket.sendInput(this.direction.toString(), 9876);
        }else {
            this.srvSocket.sendInput("null", 9876);
        }    
    }

    public void setSrvSocket(SocketMediator s) {
        this.inputSocket = s;
    }

    public List<Message> getMessage() throws IOException, ClassNotFoundException {

        List<String> exinputlist = this.inputSocket.getMessages();
        List<String> inputlist = new ArrayList(this.inputSocket.getMessages().size());
        if (!exinputlist.isEmpty()) {
            inputlist.addAll(exinputlist);
            List<Message> messages = new ArrayList();
            for (String input : inputlist) {
                if (input != null) {
                    boolean visible = true;
                    if ("0".equals(input.substring(1, 2))) {
                        visible = false;
                    }
                    String strPlayer = input.substring(4, 4+input.substring(4).indexOf("]"));
                    IPlayer player = null;
                    for (IPlayer p : gameLobby.getPlayers()) {
                        if (p.getName() == null ? strPlayer == null : p.getName().equals(strPlayer)) {
                            player = p;
                        }
                    }
                    String text = input.substring(input.indexOf("]: ") + 3);
                    if (player != null) {
                        messages.add(new Message(text, player, visible));
                    }

                }
            }
            return messages;
        }
        return null;
    }

}
