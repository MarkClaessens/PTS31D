/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

/**
 *
 * @author Mark Claessens
 */
public interface IPlayer {
    
    /**
     * 
     * @return 
     */
    public Character getCharacter();
    
    /**
     * vraag naam op van speler
     * @return Name 
     */
    public String getName();
    
    /**
     * vraag de ready status op van de speler
     * @return ready
     */
    public boolean getReady();
    
    /**
     * set de ready status van de speler naar de waarde van de param
     * @param ready 
     */
    public void setReady(boolean ready);
    
    /**
     * set de directie waar de speler naar toe gaat
     * @param direction 
     */
    public void setInput(DirectionType direction);
}
