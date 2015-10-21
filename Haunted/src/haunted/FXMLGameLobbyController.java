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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class FXMLGameLobbyController extends BaseController implements Initializable {
    
    public static final String URL_FXML = "FXMLGameLobby.fxml";
    GameLobby gamelobby;
    boolean gamekanstarten;
    String currentText;
    Lobby lobby;
    
    private List<String> playernames;
    private transient ObservableList<String> observablePersonen;
    @FXML private TextArea TAgegevens;
    @FXML private Button BTNstartgame;
    @FXML private Button BTNready;
    @FXML private Button BTNleavegamelobby;
    @FXML private ListView LVplayers;
    @FXML private Button BTNsendMessage;
    @FXML private TextField TFmessage;
    @FXML private TextArea TAchatBox;

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
             playernames.add(player.getName() + " ready: " + player.getReady());   
            } 
            
            this.observablePersonen = FXCollections.observableList(this.playernames);            
            LVplayers.setItems(observablePersonen); 
    }
    public void setGameLobby(GameLobby Gamelobby) {
        this.gamelobby = Gamelobby;
    }
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    @FXML
    private void startgame(MouseEvent event) {
         gamekanstarten = gamelobby.startGame();
         if(gamekanstarten)
         {
             
         }
         else
         {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("niet iedereen is ready");
            alert.showAndWait();
         }
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
    
    @FXML
    private void sendMessage(MouseEvent event)
    {
     currentText = TAchatBox.getText();
     TAchatBox.clear();
     gamelobby.sendMessage(TFmessage.getText());
     List<Message> messages = gamelobby.getMessages();     
     Message message = messages.get(messages.size() - 1);     
     TAchatBox.setText(currentText + message.toString() + "\n");
     TAchatBox.setScrollTop(Double.MAX_VALUE);
     TFmessage.clear();
    }
    @FXML
    private void leavegamelobby()
    {
            lobby.getPlayer1().setReady(false);
            lobby.getPlayer2().setReady(false);
            FXMLHauntedController GLC = (FXMLHauntedController)Haunted.getNavigation().load(FXMLHauntedController.URL_FXML);            
            GLC.setLobby(lobby);
            GLC.Show(); 
    }
    
    @Override
    public void PreShowing() {
        super.PreShowing();
        TAgegevens.setText("gamelobbyname: " + gamelobby.getName() + "\n" + "maximum aantal spelers: " + gamelobby.getMaxPlayers() + "\n" + "maximum aantal vloeren: " + gamelobby.getFloorAmount());
        playernames();
        
    }
    
}
