/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.geom.Point2D;
import static java.lang.Math.tan;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jvdwi
 */
public class Human extends Character {

    private int flashlightRange, flashlightAngle;
    private boolean hasKey;
    private double[][] flashlightPoints;

    private double flX3 = 0, flY3 = 0, flX2 = 0, flY2 = 0, flX1, flY1, flY23, flX32, flY31, flX13, flDet, flMinD, flMaxD;

    /**
     * The Constructor for human. This initializes the flashlightRange,
     * flashlightAngle and the flashlightPoints by calling setFlashlight Also
     * initialize the base class Character!
     *
     * @param position the spawn Point2D position of the Human on the map
     * @param game
     */
    public Human(Point2D position, Game game) {
        super(position, game);
        this.hasKey = false;
    }

    /**
     *
     * @return true if human has picked up the key
     */
    public boolean hasKey() {
        return hasKey;
    }

    //<editor-fold defaultstate="collapsed" desc="Flashlight">
    /**
     *
     * @return the flashlight range
     */
    public int getFlashlightRange() {
        return this.flashlightRange;
    }

    /**
     * Sets the flashlight range
     *
     * @param flashlightRange
     */
    public void setFlashlightRange(int flashlightRange) {
        this.flashlightRange = flashlightRange;
    }

    /**
     *
     * @return the flashlight angle
     */
    public int getFlashlightAngle() {
        return this.flashlightAngle;
    }

    /**
     * Sets the flashlight angle
     *
     * @param flashlightAngle
     */
    public void setFlashlightAngle(int flashlightAngle) {
        this.flashlightAngle = flashlightAngle;
    }

    /**
     * Initializes the flashlight for the human. With this, the human can see
     * the things in the level, but only the things in range of the flashlight.
     * Flashlight is a triangle.
     */
    private void setFlashlight() {
        flX1 = this.getPosition().getX() + 50;
        flY1 = this.getPosition().getY() + 50;
        switch (this.getDirection()) {
            case UP:
                flX2 = flX1 - tan(this.flashlightAngle) * this.flashlightRange;
                flY2 = flY1 - this.flashlightRange;
                flX3 = flX1 + tan(this.flashlightAngle) * this.flashlightRange;
                flY3 = flY1 - this.flashlightRange;
                break;
            case DOWN:
                flX2 = flX1 + tan(this.flashlightAngle) * this.flashlightRange;
                flX3 = flX1 - tan(this.flashlightAngle) * this.flashlightRange;
                flY2 = flY1 + this.flashlightRange;
                flY3 = flY2;
                break;
            case RIGHT:
                flX2 = flX1 + this.flashlightRange;
                flX3 = flX2;
                flY2 = flY1 + tan(this.flashlightAngle) * this.flashlightRange;
                flY3 = flY1 - tan(this.flashlightAngle) * this.flashlightRange;
                break;
            case LEFT:
                flX2 = flX1 - this.flashlightRange;
                flX3 = flX2;
                flY2 = flY1 + tan(this.flashlightAngle) * this.flashlightRange;
                flY3 = flY1 - tan(this.flashlightAngle) * this.flashlightRange;
                break;
        }
    }

    /**
     * Returns the coordinates of the flashlight polygon So the fx part can draw
     * this for the human.
     *
     * @return polygon of the flashlight.
     */
    public int[] getFlashlightPolygon() {
        int[] i = new int[6];
        i[0] = (int) this.getPosition().getX() + 50;
        i[1] = (int) this.getPosition().getY() + 50;
        i[2] = (int) flX2;
        i[3] = (int) flY2;
        i[4] = (int) flX3;
        i[5] = (int) flY3;
        return i;

    }

    //</editor-fold>
    /**
     * if haskey == false, hasKey becomes true
     */
    public void pickUpKey() {
        if (!hasKey) {
            hasKey = true;
        }
    }

    /**
     * if haskey == true, enterDoor will cause endround, so the game can
     * continue to the next round or go to the victory screen. Otherwise, this
     * method won't cause anything.
     */
    public void enterDoor() {
        // First check if this entering was on the last floor (last level).
        if (game.getFloorAmount() - 1 == game.getCurrentFloor()) {
            game.setRunning(false);
            boolean humanFound = false;

            while (!humanFound) {
                for (Player player : game.getPlayers()) {
                    try {
                        if (player.getCharacter() instanceof Human) {
                            game.endGame(player);
                            humanFound = true;
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(Human.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            game.setRunning(false);
            game.endRound();
        }
    }

    /**
     * check if hitbox collides with the points
     *
     * @param point1
     * @param width1
     * @param height1
     * @param point2
     * @param width2
     * @param height2
     * @return
     */
    public boolean checkHitboxCollision(Point2D point1, int width1, int height1, Point2D point2, int width2, int height2) {
        //convert point1 in leftmost and rightmost X value and top and bottom Y value;
        int p1Xmin = (int) point1.getX();
        int p1Xmax = (int) p1Xmin + width1 - 1;
        int p1Ymin = (int) point1.getY();
        int p1Ymax = (int) p1Ymin + height1 - 1;

        //convert point2 in leftmost and rightmost X value and top and bottom Y value;
        int p2Xmin = (int) point2.getX();
        int p2Xmax = (int) p2Xmin + width2 - 1;
        int p2Ymin = (int) point2.getY();
        int p2Ymax = (int) p2Ymin + height2 - 1;

        return (betweenInclusive(p1Xmax, p2Xmin, p2Xmax) && (betweenInclusive(p1Ymin, p2Ymin, p2Ymax) || betweenInclusive(p1Ymax, p2Ymin, p2Ymax))) || (betweenInclusive(p1Xmin, p2Xmin, p2Xmax) && (betweenInclusive(p1Ymin, p2Ymin, p2Ymax) || betweenInclusive(p1Ymax, p2Ymin, p2Ymax)));
    }

    /**
     * check if human collides with a ghost
     *
     * @return the ghost where the human collides with
     */
    public Ghost checkGhostCollision() {
        //ghost collision
        for (Ghost ghost : this.game.getGhosts()) {
            if (checkHitboxCollision(this.getPosition(), 90, 90, ghost.getPosition(), 90, 90)) {
                return ghost;
            }
        }
        return null;
    }

    /**
     * check if the given position collides with the flashlight.
     *
     * @param point
     * @return
     */
    public boolean flashlightCollision(Point2D point) {

        flY23 = flY2 - flY3;
        flX32 = flX3 - flX2;
        flY31 = flY3 - flY1;
        flX13 = flX1 - flX3;
        flDet = flY23 * flX13 - flX32 * flY31;
        flMinD = Math.min(flDet, 0);
        flMaxD = Math.max(flDet, 0);

        double x = point.getX();
        double y = point.getY();
        double dx = x - flX3;
        double dy = y - flY3;
        double a = flY23 * dx + flX32 * dy;
        if (a < flMinD || a > flMaxD) {
            return false;
        }
        double b = flY31 * dx + flX13 * dy;
        if (b < flMinD || b > flMaxD) {
            return false;
        }
        double c = flDet - a - b;
        return !(c < flMinD || c > flMaxD);

    }

    /**
     * check if the human interacts with ghost, key, door or wall
     *
     */
    public void checkInteract() {
        Point2D door = new Point2D.Double(this.game.getLevel().getDoorLocation().getX() + 40, this.game.getLevel().getDoorLocation().getY());
        Point2D key = this.game.getLevel().getKeyLocation();

        //door collision
        if ((checkHitboxCollision(new Point2D.Double(this.getPosition().getX() + 45, this.getPosition().getY() + 10), 10, 3, door, 20, 5) && this.hasKey)) //key collision   
        {
            this.enterDoor();
        }
        //key collision
        if (checkHitboxCollision(this.getPosition(), 80, 80, key, 80, 80)) {
            this.pickUpKey();
        }
        //flashlight and ghost collision
        if (this.checkGhostCollision() != null) {
            this.checkGhostCollision().possess();
        }
        setFlashlight();
        List<Ghost> deadghosts = new ArrayList();
        this.game.getGhosts().stream().forEach((g) -> {
            if (g.isVulnerable()) {
                boolean hit = false;
                for (Point2D p : g.getHitboxPoints()) {
                    if (flashlightCollision(p)) {
                        hit = true;
                        break;
                    }
                }
                if (hit) {
                    deadghosts.add(g);
                    g.setTimeOfDeath();
                    g.vanish();
                }
            }
        });
    }
}
