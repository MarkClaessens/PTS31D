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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
         
         Timer timer = new Timer();
         TimerTask task = new TimerTask() {
            @Override
            public void run() {
                    tellabel.setText(String.valueOf(10 - run));
                    run++;                    
            }
            
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
        while(run < 10);
        timer.cancel();
        MainGameFXScene fx = new MainGameFXScene();
        try {
            
            Stage stage = HauntedClient.getStage();
            Scene scene = fx.mainGameFX(gf, chat, player);
            HauntedClient.getStage().setScene(scene);
            HauntedClient.getStage().setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.ESCAPE, KeyCombination.ALT_DOWN));
            HauntedClient.getStage().setFullScreen(true);
            HauntedClient.getStage().show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLtussenschermController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLtussenschermController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLtussenschermController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
    
    public void setgf(gamefeed gf)
    {
        this.gf = gf;
    }
    public void setplayer(IPlayer player)
    {
        this.player = player;
    }
    public void setchat(Chat chat)
    {
        this.chat = chat;
    }

}