/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import fontys.observer.BasicPublisher;
import fontys.observer.RemotePropertyListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.awt.Color;

/**
 *
 * @author jvdwi
 */
public class Player extends UnicastRemoteObject implements IPlayer {

    private String name, ipAddress;
    private boolean ready;
    private DirectionType input;
    private Character character;
    private Color color;
    private BasicPublisher basicPublisher;

    @Override
    public Character getCharacter() throws RemoteException {
        return character;
    }

    /**
     *
     * @param character
     * @throws RemoteException
     */
    @Override
    public void setCharacter(Character character) throws RemoteException {
        this.character = character;
    }

    @Override
    public Color getColor() throws RemoteException {
        return this.color;
    }

    /**
     * vraagt de naam van de speler
     *
     * @return
     */
    @Override
    public String getName() throws RemoteException {
        return name;
    }

    /**
     *
     * @param naam
     * @throws RemoteException
     */
    @Override
    public void setName(String naam) throws RemoteException {
        this.name = naam;
    }

    /**
     * vraagt de status van de speler
     *
     * @return
     */
    @Override
    public boolean getReady() throws RemoteException {
        return ready;
    }

    /**
     * set de status van de speler
     *
     * @param ready
     */
    @Override
    public void setReady(boolean ready) throws RemoteException {
        this.ready = ready;
    }

    /**
     *
     * @return
     */
    public String getIpAdress() {
        return this.ipAddress;
    }

    /**
     * maakt de speler aan
     *
     * @param name
     * @param ipAddress
     * @throws java.rmi.RemoteException
     */
    public Player(String name, String ipAddress) throws RemoteException {
        this.name = name;
        this.ipAddress = ipAddress;
        String[] props = new String[1];
        props[0] = "ready";
        this.basicPublisher = new BasicPublisher(props);
    }

    /**
     *
     */
    public void reset() {
        this.ready = false;
        this.character = null;
    }

    /**
     * verandert de ready status van de speler
     *
     * @throws java.rmi.RemoteException
     */
    public void toggleReady() throws RemoteException {
        if (this.ready) {
            this.ready = false;
        } else if (this.ready == false) {
            this.ready = true;
        }
        basicPublisher.inform(this, "ready", null, this.ready);
    }

    /**
     *
     * @param kleur
     * @throws RemoteException
     */
    @Override
    public void setColor(Color kleur) throws RemoteException {
        this.color = kleur;
    }

    @Override
    public void addListener(RemotePropertyListener remotePropertyListener, String string) throws RemoteException {
        basicPublisher.addListener(remotePropertyListener, string);
    }

    @Override
    public void removeListener(RemotePropertyListener remotePropertyListener, String string) throws RemoteException {
        basicPublisher.removeListener(remotePropertyListener, string);
    }
}
