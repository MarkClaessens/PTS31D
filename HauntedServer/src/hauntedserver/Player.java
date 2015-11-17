import Server.*;

public class Player extends UnicastRemoteObject implements IPlayer, IPlayer {

	BasicPublisher uitgever;
	private string name;
	private boolean ready;
	private DirectionType input;
	private string color;
	private string ipAdress;

	/**
	 * 
	 * @param name
	 * @param ipAdress
	 */
	public Player(string name, string ipAdress) {
		// TODO - implement Player.Player
		throw new UnsupportedOperationException();
	}

	public void toggleReady() {
		// TODO - implement Player.toggleReady
		throw new UnsupportedOperationException();
	}

}