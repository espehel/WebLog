package com.github.espehel.weblog;

import java.io.*;

import org.bukkit.plugin.java.JavaPlugin;

public final class WebLog extends JavaPlugin{
	
	EventListener eventListener;
	
	//code for when the plugin is enable. Sets up the plugin
	@Override
	public void onEnable(){
		//initialize
		eventListener = new EventListener(this);
		
		
		File datafolder = getDataFolder();
		if(!datafolder.exists()){
			datafolder.mkdir();
			log("IMPORTANT! The directory \"WEBLOG\" has been made in your plugin directory.");
			log("IMPORTANT! Put your database.properties file there!");
		}
		
		//run
		log("onEnable has been invoked, fuck yeah");
		getServer().getPluginManager().registerEvents(eventListener, this);
	}	
	//code for when the plugin is disabled. Clean up stuff
	@Override
	public void onDisable(){
		log("onDisable has been invoked");
	}
	public void log(String text) {
		System.out.println(text);
		getLogger().info(text);
	}
}
