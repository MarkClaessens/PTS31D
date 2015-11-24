/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import fontys.observer.RemotePublisher;
import java.awt.Color;

/**
 *
 * @author jvdwi
 */
public interface IPlayer extends RemotePublisher{
    
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
    
    public Color getColor();
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
