/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import bags.Account;
import datatype.Geslacht;
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
public class AccountDBTest {
    
    private Account account;
    private AccountDB accountDB;
    
    public AccountDBTest() {
        accountDB = new AccountDB();
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Before
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
    public void tearDown() {
        try{
            accountDB.verwijderenAccount(account);
        }catch(DBException ex){
            System.out.println("-tearDown-" + ex);
        }
    }

    @Test
    public void testToevoegenAccount(){
        try{
            accountDB.toevoegenAccount(account);
            Account ophaalAcc = accountDB.zoekAccountOpLogin(account.getLogin());
            
            assertEquals("Defoort", ophaalAcc.getNaam());
            assertEquals("Mieke", ophaalAcc.getVoornaam());
            assertEquals("miekedefoort", ophaalAcc.getLogin());
            assertEquals("wachtwoord123", ophaalAcc.getPaswoord());
            assertEquals("miekedefoort@hotmail.com", ophaalAcc.getEmailadres());
            assertEquals(Geslacht.V, ophaalAcc.getGeslacht());
        }catch(DBException ex){
            System.out.println("-testToevoegenAccount-" + ex);
        }
    }
    
    @Test
    public void testToevoegenAccountNaamNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setNaam(null);
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountVoornaamNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setVoornaam(null);
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountLoginNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setLogin(null);
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountPaswoordNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setPaswoord(null);
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountEmailadresNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setEmailadres(null);
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountGeslachtNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setGeslacht(null); /* account.getGeslacht().name() in toevoegenAccount zal hierdoor nullpointer geven.....,
                                        moet je dit controleren in toevoegenAccount() of anders oplossen?*/
        
        accountDB.toevoegenAccount(account);
    }
}
