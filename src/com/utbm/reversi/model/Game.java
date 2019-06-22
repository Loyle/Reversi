package com.utbm.reversi.model;


import java.util.ArrayList;
import com.utbm.reversi.controller.FollowingRules;
import com.utbm.reversi.model.powers.Power;
import com.utbm.reversi.view.ReversiFrame;

public class Game {
	private ArrayList<Player> players;
	private ArrayList<Power> powers;	
	private Player currentPlayer;
	private Board board;
	private int powerNumber;
	private int obstaclesNumber;
	private int trapsNumber;
	private int size;
	private ReversiFrame frame;
	private boolean isStart;
	private int round;

	/**
	 * Game constructor
	 *
	 * @param frame
	 * @param size
	 * @param powerNumber
	 * @param obstaclesNumber
	 * @param trapsNumber
	 * @param nbPlayers
	 */
	public Game(ReversiFrame frame, int size, int powerNumber, int obstaclesNumber, int trapsNumber, int nbPlayers) {
		this.players = new ArrayList<Player>();
		this.powers = new ArrayList<Power>();
		this.isStart = false;
		this.round = 0;
		this.powerNumber = powerNumber ;
		this.obstaclesNumber = obstaclesNumber;
		this.trapsNumber = trapsNumber;
		this.size = size;
		this.frame = frame;	
		// Creating a board on choosed size
		this.board = new Board(this.size,obstaclesNumber,trapsNumber,nbPlayers);
	}
	/**
	 * Add player to the game
	 *
	 * @param player
	 */
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	/**
	 * Remove player from the game
	 *
	 * @param player
	 */
	public void removePlayer(Player player) {
		this.players.remove(player);
	}
	/**
	 * get the windows frame
	 *
	 * @return ReversiFrame
	 */
	public ReversiFrame getFrame() {
		return this.frame;
	}

	/**
	 * add Power to powers which are actually running
	 *
	 * @param power
	 */
	public void addPower(Power power) {
		this.powers.add(power);
	}
	/**
	 * Remove power from the powers list which are actually running
	 *
	 * @param power
	 */
	public void removePower(Power power) {
		this.powers.remove(power);
	}
	/**
	 * set game status (start/stop)
	 *
	 * @param status
	 */
	public void setStart(Boolean status) {
		this.isStart = status;
	}
	/**
	 * get if the game has started
	 * @return boolean
	 */
	public boolean isStart() {
		return this.isStart;
	}
	/**
	 * get the round number of the game
	 *
	 * @return int
	 */
	public int getRound() {
		return this.round;
	}
	/**
	 *  increment the game round by 1
	 */
	public void addRound() {
		this.round++;
	}
	/**
	 * Increment the game round by a custom value
	 *
	 * @param value
	 */
	public void addRound(int value) {
		this.round += value;
	}
	/**
	 * get the game Board
	 *
	 * @return Board
	 */
	public Board getBoard() {
		return this.board;
	}
	/**
	 * get the game players
	 *
	 * @return ArrayList<Player>
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	/**
	 * get the number of powers allowed in the game
	 *
	 * @return int
	 */
	public int getPowerNumber() {
		return powerNumber;
	}
	/**
	 *	get the number of obstacles in the game
	 *
	 * @return int
	 */
	public int getObstaclesNumber() {
		return obstaclesNumber;
	}
	/**
	 * get the number of traps in the game
	 *
	 * @return int
	 */
	public int getTrapsNumber() {
		return trapsNumber;
	}
	/**
	 * Start the game
	 */
	public void run() {
		
		this.currentPlayer = this.players.get(0);

		// Give random power to each player
		for(Player player : players) {
			for(int nbPow = 0; nbPow<this.powerNumber;nbPow++) {
				player.addRandomPower();
			}
		}

		this.frame.setCurrentPlayer(this.currentPlayer);
		
		// Update power list for this player
		this.frame.updatePlayerPowers(this.currentPlayer);

		this.countScore();
		this.frame.updateScores(this.players);

		this.setStart(true);
	}
	/**
	 * get the player currently playing
	 * @return
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	/**
	 * go to the next round
	 */
	public void next() {
		this.countScore();
		
		if(this.players.indexOf(this.currentPlayer) == this.players.size() - 1) {
			// Come back to first player
			this.currentPlayer = this.players.get(0);

			// Next Round
			this.addRound();

			// for each power using, decrement duration check state and remove array if ==
			// 0 creating stop power

		} else {
			// Go to next player
			this.currentPlayer = this.players.get(this.players.indexOf(this.currentPlayer) + 1);
		}
		
		// Update of powers to delete, stop and remove power if duration = 0
					ArrayList<Power> powersToDelete = new ArrayList<Power>();
					for(Power power : powers) {
						if(power.getOwner().equals(this.currentPlayer)) {
							power.next(this);
							if (power.getDuration() == 0) {
								powersToDelete.add(power);
							}					
						}
					}
					for (Power power : powersToDelete) {
						power.stop(this);
					}
					powers.removeAll(powersToDelete);
					powersToDelete.clear();

		// Update Score
		this.countScore();
		this.frame.updateScores(this.players);

		// Update Current player
		this.frame.setCurrentPlayer(this.currentPlayer);
		
		// Set Player power
		this.frame.updatePlayerPowers(this.currentPlayer);
		
		// Check if game is ended
		if(this.isEnded() == true) {
			this.setStart(false);
			this.frame.displayEndMessage();
			return;
		}
		else {			
			// Check if player can play
			if(this.isBlocked() == true) {
				this.currentPlayer.setLose(true);
			}
		}
		
		if(this.inGamePlayer() == 1) {
			this.setStart(false);
			this.frame.displayEndMessage();
			return;
		}
		if(this.currentPlayer.hasLose()) {
			this.next();
		}
	}

	/**
	 * get the number of players still playing in the game (not lose)
	 *
	 * @return int
	 */
	private int inGamePlayer() {
		int count = 0;
		for(Player player : this.players) {
			if(player.hasLose() == false) {
				count++;
			}
		}
		return count;
	}
	/**
	 * refresh all the players score
	 */
	public void countScore() {
		for(Player player : this.players) {
			player.setScore(0);
		}

		for(int x = 0; x < this.board.getSize(); x++) {
			for(int y = 0; y < this.board.getSize(); y++) {
				if(this.board.getBoardCells()[x][y].getOwner() != null) {
					this.board.getBoardCells()[x][y].getOwner().addScore();
				}
			}
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
		for(int x = 0; x < this.getBoard().getSize(); x++) {
			for(int y = 0; y < this.getBoard().getSize(); y++) {
				if(this.getBoard().getBoardCells()[x][y].isEnabled()) {
					totalCells++;
				}
				if(this.getBoard().getBoardCells()[x][y].getOwner() != null) {
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
		for(int x = 0; x < this.getBoard().getSize(); x++) {
			for (int y = 0; y < this.getBoard().getSize(); y++) {
				if (this.board.getBoardCells()[x][y].getOwner() == null) {
					FollowingRules rules = new FollowingRules(this, this.board.getBoardCells()[x][y]);
					if (rules.isPlayable()) {
						return false;
					}
				}
			}
		}
		return true;
	}
}