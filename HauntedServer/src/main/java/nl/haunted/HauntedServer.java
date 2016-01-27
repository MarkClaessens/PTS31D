package nl.haunted;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;

/**
 * This class starts the game server.
 *
 * @author Mike Evers
 */
public class HauntedServer {

    private static final int portNumber = 8761;
    private static final String bindingNameLobby = "lobby";
    private static Registry registry = null;
    private Lobby lobby = null;

    // Constructor

    /**
     *
     */
        public HauntedServer() {
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
            System.setProperty("java.rmi.server.hostname", "10.1.3.5");
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
        System.setProperty("java.rmi.server.hostname", "10.1.3.2");
        try {
            // Create server
            RMISocketFactory.setSocketFactory(new MyServerRMISocketFactory());
        } catch (IOException ex) {
        }

        HauntedServer server = new HauntedServer();
    }
}
