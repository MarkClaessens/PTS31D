/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class FXMLGameLobbyController extends BaseController implements Initializable {

    /**
     * the fxml location of FXMLGameLobbyController
     */
    public static final String URL_FXML = "FXMLGameLobby.fxml";
    GameLobby gamelobby;
    boolean gamekanstarten;
    String currentText;
    Lobby lobby;

    private List<String> playernames;
    private transient ObservableList<String> observablePersonen;
    @FXML
    private TextArea TAgegevens;
    @FXML
    private Button BTNstartgame;
    @FXML
    private Button BTNready;
    @FXML
    private Button BTNleavegamelobby;
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

    /**
     * Initializes the controller class.
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
        playernames = new ArrayList();
        for (Player player : gamelobby.getPlayers()) {
            playernames.add(player.getName() + " ready: " + player.getReady());
        }

        this.observablePersonen = FXCollections.observableList(this.playernames);
        LVplayers.setItems(observablePersonen);
    }

    /**
     * *
     * sets the gamelobby
     *
     * @param Gamelobby
     */
    public void setGameLobby(GameLobby Gamelobby) {
        this.gamelobby = Gamelobby;
    }

    /**
     * *
     * sets the lobby
     *
     * @param lobby
     */
    public void setLobby(Lobby lobby) {
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
        gamekanstarten = gamelobby.readyCheck();
        if (gamekanstarten) {
            gamelobby.startGame();
            MainGameFX gameFX = gamelobby.getGame().getFX();
            gameFX.start(Haunted.getStage());

        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("niet iedereen is ready");
            alert.showAndWait();
        }
    }

    /**
     * *
     * change your status to ready/undready depending on it's current status
     *
     * @param event
     */
    @FXML
    private void changeready(MouseEvent event) {
        String totaaltekst = (String) LVplayers.getSelectionModel().getSelectedItem();
        String naam = totaaltekst.substring(0, totaaltekst.indexOf(" "));
        for (Player player : gamelobby.getPlayers()) {
            if (player.getName().equals(naam)) {
                if (player.getReady()) {
                    player.setReady(Boolean.FALSE);
                } else {
                    player.setReady(Boolean.TRUE);
                }
            }

        }
        playernames();
    }

    /**
     * *
     * sends message to the chatbox
     *
     * @param event
     */
    @FXML
    private void sendMessage(MouseEvent event) {
        currentText = TAchatBox.getText();
        TAchatBox.clear();
        String totaaltekst = (String) LVplayers.getSelectionModel().getSelectedItem();
        String naam = totaaltekst.substring(0, totaaltekst.indexOf(" "));

        for (Player player : gamelobby.getPlayers()) {
            if (player.getName().equals(naam)) {
                gamelobby.sendMessage(TFmessage.getText(), player);
            }
        }
        List<Message> messages = gamelobby.getMessages();
        Message message = messages.get(messages.size() - 1);
        TAchatBox.setText(currentText + message.toString() + "\n");
        TAchatBox.setScrollTop(Double.MAX_VALUE);
        TFmessage.clear();
    }

    /**
     * *
     * leave the gamelobby. return to the lobby
     */
    @FXML
    private void leavegamelobby() {
        lobby.getPlayer1().setReady(false);
        lobby.getPlayer2().setReady(false);
        FXMLHauntedController GLC = (FXMLHauntedController) Haunted.getNavigation().load(FXMLHauntedController.URL_FXML);
        GLC.setLobby(lobby);
        GLC.show();
    }

    /**
     * *
     * before the gui will be shown
     */
    @Override
    public void preShowing() {
        super.preShowing();
        TAgegevens.setText("gamelobbyname: " + gamelobby.getName() + "\n" + "maximum aantal spelers: " + gamelobby.getMaxPlayers() + "\n" + "maximum aantal vloeren: " + gamelobby.getFloorAmount());
        playernames();

    }

}
