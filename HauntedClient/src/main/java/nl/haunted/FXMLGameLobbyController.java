/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
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
public class FXMLGameLobbyController extends UnicastRemoteObject implements Initializable, IFXMLGameLobbyController {

    /**
     * the fxml location of FXMLGameLobbyController
     */
    public static final String URL_FXML = "FXMLGameLobby.fxml";
    IGameLobby gamelobby;
    boolean gamekanstarten;
    String currentText;
    ILobby lobby;
    IPlayer tisplayer;
    ClientController controller;
    Socket msgSoc;
    private Chat chat;
    observermessages om;
    
    private List<IPlayer> players;
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

    public FXMLGameLobbyController() throws RemoteException {
    }

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

        System.out.println("kaas");
        // Set the groupID in ClientController to the groupID of this gamelobby.

    }

    /**
     * *
     * sets playernames in listview
     */
    private void gamesettings() throws RemoteException {
        TAgegevens.setText("naam: " + gamelobby.getName() + "\n" + "maxfloors: " + String.valueOf(gamelobby.getMaxFloors()) + "\n" + "maxplayers: " + String.valueOf(gamelobby.getMaxPlayer()));

    }

    private void playernames() throws RemoteException {
        List<String> namen = new ArrayList<>();
        for (IPlayer player : players) {
            if(gamelobby.getHost().equals(player))
            {
                namen.add("(Host) " + player.getName() + " ready: " + player.getReady());
            }
            else
            {
              namen.add(player.getName() + " ready: " + player.getReady());  
            }
            
        }
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                LVplayers.setItems(FXCollections.observableList(namen));
            }
        });

    }

    /**
     * *
     * sets the gamelobby
     *
     * @param Gamelobby
     * @throws java.io.IOException
     */
    public void setGameLobby(IGameLobby Gamelobby) throws IOException {
        this.gamelobby = Gamelobby;
        try {
            gamelobby.addListener(this, "players");
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLGameLobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        players = new ArrayList<>();
        subscribeToAllPlayers();
        try {
            playernames();
            gamesettings();
            setController();
            controller.setInputController(gamelobby); 
            chat = new Chat(controller.getInputController());  
            om = new observermessages(this);
            controller.setGroupID(gamelobby.getGroupID());
                                
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLGameLobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void settisPlayer(IPlayer player) {
        this.tisplayer = player;
    }

    public void setController() {
        this.controller = HauntedClient.getController();
        try {
            controller.setGroupID(this.gamelobby.getGroupID());
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLGameLobbyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLGameLobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setPlayers() {
        players.clear();
        try {
            for (IPlayer player : gamelobby.getPlayers()) {
                players.add(player);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLGameLobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        boolean startable = true;
        for (IPlayer player : players) {
            if (!player.getReady()) {
                startable = false;
            }
        }
        if (startable && players.size() > 2) {
               gamelobby.startGame();
               Thread.sleep(3000);
        }
    }

    /**
     * *
     * change your status to ready/undready depending on it's current status
     *
     * @param event
     */
    @FXML
    private void changeready(MouseEvent event) throws RemoteException {
        tisplayer.toggleReady();
    }

    /**
     * *
     * sends message to the chatbox
     *
     * @param event
     */
    @FXML
    private void sendMessage(MouseEvent event) throws IOException {

        if (!TFmessage.getText().isEmpty()) {
            Message message = new Message(TFmessage.getText(), tisplayer, true);
            chat.sendMessage(message);
            TFmessage.clear();

        } else {
            TAchatBox.setText(currentText);
            TAchatBox.setScrollTop(Double.MAX_VALUE);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("geen message ingevoerd");
            alert.showAndWait();
        }
    }

    /**
     * *
     * leave the gamelobby. return to the lobby
     */
    @FXML
    private void leavegamelobby() throws RemoteException {
        gamelobby.removePlayer(tisplayer);
        controller.setInGL(false);
        if (gamelobby.getPlayers().isEmpty()) {
            lobby.removeGL(gamelobby);
        }
        if (tisplayer.getReady()) {
            tisplayer.toggleReady();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLHaunted.fxml"));
        try {
            Node root = fxmlLoader.load();
            HauntedClient.getStage().getScene().setRoot((Parent) root);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        String propertyName = evt.getPropertyName();
        if (propertyName.equals("players")) {
            if(gamelobby.getIngame())
            {
                Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
               gamefeed gameFeed;
                try {
                    gameFeed = new gamefeed(controller.getInputController().getSrvSocket());
                    MainGameFXScene MGS = new MainGameFXScene();
                    Stage stage = HauntedClient.getStage();
                    Scene scene = MGS.mainGameFX(gameFeed, chat, tisplayer);
                    
                             HauntedClient.getStage().setScene(scene);
                            HauntedClient.getStage().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                            //HauntedClient.getStage().setFullScreen(true);
                            HauntedClient.getStage().show(); 
                        }
                catch (     IOException | ClassNotFoundException | InterruptedException ex) {
                    Logger.getLogger(FXMLGameLobbyController.class.getName()).log(Level.SEVERE, null, ex);
                }   
               }
              });
            }
                       
            else
            {
            List<IPlayer> INplayers = (List<IPlayer>) evt.getNewValue();
            if (INplayers.size() > players.size()) {
                subscribeToAllPlayers();
                setPlayers();
                playernames();
            } else {
                for (IPlayer INplayer : INplayers) {
                    boolean found = false;
                    for (IPlayer EXplayer : players) {
                        if (INplayer == EXplayer) {
                            found = true;
                        }
                    }
                    if (!found) {
                        INplayer.removeListener(this, propertyName);
                        setPlayers();
                        playernames();
                    }
                }
            }
           }

        } else if (propertyName.equals("ready")) {

            playernames();
        }
    }

    /**
     * *
     * before the gui will be shown
     */
    public void subscribeToAllPlayers() {
        // When joining the gamelobby subscribe to every player's ready status.       
        try {
            players = gamelobby.getPlayers();
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLGameLobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (players != null) {
            for (IPlayer player : players) {
                try {
                    player.addListener(this, "ready");
                } catch (RemoteException ex) {
                    Logger.getLogger(FXMLGameLobbyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void getmessages() {
        TAchatBox.appendText(chat.getMessages().get(chat.getMessages().size()).toString());
        TAchatBox.appendText("\n");
    }

    public Chat getchat() {
        return chat;
    }

    public class observermessages implements Observer {

        FXMLGameLobbyController GLC;

        public observermessages(FXMLGameLobbyController GLC) {
            this.GLC = GLC;
            GLC.getchat().addObserver(this);
        }

        @Override
        public void update(Observable o, Object arg) {
            GLC.getmessages();
        }

    }

}
