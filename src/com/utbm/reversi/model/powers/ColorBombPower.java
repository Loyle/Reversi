package com.utbm.reversi.model.powers;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import com.utbm.reversi.animation.Sprite;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class ColorBombPower extends Power {
	
	/**
	 * Create a new ColorBombPower
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 */
	public ColorBombPower(Player owner, ImageIcon icon, Sprite sprite) {
		super(owner,icon,sprite,0);
	}
	/**
	 * Create a new ColorBombPower
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 */
	public ColorBombPower(Player owner, String icon, Sprite sprite) {
		super(owner,icon,sprite,0);
	}
	
	
	@Override
	/**
	 * Generate a square around the pawn changing the color of all the pawns in the color of the player who placed the pawn
	 *	 
	 * 		°	*	*		°	°	°
	 *		*	°	*	=> 	°	°	°
	 * 		*	°	°		°	°	°
	 * @param cell clickedCell
	 * @param game
	 * @return boolean
	 */
	public boolean use(Game game, Cell cell) {
		
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
