package com.github.espehel.weblog;

import java.util.GregorianCalendar;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.espehel.db.EventDAO;
import com.github.espehel.models.*;

public class EventListener implements Listener{
	
	WebLog plugin;
	Player player = null;
	Event event = null;
	EventDAO edao = new EventDAO();
	
	public EventListener(WebLog plugin){
		this.plugin=plugin;
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent e){
		//set player properties
		player = new Player();
		player.setId(e.getPlayer().getEntityId());
		player.setNick(e.getPlayer().getName());
		
		//sets event properties
		event = new Event();
		event.setDatetime((GregorianCalendar) GregorianCalendar.getInstance());
		event.setName(e.getEventName());
		event.setPlayer(player);
		
		//sends event to db
		edao.fullInsert(event);
		
		//Reset fields
		clearModels();
		
	}
	
	private void clearModels(){
		player = null;
		event = null;
	}

}
