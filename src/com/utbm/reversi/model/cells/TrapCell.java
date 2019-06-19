package com.utbm.reversi.model.cells;

import java.awt.Color;

import javax.swing.ImageIcon;
import com.utbm.reversi.model.Player;

@SuppressWarnings("serial")
public class TrapCell extends Cell {
	private boolean isUsed;
	private int changed;
	
	public TrapCell() {
		super();
		
		this.changed = 0;
		this.isUsed = false;
	}
	public TrapCell(Color color) {
		super(color);
		
		this.changed = 0;
		this.isUsed = false;
	}
	public TrapCell(ImageIcon background) {
		super(background);
		
		this.changed = 0;
		this.isUsed = false;
	}
	
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		super.setOwner(owner);
		this.changed++;
	}
	
	public boolean use() {
		if(!this.isUsed && this.getOwner() != null && this.changed > 1) {
			this.clearOwner();
			
			this.isUsed = true;
			return true;
		}
		return false;
	}
}
