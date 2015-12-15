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
import javafx.scene.text.FontWeight;
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
    private Canvas keyDoorLayer, humanLayer, textLayer, humanPersLayer;
    private GraphicsContext keyDoorGc, humanGc, textGc, humanPersGc;

    //ghost gcs
    private Canvas ghostlayer = new Canvas();
    private GraphicsContext[] ghostGcs = new GraphicsContext[6];

    private int ghostChooser = 0;

    //Images:
    //Background map
    private Image bgImage;
    //Door imgs
    private Image[] doorImage;
    //Key imgs
    private Image keyImage;
    //Wall imgs
    private Image wallImage;
    //Human perspective
    private Image[] humanPerspectiveImage;
    //White player imgs
    private Image[][] humanWhiteImages, ghostWhiteImages;
    //Black player imgs
    private Image[][] humanBlackImages, ghostBlackImages;
    //Green player imgs
    private Image[][] humanGreenImages, ghostGreenImages;
    //Red player imgs
    private Image[][] humanRedImages, ghostRedImages;
    //Blue player imgs
    private Image[][] humanBlueImages, ghostBlueImages;
    //Orange player imgs
    private Image[][] humanOrangeImages, ghostOrangeImages;
    //Purple player imgs
    private Image[][] humanPurpleImages, ghostPurpleImages;

    private gamefeed gf;

    /**
     * Builds the scene for the main game fx
     *
     * @param gf
     * @param chat
     * @param p
     * @return the scene to view in the mainActivity stage
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InterruptedException
     */
    public Scene mainGameFX(gamefeed gf, Chat chat, IPlayer p) throws IOException, ClassNotFoundException, InterruptedException {
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
        
        ghostlayer = new Canvas(screenWidth, screenHeight);
        for (int i = 0; i < ghostGcs.length; i++) {           
            ghostGcs[i] = ghostlayer.getGraphicsContext2D();
            
        }
        root.getChildren().add(ghostlayer);

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
        TimerTask task = new TimerTask() {
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

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                if (!gf.gameInfo.isGameEnd() && !gf.gameInfo.isRoundEnd()) { //TODO
                    
                    keyDoorGc.clearRect(0, 0, screenWidth, screenHeight);
                    humanGc.clearRect(0, 0, screenWidth, screenHeight);

                    for (int i = 0; i < ghostGcs.length; i++) {
                        ghostGcs[i].clearRect(0, 0, screenWidth, screenHeight);
                    }

                    humanPersGc.clearRect(0, 0, screenWidth, screenHeight);
                    textGc.clearRect(0, 0, screenWidth, screenHeight);
                    drawImages();
                    drawTexts();
                    try {
                        HauntedClient.getController().getInputController().sendInput();
                    } catch (IOException ex) {
                        Logger.getLogger(MainGameFXScene.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                } else if (gf.gameInfo.isGameEnd()) { //TODO
                    this.stop();
                    timer.cancel();
                    //gf.gameInfo.endGame(); //TODO
                } else if (gf.gameInfo.isRoundEnd()) { //TODO
                    this.stop();
                    timer.cancel();
                    //gf.gameInfo.endRound();//TODO
                }

            }
        };
        at.start();
        //ImageView(image);
//        
//        Rotate rotation = new Rotate(90, image.getWidth()/2, image.getHeight()/2);
//        iv.getTransforms().add(rotation);

        return scene;
    }

    /**
     * Draws the texts out of Game info
     */
    private void drawTexts() {
        textGc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20.0));
        textGc.setFill(Color.BLUE);
        textGc.fillText(("Ghost lives left: " + gf.gameInfo.getGhostLives()).toUpperCase(), 10, 20);
        textGc.fillText(("Current floor: " + gf.gameInfo.getCurrentFloor()).toUpperCase(), 10, 42);
        //       textGc.strokeText("Current human: " + gf.gameInfo.getCurrentHuman(), 10, 46);
        if (!gf.gameInfo.getKey()) {
            textGc.fillText(("Key has been picked up by human").toUpperCase(), 10, 64);
        } else {
            textGc.fillText(("Key hasn't been picked up by the human yet").toUpperCase(), 10, 64);
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
     * Loads in the images to draw it. --> UP DOWN LEFT RIGHT
     */
    private void loadInImages() {
        keyImage = new Image("key.png");
        doorImage = new Image[]{new Image("doorUp.png"), new Image("doorDown.png"), new Image("doorLeft.png"), new Image("doorRight.png")};
        wallImage = new Image("wall.png");
        humanPerspectiveImage = new Image[]{new Image("outOfFlashlightUp.png"), new Image("outOfFlashlightDown.png"), new Image("outOfFlashlightLeft.png"), new Image("outOfFlashlightRight.png")};

        humanWhiteImages = new Image[][]{{new Image("humanWhiteUp1.png"), new Image("humanWhiteUp2.png"), new Image("humanWhiteUp3.png")},
        {new Image("humanWhiteDown1.png"), new Image("humanWhiteDown2.png"), new Image("humanWhiteDown3.png")}, {new Image("humanWhiteLeft1.png"), new Image("humanWhiteLeft2.png"), new Image("humanWhiteLeft3.png")},
        {new Image("humanWhiteRight1.png"), new Image("humanWhiteRight2.png"), new Image("humanWhiteRight3.png")}};

        ghostWhiteImages = new Image[][]{{new Image("ghostWhiteUp1.png"), new Image("ghostWhiteUp2.png"), new Image("ghostWhiteUp3.png")},
        {new Image("ghostWhiteDown1.png"), new Image("ghostWhiteDown2.png"), new Image("ghostWhiteDown3.png")}, {new Image("ghostWhiteLeft1.png"), new Image("ghostWhiteLeft2.png"), new Image("ghostWhiteLeft3.png")},
        {new Image("ghostWhiteRight1.png"), new Image("ghostWhiteRight2.png"), new Image("ghostWhiteRight3.png")}};

        humanBlackImages = new Image[][]{{new Image("humanBlackUp1.png"), new Image("humanBlackUp2.png"), new Image("humanBlackUp3.png")},
        {new Image("humanBlackDown1.png"), new Image("humanBlackDown2.png"), new Image("humanBlackDown3.png")}, {new Image("humanBlackLeft1.png"), new Image("humanBlackLeft2.png"), new Image("humanBlackLeft3.png")},
        {new Image("humanBlackRight1.png"), new Image("humanBlackRight2.png"), new Image("humanBlackRight3.png")}};

        ghostBlackImages = new Image[][]{{new Image("ghostBlackUp1.png"), new Image("ghostBlackUp2.png"), new Image("ghostBlackUp3.png")},
        {new Image("ghostBlackDown1.png"), new Image("ghostBlackDown2.png"), new Image("ghostBlackDown3.png")}, {new Image("ghostBlackLeft1.png"), new Image("ghostBlackLeft2.png"), new Image("ghostBlackLeft3.png")},
        {new Image("ghostBlackRight1.png"), new Image("ghostBlackRight2.png"), new Image("ghostBlackRight3.png")}};

        humanGreenImages = new Image[][]{{new Image("humanGreenUp1.png"), new Image("humanGreenUp2.png"), new Image("humanGreenUp3.png")},
        {new Image("humanGreenDown1.png"), new Image("humanGreenDown2.png"), new Image("humanGreenDown3.png")}, {new Image("humanGreenLeft1.png"), new Image("humanGreenLeft2.png"), new Image("humanGreenLeft3.png")},
        {new Image("humanGreenRight1.png"), new Image("humanGreenRight2.png"), new Image("humanGreenRight3.png")}};

        ghostGreenImages = new Image[][]{{new Image("ghostGreenUp1.png"), new Image("ghostGreenUp2.png"), new Image("ghostGreenUp3.png")},
        {new Image("ghostGreenDown1.png"), new Image("ghostGreenDown2.png"), new Image("ghostGreenDown3.png")}, {new Image("ghostGreenLeft1.png"), new Image("ghostGreenLeft2.png"), new Image("ghostGreenLeft3.png")},
        {new Image("ghostGreenRight1.png"), new Image("ghostGreenRight2.png"), new Image("ghostGreenRight3.png")}};

        humanRedImages = new Image[][]{{new Image("humanRedUp1.png"), new Image("humanRedUp2.png"), new Image("humanRedUp3.png")},
        {new Image("humanRedDown1.png"), new Image("humanRedDown2.png"), new Image("humanRedDown3.png")}, {new Image("humanRedLeft1.png"), new Image("humanRedLeft2.png"), new Image("humanRedLeft3.png")},
        {new Image("humanRedRight1.png"), new Image("humanRedRight2.png"), new Image("humanRedRight3.png")}};

        ghostRedImages = new Image[][]{{new Image("ghostRedUp1.png"), new Image("ghostRedUp2.png"), new Image("ghostRedUp3.png")},
        {new Image("ghostRedDown1.png"), new Image("ghostRedDown2.png"), new Image("ghostRedDown3.png")}, {new Image("ghostRedLeft1.png"), new Image("ghostRedLeft2.png"), new Image("ghostRedLeft3.png")},
        {new Image("ghostRedRight1.png"), new Image("ghostRedRight2.png"), new Image("ghostRedRight3.png")}};

        humanBlueImages = new Image[][]{{new Image("humanBlueUp1.png"), new Image("humanBlueUp2.png"), new Image("humanBlueUp3.png")},
        {new Image("humanBlueDown1.png"), new Image("humanBlueDown2.png"), new Image("humanBlueDown3.png")}, {new Image("humanBlueLeft1.png"), new Image("humanBlueLeft2.png"), new Image("humanBlueLeft3.png")},
        {new Image("humanBlueRight1.png"), new Image("humanBlueRight2.png"), new Image("humanBlueRight3.png")}};

        ghostBlueImages = new Image[][]{{new Image("ghostBlueUp1.png"), new Image("ghostBlueUp2.png"), new Image("ghostBlueUp3.png")},
        {new Image("ghostBlueDown1.png"), new Image("ghostBlueDown2.png"), new Image("ghostBlueDown3.png")}, {new Image("ghostBlueLeft1.png"), new Image("ghostBlueLeft2.png"), new Image("ghostBlueLeft3.png")},
        {new Image("ghostBlueRight1.png"), new Image("ghostBlueRight2.png"), new Image("ghostBlueRight3.png")}};

        humanOrangeImages = new Image[][]{{new Image("humanOrangeUp1.png"), new Image("humanOrangeUp2.png"), new Image("humanOrangeUp3.png")},
        {new Image("humanOrangeDown1.png"), new Image("humanOrangeDown2.png"), new Image("humanOrangeDown3.png")}, {new Image("humanOrangeLeft1.png"), new Image("humanOrangeLeft2.png"), new Image("humanOrangeLeft3.png")},
        {new Image("humanOrangeRight1.png"), new Image("humanOrangeRight2.png"), new Image("humanOrangeRight3.png")}};

        ghostOrangeImages = new Image[][]{{new Image("ghostOrangeUp1.png"), new Image("ghostOrangeUp2.png"), new Image("ghostOrangeUp3.png")},
        {new Image("ghostOrangeDown1.png"), new Image("ghostOrangeDown2.png"), new Image("ghostOrangeDown3.png")}, {new Image("ghostOrangeLeft1.png"), new Image("ghostOrangeLeft2.png"), new Image("ghostOrangeLeft3.png")},
        {new Image("ghostOrangeRight1.png"), new Image("ghostOrangeRight2.png"), new Image("ghostOrangeRight3.png")}};

        humanPurpleImages = new Image[][]{{new Image("humanPurpleUp1.png"), new Image("humanPurpleUp2.png"), new Image("humanPurpleUp3.png")},
        {new Image("humanPurpleDown1.png"), new Image("humanPurpleDown2.png"), new Image("humanPurpleDown3.png")}, {new Image("humanPurpleLeft1.png"), new Image("humanPurpleLeft2.png"), new Image("humanPurpleLeft3.png")},
        {new Image("humanPurpleRight1.png"), new Image("humanPurpleRight2.png"), new Image("humanPurpleRight3.png")}};

        ghostPurpleImages = new Image[][]{{new Image("ghostPurpleUp1.png"), new Image("ghostPurpleUp2.png"), new Image("ghostPurpleUp3.png")},
        {new Image("ghostPurpleDown1.png"), new Image("ghostPurpleDown2.png"), new Image("ghostPurpleDown3.png")}, {new Image("ghostPurpleLeft1.png"), new Image("ghostPurpleLeft2.png"), new Image("ghostPurpleLeft3.png")},
        {new Image("ghostPurpleRight1.png"), new Image("ghostPurpleRight2.png"), new Image("ghostPurpleRight3.png")}};
    }

    /**
     * Draws the images for all objects on the screen
     */
    private void drawImages() {
        for (Entity e : gf.gameInfo.getEntities()) {
            if (ghostChooser >= gf.gameInfo.getEntities().size()-3) {
                ghostChooser = 0;
            }
            switch (e.getType()) {
                case Door:
                    if (gf.gameInfo.amIHuman()) {
                        if (e.getDirection() == DirectionType.UP) {
                            keyDoorGc.drawImage(doorImage[0], (e.getPosition().getX() + 100) * horScale, (e.getPosition().getY()) * verScale, doorImage[0].getWidth() * horScale, doorImage[0].getHeight() * verScale);
                        } else if (e.getDirection() == DirectionType.DOWN) {
                            keyDoorGc.drawImage(doorImage[1], (e.getPosition().getX() + 100) * horScale, (e.getPosition().getY() + 200) * verScale, doorImage[1].getWidth() * horScale, doorImage[1].getHeight() * verScale);
                        } else if (e.getDirection() == DirectionType.LEFT) {
                            keyDoorGc.drawImage(doorImage[2], (e.getPosition().getX()) * horScale, (e.getPosition().getY() + 100) * verScale, doorImage[2].getWidth() * horScale, doorImage[2].getHeight() * verScale);
                        } else {
                            keyDoorGc.drawImage(doorImage[3], (e.getPosition().getX() + 200) * horScale, (e.getPosition().getY() + 100) * verScale, doorImage[3].getWidth() * horScale, doorImage[3].getHeight() * verScale);
                        }
                    }
                    break;
                case Key:
                    if (gf.gameInfo.amIHuman()) {
                        keyDoorGc.drawImage(keyImage, (e.getPosition().getX() + 100) * horScale, (e.getPosition().getY() + 100) * verScale, keyImage.getWidth() * horScale, keyImage.getHeight() * verScale);
                    }
                    break;
                case Human:
                    if (gf.gameInfo.amIHuman()) {
                        if (e.getDirection() == DirectionType.UP) {
                            humanPersGc.drawImage(humanPerspectiveImage[0], (e.getPosition().getX() - 1800) * horScale, (e.getPosition().getY() - 1800) * verScale, humanPerspectiveImage[0].getWidth() * horScale, humanPerspectiveImage[0].getHeight() * verScale);
                        } else if (e.getDirection() == DirectionType.DOWN) {
                            humanPersGc.drawImage(humanPerspectiveImage[1], (e.getPosition().getX() - 1800) * horScale, (e.getPosition().getY() - 1800) * verScale, humanPerspectiveImage[1].getWidth() * horScale, humanPerspectiveImage[1].getHeight() * verScale);
                        } else if (e.getDirection() == DirectionType.LEFT) {
                            humanPersGc.drawImage(humanPerspectiveImage[2], (e.getPosition().getX() - 1800) * horScale, (e.getPosition().getY() - 1800) * verScale, humanPerspectiveImage[2].getWidth() * horScale, humanPerspectiveImage[2].getHeight() * verScale);
                        } else {
                            humanPersGc.drawImage(humanPerspectiveImage[3], (e.getPosition().getX() - 1800) * horScale, (e.getPosition().getY() - 1800) * verScale, humanPerspectiveImage[3].getWidth() * horScale, humanPerspectiveImage[3].getHeight() * verScale);
                        }
                        //drawRotatedImage(humanPersGc, humanPerspectiveImage, getAngle(e.getDirection()), (e.getPosition().getX() - 1900), (e.getPosition().getY() - 1900), horScale, verScale);
                    }
                    humanGc.drawImage(getAnimatedHumanImage(e), (e.getPosition().getX()+100) * horScale, (e.getPosition().getY()+100) * verScale, getAnimatedHumanImage(e).getWidth() * horScale, getAnimatedHumanImage(e).getHeight() * verScale);
                    //drawRotatedImage(humanGc, getAnimatedHumanImage(e), getAngle(e.getDirection()), (e.getPosition().getX() + 100), (e.getPosition().getY() + 100), horScale, verScale);
                    break;
                case Ghost:

                    if (e.getWall()) {
                        ghostGcs[ghostChooser].drawImage(wallImage, (e.getPosition().getX() + 100) * horScale, (e.getPosition().getY() + 100) * verScale, wallImage.getWidth() * horScale, wallImage.getHeight() * verScale);
                        //drawRotatedImage(ghostGcs[ghostChooser], wallImage, 0, (e.getPosition().getX() + 100), (e.getPosition().getY() + 100), horScale, verScale);
                    } else {
                        ghostGcs[ghostChooser].drawImage(getAnimatedGhostImage(e), (e.getPosition().getX() + 100) * horScale, (e.getPosition().getY() + 100) * verScale, getAnimatedGhostImage(e).getWidth() * horScale, getAnimatedGhostImage(e).getHeight() * verScale);
                    }
                    ghostChooser++;
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
        //rotate(gc, angle, ((tlpx + image.getWidth()) * scaleX) / 2, ((tlpy + image.getHeight()) * scaleY) / 2);
        rotate(gc, angle, tlpx + 50, tlpy + 50);
        //rotate(gc, angle, tlpx, (tlpy + image.getHeight()/2));
        if (angle == 90 || angle == 270) {
            gc.drawImage(image, tlpx * scaleX, tlpy * scaleY, image.getWidth() * scaleX, image.getWidth() * scaleX);
        } else {
            gc.drawImage(image, tlpx * scaleX, tlpy * scaleY, image.getWidth() * scaleX, image.getHeight() * scaleY);
        }
        gc.restore(); // back to original state (before rotation)
    }

    private int getRotationIndex(DirectionType direction) {
        switch (direction) {
            case UP:
                return 0;
            case DOWN:
                return 1;
            case LEFT:
                return 2;
            case RIGHT:
                return 3;
            default:
                return 0;
        }
    }

    /**
     * Returns the proper human image
     *
     * @param e
     * @return image
     */
    private Image getAnimatedHumanImage(Entity e) {
        Image returnImage = null;
        int rotIndex = getRotationIndex(e.getDirection());

        java.awt.Color c = e.getColor();
        if (c == java.awt.Color.WHITE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanWhiteImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanWhiteImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanWhiteImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanWhiteImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.BLACK) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanBlackImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanBlackImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanBlackImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanBlackImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.GREEN) {
            if (e.getMoving()) {

                switch (state) {
                    case 2:
                        returnImage = humanGreenImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanGreenImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanGreenImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanGreenImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.RED) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanRedImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanRedImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanRedImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanRedImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.BLUE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanBlueImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanBlueImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanBlueImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanBlueImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.ORANGE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanOrangeImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanOrangeImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanOrangeImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = humanOrangeImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.MAGENTA) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = humanPurpleImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = humanPurpleImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = humanPurpleImages[rotIndex][2];
                        state = 2;
                        break;
                }
            }
        } else {
            returnImage = humanPurpleImages[rotIndex][0];
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
        int rotIndex = getRotationIndex(e.getDirection());

        java.awt.Color c = e.getColor();
        if (c == java.awt.Color.WHITE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostWhiteImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostWhiteImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostWhiteImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostWhiteImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.BLACK) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostBlackImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostBlackImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostBlackImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostBlackImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.GREEN) {
            if (e.getMoving()) {

                switch (state) {
                    case 2:
                        returnImage = ghostGreenImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostGreenImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostGreenImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostGreenImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.RED) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostRedImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostRedImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostRedImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostRedImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.BLUE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostBlueImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostBlueImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostBlueImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostBlueImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.ORANGE) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostOrangeImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostOrangeImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostOrangeImages[rotIndex][2];
                        state = 2;
                        break;
                }
            } else {
                returnImage = ghostOrangeImages[rotIndex][0];
            }
        } else if (c == java.awt.Color.MAGENTA) {
            if (e.getMoving()) {
                switch (state) {
                    case 2:
                        returnImage = ghostPurpleImages[rotIndex][0];
                        state = 0;
                        break;
                    case 0:
                        returnImage = ghostPurpleImages[rotIndex][1];
                        state = 1;
                        break;
                    case 1:
                        returnImage = ghostPurpleImages[rotIndex][2];
                        state = 2;
                        break;
                }
            }
        } else {
            returnImage = ghostPurpleImages[rotIndex][0];
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
