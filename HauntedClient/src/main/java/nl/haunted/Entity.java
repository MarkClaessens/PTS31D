/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.Color;

/**
 *
 * @author jvdwi
 */
public class Entity {

    private Point2D position;
    private DirectionType direction;
    private boolean moving, wall, Dead;
    private final EntityType type;
    private Color color;
    private int id;

    /**
     *
     * @param position
     * @param type
     */
    public Entity(Point2D position, EntityType type) {
        this.position = position;
        this.type = type;
    }

    public boolean getWall() {
        return this.wall;
    }

    public void setWall(boolean var) {
        this.wall = var;
    }

    public boolean getDead() {
        return this.Dead;
    }

    public void setDead(boolean bool) {
        this.Dead = bool;
    }

    public EntityType getType() {
        return this.type;
    }

    public DirectionType getDirection() {
        return this.direction;
    }

    public void setDirection(DirectionType dir) {
        this.direction = dir;
    }

    public boolean getMoving() {
        return this.moving;
    }

    public void setMoving(boolean mov) {
        this.moving = mov;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public void setPosition(Point2D pos) {
        this.position = pos;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color col) {
        this.color = col;
    }

    int getID() {
        return this.id;
    }

    public void setID(int i) {
        this.id = i;
    }
}
