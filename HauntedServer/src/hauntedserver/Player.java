/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

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
    
    /**
     * 
     * @param name
     * @param ipAddress 
     */
    public Player(String name, String ipAddress){
        this.name = name;
        this.ipAddress = ipAddress;
    }
    
    /**
     * 
     */
    public void toggleReady(){
        
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
    public String getReady() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param ready 
     */
    @Override
    public void setReady(String ready) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param direction 
     */
    @Override
    public void setInput(DirectionType direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param rl
     * @param string
     * @throws RemoteException 
     */
    @Override
    public void addListener(RemotePropertyListener rl, String string) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param rl
     * @param string
     * @throws RemoteException 
     */
    @Override
    public void removeListener(RemotePropertyListener rl, String string) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
