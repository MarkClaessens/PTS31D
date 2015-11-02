package haunted;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;
import static java.lang.Math.ceil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.logging.Logger;
import java.util.TimerTask;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Mal && Mike
 */
public class Game {

    private Scene scene;
    private List<Player> players;
    private int floorAmount;
    private int currentRound = 0; //value is -1 because the first round (=floor) is equal to 0. 
    private Timer tickTimer;
    private Level currentLevel;
    private boolean isRunning = false; // flag that indicates if the game is active, like players are playing.
    private List<Ghost> ghosts = new ArrayList();
    private Human human;
    private boolean isPaused = false;
    private Timer timer;
    private MainGameFX gameFX;
    public Calendar cl;

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
        this.isPaused = true;
        this.currentLevel = new Level(0);
        this.gameFX = new MainGameFX();
        setupGameClasses();
        this.bindCharactersToPlayers();        
        cl = Calendar.getInstance();

    }

    /**
     * Create the spawnPositions for the Characters and create their objects.
     */
    public void setupGameClasses() {
        // Pick the spawn positions for the characters
        Point2D ghostSpawnPosition = pickRandomGhostSpawnPoint();
        Point2D humanSpawnPosition = pickRandomHumanSpawnPoint();

        // Create the characters and bind them to the players
        String[] ghoSpritesUp = new String[]{"GhostRedUp1.png", "GhostRedUp2.png", "GhostRedUp3.png"};
        String[] ghoSpritesDown = new String[]{"GhostRedDown1.png", "GhostRedDown2.png", "GhostRedDown3.png"};
        String[] ghoSpritesLeft = new String[]{"GhostRedLeft1.png", "GhostRedLeft2.png", "GhostRedLeft3.png"};
        String[] ghoSpritesRight = new String[]{"GhostRedRight1.png", "GhostRedRight2.png", "GhostRedRight3.png"};
        Ghost ghost = new Ghost(ghostSpawnPosition, Color.RED, ghoSpritesUp, ghoSpritesDown, ghoSpritesLeft, ghoSpritesRight, this);
        ghosts.add(ghost);

        String[] humSpritesUp = new String[]{"HumanBlueUp1.png", "HumanBlueUp2.png", "HumanBlueUp3.png"};
        String[] humSpritesDown = new String[]{"HumanBlueDown1.png", "HumanBlueDown2.png", "HumanBlueDown3.png"};
        String[] humSpritesLeft = new String[]{"HumanBlueLeft1.png", "HumanBlueLeft2.png", "HumanBlueLeft3.png"};
        String[] humSpritesRight = new String[]{"HumanBlueRight1.png", "HumanBlueRight2.png", "HumanBlueRight3.png"};
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
        human.setPosition(pickRandomHumanSpawnPoint());
        human.setHasKey(false);
        for(Ghost g : ghosts)
        {
            g.setPosition(pickRandomGhostSpawnPoint());
        }
        try {
            this.currentLevel = new Level(currentRound);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.isRunning = true;
        this.isPaused = false;
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    long l = System.currentTimeMillis() - cl.getTimeInMillis();
                    cl = Calendar.getInstance();
                    tick();
                }
            };
            timer.scheduleAtFixedRate(task, 0, 16);               
    }

    /**
     * ends the current round, after this the next level will be generated and
     * the current round will increase with one.
     */
    public void endRound() {
        timer.cancel();
        if (this.currentRound >= this.floorAmount) {
            this.players.stream().filter((P) -> (P.getCharacter() instanceof Human)).forEach((P) -> {
                this.endGame(P);
            });
        } else {
            scene = gameFX.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLnextroundController.URL_FXML));
        try {
            Node root = fxmlLoader.load();
            Controller controller = fxmlLoader.getController();
            controller.setView(root);
            FXMLnextroundController NRC = (FXMLnextroundController) fxmlLoader.getController();
            NRC.setGame(this);
            NRC.setStage(gameFX.getStage());
            scene.setRoot((Parent) NRC.getView());            
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
        scene = gameFX.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLvictoryController.URL_FXML));
        try {
            Node root = fxmlLoader.load();
            Controller controller = fxmlLoader.getController();
            controller.setView(root);
            FXMLvictoryController VC = (FXMLvictoryController) fxmlLoader.getController();
            VC.setWinnaarnaam(winner.getName());
            scene.setRoot((Parent) VC.getView());            
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

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
        if (this.isRunning && !this.isPaused) {
            if (!this.ghosts.isEmpty()) {
                Object[] keyboard = this.gameFX.getPressedKeys();
                if (keyboard != null) {
                    if ((boolean) keyboard[2]) {
                        this.isPaused = !this.isPaused;
                    }

                    for (int i = 0; i < this.players.size(); i++) {
                        if (keyboard[i] != null) {
                            this.players.get(i).getCharacter().move((DirectionType) keyboard[i]);
                        } else {
                            if(this.players.get(i).getCharacter() instanceof Ghost){
                                Ghost g = (Ghost) this.players.get(i).getCharacter();
                                if(g.getIsMoving()){
                                    if(this.players.get(i).getCharacter() instanceof Ghost){                                       
                                        g.setStationaryTime();
                                     }                                   
                                }
                            }  
                            this.players.get(i).getCharacter().setIsMoving(false);
                        }
                    }
                }
                this.human.checkInteract();
                this.ghosts.stream().forEach((G) -> {
                    G.changeAppearance();
                    if (G.getBeginSpawnTime() != null) {
                        if (System.currentTimeMillis() >= (G.getBeginSpawnTime().getTimeInMillis() + 2000)) {
                            G.setPosition(this.pickRandomGhostSpawnPoint());
                            G.setBeginSpawnTime(null);
                        }
                    }
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
    public Scene getScene(){
        return scene;
    }
    public void setgameFX(MainGameFX gameFX)
    {
        this.gameFX = gameFX;
    }
}
