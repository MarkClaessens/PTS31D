/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author jvdwi
 */
public class MainGameFX extends Application {

    Human human;
    Ghost ghost;
    List<Obstacle> obstacles;

    public void start(Stage stage) throws Exception {
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
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                drawElements(gc);
            }
        }.start();
        stage.show();
        stage.setFullScreen(true);
    }

    //TODO: handling 3 versions when moving
    public void drawElements(GraphicsContext gc) {
        for (String img : human.getSprites()) {
            Image humanImg = new Image(img);
            gc.drawImage(humanImg, human.getPosition().getX(), human.getPosition().getY());
        }

        for (String img : ghost.getSprites()) {
            Image ghostImg = new Image(img);
            gc.drawImage(ghostImg, ghost.getPosition().getX(), ghost.getPosition().getY());
        }

        for (Obstacle o : obstacles) {
            Image obstImg = new Image(o.getSprite());
            gc.drawImage(obstImg, o.getPosition().getX(), o.getPosition().getY());
        }
    }

    public void setItems(Game game) {
        for (Player p : game.getPlayers()) {
            if (p.getCharacter().getClass().equals(human.getClass())) {
                this.human = (Human) p.getCharacter();
            } else {
                this.ghost = (Ghost) p.getCharacter();
            }
        }
        Level lvl = game.getCurrentLevel();
        this.obstacles = lvl.getObstacles();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
