package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class ShieldPower extends Power {
	
	public ShieldPower(Player owner, ImageIcon icon) {
		super(owner,icon,3);
	}
	public ShieldPower(Player owner, String icon) {
		super(owner,icon,3);
	}
	
	@Override
	public void use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 * Use -> le pion choisi ne se retourne pas s'il est encadré 
		 */
		int currentX = cell.getCoordX();
		int currentY = cell.getCoordY();
		if(cell.getOwner()!=null) {
			if(cell.getOwner().equals(this.getOwner())) {
				cell.setEnabled(false);
			}			
		}
		game.getBoard().getBoardCells()[currentX][currentY].updateState();
		
	}
	@Override
	public void next(Game game, Cell cell) {
		if(this.getDuration()>0) {
			this.setDuration(this.getDuration()-1);			
		}
	}
	@Override
	public void stop(Game game) {
		// TODO Auto-generated method stub
		
	}

}
