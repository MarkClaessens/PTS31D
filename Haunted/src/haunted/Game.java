package haunted;

import java.awt.Color;
import java.awt.geom.Point2D;
import static java.lang.Math.ceil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
     * Sets the current level of the game.
     * @param level 
     */
    public void setCurrentLevel(Level level){
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
     * Bind the characters to the players and give them a spawnposition
     */
     public List<Player> bindCharactersToPlayers(){
         // Generate random spawn positions for the characters
        Point2D ghostSpawnPosition = generateRandomGhostPoint2DLocation();
        Point2D humanSpawnPosition = generateRandomHumanPoint2DLocation();
        
        // See javadoc for more information about checkSpawnForCollisio method
        // spawnPoints.get(0) is the ghost spawnPoint and spawnPoints.get(1) is the human spawnPoint
        List<Point2D> spawnPoints = checkSpawnForCollision(ghostSpawnPosition, humanSpawnPosition);
        ghostSpawnPosition = spawnPoints.get(0);
        humanSpawnPosition = spawnPoints.get(1);
        
        // Create the characters and bind them to the players
        Ghost ghost = new Ghost(ghostSpawnPosition, Color.RED, "ghostRedDown", this);
        Human human = new Human(humanSpawnPosition, Color.BLUE, "humanBlueDown", this);
        
        // Choose random who becomes the human
        Random randomizer = new Random();
        int index = randomizer.nextInt(players.size());
        players.get(index).setCharacter(human);
        if(index == 0){
            players.get(1).setCharacter(ghost);
        }
        else{
            players.get(0).setCharacter(ghost);
        }

        
        return players;
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
    
    /**
     * Check if the random spawn positions collide with a obstacle.
     *  If so, create a new random spawn position and check for colliding again (recursive). 
     *  After that the method will return the spawnpositions.
     * 
     * @param ghostSpawnPoint
     * @param humanSpawnPoint
     * @return 
     */
    public List<Point2D> checkSpawnForCollision(Point2D ghostSpawnPointParameter, Point2D humanSpawnPointParameter){
        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.addAll(currentLevel.getObstacles());
        
        Point2D ghostSpawnPoint = ghostSpawnPointParameter;
        Point2D humanSpawnPoint = humanSpawnPointParameter;
        boolean hasCollision = false;
        
        for(Obstacle o : obstacles){
            if (o.getPosition() == ghostSpawnPoint){
                hasCollision = true;
                Point2D newRandomPosition = generateRandomGhostPoint2DLocation();
                ghostSpawnPoint.setLocation(newRandomPosition);
            }
            else if (o.getPosition() == humanSpawnPoint){
                hasCollision = true;
                Point2D newRandomPosition = generateRandomHumanPoint2DLocation();
                ghostSpawnPoint.setLocation(newRandomPosition);
            }
            else {
                hasCollision = false;
            }
        }
        
        List<Point2D> spawnPoints = new ArrayList<>();
        
        if(hasCollision){
            spawnPoints = checkSpawnForCollision(ghostSpawnPoint, humanSpawnPoint);
        }
        else{
            spawnPoints.add(ghostSpawnPoint);
            spawnPoints.add(humanSpawnPoint);
        }
             
        return spawnPoints;
    }
    
    /**
     * 
     * @return a random generated Ghost Point2D location that is inside the game map 
     */
    public Point2D generateRandomGhostPoint2DLocation(){                
        Random randomizer = new Random();
        int min = 1;
        int max = 7;
        int random = randomizer.nextInt(max - min + 1) + min;
        int randomX = random * 100;

        int min2 = 1;
        int max2 = 5;
        int random2 = randomizer.nextInt(max2 - min2 + 1) + min2;
        int randomY = random2 * 100;
        
        Point2D randomPoint2D = new Point2D.Double(randomX, randomY);
        
        return randomPoint2D;
    }
    
    /**
     * 
     * @return a random generated Human Point2D location that is inside the game map (height 1000 - width 1500)
     */
    public Point2D generateRandomHumanPoint2DLocation(){
        Random randomizer = new Random();
        int min = 8;
        int max = 14;
        int random = randomizer.nextInt(max - min + 1) + min;
        int randomX = random * 100;

        int min2 = 7;
        int max2 = 9;
        int random2 = randomizer.nextInt(max2 - min2 + 1) + min2;
        int randomY = random2 * 100;
        
        Point2D randomPoint2D = new Point2D.Double(randomX, randomY);
        
        return randomPoint2D;
    }
    
    

}


        