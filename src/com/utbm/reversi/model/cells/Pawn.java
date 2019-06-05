package com.utbm.reversi.model.cells;

import java.awt.Color;

import com.utbm.reversi.model.Player;

public class Pawn {
	private Player owner;
	private Color color;
	
	/**
	 * Default Constructor
	 */
	public Pawn() {
		this.setColor(Color.black);
		this.setOwner(null);
	}
	/**
	 * Constructor
	 * @param owner
	 * @param color
	 */
	public Pawn(Player owner) {
		this.owner = owner;
		this.color = owner.getColor();
	}
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	
}
