/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haunted;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class FXMLvictoryController extends BaseController implements Initializable {

    public static final String URL_FXML = "FXMLvictory.fxml";

    String winnaarnaam;
    @FXML
    private Label lblwinner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        winnaarnaam = "";
    }

    public void setWinnaarnaam(String naam) {
        this.winnaarnaam = naam;
    }

    @Override
    public void PreShowing() {
        super.PreShowing();
        lblwinner.setText(winnaarnaam);
    }

}
