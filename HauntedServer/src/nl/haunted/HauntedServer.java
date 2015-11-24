package nl.haunted;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * This class starts the game server. 
 * @author Mike Evers
 */
public class HauntedServer {
    private static final int portNumber = 1099;
    private static final String bindingNameLobby = "lobby";
    private static Registry registry = null;
    private Lobby lobby = null;
    
    // Constructor
    public HauntedServer(){
        try {
            // Create the lobby
            lobby = new Lobby();         
        } catch (RemoteException ex) {
            System.out.println("Server: RemoteException: " + ex.getMessage());
            System.out.println("Server: Cannot create lobby, it's null now.");
            lobby = null;
        }
        
        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: RemoteException: " + ex.getMessage());
            System.out.println("Server: Cannot create registry, it's null now.");
            registry = null;
        }
                
        // Bind lobby using registry
        try {  
            registry.rebind(bindingNameLobby, lobby);
        } catch (RemoteException ex) {
            System.out.println("Server: RemoteException: " + ex.getMessage());
            System.out.println("Server: Cannot bind lobby to registry.");
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create serversdasd
        HauntedServer server = new HauntedServer();
        System.setSecurityManager(new SecurityManager());
    }    
}
