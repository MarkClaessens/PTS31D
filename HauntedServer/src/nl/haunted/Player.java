/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import fontys.observer.RemotePropertyListener;
import java.awt.Color;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author jvdwi
 */
public class Player extends UnicastRemoteObject implements IPlayer{

    private String name, ipAddress;
    private boolean ready;
    private DirectionType input;
    private Character character;
    private Color color;
    
    @Override
    public Character getCharacter() throws RemoteException{
        return character;
    }
    
    @Override
    public Color getColor() throws RemoteException{
        return this.color;
    }

    /**
     * vraagt de naam van de speler
     * @return 
     */
    @Override
    public String getName() throws RemoteException{
       return name;
    }

    /**
     * vraagt de status van de speler
     * @return 
     */
    @Override
    public boolean getReady() throws RemoteException{
        return ready;
    }

    /**
     * set de status van de speler
     * @param ready 
     */
    @Override
    public void setReady(boolean ready) throws RemoteException{
        this.ready = ready;
    }

    /**
     * set de directie van de speler
     * @param direction 
     */
    @Override
    public void setInput(DirectionType direction) throws RemoteException {
        this.input = direction;
    }

    /**
     * maakt de speler aan
     * @param name
     * @param ipAddress 
     * @throws java.rmi.RemoteException 
     */
    public Player(String name, String ipAddress) throws RemoteException {
        super(0, new MyRMISocketFactory(), new MyRMISocketFactory());
        
        this.name = name;
        this.ipAddress = ipAddress;
    }
    
    /**
     * verandert de ready status van de speler
     * @throws java.rmi.RemoteException
     */
    public void toggleReady() throws RemoteException{
        
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
