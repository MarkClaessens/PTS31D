/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import fontys.observer.BasicPublisher;
import fontys.observer.RemotePropertyListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jvdwi
 */
public class GameLobby extends UnicastRemoteObject implements IGameLobby {

    private String name, password;
    private int maxPlayer, maxFloors;
    private Player host;
    private List<Message> messages;
    private List<IPlayer> players;
    private BasicPublisher basicPublisher;
    private static int gameLobbyNum = 0;
    private final Socket msgSoc;
    private String groupID;

    /**
     * maakt een nieuwe gamelobby aan
     *
     * @param name
     * @param password
     * @param host
     * @param maxFloors
     * @param maxPlayers
     * @throws java.rmi.RemoteException
     */
    public GameLobby(String name, String password, Player host, int maxFloors, int maxPlayers) throws RemoteException, IOException {
        this.name = name;
        this.password = password;
        this.host = host;
        this.messages = new ArrayList();
        this.maxPlayer = maxPlayers;
        this.maxFloors = maxFloors;
        this.players = new ArrayList();
        String[] props = new String[1];
        props[0] = "players";
        this.basicPublisher = new BasicPublisher(props);
        players.add(host);

        //<socket>
        msgSoc = new Socket();
        int groupIdNum = 234567890 + gameLobbyNum;
        String stringConverter = "" + groupIdNum;
        groupID = stringConverter.substring(0, 3) + "." + stringConverter.substring(4, 5) + "." + stringConverter.substring(6, 7) + "." + stringConverter.substring(8, 9);
        msgSoc.socketSetup(groupID, 9877);
        gameLobbyNum++;
        //</socket>

    }

    /**
     * als alle spelers klaar zijn dan start de game
     */
    @Override
    public void startGame() throws RemoteException {
        if (players.size() > 2) {
            boolean ready = readycheck();
            if (ready) {
                Game game;
                try {
                    game = new Game(players, maxFloors, groupID);
                    game.startRound();
                } catch (IOException ex) {
                    Logger.getLogger(GameLobby.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    /**
     * verstuurt een message naar de andere spelers
     *
     * @param message
     */
    @Override
    public void sendMessage(String message) throws RemoteException {
        Message bericht = new Message(message, host);
    }

    /**
     * verwijdert een speler uit de gamelobby
     *
     * @param player
     * @return
     */
    @Override
    public boolean removePlayer(IPlayer player) throws RemoteException {
        boolean exist = false;
        for (IPlayer speler : players) {
            if (player == speler) {
                exist = true;
            }
        }
        if (exist) {
            players.remove(player);
            return true;
        } else {
            return false;
        }

    }

    /**
     * voegt een speler toe aan de gamelobby
     *
     * @param player
     * @return
     */
    @Override
    public boolean addPlayer(IPlayer player) throws RemoteException {
        boolean exist = false;
        for (IPlayer speler : players) {
            if (speler == player) {
                exist = true;
            }
        }
        if (exist == false) {
            players.add((Player) player);
            this.basicPublisher.inform(this, "players", null, players);
            return true;
        } else {
            return false;
        }
    }

    /**
     * vraagt de naam op van de gamelobby
     *
     * @return
     */
    @Override
    public String getName() throws RemoteException {
        return name;
    }

    /**
     * vraagt het maximum aantal spelers van de gamelobby op
     *
     * @return
     */
    @Override
    public int getMaxPlayer() throws RemoteException {
        return maxPlayer;
    }

    /**
     * vraagt het maximum aantal vloeren van de gamelobby op
     *
     * @return
     */
    @Override
    public int getMaxFloors() throws RemoteException {
        return maxFloors;
    }

    /**
     * vraagt lijst met spelers die in de gamelobby zit op
     *
     * @return
     */
    @Override
    public List<IPlayer> getPlayers() throws RemoteException {
        return players;
    }

    @Override
    public boolean readycheck() throws RemoteException {
        for (IPlayer speler : players) {
            if (!speler.getReady()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addListener(RemotePropertyListener remotePropertyListener, String string) throws RemoteException {
        basicPublisher.addListener(remotePropertyListener, string);
    }

    @Override
    public void removeListener(RemotePropertyListener remotePropertyListener, String string) throws RemoteException {
        basicPublisher.removeListener(remotePropertyListener, string);
    }
}
