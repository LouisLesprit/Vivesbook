package database;

import bags.Post;
import database.connect.ConnectionManager;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class PostDB implements InterfacePostDB {

    @Override
    public Post zoekPost(int id) throws DBException {
        Post returnPost = null;
        
        try(Connection conn = ConnectionManager.getConnection();){
            try(PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM Post WHERE id = ?");){
                
                if((Integer) id == null){
                    stmt.setNull(1, java.sql.Types.NULL);
                }else{
                    stmt.setInt(1, id);
                }
                
                stmt.execute();
                
                try(ResultSet r = stmt.getResultSet()){
                    Post p = new Post();
                    
                    if(r.next()){
                        p.setId(r.getInt("id"));
                        p.setDatum(r.getObject("datum", LocalDateTime.class)); // ????????????????????????????????,
                        p.setTekst(r.getString("tekst"));
                        p.setEigenaar(r.getString("eigenaar"));
                        returnPost = p;
                    }
                    
                    return returnPost;
                }catch(SQLException sqlEx){
                    throw new DBException("SQL-exception in zoekPost - resultset " + sqlEx);
                }
            }catch(SQLException sqlEx){
                throw new DBException("SQL-Exception in zoekPost - statement" + sqlEx);
            }
        }catch(SQLException sqlEx){
            throw new DBException("SQL-exception in zoekPost - connection" + sqlEx);
        }    
    }

    @Override
    public ArrayList<Post> zoekAllePostsVanAccountEnVrienden(String login) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void verwijderenPost(Integer id) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer toevoegenPost(Post post) throws DBException {
        int primaryKey = -1;
                
        try(Connection conn = ConnectionManager.getConnection();){
            try(PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO post(datum, tekst, eigenaar) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);){
                
                stmt.setTimestamp(1, Timestamp.valueOf(post.getDatum()));
                stmt.setString(2, post.getTekst());
                stmt.setString(3, post.getEigenaar());
                stmt.execute();
                
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if(generatedKeys.next()){
                    primaryKey = generatedKeys.getInt(1);
                }
                
            }catch(SQLException sqlEx){
                throw new DBException("SQL-Exception in toevoegenPost- statement" + sqlEx);
            }
        }catch(SQLException sqlEx){
            throw new DBException("SQL-exception in toevoegenPost - connection" + sqlEx);
        }
        
        return primaryKey;
    }
}
