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
public interface IPlayer {
    
    /**
     * 
     * @return 
     */
    public Character getCharacter();
    
    /**
     * 
     * @return 
     */
    public String getName();
    
    /**
     * 
     * @return 
     */
    public boolean getReady();
    
    /**
     * 
     * @param ready 
     */
    public void setReady(boolean ready);
    
    /**
     * 
     * @param direction 
     */
    public void setInput(DirectionType direction);
}
