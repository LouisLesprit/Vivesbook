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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    // Een account maken
    public void setUp() { 
        try{
            account = new Account();
            account.setNaam("Defoort");
            account.setVoornaam("Mieke");
            account.setLogin("miekedefoort");
            account.setPaswoord("wachtwoord123");
            account.setEmailadres("miekedefoort@hotmail.com");
            account.setGeslacht(Geslacht.V);
            
            accountTrans.accountToevoegen(account);
        }catch(DBException | ApplicationException ex){
            System.out.println("-setUp-" + ex);
        }
    }
    
    @After
    // Het account dat in de setup gemaakt werd, terug verijwderen, maar mag er verwijderd worden????
    public void tearDown() {
        try{
            accountDB.verwijderenAccount(account);
        }catch(DBException ex){
            System.out.println("-tearDown-" + ex);
        }
    }

    /**
     * Test of accountToevoegen method, of class AccountTrans.
     */
    @Test
    public void testAccountToevoegen() throws Exception {
        System.out.println("accountToevoegen");
        Account acc = null;
        AccountTrans instance = new AccountTrans();
        instance.accountToevoegen(acc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accountWijzigen method, of class AccountTrans.
     */
    @Test
    public void testAccountWijzigen() throws Exception {
        System.out.println("accountWijzigen");
        Account acc = null;
        AccountTrans instance = new AccountTrans();
        instance.accountWijzigen(acc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testToevoegenAccountNaamNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setNaam(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(ApplicationException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountNaamLeeg() throws DBException{
        thrown.expect(DBException.class);
        
        account.setNaam("");
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(ApplicationException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountVoornaamNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setVoornaam(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(ApplicationException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountVoornaamLeeg() throws DBException{
        thrown.expect(DBException.class);
        
        account.setVoornaam("");
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(ApplicationException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountLoginNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setLogin(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(ApplicationException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountLoginLeeg() throws DBException{
        thrown.expect(DBException.class);
        
        account.setLogin("");
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(ApplicationException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountPaswoordNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setPaswoord(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(ApplicationException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountPaswoordLeeg() throws DBException{
        thrown.expect(DBException.class);
        
        account.setPaswoord("");
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(ApplicationException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountEmailadresNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setEmailadres(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(ApplicationException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountEmailadresLeeg() throws DBException{
        thrown.expect(DBException.class);
        
        account.setEmailadres("");
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(ApplicationException ex){
            
        }
    }
    
    @Test
    public void testToevoegenAccountGeslachtNull() throws DBException{
        thrown.expect(DBException.class);
        
        account.setGeslacht(null);
        
        try{
            accountTrans.accountToevoegen(account);
        }catch(ApplicationException ex){
            
        }
    }
}
