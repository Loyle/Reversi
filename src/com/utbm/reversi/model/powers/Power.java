package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;
import com.utbm.reversi.animation.Sprite;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public abstract class Power {
	
	private Player owner;
	private ImageIcon icon;
	private Sprite sprite;
	private int duration;
	private Cell originCell;
	
	public Power() {
		this.owner = null;
		this.icon = null;
		this.sprite = null;
		this.setOriginCell(null);
	}
	public Power(Player owner, ImageIcon icon, Sprite sprite, int duration) {
		this.owner = owner;
		this.icon = icon;
		this.sprite = sprite;
		this.duration = duration;
		this.setOriginCell(null);
	}
	public Power(Player owner, String icon, Sprite sprite, int duration) {
		this.owner = owner;
		this.icon = new ImageIcon(icon);
		this.sprite = sprite;
		this.duration =  duration;
		this.setOriginCell(null);
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
	
	public Cell getOriginCell() {
		return originCell;
	}
	public void setOriginCell(Cell originCell) {
		this.originCell = originCell;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	
	public abstract boolean use(Game game, Cell cell);
	public abstract void next(Game game);
	public abstract void stop(Game game);
}
