package haunted;

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
        // Generate random spawn positions for the characters
        Point2D ghostSpawnPosition = generateRandomPoint2DLocation();
        Point2D humanSpawnPosition = generateRandomPoint2DLocation();
        
        // See javadoc for more information
        checkSpawnForCollision(ghostSpawnPosition, humanSpawnPosition);
        
        
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
                Point2D newRandomPosition = generateRandomPoint2DLocation();
                ghostSpawnPoint.setLocation(newRandomPosition);
            }
            else if ( o.getPosition() == humanSpawnPoint){
                hasCollision = true;
                Point2D newRandomPosition = generateRandomPoint2DLocation();
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
            spawnPoints.add(humanSpawnPoint);
            spawnPoints.add(ghostSpawnPoint);
        }
             
        return spawnPoints;
    }
    
    /**
     * 
     * @return a random generated Point2D location that is inside the game map (height 1000 - width 1500)
     */
    public Point2D generateRandomPoint2DLocation(){
        Random randomizer = new Random();
        double randomX = ceil(100 + ((1400 - 100) * randomizer.nextDouble()));
        double randomY = ceil(100 + ((1000 - 100) * randomizer.nextDouble()));
        
        Point2D randomPoint2D = new Point2D.Double(randomX, randomY);
        
        return randomPoint2D;
    }

}
