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

    //FIELDS
    private double screenWidth;
    private double screenHeight;
    private int levelWidth;
    private int levelHeight;
    private long animationSpeed = 90000000;

    private String[] pressedKeys;
    private final long startNanoTime = System.nanoTime();

    private Human human;
    private Ghost ghost;
    private Obstacle key;
    private Obstacle door;

    private final String backgroundImg = "background.png";

    /**
     * Starting point of drawing. in animationTimer you can find the drawing in
     * loop
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Initialize GUI
        stage.setTitle("The Game");
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        determineScreenSize();
        pressedKeys = new String[2];

        //Make canvas and gc and put it on the screen
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        //Handle key pressings
        onKeyPresses(scene);

        //Animation timer start and handling
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, screenWidth, screenHeight);
                drawElements(gc, currentNanoTime);
            }
        }.start();
        stage.show();
        stage.setFullScreen(true);
    }

    /**
     * Draw the elements on screen every time AnimationTimer calls this.
     *
     * @param gc
     * @param currentNanoTime
     */
    private void drawElements(GraphicsContext gc, long currentNanoTime) {
        int time = Math.round((currentNanoTime - startNanoTime) / animationSpeed);
        //Set backgroundImage
        Image backgroundImage = new Image(backgroundImg);
        gc.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight);

        //Draw Human
        if (human.isIsMoving()) {
            Image humanImg = new Image(human.getSprites()[0]);
            gc.drawImage(humanImg, human.getPosition().getX(), human.getPosition().getY(), screenWidth / levelWidth, screenHeight / levelHeight);
        } else {
            Image humanImg = null;
            if (time % 3 == 0) {
                humanImg = new Image(human.getSprites()[0]);
            } else if (time % 3 == 1) {
                humanImg = new Image(human.getSprites()[1]);
            } else if (time % 3 == 2) {
                humanImg = new Image(human.getSprites()[2]);
            }
            if (humanImg != null) {
                gc.drawImage(humanImg, human.getPosition().getX(), human.getPosition().getY(), screenWidth / levelWidth, screenHeight / levelHeight);
            }
        }

        //Draw Ghost
        if (ghost.isIsMoving()) {
            Image ghostImg = new Image(ghost.getSprites()[0]);
            gc.drawImage(ghostImg, ghost.getPosition().getX(), ghost.getPosition().getY(), screenWidth / levelWidth, screenHeight / levelHeight);
        } else {
            Image ghostImg = null;
            if (time % 3 == 0) {
                ghostImg = new Image(human.getSprites()[0]);
            } else if (time % 3 == 1) {
                ghostImg = new Image(human.getSprites()[1]);
            } else if (time % 3 == 2) {
                ghostImg = new Image(human.getSprites()[2]);
            }
            if (ghostImg != null) {
                gc.drawImage(ghostImg, ghost.getPosition().getX(), ghost.getPosition().getY(), screenWidth / levelWidth, screenHeight / levelHeight);
            }
        }

        //Draw Key
        if (key != null) {
            Image keyImg = new Image(key.getSprite());
            gc.drawImage(keyImg, screenWidth / levelWidth, screenHeight / levelHeight);
        }

        //Draw Door
        Image doorImage = new Image(door.getSprite());
        gc.drawImage(doorImage, screenWidth / levelWidth, screenHeight / levelHeight);
    }

    /**
     * sets the items from the game class
     *
     * @param game
     */
    public void setItems(Game game) {
        //Set human and ghost
        for (Player p : game.getPlayers()) {
            if (p.getCharacter().getClass().equals(human.getClass())) {
                this.human = (Human) p.getCharacter();
            } else {
                this.ghost = (Ghost) p.getCharacter();
            }
        }

        //Set door and key
        Level lvl = game.getCurrentLevel();
        this.levelWidth = lvl.getWidth();
        this.levelHeight = lvl.getHeight();
        for (Obstacle o : lvl.getObstacles()) {
            if (o.getBehaviour() == ObstacleType.DOOR) {
                this.door = o;
            } else if (o.getBehaviour() == ObstacleType.KEY) {
                this.key = o;
            }
        }
    }

    /**
     * Determines the screen resolution of the device the user is using.
     */
    private void determineScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.getWidth();
        screenHeight = screenSize.getHeight();
    }

    /**
     * handle the on key pressings
     *
     * @param scene
     */
    private void onKeyPresses(Scene scene) {
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if (code == "W" || code == "A" || code == "S" || code == "D") {
                            pressedKeys[0] = code;
                        } else if (code == "UP" || code == "DOWN" || code == "LEFT" || code == "RIGHT") {
                            pressedKeys[1] = code;
                        }

                    }
                }
        );
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {

                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (code == "W" || code == "A" || code == "S" || code == "D") {
                            pressedKeys[0] = null;
                        } else if (code == "UP" || code == "DOWN" || code == "LEFT" || code == "RIGHT") {
                            pressedKeys[1] = null;
                        }
                    }
                }
        );
    }

    /**
     * returns the pressed keys
     *
     * @return pressed keys
     */
    public String[] getPressedKeys() {
        return pressedKeys;
    }

    /**
     * start point of this executable class
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
