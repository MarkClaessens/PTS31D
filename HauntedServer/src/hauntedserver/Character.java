/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hauntedserver;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public abstract class Character {
    private Point2D position;
    private double movementSpeed;
    private DirectionType direction;
    private boolean moving;
    private String[] spritesUp;
    private String[] spritesLeft;
    private String[] spritesDown;
    private String[] spritesRight;
    private Game game;
    
    /**
     * the constructor for character
     *
     * @param position
     * @param color
     * @param spritesUp
     * @param spritesDown
     * @param spritesLeft
     * @param spritesRight
     * @param game
     */
    public Character(Point2D position, String[] spritesUp, String[] spritesDown, String[] spritesLeft, String[] spritesRight, Game game) {
        this.spritesUp = spritesUp;
        this.spritesDown = spritesDown;
        this.spritesLeft = spritesLeft;
        this.spritesRight = spritesRight;
        this.direction = DirectionType.DOWN; // Set a default direction
        this.game = game;
        this.moving = false;
    }
    
    /**
     * Moves the character into the given direction. 
     * But be aware of collision with another characters, key, walls or door.
     * @param direction 
     */
    public void move(DirectionType direction){
        
    }
    
    /**
     * Checks if there is a collision with another character, walls, key or door.
     */
    public void detectCollision(){
        
    }
    
    /**
     * Returns a list of hitbox points, the points where the character can be hitted on.
     * Important for the collisioncheck.
     * @return list of hitbox points.
     */
    public List<Point2D> getHitBoxPoints(){
        return null;
    }
    
    /**
     * returns true if i is between or equal to min and max
     * @param i
     * @param min
     * @param max
     * @return true if between or equal
     */
    public boolean betweenInclusive(int i, int min, int max){
        return false;
    }
}
