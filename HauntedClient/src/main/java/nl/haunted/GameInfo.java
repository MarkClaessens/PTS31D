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

    public int getGhostLives() {
        return this.ghostLives;
    }

    public void setGhostLives(int i) {
        this.ghostLives = i;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public void setCurrentFloor(int i) {
        //this.setCurrentFloor(i);
        this.currentFloor = i;
    }

    public String getCurrentHuman() {
        return this.currentHuman;
    }

    public void setCurrentHuman(String s) {
        this.currentHuman = s;
    }

    public boolean getKey() {
        return this.key;
    }

    public void setKey(boolean bool) {
        this.key = bool;
    }

    public Image getBackgroundImage() {
        return this.backgroundImage;
    }

    /**
     * Background1.png example;
     *
     * @param i
     */
    public void setBackgroundImage(int i) {
        this.backgroundImage = new Image("Background" + i + ".png");
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
