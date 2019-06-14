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
		if (this.game.isStart()) {
			// On veut que la case appartienne pas à un joueur
			if (this.game.getCurrentPlayer().getUsingPower() == null) {
				if (cell.getOwner() == null) {
					FollowingRules rules = new FollowingRules(this.game, cell);

					if (rules.isPlayable()) {
						rules.replaceCell();
						this.game.next();
					}
				}
			} else {
				if(this.game.getCurrentPlayer().getUsingPower().use(this.game, cell)) {
					this.game.getPowers().add(this.game.getCurrentPlayer().getUsingPower());
					//this.game.getCurrentPlayer().getPowers().remove(this.game.getCurrentPlayer().getUsingPower());
					this.game.getCurrentPlayer().setUsingPower(null);
					this.game.next();
				}
			}
		} else {
			System.out.println("[DEBUG] Game is not start");
		}
	}

	public Game getGame() {
		return game;
	}

	public ReversiFrame getFrame() {
		return frame;
	}

}
