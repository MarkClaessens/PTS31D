/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import fontys.observer.RemotePublisher;
import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public interface ILobby extends Remote, RemotePublisher{
    /**
     * 
     * @return 
     */
    public List<IGameLobby> getGameLobbys();
    
    /**
     * 
     * @param name
     * @param password
     * @param host
     * @param maxFloors
     * @param maxPlayers 
     */
    public void createGameLobby(String name, String password, IPlayer host, int maxFloors, int maxPlayers);
    
    /**
     * 
     */
    public void exit();
    
    /**
     * 
     * @param name
     * @param ipAddress 
     */
    public void createPlayer(String name, String ipAddress);
}