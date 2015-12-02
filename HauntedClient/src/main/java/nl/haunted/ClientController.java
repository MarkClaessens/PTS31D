/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;


import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

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
    private IPlayer tisplayer;
    private boolean INGameLobby;
    private IGameLobby YourGL;
    private String groupID;
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
        INGameLobby = false;
        YourGL = null;
        //player = lobby.createPlayer("player", "get ip adress not implemented yeti");create return type for createplayer 
    }
    
    public void startClient(String ip) throws RemoteException{
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
                ex.printStackTrace();
                System.out.println("Client: Controller couldn't subscribe himself to the lobby.");
            }
        }
        tisplayer = lobby.createPlayer("player", "ipadres");
    }
    
    /**
     * @param propertyChangeEvent
     * @throws RemoteException 
     */
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException {
        // NOG NIET AF WORK IN PROGRESS methode toevoegen om uit gamelobby te gaan.
        gamelobbys = (List<IGameLobby>)propertyChangeEvent.getNewValue();
        for(IGameLobby GL : gamelobbys)
        {
          for(IPlayer player : GL.getPlayers())
          {
              if(tisplayer == player)
              {
                INGameLobby = true;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLGameLobby.fxml"));
                  try {
                      Node root = fxmlLoader.load();
                      FXMLGameLobbyController GMC = (FXMLGameLobbyController) fxmlLoader.getController();                      
                      GMC.setGameLobby(GL);
                      HauntedClient.getStage().getScene().setRoot((Parent)root);
                  } catch (IOException ex) {
                      Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                
              }
          }
        }
    }
    public IPlayer getPlayer()
    {
        return tisplayer;
    }
    
    public void setGroupID(String groupID){
        this.groupID = groupID;
    }
}
