/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public class GameLobby implements IGameLobby {

    private String name, password;
    private int maxPlayer, maxFloors;
    private Player host;
    private List<Message> messages;
    
    /**
     * 
     * @param name
     * @param password
     * @param host 
     */
    public GameLobby(String name, String password, Player host){
        this.name = name;
        this.password = password;
        this.host = host;
        this.messages = new ArrayList();
    }
    
    /**
     * 
     */
    @Override
    public void startGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param message 
     */
    @Override
    public void sendMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param player
     * @return 
     */
    @Override
    public boolean removePlayer(IPlayer player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param player
     * @return 
     */
    @Override
    public boolean addPlayer(IPlayer player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return 
     */
    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return 
     */
    @Override
    public int getMaxPlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return 
     */
    @Override
    public int getMaxFloors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return 
     */
    @Override
    public int getPlayers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
