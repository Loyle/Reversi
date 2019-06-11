package com.utbm.reversi.controller;

import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.view.ReversiFrame;

public class GameController {
	
	Game game;
	ReversiFrame frame;
	
	public GameController(Game game, ReversiFrame frame) {
		// Init GameListener
		this.game = game;
		this.frame = frame;
		
	}
	
	public void onCellClick(Cell cell) {
		if(this.game.isStart()) {
			// On veut que la case appartienne pas à un joueur
			if(cell.getOwner() == null) {
				cell.setOwner(this.game.getCurrentPlayer());
				this.game.next();
			}
		}
		else {
			System.out.println("[DEBUG] Game is not start");
		}
	}
}
