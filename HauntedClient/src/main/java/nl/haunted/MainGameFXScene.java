package nl.haunted;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

//important for full screen http://stackoverflow.com/questions/16713554/how-to-change-scene-when-in-fullscreen-in-javafx-and-avoid-press-esc-to-exit-fu
/**
 *
 * @author Joris
 */
public class MainGameFXScene {
    
    private Timer timer;

    private double screenWidth, screenHeight;
    private double levelDrawWidth, levelDrawHeight;
    private double horScale, verScale;

    private Chat chat;
    private IPlayer p;

    private Stage chatstage;

    private Scene scene;
    private Group root;

    private int state;

    //background
    private Canvas bgLayer;
    private GraphicsContext bgGc;

    //foreground
    private Canvas keyDoorLayer, humanLayer, ghostLayer, textLayer, humanPersLayer;
    private GraphicsContext keyDoorGc, humanGc, ghostGc, textGc, humanPersGc;

    //Images:
    //Background map
    private Image bgImage;
    //Door imgs
    private Image doorImage;
    //Key imgs
    private Image keyImage;
    //Wall imgs
    private Image wallImage;
    //Human perspective
    private Image humanPerspectiveImage;
    //White player imgs
    private Image[] humanWhiteImages, ghostWhiteImages;
    //Black player imgs
    private Image[] humanBlackImages, ghostBlackImages;
    //Green player imgs
    private Image[] humanGreenImages, ghostGreenImages;
    //Red player imgs
    private Image[] humanRedImages, ghostRedImages;
    //Blue player imgs
    private Image[] humanBlueImages, ghostBlueImages;
    //Orange player imgs
    private Image[] humanOrangeImages, ghostOrangeImages;
    //Purple player imgs
    private Image[] humanPurpleImages, ghostPurpleImages;

    private gamefeed gf;

    /**
     * Builds the scene for the main game fx
     *
     * @param gf
     * @return the scene to view in the mainActivity stage
     */
    public Scene mainGameFX(gamefeed gf, Chat chat, IPlayer p) throws IOException, ClassNotFoundException {
        this.gf = gf;
        this.chat = chat;
        this.p = p;
        state = 0;
        gf.setupGameInfo();
        this.bgImage = gf.gameInfo.getBackgroundImage();
        levelDrawWidth = bgImage.getWidth();
        levelDrawHeight = bgImage.getHeight();

        root = new Group();
        scene = new Scene(root);
        determineScreenSizes();

        loadInImages();

        bgLayer = new Canvas(screenWidth, screenHeight);
        bgGc = bgLayer.getGraphicsContext2D();
        root.getChildren().add(bgLayer);

        keyDoorLayer = new Canvas(screenWidth, screenHeight);
        keyDoorGc = keyDoorLayer.getGraphicsContext2D();
        root.getChildren().add(keyDoorLayer);

        humanLayer = new Canvas(screenWidth, screenHeight);
        humanGc = humanLayer.getGraphicsContext2D();
        root.getChildren().add(humanLayer);

        ghostLayer = new Canvas(screenWidth, screenHeight);
        ghostGc = ghostLayer.getGraphicsContext2D();
        root.getChildren().add(ghostLayer);

        textLayer = new Canvas(screenWidth, screenHeight);
        textGc = textLayer.getGraphicsContext2D();
        root.getChildren().add(textLayer);

        humanPersLayer = new Canvas(screenWidth, screenHeight);
        humanPersGc = humanPersLayer.getGraphicsContext2D();
        root.getChildren().add(humanPersLayer);

        bgGc.drawImage(bgImage, 0, 0, levelDrawWidth * horScale, levelDrawHeight * verScale);

        //Handle key pressings
        onKeyPresses(scene);
        onKeyReleases(scene);
        
        timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {          
                try {
                    gf.decompressFeed();
                } catch (IOException ex) {
                    Logger.getLogger(MainGameFXScene.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainGameFXScene.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        timer.scheduleAtFixedRate(task, 0, 16);

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                if (!gf.gameInfo.isGameEnd() && !gf.gameInfo.isRoundEnd()) { //TODO
                    keyDoorGc.clearRect(0, 0, screenWidth, screenHeight);
                    humanGc.clearRect(0, 0, screenWidth, screenHeight);
                    ghostGc.clearRect(0, 0, screenWidth, screenHeight);
                    textGc.clearRect(0, 0, screenWidth, screenHeight);
                    humanPersGc.clearRect(0, 0, screenWidth, screenHeight);
                    drawImages();
                    drawTexts();
                }
                else if (gf.gameInfo.isGameEnd()){ //TODO
                    this.stop();
                    timer.cancel();
                    //gf.gameInfo.endGame(); //TODO
                }
                else if(gf.gameInfo.isRoundEnd()){ //TODO
                    this.stop();
                    timer.cancel();
                    //gf.gameInfo.endRound();//TODO
                }

            }
        }.start();
//        ImageView iv = new ImageView(image);
//        
//        Rotate rotation = new Rotate(90, image.getWidth()/2, image.getHeight()/2);
//        iv.getTransforms().add(rotation);

        return scene;
    }

    /**
     * Draws the texts out of Game info
     */
    private void drawTexts() {
        textGc.setFont(new Font("Times New Roman", 14.0));
        textGc.setFill(Color.BLACK);
        textGc.fillText(("Ghost lives left: " + gf.gameInfo.getGhostLives()).toUpperCase(), 10, 14);
        textGc.fillText(("Current floor: " + gf.gameInfo.getCurrentFloor()).toUpperCase(), 10, 30);
        //       textGc.strokeText("Current human: " + gf.gameInfo.getCurrentHuman(), 10, 46);
        if (!gf.gameInfo.getKey()) {
            textGc.fillText(("Key has been picked up by human").toUpperCase(), 10, 46);
        } else {
            textGc.fillText(("Key hasn't been picked up by the human yet").toUpperCase(), 10, 46);
        }
    }

    /**
     * builds the scene to make chats.
     *
     * @return the scene to view in the ChatStage.
     */
    private Scene getChatScene() {
        Group chatRoot = new Group();
        Scene chatScene = new Scene(chatRoot);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        TextFlow tf = new TextFlow();
        for (Message chatMessage : chat.getMessages()) {
            if (gf.gameInfo.amIHuman() && chatMessage.getIsVisibleForEveryone()) {
                Text txt = new Text(chatMessage.toString());
                tf.getChildren().add(txt);
            } else if (!gf.gameInfo.amIHuman()) {
                Text txt = new Text(chatMessage.toString());
                tf.getChildren().add(txt);
            }

        }
        grid.add(tf, 0, 0, 8, 8);

        TextField tef = new TextField();
        grid.add(tef, 0, 9, 1, 7);

        CheckBox cbGhost = new CheckBox();
        cbGhost.setText("Visible for all players?");
        grid.add(cbGhost, 0, 9);

        Button btCommit = new Button("Send");
        btCommit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (tef.getText().trim().length() >= 0) {
                        if (cbGhost.isSelected()) {
                            chat.sendMessage(new Message(tef.getText(), p, true));
                        } else {
                            chat.sendMessage(new Message(tef.getText(), p, false));
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainGameFXScene.class.getName()).log(Level.SEVERE, null, ex);
                }
                chatstage.close();
            }
        });
        grid.add(btCommit, 8, 9);

        chatRoot.getChildren().add(grid);
        return chatScene;
    }

    /**
     * handle the on key pressings
     *
     * @param scene
     */
    private void onKeyPresses(Scene scene) {
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();

                        if (code.equals("ENTER")) {
                            //TODO: call up chatbox popup
                            chatstage = new Stage();
                            chatstage.setTitle("Chat | Haunted");
                            chatstage.setScene(getChatScene());
                            chatstage.show();
                        } else {

                            switch (code) {
                                case "W":
                                    HauntedClient.getController().getInputController().setDirection(DirectionType.UP);
                                    break;
                                case "A":
                                    HauntedClient.getController().getInputController().setDirection(DirectionType.LEFT);
                                    break;
                                case "S":
                                    HauntedClient.getController().getInputController().setDirection(DirectionType.DOWN);
                                    break;
                                case "D":
                                    HauntedClient.getController().getInputController().setDirection(DirectionType.RIGHT);
                                    break;
                                case "UP":
                                    HauntedClient.getController().getInputController().setDirection(DirectionType.UP);
                                    break;
                                case "LEFT":
                                    HauntedClient.getController().getInputController().setDirection(DirectionType.LEFT);
                                    break;
                                case "DOWN":
                                    HauntedClient.getController().getInputController().setDirection(DirectionType.DOWN);
                                    break;
                                case "RIGHT":
                                    HauntedClient.getController().getInputController().setDirection(DirectionType.RIGHT);
                                    break;
                                default:
                                    HauntedClient.getController().getInputController().setDirection(null);
                                    break;
                            }

                        }

                        // only add once... prevent duplicates
                    }
                }
        );
    }

    /**
     * Handle the on key releases
     *
     * @param scene
     */
    private void onKeyReleases(Scene scene) {
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();

                        switch (code) {
                            case "W":
                                HauntedClient.getController().getInputController().setDirection(null);
                                break;
                            case "A":
                                HauntedClient.getController().getInputController().setDirection(null);
                                break;
                            case "S":
                                HauntedClient.getController().getInputController().setDirection(null);
                                break;
                            case "D":
                                HauntedClient.getController().getInputController().setDirection(null);
                                break;
                            case "UP":
                                HauntedClient.getController().getInputController().setDirection(null);
                                break;
                            case "LEFT":
                                HauntedClient.getController().getInputController().setDirection(null);
                                break;
                            case "DOWN":
                                HauntedClient.getController().getInputController().setDirection(null);
                                break;
                            case "RIGHT":
                                HauntedClient.getController().getInputController().setDirection(null);
                                break;
                        }
                    }
                }
        );
    }

    /**
     * Loads in the images to draw it.
     */
    private void loadInImages() {
        keyImage = new Image("key.png");
        doorImage = new Image("door.png");
        wallImage = new Image("wall.png");
        humanPerspectiveImage = new Image("outOfFlashlight.png");
        humanWhiteImages = new Image[]{new Image("humanWhite1.png"), new Image("humanWhite2.png"), new Image("humanWhite3.png")};
        ghostWhiteImages = new Image[]{new Image("ghostWhite1.png"), new Image("ghostWhite2.png"), new Image("ghostWhite3.png")};
        humanBlackImages = new Image[]{new Image("humanBlack1.png"), new Image("humanBlack2.png"), new Image("humanBlack3.png")};
        ghostBlackImages = new Image[]{new Image("ghostBlack1.png"), new Image("ghostBlack2.png"), new Image("ghostBlack3.png")};
        humanGreenImages = new Image[]{new Image("humanGreen1.png"), new Image("humanGreen2.png"), new Image("humanGreen3.png")};
        ghostGreenImages = new Image[]{new Image("ghostGreen1.png"), new Image("ghostGreen2.png"), new Image("ghostGreen3.png")};
        humanRedImages = new Image[]{new Image("humanRed1.png"), new Image("humanRed2.png"), new Image("humanRed3.png")};
        ghostRedImages = new Image[]{new Image("ghostRed1.png"), new Image("ghostRed2.png"), new Image("ghostRed3.png")};
        humanBlueImages = new Image[]{new Image("humanBlue1.png"), new Image("humanBlue2.png"), new Image("humanBlue3.png")};
        ghostBlueImages = new Image[]{new Image("ghostBlue1.png"), new Image("ghostBlue2.png"), new Image("ghostBlue3.png")};
        humanOrangeImages = new Image[]{new Image("humanOrange1.png"), new Image("humanOrange2.png"), new Image("humanOrange3.png")};
        ghostOrangeImages = new Image[]{new Image("ghostOrange1.png"), new Image("ghostOrange2.png"), new Image("ghostOrange3.png")};
        humanPurpleImages = new Image[]{new Image("humanPurple1.png"), new Image("humanPurple2.png"), new Image("humanPurple3.png")};
        ghostPurpleImages = new Image[]{new Image("ghostPurple1.png"), new Image("ghostPurple2.png"), new Image("ghostPurple3.png")};

    }

    /**
     * Draws the images for all objects on the screen
     */
    private void drawImages() {
        for (Entity e : gf.gameInfo.getEntities()) {
            switch (e.getType()) {
                case Door:
                    drawRotatedImage(keyDoorGc, doorImage, getAngle(e.getDirection()), e.getPosition().getX() + 100, e.getPosition().getY(), horScale, verScale);
                    break;
                case Key:
                    keyDoorGc.drawImage(keyImage, e.getPosition().getX() + 100, e.getPosition().getY() + 100, keyImage.getWidth() * horScale, keyImage.getHeight() * verScale);
                    break;
                case Human:
                    if (gf.gameInfo.amIHuman()) {
                        //TODO: calculate proper x and y coordinates
                        drawRotatedImage(humanPersGc, humanPerspectiveImage, getAngle(e.getDirection()), e.getPosition().getX() - 1900, e.getPosition().getY() - 1900, horScale, verScale);
                    }
                    drawRotatedImage(humanGc, getAnimatedHumanImage(e), getAngle(e.getDirection()), e.getPosition().getX() + 100, e.getPosition().getY() + 100, horScale, verScale);
                    break;
                case Ghost:
                    //todo : draw animated ghost images
                    if (e.getWall()) {
                        drawRotatedImage(ghostGc, wallImage, 0, e.getPosition().getX() + 100, e.getPosition().getY() + 100, horScale, verScale);
                    } else {
                        drawRotatedImage(ghostGc, getAnimatedGhostImage(e), getAngle(e.getDirection()), e.getPosition().getX() + 100, e.getPosition().getY() + 100, horScale, verScale);
                    }
                    break;
                default:
                break;
                        
            }
        }
    }

    /**
     * convert directionType into angle degrees (example: LEFT == 270°)
     *
     * @param direction
     * @return degrees of the rotation
     */
    private double getAngle(DirectionType direction) {
        switch (direction) {
            case UP:
                return 0;
            case RIGHT:
                return 90;
            case DOWN:
                return 180;
            case LEFT:
                return 270;
            default:
                return 0;
        }
    }

    /**
     * Rotates the graphicscontext for a rotated image.
     *
     * @param gc
     * @param angle
     * @param px
     * @param py
     */
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /**
     * Draws the rotated image on the right position with the right scalings.
     *
     * @param gc
     * @param image
     * @param angle
     * @param tlpx
     * @param tlpy
     * @param scaleX
     * @param scaleY
     */
    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy, double scaleX, double scaleY) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, ((tlpx + image.getWidth()) * scaleX) / 2, ((tlpy + image.getHeight()) * scaleY) / 2);
        gc.drawImage(image, tlpx, tlpy, image.getWidth() * scaleX, image.getHeight() * scaleY);
        gc.restore(); // back to original state (before rotation)
    }

    /**
     * Returns the proper human image
     *
     * @param e
     * @return image
     */
    private Image getAnimatedHumanImage(Entity e) {
        Image returnImage = null;

        java.awt.Color c = e.getColor();
        if (c == java.awt.Color.WHITE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanWhiteImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanWhiteImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanWhiteImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanWhiteImages[0];
            }
        } else if (c == java.awt.Color.BLACK) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanBlackImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanBlackImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanBlackImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanBlackImages[0];
            }
        } else if (c == java.awt.Color.GREEN) {
            if (e.getMoving()) {

                switch (state) {
                    case 2:
                        returnImage = humanGreenImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanGreenImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanGreenImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanGreenImages[0];
            }
        } else if (c == java.awt.Color.RED) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanRedImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanRedImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanRedImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanRedImages[0];
            }
        } else if (c == java.awt.Color.BLUE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanBlueImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanBlueImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanBlueImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanBlueImages[0];
            }
        } else if (c == java.awt.Color.ORANGE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanOrangeImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanOrangeImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanOrangeImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanOrangeImages[0];
            }
        } else if (c == java.awt.Color.MAGENTA) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanPurpleImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanPurpleImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanPurpleImages[2];
                        state = 2;
                        break;
                }
            }
        } else {
            returnImage = humanPurpleImages[0];
        }

        return returnImage;
    }

    /**
     * Returns the proper ghost image
     *
     * @param e
     * @return image
     */
    private Image getAnimatedGhostImage(Entity e) {
        Image returnImage = null;

        java.awt.Color c = e.getColor();
        if (c == java.awt.Color.WHITE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostWhiteImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostWhiteImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostWhiteImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostWhiteImages[0];
            }
        } else if (c == java.awt.Color.BLACK) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostBlackImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostBlackImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostBlackImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostBlackImages[0];
            }
        } else if (c == java.awt.Color.GREEN) {
            if (e.getMoving()) {

                switch (state) {
                    case 2:
                        returnImage = ghostGreenImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostGreenImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostGreenImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostGreenImages[0];
            }
        } else if (c == java.awt.Color.RED) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostRedImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostRedImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostRedImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostRedImages[0];
            }
        } else if (c == java.awt.Color.BLUE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostBlueImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostBlueImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostBlueImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostBlueImages[0];
            }
        } else if (c == java.awt.Color.ORANGE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostOrangeImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostOrangeImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostOrangeImages[2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostOrangeImages[0];
            }
        } else if (c == java.awt.Color.MAGENTA) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostPurpleImages[0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostPurpleImages[1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostPurpleImages[2];
                        state = 2;
                        break;
                }
            }
        } else {
            returnImage = ghostPurpleImages[0];
        }
        return returnImage;
    }

    /**
     * Calculates the screen sizes and the scalings
     */
    private void determineScreenSizes() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.getWidth();
        screenHeight = screenSize.getHeight();
        horScale = screenWidth / levelDrawWidth;
        verScale = screenHeight / levelDrawHeight;
    }

}
