/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class FXMLnextroundController extends BaseController implements Initializable {
    
public static final String URL_FXML = "FXMLGameLobby.fxml";
private MainGameFX gameFX;

    @FXML
    private Button BTNnextRound;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setGamefx(MainGameFX GameFX) {
        this.gameFX = GameFX;
    }
    
    @FXML
    private void nextround() throws Exception{
        gameFX.setShowEmpty(false);
        gameFX.start(Haunted.getStage());
    }
}
