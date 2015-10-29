package haunted;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.List;
import java.util.Timer;
import java.util.ArrayList;

/**
 *
 * @author Mal
 */
public class GameLobby {

    private Player host;
    private String name;
    private String password;
    private int maxPlayers;
    private int floorAmount;
    private List<Player> players;
    private List<Message> messages;
    private Game game;

    /**
     * create a GameLobby name cannot be null or consist only of spaces. name
     * has to be unique. password can be null host cannot be null default
     * maxPlayers 4 default floorAmount 4S
     *
     * @param name
     * @param password
     * @param host
     */
    public GameLobby(String Name, String Password, Player Host) {
        this.name = Name;
        if (Password != null) {
            this.password = Password;
        } else {
            this.password = null;
        }
        this.players = new ArrayList();
        this.messages = new ArrayList();
        this.host = Host;
        this.addPlayer(Host);

    }

    /**
     * gives the host of the game
     *
     * @return
     */
    public Player getHost() {
        return this.host;
    }

    /**
     * sets host of the game host cannot be null;
     *
     * @param Host
     */
    public void setHost(Player Host) {
        this.host = Host;
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
     * sets name of the Gamelobby name cannot be null or consist only of spaces.
     *
     * @param name
     */
    public void setName(String Name) {
        this.name = Name;
    }

    /**
     * gives password of the Gamelobby
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
    public void setPassword(String Password) {
        this.password = Password;
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
    public void setMaxPlayers(int MaxPlayers) {
        if (MaxPlayers > 0 && MaxPlayers >= this.players.size()) {
            this.maxPlayers = MaxPlayers;
        }
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
    public void setFloorAmount(int FloorAmount) {
        if (FloorAmount >= 1) {
            this.floorAmount = FloorAmount;
        }
    }

    /**
     * you can start the game when everyone is ready. this starts a timer
     *
     * @return
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public boolean startGame() throws IOException, InterruptedException {
        if (this.readyCheck()) {
            this.game = new Game(this.players, this.floorAmount);
            sleep(3000);
            this.game.startRound();
            return true;
        } else {
            return false;
        }
    }

    /**
     * set status to ready/not ready, depends on current status.
     */
    public void changeReadyStatus() {
        this.host.setReady(this.host.getReady());
    }

    /**
     * gets list of players in the GameLobby
     *
     * @return list<Player>
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * kick player from GameLobby
     *
     * @param player
     */
    public void removePlayer(Player player) {
        for (Player P : this.players) {
            if (P.getName() == player.getName()) {
                this.players.remove(P);
                break;
            }
        }
    }

    /**
     * this returns the list of messages from chat.
     *
     * @return list of messages
     */
    public List<Message> getMessages() {
        return this.messages;
    }

    /**
     * send a message to the other players message cannot be null
     *
     * @param message
     */
    public void sendMessage(String message) {
        if (message != null && !message.equalsIgnoreCase("")) {
            this.messages.add(new Message(message, this.host));
        }
    }

    /**
     * add a player to the GameLobby player cannot be null or already added to
     * the GameLobby
     *
     * @param player
     */
    public void addPlayer(Player player) {
        if (player != null) {
            if (!this.players.contains(player)) {
                this.players.add(player);
            }
        }
    }

    /**
     * checks if all players are ready
     *
     * @return
     */
    public boolean readyCheck() {
        return players.stream().noneMatch((P) -> (!P.getReady()));
    }
}
