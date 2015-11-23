/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import fontys.observer.RemotePropertyListener;
import java.rmi.RemoteException;

/**
 *
 * @author jvdwi
 */
public class Player implements IPlayer{

    private String name, color, ipAddress;
    private boolean ready;
    private DirectionType input;
    private Character character;
    
    @Override
    public Character getCharacter() {
        return character;
    }

    /**
     * vraagt de naam van de speler
     * @return 
     */
    @Override
    public String getName() {
       return name;
    }

    /**
     * vraagt de status van de speler
     * @return 
     */
    @Override
    public boolean getReady() {
        return ready;
    }

    /**
     * set de status van de speler
     * @param ready 
     */
    @Override
    public void setReady(boolean ready) {
        this.ready = ready;
    }

    /**
     * set de directie van de speler
     * @param direction 
     */
    @Override
    public void setInput(DirectionType direction) {
        this.input = direction;
    }

    /**
     * maakt de speler aan
     * @param name
     * @param ipAddress 
     */
    public Player(String name, String ipAddress){
        this.name = name;
        this.ipAddress = ipAddress;
    }
    
    /**
     * verandert de ready status van de speler
     */
    public void toggleReady(){
        
    }
    
    /**
     * voegt de listener toe aan de lijst van listeners
     * @param rl
     * @param string
     * @throws RemoteException 
     */
    @Override
    public void addListener(RemotePropertyListener rl, String string) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * verwijdert de listener van de lijst van listeners
     * @param rl
     * @param string
     * @throws RemoteException 
     */
    @Override
    public void removeListener(RemotePropertyListener rl, String string) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
