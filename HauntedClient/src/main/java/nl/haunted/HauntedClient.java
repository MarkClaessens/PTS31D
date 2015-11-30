/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.haunted;

import java.rmi.RemoteException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Mike Evers + ..
 */
public class HauntedClient extends Application {
    private ClientController controller;    
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        // Get ip address of server
        Scanner input = new Scanner(System.in);
        System.out.print("Client: Enter IP address of server: ");
        String ipAddress = input.nextLine();

        try {
            controller = new ClientController(this, ipAddress);
        } catch (RemoteException ex) {
            System.out.println("Client: following exception was found: " + ex.getMessage());
            primaryStage.close();
        }
        
        /***********ABOVE THIS IS ENTERING SERVER IP + INIT THE CONTROLLER*****************/
        this.primaryStage = primaryStage;
        StackPane root = new StackPane();
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Haunted");
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
