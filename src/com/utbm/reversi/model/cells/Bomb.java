package com.utbm.reversi.model.cells;

import java.awt.Color;

import com.utbm.reversi.model.Player;

@SuppressWarnings("serial")
public class Bomb extends Cell {
	private boolean isUsed;
	
	public Bomb() {
		super();
		
		this.isUsed = false;
	}
	public Bomb(Color color) {
		super(color);
		
		this.isUsed = false;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		super.setOwner(owner);
		
		if(isUsed == false) {
			System.out.println("It's and BOMB");
			this.isUsed = true;
		}
	}
}
