package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Board;
import com.utbm.reversi.model.Player;

public class ShieldPower extends Power {
	
	public ShieldPower(Player owner, ImageIcon icon) {
		super(owner,icon);
	}
	public ShieldPower(Player owner, String icon) {
		super(owner,icon);
	}
	
	@Override
	public void use(Board board, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
