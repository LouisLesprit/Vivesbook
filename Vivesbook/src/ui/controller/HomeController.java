/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import bags.Account;
import exception.DBException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ui.VIVESbook;
import transactie.AccountTrans;

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
    
    public void setData(String accountLogin){
        try{
            AccountTrans accTrans = new AccountTrans();
            account = accTrans.zoekAccountOpLogin(accountLogin);
            
            if(account != null){
                lblLogin.setText(account.getLogin());
            }
        }catch(DBException ex){
            System.out.println("setData - " + accountLogin + " - " + ex);
        }
    }
}
