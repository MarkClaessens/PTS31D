/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advancingwithsprites;

/**
 *
 * @author jvdwi
 */
public class Human {
    private String[] sprites;
    private boolean isMoving;
    
    public Human (String[] sprites){
        this.sprites = sprites;
        this.isMoving = false;
    }

    /**
     * @return the sprites
     */
    public String[] getSprites() {
        return sprites;
    }

    /**
     * @param sprites the sprites to set
     */
    public void setSprites(String[] sprites) {
        this.sprites = sprites;
    }

    /**
     * @return the isMoving
     */
    public boolean getIsMoving() {
        return isMoving;
    }

    /**
     * @param isMoving the isMoving to set
     */
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    
    
}
