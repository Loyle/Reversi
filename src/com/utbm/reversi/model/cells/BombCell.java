package com.utbm.reversi.model.cells;

import java.awt.Color;

import javax.swing.ImageIcon;

import com.utbm.reversi.animation.Sprite;
import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Player;
/**
 * 
 * extends @see Cell
 *
 */
@SuppressWarnings("serial")
public class BombCell extends Cell {
	private boolean isUsed;
	private Board board;
	private int changed;
	private Sprite spriteExplosion = new Sprite("./data/explosion_animation.png", 9, 9, 10, 100, 100);;
	/**
	 * Generate a bomb 
	 * @param board 
	 */
	public BombCell(Board board) {
		super();
		this.board = board;
		this.isUsed = false;
		this.changed = 0;
	}
	/**
	 * Generate a bomb  with the cell's color
	 * @param color
	 * @param board
	 */
	public BombCell(Color color, Board board) {
		super(color);
		
		this.board = board;
		this.isUsed = false;
		this.changed = 0;
	}
	/**
	 * Generate a bomb with the cell's background
	 * @param background
	 * @param board
	 */
	public BombCell(ImageIcon background, Board board) {
		super(background);
		
		this.board = board;
		this.isUsed = false;
		this.changed = 0;
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
	 * Destroy all pawn in a 3x3 square 
	 * @return boolean
	 */
	public boolean use() {
		if(!this.isUsed && this.getOwner() != null && this.changed > 1) {			
			int x = this.getCoordX();
			int y = this.getCoordY();
			int stopX = x+1;
			int stopY = y+1;
			
			if(x > 0) {
				x--;
			}
			if(y > 0) {
				y--;
			}
			if(stopX >=  this.board.getSize()) {
				stopX--;
			}
			if(stopY >= this.board.getSize()) {
				stopY--;
			}
			
			for(int i = x; i <= stopX; i ++) {
				for(int j = y; j <= stopY; j ++) {
					if(this.board.getBoardCells()[i][j].isEnabled()) {
						this.board.getBoardCells()[i][j].addHoverAnimation(this.spriteExplosion, false);
						this.board.getBoardCells()[i][j].clearOwner();
						this.board.getBoardCells()[i][j].updateState();						
					}
				}
			}
			
			
			this.isUsed = true;
			return true;
		}
		return false;
	}
}
