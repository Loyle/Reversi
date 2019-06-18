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
	
	private ReversiFrame frame;

	private boolean isStart;
	private int round;

	public Game(ReversiFrame frame, int size, int powerNumber, int obstaclesNumber, int trapsNumber) {
		this.players = new ArrayList<Player>();
		this.powers = new ArrayList<Power>();
		this.isStart = false;
		this.round = 0;
		this.powerNumber = powerNumber ;
		this.obstaclesNumber = obstaclesNumber;
		this.trapsNumber = trapsNumber;
		
		this.frame = frame;

		// On crée un board de la taille voulu
		this.board = new Board(size,obstaclesNumber,trapsNumber);

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
			
			// update des power à supprimer, stop et remove power si duration = 0
			ArrayList<Power> powersToDelete = new ArrayList<Power>();
			for(Power power : powers) {
				power.next(this);
				System.out.println(power.getDuration());
				if(power.getDuration()==0) {
					powersToDelete.add(power);
				}
			}
			for(Power power : powersToDelete) {
				power.stop(this);
			}
				powers.removeAll(powersToDelete);
				powersToDelete.clear();
	
				// Tour suivant
				this.addRound();
		

			//for each power en cours decrementer duration check état et remove array si == 0 créer stop power 

		}else {
			// Go to next player
			this.currentPlayer = this.players.get(this.players.indexOf(this.currentPlayer) + 1);
		}

		// Update Score
		this.countScore();
		this.frame.updateScores(this.players);

		// Update Current player
		this.frame.setCurrentPlayer(this.currentPlayer);
		
		// Set Player power
		this.frame.updatePlayerPowers(this.currentPlayer);
		
		
		if (isEnded() || isBlocked()) 
		{
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
		
		int idx=0;
		
		// On parcourt la grille et on incrémente à chaque case non vide/non verte
		for (int i=0;i<board.getSize();i++) 
		{
			for (int j=0;j<board.getSize();j++) 
			{
				if (this.board.getBoardCells()[i][j].getOwner() != null || this.board.getBoardCells()[i][j].isObstacle() == true) 
				{
					idx=idx+1;
				}
			}
		}
		
		// Si le nombre de case non vides correspond au nombre de cases de la grille, la grilles est complète
		if (idx == board.getSize()*board.getSize()) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
	public boolean isBlocked() 
	{
		
		int idx1=0;
		int idx2=0;
		
		// On parcourt toute la grille
		for (int i=0;i<board.getSize();i++) 
		{
			for (int j=0;j<board.getSize();j++) 
			{
				FollowingRules rules = new FollowingRules(this, this.board.getBoardCells()[i][j]);
				
				// Si la case est vide et que l'on ne peut rien y poser, on incrémente idx1
				if (this.board.getBoardCells()[i][j].getOwner() == null && rules.isPlayable() == false && this.board.getBoardCells()[i][j].isObstacle() == false) 
				{
					idx1=idx1+1;
				}
				
				// Si la case est vide, on incrémente idx2
				if (this.board.getBoardCells()[i][j].getOwner() == null && this.board.getBoardCells()[i][j].isObstacle() == false) 
				{
					idx2=idx2+1;
				}
					
			}
		}
		
		// S'il y a autant de case vide que de cases vides où on ne peut rien poser, alors le jeu est bien bloqué
		if (idx1 == idx2) 
		{
			return true;
		}
		// Sinon, on peut continuer à jouer
		else
		{
			return false;
		}
	}
}
