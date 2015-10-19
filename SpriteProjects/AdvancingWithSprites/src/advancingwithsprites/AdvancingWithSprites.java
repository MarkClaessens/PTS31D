/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advancingwithsprites;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
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
public class AdvancingWithSprites extends Application {

    Human human;
    final long startNanoTime = System.nanoTime();
    int x = 0;
    int time;

    public void start(Stage stage) throws Exception {
        String[] sprites = new String[]{"human1.gif", "human2.gif", "human3.gif"};
        Human hum = new Human(sprites);
        setItems(hum);
        stage.setTitle("the game");
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        
        ArrayList<String> input = new ArrayList<String>();

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if (!input.contains(code)) {
                            input.add(code);
                        }
                    }
                });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });
        
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (input.contains("RIGHT")) {
                    human.setIsMoving(true);
                }  
                else{
                    human.setIsMoving(false);
                }
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawElements(gc);
                time = Math.round((currentNanoTime - startNanoTime) / 200000000);
            }
        }.start();
        stage.show();
        stage.setFullScreen(true);
    }

    public void drawElements(GraphicsContext gc) {
        if (human.getIsMoving()) {
            x++;
            Image drawImage = null;
            if (time % 3 == 0) {
                drawImage = new Image(human.getSprites()[0]);
            } else if (time % 3 == 1) {
                drawImage = new Image(human.getSprites()[1]);
            } else if (time % 3 == 2) {
                drawImage = new Image(human.getSprites()[2]);
            }
            if (drawImage != null) {
                gc.drawImage(drawImage, x, 0);
            }
        }
        else{
            gc.drawImage(new Image(human.getSprites()[0]), x, 0);
        }
    }

    public void setItems(Human human) {
        this.human = human;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
