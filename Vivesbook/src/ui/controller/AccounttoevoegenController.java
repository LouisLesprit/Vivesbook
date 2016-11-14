/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import bags.Account;
import datatype.Geslacht;
import exception.ApplicationException;
import exception.DBException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    
    private Account account;
    
    @FXML
    private TextField txtNaam, txtVoornaam, txtLogin, txtPaswoord, txtEmailadres;
    
    @FXML
    private ComboBox cbGeslacht; // De ComboBox voor het geslacht
    
    @FXML
    private Button btnAddUser, btnLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeChoiceBox();
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
        
        if(account != null){
            txtNaam.setText(account.getNaam());
            txtVoornaam.setText(account.getVoornaam());
            txtLogin.setText(account.getLogin());
            txtPaswoord.setText(account.getPaswoord());
            txtEmailadres.setText(account.getEmailadres());
            cbGeslacht.getSelectionModel().select(account.getGeslacht());
            
            btnAddUser.setText("Edit account");
            btnLogin.setText("Cancel");
            txtLogin.setDisable(true);
        }
    }
    
    private void initializeChoiceBox(){
        cbGeslacht.getItems().add(Geslacht.M);
        cbGeslacht.getItems().add(Geslacht.V);
    }
    
    public void clearFields(){
        txtNaam.clear();
        txtVoornaam.clear();
        txtLogin.clear();
        txtPaswoord.clear();
        txtEmailadres.clear();
        cbGeslacht.getSelectionModel().clearSelection();
    }
    
    @FXML
    private void btnAddUserClicked(ActionEvent event){
        Account newAccount = new Account();

        newAccount.setNaam(txtNaam.getText());
        newAccount.setVoornaam(txtVoornaam.getText());
        newAccount.setLogin(txtLogin.getText());
        newAccount.setPaswoord(txtPaswoord.getText());
        newAccount.setEmailadres(txtEmailadres.getText());
        newAccount.setGeslacht((Geslacht) cbGeslacht.getSelectionModel().getSelectedItem());

        AccountTrans accTrans = new AccountTrans();
        
        try{
            if(account != null){
                accTrans.accountWijzigen(newAccount);
            }else{
                accTrans.accountToevoegen(newAccount);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");
                alert.setHeaderText(null);
                alert.setContentText("Account werd aangemaakt");
                alert.showAndWait();
                clearFields();
            }
        }catch(DBException ex){ // NOG AANPASSEN
            System.out.println(ex);
        }catch(ApplicationException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    private void btnLoginClicked(ActionEvent event){
        if(account != null){
            mainApp.laadHomeScherm(account);
        }else{
            mainApp.laadLoginScherm();
        }
    }
}
