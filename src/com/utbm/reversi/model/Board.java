package com.utbm.reversi.model;
import com.utbm.reversi.model.cells.Cells;

public class Board {
		private int size;
		private Cells [][] board;
		
		int[][] multiples = new int[4][2]; 
		
		public Board(int size) {
			board = new Cells[size][size];
			this.size=size;
			for (int i=0;i<size;i++ ) {
				for(int j=0;j<size;j++) {
							board[i][j]= new Cells();		
				}
			}
		}
		
		public int getSize() {
			return this.size;
		}
		
		public void setSize(int size) {
			this.size=size;
		}
		
		
		public void print() {
			for (int i=0;i<size;i++ ) {
				for(int j=0;j<size;j++) {
					System.out.print(""+this.board[i][j].getValue());			
				}
				System.out.println();
			}
		}
		
}
