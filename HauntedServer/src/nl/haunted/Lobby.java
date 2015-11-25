/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import fontys.observer.BasicPublisher;
import fontys.observer.RemotePropertyListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mike Evers + ..
 */
public class Lobby extends UnicastRemoteObject implements ILobby {
    //private singleton Lobby;
    private final List<IGameLobby> gameLobbys;

    private final BasicPublisher basicPublisher;
    private List<Player> players; 
 
    /**
     * @throws java.rmi.RemoteException
     */
    public Lobby() throws RemoteException {
        //super(0, new MyRMISocketFactory(), new MyRMISocketFactory());
        UnicastRemoteObject.exportObject(this, 1096);
        String[] props = new String[1];
        props[0] = "gamelobbys";
        basicPublisher = new BasicPublisher(props);
        this.gameLobbys = new ArrayList();
        gameLobbys.add(new GameLobby("gamelobbyMike", null, new Player("Mike", "1223"), 5, 5));
	this.players = new ArrayList();
    }
  
    @Override
    public List<IGameLobby> getGameLobbys() throws RemoteException {
        return gameLobbys;
    }

    @Override
    public void createGameLobby(String name, String password, IPlayer host, int maxFloors, int maxPlayers) throws RemoteException {
        GameLobby gamelobby = new GameLobby(name, password, (Player)host, maxFloors, maxPlayers);
        gameLobbys.add(gamelobby);
    }

    @Override
    public void exit() throws RemoteException {
        
    }


    @Override
    public void createPlayer(String name, String ipAddress) throws RemoteException {
        Player player = new Player(name, ipAddress);
        players.add(player);
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
