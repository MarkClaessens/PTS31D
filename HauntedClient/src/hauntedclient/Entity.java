/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedclient;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author jvdwi
 */
public class Entity {
    private Point2D position;
    private DirectionType direction;
    private boolean moving, wall;
    private ArrayList<String> sprites;
    private EntityType type;
    
    /**
     * 
     * @param position
     * @param direction
     * @param moving
     * @param sprites
     * @param wall
     * @param type 
     */
    public Entity(Point2D position, DirectionType direction, boolean moving, ArrayList<String> sprites, boolean wall, EntityType type){
        this.position = position;
        this.direction = direction;
        this.moving = moving;
        this.sprites = sprites;
        this.wall = wall;
        this.type = type;
    }
}
