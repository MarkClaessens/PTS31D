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
import java.util.logging.Logger;
import java.util.TimerTask;

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
    private Thread tickThread;
    private MainGameFX gameFX;

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

    public MainGameFX getFX() {
        return gameFX;
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
        this.setupGameClasses();
        this.bindCharactersToPlayers();
        this.gameFX = new MainGameFX();

    }

    /**
     * Create the spawnPositions for the Characters and create their objects.
     */
    public void setupGameClasses() {
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
    }

    /**
     * Bind the characters to the players at random
     */
    public void bindCharactersToPlayers() {
        // Choose random who becomes the human
        Random randomizer = new Random();
        int index = randomizer.nextInt(players.size());
        players.get(index).setCharacter(human);
        if (index == 0) {
            players.get(1).setCharacter(ghosts.get(0));
        } else {
            players.get(0).setCharacter(ghosts.get(0));
        }
    }

    /**
     * sets the game to the next level Increases the currentRound with 1,
     * generates a new level object and calls the startRound method.
     */
    public void nextLevel() {
        // TODO - implement Game.nextLevel
        endRound();
        try {
            this.currentLevel = new Level(currentRound);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        startRound();
    }

    /**
     * starts the next round at the current floor.
     */
    public void startRound() {

        this.isRunning = true;
        this.isPauzed = false;
        tickThread = new Thread(new Runnable() {

            @Override
            public void run() {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {

                    @Override
                    public void run() {
                        tick();
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 16);
            }
        });
        gameFX.setShowEmpty(false);
    }

    /**
     * ends the current round, after this the next level will be generated and
     * the current round will increase with one.
     */
    public void endRound() {
        tickThread.interrupt();
        gameFX.setShowEmpty(true);
        if (this.currentRound >= this.floorAmount) {
            this.players.stream().filter((P) -> (P.getCharacter() instanceof Human)).forEach((P) -> {
                this.endGame(P);
            });
        } else {
            this.currentRound++;
        }
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
        if (this.isRunning && !this.isPauzed) {
            if (!this.ghosts.isEmpty()) {
                Object[] keyboard = this.gameFX.getPressedKeys();
                this.gameFX.clearPressedKeys();

                if ((boolean) keyboard[2]) {
                    this.isPauzed = !this.isPauzed;
                }

                for (int i = 0; i <= this.players.size(); i++) {
                    if (keyboard[i] != null) {
                        this.players.get(i).getCharacter().move((DirectionType) keyboard[i]);
                        if (this.players.get(i).getCharacter() instanceof Ghost) {
                            this.players.get(i).getCharacter().setIsMoving(true);
                        }
                    } else if (this.players.get(i).getCharacter() instanceof Ghost) {
                        Ghost G = (Ghost) this.players.get(i).getCharacter();
                        if (G.isIsMoving()) {
                            G.setIsMoving(false);
                            G.setStationaryTime();
                        }
                    }
                }
                this.human.checkInteract();
                this.ghosts.stream().forEach((G) -> {
                    G.changeAppearance();
                });
            } else {
                this.endRound();
            }
            gameFX.setItems(this);
        }
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
        Point2D spawnPoint = spawnPoints[randomizer.nextInt(5)];

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
        Point2D spawnPoint = spawnPoints[randomizer.nextInt(5)];

        return spawnPoint;
    }

    public List<Ghost> getGhosts() {
        return this.ghosts;
    }

    public Human getHuman() {
        return this.human;
    }
}
