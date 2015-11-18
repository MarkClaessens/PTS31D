/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package continuingwithsprites;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author jvdwi
 */
public class ContinuingWithSprites extends Application {
    
    @Override
    public void start(Stage theStage) {
        theStage.setTitle( "Timeline Example" );
 
    Group root = new Group();
    Scene theScene = new Scene( root );
    theStage.setScene( theScene );
 
    Canvas canvas = new Canvas( 512, 512 );
    root.getChildren().add( canvas );
 
    GraphicsContext gc = canvas.getGraphicsContext2D();
 
    Image human = new Image( "human.gif" );
 
    final long startNanoTime = System.nanoTime();
 
    new AnimationTimer()
    {
        public void handle(long currentNanoTime)
        {
            double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
 
            double x = 232 + 128 * Math.cos(t);
            double y = 232 + 128 * Math.sin(t);
 
            // background image clears canvas
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage( human, x, y );
        }
    }.start();
 
    theStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}