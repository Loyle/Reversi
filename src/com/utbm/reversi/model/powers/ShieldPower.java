package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class ShieldPower extends Power {
	
	public ShieldPower(Player owner, ImageIcon icon) {
		super(owner,icon);
	}
	public ShieldPower(Player owner, String icon) {
		super(owner,icon);
	}
	
	@Override
	public void use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 * Use -> le pion choisi ne se retourne pas s'il est encadré 
		 */
		
		if(cell.getOwner().equals(this.getOwner()) == true) {
			while( this.getDuration()<3) {
				cell.setOwner(this.getOwner());	
			}
		}
	}

}
