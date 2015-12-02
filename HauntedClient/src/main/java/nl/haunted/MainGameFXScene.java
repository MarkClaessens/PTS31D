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

    //background
    private Canvas bgLayer;
    private GraphicsContext bgGc;

    //foreground
    private Canvas keyDoorLayer, humanLayer, ghostLayer;
    private GraphicsContext keyDoorGc, humanGc, ghostGc;

    Image bgImage;

    List<ImageProps> imgProps;

//    Image image;
    private gamefeed gf;

    public Scene MainGameFX(gamefeed gf) {
        imgProps = new ArrayList();
        this.gf = gf;
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
        for (Entity e : gf.gameInfo.getEntities()) {
            switch (e.getType()) {
                case Door:
                    Image[] doorSprites = new Image[]{new Image("door.png")};
                    imgProps.add(new ImageProps(doorSprites, e));
                    break;
                case Key:
                    Image[] keySprites = new Image[]{new Image("key.png")};
                    imgProps.add(new ImageProps(keySprites, e));
                    break;
                case Human:
                    String humanImgText = "human" + getStringFromColor(e.getColor());
                    Image[] humanSprites = new Image[]{new Image(humanImgText + "1.png"), new Image(humanImgText + "2.png"), new Image(humanImgText + "3.png")};
                    imgProps.add(new ImageProps(humanSprites, e));
                    break;
                case Ghost:
                    String ghostImgText = "ghost" + getStringFromColor(e.getColor());
                    Image[] ghostSprites = new Image[]{new Image(ghostImgText + "1.png"), new Image(ghostImgText + "2.png"), new Image(ghostImgText + "3.png"), new Image("wall.png")};
                    imgProps.add(new ImageProps(ghostSprites, e));
                    break;
            }
        }
    }

    private void drawImages() {
        for (ImageProps imgp : imgProps) {
            switch (imgp.getEntity().getType()) {
                case Door:
                    drawRotatedImage(keyDoorGc, imgp.getImage(0), getAngle(imgp.getEntity().getDirection()), imgp.getEntity().getPosition().getX(), imgp.getEntity().getPosition().getY(), horScale, verScale);
                    break;
                case Key:
                    //todo : draw key image
                    drawRotatedImage(keyDoorGc, imgp.getImage(0), getAngle(imgp.getEntity().getDirection()), imgp.getEntity().getPosition().getX(), imgp.getEntity().getPosition().getY(), horScale, verScale);
                    break;
                case Human:
                    //todo : draw animated human images
                    break;
                case Ghost:
                    //todo : draw animated ghost images
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

    private String getStringFromColor(Color c) {
        String name = "";
        if (c == Color.WHITE) {
            return "white";
        } else if (c == Color.BLACK) {
            return "black";
        } else if (c == Color.GREEN) {
            return "green";
        } else if (c == Color.RED) {
            return "red";
        } else if (c == Color.BLUE) {
            return "blue";
        } else if (c == Color.ORANGE) {
            return "orange";
        } else if (c == Color.PURPLE) {
            return "purple";
        } else {
            return "trans";
        }
    }

    private void determineScreenSizes() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.getWidth();
        screenHeight = screenSize.getHeight();
        horScale = screenWidth / levelDrawWidth;
        verScale = screenHeight / levelDrawHeight;
    }

}
