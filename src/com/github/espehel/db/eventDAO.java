package com.github.espehel.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.github.espehel.models.Event;

public class EventDAO extends DAO {
	
	/**
	 * Simply inserts a new event row in the db
	 * 
	 * @param event
	 * @return returns the same event with an ID
	 */
	
	public Event insert(Event event){
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			st = db.prepareStatement("INSERT INTO weblog.Event(time,name,Player_playerId) VALUES (?,?,?)");
			st.setDate(1, new Date(event.getDatetime().getTimeInMillis()), Calendar.getInstance());
			st.setString(2, event.getName());
			st.setInt(3, event.getPlayer().getId());
			
			st.executeUpdate();
			rs=st.getGeneratedKeys();
			if(rs.next())
				event.setId(rs.getInt(1));
		}catch(SQLException e){
			e.printStackTrace();
			//TODO log this or something
		}finally{
			db.close(st,rs);
		}
		return event;
	}
	
	/**
	 * Inserts event and checks if the player exist in the db, if not it inserts the player first
	 * 
	 * @param event
	 * @return same event with an id
	 * @throws IllegalArgumentException if the event does not have a player
	 */
	public Event fullInsert(Event event) throws IllegalArgumentException{
		if(event.getPlayer()==null)
			throw new IllegalArgumentException();
		
		PlayerDAO pdao = new PlayerDAO();
		
		//if the player is not in the db, it insert the player before it inserts the event
		if(!pdao.exist(event.getPlayer()))
			pdao.insert(event.getPlayer());
		return insert(event);
	}
}
