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

    private SocketMediator messageSocket, srvSocket, inputSocket;
    private DirectionType direction, prevDirection;
    private IGameLobby gameLobby;
    private Timer timer;
//    SocketMediator messageSocket;
//    DirectionType direction;

    public InputController(String groupID, IGameLobby GL) throws IOException {
        this.messageSocket = new SocketMediator();
        this.srvSocket = new SocketMediator();
        this.inputSocket = new SocketMediator();
        this.messageSocket.socketSetup(groupID, 9877, "UDP");
        this.srvSocket.socketSetup(groupID, 9876, "UDP");
        this.gameLobby = GL;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    srvSocket.receiveObject();
                    messageSocket.receiveMessage();
                } catch (ClassNotFoundException | IOException ex) {
                    Logger.getLogger(InputController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 16);

    }

    public SocketMediator getInputSocket() {
        return this.messageSocket;
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
        this.messageSocket.sendMessage(sb.toString());
    }

    public void sendInput() throws IOException {
        if (this.direction != this.prevDirection) {
            this.inputSocket.socketSetup("",9878,"TCPS");
            if (this.direction != null) {
                this.inputSocket.sendInput(this.direction.toString());
            } else {
                this.inputSocket.sendInput("null");
            }
            this.prevDirection = this.direction;
        }
    }

    public void setSrvSocket(SocketMediator s) {
        this.messageSocket = s;
    }

    public List<Message> getMessage() throws IOException, ClassNotFoundException {

        List<String> exinputlist = this.messageSocket.getMessages();
        List<String> inputlist = new ArrayList(this.messageSocket.getMessages().size());
        if (!exinputlist.isEmpty()) {
            inputlist.addAll(exinputlist);
            List<Message> messages = new ArrayList();
            for (String input : inputlist) {
                if (input != null) {
                    boolean visible = true;
                    if ("0".equals(input.substring(1, 2))) {
                        visible = false;
                    }
                    String strPlayer = input.substring(4, 4 + input.substring(4).indexOf("]"));
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
