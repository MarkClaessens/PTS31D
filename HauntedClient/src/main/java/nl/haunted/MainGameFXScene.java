/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

/**
 *
 * @author jvdwi
 */
public class MainGameFXScene {

    private double screenWidth, screenHeight;
    private double levelDrawWidth, levelDrawHeight;
    private double horScale, verScale;

    private Scene scene;
    private Group root;

    private int state;

    //background
    private Canvas bgLayer;
    private GraphicsContext bgGc;

    //foreground
    private Canvas keyDoorLayer, humanLayer, ghostLayer;
    private GraphicsContext keyDoorGc, humanGc, ghostGc;

    //Images:
    //Background map
    private Image bgImage;
    //Door imgs
    private Image doorImage;
    //Key imgs
    private Image keyImage;
    //Wall imgs
    private Image wallImage;
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

//    Image image;
    private gamefeed gf;

    public Scene MainGameFX(gamefeed gf) {
        this.gf = gf;
        state = 0;
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

        bgGc.drawImage(bgImage, 0, 0, levelDrawWidth * horScale, levelDrawHeight * verScale);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                List<Object> obj = new ArrayList();
                root.getChildren().stream().filter((o) -> (o instanceof Polygon)).forEach((o) -> {
                    obj.add(o);
                });
                root.getChildren().removeAll(obj);
                drawImages();
            }
        }.start();
//        ImageView iv = new ImageView(image);
//        
//        Rotate rotation = new Rotate(90, image.getWidth()/2, image.getHeight()/2);
//        iv.getTransforms().add(rotation);

        return scene;
    }

    private void loadInImages() {
        keyImage = new Image("key.png");
        doorImage = new Image("door.png");
        wallImage = new Image("wall.png");
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

    private void drawImages() {
        for (Entity e : gf.gameInfo.getEntities()) {
            switch (e.getType()) {
                case Door:
                    drawRotatedImage(keyDoorGc, doorImage, getAngle(e.getDirection()), e.getPosition().getX(), e.getPosition().getY(), horScale, verScale);
                    break;
                case Key:
                    keyDoorGc.drawImage(keyImage, e.getPosition().getX(), e.getPosition().getY(), keyImage.getWidth() * horScale, keyImage.getHeight() * verScale);
                    break;
                case Human:
                    //todo : draw animated human images
                    drawRotatedImage(humanGc, getAnimatedHumanImage(e), getAngle(e.getDirection()), e.getPosition().getX(), e.getPosition().getY(), horScale, verScale);
                    break;
                case Ghost:
                    //todo : draw animated ghost images
                    if (e.getWall()) {
                        int xPos = (((int) e.getPosition().getX()) + 50) / 100 * 100;
                        int yPos = (((int) e.getPosition().getY()) + 50) / 100 * 100;
                        drawRotatedImage(ghostGc, wallImage, 0, xPos, yPos, horScale, verScale);
                    } else {
                        drawRotatedImage(ghostGc, getAnimatedGhostImage(e), getAngle(e.getDirection()), e.getPosition().getX(), e.getPosition().getY(), horScale, verScale);
                    }
                    break;

            }
        }
    }

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

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy, double scaleX, double scaleY) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, ((tlpx + image.getWidth()) * scaleX) / 2, ((tlpy + image.getHeight()) * scaleY) / 2);
        gc.drawImage(image, tlpx, tlpy, image.getWidth() * scaleX, image.getHeight() * scaleY);
        gc.restore(); // back to original state (before rotation)
    }

    private Image getAnimatedHumanImage(Entity e) {
        Image returnImage = null;

        Color c = e.getColor();
        if (c == Color.WHITE) {
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
        } else if (c == Color.BLACK) {
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
        } else if (c == Color.GREEN) {
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
        } else if (c == Color.RED) {
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
        } else if (c == Color.BLUE) {
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
        } else if (c == Color.ORANGE) {
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
        } else if (c == Color.PURPLE) {
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

        return returnImage;
    }

    private Image getAnimatedGhostImage(Entity e) {
        Image returnImage = null;
        Color c = e.getColor();
        if (c == Color.WHITE) {
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
        } else if (c == Color.BLACK) {
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
        } else if (c == Color.GREEN) {
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
        } else if (c == Color.RED) {
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
        } else if (c == Color.BLUE) {
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
        } else if (c == Color.ORANGE) {
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
        } else if (c == Color.PURPLE) {
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

        return returnImage;
    }

    private void determineScreenSizes() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.getWidth();
        screenHeight = screenSize.getHeight();
        horScale = screenWidth / levelDrawWidth;
        verScale = screenHeight / levelDrawHeight;
    }

}
