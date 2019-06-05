package com.utbm.reversi.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import com.utbm.reversi.model.powers.ColorBombPower;
import com.utbm.reversi.model.powers.GiletJaunePower;
import com.utbm.reversi.model.powers.LightningPower;
import com.utbm.reversi.model.powers.Power;
import com.utbm.reversi.model.powers.SwitchPower;

public class Player {
	private String name;
	private int level;
	private float xp;
	private Color color;
	private ArrayList<Power> powers;
	
	public Player(String name) {
		this.setName(name);
		this.setColor(Color.black);
		this.setLevel(0);
		this.setXp(0);
		this.powers = new ArrayList<Power>();
	}
	public Player(String name, Color color) {
		this.setName(name);
		this.setColor(color);
		this.setLevel(0);
		this.setXp(0);
		this.powers = new ArrayList<Power>();
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
	
	/**
	 * 
	 * @param power
	 */
	public void addPower(Power power) {
		this.powers.add(power);
	}
	
	/**
	 * 
	 * @param power
	 */
	public void removePower(Power power) {
		this.powers.remove(power);
	}
	
	
	public ArrayList<Power> getPowers() {
		return this.powers;
	}
	
	/**
	 * 
	 */
	public void addRandomPower() {
		int rando = (int) new Random().nextInt(4);
		
		switch (rando) {
		case 0:
			this.powers.add(new SwitchPower(this,"switch_logo.png"));
			break;
		case 1:
			this.powers.add(new ColorBombPower(this,"colorbomb_logo.png"));	
			break;
		case 2:
			this.powers.add(new LightningPower(this,"lightning_logo.png"));
			break;
		case 3:
			this.powers.add(new GiletJaunePower(this,"giletjaune_logo.png"));
			break;

		default:
			break;
		}
	}
}
