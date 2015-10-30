/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;


import java.io.IOException;
import java.net.MalformedURLException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Kevin Stoelers, Joris van de Wijgert, Cyril Brugman, Mark Claesens,
 * Mike Evers
 *
 */
public class FXMLHauntedController extends BaseController implements Initializable {

    public static final String URL_FXML = "FXMLHaunted.fxml";

    Lobby lobby;
    GameLobby gamelobby;

    @FXML
    TextField TFchangenameplayer1;
    @FXML
    TextField TFchangenameplayer2;
    @FXML
    TextField TFroomname;
    @FXML
    TextField TFpassword;
    @FXML
    TextField TFplayers;
    @FXML
    TextField TFfloors;
    @FXML
    Button BTNrename;
    @FXML
    Button BTNcreategamelobby;
    @FXML
    Button BTNexit;
    @FXML
    AnchorPane paneel;
    /**
     * *
     * initialize the Lobby of the game. first screen.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lobby = new Lobby();
        BackgroundImage myBI= new BackgroundImage(new Image("lobbypic.jpg",1024,576,false,true),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
         //then you set to your node
        paneel.setBackground(new Background(myBI));
        setplayernames();
    }

    /**
     * *
     * set the playernames in the textfields
     */
    private void setplayernames() {
        TFchangenameplayer1.setText(lobby.getPlayer1().getName());
        TFchangenameplayer2.setText(lobby.getPlayer2().getName());
    }

    /**
     * *
     * sets the lobby. There will always be one and the same lobby
     *
     * @param lobby
     */
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;        
    }

    /**
     * *
     * change the name of both players
     */
    public void changename() {
        if (!TFchangenameplayer1.getText().isEmpty() && !TFchangenameplayer2.getText().isEmpty()) {
            lobby.changePlayerName(TFchangenameplayer1.getText(), TFchangenameplayer2.getText());
            setplayernames();
        } else if (!TFchangenameplayer1.getText().isEmpty() && TFchangenameplayer2.getText().isEmpty()) {
            lobby.changePlayerName(TFchangenameplayer1.getText(), lobby.getPlayer2().getName());
            setplayernames();
        } else if (TFchangenameplayer1.getText().isEmpty() && !TFchangenameplayer2.getText().isEmpty()) {
            lobby.changePlayerName(lobby.getPlayer1().getName(), TFchangenameplayer2.getText());
            setplayernames();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("onjuiste tekst");
            alert.setContentText("voer tekst in bij beide spelers.");
            alert.showAndWait();
            setplayernames();
        }

    }

    /**
     * *
     * creates a gamelobby from which the players can play a game
     *
     * @throws IOException
     */
    public void creategamelobby() throws IOException {
        if (!(TFroomname.getText().equals("")) || !(TFplayers.getText().equals("")) || !(TFfloors.getText().equals(""))) {
            gamelobby = lobby.createGameLobby(TFroomname.getText(), TFpassword.getText());            
            try {
                if(Integer.parseInt(TFplayers.getText()) < 7 && Integer.parseInt(TFfloors.getText()) < 11)
                {
                   gamelobby.setMaxPlayers(Integer.parseInt(TFplayers.getText()));
                   gamelobby.setFloorAmount(Integer.parseInt(TFfloors.getText()));                
                   FXMLGameLobbyController GMC = (FXMLGameLobbyController) Haunted.getNavigation().load(FXMLGameLobbyController.URL_FXML);
                   GMC.setGameLobby(gamelobby);
                   GMC.setLobby(lobby);
                   GMC.Show(); 
                }
                else
                {
                   Alert alert = new Alert(AlertType.INFORMATION);
                   alert.setHeaderText("maximum overschreden");
                   alert.setContentText("het maximum van een van de 2 getallen is overschreden. Het maximum voor spelers is 6 en voor floors 10");
                   alert.showAndWait(); 
                }
                
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("geen getal");
                alert.setContentText("zorg ervoor dat bij maximum spelers en floor amount er een getal staat");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("invoer velden leeg");
            alert.setContentText("voer velden van naam, maximum spelers en floor amount in!");
            alert.showAndWait();
        }
    }

    /**
     * *
     * close the whole game
     */
    public void exit() {
        lobby.exit();
    }

    /**
     * *
     * before the gui will be shown
     */
    @Override
    public void PreShowing() {
        super.PreShowing();
        setplayernames();
    }
}
