import Server.*;
import java.util.*;

public class GameLobby extends UnicastRemoteObject implements IGameLobby, IGameLobby {

	Collection<Player> bezoeker;
	private string name;
	private int maxPlayer;
	private int maxFloors;
	private string password;

	public void removePlayer() {
		// TODO - implement GameLobby.removePlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public void addPlayer(Player player) {
		// TODO - implement GameLobby.addPlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param password
	 * @param host
	 */
	public GameLobby(String name, String password, Player host) {
		// TODO - implement GameLobby.GameLobby
		throw new UnsupportedOperationException();
	}

	public boolean readyCheck() {
		// TODO - implement GameLobby.readyCheck
		throw new UnsupportedOperationException();
	}

}