package haunted;

/**
 *
 * @author Mal
 */
public class Ghost extends Character {

    private boolean vulnerable;
    private boolean isGhost;

    /**
     * 
     *
     * @return the state of vulnerability
     */
    public boolean isVulnerable() {
        return this.vulnerable;
    }

    /**
     * this sets the vulnerability of the ghost object
     *
     * @param vulnerable
     */
    public void setVulnerable(boolean vulnerable) {
        this.vulnerable = vulnerable;
    }

    /**
     * This possesses a human, this ghost becomes the human, the previous human
     * becomes a ghost.
     */
    public void possess() {
        // TODO - implement Ghost.possess
        // TODO - posses the human, works with Game.
        throw new UnsupportedOperationException();
    }

    /**
     * Changes the appearance of the ghost, it becomes a wall or a ghost.
     */
    public void changeAppearance() {
        // TODO - implement Ghost.changeApperance
        if (this.isGhost) {
            this.vulnerable = false;
            this.setSprite("Wall");
            this.isGhost = false;
        } else {
            this.vulnerable = true;
            this.setSprite("Ghost");
            this.isGhost = true;
        }
    }

    /**
     * Removes the ghost when hit by the flashlight
     */
    public void vanish() {
        // TODO - implement Ghost.vanish
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor for ghost
     * sets vulnerable and isGhost to true
     * sets the super variables in characters
     * @param color, color of the ghost
     */
    public Ghost(String color) {
        super(color);
        // TODO - implement Ghost.Ghost
        throw new UnsupportedOperationException();
    }

}
