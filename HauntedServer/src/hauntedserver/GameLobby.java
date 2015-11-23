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
     * maakt een niewe gamelobby aan
     * @param name
     * @param password
     * @param host 
     */
    public GameLobby(String name, String password, Player host, int maxFloors, int maxPlayers){
        this.name = name;
        this.password = password;
        this.host = host;
        this.messages = new ArrayList();
        this.maxPlayer = maxPlayers;
        this.maxFloors = maxFloors;
    }
    
    /**
     * als alle spelers klaar zijn dan start de game
     */
    @Override
    public void startGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * verstuurt een message naar de andere spelers
     * @param message 
     */
    @Override
    public void sendMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * verwijdert een speler uit de gamelobby
     * @param player
     * @return 
     */
    @Override
    public boolean removePlayer(IPlayer player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * voegt een speler toe aan de gamelobby
     * @param player
     * @return 
     */
    @Override
    public boolean addPlayer(IPlayer player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * vraagt de naam op van de gamelobby
     * @return 
     */
    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * vraagt het maximum aantal spelers van de gamelobby op
     * @return 
     */
    @Override
    public int getMaxPlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * vraagt het maximum aantal vloeren van de gamelobby op
     * @return 
     */
    @Override
    public int getMaxFloors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * vraagt lijst met spelers die in de gamelobby zit op
     * @return 
     */
    @Override
    public List<Player> getPlayers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
