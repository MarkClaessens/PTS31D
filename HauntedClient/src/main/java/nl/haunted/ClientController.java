/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mike Evers, 
 */
public class ClientController extends UnicastRemoteObject implements IClientController {
    private GameInfo gameInfo;
    private Chat chat;
    private final HauntedClient client;
    private static Registry registry = null;
    private ILobby lobby = null;
    private List<IGameLobby> gamelobbys;
    private IPlayer player;
    // Binding name for lobby
    private static final String bindingNameLobby = "lobby";
    
    public List<IGameLobby> getGameLobbys(){
        return gamelobbys;
    }
    
    public ClientController(HauntedClient client, String ip) throws RemoteException {
        gamelobbys = new ArrayList();
        this.client = client;
        startClient(ip);
        gamelobbys = lobby.getGameLobbys();
        //player = lobby.createPlayer("player", "get ip adress not implemented yeti");create return type for createplayer 
    }
    
    public void startClient(String ip){
        // Locate registry at ip address (server) with port 8761.
        try {
            registry = LocateRegistry.getRegistry(ip, 8761);
        } catch (RemoteException ex) {
            System.out.println("Client: following exception was found: " + ex.getMessage());
        }
        
        // Bind Lobby using registry.
        if(registry != null){
            try {
                lobby = (ILobby)registry.lookup(bindingNameLobby);
            } catch (RemoteException ex) {
                System.out.println("Client: Cannot bind lobby");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                lobby = null;
            } catch (NotBoundException ex) {
                System.out.println("Client: Cannot bind lobby");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                lobby = null;
            }
        }
        
        // Subscribe to all changes on the gamelobby list of the lobby.
        if(lobby  != null){
            try {
                lobby.addListener(this, "gamelobbys");
            } catch (RemoteException ex) {
                System.out.println("Client: Controller couldn't subscribe himself to the lobby.");
            }
        }
    }
    
    /**
     * @param propertyChangeEvent
     * @throws RemoteException 
     */
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException {
        gamelobbys = (List<IGameLobby>)propertyChangeEvent.getNewValue();
    }
    public IPlayer getPlayer()
    {
        return player;
    }
}
