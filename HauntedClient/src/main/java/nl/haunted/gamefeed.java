/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author Mal
 */
public class gamefeed {

    Socket soc;
    GameInfo gameInfo;

    public gamefeed(Socket socket) throws IOException, ClassNotFoundException {
        this.soc = socket;
        setupGameInfo();
    }

    private void setupGameInfo() throws IOException, ClassNotFoundException {
        boolean setup = false;
        while (!setup) {
            Object[][] o = soc.receiveObject();
            if (o[0][0] == "Server") {
                int ghostLives = (int) o[1][0];
                int currentFloor = (int) o[1][1];
                String currentHuman = (String) o[1][2];
                boolean key = (boolean) o[1][3];
                gameInfo = new GameInfo(ghostLives, currentFloor, currentHuman, key);
                fillGameInfo(o);
                setup = true;
            }
        }
    }

    public void fillGameInfo(Object[][] o) {
        Entity key = new Entity((Point2D) o[3][0], EntityType.Key);
        Entity door = new Entity((Point2D) o[5][0], EntityType.Door);
        door.setDirection((DirectionType) o[5][1]);
        Entity Human = new Entity((Point2D) o[4][0], EntityType.Human);
        Human.setDirection((DirectionType) o[4][1]);
        Human.setMoving((boolean) o[4][2]);
        Human.setColor((Color) o[4][3]);
        List<Entity> ghosts = new ArrayList();
        for (int i = 0; i < (int) o[1][6]; i++) {
            Entity e = new Entity((Point2D) o[i + 6][0], EntityType.Ghost);
            e.setDirection((DirectionType) o[i + 6][1]);
            e.setMoving((boolean) o[i + 6][2]);
            e.setColor((Color) o[i + 6][3]);
            e.setWall((boolean) o[i + 6][4]);
            e.setDead((boolean) o[i + 6][5]);
            ghosts.add(e);
        }
        gameInfo.addEntity(key);
        gameInfo.addEntity(door);
        gameInfo.addEntity(Human);
        ghosts.stream().forEach((e) -> {
            gameInfo.addEntity(e);
        });

    }

    public void decompressFeed() throws IOException, ClassNotFoundException {
        Object[][] o = soc.receiveObject();
        if ("Server".equals((String) o[0][0])) {
            //set gameinfo
            gameInfo.setBackgroundImage((int) o[0][2]);
            gameInfo.setGhostLives((int) o[1][0]);
            gameInfo.setCurrentFloor((int) o[1][1]);
            gameInfo.setCurrentHuman((String) o[1][2]);
            gameInfo.setKey((boolean) o[1][3]);
            int i = 6;
            boolean foundHuman = false;

            //set Human && ghosts
            for (Entity e : gameInfo.getEntities()) {
                if (e.getType() != EntityType.Key && e.getType() != EntityType.Door) {
                    //set ghosts
                    if (e.getType() == EntityType.Human && !foundHuman) {
                        e.setPosition((Point2D) o[4][0]);
                        e.setDirection((DirectionType) o[4][1]);
                        e.setMoving((boolean) o[4][2]);
                        e.setColor((Color) o[4][3]);
                        foundHuman = true;
                    }
                    //set ghosts
                    if (e.getType() == EntityType.Ghost) {
                        if (!(boolean) o[i][5]) {
                            e.setPosition((Point2D) o[i][0]);
                            e.setDirection((DirectionType) o[5][1]);
                            e.setMoving((boolean) o[5][2]);
                            e.setColor((Color) o[5][3]);
                            e.setWall((boolean) o[5][4]);
                        }
                        e.setDead((boolean) o[i][5]);
                        i++;
                    }
                }
            }
        }
    }
    
    
    public void compressInput(){
        
    }
}
