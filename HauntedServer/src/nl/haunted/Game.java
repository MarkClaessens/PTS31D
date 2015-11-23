/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Timer;

/**
 *
 * @author jvdwi
 */
public class Game {
    private int floorAmount, currentRound;
    private Timer tickTimer;
    private boolean running;
    private Level level;
    private List<Character> characters;
    
    /**
     * 
     * @param players
     * @param floors 
     */
    public Game(List<IPlayer> players, int floors){
        this.floorAmount = floors;
    }
    
    /**
     * 
     */
    public void nextLevel(){
        
    }
    
    /**
     * 
     */
    public void startRound(){
        
    }
    
    /**
     * 
     */
    public void endRound(){
        
    }
    
    /**
     * 
     */
    public void endGame(){
        
    }
    
    /**
     * 
     */
    public void leaveGame(){
        
    }
    
    /**
     * 
     */
    public void tick(){
        
    }
    
    /**
     * 
     */
    public void compressGameInfo(){
        
    }
    
    /**
     * 
     */
    public void setupGameClasses(){
        
    }
    
    /**
     * 
     */
    public void bindCharactersToPlayers(){
        
    }
    
    /**
     * 
     * @return 
     */
    public Point2D pickSpawnPoint(){
        return null;
    }
    
    /**
     * 
     */
    public void sendMapFiles(){
        
    }
}
