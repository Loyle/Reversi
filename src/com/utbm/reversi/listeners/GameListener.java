package com.utbm.reversi.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import com.utbm.reversi.controller.GameController;
import com.utbm.reversi.model.cells.Cell;

public class GameListener implements ActionListener {
	private GameController controller;
	
	public GameListener(GameController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source instanceof Cell) {
			this.controller.onCellClick((Cell) source);
		}
	}
}
