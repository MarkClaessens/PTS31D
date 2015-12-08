/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Mal + Mike
 */
public class Game {

    private final int floorAmount; // starts at 1
    private int currentFloor = -1; // starts at 0 so the init has to be -1.
    private Timer tickTimer;
    private boolean roundEnded, nextRound; 
    private Level level;
    private List<Ghost> ghosts;
    private Human human;
    private List<IPlayer> players;
    private IPlayer currentHuman;
    private Socket srvSoc;
    private IGameLobby gameLobby;
    
    public Level getLevel() {
        return level;
    }

    public List<IPlayer> getPlayers() {
        return players;
    }

    /**
     *
     * @return the amount of floors in this game.
     */
    public int getFloorAmount() {
        return floorAmount;
    }

    /**
     *
     * @return the current floor of the game, starts at 0.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    public List<Ghost> getGhosts() {
        return this.ghosts;
    }

    /**
     *
     * @param players
     * @param floors
     * @param groupID
     * @param gl
     * @throws java.io.IOException
     */
    public Game(List<IPlayer> players, int floors, String groupID, IGameLobby gl) throws IOException {
        this.players = players;
        //Setup the socket for this game;
        srvSoc = new Socket();
        srvSoc.socketSetup(groupID, 9876);
        Random randomizer = new Random();
        // the minimum floors is hardcoded to 3 right now.
        this.floorAmount = randomizer.nextInt(floors - 3 + 1) + 3;
        this.currentFloor = 0;
        this.gameLobby = gl;
        		
	// Create the characters and bind them to the players.
        bindCharactersToPlayers();
        
        // Create the first level.
        nextLevel();	
    }

    /**
     * Creates the new level object when a level has finished.
     */
    public void nextLevel() {
        this.currentFloor++;
        this.level = new Level(this.currentFloor, this.ghosts.size());
    }

    /**
     *  starts the next round at the current floor.
     */
    public void startRound() {    
        this.roundEnded = false;
        this.tickTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {           
                try {
                    tick();
                    
                } catch (IOException | InterruptedException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                } 
            }
        };        
        this.tickTimer.scheduleAtFixedRate(task, 0, 16);
    }

    /**
     *  Ends the current round and opens the counting screen. 
     */
    public void endRound() {
        this.roundEnded = true;
        
        human.setPosition(pickHumanSpawnpoint());
        human.setHasKey(false);
        human.setMoving(false);
        
        for (Ghost ghost : ghosts) {
            ghost.reset();
            ghost.setPosition(pickGhostSpawnPoint(true));
        }
        
        nextLevel();
    }

    /**
     * Will be called when the last level is played. After calling the victory
     * screen will be shown.
     *
     * @param winner the player that wins the game by entering the last door.
     * @throws java.rmi.RemoteException
     * @throws java.lang.InterruptedException
     */
    public void endGame(IPlayer winner) throws RemoteException, InterruptedException {
        for(IPlayer player : this.players){
            player.reset();
        }
        this.roundEnded = true;
        this.nextRound = false;
        
        // Wait 500 milliseconds (tick timer needs to go one some time) before cancelling ticking.
        Thread.sleep(500);
        this.tickTimer.cancel();
        GameLobby gameLobby = (GameLobby)this.gameLobby;
        gameLobby.getLobby().removeGLAfterGame(gameLobby);
    }

    /**
     * The player leaves the game (gamelobby).
     * @param player the player that wants to leave the game.
     * @throws java.rmi.RemoteException
     */
    public synchronized void leaveGame(Player player) throws RemoteException {
        if(player.getCharacter() instanceof Human){
           Random randomizer = new Random();
           int randomInt = randomizer.nextInt(this.players.size() - 1);
           this.players.get(randomInt).setCharacter(human);
        }
        
        player.setCharacter(null);
        this.players.remove(player);
    }

    /**
     *
     * @throws java.rmi.RemoteException
     * @throws java.net.UnknownHostException
     * @throws java.lang.ClassNotFoundException
     */
    public void tick() throws RemoteException, UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        //check if the game is running and not in the pause screen (between rounds)
        if (this.roundEnded) {
        } else {
            //check if the list of ghosts is empty
            // <editor-fold defaultstate="collapsed" desc="if there are ghosts">
            if (!this.ghosts.isEmpty()) {
                DirectionType[] keyboard = this.getPlayerInput();

                // <editor-fold defaultstate="collapsed" desc="if there is a pressed key TODO: get keypresses">
                if (keyboard != null) {
                    //go through every player
                    for (int i = 0; i < this.players.size(); i++) {
                        //check if the player pressed a button
                        if (keyboard[i] != null) {
                            this.players.get(i).getCharacter().move((DirectionType) keyboard[i]);
                        } else {

                            // <editor-fold defaultstate="collapsed" desc="set moving and check if a ghost needs to become a wall">
                            //if the player didnt press a button and is a ghost check if it was moving.
                            //if it was moving set the stationary time
                            //set the characters moving on false
                            if (this.players.get(i).getCharacter() instanceof Ghost) {
                                Ghost g = (Ghost) this.players.get(i).getCharacter();
                                if (g.getMoving()) {
                                    if (this.players.get(i).getCharacter() instanceof Ghost) {
                                        g.setStationaryTime();
                                    }
                                }
                            }
                            this.players.get(i).getCharacter().setMoving(false);
                            //</editor-fold>
                        }
                    }
                }
                //</editor-fold>
                this.human.checkInteract();
                // <editor-fold defaultstate="collapsed" desc="loop  to change ghosts to wall and respawn them">
                this.ghosts.stream().forEach((G) -> {
                    G.changeAppearance();
                    if (G.getTimeOfDeath() != null) {
                        if (System.currentTimeMillis() >= (G.getTimeOfDeath().getTimeInMillis() + 2000)) {
                            G.setPosition(this.pickGhostSpawnPoint(false));

                            G.setTimeOfDeath();
                        }
                    }
                });
                //</editor-fold>

            } // if there are ghosts </editor-fold> 
            else { // if there are no ghosts.
                this.endRound();
            }
            srvSoc.sendObject(this.compressGameInfo());
        } // server runnin
        // server runnin 
    }

    /**
     *
     * @return @throws java.net.UnknownHostException
     * @throws java.rmi.RemoteException
     */
    public Object[][] compressGameInfo() throws UnknownHostException, RemoteException {
        Object[][] obj;
        obj = new Object[this.ghosts.size() + 6][7];
        obj[0][0] = "Server";
        obj[0][1] = InetAddress.getLocalHost();
        obj[0][2] = this.level.getBackgroundInt();
        obj[1][0] = this.level.getGhostLifePool();
        obj[1][1] = this.level.getCurrentFoor();
        obj[1][2] = this.human;
        obj[1][3] = !this.human.hasKey();
        obj[1][4] = this.roundEnded;
        obj[1][5] = this.nextRound;
        obj[1][6] = this.ghosts.size();
        obj[2][0] = new Point2D.Double(this.human.getFlashlightPolygon()[0], this.human.getFlashlightPolygon()[1]);
        obj[2][1] = new Point2D.Double(this.human.getFlashlightPolygon()[2], this.human.getFlashlightPolygon()[3]);
        obj[2][2] = new Point2D.Double(this.human.getFlashlightPolygon()[4], this.human.getFlashlightPolygon()[5]);
        obj[3][0] = this.level.getKeyLocation();
        obj[4][0] = this.human.getPosition();
        obj[4][1] = this.human.getDirection();
        obj[4][2] = this.human.getMoving();
        obj[4][3] = this.currentHuman.getColor();
        obj[5][0] = this.level.getDoorLocation();
        obj[5][1] = DirectionType.DOWN;

        //ghostloop
        int i = 6;
        for (IPlayer p : players) {
            for (Ghost G : this.ghosts) {
                if (p.getCharacter().equals(G)) {
                    if (G.getDead()) {
                        obj[i][0] = G.getPosition();
                        obj[i][1] = G.getDirection();
                        obj[i][2] = G.getMoving();
                        obj[i][3] = p.getColor();
                        obj[i][4] = !G.isVulnerable();
                    }
                    obj[i][5] = G.getDead();
                    i++;
                }
            }
        }
        return obj;

    }
	
	/**
     * Create the ghosts and human and bind them random to the players.
     * @throws java.rmi.RemoteException
     */
    public void bindCharactersToPlayers() throws RemoteException {
        Collections.shuffle(this.players);
        
        for(int i = 0; i < this.players.size() - 1; i++){
            Ghost ghost = new Ghost(pickGhostSpawnPoint(true), this, this.players.get(i));
            this.players.get(i).setCharacter(ghost);
            this.ghosts.add(ghost);
        }
        
        Human newHuman = new Human(pickHumanSpawnpoint(), this);
        this.players.get(this.players.size() - 1).setCharacter(newHuman);
        this.human = newHuman;
    }

    /**
     * Picks a human spawnpoint. The human spawns anywhere in the middle of the
     * map.
     *
     * @return the spawnpoint
     */
    public Point2D pickHumanSpawnpoint() {
        // Pick random x and y positions in the middle of the map.
        Random randomizer = new Random();
        int x = randomizer.nextInt(800 - 600) + 600; // minimum is 600 and maximum is 800
        int y = randomizer.nextInt(600 - 300) + 300; // minimum is 300 and maximum is 600

        // Create a Point2D object with the random picked x and y values.
        Point2D humanSpawnpoint = new Point2D.Double(x, y);

        // Check if the choosen spawnpoint doesn't collide with a wall.
        if (human.detectCollision(humanSpawnpoint)) {
            humanSpawnpoint = pickHumanSpawnpoint();
        }

        return humanSpawnpoint;
    }

    /**
     * Picks a ghost spawnpoint.
     * The Ghosts spawn in any corner.
     * @param startOfGame boolean if the spawnpoint is for the start of the game.
     * @return the ghost spawn point.
     */
    public Point2D pickGhostSpawnPoint(boolean startOfGame){       
        List<Point2D> spawnPoints = new ArrayList<>();    
        spawnPoints.add(new Point2D.Double(0,0));
        spawnPoints.add(new Point2D.Double(0, 1000));
        spawnPoints.add(new Point2D.Double(1500, 1500));
        spawnPoints.add(new Point2D.Double(1500, 1000));
        spawnPoints.add(new Point2D.Double(0, 500));
        spawnPoints.add(new Point2D.Double(1500, 500));
        
        Random randomizer = new Random();
        int randomInt = randomizer.nextInt(6);
        Point2D spawnPoint = spawnPoints.get(randomInt);
        
        if(startOfGame){
            for(Ghost ghost : this.ghosts){
                if (ghost.getPosition() == spawnPoint){
                    spawnPoint = pickGhostSpawnPoint(true);
                }
            }
        }
        
        return spawnPoint;
    }

    private DirectionType[] getPlayerInput() throws IOException, ClassNotFoundException {
        DirectionType[] dir = new DirectionType[this.players.size()];
        boolean[] filledPlayer = new boolean[this.players.size()];
        boolean filled = false;
        while (!filled) {
            String[] input = this.srvSoc.receiveInput();
            for (IPlayer p : this.players) {
                if (p.getIpAdress().equals((String) input[0])) {
                    int index = this.players.indexOf(p);
                    switch (input[1]) {
                        case "UP":
                            dir[index] = DirectionType.UP;
                        case "DOWN":
                            dir[index] = DirectionType.DOWN;
                        case "LEFT":
                            dir[index] = DirectionType.LEFT;
                        case "RIGHT":
                            dir[index] = DirectionType.RIGHT;
                        case "":
                            dir[index] = null;
                    }
                    filledPlayer[index] = true;
                }
            }
            filled = true;
            for (boolean b : filledPlayer) {
                if (!b) {
                    filled = false;
                }
            }
        }
        return dir;
    }
}
