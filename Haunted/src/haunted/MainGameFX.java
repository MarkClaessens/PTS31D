/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author jvdwi
 */
public class MainGameFX extends Application {

    double width;
    double height;

    ArrayList<String> pressedKeys;
    
    final long startNanoTime = System.nanoTime();

    Human human;
    Ghost ghost;
    List<Obstacle> obstacles;
    int levelWidth;
    int levelHeight;

    public void start(Stage stage) throws Exception {
        stage.setTitle("the game");
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        pressedKeys = new ArrayList();

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if (!pressedKeys.contains(code)) {
                            pressedKeys.add(code);
                        }
                    }
                });
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        pressedKeys.remove(code);
                    }
                });

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, width, height);
                drawElements(gc, currentNanoTime);
            }
        }.start();
        stage.show();
        stage.setFullScreen(true);
    }

    //TODO: handling 3 versions when moving
    public void drawElements(GraphicsContext gc, long currentNanoTime) {
        int time = Math.round((currentNanoTime - startNanoTime) / 90000000);
        if (!pressedKeys.contains("UP") && !pressedKeys.contains("DOWN") && !pressedKeys.contains("LEFT") && !pressedKeys.contains("RIGHT")) {
            Image humanImg = new Image(human.getSprites()[0]);
            gc.drawImage(humanImg, human.getPosition().getX(), human.getPosition().getY(), width / levelWidth, height / levelHeight);
        } else {
            Image humanImg = null;
            if (time % 3 == 0) {
                humanImg = new Image(human.getSprites()[0]);
            } else if (time % 3 == 1) {
                humanImg = new Image(human.getSprites()[1]);
            } else if (time % 3 == 2) {
                humanImg = new Image(human.getSprites()[2]);
            }
            if(humanImg != null){
                gc.drawImage(humanImg, human.getPosition().getX(), human.getPosition().getY(), width / levelWidth, height / levelHeight);
            }
        }

        if (!pressedKeys.contains("W") && !pressedKeys.contains("A") && !pressedKeys.contains("S") && !pressedKeys.contains("D")) {
            Image ghostImg = new Image(ghost.getSprites()[0]);
            gc.drawImage(ghostImg, ghost.getPosition().getX(), ghost.getPosition().getY(), width / levelWidth, height / levelHeight);
        } else {
            Image ghostImg = null;
            if (time % 3 == 0) {
                ghostImg = new Image(human.getSprites()[0]);
            } else if (time % 3 == 1) {
                ghostImg = new Image(human.getSprites()[1]);
            } else if (time % 3 == 2) {
                ghostImg = new Image(human.getSprites()[2]);
            }
            if(ghostImg != null){
                gc.drawImage(ghostImg, ghost.getPosition().getX(), ghost.getPosition().getY(), width / levelWidth, height / levelHeight);
            }
        }

        for (Obstacle o : obstacles) {
            Image obstImg = new Image(o.getSprite());
            gc.drawImage(obstImg, o.getPosition().getX(), o.getPosition().getY(), width / levelWidth, height / levelHeight);
        }
    }

    public void setItems(Game game) {
        for (Player p : game.getPlayers()) {
            if (p.getCharacter().getClass().equals(human.getClass())) {
                this.human = (Human) p.getCharacter();
            } else {
                this.ghost = (Ghost) p.getCharacter();
            }
        }
        Level lvl = game.getCurrentLevel();
        this.levelWidth = lvl.getWidth();
        this.levelHeight = lvl.getHeight();
        this.obstacles = lvl.getObstacles();
    }

    public ArrayList<String> getPressedKeys() {
        return pressedKeys;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
