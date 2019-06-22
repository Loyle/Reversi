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
	private Sprite clickSprite;
	private int duration;
	private Cell originCell;
	
	/**
	 * Generates a new power
	 */
	
	public Power() {
		this.owner = null;
		this.icon = null;
		this.sprite = null;
		this.clickSprite = null;
		this.setOriginCell(null);
	}
	
	/**
	 * Generates a new power using a player, icon , and sprite , and a duration for an Animate power 
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 * @param duration number round where the power is effective
	 */
	public Power(Player owner, ImageIcon icon, Sprite sprite, int duration) {
		this.owner = owner;
		this.icon = icon;
		this.sprite = sprite;
		this.clickSprite = null;
		this.duration = duration;
		this.setOriginCell(null);
	}
	/**
	 * Generates a new power using a player, icon , and sprite , and a duration for an non Animate power
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 * @param duration number round where the power is effective
	 */
	public Power(Player owner, String icon, Sprite sprite, int duration) {
		this.owner = owner;
		this.icon = new ImageIcon(icon);
		this.sprite = sprite;
		this.clickSprite = null;
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
	
	
	/**
	 * @return the clickSprite
	 */
	public Sprite getClickSprite() {
		return clickSprite;
	}
	/**
	 * @param clickSprite the clickSprite to set
	 */
	public void setClickSprite(Sprite clickSprite) {
		this.clickSprite = clickSprite;
	}
	public abstract boolean use(Game game, Cell cell);
	public abstract void next(Game game);
	public abstract void stop(Game game);
}
