package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class ColorBombPower extends Power {
	
	public ColorBombPower(Player owner, ImageIcon icon, ImageIcon hoverIcon) {
		super(owner,icon,hoverIcon,0);
	}
	public ColorBombPower(Player owner, String icon, String hoverIcon) {
		super(owner,icon,hoverIcon,0);
	}
	
	@Override
	public boolean use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 *Use ->  Carr� autour du pion changeant la couleur de touts les pions dans la couelur du 
		 * player ayant poser le pion
		 * 		�	*	*		�	�	�
		 *		*	�	*	=> 	�	�	�
		 * 		*	�	�		�	�	�
		 */
		this.setOriginCell(cell);
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
		
		return true;

	}
	@Override
	public void next(Game game) {
		// not necessary
	}
	@Override
	public void stop(Game game) {
		// not necessary
	}

}
