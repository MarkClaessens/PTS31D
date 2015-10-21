/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class FXMLGameLobbyController extends BaseController implements Initializable {
    
    public static final String URL_FXML = "FXMLGameLobby.fxml";
    GameLobby gamelobby;
    
    private List<String> playernames;
    private transient ObservableList<String> observablePersonen;
    
    @FXML private Button BTNstartgame;
    @FXML private Button BTNready;
    @FXML private ListView LVplayers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {            
            
    }
    private void playernames()
    {
            playernames = new ArrayList();
            for(Player player : gamelobby.getPlayers())
            {
             playernames.add(player.getName() + " ready:" + player.getReady());   
            } 
            
            this.observablePersonen = FXCollections.observableList(this.playernames);            
            LVplayers.setItems(observablePersonen); 
    }
    public void setGameLobby(GameLobby Gamelobby) {
        this.gamelobby = Gamelobby;
    }

    @FXML
    private void startgame(MouseEvent event) {
        gamelobby.startGame();
    }

    @FXML
    private void changeready(MouseEvent event) 
    {
       String totaaltekst = (String)LVplayers.getSelectionModel().getSelectedItem();
       String naam = totaaltekst.substring(0, totaaltekst.indexOf(" "));
       for(Player player : gamelobby.getPlayers())
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
       playernames();
    }
    
    @Override
    public void PreShowing() {
        super.PreShowing();
        playernames();
        
    }
    
}
