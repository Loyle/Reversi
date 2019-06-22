package com.utbm.reversi.model.powers;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import com.utbm.reversi.animation.Sprite;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class SwitchPower extends Power {
	
	/**
	 * Generate a new SwithPower
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 */
	public SwitchPower(Player owner, ImageIcon icon, Sprite sprite) {
		super(owner,icon,sprite,0);
	}
	/**
	 * Generate a new SwitchPower
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 */
	public SwitchPower(Player owner, String icon, Sprite sprite) {
		super(owner,icon,sprite,0);
	}
	
	@Override
	/**
	 * Change the color of only one pawn
	 * @param game
	 * @param cell clickedCell
	 */
	public boolean use(Game game, Cell cell) {
	
		this.setOriginCell(cell);
		int currentX = cell.getCoordX();
		int currentY = cell.getCoordY();
		if(cell.getOwner() !=null && cell.isEnabled() && !cell.isLock()) {
			if(!cell.getOwner().equals(game.getCurrentPlayer())) {
				cell.setOwner(game.getCurrentPlayer());
				
				game.getFrame().setCursor(Cursor.getDefaultCursor());
				return true;
			}
		} 
			game.getBoard().getBoardCells()[currentX][currentY].updateState();
		return false;
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
