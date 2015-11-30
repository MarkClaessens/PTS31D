/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
        
    public Scene MainGameFX(Gamefeed gf){
        root = new Group();
        scene = new Scene(root);
        determineScreenSizes();
        
        bgLayer = new Canvas(screenWidth, screenHeight);
        bgGc = bgLayer.getGraphicsContext2D();
        root.getChildren().add(bgLayer);
        
        return scene;
    }
    
    private void determineScreenSizes(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.getWidth();
        screenHeight = screenSize.getHeight();
    }
    
}
