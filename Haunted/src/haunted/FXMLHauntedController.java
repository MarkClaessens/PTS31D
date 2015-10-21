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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    
    @FXML TextField TFchangenameplayer1;    
    @FXML TextField TFchangenameplayer2;    
    @FXML TextField TFroomname;    
    @FXML TextField TFpassword;
    @FXML TextField TFplayers; 
    @FXML TextField TFfloors; 
    @FXML Button BTNrename;    
    @FXML Button BTNcreategamelobby;    
    @FXML Button BTNexit;  
/***
 * initialize the Lobby of the game. first screen.
 * @param url
 * @param rb 
 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
          lobby = new Lobby();   
          setplayernames();        
    }
    /***
     * set the playernames in the textfields
     */
    private void setplayernames()
    {
       TFchangenameplayer1.setText(lobby.getPlayer1().getName());
       TFchangenameplayer2.setText(lobby.getPlayer2().getName()); 
    }
    /***
     * sets the lobby. There will always be one and the same lobby
     * @param lobby 
     */
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }
    /***
     * change the name of both players
     */
    public void changename()
    {
        if(!TFchangenameplayer1.getText().isEmpty() && !TFchangenameplayer2.getText().isEmpty())
        {
           lobby.changePlayerName(TFchangenameplayer1.getText(), TFchangenameplayer2.getText());
           setplayernames(); 
        }
        else if(!TFchangenameplayer1.getText().isEmpty() && TFchangenameplayer2.getText().isEmpty())
        {
           lobby.changePlayerName(TFchangenameplayer1.getText(), lobby.getPlayer2().getName());
           setplayernames();     
        }
        else if(TFchangenameplayer1.getText().isEmpty() && !TFchangenameplayer2.getText().isEmpty())
        {
           lobby.changePlayerName(lobby.getPlayer1().getName(), TFchangenameplayer2.getText());
           setplayernames();    
        }
        else {
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setContentText("voer tekst in");
           alert.showAndWait();
           setplayernames(); 
        }
        
        
    }
    /***
     * creates a gamelobby from which the players can play a game
     * @throws IOException 
     */
    public void creategamelobby() throws IOException
    {
        if(!(TFroomname.getText().equals(""))||!(TFplayers.getText().equals(""))||!(TFfloors.getText().equals("")))
        {
            gamelobby = lobby.createGameLobby(TFroomname.getText(), TFpassword.getText());
            try
            {
             gamelobby.setMaxPlayers(Integer.parseInt(TFplayers.getText()));
             gamelobby.setFloorAmount(Integer.parseInt(TFfloors.getText())); 
             FXMLGameLobbyController GMC = (FXMLGameLobbyController)Haunted.getNavigation().load(FXMLGameLobbyController.URL_FXML);
             GMC.setGameLobby(gamelobby);
             GMC.setLobby(lobby);
             GMC.Show();
            }
            catch(NumberFormatException e)
            {
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setContentText("voer nummer in");
              alert.showAndWait();          
            }
            
            
            
        }
        else
        {
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setContentText("fill in roomname");
           alert.showAndWait();
        }        
    }
    /***
     * close the whole game
     */
    public void exit()
    {
        lobby.exit();
    }
    /***
     * before the gui will be shown
     */
    public void PreShowing() {
        super.PreShowing();
        setplayernames();         
    }
}
