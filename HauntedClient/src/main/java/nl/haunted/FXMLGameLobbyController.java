/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import nl.haunted.IGameLobby;
import nl.haunted.ILobby;
import nl.haunted.IPlayer;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class FXMLGameLobbyController implements Initializable {

    /**
     * the fxml location of FXMLGameLobbyController
     */
    public static final String URL_FXML = "FXMLGameLobby.fxml";
    IGameLobby gamelobby;
    boolean gamekanstarten;
    String currentText;
    ILobby lobby;

    private List<String> playernames;
    private transient ObservableList<String> observablePersonen;
    @FXML
    private TextArea TAgegevens;
    @FXML
    private ListView LVplayers;
    @FXML
    private Button BTNsendMessage;
    @FXML
    private TextField TFmessage;
    @FXML
    private TextArea TAchatBox;
    @FXML
    AnchorPane paneel;
    @FXML
    ImageView IVexit;
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BackgroundImage myBI = new BackgroundImage(new Image("gamelobby.jpg", 1024, 576, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //then you set to your node
        paneel.setBackground(new Background(myBI));
    }

    /**
     * *
     * sets playernames in listview
     */
    private void playernames() {
        
    }

    /**
     * *
     * sets the gamelobby
     *
     * @param Gamelobby
     */
    public void setGameLobby(IGameLobby Gamelobby) {
        this.gamelobby = Gamelobby;
    }

    /**
     * *
     * sets the lobby
     *
     * @param lobby
     */
    public void setLobby(ILobby lobby) {
        this.lobby = lobby;
    }

    /**
     * *
     * starts the game. This can only happen when everyone is ready
     *
     * @param event
     */
    @FXML
    private void startgame(MouseEvent event) throws IOException, InterruptedException, Exception {
        
    }

    /**
     * *
     * change your status to ready/undready depending on it's current status
     *
     * @param event
     */
    @FXML
    private void changeready(MouseEvent event) {
        
    }

    /**
     * *
     * sends message to the chatbox
     *
     * @param event
     */
    @FXML
    private void sendMessage(MouseEvent event) 
    {
        

    }

    /**
     * *
     * leave the gamelobby. return to the lobby
     */
    @FXML
    private void leavegamelobby() {
        
    }

    /**
     * *
     * before the gui will be shown
     */
    

}