/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Kevin Stoelers, Joris van de Wijgert, Cyril Brugman, Mark Claesens,
 * Mike Evers
 */
public class Haunted extends Application {

    private static Navigation navigation;
    private static Stage currentStage;

    public static Navigation getNavigation() {
        return navigation;
    }
    
    public static Stage getStage(){
        return currentStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.currentStage = primaryStage;
        navigation = new Navigation(primaryStage);

        primaryStage.setTitle("Haunted");
        primaryStage.show();

        //navigate to first view
        Haunted.getNavigation().load(FXMLHauntedController.URL_FXML).Show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
