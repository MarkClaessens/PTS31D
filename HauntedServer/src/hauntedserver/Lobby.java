/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import fontys.observer.BasicPublisher;
import fontys.observer.RemotePropertyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public class Lobby implements ILobby {
    //private singleton Lobby;
    private BasicPublisher publisher;
    private List<IGameLobby> gameLobbys;
    
    /**
     * 
     */
    private Lobby(){
        this.gameLobbys = new ArrayList();
    }

    /**
     * 
     * @return 
     */
    @Override
    public List<IGameLobby> getGameLobbys() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param name
     * @param password
     * @param host
     * @param maxFloors
     * @param maxPlayers 
     */
    @Override
    public void createGameLobby(String name, String password, IPlayer host, int maxFloors, int maxPlayers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     */
    @Override
    public void exit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param name
     * @param ipAddress 
     */
    @Override
    public void createPlayer(String name, String ipAddress) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param rl
     * @param string
     * @throws RemoteException 
     */
    @Override
    public void addListener(RemotePropertyListener rl, String string) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param rl
     * @param string
     * @throws RemoteException 
     */
    @Override
    public void removeListener(RemotePropertyListener rl, String string) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
