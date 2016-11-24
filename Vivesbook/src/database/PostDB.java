package database;

import bags.Post;
import database.connect.ConnectionManager;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;


public class PostDB implements InterfacePostDB {

    @Override
    public Post zoekPost(int id) throws DBException {
      Post returnPost = null;
      
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.prepareStatement(
            "select id, datum, tekst, eigenaar from post where id = ?");) {
             
            stmt.setInt(1,id);
            stmt.execute();

            try (ResultSet r = stmt.getResultSet()) {
               if (r.next()) {
                  Post post = new Post();
                  post.setId(r.getInt("id"));
                  post.setDatum(r.getTimestamp("datum").toLocalDateTime());
                  post.setTekst(r.getString("tekst"));
                  post.setEigenaar(r.getString("eigenaar"));
                  returnPost = post;
               }
               return returnPost;

            } catch (SQLException sqlEx) {
               throw new DBException("SQL-exception in zoekPost - resultset"+ sqlEx);
            }
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in zoekPost - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekPost - connection");
      }
    }

    @Override
    public ArrayList<Post> zoekAllePostsVanAccountEnVrienden(String login) throws DBException {
        
      ArrayList<Post> postArray = new ArrayList<>();
      postArray.addAll(zoekPosts(login));
      
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.
            prepareStatement(
                "select accountvriendlogin from vriendschap where accountlogin = ?");) {

               stmt.setString(1, login);
               stmt.execute();

               try (ResultSet r = stmt.getResultSet()) {

                  while (r.next()) {
                      
                      postArray.addAll(zoekPosts(r.getString("accountvriendlogin")));
                      
                  }
                  
                  return postArray;
                  
               } catch (SQLException sqlEx) {
                  throw new DBException(
                     "SQL-exception in zoekAllePostsVanAccountEnVrienden - resultset"+ sqlEx);
               }
            } catch (SQLException sqlEx) {
               throw new DBException(
                  "SQL-exception in zoekAllePostsVanAccountEnVrienden - statement"+ sqlEx);
            }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekAllePostsVanAccountEnVrienden - connection"+ sqlEx);
      }
    
    }

    @Override
    public void verwijderenPost(Integer id) throws DBException {
        
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.prepareStatement(
                 
            "delete from posts where id = ?");) {
             
            stmt.setInt(1, id);
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in verwijderenPost - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in verwijderenPost- connection"+ sqlEx);
      }  
    }

    @Override
    public Integer toevoegenPost(Post post) throws DBException {
        
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.prepareStatement(
                 
            "insert into post(datum, tekst, eigenaar) values(?,?,?)",
                Statement.RETURN_GENERATED_KEYS);) {
             
            stmt.setTimestamp(1, Timestamp.valueOf(post.getDatum()));
            stmt.setString(2,post.getTekst());
            stmt.setString(3, post.getEigenaar());
            stmt.executeUpdate();

        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
   
            return generatedKeys.getInt(1);
        }
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in toevoegenPost - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in toevoegenPost - connection"+ sqlEx);
      }
    }

    
    
    // Deze method bijgeschreven om de functionaliteit gescheiden te houden, kan eventueel in toekomstige versies
    // gebruikt worden om enkel de post van je eigen account te zoeken (bv voor de startpagina/overzicht).
    
    @Override
    public ArrayList<Post> zoekPosts(String login) throws DBException {
        
      ArrayList<Post> postArray = new ArrayList<>();
      
      try (Connection conn = ConnectionManager.getConnection();) {
         try (PreparedStatement stmt = conn.
            prepareStatement(
                "select id, datum, tekst, eigenaar from post where eigenaar = ?");) {

               stmt.setString(1, login);
               stmt.execute();

               try (ResultSet r = stmt.getResultSet()) {

                  while (r.next()) {

                     Post p = new Post();
                     p.setId(r.getInt("id"));
                     p.setDatum(r.getTimestamp("datum").toLocalDateTime());
                     p.setTekst(r.getString("tekst"));
                     p.setEigenaar(r.getString("eigenaar"));
                     postArray.add(p);
                  }
                  
                  return postArray;
                  
               } catch (SQLException sqlEx) {
                  throw new DBException(
                     "SQL-exception in zoekPosts - resultset"+ sqlEx);
               }
            } catch (SQLException sqlEx) {
               throw new DBException(
                  "SQL-exception in zoekPosts - statement"+ sqlEx);
            }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekPosts - connection"+ sqlEx);
      }
    }

}
