/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
public class FXMLvictoryController extends BaseController implements Initializable {

    public static final String URL_FXML = "FXMLvictory.fxml";

    private String winnaarnaam;
    @FXML
    private Label lblwinner;
    @FXML
    AnchorPane paneel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BackgroundImage myBI = new BackgroundImage(new Image("victoryscreen.jpg", screenSize.getWidth(), screenSize.getHeight(), false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //then you set to your node
        paneel.setBackground(new Background(myBI));        
    }

    public void setWinnaarnaam(String naam) {
        this.winnaarnaam = naam;
        lblwinner.setText(winnaarnaam);
    }

    @Override
    public void PreShowing() {
        super.PreShowing();        
       }

}
