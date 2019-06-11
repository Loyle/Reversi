package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class GiletJaunePower extends Power {

	public GiletJaunePower(Player owner, ImageIcon icon) {
		super(owner,icon);
	}
	public GiletJaunePower(Player owner, String icon) {
		super(owner,icon);
	}
	
	@Override
	public void use(Board board, Cell cell) {
		// TODO Auto-generated method stub
		
	}

}
