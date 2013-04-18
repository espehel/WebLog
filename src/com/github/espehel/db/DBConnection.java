package com.github.espehel.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Handles DB connectivity, function as wrapper for jdbc connection
 * 
 * @author Espen
 *
 */

public class DBConnection {
	
	private Connection conn = null;
	private Properties props = new Properties();
	
	private static DBConnection instance;
	
	public static DBConnection getInstance(){
		return instance = (instance == null) ? new DBConnection() : instance;
	}
	
	public DBConnection(){
		try {
			props.load(new FileInputStream(new File("resources/server.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates and returns a new PreparedStatemt object
	 * 
	 * @param sql The sql to be used in the statement
	 * @return the new PrepatedStatement
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException{
		connect();
		PreparedStatement st;
		
		try{
		st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}catch(SQLException e){
			//TODO log it
			throw e;
		}
		return st;
	}
	/**
	 * Closes the provided statement
	 * 
	 * @param st
	 */
	public void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			//TODO log it
		}
	}

	/**
	 * Closes the provided parameters
	 * 
	 * @param st
	 * @param rs
	 */
	public void close(Statement st, ResultSet rs) {
		close(conn, st, rs);
	}

	/**
	 * Closes all input parameters
	 * 
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			// if (conn != null)
			// conn.close();
		} catch (Exception e) {
			//TODO log it
		}
	}

	/**
	 * Closes the current connection
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			//TODO log it
		}
	}
	
	/**
	 * sets up a connection to the database
	 * 
	 * @throws SQLException
	 */
	private void connect() throws SQLException{
		try{
			if(conn==null || conn.isClosed())
				conn = DriverManager.getConnection(props.getProperty("jdbcDriver")+props.getProperty("url"), props);
		}catch(SQLException e){
			//TODO log that cant connect to db, make appropriet actions
			throw e;
		}
	}
}
