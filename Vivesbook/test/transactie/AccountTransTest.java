/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactie;

import bags.Account;
import database.AccountDB;
import datatype.Geslacht;
import exception.ApplicationException;
import exception.DBException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Anon
 */
public class AccountTransTest {
    
    private Account account;
    private AccountDB accountDB;
    private AccountTrans accountTrans;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    public AccountTransTest() {
        accountDB = new AccountDB();
        accountTrans = new AccountTrans();
    }
    
    @Before
    // Een account maken
    public void setUp() { 
        account = new Account();
        account.setNaam("Defoort");
        account.setVoornaam("Mieke");
        account.setLogin("miekedefoort");
        account.setPaswoord("wachtwoord123");
        account.setEmailadres("miekedefoort@hotmail.com");
        account.setGeslacht(Geslacht.V);
    }
    
    @After
    // Het account dat in de setup gemaakt werd, terug verijwderen
    public void tearDown() {
        try{
            accountDB.verwijderenAccount(account);
        }catch(DBException ex){
            System.out.println("-tearDown-" + ex);
        }
    }

    /**
     * Een null object proberen toevoegen
     */
    @Test
    public void testNullAccountToevoegen() throws ApplicationException {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Er werd geen account ingevuld");
        
        try{
            accountTrans.accountToevoegen(null);
        }catch(DBException ex){
            
        }
    }

    /**
     * Een null object proberen wijzigen
     */
    @Test
    public void testNullAccountWijzigen() throws ApplicationException {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Er werd geen account ingevuld");
        
        try{
            accountTrans.accountWijzigen(null);
        }catch(DBException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountNaamNull() throws ApplicationException{
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Naam niet ingevuld");
        
        account.setNaam(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(DBException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountNaamLeeg() throws ApplicationException{
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Naam niet ingevuld");
        
        account.setNaam("");
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(DBException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountVoornaamNull() throws ApplicationException{
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Voornaam niet ingevuld");
        
        account.setVoornaam(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(DBException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountVoornaamLeeg() throws ApplicationException{
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Voornaam niet ingevuld");
        
        account.setVoornaam("");
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(DBException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountLoginNull() throws ApplicationException{
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Login niet ingevuld");
        
        account.setLogin(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(DBException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountLoginLeeg() throws ApplicationException{
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Login niet ingevuld");
        
        account.setLogin("");
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(DBException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountPaswoordNull() throws ApplicationException{
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Paswoord niet ingevuld");
        
        account.setPaswoord(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(DBException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountPaswoordLeeg() throws ApplicationException{
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Paswoord niet ingevuld");
        
        account.setPaswoord("");
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(DBException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountEmailadresNull() throws ApplicationException{
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Emailadres niet ingevuld");
        
        account.setEmailadres(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(DBException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountEmailadresLeeg() throws ApplicationException{
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Emailadres niet ingevuld");
        
        account.setEmailadres("");
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(DBException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountGeslachtNull() throws ApplicationException{
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Geslacht niet ingevuld");
        
        account.setGeslacht(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(DBException ex){
            
        }
    }
}
