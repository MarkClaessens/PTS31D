/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedclient;

/**
 *
 * @author jvdwi
 */
public interface IGameLobby {
    /**
     * 
     */
    public void startGame();
    
    /**
     * 
     * @param message 
     */
    public void sendMessage(String message);
    
    /**
     * 
     * @param player
     * @return 
     */
    public boolean removePlayer(IPlayer player);
    
    /**
     * 
     * @param player
     * @return 
     */
    public boolean addPlayer(IPlayer player);
    
    /**
     * 
     * @return 
     */
    public String getName();
    
    /**
     * 
     * @return 
     */
    public int getMaxPlayer();
    
    /**
     * 
     * @return 
     */
    public int getMaxFloors();
    
    /**
     * 
     * @return 
     */
    public int getPlayers();
}
