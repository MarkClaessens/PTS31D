package haunted;

public class GameLobby {

    private Player Host;
    private String name;
    private String password;
    private int maxPlayers;
    private int floorAmount;

    public Player getHost() {
        return this.Host;
    }

    public void setHost(Player Host) {
        this.Host = Host;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getFloorAmount() {
        return this.floorAmount;
    }

    public void setFloorAmount(int floorAmount) {
        this.floorAmount = floorAmount;
    }

    /**
     *
     * @param name
     * @param password
     * @param host
     */
    public GameLobby(String name, String password, Player host) {
        // TODO - implement GameLobby.GameLobby
        throw new UnsupportedOperationException();
    }

    public void startGame() {
        // TODO - implement GameLobby.startGame
        throw new UnsupportedOperationException();
    }

    public void changeReadyStatus() {
        // TODO - implement GameLobby.changeReadyStatus
        throw new UnsupportedOperationException();
    }

    public void removePlayer() {
        // TODO - implement GameLobby.removePlayer
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param message
     */
    public void sendMessage(String message) {
        // TODO - implement GameLobby.sendMessage
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param player
     */
    public void addPlayer(Player player) {
        // TODO - implement GameLobby.addPlayer
        throw new UnsupportedOperationException();
    }

    public boolean readyCheck() {
        // TODO - implement GameLobby.readyCheck
        throw new UnsupportedOperationException();
    }

}
