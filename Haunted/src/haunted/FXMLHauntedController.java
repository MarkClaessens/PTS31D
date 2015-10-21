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
    
    @FXML Label label;    
    @FXML TextField TFchangenameplayer1;    
    @FXML TextField TFchangenameplayer2;    
    @FXML TextField TFroomname;    
    @FXML TextField TFpassword;    
    @FXML Button BTNrename;    
    @FXML Button BTNcreategamelobby;
    @FXML Button BTNsettings;
    @FXML Button BTNexit;  

    @Override
    public void initialize(URL url, ResourceBundle rb) {          
          lobby = new Lobby();   
          TFchangenameplayer1.setText(lobby.getPlayer1().getName());
          TFchangenameplayer2.setText(lobby.getPlayer2().getName());
        
    }
    public void changename()
    {
        lobby.changePlayerName(TFchangenameplayer1.getText(), TFchangenameplayer2.getText());
        TFchangenameplayer1.setText(lobby.getPlayer1().getName());
        TFchangenameplayer2.setText(lobby.getPlayer2().getName());
    }
    public void creategamelobby() throws IOException
    {
        if(!(TFroomname.getText().equals("")))
        {
            gamelobby = lobby.createGameLobby(TFroomname.getText(), TFpassword.getText());            
            FXMLGameLobbyController GMC = (FXMLGameLobbyController)Haunted.getNavigation().load(FXMLGameLobbyController.URL_FXML);
            GMC.setGameLobby(gamelobby);
            GMC.Show();
            
        }
        else
        {
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setContentText("fill in roomname");
           alert.showAndWait();
        }        
    }
    public void exit()
    {
        lobby.exit();
    }
    public void settings()
    {
        //weet nog niet precies hoe dit gedaan moet worden. 
    }     
}
