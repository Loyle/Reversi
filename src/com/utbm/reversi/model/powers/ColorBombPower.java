package com.utbm.reversi.model.powers;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import com.utbm.reversi.animation.Sprite;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class ColorBombPower extends Power {
	
	public ColorBombPower(Player owner, ImageIcon icon, Sprite sprite) {
		super(owner,icon,sprite,0);
	}
	public ColorBombPower(Player owner, String icon, Sprite sprite) {
		super(owner,icon,sprite,0);
	}
	
	@Override
	public boolean use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 *Use ->  Carré autour du pion changeant la couleur de touts les pions dans la couelur du 
		 * player ayant poser le pion
		 * 		°	*	*		°	°	°
		 *		*	°	*	=> 	°	°	°
		 * 		*	°	°		°	°	°
		 */
		if(cell.isLock()) {
			return false;
		}else {
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
					if(game.getBoard().getBoardCells()[xStart][yStart].isEnabled() && !game.getBoard().getBoardCells()[xStart][yStart].isLock()) {
						game.getBoard().getBoardCells()[xStart][yStart].setOwner(this.getOwner());
						game.getBoard().getBoardCells()[xStart][yStart].updateState();
					}
					yStart++;
				}
				xStart++;
			}
			
			game.getFrame().setCursor(Cursor.getDefaultCursor());
			return true; 			
		}
		

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
