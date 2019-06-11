package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class ColorBombPower extends Power {
	
	public ColorBombPower(Player owner, ImageIcon icon) {
		super(owner,icon);
	}
	public ColorBombPower(Player owner, String icon) {
		super(owner,icon);
	}
	
	@Override
	public void use(Board board, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 *Use ->  Carré autour du pion changeant la couleur de touts les pions dans la couelur du 
		 * player ayant poser le pion
		 * 		°	*	*		°	°	°
		 *		*	°	*	=> 	°	°	°
		 * 		*	°	°		°	°	°
		 */


	}

}
