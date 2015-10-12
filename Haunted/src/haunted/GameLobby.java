package haunted;

import java.sql.Time;
import java.util.List;
import java.util.Timer;

/**
 *
 * @author Mal
 */
public class GameLobby {

    private Player Host;
    private String name;
    private String password;
    private int maxPlayers;
    private int floorAmount;
    private Timer tickTimer;
    private List<Player> players;

    /**
     * create a GameLobby name cannot be null or consist only of spaces. name
     * has to be unique. password can be null host cannot be null default
     * maxPlayers 4 default floorAmount 4S
     *
     * @param name
     * @param password
     * @param host
     */
    public GameLobby(String name, String password, Player host) {

        // TODO - implement GameLobby.GameLobby
        throw new UnsupportedOperationException();
    }

    /**
     * gives the host of the game
     *
     * @return
     */
    public Player getHost() {
        return this.Host;
    }

    /**
     * sets host of the game host cannot be null;
     *
     * @param Host
     */
    public void setHost(Player Host) {
        this.Host = Host;
    }

    /**
     * gives name of the gamelobby
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets name of the gamelobby name cannot be null or consist only of spaces.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gives password of the gamelobby
     *
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * sets password of the GameLobby password can be null
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gives maximal number of players for the GameLobby
     *
     * @return
     */
    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    /**
     * sets maximal number of players for the gamelobby maxPlayers can not be
     * null, lower than 3 or lower than the number current players in the lobby.
     *
     * @param maxPlayers
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * gives amount of floors for the game
     *
     * @return
     */
    public int getFloorAmount() {
        return this.floorAmount;
    }

    /**
     * sets amount of floors for the game
     *
     * @param floorAmount
     */
    public void setFloorAmount(int floorAmount) {
        this.floorAmount = floorAmount;
    }

    /**
     * you can start the game when everyone is ready. this starts a timer
     */
    public void startGame() {
        // TODO - implement GameLobby.startGame
        throw new UnsupportedOperationException();
    }

    /**
     * set status to ready/not ready, depends on current status.
     */
    public void changeReadyStatus() {
        // TODO - implement GameLobby.changeReadyStatus
        throw new UnsupportedOperationException();
    }

    /**
     * gets list of players in the GameLobby
     *
     * @return list<Player>
     */
    public List<Player> getPlayers() {
        // TODO - implement GameLobby.getPlayers
        throw new UnsupportedOperationException();
    }

    /**
     * kick player from GameLobby
     *
     * @param player
     */
    public void removePlayer(Player player) {
        // TODO - implement GameLobby.removePlayer
        throw new UnsupportedOperationException();
    }
    
    /**
     * this returns the list of messages from chat.
     * @return list of messages
     */
    public List<Message> getMessages(){
        throw new UnsupportedOperationException();
    }
    /**
     * send a message to the other players message cannot be null
     *
     * @param message
     */
    public void sendMessage(String message) {
        // TODO - implement GameLobby.sendMessage
        throw new UnsupportedOperationException();
    }

    /**
     * add a player to the GameLobby player cannot be null or already added to
     * the GameLobby
     *
     * @param player
     */
    public void addPlayer(Player player) {
        // TODO - implement GameLobby.addPlayer
        throw new UnsupportedOperationException();
    }

    /**
     * checks if all players are ready
     *
     * @return
     */
    public boolean readyCheck() {
        // TODO - implement GameLobby.readyCheck
        throw new UnsupportedOperationException();
    }

    /**
     * this returns true if the ticktimer is on
     *
     * @return TickTimerState
     */
    public boolean getTickTimerState() {
        // TODO - implement GameLobby.getTickTimerState
        throw new UnsupportedOperationException();
    }

}
