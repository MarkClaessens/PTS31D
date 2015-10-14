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
    private int currentRound = -1; //value is -1 because the first round (=floor) is equal to 0. 
    private Timer tickTimer;
    private Level currentLevel;
    private boolean isRunning = false;

    /**
     * @return if the game isRunning (boolean)
     */
    public boolean getIsRunning() {
        return this.isRunning;
    }

    /**
     * @return the current level of the game
     */
    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    /**
     * @return the list of Players that are playing the game.
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * @return amount of floors that the game contains.
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
     * @return current round number
     */
    public int getCurrentRound() {
        return this.currentRound;
    }

    /**
     * @return the tick timer
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
     * sets the game to the next level Increases the currentRound with 1,
     * generates a new level object and calls the startRound method.
     */
    public void nextLevel() {
        // TODO - implement Game.nextLevel
        throw new UnsupportedOperationException();
    }

    /**
     * starts the next round at the current floor.
     */
    public void startRound() {
        // TODO - implement Game.startRound
        throw new UnsupportedOperationException();
    }

    /**
     * ends the current round, after this the next level will be generated and
     * the current round will increase with one.
     */
    public void endRound() {
        // TODO - implement Game.endRound
        throw new UnsupportedOperationException();
    }

    /**
     * Will be called when the game is done. isRunning has to be false. There
     * might be a victory screen.
     */
    public void endGame() {
        // TODO - implement Game.endGame
        throw new UnsupportedOperationException();
    }

    /**
     * player leaves the game First iteration doesn't need a parameter because
     * we only have two player.
     */
    public void leaveGame() {
        // TODO - implement Game.leaveGame
        throw new UnsupportedOperationException();
    }

    /**
     * Engine of the game. Makes al calculations for the game and checks if
     * there are any changes to the game state.
     */
    public void tick() {
        // TODO - implement Game.tick
        throw new UnsupportedOperationException();
    }

}
