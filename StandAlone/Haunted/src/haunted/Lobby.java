package haunted;

import static com.sun.deploy.cache.MemoryCache.shutdown;
import javafx.application.Platform;

/**
 *
 * @author Mal
 */
public class Lobby {

    private Player speler1;
    private Player speler2;

    public Lobby() {
        speler1 = new Player("speler1");
        speler2 = new Player("speler2");
    }

    public Player getPlayer1() {
        return speler1;
    }

    public Player getPlayer2() {
        return speler2;
    }

    /**
     * change the name of the current player
     *
     * @param name
     */
    public void changePlayerName(String speler1name, String speler2name) {
        speler1.setName(speler1name);
        speler2.setName(speler2name);
    }

    /**
     * this exits the lobby
     */
    public void exit() {
        Platform.exit();
    }

    /**
     * this function creates a GameLobby object with a name and password name
     * can not be null password can be null
     *
     * @param name
     * @param password
     */
    public GameLobby createGameLobby(String name, String password) {
        GameLobby gamelobby = new GameLobby(name, password, speler1);
        gamelobby.addPlayer(speler2);
        return gamelobby;
    }

}
