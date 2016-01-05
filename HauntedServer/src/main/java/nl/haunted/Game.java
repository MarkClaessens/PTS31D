/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.Serializable;
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
public class Game implements Serializable {

    private final int floorAmount; // starts at 1
    private int currentFloor = -1; // starts at 0 so the init has to be -1.
    private Timer tickTimer, inputTimer;
    private boolean roundEnded, nextRound;
    private Level level;
    private List<Ghost> ghosts;
    private Human human;
    private List<IPlayer> players;
    private IPlayer currentHuman;
    private SocketMediator srvSoc;
    private IGameLobby gameLobby;
    private SocketMediator TCPCsoc;

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

    public void setCurrentHuman(IPlayer p) {
        this.currentHuman = p;
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
        srvSoc = new SocketMediator();
        srvSoc.socketSetup(groupID, 9876, "UDP");
        TCPCsoc = new SocketMediator();
        TCPCsoc.socketSetup(groupID, 9877, "TCPC");
        Random randomizer = new Random();
        // the minimum floors is hardcoded to 3 right now.
        this.floorAmount = randomizer.nextInt(floors - 3 + 1) + 3;
        this.currentFloor = 0;
        this.gameLobby = gl;
        this.ghosts = new ArrayList();

        // Create the first level.
        nextLevel();

        // Create the characters and bind them to the players.
        bindCharactersToPlayers();
    }

    /**
     * Creates the new level object when a level has finished.
     */
    public void nextLevel() {
        this.currentFloor++;
        this.level = new Level(this.currentFloor, this.players.size() - 1);
    }

    /**
     * starts the next round at the current floor.
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

        this.inputTimer = new Timer();
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                try {
                    TCPCsoc.socketSetup("", 9877, "TCPC");
                    TCPCsoc.receiveInput();
                } catch (IOException | ClassNotFoundException ex) {
                }
            }
        };
        this.inputTimer.scheduleAtFixedRate(task2, 0, 5);
    }

    /**
     * Ends the current round and opens the counting screen.
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
        for (IPlayer player : this.players) {
            player.reset();
        }
        this.roundEnded = true;
        this.nextRound = false;

        // Wait 500 milliseconds (tick timer needs to go one some time) before cancelling ticking.
        Thread.sleep(500);
        this.tickTimer.cancel();
        GameLobby gl = (GameLobby) this.gameLobby;
        gl.getLobby().removeGLAfterGame(gl);
    }

    /**
     * The player leaves the game (gamelobby).
     *
     * @param player the player that wants to leave the game.
     * @throws java.rmi.RemoteException
     * @throws java.lang.InterruptedException
     */
    public synchronized void leaveGame(Player player) throws RemoteException, InterruptedException {
        if (player.getCharacter() instanceof Human) {
            Random randomizer = new Random();
            int randomInt = randomizer.nextInt(this.players.size() - 1);
            this.players.get(randomInt).setCharacter(human);
            this.currentHuman = this.players.get(randomInt);
        }

        player.setCharacter(null);
        this.players.remove(player);
        if (this.players.size() == 1) {
            this.endGame(this.players.get(0));
        }
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
                            if (this.players.get(i).getCharacter() instanceof Ghost) {
                                for (Ghost g : ghosts){
                                    if(g.getControllingPlayer().getIpAdress().equals(this.players.get(i).getIpAdress())){
                                        g.move(this, (DirectionType) keyboard[i]);
                                    }
                                }
                            }
                            else if (this.players.get(i).getCharacter() instanceof Human){
                                this.human.move(this, (DirectionType) keyboard[i]);
                            }
                        } else {

                            // <editor-fold defaultstate="collapsed" desc="set moving and check if a ghost needs to become a wall">
                            //if the player didnt press a button and is a ghost check if it was moving.
                            //if it was moving set the stationary time
                            //set the characters moving on false
                            if (this.players.get(i).getCharacter() instanceof Ghost) {
                                for (Ghost g : ghosts){
                                    if(g.getControllingPlayer().getIpAdress().equals(this.players.get(i).getIpAdress())){
                                        g.move(this, (DirectionType) keyboard[i]);
                                        if (g.getMoving()) {
                                            if (this.players.get(i).getCharacter() instanceof Ghost) {
                                                g.setStationaryTime();
                                            }
                                        }
                                        if (g.getStationaryTime() == null) {
                                            g.setStationaryTime();
                                        }
                                        g.setMoving(false);
                                    }
                                }
                            }
                            else
                            {
                                this.human.setMoving(false);
                            }
//                            this.players.get(i).getCharacter().setMoving(false);
                            //</editor-fold>
                        }
                    }
                }
                //</editor-fold>
                this.human.checkInteract(this);
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
        obj[1][2] = this.currentHuman;
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
        obj[5][1] = this.level.getDoorDirection();

        //ghostloop
        int i = 6;
        for (IPlayer p : players) {
            for (Ghost G : this.ghosts) {
                if (p.equals(G.getControllingPlayer())) {
                    if (!G.getDead()) {
                        obj[i][0] = G.getPosition();
                        obj[i][1] = G.getDirection();
                        obj[i][2] = G.getMoving();
                        obj[i][3] = p.getColor();
                        obj[i][4] = !G.isVulnerable();
                        obj[i][6] = G.getID();
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
     *
     * @throws java.rmi.RemoteException
     */
    public void bindCharactersToPlayers() throws RemoteException {
        Collections.shuffle(this.players);

        for (int i = 0; i < this.players.size() - 1; i++) {
            Ghost ghost = new Ghost(this.players.get(i));
            ghost.setPosition(this.pickGhostSpawnPoint(true));
            this.players.get(i).setCharacter(ghost);
            this.ghosts.add(ghost);
        }

        this.human = new Human();
        this.human.setPosition(pickHumanSpawnpoint());
        this.players.get(this.players.size() - 1).setCharacter(this.human);
        this.currentHuman = this.players.get(this.players.size() - 1);

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
        int x = ((randomizer.nextInt(800 - 600) + 600) + 50) / 100 * 100; // minimum is 600 and maximum is 800
        int y = ((randomizer.nextInt(600 - 300) + 300) + 50) / 100 * 100; // minimum is 300 and maximum is 600
        // Create a Point2D object with the random picked x and y values.
        Point2D humanSpawnpoint = new Point2D.Double(x, y);

        // Check if the choosen spawnpoint doesn't collide with a wall.
        if (human != null) {
            if (human.detectCollision(humanSpawnpoint, this)) {
                humanSpawnpoint = pickHumanSpawnpoint();
            }
        }

        return humanSpawnpoint;
    }

    /**
     * Picks a ghost spawnpoint. The Ghosts spawn in any corner.
     *
     * @param startOfGame boolean if the spawnpoint is for the start of the
     * game.
     * @return the ghost spawn point.
     */
    public Point2D pickGhostSpawnPoint(boolean startOfGame) {
        List<Point2D> spawnPoints = new ArrayList<>();
        spawnPoints.add(new Point2D.Double(0, 0));
        spawnPoints.add(new Point2D.Double(0, 900));
        spawnPoints.add(new Point2D.Double(1400, 0));
        spawnPoints.add(new Point2D.Double(1400, 900));
        spawnPoints.add(new Point2D.Double(0, 500));
        spawnPoints.add(new Point2D.Double(1400, 500));

        Random randomizer = new Random();
        int randomInt = randomizer.nextInt(6);
        Point2D spawnPoint = spawnPoints.get(randomInt);

        if (startOfGame) {
            for (Ghost ghost : this.ghosts) {
                if (ghost.getPosition() == spawnPoint) {
                    spawnPoint = pickGhostSpawnPoint(true);
                }
            }
        }

        return spawnPoint;
    }

    private DirectionType[] getPlayerInput() throws IOException, ClassNotFoundException {
        DirectionType[] dir = new DirectionType[this.players.size()];
        List<String[]> inputSrc = new ArrayList();
        inputSrc = this.srvSoc.getInputArray();
        List<String[]> input = new ArrayList(inputSrc.size());
        input.addAll(inputSrc);
        if (input.size() > 0) {
            for (IPlayer p : this.players) {
                for (String[] s : input) {
                    if (p.getIpAdress().equals(s[0])) {
                        int index = this.players.indexOf(p);
                        switch (s[1]) {
                            case "UP":
                                dir[index] = DirectionType.UP;
                                break;
                            case "DOWN":
                                dir[index] = DirectionType.DOWN;
                                break;
                            case "LEFT":
                                dir[index] = DirectionType.LEFT;
                                break;
                            case "RIGHT":
                                dir[index] = DirectionType.RIGHT;
                                break;
                            case "null":
                                dir[index] = null;
                                break;
                        }
                    }
                }
            }
        }
        return dir;
    }
}
