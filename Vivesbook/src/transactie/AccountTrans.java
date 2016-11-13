/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactie;

import bags.Account;
import database.AccountDB;
import exception.ApplicationException;
import exception.DBException;

/**
 *
 * @author Katrien.Deleu
 */
public class AccountTrans implements InterfaceAccountTrans {
    
    public Account zoekAccountOpLogin(String login) throws DBException {
        AccountDB accDB = new AccountDB();
        return accDB.zoekAccountOpLogin(login);
    }
    
    public Account zoekAccountOpEmail(String email) throws DBException {
        AccountDB accDB = new AccountDB();
        return accDB.zoekAccountOpEmail(email);
    }
    
    public Account inloggenAccount(String login, String paswoord) throws DBException{
        AccountDB accDB = new AccountDB();
        return accDB.inloggenAccount(login, paswoord);
    }

    @Override
    public void accountToevoegen(Account acc) throws DBException, ApplicationException {
        if(acc == null){
            throw new ApplicationException("Er werd geen account ingevuld");
        }
        
        checkAlleVeldenIngevuld(acc);
        
        AccountDB accDB = new AccountDB();
        
        // Bestaat account reeds?
        if(accDB.zoekAccountOpLogin(acc.getLogin()) != null){
            throw new ApplicationException("Account bestaat al");
        }
        
        accDB.toevoegenAccount(acc);
    }

    @Override
    public void accountWijzigen(Account acc) throws DBException, ApplicationException {
        if(acc == null){
            throw new ApplicationException("Er werd geen account ingevuld");
        }
        
        checkAlleVeldenIngevuld(acc);
        
        AccountDB accDB = new AccountDB();
        
        if(accDB.zoekAccountOpLogin(acc.getLogin()) != null){
            throw new ApplicationException("Er bestaat al een account met dezelfde login");
        }
        if(accDB.zoekAccountOpEmail(acc.getEmailadres()) != null){
            throw new ApplicationException("Er bestaat al een account met hetzelfde emailadres");
        }
        
        accDB.wijzigenAccount(acc);
    }

    private void checkAlleVeldenIngevuld(Account a) throws ApplicationException{
        if(a.getNaam() == null || a.getNaam().equals("")){
            throw new ApplicationException("Naam niet ingevuld");
        }
        if(a.getVoornaam() == null || a.getVoornaam().equals("")){
            throw new ApplicationException("Voornaam niet ingevuld");
        }
        if(a.getLogin() == null || a.getLogin().equals("")){
            throw new ApplicationException("Login niet ingevuld");
        }
        if(a.getPaswoord() == null || a.getPaswoord().equals("")){
            throw new ApplicationException("Paswoord niet ingevuld");
        }
        if(a.getEmailadres() == null || a.getEmailadres().equals("")){
            throw new ApplicationException("Emailadres niet ingevuld");
        }
        if(a.getGeslacht() == null){
            throw new ApplicationException("Geslacht niet ingevuld");
        }
    }
}
