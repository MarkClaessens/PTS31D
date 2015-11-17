/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedclient;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author S31D
 */
public class Controller extends UnicastRemoteObject implements IController {
    private GameInfo gameInfo;
    private Chat chat;

    /**
     * 
     * @param pce
     * @throws RemoteException 
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
