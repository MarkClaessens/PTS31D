package haunted;

public class GameLobby {

	private Player Host;
	private String name;
	private String password;
	private int maxPlayers;
	private int floorAmount;
/**
 * gives the host of the game
 * @return 
 */
	public Player getHost() {
		return this.Host;
	}
/**
 * sets host of the game
 * @param Host 
 */
	public void setHost(Player Host) {
		this.Host = Host;
	}
/**
 * gives name of the gamelobby
 * @return 
 */
	public String getName() {
		return this.name;
	}
/**
 * sets name of the gamelobby
 * @param name 
 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * gives password of the gamelobby
 * @return 
 */
	public String getPassword() {
		return this.password;
	}
/**
 * sets password of the gamelobby
 * @param password 
 */
	public void setPassword(String password) {
		this.password = password;
	}
/**
 * gives maximal number of players for the gamelobby
 * @return 
 */
	public int getMaxPlayers() {
		return this.maxPlayers;
	}
/**
 * sets maximal number of players for the gamelobby
 * @param maxPlayers 
 */
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
/**
 * gives amount of floors for the game
 * @return 
 */
	public int getFloorAmount() {
		return this.floorAmount;
	}
/**
 * sets amount of floors for the game
 * @param floorAmount 
 */
	public void setFloorAmount(int floorAmount) {
		this.floorAmount = floorAmount;
	}

	/**
	 * create a gamelobby
	 * @param name
	 * @param password
	 * @param host
	 */
	public GameLobby(String name, String password, Player host) {
		// TODO - implement GameLobby.GameLobby
		throw new UnsupportedOperationException();
	}
/**
 * you can start the game when everyone is ready.
 */
	public void startGame() {
		// TODO - implement GameLobby.startGame
		throw new UnsupportedOperationException();
	}
/**
 * set status to ready/not ready, depends on current status.
 */
	public void changeReadyStatus() {
		// TODO - implement GameLobby.changeReadyStatus
		throw new UnsupportedOperationException();
	}
/**
 * kick player from gamelobby
 */
	public void removePlayer() {
		// TODO - implement GameLobby.removePlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * send a message to the other players
	 * @param message
	 */
	public void sendMessage(String message) {
		// TODO - implement GameLobby.sendMessage
		throw new UnsupportedOperationException();
	}

	/**
	 * add a player to the gamelobby
	 * @param player
	 */
	public void addPlayer(Player player) {
		// TODO - implement GameLobby.addPlayer
		throw new UnsupportedOperationException();
	}
/**
 * checks if all players are ready
 * @return 
 */
	public boolean readyCheck() {
		// TODO - implement GameLobby.readyCheck
		throw new UnsupportedOperationException();
	}

}