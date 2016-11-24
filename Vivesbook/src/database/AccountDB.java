package database;

import bags.Account;
import exception.ApplicationException;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.connect.ConnectionManager;
import datatype.Geslacht;

public class AccountDB implements InterfaceAccountDB {

    @Override
    public Account zoekAccountOpLogin(String login) throws DBException {

      Account returnAccount = null;
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.prepareStatement(
            "select naam, voornaam, login, passwoord, emailadres, geslacht from account where login = ? ");) {
            stmt.setString(1,login);
            stmt.execute();

            try (ResultSet r = stmt.getResultSet()) {
               if (r.next()) {
                  Account acc = new Account();
                  acc.setNaam(r.getString("naam"));
                  acc.setVoornaam(r.getString("voornaam"));
                  acc.setLogin(r.getString("login"));
                  acc.setPaswoord(r.getString("passwoord"));
                  acc.setEmailadres(r.getString("emailadres"));
                  acc.setGeslacht(Geslacht.valueOf(r.getString("geslacht")));
                  returnAccount = acc;
               }
               return returnAccount;

            } catch (SQLException sqlEx) {
               throw new DBException("SQL-exception in zoekAccountOpLogin - resultset"+ sqlEx);
            }
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in zoekAccountOpLogin - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekAccountOpLogin - connection");
      }
    }

    
    @Override
    public Account zoekAccountOpEmail(String email) throws DBException {
        
      Account returnAccount = null;
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.prepareStatement(
            "select naam, voornaam, login, passwoord, emailadres, geslacht from account where emailadres = ? ");) {
            stmt.setString(1,email);
            stmt.execute();

            try (ResultSet r = stmt.getResultSet()) {
               if (r.next()) {
                  Account acc = new Account();
                  acc.setNaam(r.getString("naam"));
                  acc.setVoornaam(r.getString("voornaam"));
                  acc.setLogin(r.getString("login"));
                  acc.setPaswoord(r.getString("passwoord"));
                  acc.setEmailadres(r.getString("emailadres"));
                  acc.setGeslacht(Geslacht.valueOf(r.getString("geslacht")));
                  returnAccount = acc;
               }
               return returnAccount;

            } catch (SQLException sqlEx) {
               throw new DBException("SQL-exception in zoekAccountOpEmail - resultset"+ sqlEx);
            }
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in zoekAccountOpEmail - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekAccountOpEmail - connection");
      }
    }

    
    
    @Override
    public void toevoegenAccount(Account account) throws DBException {
        
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.prepareStatement(
            "insert into account(naam, voornaam, login, passwoord, "
            + "emailadres, geslacht) values(?,?,?,?,?,?)");) {
            stmt.setString(1, account.getNaam());
            stmt.setString(2, account.getVoornaam());
            stmt.setString(3, account.getLogin());
            stmt.setString(4, account.getPaswoord());
            stmt.setString(5, account.getEmailadres());
            stmt.setString(6, account.getGeslacht().toString());
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in toevoegenAccount - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in toevoegenAccount - connection"+ sqlEx);
      }
    }

    
    @Override
    public void wijzigenAccount(Account tWA) throws DBException {

      try (Connection conn = ConnectionManager.getConnection();) {
            try (PreparedStatement stmt = conn.prepareStatement(            
            "update account set(naam, voornaam, login, passwoord, emailadres, geslacht) values(?,?,?,?,?,?)");) {

            stmt.setString(1, tWA.getNaam());
            stmt.setString(2, tWA.getVoornaam());
            stmt.setString(3, tWA.getLogin());
            stmt.setString(4, tWA.getPaswoord());
            stmt.setString(5, tWA.getEmailadres());
            stmt.setString(6, tWA.getGeslacht().toString());
            stmt.setString(7, tWA.getLogin());
            stmt.execute();
            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in wijzigenAccount"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in wijzigenAccount - connection"+ sqlEx);
      }
   }
    
}
