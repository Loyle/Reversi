package com.utbm.reversi.model.cells;

import java.awt.Color;

import javax.swing.ImageIcon;
import com.utbm.reversi.model.Player;

@SuppressWarnings("serial")
/**
 * extends @see Cell
 * 
 */
public class TrapCell extends Cell {
	private boolean isUsed;
	private int changed;
	/**
	 * Generate a trapCell
	 */
	public TrapCell() {
		super();
		
		this.changed = 0;
		this.isUsed = false;
	}
	/**
	 * Generate a trapCell with the cell's color
	 * @param color
	 */
	public TrapCell(Color color) {
		super(color);
		
		this.changed = 0;
		this.isUsed = false;
	}
	/**
	 * Generate a trapCell with the cell's background
	 * @param background
	 */
	public TrapCell(ImageIcon background) {
		super(background);
		
		this.changed = 0;
		this.isUsed = false;
	}
	
	/**
	 * @param owner the owner to set
	 */
	@Override
	public void setOwner(Player owner) {
		super.setOwner(owner);
		this.changed++;
	}
	
	@Override
	public void clearOwner() {
		this.setOwner(null);
		this.setColor(this.getDefaultColor());
		this.changed = 0;
	}
	/**
	 * Make disappear a pawn 
	 * @return
	 */
	public boolean use() {
		if(!this.isUsed && this.getOwner() != null && this.changed > 1) {
			this.clearOwner();
			
			this.isUsed = true;
			return true;
		}
		return false;
	}
}
