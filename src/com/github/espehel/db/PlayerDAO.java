package com.github.espehel.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.espehel.models.Player;

public class PlayerDAO extends DAO {
	
	
	/**
	 * inserts a player into the database
	 * 
	 * @param player
	 */
	public void insert(Player player){
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			st = db.prepareStatement("INSERT INTO weblog.Player(playerId,nick) VALUES(?,?)");
			st.setInt(1, player.getId());
			st.setString(2, player.getNick());
			
			st.executeUpdate();

		}catch(SQLException e){
			//TODO log this or something
		}finally{
			db.close(st,rs);
		}
	}
	
	/**
	 * checks if this player exist in the database
	 * 
	 * @param player
	 * @return returns true if the player exists in the database
	 */
	public boolean exist(Player player){
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean exist = false;
		
		try{
			st = db.prepareStatement("SELECT playerId FROM weblog.Player WHERE playerId=?");
			st.setInt(1, player.getId());
			
			rs = st.executeQuery();
			
			exist = rs.getInt("playerId") != 0;
			if(rs.next());
		}catch(SQLException e){
			//TODO log this or something
		}finally{
			db.close(st,rs);
		}
		
		return exist;
	}

}
