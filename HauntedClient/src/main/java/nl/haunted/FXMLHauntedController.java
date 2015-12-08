/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import fontys.observer.RemotePropertyListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import nl.haunted.ClientController;
import nl.haunted.HauntedClient;
import nl.haunted.IGameLobby;
import nl.haunted.ILobby;
import nl.haunted.IPlayer;

/**
 *
 * @author Joris van de Wijgert, Cyril Brugman, Mark Claesens, Mike Evers
 *
 */
public class FXMLHauntedController extends TimerTask implements Initializable {

    /**
     * the fxml location for FXMLHauntedController
     */
    public static final String URL_FXML = "FXMLHaunted.fxml";

    ILobby lobby;
    ClientController controller;
    IPlayer tisplayer;
    List<IGameLobby> gamelobbys;
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
        setController();
        settisPlayer();
        setLobby();

        try {
            setgamelobbys();
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLHauntedController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* BackgroundImage myBI = new BackgroundImage(new Image("lobbypic.jpg", 1024, 576, false, true),
         BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
         BackgroundSize.DEFAULT);
         //then you set to your node
         paneel.setBackground(new Background(myBI));*/
        try {
            setplayername();
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLHauntedController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TimerTask timerTask = this;
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 2000);

    }

    /**
     * *
     * set the playernames in the textfields
     */
    private void setplayername() throws RemoteException {
        TFchangenameplayer1.setText(tisplayer.getName());
    }

    /**
     * *
     * sets the lobby. There will always be one and the same lobby
     *
     * @param lobby
     */
    public void setLobby() {
        this.lobby = controller.getLobby();
    }

    public void settisPlayer() {
        this.tisplayer = controller.getPlayer();
    }

    public void setController() {
        this.controller = HauntedClient.getController();
    }

    /**
     * *
     * change the name of both players
     */
    public void changename() throws RemoteException {

        if (!TFchangenameplayer1.getText().isEmpty()) {
            tisplayer.setName(TFchangenameplayer1.getText());
            setplayername();
            TFroomname.setText(tisplayer.getName());
        }
    }

    /**
     * *
     * creates a gamelobby from which the players can play a game
     *
     * @throws IOException
     */
    public void creategamelobby() throws IOException {
        if ((!(TFroomname.getText().equals("")) && !(TFplayers.getText().equals("")) && !(TFfloors.getText().equals("")))) {
            try {
                if (Integer.parseInt(TFplayers.getText()) > 2 && Integer.parseInt(TFplayers.getText()) < 8 && Integer.parseInt(TFfloors.getText()) < 11) {
                    lobby.createGameLobby(TFroomname.getText(), TFpassword.getText(), tisplayer, Integer.parseInt(TFfloors.getText()), Integer.parseInt(TFplayers.getText()));
                } else {
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
        try {
            lobby.exit(controller.getPlayer());
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLHauntedController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *
     * before the gui will be shown
     */
    public void setgamelobbys() throws RemoteException {
        setLobby();
        List<String> namen = new ArrayList<>();
        for (IGameLobby GL : lobby.getGameLobbys()) {
            namen.add("naam: " + GL.getName() + " players: " + GL.getPlayers().size() + "/" + GL.getMaxPlayer() + " maxFloors: " + GL.getMaxFloors());
        }
        LVgamelobbys.setItems(FXCollections.observableList(namen));
    }

    public void addPlayerGL() throws RemoteException {
        String totaaltekst = (String) LVgamelobbys.getSelectionModel().getSelectedItem();
        String naam = totaaltekst.substring(6, totaaltekst.indexOf(" players:"));
        for (IGameLobby GL : lobby.getGameLobbys()) {
            System.out.println(GL.getName());
            if (GL.getName().equals(naam)) {
                GL.addPlayer(tisplayer);
                lobby.informlobbys();
            }

        }

    }

    @Override
    public void run() {
        
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    try {
                        setgamelobbys();
                    } catch (RemoteException ex) {
                        Logger.getLogger(FXMLHauntedController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });            
       
    }

}
