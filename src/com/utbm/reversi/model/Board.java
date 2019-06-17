package com.utbm.reversi.model;
import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.cells.BombCell;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.model.cells.TrapCell;

public class Board {
		private int size;
		private Cell [][] board;
		
		public Board(int size) {
			board = new Cell[size][size];
			this.size=size;
			for (int j=0;j<size;j++ ) {
				for(int i=0;i<size;i++) {
					int rando = new Random().nextInt(20);
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
				}
			}
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
