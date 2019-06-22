package com.utbm.reversi.controller;

import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.BombCell;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.model.cells.TrapCell;
import com.utbm.reversi.model.powers.Power;
import com.utbm.reversi.view.ReversiFrame;

import java.util.ArrayList;


public class GameController {

	Game game;
	ReversiFrame frame;

	public GameController(Game game, ReversiFrame frame) {
		// Init GameListener
		this.game = game;
		this.frame = frame;

	}
	/**
	 * Check the clicked cell for traps
	 * @param cell
	 * 		The cell clicked
	 */
	public void onCellClick(Cell cell) {
		if (this.game.isStart()) {
			// We want to the cell doesn't belong to a player 
			if (this.game.getCurrentPlayer().getUsingPower() == null) {
				if (cell.getOwner() == null && !cell.isLock()) {
					FollowingRules rules = new FollowingRules(this.game, cell);
					if (rules.isPlayable()) {
						rules.replaceCell();
						
						
						if(cell instanceof BombCell) {
							((BombCell) cell).use();							
						}
						else if(cell instanceof TrapCell) {
							((TrapCell) cell).use();
						}
						
						this.next();
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
					this.next();
				}
			}
		} else {
			System.out.println("[DEBUG] Game is not start");
		}
	}
	/**
	 * Start the game
	 */
	public void run() {

		this.game.setCurrentPlayer(this.game.getPlayers().get(0));

		// Give random power to each player
		for(Player player : this.game.getPlayers()) {
			for(int nbPow = 0; nbPow<this.game.getPowerNumber();nbPow++) {
				player.addRandomPower();
			}
		}

		this.frame.setCurrentPlayer(this.game.getCurrentPlayer());

		// Update power list for this player
		this.frame.updatePlayerPowers(this.game.getCurrentPlayer());

		this.game.countScore();
		this.frame.updateScores(this.game.getPlayers());

		this.game.setStart(true);
	}

	/**
	 * go to the next round
	 */
	public void next() {
		if(this.game.getPlayers().indexOf(this.game.getCurrentPlayer()) == this.game.getPlayers().size() - 1) {
			// Come back to first player
			this.game.setCurrentPlayer(this.game.getPlayers().get(0));

			// Next Round
			this.game.addRound();

			// for each power using, decrement duration check state and remove array if ==
			// 0 creating stop power

		} else {
			// Go to next player
			this.game.setCurrentPlayer(this.game.getPlayers().get(this.game.getPlayers().indexOf(this.game.getCurrentPlayer()) + 1));
		}

		// Update of powers to delete, stop and remove power if duration = 0
		ArrayList<Power> powersToDelete = new ArrayList<Power>();
		for(Power power : this.game.getPowers()) {
			if(power.getOwner().equals(this.game.getCurrentPlayer())) {
				power.next(this.game);
				if (power.getDuration() == 0) {
					powersToDelete.add(power);
				}
			}
		}
		for (Power power : powersToDelete) {
			power.stop(this.game);
		}
		this.game.getPowers().removeAll(powersToDelete);
		powersToDelete.clear();

		// Update Score
		this.game.countScore();
		this.frame.updateScores(this.game.getPlayers());

		// Update Current player
		this.frame.setCurrentPlayer(this.game.getCurrentPlayer());

		// Set Player power
		this.frame.updatePlayerPowers(this.game.getCurrentPlayer());

		// Check if game is ended
		if(this.isEnded() == true) {
			this.game.setStart(false);
			this.frame.displayEndMessage();
			return;
		}
		else {
			// Check if player can play
			if(this.isBlocked() == true) {
				this.game.getCurrentPlayer().setLose(true);
			}
		}

		if(this.game.inGamePlayer() == 1) {
			this.game.setStart(false);
			this.frame.displayEndMessage();
			return;
		}
		if(this.game.getCurrentPlayer().hasLose()) {
			this.next();
		}
	}

	/**
	 * check if the board is full => game ended
	 *
	 * @return boolean
	 */
	public boolean isEnded()
	{
		int totalCells = 0;
		int usedCells = 0;
		for(int x = 0; x < this.game.getBoard().getSize(); x++) {
			for(int y = 0; y < this.game.getBoard().getSize(); y++) {
				if(this.game.getBoard().getBoardCells()[x][y].isEnabled()) {
					totalCells++;
				}
				if(this.game.getBoard().getBoardCells()[x][y].getOwner() != null) {
					usedCells++;
				}
			}
		}
		if(totalCells == usedCells) {
			return true;
		}
		return false;
	}

	/**
	 * Check if the current player can play => is blocked
	 * @return boolean
	 */
	public boolean isBlocked()
	{
		for(int x = 0; x < this.game.getBoard().getSize(); x++) {
			for (int y = 0; y < this.game.getBoard().getSize(); y++) {
				if (this.game.getBoard().getBoardCells()[x][y].getOwner() == null && this.game.getBoard().getBoardCells()[x][y].isEnabled()) {
					FollowingRules rules = new FollowingRules(this.game, this.game.getBoard().getBoardCells()[x][y]);
					if (rules.isPlayable()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public Game getGame() {
		return game;
	}
	
	public ReversiFrame getFrame() {
		return frame;
	}

}
