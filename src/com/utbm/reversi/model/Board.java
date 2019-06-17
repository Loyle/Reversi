package com.utbm.reversi.model;
import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.cells.BombCell;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.model.cells.TrapCell;

public class Board {
		private int size;
		private int obstaclesNumber;
		private int trapsNumber;
		private Cell [][] board;
		
		public Board(int size, int obstaclesNumber, int trapsNumber) {
			this.board = new Cell[size][size];
			this.size=size;
			this.obstaclesNumber=obstaclesNumber;
			this.trapsNumber=trapsNumber;
			for (int j=0;j<size;j++ ) {
				for(int i=0;i<size;i++) {
					this.board[i][j]= new Cell(new ImageIcon("./data/grass.png"));	
				}
			}
					
			int incrObstacles = 0;
			while (incrObstacles<obstaclesNumber) 
			{
				int randoX = new Random().nextInt(size);
				int randoY = new Random().nextInt(size);

				if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null) 
				{
					this.board[randoX][randoY] = new Cell(new Color(50,50,50));
					this.board[randoX][randoY].addHoverIcon(new ImageIcon("./data/wall.png"));
					this.board[randoX][randoY].setEnabled(false);
					incrObstacles++;
				}
			}	
			
			
			/*
					if(rando < 1) {
						// Obstacle
						board[i][j] = new Cell(new Color(50,50,50));
						board[i][j].addHoverIcon(new ImageIcon("./data/wall.png"));
						board[i][j].setEnabled(false);
					}
					
					else if(rando < 2) {
						// Add trap
						board[i][j]= new TrapCell(new ImageIcon("./data/grass.png"));
					}
					else if(rando < 3) {
						// Add Bomb
						board[i][j]= new BombCell(new ImageIcon("./data/grass.png"));
					}
					
					else {
						// Add basic Cell
						board[i][j]= new Cell(new ImageIcon("./data/grass.png"));	
					}	
					*/
					
					

					
					
					
							
		}
		
		public int getSize() {
			return this.size;
		}
		
		public void setSize(int size) {
			this.size=size;
		}
		
		public Cell[][] getBoardCells() {
			return this.board;
		}
			
}
