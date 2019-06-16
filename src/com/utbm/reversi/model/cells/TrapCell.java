package com.utbm.reversi.model.cells;

import java.awt.Color;

import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;

@SuppressWarnings("serial")
public class TrapCell extends Cell {
	private boolean isUsed;
	
	public TrapCell() {
		super();
		
		this.isUsed = false;
	}
	public TrapCell(Color color) {
		super(color);
		
		this.isUsed = false;
	}
	
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		super.setOwner(owner);
	}
	
	public void use(Game game) {
		if(!this.isUsed) {
			this.clearOwner();
			
			this.isUsed = true;
		}
	}
}
