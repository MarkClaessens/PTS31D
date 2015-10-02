package haunted;

import java.util.List;
import java.util.Timer;

/**
 *
 * @author Mal
 */
public class Game {
    
    private List<Player> players;
    private int floorAmount;
    private int currentRound;
    private int currentFloor;
    private Timer tickTimer;

    /**
     * @return the list of Players that are playing the game.
     */
    public List<Player> getPlayers(){
        return this.players;
    }
    /**
     * gives amount of floors the game contains
     *
     * @return
     */
    public int getFloorAmount() {
        return this.floorAmount;
    }

    /**
     * sets amount of floors the game will contain
     *
     * @param floorAmount
     */
    public void setFloorAmount(int floorAmount) {
        this.floorAmount = floorAmount;
    }

    /**
     * gives current round number
     *
     * @return
     */
    public int getCurrentRound() {
        return this.currentRound;
    }

    /**
     * sets current round number
     *
     * @param currentRound
     */
    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    /**
     * gives current floor number of the game
     *
     * @return
     */
    public int getCurrentFloor() {
        return this.currentFloor;
    }

    /**
     * sets current floor number of the game
     *
     * @param currentFloor
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * gives the tick timer
     *
     * @return
     */
    public Timer getTickTimer() {
        return this.tickTimer;
    }

    /**
     * sets the tick timer
     *
     * @param tickTimer
     */
    public void setTickTimer(Timer tickTimer) {
        this.tickTimer = tickTimer;
    }

    /**
     * creates the game
     *
     * @param players List can not be empty.
     * @param floors Cannot be 0.
     */
    public Game(List<Player> players, int floors) {
        // TODO - implement Game.Game
        throw new UnsupportedOperationException();
    }

    /**
     * sets the game to the next level
     *
     * @param floor Mike: why do we use a parameters???
     */
    public void nextLevel(int floor) {
        // TODO - implement Game.nextLevel
        throw new UnsupportedOperationException();
    }

    /**
     * starts the next round
     */
    public void startRound() {
        // TODO - implement Game.startRound
        throw new UnsupportedOperationException();
    }

    /**
     * ends the current round
     */
    public void endRound() {
        // TODO - implement Game.endRound
        throw new UnsupportedOperationException();
    }

    /**
     * ends the game
     */
    public void endGame() {
        // TODO - implement Game.endGame
        throw new UnsupportedOperationException();
    }

    /**
     * player leaves the game
     * @param player the player who wants to leave the game.
     */
    public void leaveGame(Player player) {
        // TODO - implement Game.leaveGame
        throw new UnsupportedOperationException();
    }

    /**
     * everything that needs to be checked every tick from the timer
     */
    public void tick() {
        // TODO - implement Game.tick
        throw new UnsupportedOperationException();
    }

}
