package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class LightningPower extends Power {

	public LightningPower(Player owner, ImageIcon icon) {
		super(owner,icon,5);
	}
	public LightningPower(Player owner, String icon) {
		super(owner,icon,5);
	}
	
	@Override
	public void use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		
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
