package database;

import bags.Account;
import database.connect.ConnectionManager;
import datatype.Geslacht;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDB implements InterfaceAccountDB {

    @Override
    public Account zoekAccountOpLogin(String login) throws DBException {
        Account returnAccount = null;
        
        try(Connection conn = ConnectionManager.getConnection();){
            try(PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM Account WHERE login = ?");){
                
                stmt.setString(1, login);
                stmt.execute();
                
                try(ResultSet r = stmt.getResultSet()){
                    Account a = new Account();
                    
                    if(r.next()){
                        a.setNaam(r.getString("naam"));
                        a.setVoornaam(r.getString("voornaam"));
                        a.setLogin(r.getString("login"));
                        a.setPaswoord(r.getString("paswoord"));
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
        Account returnAccount = null;
        
        try(Connection conn = ConnectionManager.getConnection();){
            try(PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM Account WHERE email = ?");){
                
                stmt.setString(1, email); 
                stmt.execute();
                
                try(ResultSet r = stmt.getResultSet()){
                    Account a = new Account();
                    
                    if(r.next()){
                        a.setNaam(r.getString("naam"));
                        a.setVoornaam(r.getString("voornaam"));
                        a.setLogin(r.getString("login"));
                        a.setPaswoord(r.getString("paswoord"));
                        a.setEmailadres(r.getString("emailadres"));
                        a.setGeslacht(Geslacht.valueOf(r.getString("geslacht")));
                        returnAccount = a;
                    }
                    
                    return returnAccount;
                }catch(SQLException sqlEx){
                    throw new DBException("SQL-exception in zoekAccountOpEmail - resultset " + sqlEx);
                }
            }catch(SQLException sqlEx){
                throw new DBException("SQL-Exception in zoekAccountOpEmail - statement" + sqlEx);
            }
        }catch(SQLException sqlEx){
            throw new DBException("SQL-exception in zoekAccountOpEmail - connection" + sqlEx);
        }    
    }

    @Override
    public void toevoegenAccount(Account account) throws DBException {
        try(Connection conn = ConnectionManager.getConnection();){
            try(PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO account(naam, voornaam, login, paswoord, emailadres, geslacht) values (?,?,?,?,?,?)");){
                
                stmt.setString(1, account.getNaam());
                stmt.setString(2, account.getVoornaam());
                stmt.setString(3, account.getLogin());
                stmt.setString(4, account.getPaswoord());
                stmt.setString(5, account.getEmailadres());
                
                if(account.getGeslacht() == null){
                    stmt.setNull(6, java.sql.Types.NULL);
                }else{
                    stmt.setString(6, account.getGeslacht().name());
                }

                stmt.execute();
                
            }catch(SQLException sqlEx){
                throw new DBException("SQL-Exception in toevoegenAccount - statement" + sqlEx);
            }
        }catch(SQLException sqlEx){
            throw new DBException("SQL-exception in toevoegenAccount - connection" + sqlEx);
        }
    }

    @Override
    public void wijzigenAccount(Account teWijzigenAccount) throws DBException {
        try(Connection conn = ConnectionManager.getConnection();){
            try(PreparedStatement stmt = conn.prepareStatement("UPDATE account SET naam = ?, "
                    + "voornaam = ?, "
                    + "paswoord = ?, "
                    + "emailadres = ?, "
                    + "geslacht = ? WHERE login = ?");){
                
                stmt.setString(1, teWijzigenAccount.getNaam());
                stmt.setString(2, teWijzigenAccount.getVoornaam());
                stmt.setString(3, teWijzigenAccount.getPaswoord());
                stmt.setString(4, teWijzigenAccount.getEmailadres());
                
                if(teWijzigenAccount.getGeslacht() == null){
                    stmt.setNull(5, java.sql.Types.NULL);
                }else{
                    stmt.setString(5, teWijzigenAccount.getGeslacht().name());
                }
                
                stmt.setString(6, teWijzigenAccount.getLogin());
                
                stmt.execute();
           }catch(SQLException sqlEx){
               throw new DBException("SQL-exception in wijzigenAccount " + sqlEx);
           }
        }catch(SQLException sqlEx){
            throw new DBException("SQL-exception in wijzigenAccount - connection " + sqlEx);
        }
    }

    public void verwijderenAccount(Account teVerwijderenAccount) throws DBException{
        try(Connection conn = ConnectionManager.getConnection();){
            try(PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM account WHERE login = ?");){
                
                stmt.setString(1, teVerwijderenAccount.getLogin());
                stmt.execute();
                
            }catch(SQLException sqlEx){
                throw new DBException("SQL-Exception in verwijderenAccount - statement" + sqlEx);
            }
        }catch(SQLException sqlEx){
            throw new DBException("SQL-exception in verwijderenAccount - connection" + sqlEx);
        }
    }
    
    public Account inloggenAccount(String login, String paswoord) throws DBException{
        Account returnAccount = null;
        
        try(Connection conn = ConnectionManager.getConnection();){
            try(PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM Account WHERE login = ? AND paswoord = ?");){
                
                stmt.setString(1, login);             
                stmt.setString(2, paswoord);
                stmt.execute();
                
                try(ResultSet r = stmt.getResultSet()){
                    Account a = new Account();
                    
                    if(r.next()){
                        a.setNaam(r.getString("naam"));
                        a.setVoornaam(r.getString("voornaam"));
                        a.setLogin(r.getString("login"));
                        a.setPaswoord(r.getString("paswoord"));
                        a.setEmailadres(r.getString("emailadres"));
                        a.setGeslacht(Geslacht.valueOf(r.getString("geslacht")));
                        returnAccount = a;
                    }
                    
                    return returnAccount;
                }catch(SQLException sqlEx){
                    throw new DBException("SQL-exception in inloggenAccount - resultset " + sqlEx);
                }
            }catch(SQLException sqlEx){
                throw new DBException("SQL-Exception in inloggenAccount - statement" + sqlEx);
            }
        }catch(SQLException sqlEx){
            throw new DBException("SQL-exception in inloggenAccount - connection" + sqlEx);
        }    
    }
}
