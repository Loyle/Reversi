package com.utbm.reversi.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import com.utbm.reversi.animation.Sprite;
import com.utbm.reversi.model.powers.ColorBombPower;
import com.utbm.reversi.model.powers.FirePower;
import com.utbm.reversi.model.powers.GiletJaunePower;
import com.utbm.reversi.model.powers.LightningPower;
import com.utbm.reversi.model.powers.Power;
import com.utbm.reversi.model.powers.ShieldPower;
import com.utbm.reversi.model.powers.SwitchPower;

public class Player {
	private String name;
	private int level;
	private int score;
	private float xp;
	private Color color;
	private ArrayList<Power> powers;
	private Power usingPower;
	private boolean hasLose;

	/**
	 * Player constructor
	 *
	 * @param name
	 */
	public Player(String name) {
		this.setName(name);
		this.setColor(Color.black);
		this.setLevel(0);
		this.setXp(0);
		this.powers = new ArrayList<Power>();
		this.usingPower = null;
		this.hasLose = false;
	}

	/**
	 * Player constructor
	 *
	 * @param name
	 * @param color
	 */
	public Player(String name, Color color) {
		this.setName(name);
		this.setColor(color);
		this.setLevel(0);
		this.setXp(0);
		this.powers = new ArrayList<Power>();
		this.usingPower = null;
		this.hasLose = false;
	}
	
	/**
	 * get player color
	 *
	 * @return Color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * set player color
	 *
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * get player name
	 *
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * set player name
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * get player XP
	 *
	 * @return float
	 */
	public float getXp() {
		return this.xp;
	}
	/**
	 * set player XP
	 *
	 * @param xp
	 */
	public void setXp(float xp) {
		this.xp = xp;
	}
	
	/**
	 * get player level
	 *
	 * @return int
	 */
	public int getLevel() {
		return this.level;
	}
	/**
	 * set player level
	 *
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * set player score
	 *
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * get player score
	 *
	 * @return int
	 */
	public int getScore() {
		return this.score;
	}
	/**
	 * get if the player has lose
	 *
	 * @return the hasLose
	 */
	public boolean hasLose() {
		return hasLose;
	}
	/**
	 * set if the player has lose
	 *
	 * @param hasLose the hasLose to set
	 */
	public void setLose(boolean hasLose) {
		this.hasLose = hasLose;
	}
	/**
	 * Increase score by 1
	 */
	public void addScore() {
		this.score++;
	}
	/**
	 * Increase score by custom value
	 * @param score
	 */
	public void addScore(int score) {
		this.score += score;
	}
	
	/**
	 * add specific power to the player
	 *
	 * @param power
	 */
	public void addPower(Power power) {
		this.powers.add(power);
	}
	
	/**
	 * Remove a power
	 *
	 * @param power
	 */
	public void removePower(Power power) {
		this.powers.remove(power);
	}

	/**
	 * get player powers
	 *
	 * @return ArrayList<Power>
	 */
	public ArrayList<Power> getPowers() {
		return this.powers;
	}

	/**
	 * set the actual using power
	 *
	 * @param power
	 */
	public void setUsingPower(Power power) {
		this.usingPower = power;
	}

	/**
	 * get the actual using power
	 *
	 * @return Power
	 */
	public Power getUsingPower() {
		return this.usingPower;
	}
	
	/**
	 * give random power to player
	 */
	public void addRandomPower() {
		int rando = (int) new Random().nextInt(6);
		
		switch (rando) {
		case 0:
			this.powers.add(new SwitchPower(this,"./data/Switch_logo.png", new Sprite("./data/fire_animation.png",1,1,50,28,33)));
			break;
		case 1:
			this.powers.add(new ColorBombPower(this,"./data/ColorBomb_logo.png",new Sprite("./data/ColorBomb_logo.png",1,1,50,100,100)));	
			break;
		case 2:
			this.powers.add(new LightningPower(this,"./data/Lightning_logo.png",new Sprite("./data/lightning.png",9,1,75,42,56)));
			break;
		case 3:
			this.powers.add(new GiletJaunePower(this,"./data/GiletJaune_logo.png",new Sprite("./data/GiletJaune_hover.png",1,1,1000,100,100)));
			break;
		case 4:
			this.powers.add(new ShieldPower(this,"./data/Shield_logo.png",new Sprite("./data/Shield_logo.png",1,1,1000,100,100)));
			break;
		case 5:
			this.powers.add(new FirePower(this,"./data/Fire_logo.png",new Sprite("./data/fire_animation.png",4,1,75,28, 33)));
			break;

		default:
			break;
		}
	}
}
