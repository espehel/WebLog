package com.github.espehel.test;

import java.util.GregorianCalendar;

import com.github.espehel.db.EventDAO;
import com.github.espehel.models.Event;
import com.github.espehel.models.Player;

public class SQLTest {
	
	
	public static void main(String[] args){
		Player player= new Player();
		player.setId(555);
		player.setNick("testPlayer");
		
		Event event = new Event();
		event.setDatetime((GregorianCalendar) GregorianCalendar.getInstance());
		event.setName("TestEvent");
		event.setPlayer(player);
		
		EventDAO edao = new EventDAO();
		Event returnedEvent = edao.fullInsert(event);
		
		System.out.println(returnedEvent.getId());
	}

}
