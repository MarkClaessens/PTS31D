/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

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
    
    
    /**
     * @throws java.rmi.RemoteException
     */
    public Lobby() throws RemoteException {
        String[] props = new String[1];
        props[0] = "gamelobbys";
        basicPublisher = new BasicPublisher(props);
        this.gameLobbys = new ArrayList();
    }
  
    @Override
    public List<IGameLobby> getGameLobbys() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void createGameLobby(String name, String password, IPlayer host,
            int maxFloors, int maxPlayers) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void exit() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void createPlayer(String name, String ipAddress) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }


    @Override
    public void addListener(RemotePropertyListener rl, String string) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void removeListener(RemotePropertyListener rl, String string) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
