/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotationtest;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author jvdwi
 */
public class Rotationtest extends Application {

    private int rotation = 0;
    private double screenWidth, screenHeight;
    private double horScale, verScale;
    Timer timer;

    @Override
    public void start(Stage primaryStage) {
        this.determineScreenSizes();
        Canvas canvas = new Canvas(1920, 1080);
        Canvas canvas2 = new Canvas((102), (102));
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        root.getChildren().add(canvas2);
        Color col = Color.GREEN;
        Image image = new Image("img.png");
        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        AnimationTimer at = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, 1920, 1080);
                drawRotatedImage(gc, image, rotation, 500, 500, horScale, verScale);
                gc2.clearRect(0, 0, 102, 102);
                drawRotatedCanvas(gc2, image, rotation, 800, 500);

            }
        };
        at.start();
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                rotation = rotation + 90;
                if (rotation == 360) {
                    rotation = 0;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
        primaryStage.show();
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy, double scaleX, double scaleY) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, (tlpx + (image.getWidth() / 2)), (tlpy + (image.getHeight() / 2)));
        if (angle == 0 || angle == 180) {
            gc.drawImage(image, tlpx, tlpy, image.getWidth() * scaleX, image.getHeight() * scaleY);
        } else {
            gc.drawImage(image, tlpx, tlpy, image.getWidth() * scaleX, image.getHeight() * scaleY);
        }
        gc.restore(); // back to original state (before rotation)
    }

    private void drawRotatedCanvas(GraphicsContext gc, Image image, double angle, double x, double y) {
        gc.save();
        rotate(gc, angle, 51, 51);

        gc.moveTo(x, y);
        if(angle == 0 || angle == 180){
        gc.drawImage(image, 1, 1, image.getWidth()*horScale, image.getHeight()*verScale);
        } else {
            gc.drawImage(image, 1, 1, image.getWidth()*verScale, image.getHeight()*horScale);
        }
        gc.restore();

    }

    private String getStringFromColor(Color c) {
        String name = "";
        if (c == Color.RED) {
            return "red";
        } else if (c == Color.GREEN) {
            return "Oploo";
        } else if (c == Color.BLUE) {
            return "blue";
        } else {
            return "red";
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void determineScreenSizes() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.getWidth();
        screenHeight = screenSize.getHeight();
        horScale = (screenWidth / 1700) - ((screenWidth / 1700) % 0.1);
        verScale = (screenHeight / 1200) - ((screenWidth / 1700) % 0.1);
        double temphorscale = (1366.0 / 1700) - ((1366.0 / 1700) % 0.1);
        double tempverscale = (768.0 / 1200) - ((768.0 / 1700) % 0.1);
    }

}
