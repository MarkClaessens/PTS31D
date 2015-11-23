/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import fontys.observer.RemotePublisher;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public interface ILobby extends Remote, RemotePublisher {
    
    /** 
     * @return all the gameLobby's in the lobby.
     * @throws java.rmi.RemoteException
     */
    public List<IGameLobby> getGameLobbys() throws RemoteException;
    
    /**
     * 
     * @param name the name of the gamelobby
     * @param password the password of the gamelobby, can be null
     * @param host the player that created the gamelobby
     * @param maxFloors the maximum amount of floors that will be generated.
     * @param maxPlayers the maximum amount of player that can acces the gamelobby.
     * 
     * @throws java.rmi.RemoteException
     */
    public void createGameLobby(String name, String password, IPlayer host, 
            int maxFloors, int maxPlayers) throws RemoteException;
    
    /**
     * This method will delete the player from the gamelobby.
     * So the player can leave the gamelobby.
     * 
     * @throws java.rmi.RemoteException
     */
    public void exit() throws RemoteException;
    
    /**
     * 
     * @param name the name of the player
     * @param ipAddress the ipadress of the player
     * 
     * @throws java.rmi.RemoteException
     */
    public void createPlayer(String name, String ipAddress) throws RemoteException;
}
