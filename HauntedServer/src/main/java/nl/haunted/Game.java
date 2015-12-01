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
import java.util.List;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Mal + Mike
 */
public class Game {

    private final int floorAmount;
    private int currentFloor;
    private Timer tickTimer;
    private boolean running, roundEnded, nextRound;
    private Level level;
    private List<Character> characters;
    private List<Ghost> ghosts;
    private Human human;
    private final Socket soc;
    private List<Player> players;
    private IPlayer currentHuman;

    public Level getLevel() {
        return level;
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @return the amount of floors in this game.
     */
    public int getFloorAmount() {
        return floorAmount;
    }

    public void setRunning(boolean isRunning) {
        this.running = isRunning;
    }

    /**
     *
     * @return the current floor of the game, starts at 0.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     *
     * @param players
     * @param floors
     * @throws java.io.IOException
     */
    public Game(List<IPlayer> players, int floors) throws IOException {
        //Setup the socket for this game;
        soc = new Socket();
        soc.socketSetup("234.56.78.90", 9876);
        Random randomizer = new Random();
        this.floorAmount = randomizer.nextInt(floors - 3 + 1) + 3;
        this.currentFloor = 0;
    }

    /**
     *
     */
    public void nextLevel() {

    }

    /**
     *
     */
    public void startRound() {

    }

    /**
     *
     */
    public void endRound() {

    }

    /**
     *
     */
    public void endGame() {

    }

    /**
     *
     */
    public void leaveGame() {

    }

    /**
     *
     */
    public void tick() {

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
        obj[1][0] = this.level.getGhostLifePool();
        obj[1][1] = this.level.getCurrentFoor();
        obj[1][2] = this.getCurrentHuman();
        obj[1][3] = !this.getCurrentHuman().hasKey();
        obj[1][4] = this.roundEnded;
        obj[1][5] = this.nextRound;
        obj[1][6] = this.ghosts.size();
        obj[2][0] = new Point2D.Double(this.human.getFlashlightPolygon()[0], this.human.getFlashlightPolygon()[1]);
        obj[2][1] = new Point2D.Double(this.human.getFlashlightPolygon()[2], this.human.getFlashlightPolygon()[3]);
        obj[2][2] = new Point2D.Double(this.human.getFlashlightPolygon()[4], this.human.getFlashlightPolygon()[5]);
        obj[3][0] = this.level.getKeyLocation();
        obj[4][0] = this.human.getPosition();
        obj[4][1] = this.human.getDirection();
        obj[4][2] = this.human.getIsMoving();
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
                        obj[i][2] = G.getIsMoving();
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
     *
     */
    public void setupGameClasses() {

    }

    /**
     *
     */
    public void bindCharactersToPlayers() {

    }

    /**
     *
     * @return
     */
    public Point2D pickSpawnPoint() {
        return null;
    }

    /**
     *
     */
    public void sendMapFiles() {

    }

    private Human getCurrentHuman() {
        for (Character C : this.characters) {
            if (C instanceof Human) {
                return (Human) C;
            }
        }
        return null;
    }
}
