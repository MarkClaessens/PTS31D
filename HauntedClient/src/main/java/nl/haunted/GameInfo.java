/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author jvdwi
 */
public class GameInfo {

    private int ghostLives, currentFloor;
    private String currentHuman;
    private boolean key;
    private List<Entity> entities;
    private boolean roundEnd = false;
    private boolean gameEnd = false;
    private Image backgroundImage;

    /**
     *
     * @param ghostLives
     * @param currentFloor
     * @param currentHuman
     * @param key
     */
    public GameInfo(int ghostLives, int currentFloor, String currentHuman, boolean key) {
        this.ghostLives = ghostLives;
        this.currentFloor = currentFloor;
        this.currentHuman = currentHuman;
        this.key = key;
        entities = new ArrayList();
    }

    public void setGhostLives(int i) {
        this.ghostLives = i;
    }

    public void setCurrentFloor(int i) {
        this.setCurrentFloor(i);
    }

    public void setCurrentHuman(String s) {
        this.currentHuman = s;
    }
    
    public void setKey(boolean bool){
        this.key = bool;
    }

    public Image getBackgroundImage() {
        return this.backgroundImage;
    }

    public boolean addEntity(Entity e) {
        if (!entities.contains(e)) {
            entities.add(e);
            return true;
        }
        return false;
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public void setVictory(boolean bool) {
        this.gameEnd = bool;
    }

    public void setRoundEnd(boolean bool) {
        this.roundEnd = bool;
    }
}
