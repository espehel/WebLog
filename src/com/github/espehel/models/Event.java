package com.github.espehel.models;

import java.util.GregorianCalendar;

public class Event {
	private int id;
	private GregorianCalendar datetime;
	private String name;
	Player player;
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public GregorianCalendar getDatetime() {
		return datetime;
	}
	public void setDatetime(GregorianCalendar datetime) {
		this.datetime = datetime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
