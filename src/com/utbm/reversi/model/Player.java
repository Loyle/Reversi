package com.utbm.reversi.model;

import java.awt.Color;

public class Player {
	private String name;
	private int level;
	private float xp;
	private Color color;
	
	public Player(String name) {
		this.setName(name);
		this.setColor(Color.black);
		this.setLevel(0);
		this.setXp(0);
	}
	public Player(String name, Color color) {
		this.setName(name);
		this.setColor(color);
		this.setLevel(0);
		this.setXp(0);
	}
	
	/**
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return 
	 */
	public float getXp() {
		return this.xp;
	}
	/**
	 * @param xp
	 */
	public void setXp(float xp) {
		this.xp = xp;
	}
	
	/**
	 * @return 
	 */
	public int getLevel() {
		return this.level;
	}
	/**
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}	
}
