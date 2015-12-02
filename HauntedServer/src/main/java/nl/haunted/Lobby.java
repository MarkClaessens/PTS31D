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
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike Evers + ..
 */
public class Lobby extends UnicastRemoteObject implements ILobby {

    //private singleton Lobby;
    private final List<IGameLobby> gameLobbys;

    private final BasicPublisher basicPublisher;
    private List<IPlayer> players;

    /**
     * @throws java.rmi.RemoteException
     */
    public Lobby() throws RemoteException {
        String[] props = new String[1];
        props[0] = "gamelobbys";
        basicPublisher = new BasicPublisher(props);
        this.gameLobbys = new ArrayList();
        this.players = new ArrayList();
    }

    @Override
    public List<IGameLobby> getGameLobbys() throws RemoteException {
        return gameLobbys;
    }

    @Override
    public void createGameLobby(String name, String password, IPlayer host, int maxFloors, int maxPlayers) throws RemoteException {
        try {
           GameLobby gamelobby = new GameLobby(name, password, host, maxFloors, maxPlayers);
           gameLobbys.add(gamelobby);
           basicPublisher.inform(this, "gamelobbys", null, gameLobbys);
        } catch (IOException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void exit(IPlayer player) throws RemoteException {
        boolean exist = false;
        for (IPlayer speler : players) {
            if (player == speler) {
                exist = true;
            }
        }
        if (exist) {
            players.remove(player);
        } 
    }

    @Override
    public IPlayer createPlayer(String name, String ipAddress) throws RemoteException {
        Player player = new Player(name, ipAddress);
        players.add(player);
        return (IPlayer)player;
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
