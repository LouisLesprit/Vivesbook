/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import bags.Account;
import datatype.Geslacht;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import transactie.AccountTrans;
import ui.VIVESbook;

/**
 *
 * @author Anon
 */
public class AccounttoevoegenController implements Initializable{
    
    // referentie naar mainapp (start)
    private VIVESbook mainApp;
    
    @FXML
    private TextField txtNaam, txtVoornaam, txtLogin, txtPaswoord, txtEmailadres, txtGeslacht;

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
    
    @FXML
    private void btnAddUserClicked(ActionEvent event){
        Account newAccount = new Account();

        newAccount.setNaam(txtNaam.getText());
        newAccount.setVoornaam(txtVoornaam.getText());
        newAccount.setLogin(txtLogin.getText());
        newAccount.setPaswoord(txtPaswoord.getText());
        newAccount.setEmailadres(txtEmailadres.getText());
        newAccount.setGeslacht(Geslacht.valueOf(txtGeslacht.getText()));

        AccountTrans at = new AccountTrans();
        
        try{
            at.accountToevoegen(newAccount);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
