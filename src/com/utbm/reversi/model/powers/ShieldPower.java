package com.utbm.reversi.model.powers;

import java.awt.Cursor;
import javax.swing.ImageIcon;

import com.utbm.reversi.animation.PowerAnimation;
import com.utbm.reversi.animation.Sprite;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class ShieldPower extends Power {
	private PowerAnimation animation;
	/**
	 * Generate a new ShieldPower
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 */
	public ShieldPower(Player owner, ImageIcon icon, Sprite sprite) {
		super(owner,icon,sprite,3);
		this.animation = null;
	}
	/**
	 * Generate a new ShieldPower
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 */
	public ShieldPower(Player owner, String icon, Sprite sprite) {
		super(owner,icon,sprite,3);
		this.animation = null;
	}
	
	@Override
	/**
	 * the chosen pawn does not turn over if it is framed 
	 * @param game 
	 * @param cell clickedCell
	 * @return boolean
	 */
	public boolean use(Game game, Cell cell) {

		if(cell.getOwner()!=null) {
			if(cell.getOwner().equals(this.getOwner())) {
				this.setOriginCell(cell); 
				cell.setLock(true);
				this.animation = cell.addHoverAnimation(this.getSprite());
				game.getBoard().getBoardCells()[cell.getCoordX()][cell.getCoordY()].updateState();
				
				game.getFrame().setCursor(Cursor.getDefaultCursor());
				return true;
			}			
		}
		return false;
	}
	@Override
	/**
	 * Decrease the duration
	 * @param game
	 */
	public void next(Game game) {
		if(this.getDuration()>0) {
			this.setDuration(this.getDuration()-1);			
		}
	}
	@Override
	/**
	 * Unlock the pawn
	 * @param game
	 */
	public void stop(Game game) {
		this.getOriginCell().setLock(false);	
		this.animation.stop();
		this.getOriginCell().updateState();
	}

}
