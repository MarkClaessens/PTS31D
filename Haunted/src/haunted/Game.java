package haunted;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import static java.lang.Math.ceil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Mal && Mike
 */
public class Game {

    private List<Player> players;
    private int floorAmount;
    private int currentRound = -1; //value is -1 because the first round (=floor) is equal to 0. 
    private Timer tickTimer;
    private Level currentLevel;
    private boolean isRunning = false; // flaf that indicates if the game is active, like players are playing.

    /**
     * @return if the game isRunning (boolean)
     */
    public boolean getIsRunning() {
        return this.isRunning;
    }

    /**
     * Sets if the game is running or not.
     *
     * @param isRunning
     */
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * @return the current level of the game
     */
    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    /**
     * Sets the current level of the game.
     *
     * @param level
     */
    public void setCurrentLevel(Level level) {
        this.currentLevel = level;
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
     * @param floors Cannot be 0..
     */
    public Game(List<Player> players, int floors) {
        this.players = players;
        this.floorAmount = floors;
    }

    /**
     * Bind the characters to the players and give them a spawnposition Call
     * this method after the game generated his level and sets his obstacles [ !
     * ]
     */
    public void bindCharactersToPlayers() {
        // Pick the spawn positions for the characters
        Point2D ghostSpawnPosition = pickRandomGhostSpawnPoint();
        Point2D humanSpawnPosition = pickRandomHumanSpawnPoint();

        // Create the characters and bind them to the players
        String[] ghoSpritesUp = new String[]{"ghostRedUp1.gif", "ghostRedUp2.gif", "ghostRedUp3.gif"};
        String[] ghoSpritesDown = new String[]{"ghostRedDown1.gif", "ghostRedDown2.gif", "ghostRedDown3.gif"};
        String[] ghoSpritesLeft = new String[]{"ghostRedLeft1.gif", "ghostRedLeft2.gif", "ghostRedLeft3.gif"};
        String[] ghoSpritesRight = new String[]{"ghostRedRight1.gif", "ghostRedRight2.gif", "ghostRedRight3.gif"};
        Ghost ghost = new Ghost(ghostSpawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, this);

        String[] humSpritesUp = new String[]{"humanBlueUp1.gif", "humanBlueUp2.gif", "humanBlueUp3.gif"};
        String[] humSpritesDown = new String[]{"humanBlueDown1.gif", "humanBlueDown2.gif", "humanBlueDown3.gif"};
        String[] humSpritesLeft = new String[]{"humanBlueLeft1.gif", "humanBlueLeft2.gif", "humanBlueLeft3.gif"};
        String[] humSpritesRight = new String[]{"humanBlueRight1.gif", "humanBlueRight2.gif", "humanBlueRight3.gif"};
        Human human = new Human(humanSpawnPosition, Color.BLUE, humSpritesUp, humSpritesDown, humSpritesLeft, humSpritesRight, this);

        // Choose random who becomes the human
        Random randomizer = new Random();
        int index = randomizer.nextInt(players.size());
        players.get(index).setCharacter(human);
        if (index == 0) {
            players.get(1).setCharacter(ghost);
        } else {
            players.get(0).setCharacter(ghost);
        }
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
     * Will be called when the game is done. Sets isRunning to  false. There
     * might be a victory screen.
     *
     * @param The player who has won the game.
     */
    public void endGame(Player winner) {
       this.isRunning = false;
            FXMLvictoryController VC = (FXMLvictoryController)Haunted.getNavigation().load(FXMLvictoryController.URL_FXML);
             VC.setWinnaarnaam(winner.getName());
             VC.Show();
       // TODO: call in view (FXML) the method to launch the victory screen with the winner (inside the parameter)
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

    /**
     *
     * @return The location on the map where the Ghost will spawn.
     */
    public Point2D pickRandomGhostSpawnPoint() {
        // Make an array with the points where the Ghost is allowed to spawn.
        Point2D[] spawnPoints = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 100),
            new Point2D.Double(0, 200),
            new Point2D.Double(100, 0),
            new Point2D.Double(200, 0)
        };

        // Take a random point from the array. This is where the human will spawn.
        Random randomizer = new Random();
        Point2D spawnPoint = spawnPoints[randomizer.nextInt(6)];

        return spawnPoint;
    }

    /**
     *
     * @return The location on the map where the Human will spawn.
     */
    public Point2D pickRandomHumanSpawnPoint() {
        // Make an array with the points where the human is allowed to spawn.
        Point2D[] spawnPoints = new Point2D[]{
            new Point2D.Double(1400, 900),
            new Point2D.Double(1400, 800),
            new Point2D.Double(1400, 700),
            new Point2D.Double(1300, 900),
            new Point2D.Double(1200, 900)
        };

        // Take a random point from the array. This is where the human will spawn.
        Random randomizer = new Random();
        Point2D spawnPoint = spawnPoints[randomizer.nextInt(6)];

        return spawnPoint;
    }

}
