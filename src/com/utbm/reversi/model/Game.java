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

	public void addPlayer(Player player) {
		this.players.add(player);
	}
	public void removePlayer(Player player) {
		this.players.remove(player);
	}
	
	public ReversiFrame getFrame() {
		return this.frame;
	}
	
	public void addPower(Power power) {
		this.powers.add(power);
	}
	public void removePower(Power power) {
		this.powers.remove(power);
	}

	public void setStart(Boolean status) {
		this.isStart = status;
	}

	public boolean isStart() {
		return this.isStart;
	}
	public int getRound() {
		return this.round;
	}


	public void addRound() {
		this.round++;
	}
	public void addRound(int value) {
		this.round += value;
	}

	public Board getBoard() {
		return this.board;
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	

	public int getPowerNumber() {
		return powerNumber;
	}

	public int getObstaclesNumber() {
		return obstaclesNumber;
	}

	public int getTrapsNumber() {
		return trapsNumber;
	}

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

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

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
			this.frame.displayEndMessage();
		}
		else {			
			// Check if player can play
			if(this.isBlocked() == true) {
				this.players.remove(this.currentPlayer);
				this.next();
			}
		}
		
		if(this.players.size() == 1) {
			this.frame.displayEndMessage();
		}
	}
		

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
	
	
	
	public boolean isBlocked() 
	{
		for(int x = 0; x < this.getBoard().getSize(); x++) {
			for(int y = 0; y < this.getBoard().getSize(); y++) {
				FollowingRules rules = new FollowingRules(this, this.board.getBoardCells()[x][y]);
				if(rules.isPlayable()) {
					return false;
				}
			}
		}
		return true;
	}
}
