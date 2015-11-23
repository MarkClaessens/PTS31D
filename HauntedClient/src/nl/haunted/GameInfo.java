/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public class GameInfo {
    private int ghostLives, currentFloor;
    private String currentHuman;
    private boolean key;
    private List<Entity> entities;
    
    /**
     * 
     * @param ghostLives
     * @param currentFloor
     * @param currentHuman
     * @param key 
     */
    public GameInfo(int ghostLives, int currentFloor, String currentHuman, boolean key){
        this.ghostLives = ghostLives;
        this.currentFloor = currentFloor;
        this.currentHuman = currentHuman;
        this.key = key;
        entities = new ArrayList();
    }
}
