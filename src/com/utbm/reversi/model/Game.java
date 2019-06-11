package com.utbm.reversi.model;

import java.util.ArrayList;
import com.utbm.reversi.model.powers.Power;
import com.utbm.reversi.view.ReversiFrame;

public class Game {
	private ArrayList<Player> players;
	private ArrayList<Power> powers;
	private Player currentPlayer;
	private Board board;
	
	private ReversiFrame frame;
	
	private boolean isStart;
	private int round;
	
	public Game(ReversiFrame frame, int size) {
		this.players = new ArrayList<Player>();
		this.powers = new ArrayList<Power>();
		
		this.isStart = false;
		this.round = 0;
		
		this.frame = frame;
		
		// On crée un board de la taille voulu
		this.board = new Board(size);
		
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	public void removePlayer(Player player) {
		this.players.remove(player);
	}
	
	public void addPower(Power power) {
		this.powers.add(power);
	}
	public void removePower(Power power) {
		this.powers.add(power);
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
	
	public void run() {
		this.currentPlayer = this.players.get(0);
		
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

			// Tour suivant
			this.addRound();
		}else {
			// Go to next player
			this.currentPlayer = this.players.get(this.players.indexOf(this.currentPlayer) + 1);
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
		
		System.out.println("SCORE : " + this.players.get(0).getScore());
	}
}
