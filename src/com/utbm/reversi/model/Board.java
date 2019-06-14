package com.utbm.reversi.model;
import java.awt.Color;
import java.util.Random;

import com.utbm.reversi.model.cells.Bomb;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.model.cells.Trap;

public class Board {
		private int size;
		private Cell [][] board;
		
		public Board(int size) {
			board = new Cell[size][size];
			this.size=size;
			for (int j=0;j<size;j++ ) {
				for(int i=0;i<size;i++) {
					int rando = new Random().nextInt(20);
					if(rando < 2) {
						// Add trap
						board[i][j]= new Trap(new Color(0,200,0));
					}
					else if(rando < 4) {
						// Add Bomb
						board[i][j]= new Bomb(new Color(0,200,0));
					}
					else {
						// Add basic Cell
						board[i][j]= new Cell(new Color(0,200,0));	
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
