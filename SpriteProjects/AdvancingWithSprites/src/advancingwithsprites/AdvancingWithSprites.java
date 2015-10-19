/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advancingwithsprites;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
public class AdvancingWithSprites extends Application {

    double x = 0;
    double y = 131;
    int movespeed = 10;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Advancing with sprites");
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        ArrayList<String> input = new ArrayList<String>();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();

                // only add once... prevent duplicates
                if (!input.contains(code)) {
                    input.add(code);
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                input.remove(code);
            }
        });

        Canvas canvas = new Canvas(500, 500);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image human = new Image("human.gif");
        Image wall = new Image("wall.png");

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                if (input.contains("LEFT")) {
                    if (x - movespeed >= 0) {
                        x -= movespeed;
                    } else {
                        x = 0;
                    }
                }
                if (input.contains("RIGHT")) {
                    if (x + movespeed <= canvas.getWidth()-112) {
                        x += movespeed;
                    } else {
                        x = canvas.getWidth() - movespeed - 112;
                    }
                }
                if (input.contains("UP")) {
                    if (y - movespeed >= 0) {
                        y -= movespeed;
                    } else {
                        y = 0;
                    }
                }
                if (input.contains("DOWN")) {
                    if (y + movespeed <= canvas.getHeight()-133) {
                        y += movespeed;
                    } else {
                        y = canvas.getHeight() - movespeed - 133;
                    }
                }

                // background image clears canvas
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.drawImage(human, x, y);
                gc.drawImage(wall, 0, 0);
                gc.drawImage(wall, 0, 264);
            }
        }.start();

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
