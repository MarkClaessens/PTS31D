package nl.haunted;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
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
public class FXMLtussenschermController implements Initializable {

    private int run = 0;
    private gamefeed gf;
    private IPlayer player;
    private Chat chat;
    private Scene scene;
    
    @FXML
    AnchorPane paneel;

    @FXML
    Label tellabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BackgroundImage myBI = new BackgroundImage(new Image("CreepyStairs.jpg", 1024, 576, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //then you set to your node
        paneel.setBackground(new Background(myBI));
    }

    public void setgf(gamefeed gf) {
        this.gf = gf;
    }

    public void setplayer(IPlayer player) {
        this.player = player;
    }

    public void setchat(Chat chat) {
        this.chat = chat;
    }

    public void startTimer() {
        Timer timer = new Timer();
        Timer timer2 = new Timer();
        TimerTask task2 = new TimerTask() {

            @Override
            public void run() 
            {                
                try {
                    MainGameFXScene fx = new MainGameFXScene();
                    scene = fx.mainGameFX(gf, chat, player);
                    timer2.cancel();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLtussenschermController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FXMLtussenschermController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLtussenschermController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };  
        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        tellabel.setText(String.valueOf(10 - run));
                        if (run > 9) {
                            timer.cancel();
                           
                            Stage stage = HauntedClient.getStage();
                            HauntedClient.getStage().setScene(scene);
                            HauntedClient.getStage().setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.ESCAPE, KeyCombination.ALT_DOWN));
                            HauntedClient.getStage().setFullScreen(true);
                            HauntedClient.getStage().show();
                            timer.cancel();
                        }
                    }
                });
                run++;
            }

        };
        
        
        try {
            timer.scheduleAtFixedRate(task, 0, 1000);
            Thread.sleep(2000);
            timer2.scheduleAtFixedRate(task2, 0, 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLtussenschermController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
