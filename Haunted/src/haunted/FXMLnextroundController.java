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
import javafx.scene.control.Button;
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
public class FXMLnextroundController extends BaseController implements Initializable {

    public static final String URL_FXML = "FXMLnextround.fxml";
    private Game game;
    private Stage stage;

    @FXML
    private Button BTNnextRound;
    @FXML
    AnchorPane paneel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                BackgroundImage myBI = new BackgroundImage(new Image("CreepyStairs.jpg", screenSize.getWidth(), screenSize.getHeight(), false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //then you set to your node
        paneel.setBackground(new Background(myBI));   
    }

    public void setGame(Game Game) {
        this.game = Game;
    }
    public void setStage(Stage Stage) {
        this.stage = Stage;
    }

    @FXML
    private void nextround() throws Exception {
            game.startRound();
            MainGameFX gameFX = new MainGameFX();
            game.setgameFX(gameFX);
            gameFX.start(stage);
    }
}
