package com.utbm.reversi.model;


import java.util.ArrayList;
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
	 * get the player currently playing
	 * @return
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * set the current player
	 *
	 * @param player
	 */
	public void setCurrentPlayer(Player player) {
		this.currentPlayer = player;
	}

	/**
	 * Return the power which are currently activate
	 *
	 * @return
	 */
	public ArrayList<Power> getPowers() {
		return this.powers;
	}
	/**
	 * get the number of players still playing in the game (not lose)
	 *
	 * @return int
	 */
	public int inGamePlayer() {
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
		for (Player player : this.players) {
			player.setScore(0);
		}

		for (int x = 0; x < this.board.getSize(); x++) {
			for (int y = 0; y < this.board.getSize(); y++) {
				if (this.board.getBoardCells()[x][y].getOwner() != null) {
					this.board.getBoardCells()[x][y].getOwner().addScore();
				}
			}
		}
	}
}