/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trialtext;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author jvdwi
 */
public class TrialText extends Application {

    private int rotation = 0;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        gc.setFont(new Font("Times New Roman", 60.0));
        gc.setStroke(Color.BLUE);
        gc.setFill(Color.BLUE);
        Color col = Color.GREEN;

        Image image = new Image("Door.png");

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, 300, 250);
                //gc.strokeText(getStringFromColor(col), 20, 60);
                gc.fillText(getStringFromColor(col).toUpperCase(), 20, 60);
                drawRotatedImage(gc, image, rotation, 0, 0, 300/image.getWidth(), 250/image.getHeight());
                rotation = rotation + 5;
            }
        }.start();
        primaryStage.show();
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

}
