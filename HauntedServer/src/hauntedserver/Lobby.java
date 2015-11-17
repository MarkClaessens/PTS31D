import Server.*;

public class Lobby extends UnicastRemoteObject implements ILobby, ILobby {

	BasicPublisher uitgever;
	private Lobby singleton;

	private Lobby() {
		// TODO - implement Lobby.Lobby
		throw new UnsupportedOperationException();
	}

	public Lobby getInstance() {
		// TODO - implement Lobby.getInstance
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param lobby
	 * @param players
	 */
	public boolean joinGameLobby(GameLobby lobby, List<Player> players) {
		// TODO - implement Lobby.joinGameLobby
		throw new UnsupportedOperationException();
	}

	public void refresh() {
		// TODO - implement Lobby.refresh
		throw new UnsupportedOperationException();
	}

	public void exit() {
		// TODO - implement Lobby.exit
		throw new UnsupportedOperationException();
	}

}