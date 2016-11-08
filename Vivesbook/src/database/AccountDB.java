package database;

import bags.Account;
import database.connect.ConnectionManager;
import datatype.Geslacht;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDB implements InterfaceAccountDB {

    @Override
    public Account zoekAccountOpLogin(String login) throws DBException {
        Account returnAccount = null;
        
        try(Connection conn = ConnectionManager.getConnection();){
            try(PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM Account WHERE login = ?");){
                
                if(login == null){
                    stmt.setNull(1, java.sql.Types.NULL);
                }else{
                    stmt.setString(1, login);
                }
                
                stmt.execute();
                
                try(ResultSet r = stmt.getResultSet()){
                    Account a = new Account();
                    
                    if(r.next()){
                        a.setNaam(r.getString("naam"));
                        a.setVoornaam(r.getString("voornaam"));
                        a.setLogin(r.getString("login"));
                        a.setEmailadres(r.getString("emailadres"));
                        a.setGeslacht(Geslacht.valueOf(r.getString("geslacht")));
                        returnAccount = a;
                    }
                    
                    return returnAccount;
                }catch(SQLException sqlEx){
                    throw new DBException("SQL-exception in zoekAccountOpLogin - resultset " + sqlEx);
                }
            }catch(SQLException sqlEx){
                throw new DBException("SQL-Exception in zoekAccountOpLogin - statement" + sqlEx);
            }
        }catch(SQLException sqlEx){
            throw new DBException("SQL-exception in zoekAccountOpLogin - connection" + sqlEx);
        }    
    }

    @Override
    public Account zoekAccountOpEmail(String email) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toevoegenAccount(Account account) throws DBException {
        int primaryKey = -1;
        
        try(Connection conn = ConnectionManager.getConnection();){
            try(PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO account(naam, voornaam, login, paswoord, emailadres, geslacht) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);){
                
                stmt.setString(1, account.getNaam());
                stmt.setString(2, account.getVoornaam());
                stmt.setString(3, account.getLogin());
                stmt.setString(4, account.getPaswoord());
                stmt.setString(5, account.getEmailadres());
                stmt.setString(6, account.getGeslacht().name());
                stmt.execute();
                
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if(generatedKeys.next()){
                    primaryKey = generatedKeys.getInt(1);
                }
            }catch(SQLException sqlEx){
                throw new DBException("SQL-Exception in toevoegenAccount - statement" + sqlEx);
            }
        }catch(SQLException sqlEx){
            throw new DBException("SQL-exception in toevoegenAccount - connection" + sqlEx);
        }
    }

    @Override
    public void wijzigenAccount(Account teWijzigenAccount) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
