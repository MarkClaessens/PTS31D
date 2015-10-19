package haunted;

/**
 *
 * @author Mal
 */
public class Player {

    Game game;
    private String name;
    private Boolean ready;
    private Character character;

    /**
     * this creates the Player object.
     *
     * @param name
     */
    public Player(String name) {
        this.name = name;
        this.ready = false;
    }

    public void setCharacter(Character character){
        this.character = character;
    }
    
    
    /**
     *
     * @return The name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the player. Where the name String is not empty and
     * contains at least one character.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return returns if the player is ready to play.
     */
    public Boolean getReady() {
        return this.ready;
    }

    /**
     * Sets if the player is ready to play.
     *
     * @param ready
     */
    public void setReady(Boolean ready) {
        this.ready = ready;
    }

}
