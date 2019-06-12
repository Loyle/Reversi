package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class FlintnSteelPower extends Power {
	
	public FlintnSteelPower(Player owner, ImageIcon icon) {
		super(owner,icon,3);
	}
	
	public FlintnSteelPower(Player owner, String icon) {
		super(owner,icon,3);
	}
	
	@Override
	public void use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 * Use -> une cell en fire , set proba pour que une des cells autour devient en fire 
		 * dure 3-5 tour après s'éteint
		 */
	}
	@Override
	public void next(Game game, Cell cell) {
		this.setDuration(this.getDuration()-1);
	}
	@Override
	public void stop(Game game) {
		// TODO Auto-generated method stub
		
	}
	
}
