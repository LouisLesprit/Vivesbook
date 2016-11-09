/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ui.VIVESbook;
import database.connect.ConnectionManager;

/**
 * FXML Controller class
 *
 * @author Katrien.Deleu
 */
public class LoginController implements Initializable {

    // referentie naar mainapp (start)
    private VIVESbook mainApp;
    
    @FXML
    private Label lblStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            /* als de server niet werkt kan het een paar seconden duren voordat het venster getoond wordt wanneer je terugkomt van een ander scherm
            dit weg doen in toekomst?
            */
            if(ConnectionManager.getConnection().isValid(0)){ 
                lblStatus.setText("Connected");
            }
        }catch(Exception e){ // NOG AAN TE PASSEN
            
        }
    }

    /**
     * Referentie naar mainApp (start) instellen
     *
     * @param mainApp referentie naar de runnable class die alle oproepen naar
     * de schermen bestuurt
     */
    public void setMainApp(VIVESbook mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private void btnLoginClicked(ActionEvent event){
        System.out.println("clicked");
    }
    
    @FXML
    private void btnNewUserClicked(ActionEvent event){
        mainApp.laadAccounttoevoegenScherm();
    }

}
