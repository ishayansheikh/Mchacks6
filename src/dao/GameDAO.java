package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;
import java.util.logging.Level;

import util.DAOUtil;
import entity.Hint;


/**
 * This is the DAO connection test to MYSQL
 * @author leo_s
 *
 */
public class GameDAO extends DAOUtil {
	
	/**
	 * insertHint(Hint)
	 * @param hint
	 */
	public void insertHint( Hint  hint) {
		Connection connection = null;     
	    PreparedStatement statement = null;
	    String sql =	"INSERT INTO hint (xcoord, ycoord, distance, imageurl) VALUES (?,?,?,?)";
	    try {
			connection = super.getConnection();       	    	
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, hint.getXcoordinat());
	        statement.setInt(2,  hint.getYcoordinat());
	        statement.setDouble(3, hint.getDistance());
	        statement.setString(4,  hint.getImageUrl());
	        statement.executeUpdate();
	    } catch (Exception e ) {
	    	e.printStackTrace();
	
	    } finally {
	    	try {
	    		if (statement != null) {
	    			statement.close();
	    		}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	try {
	    		if (connection != null)  connection.close();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}	
	
	
	/**
	 * DeleteHint
	 * @param hint
	 */
	public void deleteHint( Hint  hint) {
		Connection connection = null;     
	    PreparedStatement statement = null;
	    String sql =	"DELETE FROM hint WHERE hintid = ?";
	    try {
			connection = super.getConnection();       	    	
	        statement = connection.prepareStatement(sql);
	        statement.setInt(1, hint.getHintId());
	        statement.executeUpdate();
	    } catch (Exception e ) {
	    	e.printStackTrace();
	
	    } finally {
	    	try {
	    		if (statement != null) {
	    			statement.close();
	    		}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	try {
	    		if (connection != null)  connection.close();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}		


	/**
	 * Return collection of hint
	 * @return
	 */
	public Collection<Hint> listAllHint() {
		Vector<Hint> vHint = new Vector<Hint>();
		String sql = "SELECT * from hint";	
  		Connection connection = null;
		PreparedStatement statement = null;
		try     {       
			connection = super.getConnection();       
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();   
            while (resultSet.next()) {
            	Hint hint = new Hint();
            	hint.setHintId(resultSet.getInt(1));
            	hint.setXcoordinat(resultSet.getInt(2));
            	hint.setYcoordinat(resultSet.getInt(3));
            	hint.setDistance(resultSet.getDouble(4));
            	hint.setImageUrl(resultSet.getString(5));
            	vHint.add(hint);    // store hint to vector
            }
		} catch (Exception e) {   
			e.printStackTrace();
		} finally {   
			try {
				statement.close();   
			} catch (Exception e) {   
				e.printStackTrace();   
			}
			
			try {   
				connection.close();   
			} catch (Exception e) {   
				e.printStackTrace();   
			}   
		}    
		return vHint;
	}

}
