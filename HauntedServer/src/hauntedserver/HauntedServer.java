package hauntedserver;

import java.rmi.registry.Registry;

/**
 * This class starts the game server. 
 * @author Mike Evers
 */
public class HauntedServer {

    // Set port number
    private static final int portNumber = 1099;

    // Set binding name for lobby
    private static final String bindingName = "lobby";

    // Create references to registry and lobby
    private final Registry registry = null;
    private Lobby lobby = null;
    
    // Constructor
    public HauntedServer(){
        // Create the lobby
        lobby = new Lobby();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
}
