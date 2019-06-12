package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public abstract class Power {
	
	private Player owner;
	private ImageIcon icon;
	private int duration;
	
	public Power() {
		this.owner = null;
		this.icon = null;
	}
	public Power(Player owner, ImageIcon icon, int duration) {
		this.owner = owner;
		this.icon = icon;
		this.duration = duration;
	}
	public Power(Player owner, String icon, int duration) {
		this.owner = owner;
		this.icon = new ImageIcon(icon);
		this.duration =  duration;
	}
	
	
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	public Player getOwner() {
		return this.owner;
	}
	public ImageIcon getIcon() {
		return this.icon;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public abstract void use(Game game, Cell cell);
	public abstract void next(Game game, Cell cell);
	public abstract void stop(Game game);
}
