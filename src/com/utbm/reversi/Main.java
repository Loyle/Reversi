package com.utbm.reversi;

import com.utbm.reversi.model.Board;
import com.utbm.reversi.view.BoardView;

public class Main {

	public static void main(String[] args) {
		//BoardView boardView = new BoardView();
		Board board = new Board(8);
		board.print();
	}
}
