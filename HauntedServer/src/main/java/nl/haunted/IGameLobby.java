/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import fontys.observer.RemotePublisher;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public interface IGameLobby extends Remote, RemotePublisher {

    /**
     * het spel wordt gestart
     *
     * @throws java.rmi.RemoteException
     */
    public void startGame() throws RemoteException;

    /**
     * kijkt of een speler uit de room verwijdert kan worden. als dit kan dan
     * wordt hij verwijdert.
     *
     * @param player
     * @return boolean
     * @throws java.rmi.RemoteException
     */
    public boolean removePlayer(IPlayer player) throws RemoteException;

    /**
     * kijkt of een speler bij de room toegevoegt kan worden. als dit kan dan
     * wordt hij toegevoegt.
     *
     * @param player
     * @return boolean
     * @throws java.rmi.RemoteException
     */
    public boolean addPlayer(IPlayer player) throws RemoteException;

    /**
     * geeft naam van de gamelobby terug
     *
     * @return name
     * @throws java.rmi.RemoteException
     */
    public String getName() throws RemoteException;

    /**
     * geeft het maximum aantal spelers terug die in de gamelobby mogen
     *
     * @return maxplayers
     * @throws java.rmi.RemoteException
     */
    public int getMaxPlayer() throws RemoteException;

    /**
     * geeft het maximum aantal floors terug die in de game zitten
     *
     * @return maxfloors
     * @throws java.rmi.RemoteException
     */
    public int getMaxFloors() throws RemoteException;

    /**
     * geeft het aantal players terug die in de game zitten
     *
     * @return players
     * @throws java.rmi.RemoteException
     */
    public List<IPlayer> getPlayers() throws RemoteException;

    /**
     * this function returns the variable groupId
     * @return the id of the group to be connecting to.
     * @throws RemoteException
     */
    public String getGroupID() throws RemoteException;

    /**
     * Check wether the player is ready or not.
     *
     * @return
     * @throws RemoteException
     */
    public boolean readycheck() throws RemoteException;

    /**
     * this function returns the variable ingame.
     * @return true if the game has started.
     * @throws RemoteException
     */
    public boolean getIngame() throws RemoteException;

    /**
     * this function returns the variable host
     * @return the IPlayer that is the host.
     * @throws RemoteException
     */
    public IPlayer getHost() throws RemoteException;

    /**
     * this function returns the variable password
     * @return password of the lobby
     * @throws RemoteException
     */
    public String getPassword() throws RemoteException;

    /**
     * Set the player that will be the host
     * @param player the IPlayer that will be the host
     * @throws RemoteException
     */
    public void setHost(IPlayer player) throws RemoteException;

    /**
     * This function sets the name of the gamelobby.
     * @param name the name of the gamelobby
     * @throws RemoteException
     */
    public void setName(String name) throws RemoteException;

    /**
     * This function sets the maximum floors
     * @param maxfloors the maximum amount of floors.
     * @throws RemoteException
     */
    public void setMaxFloors(int maxfloors) throws RemoteException;

    /**
     * this function sets the max players in the gamelobby
     * @param maxplayers max amount of players.
     * @throws RemoteException
     */
    public void setMaxPlayers(int maxplayers) throws RemoteException;
}
