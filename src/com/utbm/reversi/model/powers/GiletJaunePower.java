package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class GiletJaunePower extends Power {

	public GiletJaunePower(Player owner, ImageIcon icon) {
		super(owner,icon,5);
	}
	public GiletJaunePower(Player owner, String icon) {
		super(owner,icon,5);
	}
	
	@Override
	public void use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 * Use -> block 5 case ne croix 
		 */
		
		int xStart = cell.getCoordX();
		int yStart = cell.getCoordY();
		if(cell.getCoordX()>0) {
			xStart--;
		}

		while(xStart<=cell.getCoordX()+1 && xStart<game.getBoard().getSize()) {
			game.getBoard().getBoardCells()[xStart][yStart].setEnabled(false);
			xStart++;
		}
		
		if(cell.getCoordY()>0) {
			yStart--;
		}
		xStart=cell.getCoordX();
		
		while(yStart<=cell.getCoordY()+1 && yStart<game.getBoard().getSize()) {
			game.getBoard().getBoardCells()[xStart][yStart].setEnabled(false);
			yStart++;
		}
		
	}
	@Override
	public void next(Game game, Cell cell) {
		
		this.setDuration(this.getDuration()-1);
		
	}
	@Override
	public void stop(Game game) {
		// TODO Auto-generated method stub
		
	}
}
