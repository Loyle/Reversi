package com.utbm.reversi.model.cells;

import java.awt.Color;

import javax.swing.ImageIcon;
import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Player;

@SuppressWarnings("serial")
public class BombCell extends Cell {
	private boolean isUsed;
	private Board board;
	
	public BombCell(Board board) {
		super();
		this.board = board;
		this.isUsed = false;
	}
	public BombCell(Color color, Board board) {
		super(color);
		
		this.board = board;
		this.isUsed = false;
	}
	public BombCell(ImageIcon background, Board board) {
		super(background);
		
		this.board = board;
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
