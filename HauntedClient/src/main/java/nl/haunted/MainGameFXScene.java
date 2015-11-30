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

/**
 *
 * @author jvdwi
 */
public class MainGameFXScene {

    private double screenWidth, screenHeight;

    private Scene scene;
    private Group root;

    private Canvas bgLayer;
    private GraphicsContext bgGc;
    
    Image image;

    private Gamefeed gf;

    public Scene MainGameFX(Gamefeed gf) {
        this.gf = gf;
        root = new Group();
        scene = new Scene(root);
        determineScreenSizes();

        bgLayer = new Canvas(screenWidth, screenHeight);
        bgGc = bgLayer.getGraphicsContext2D();
        root.getChildren().add(bgLayer);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                List<Object> obj = new ArrayList();
                root.getChildren().stream().filter((o) -> (o instanceof Polygon)).forEach((o) -> {
                    obj.add(o);
                });
                root.getChildren().removeAll(obj);
            }
        }.start();
        ImageView iv = new ImageView(image);
        
        iv.setRotate(90);
        
        return scene;
    }

    private void determineScreenSizes() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.getWidth();
        screenHeight = screenSize.getHeight();
    }

}
