/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import bags.Likes;
import database.connect.ConnectionManager;
import datatype.LikeType;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LikesDB implements InterfaceLikesDB {

    @Override
    public void toevoegenLike(Likes like) throws DBException {
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.prepareStatement(
                 
            "insert into likes(accountlogin, postid, type) values(?,?,?)");) {
            stmt.setString(1, like.getAccountlogin());
            stmt.setInt(2, like.getPostid());
            stmt.setString(3, like.getType().toString());
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in toevoegenLike - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in toevoegenLike - connection"+ sqlEx);
      }
    }

    
    @Override
    public void wijzigenLike(Likes tWL) throws DBException {
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.prepareStatement(
                 
            "update likes set(accountlogin, postid, type) values(?,?,?)");) {

            stmt.setString(1, tWL.getAccountlogin());
            stmt.setInt(2, tWL.getPostid());
            stmt.setString(3, tWL.getType().toString());
            stmt.execute();
            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in wijzigenLike"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in wijzigenLike - connection"+ sqlEx);
      }
   }
    

    @Override
    public void verwijderenLike(String login, Integer postid) throws DBException {
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.prepareStatement(
                 
            "delete from likes where(accountlogin, postid) values(?,?)");) {
            stmt.setString(1, login);
            stmt.setInt(2, postid);
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in verwijderenLike - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in verwijderenLike- connection"+ sqlEx);
      }  
    }

    @Override
    public Likes zoekLike(String login, int postid) throws DBException {
        
      Likes returnLike = null;
      
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.prepareStatement(
            "select accountlogin, postid, type from likes where accountlogin = ? and postid = ? ");) {
            stmt.setString(1,login);
            stmt.setInt(2,postid);
            stmt.execute();

            try (ResultSet r = stmt.getResultSet()) {
               if (r.next()) {
                  Likes like = new Likes();
                  like.setAccountlogin(r.getString("accountlogin"));
                  like.setType(LikeType.valueOf(r.getString("type")));
                  returnLike = like;
               }
               return returnLike;

            } catch (SQLException sqlEx) {
               throw new DBException("SQL-exception in zoekLike - resultset"+ sqlEx);
            }
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in zoekLike - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekLike - connection");
      }
    }

    
    @Override
    public ArrayList<Likes> zoekAlleLikesVanPost(int postID) throws DBException {
        
      ArrayList<Likes> likeArray = new ArrayList<>();
      
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.
            prepareStatement(
                "select accountlogin, postid, type from klant where postid = ?");) {

               stmt.setInt(1, postID);
               stmt.execute();

               try (ResultSet r = stmt.getResultSet()) {

                  while (r.next()) {

                     Likes k = new Likes();
                     k.setAccountlogin(r.getString("accountlogin"));
                     k.setPostid(r.getInt("postid"));
                     k.setType(LikeType.valueOf(r.getString("type")));
                     likeArray.add(k);
                  }
                  
                  return likeArray;
                  
               } catch (SQLException sqlEx) {
                  throw new DBException(
                     "SQL-exception in zoekAlleLikesVanPost - resultset"+ sqlEx);
               }
            } catch (SQLException sqlEx) {
               throw new DBException(
                  "SQL-exception in zoekAlleLikesVanPost - statement"+ sqlEx);
            }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekAlleLikesVanPost - connection"+ sqlEx);
      }
    }

   
}
