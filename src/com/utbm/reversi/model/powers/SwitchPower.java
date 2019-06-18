package com.utbm.reversi.model.powers;

import java.awt.Cursor;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.utbm.reversi.animation.Sprite;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class SwitchPower extends Power {
	
	public SwitchPower(Player owner, ImageIcon icon, Sprite sprite) {
		super(owner,icon,sprite,0);
	}
	public SwitchPower(Player owner, String icon, Sprite sprite) {
		super(owner,icon,sprite,0);
	}
	
	@Override
	public boolean use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 * Use -> change juste un pion de couleur 
		 */
		this.setOriginCell(cell);
		int currentX = cell.getCoordX();
		int currentY = cell.getCoordY();
		if(cell.getOwner() !=null && cell.isEnabled()) {
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
