package com.github.espehel.weblog;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class EventListener implements Listener{
	
	WebLog plugin;
	
	public EventListener(WebLog plugin){
		this.plugin=plugin;
	}
	
	@EventHandler
	public void playerLogIn(PlayerLoginEvent e){
		plugin.getLogger().info(e.getPlayer().getDisplayName()+" is logging in");
	}

}
