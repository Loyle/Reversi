package com.utbm.reversi.model;
import java.awt.Color;

import com.utbm.reversi.model.cells.Cell;

public class Board {
		private int size;
		private Cell [][] board;
		
		public Board(int size) {
			board = new Cell[size][size];
			this.size=size;
			for (int j=0;j<size;j++ ) {
				for(int i=0;i<size;i++) {
							board[i][j]= new Cell(new Color(0,200,0));		
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
