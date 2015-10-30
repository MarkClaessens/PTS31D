/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
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
    private int levelDrawWidth;
    private int levelDrawHeight;
    private double horScale;
    private double verScale;
    private long animationSpeed = 90000000;

    private Object[] pressedKeys;
    private final long startNanoTime = System.nanoTime();

    private Human human = null;
    private Ghost ghost = null;
    private Level level = null;
    private Obstacle key = null;
    private Obstacle door = null;

    private boolean showEmpty;

    private final String backgroundImg = "background.png";

    public MainGameFX() {
    }

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
        this.levelDrawWidth = 1700;
        this.levelDrawHeight = 1200;
        this.showEmpty = false;
        stage.setTitle("The Game");
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        determineScreenSize();
        pressedKeys = new Object[3];
        pressedKeys[2] = false;
//        this.clearPressedKeys();

        //Make canvas and gc and put it on the screen
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        //Handle key pressings
        onKeyPresses(scene);
        onKeyReleases(scene);

        //Animation timer start and handling
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (!showEmpty) {
                    gc.clearRect(0, 0, screenWidth, screenHeight);
                    drawElements(gc, currentNanoTime);
                } else {
                    gc.clearRect(0, 0, screenWidth, screenHeight);
                    Image waitImage = new Image("waiting.png");
                    gc.drawImage(waitImage, 0, 0, screenWidth, screenHeight);
                }
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

        //Draw Human
        Image drawHumanImage = drawCharacter(human, time);
        //System.out.println("HumanX: " + human.getPosition().getX());
        //System.out.println("HumanY: " + human.getPosition().getY());

        //Draw Ghost
        Image drawGhostImage = drawCharacter(ghost, time);
        //System.out.println("GhostX: " + ghost.getPosition().getX());
        //System.out.println("GhostY: " + ghost.getPosition().getY());

        Image keyImg = null;
        //Draw Key
        if (!human.getHasKey()) {
            keyImg = new Image(key.getSprite());
        }

        //Draw Door
        Image doorImage = new Image(door.getSprite());

        gc.drawImage(backgroundImage, 0, 0, levelDrawWidth * horScale, levelDrawHeight * verScale);//, screenWidth, screenHeight
        //, screenWidth / levelWidth, screenHeight / levelHeight
        if (drawHumanImage != null) {
            gc.drawImage(drawHumanImage, (human.getPosition().getX() + 100) * horScale, (human.getPosition().getY() + 100) * verScale, 100 * horScale, 100 * verScale);
        }
        if (drawGhostImage != null) {
            gc.drawImage(drawGhostImage, (ghost.getPosition().getX() + 100) * horScale, (ghost.getPosition().getY() + 100) * verScale, 100 * horScale, 100 * verScale);
        }
        if (key != null && !human.getHasKey()) {
            gc.drawImage(keyImg, (level.getKeyLocation().getX() + 100) * horScale, (level.getKeyLocation().getY() + 100) * verScale, 100 * horScale, 100 * verScale);
        }
        if (door != null) {
            gc.drawImage(doorImage, (level.getDoorLocation().getX() + 100) * horScale, level.getDoorLocation().getY() * verScale, 100 * horScale, 100 * verScale);
        }
    }

    /**
     * gives the right image when drawing human
     *
     * @param human
     * @param time
     * @return the image for drawing a character
     */
    private Image drawCharacter(Character character, int time) {
        Image image = null;
        if (character != null) {
            switch (character.getDirection()) {
                case UP:
                    if (!character.isIsMoving()) {
                        image = new Image(character.getSpritesUp()[0]);
                    } else {
                        getAnimatedImage(time, character.getSpritesUp());
                    }
                    break;
                case DOWN:
                    if (!character.isIsMoving()) {
                        image = new Image(character.getSpritesDown()[0]);
                    } else {
                        getAnimatedImage(time, character.getSpritesDown());
                    }
                    break;
                case LEFT:
                    if (!character.isIsMoving()) {
                        image = new Image(character.getSpritesLeft()[0]);
                    } else {
                        getAnimatedImage(time, character.getSpritesLeft());
                    }
                    break;
                case RIGHT:
                    if (!character.isIsMoving()) {
                        image = new Image(character.getSpritesRight()[0]);
                    } else {
                        getAnimatedImage(time, character.getSpritesRight());
                    }
                    break;
            }
        }
        System.out.println(image.impl_getUrl());
        return image;
    }

    /**
     * if animated, this returns the rigth animated image
     *
     * @param time
     * @param sprites
     * @return image to draw
     */
    private Image getAnimatedImage(int time, String[] sprites) {
        Image returnImage = null;
        switch (time % 3) {
            case 0:
                returnImage = new Image(sprites[0]);
                break;
            case 1:
                returnImage = new Image(sprites[1]);
                break;
            case 2:
                returnImage = new Image(sprites[2]);
                break;
        }
        //System.out.println(returnImage.impl_getUrl());
        return returnImage;
    }

    /**
     * sets the items from the game class
     *
     * @param game
     */
    public void setItems(Game game) {
        //Set human and ghost
        for (Player p : game.getPlayers()) {
            if (p.getCharacter() instanceof Human) {
                this.human = (Human) p.getCharacter();
            } else {
                this.ghost = (Ghost) p.getCharacter();
            }
        }

        //Set door and key.
        Level lvl = game.getCurrentLevel();
        this.level = lvl;
        this.levelDrawWidth = lvl.getWidth() + 200;
        this.levelDrawHeight = lvl.getHeight() + 200;
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
        horScale = screenWidth / levelDrawWidth;
        verScale = screenHeight / levelDrawHeight;
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
                        switch (code) {
                            case "W":
                                pressedKeys[0] = DirectionType.UP;
                                break;
                            case "A":
                                pressedKeys[0] = DirectionType.LEFT;
                                break;
                            case "S":
                                pressedKeys[0] = DirectionType.DOWN;
                                break;
                            case "D":
                                pressedKeys[0] = DirectionType.RIGHT;
                                break;
                        }
                        switch (code) {
                            case "UP":
                                pressedKeys[1] = DirectionType.UP;
                                break;
                            case "DOWN":
                                pressedKeys[1] = DirectionType.DOWN;
                                break;
                            case "LEFT":
                                pressedKeys[1] = DirectionType.LEFT;
                                break;
                            case "RIGHT":
                                pressedKeys[1] = DirectionType.RIGHT;
                                break;
                            case "ESCAPE":
                                pressedKeys[2] = true;
                        }
                    }
                }
        );
    }
    
    private void onKeyReleases(Scene scene){
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        switch (code) {
                            case "W":
                                pressedKeys[0] = null;
                                break;
                            case "A":
                                pressedKeys[0] = null;
                                break;
                            case "S":
                                pressedKeys[0] = null;
                                break;
                            case "D":
                                pressedKeys[0] = null;
                                break;
                        }
                        switch (code) {
                            case "UP":
                                pressedKeys[1] = null;
                                break;
                            case "DOWN":
                                pressedKeys[1] = null;
                                break;
                            case "LEFT":
                                pressedKeys[1] = null;
                                break;
                            case "RIGHT":
                                pressedKeys[1] = null;
                                break;
                            case "ESCAPE":
                                pressedKeys[2] = false;
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
    public Object[] getPressedKeys() {
        return pressedKeys;
    }

    public void clearPressedKeys() {
//        pressedKeys = new Object[3];
//        pressedKeys[0] = null;
//        pressedKeys[1] = null;
//        pressedKeys[2] = false;
    }

    /**
     * start point of this executable class
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @return the showEmpty
     */
    public boolean isShowEmpty() {
        return showEmpty;
    }

    /**
     * @param showEmpty the showEmpty to set
     */
    public void setShowEmpty(boolean showEmpty) {
        this.showEmpty = showEmpty;
    }
}
