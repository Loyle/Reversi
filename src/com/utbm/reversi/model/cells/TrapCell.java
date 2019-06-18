package com.utbm.reversi.model.cells;

import java.awt.Color;

import javax.swing.ImageIcon;
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
	public TrapCell(ImageIcon background) {
		super(background);
		
		this.isUsed = false;
	}
	
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		if(!this.use()) {
			super.setOwner(owner);
		}
	}
	
	public boolean use() {
		if(!this.isUsed && this.getOwner() != null) {
			this.clearOwner();
			
			this.isUsed = true;
			return true;
		}
		return false;
	}
}
