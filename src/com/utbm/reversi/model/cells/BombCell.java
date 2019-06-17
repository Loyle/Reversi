package com.utbm.reversi.model.cells;

import java.awt.Color;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;

@SuppressWarnings("serial")
public class BombCell extends Cell {
	private boolean isUsed;
	
	public BombCell() {
		super();
		
		this.isUsed = false;
	}
	public BombCell(Color color) {
		super(color);
		
		this.isUsed = false;
	}
	public BombCell(ImageIcon background) {
		super(background);
		
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
			if(stopX >= game.getBoard().getSize()) {
				stopX--;
			}
			if(stopY >= game.getBoard().getSize()) {
				stopY--;
			}
			
			for(int i = x; i <= stopX; i ++) {
				for(int j = y; j <= stopY; j ++) {
					if(game.getBoard().getBoardCells()[i][j].isEnabled()) {
						game.getBoard().getBoardCells()[i][j].clearOwner();
						game.getBoard().getBoardCells()[i][j].updateState();						
					}
				}
			}
			
			
			this.isUsed = true;
		}
	}
}
