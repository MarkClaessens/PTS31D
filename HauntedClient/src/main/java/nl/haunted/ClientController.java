/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
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
    private InputController inputController = null;

    // Binding name for lobby
    private static final String bindingNameLobby = "lobby";

    public List<IGameLobby> getGameLobbys() {
        return gamelobbys;
    }

    public ClientController(HauntedClient client, String ip) throws RemoteException, UnknownHostException, IOException {
        gamelobbys = new ArrayList();
        this.client = client;
        startClient(ip);
        gamelobbys = lobby.getGameLobbys();
        INGameLobby = false;
        YourGL = null;

        //player = lobby.createPlayer("player", "get ip adress not implemented yeti");create return type for createplayer 
    }

    public void startClient(String ip) throws RemoteException, UnknownHostException, IOException {
        // Locate registry at ip address (server) with port 8761.
        try {
            registry = LocateRegistry.getRegistry(ip, 8761);
        } catch (RemoteException ex) {
            System.out.println("Client: following exception was found: " + ex.getMessage());
        }

        // Bind Lobby using registry.
        if (registry != null) {
            try {
                lobby = (ILobby) registry.lookup(bindingNameLobby);
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
        if (lobby != null) {
            try {
                lobby.addListener(this, "gamelobbys");
            } catch (RemoteException ex) {
                ex.printStackTrace();
                System.out.println("Client: Controller couldn't subscribe himself to the lobby.");
            }
        }           
        tisplayer = lobby.createPlayer("player",this.getIPadress());
    }
    
    
   private String getIPadress() throws SocketException, IOException{
       Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        OUTER:
        for (NetworkInterface interface_ : Collections.list(interfaces)) {
            // we shouldn't care about loopback addresses
            if (interface_.isLoopback()) {
                continue;
            }

            // if you don't expect the interface to be up you can skip this
            // though it would question the usability of the rest of the code
            if (!interface_.isUp()) {
                continue;
            }

            // iterate over the addresses associated with the interface
            Enumeration<InetAddress> addresses = interface_.getInetAddresses();
            for (InetAddress address : Collections.list(addresses)) {
                // look only for ipv4 addresses
                if (address instanceof Inet6Address) {
                    continue;
                }

                // use a timeout big enough for your needs
                if (!address.isReachable(3000)) {
                    continue;
                }

                // java 7's try-with-resources statement, so that
                // we close the socket immediately after use
                if (address.toString().indexOf("10.1.3.") > 0) {
                    System.out.println(address.getHostAddress());
                    return interface_.getInetAddresses().nextElement().getHostAddress();
                }
            }
        }
        return "";
   }

    /**
     * @param propertyChangeEvent
     * @throws RemoteException
     */
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException {

        if (!INGameLobby) {
            gamelobbys = (List<IGameLobby>) propertyChangeEvent.getNewValue();
            for (IGameLobby GL : gamelobbys) {
                for (IPlayer player : GL.getPlayers()) {
                    if (tisplayer.equals(player)) {
                        YourGL = GL;
                        INGameLobby = true;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLGameLobby.fxml"));
                        try {
                            Node root = fxmlLoader.load();
                            FXMLGameLobbyController GMC = (FXMLGameLobbyController) fxmlLoader.getController();
                            GMC.setGameLobby(GL);
                            GMC.settisPlayer(tisplayer);
                            GMC.setController();
                            GMC.setLobby(lobby);
                            HauntedClient.getStage().getScene().setRoot((Parent) root);
                        } catch (IOException ex) {
                            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
        }

    }

    public IPlayer getPlayer() {
        return tisplayer;
    }

    public void setGroupID(String groupID) throws IOException {
        this.groupID = groupID;
    }
    public void setInputController(IGameLobby gamelobby) throws IOException{
        this.inputController = new InputController(this.groupID, gamelobby);
    }
    public String getGroupID() {
        return this.groupID;
    }

    public InputController getInputController() {
        return this.inputController;
    }

    public ILobby getLobby() {
        return lobby;
    }

    public void setInGL(boolean status) {
        INGameLobby = status;
    }

}
