package haunted;

import java.util.List;
import java.util.Timer;

public class Game {

	private int floorAmount;
	private int currentRound;
	private int currentFloor;
	private Timer tickTimer;

	public int getFloorAmount() {
		return this.floorAmount;
	}

	public void setFloorAmount(int floorAmount) {
		this.floorAmount = floorAmount;
	}

	public int getCurrentRound() {
		return this.currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public int getCurrentFloor() {
		return this.currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public Timer getTickTimer() {
		return this.tickTimer;
	}

	public void setTickTimer(Timer tickTimer) {
		this.tickTimer = tickTimer;
	}

	/**
	 * 
	 * @param players
	 * @param floors
	 */
	public Game(List<Player> players, int floors) {
		// TODO - implement Game.Game
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param floor
	 */
	public void nextLevel(int floor) {
		// TODO - implement Game.nextLevel
		throw new UnsupportedOperationException();
	}

	public void startRound() {
		// TODO - implement Game.startRound
		throw new UnsupportedOperationException();
	}

	public void endRound() {
		// TODO - implement Game.endRound
		throw new UnsupportedOperationException();
	}

	public void endGame() {
		// TODO - implement Game.endGame
		throw new UnsupportedOperationException();
	}

	public void leaveGame() {
		// TODO - implement Game.leaveGame
		throw new UnsupportedOperationException();
	}

	public void tick() {
		// TODO - implement Game.tick
		throw new UnsupportedOperationException();
	}

}