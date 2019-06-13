package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class ColorBombPower extends Power {
	
	public ColorBombPower(Player owner, ImageIcon icon) {
		super(owner,icon,0);
	}
	public ColorBombPower(Player owner, String icon) {
		super(owner,icon,0);
	}
	
	@Override
	public void use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 *Use ->  Carré autour du pion changeant la couleur de touts les pions dans la couelur du 
		 * player ayant poser le pion
		 * 		°	*	*		°	°	°
		 *		*	°	*	=> 	°	°	°
		 * 		*	°	°		°	°	°
		 */

		int xStart = cell.getCoordX();
		int yStart = cell.getCoordY();
		if (cell.getCoordX()>0) {
			xStart--;
		}
		if (cell.getCoordY()>0) {
			yStart--;
		}
		int saveY = yStart;
		while ( xStart<=cell.getCoordX()+1 && xStart<game.getBoard().getSize()) {
			yStart= saveY;
			while ( yStart<=cell.getCoordY()+1 && yStart<game.getBoard().getSize()) {
				game.getBoard().getBoardCells()[xStart][yStart].setOwner(this.getOwner());
				game.getBoard().getBoardCells()[xStart][yStart].updateState();
				yStart++;
			}
			xStart++;
		}

	}
	@Override
	public void next(Game game, Cell cell) {
		
	}
	@Override
	public void stop(Game game) {
		
	}

}
