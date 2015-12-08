package nl.haunted;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class FXMLtussenschermController implements Initializable {

    @FXML
    AnchorPane paneel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         BackgroundImage myBI = new BackgroundImage(new Image("CreepyStairs.jpg", 1024, 576, false, true),
         BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
         BackgroundSize.DEFAULT);
         //then you set to your node
         paneel.setBackground(new Background(myBI));
    }    
    
}
