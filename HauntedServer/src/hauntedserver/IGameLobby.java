/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public interface IGameLobby extends Remote {
       /**
     * het spel wordt gestart
     */
    public void startGame();
    
    /**
     * een speler verstuurt een bericht naar de andere spelers in de gamelobby
     * @param message 
     */
    public void sendMessage(String message);
    
    /**
     * kijkt of een speler uit de room verwijdert kan worden. als dit kan dan wordt hij verwijdert.
     * @param player
     * @return boolean
     */
    public boolean removePlayer(IPlayer player);
    
    /**
     * kijkt of een speler bij de room toegevoegt kan worden. als dit kan dan wordt hij toegevoegt.
     * @param player
     * @return boolean
     */
    public boolean addPlayer(IPlayer player);
    
    /**
     * geeft naam van de gamelobby terug
     * @return name
     */
    public String getName();
    
    /**
     * geeft het maximum aantal spelers terug die in de gamelobby mogen
     * @return maxplayers
     */
    public int getMaxPlayer();
    
    /**
     * geeft het maximum aantal floors terug die in de game zitten
     * @return maxfloors
     */
    public int getMaxFloors();
    
    /**
     * geeft het aantal players terug die in de game zitten
     * @return players
     */
    public List<Player> getPlayers();
    
    public boolean readycheck();
}
