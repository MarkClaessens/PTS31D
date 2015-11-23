/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jvdwi
 */
public class GameLobby implements IGameLobby {

    private String name, password;
    private int maxPlayer, maxFloors;
    private Player host;
    private List<Message> messages;
    private List<IPlayer> players;
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
        this.players = new ArrayList();
        players.add(host);
    }
    
    /**
     * als alle spelers klaar zijn dan start de game
     */
    @Override
    public void startGame() {
      if(players.size() > 2)
      {
        boolean ready = readycheck();
        if(ready)
        {           
           Game game = new Game(players, maxFloors);
           game.startRound();
        }
      }       
    }

    /**
     * verstuurt een message naar de andere spelers
     * @param message 
     */
    @Override
    public void sendMessage(String message) {
       Message bericht = new Message(message, host);
    }

    /**
     * verwijdert een speler uit de gamelobby
     * @param player
     * @return 
     */
    @Override
    public boolean removePlayer(IPlayer player) {
      boolean exist = false;
        for(IPlayer speler : players)
      {
          if(player == speler)
          {
              exist = true;
          }
      }
        if(exist)
        {
           players.remove(player);
           return true;
        }
        else
        {
            return false;
        }
      
    }

    /**
     * voegt een speler toe aan de gamelobby
     * @param player
     * @return 
     */
    @Override
    public boolean addPlayer(IPlayer player) {
        boolean exist = false;
        for(IPlayer speler : players)
        {
            if(speler == player)
            {
              exist = true;
            }
        }
        if(exist == false)
        {
            players.add((Player) player);
            return true;
        }
        else
        {
            return false;
        }        
      }

    /**
     * vraagt de naam op van de gamelobby
     * @return 
     */
    @Override
    public String getName() {
     return name;    
    }

    /**
     * vraagt het maximum aantal spelers van de gamelobby op
     * @return 
     */
    @Override
    public int getMaxPlayer() {
     return maxPlayer;    
    }

    /**
     * vraagt het maximum aantal vloeren van de gamelobby op
     * @return 
     */
    @Override
    public int getMaxFloors() {
     return maxFloors;    
    }

    /**
     * vraagt lijst met spelers die in de gamelobby zit op
     * @return 
     */
    @Override
    public List<IPlayer> getPlayers() {
     return players;    
    }
    
    @Override
    public boolean readycheck()
    {
        for(IPlayer speler : players)
        {
            if(!speler.getReady())
            {
                return false;
            }
        }
        return true;
    }
    
}
