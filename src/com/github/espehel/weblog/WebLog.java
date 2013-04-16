package com.github.espehel.weblog;

import org.bukkit.plugin.java.JavaPlugin;

public final class WebLog extends JavaPlugin{
	
	EventListener eventListener;
	
	//code for when the plugin is enable. Sets up the plugin
	@Override
	public void onEnable(){
		//initialize
		eventListener = new EventListener(this);
		
		//run
		getLogger().info("onEnable has been invoked, fuck yeah");
		getServer().getPluginManager().registerEvents(eventListener, this);
	}	
	//code for when the plugin is disabled. Clean up stuff
	@Override
	public void onDisable(){
		getLogger().info("onDisable has been invoked");
	}
}
