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
public class FXMLHauntedController implements Initializable {

    Stage stage; 
    Parent root;
    Lobby lobby;
    GameLobby gamelobby;
    private List<Player> players;
    private List<String> playernames;
    private transient ObservableList<String> observablePersonen;
   
    @FXML Button BTNready;    
    @FXML Button BTNstartgame;
    @FXML ListView LVplayers;
    @FXML Label label;    
    @FXML TextField TFchangenameplayer1;    
    @FXML TextField TFchangenameplayer2;    
    @FXML TextField TFroomname;    
    @FXML TextField TFpassword;    
    @FXML Button BTNrename;    
    @FXML Button BTNcreategame;
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
            GameLobby gamelobby = lobby.createGameLobby(TFroomname.getText(), TFpassword.getText());
            Stage newstage = new Stage();
            stage=(Stage) BTNcreategame.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("FXMLGameLobby.fxml"));
            Scene scene = new Scene(root);
            newstage.setScene(scene);
            newstage.show();
            playernames = new ArrayList();
            for(Player ply : gamelobby.getPlayers())
            {
             playernames.add(ply.getName() + " ready:" + ply.getReady());   
            } 
            
            this.observablePersonen = FXCollections.observableList(this.playernames);            
            LVplayers.setItems(observablePersonen);
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
    public void changeready()
    {
       String totaaltekst = (String)LVplayers.getSelectionModel().getSelectedItem();
       String naam = totaaltekst.substring(0, totaaltekst.indexOf(" "));
       for(Player player : players)
       {
           if(player.getName().equals(naam))
           {
            if(player.getReady())
            {
                player.setReady(Boolean.FALSE);
            }
             else
            {
                player.setReady(Boolean.TRUE); 
            }     
           }
            
       }
       
    }
    public void startgame()
    {
        gamelobby.startGame();
    }
    

}
