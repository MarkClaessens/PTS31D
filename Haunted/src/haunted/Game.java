package haunted;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;
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
    private int currentRound = 0; //value is -1 because the first round (=floor) is equal to 0. 
    private Timer tickTimer;
    private Level currentLevel;
    private boolean isRunning = false; // flag that indicates if the game is active, like players are playing.
    private List<Ghost> ghosts = new ArrayList();
    private Human human;
    private boolean isPauzed = false;

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
     * creates the game
     *
     * @param players List can not be empty.
     * @param floors Cannot be 0..
     * @throws java.io.IOException
     */
    public Game(List<Player> players, int floors) throws IOException {
        this.players = players;
        this.floorAmount = floors;
        this.isRunning = false;
        this.isPauzed = true;
        this.currentLevel = new Level(0);
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
        String[] ghoSpritesUp = new String[]{"ghostRedUp1.png", "ghostRedUp2.png", "ghostRedUp3.png"};
        String[] ghoSpritesDown = new String[]{"ghostRedDown1.png", "ghostRedDown2.png", "ghostRedDown3.png"};
        String[] ghoSpritesLeft = new String[]{"ghostRedLeft1.png", "ghostRedLeft2.png", "ghostRedLeft3.png"};
        String[] ghoSpritesRight = new String[]{"ghostRedRight1.png", "ghostRedRight2.png", "ghostRedRight3.png"};
        Ghost ghost = new Ghost(ghostSpawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, this);
        ghosts.add(ghost);

        String[] humSpritesUp = new String[]{"humanBlueUp1.png", "humanBlueUp2.png", "humanBlueUp3.png"};
        String[] humSpritesDown = new String[]{"humanBlueDown1.png", "humanBlueDown2.png", "humanBlueDown3.png"};
        String[] humSpritesLeft = new String[]{"humanBlueLeft1.png", "humanBlueLeft2.png", "humanBlueLeft3.png"};
        String[] humSpritesRight = new String[]{"humanBlueRight1.png", "humanBlueRight2.png", "humanBlueRight3.png"};
        human = new Human(humanSpawnPosition, Color.BLUE, humSpritesUp, humSpritesDown, humSpritesLeft, humSpritesRight, this);

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
        this.isRunning = true;
        this.isPauzed = false;
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
     * Will be called when the game is done. Sets isRunning to false. There
     * might be a victory screen.
     *
     * @param The player who has won the game.
     */
    public void endGame(Player winner) {
        this.isRunning = false;
        FXMLvictoryController VC = (FXMLvictoryController) Haunted.getNavigation().load(FXMLvictoryController.URL_FXML);
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

    public List<Ghost> getGhosts() {
        return this.ghosts;
    }

    public Human getHuman() {
        return this.human;
    }
}
