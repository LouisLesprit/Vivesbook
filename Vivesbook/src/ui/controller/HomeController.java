/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import bags.Account;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.VIVESbook;

/**
 * FXML Controller class
 *
 * @author Anon
 */
public class HomeController implements Initializable {

    // referentie naar mainapp (start) 
    private VIVESbook mainApp;
    
    private Account account;
    
    @FXML
    private Label lblLogin;
    
    @FXML
    private Button btnEdit;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    
    public void setData(Account account){
        this.account = account;
        lblLogin.setText(account.getLogin());
    }
    
    @FXML
    private void btnLogoutClicked(ActionEvent event){
        /*
        in de toekomst:
        
        try{
            AccountTrans accTrans = new accountTrans();
            accTrans.uitloggenAccount(account);
        }catch(DBException ex){
            System.out.println("btnLogoutClicked - " + ex);
        }
        */
        
        mainApp.laadLoginScherm();
    }
    
    @FXML
    private void btnEditClicked(ActionEvent event){
        mainApp.laadAccounttoevoegenScherm(account);
    }
}
