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

    @Override
    public void accountToevoegen(Account acc) throws DBException, ApplicationException {
        if(acc == null){
            throw new ApplicationException("Er werd geen account ingevuld");
            //checkAlleVeldenIngevuld(acc);
        }
        
        // Bestaat account reeds?
        AccountDB accDb= new AccountDB();
        if(accDb.zoekAccountOpLogin(acc.getLogin()) != null){
            throw new ApplicationException("Account bestaat al");
        }
        
        accDb.toevoegenAccount(acc);
    }

    @Override
    public void accountWijzigen(Account acc) throws DBException, ApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    }
}
