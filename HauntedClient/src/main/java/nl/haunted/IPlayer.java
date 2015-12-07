/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import fontys.observer.RemotePublisher;
import java.awt.Color;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author jvdwi
 */
public interface IPlayer extends Remote, RemotePublisher {

    /**
     *
     * @return @throws java.rmi.RemoteException
     */
    public Character getCharacter() throws RemoteException;

    /**
     * vraag naam op van speler
     *
     * @return Name
     * @throws java.rmi.RemoteException
     */
    public String getName() throws RemoteException;

    public void setName(String naam) throws RemoteException;

    public void toggleReady() throws RemoteException;

    /**
     * vraag de ready status op van de speler
     *
     * @return ready
     * @throws java.rmi.RemoteException
     */
    public boolean getReady() throws RemoteException;

    /**
     *
     * @return the color of the player
     * @throws RemoteException
     */
    public Color getColor() throws RemoteException;

    /**
     * set de ready status van de speler naar de waarde van de param
     *
     * @param ready
     * @throws java.rmi.RemoteException
     */
    public void setReady(boolean ready) throws RemoteException;

    public void setCharacter(Character character) throws RemoteException;
}
