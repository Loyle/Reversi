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
	
	public ShieldPower(Player owner, ImageIcon icon, Sprite sprite) {
		super(owner,icon,sprite,3);
		this.animation = null;
	}
	public ShieldPower(Player owner, String icon, Sprite sprite) {
		super(owner,icon,sprite,3);
		this.animation = null;
	}
	
	@Override
	public boolean use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 * Use -> le pion choisi ne se retourne pas s'il est encadr� 
		 */
		this.setOriginCell(cell); 

		if(cell.getOwner()!=null) {
			if(cell.getOwner().equals(this.getOwner())) {
				cell.setEnabled(false);
				this.animation = cell.addHoverAnimation(this.getSprite());
				game.getBoard().getBoardCells()[cell.getCoordX()][cell.getCoordY()].updateState();
				
				game.getFrame().setCursor(Cursor.getDefaultCursor());
				return true;
			}			
		}
		return false;
	}
	@Override
	public void next(Game game) {
		if(this.getDuration()>0) {
			this.setDuration(this.getDuration()-1);			
		}
	}
	@Override
	public void stop(Game game) {
		this.getOriginCell().setEnabled(true);	
		this.getOriginCell().removeHoverIcon(this.animation);
		this.getOriginCell().updateState();
	}

}
