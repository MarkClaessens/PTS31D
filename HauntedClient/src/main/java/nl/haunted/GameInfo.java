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
    private boolean roundEnd = false, gameEnd = false, amIHuman = false;
    private Image backgroundImage;

    /**
     *
     * @param ghostLives
     * @param currentFloor
     * @param currentHuman
     * @param key
     */
    public GameInfo(int ghostLives, int currentFloor, boolean amIHuman, boolean key) {
        this.ghostLives = ghostLives;
        this.currentFloor = currentFloor;
        this.amIHuman = amIHuman;
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

    public Boolean amIHuman() {
       return this.amIHuman;
    }

    public void setamIHuman(boolean b) {
        this.amIHuman = b;
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
     * background1.png example;
     *
     * @param i
     */
    public void setBackgroundImage(int i) {
        this.backgroundImage = new Image("background" + i + ".png");
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

    public void setNextRound(boolean bool) {
        if(this.roundEnd == true && bool == false){
            this.gameEnd = true;
        } else {
            this.gameEnd = false;
        }
    }

    public void setRoundEnd(boolean bool) {
        this.roundEnd = bool;
    }
    
    public boolean isRoundEnd(){
        return this.roundEnd;      
    }
    
    public boolean isGameEnd(){
        return this.gameEnd;
    }
    
    
    
    public boolean isRunning(){
        return false;
    }
     public boolean isEnded(){
         return true;
     }
     public void endGame(){}
     public void endRound(){}
}
