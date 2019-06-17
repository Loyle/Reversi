package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class ShieldPower extends Power {
	
	public ShieldPower(Player owner, ImageIcon icon, ImageIcon hoverIcon) {
		super(owner,icon,hoverIcon,3);
	}
	public ShieldPower(Player owner, String icon, String hoverIcon) {
		super(owner,icon,hoverIcon,3);
	}
	
	@Override
	public boolean use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 * Use -> le pion choisi ne se retourne pas s'il est encadré 
		 */
		this.setOriginCell(cell); 

		if(cell.getOwner()!=null) {
			if(cell.getOwner().equals(this.getOwner())) {
				cell.setEnabled(false);
				cell.addHoverIcon(this.getHoverIcon());
				game.getBoard().getBoardCells()[cell.getCoordX()][cell.getCoordY()].updateState();
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
		this.getOriginCell().removeHoverIcon(this.getHoverIcon());
		this.getOriginCell().updateState();
	}

}
