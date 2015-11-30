/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import nl.haunted.IGameLobby;
import nl.haunted.ILobby;

/**
 *
 * @author Kevin Stoelers, Joris van de Wijgert, Cyril Brugman, Mark Claesens,
 * Mike Evers
 *
 */
public class FXMLHauntedController implements Initializable {

    /**
     * the fxml location for FXMLHauntedController
     */
    public static final String URL_FXML = "FXMLHaunted.fxml";

    ILobby lobby;
    IGameLobby gamelobby;

    @FXML
    TextField TFchangenameplayer1;
    @FXML
    ListView LVgamelobbys;    
    @FXML
    TextField TFroomname;
    @FXML
    TextField TFpassword;
    @FXML
    TextField TFplayers;
    @FXML
    TextField TFfloors;
    @FXML
    AnchorPane paneel;
    @FXML
    ImageView IVexit;
    @FXML
    ImageView IVRename;
    @FXML
    ImageView IVCreategamelobby;

    /**
     * *
     * initialize the Lobby of the game. first screen.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BackgroundImage myBI = new BackgroundImage(new Image("lobbypic.jpg", 1024, 576, false, true),
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
     //   TFchangenameplayer1.setText(lobby.getPlayer().getName());        
    }

    /**
     * *
     * sets the lobby. There will always be one and the same lobby
     *
     * @param lobby
     */
    public void setLobby(ILobby lobby) {
        this.lobby = lobby;
    }

    /**
     * *
     * change the name of both players
     */
    public void changename() {
        
        if (!TFchangenameplayer1.getText().isEmpty()) 
        {     
             
        }
    }

    /**
     * *
     * creates a gamelobby from which the players can play a game
     *
     * @throws IOException
     */
    public void creategamelobby() throws IOException {
        
    }

    /**
     * *
     * close the whole game
     */
    public void exit() {
        try {
            lobby.exit();
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLHauntedController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *
     * before the gui will be shown
     */
    
}
