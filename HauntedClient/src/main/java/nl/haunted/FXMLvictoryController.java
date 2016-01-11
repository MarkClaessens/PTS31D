package nl.haunted;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class FXMLvictoryController implements Initializable {

    @FXML
    AnchorPane paneel;

    @FXML
    Label winaarslabel;
    
    @FXML
    Button btncontinue;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BackgroundImage myBI = new BackgroundImage(new Image("victoryscreen.jpg", 1024, 576, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //then you set to your node
        paneel.setBackground(new Background(myBI));
    }
    
    public void setwinnaar(String naam)
    {
       winaarslabel.setText(naam);
    }
    
    @FXML
    public void continuetolobby()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLGameLobby.fxml"));
            Node root = fxmlLoader.load();            
            HauntedClient.getStage().getScene().setRoot((Parent) root);
            HauntedClient.getStage().show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLvictoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
