package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class SwitchPower extends Power {
	
	public SwitchPower(Player owner, ImageIcon icon) {
		super(owner,icon,0);
	}
	public SwitchPower(Player owner, String icon) {
		super(owner,icon,0);
	}
	
	@Override
	public void use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 * Use -> change juste un pion de couleur 
		 */
		int currentX = cell.getCoordX();
		int currentY = cell.getCoordY();
		if(cell.getOwner() !=null && cell.isEnabled()) {
			if(!cell.getOwner().equals(game.getCurrentPlayer())) {
				cell.setOwner(game.getCurrentPlayer());			
			}
		} 
			game.getBoard().getBoardCells()[currentX][currentY].updateState();
			
	}
	@Override
	public void next(Game game, Cell cell) {
		
	}
	@Override
	public void stop(Game game) {
		// TODO Auto-generated method stub
		
	}

}
