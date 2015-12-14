/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mike Evers + ..
 */
public class HauntedClient extends Application {

    private static ClientController controller;
    private static Stage primaryStage;
    private static FXMLHauntedController HC;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Get ip address of server
        //Scanner input = new Scanner(System.in);
        //System.out.print("Client: Enter IP address of server: ");
        //String ipAddress = input.nextLine();
        String ipAddress = "10.1.3.2";
        try {
            controller = new ClientController(this, ipAddress);
        } catch (RemoteException ex) {
            System.out.println("Client: following exception was found: " + ex.getMessage());
            primaryStage.close();
        }

        /**
         * *********ABOVE THIS IS ENTERING SERVER IP + INIT THE
         * CONTROLLER****************
         */
        HauntedClient.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLHaunted.fxml"));
        Node root = fxmlLoader.load();
        Scene scene = new Scene((Parent) root);

        primaryStage.setTitle("Haunted");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static ClientController getController() {
        return controller;
    }

    public static Stage getStage() {
        return primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        try {
            // Create server
            RMISocketFactory.setSocketFactory(new MyClientRMISocketFactory());
        } catch (IOException ex) {
            Logger.getLogger(HauntedClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
