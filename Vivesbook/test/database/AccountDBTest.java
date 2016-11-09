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
    }

    /**
     * Test of zoekAccountOpLogin method, of class AccountDB.
     */
    @Test
    public void testZoekAccountOpLogin() throws Exception {
        System.out.println("zoekAccountOpLogin");
        String login = "";
        AccountDB instance = new AccountDB();
        Account expResult = null;
        Account result = instance.zoekAccountOpLogin(login);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of zoekAccountOpEmail method, of class AccountDB.
     */
    @Test
    public void testZoekAccountOpEmail() throws Exception {
        System.out.println("zoekAccountOpEmail");
        String email = "";
        AccountDB instance = new AccountDB();
        Account expResult = null;
        Account result = instance.zoekAccountOpEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Positieve test om een account toe te voegen
     */
    @Test
    public void testToevoegenAccount() throws Exception {
        System.out.println("toevoegenAccount");
        Account account = null;
        AccountDB instance = new AccountDB();
        instance.toevoegenAccount(account);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testToevoegenAccountNaamNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setNaam(null);
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountNaamLeeg() throws DBException{
        thrown.expect(DBException.class);
        
        account.setNaam("");
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountVoornaamNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setVoornaam(null);
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountVoornaamLeeg() throws DBException{
        thrown.expect(DBException.class);
        
        account.setVoornaam("");
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountLoginNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setLogin(null);
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountLoginLeeg() throws DBException{
        thrown.expect(DBException.class);
        
        account.setLogin("");
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountPaswoordNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setPaswoord(null);
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountPaswoordLeeg() throws DBException{
        thrown.expect(DBException.class);
        
        account.setPaswoord("");
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountEmailadresNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setEmailadres(null);
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountEmailadresLeeg() throws DBException{
        thrown.expect(DBException.class);
        
        account.setEmailadres("");
        
        accountDB.toevoegenAccount(account);
    }
    
    @Test
    public void testToevoegenAccountGeslachtNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setGeslacht(null);
        
        accountDB.toevoegenAccount(account);
    }

    /**
     * Test of wijzigenAccount method, of class AccountDB.
     */
    @Test
    public void testWijzigenAccount() throws Exception {
        System.out.println("wijzigenAccount");
        Account teWijzigenAccount = null;
        AccountDB instance = new AccountDB();
        instance.wijzigenAccount(teWijzigenAccount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
