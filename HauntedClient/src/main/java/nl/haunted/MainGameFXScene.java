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

    private Canvas bgLayer;
    private GraphicsContext bgGc;

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

        bgGc.drawImage(bgImage, 0, 0, levelDrawWidth * horScale, levelDrawHeight * verScale);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                List<Object> obj = new ArrayList();
                root.getChildren().stream().filter((o) -> (o instanceof Polygon)).forEach((o) -> {
                    obj.add(o);
                });
                root.getChildren().removeAll(obj);
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
                    break;
                case Human:
                    break;
                case Ghost:
                    break;
            }
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
