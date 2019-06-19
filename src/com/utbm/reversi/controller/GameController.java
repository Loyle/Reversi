package com.utbm.reversi.controller;

import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.cells.BombCell;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.model.cells.TrapCell;
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
						
						
						if(cell instanceof BombCell) {
							((BombCell) cell).use();							
						}
						else if(cell instanceof TrapCell) {
							((TrapCell) cell).use();
						}
						
						this.game.next();
					}
				}
			} else {
				if(this.game.getCurrentPlayer().getUsingPower().use(this.game, cell)) {
					if(this.game.getCurrentPlayer().getUsingPower().getClickSprite() != null) {
						cell.addHoverAnimation(this.game.getCurrentPlayer().getUsingPower().getClickSprite(),false);
					}
					this.game.addPower(this.game.getCurrentPlayer().getUsingPower());
					this.game.getCurrentPlayer().removePower(this.game.getCurrentPlayer().getUsingPower());
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
