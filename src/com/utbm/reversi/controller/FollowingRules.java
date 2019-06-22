package com.utbm.reversi.controller;

import java.util.ArrayList;

import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.cells.BombCell;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.model.cells.TrapCell;

public class FollowingRules {
	private Cell cell;
	private Game game;
	private int x;
	private int y;
	private int size;
	private int countInter[];

	public FollowingRules(Game game, Cell cell) {
		this.game = game;
		this.cell = cell;

		this.x = this.cell.getCoordX();
		this.y = this.cell.getCoordY();
		this.size = this.game.getBoard().getSize();
	}

	/**
	 * To see how many pawns the player can win
	 * @return Array of integer 
	 * 		To see for each direction how many points the player should win
	 */
	public int[] findInter() {
		int inter[] = { 0, 0, 0, 0, 0, 0, 0, 0 };

		// startX and startY are coordinates of a neighbour Cell
		int startX;
		int startY;

		int count;
		// Top
		startX = this.x;
		startY = this.y - 1;
		count = 0;

		// WHILE
		// Coordinates of the neighbour are in the board
		// AND
		// This neighbour has a pawn put on him/has an owner
		while (startY >= 0 && this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			// IF
			// Owner on this cell is the one who's playing now
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {

				inter[0] = count;
				break;
			} else {
				count++;
			}

			startY--;
		}

		// Bottom
		startX = this.x;
		startY = this.y + 1;
		count = 0;
		while (startY < this.size && this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[1] = count;
				break;
			} else {
				count++;
			}

			startY++;
		}

		// Left
		startX = this.x - 1;
		startY = this.y;
		count = 0;
		while (startX >= 0 && this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[2] = count;
				break;
			} else {
				count++;
			}

			startX--;
		}

		// Right
		startX = this.x + 1;
		startY = this.y;
		count = 0;
		while (startX < this.size && this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[3] = count;
				break;
			} else {
				count++;
			}

			startX++;
		}

		// Top-Right
		startX = this.x + 1;
		startY = this.y - 1;
		count = 0;
		while (startX < this.size && startY >= 0
				&& this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[4] = count;
				break;
			} else {
				count++;
			}

			startX++;
			startY--;
		}

		// Bottom-Right
		startX = this.x + 1;
		startY = this.y + 1;
		count = 0;
		while (startX < this.size && startY < this.size
				&& this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[5] = count;
				break;
			} else {
				count++;
			}
			startX++;
			startY++;
		}

		// Bottom-Left
		startX = this.x - 1;
		startY = this.y + 1;
		count = 0;
		while (startX >= 0 && startY < this.size && this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[6] = count;
				break;
			} else {
				count++;
			}
			startX--;
			startY++;
		}

		// Top-Left
		startX = this.x - 1;
		startY = this.y - 1;
		count = 0;
		while (startX >= 0 && startY >= 0 && this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[7] = count;
				break;
			} else {
				count++;
			}
			startX--;
			startY--;
		}

		return inter;
	}

	// The cell is playable if we could count an opponent's pawn between the new pawn and another pawn of the same color
	/**
	 * See if a cell is playable, i.e. if it exists pawns of other player between a player's cell and the tested cell 
	 * @return boolean 
 	 *			To see if the cell is playable
	 */
	public boolean isPlayable() {
		this.countInter = this.findInter();

		for (int i = 0; i < 8; i++) {
			if (this.countInter[i] > 0) {
				return true;
			}
		}
		return false;
	}

	// Permit to place the new pawn and reverse a impacted pawns
	/**
	 * Change, for each direction, pawns to player color
	 */
	public void replaceCell() {
		// TOP
		// Change owners of all the framed cells, and update (that's placing the pawn)
		
		ArrayList<Cell> treatedCells = new ArrayList<Cell>();
		this.cell.setOwner(this.game.getCurrentPlayer());
		treatedCells.add(this.cell);

		for (int i = 0; i <= this.countInter[0]; i++) {
			Cell treating = this.game.getBoard().getBoardCells()[this.x][this.y - i];
			if (treating.isEnabled() && treating.equals(this.cell) == false && !treating.isLock()) {
				treating.setOwner(this.game.getCurrentPlayer());
				treatedCells.add(treating);
			}
		}
		// BOTTOM
		for (int i = 0; i <= this.countInter[1]; i++) {
			Cell treating = this.game.getBoard().getBoardCells()[this.x][this.y + i];
			if (treating.isEnabled() && treating.equals(this.cell) == false && !treating.isLock()) {
				treating.setOwner(this.game.getCurrentPlayer());
				treatedCells.add(treating);
			}
		}
		// LEFT
		for (int i = 0; i <= this.countInter[2]; i++) {	
			Cell treating = this.game.getBoard().getBoardCells()[this.x - i][this.y];
			if (treating.isEnabled() && treating.equals(this.cell) == false && !treating.isLock()) {
				treating.setOwner(this.game.getCurrentPlayer());
				treatedCells.add(treating);
			}
		}
		// RIGHT
		for (int i = 0; i <= this.countInter[3]; i++) {			
			Cell treating = this.game.getBoard().getBoardCells()[this.x + i][this.y];
			if (treating.isEnabled() && treating.equals(this.cell) == false && !treating.isLock()) {
				treating.setOwner(this.game.getCurrentPlayer());
				treatedCells.add(treating);
			}
		}

		// TOP-RIGHT
		for (int i = 0; i <= this.countInter[4]; i++) {
			Cell treating = this.game.getBoard().getBoardCells()[this.x + i][this.y - i];
			if (treating.isEnabled() && treating.equals(this.cell) == false && !treating.isLock()) {
				treating.setOwner(this.game.getCurrentPlayer());
				treatedCells.add(treating);
			}
		}
		// BOTTOM-RIGHT
		for (int i = 0; i <= this.countInter[5]; i++) {
			Cell treating = this.game.getBoard().getBoardCells()[this.x + i][this.y + i];
			if (treating.isEnabled() && treating.equals(this.cell) == false && !treating.isLock()) {
				treating.setOwner(this.game.getCurrentPlayer());
				treatedCells.add(treating);
			}
		}

		// BOTTOM-LEFT
		for (int i = 0; i <= this.countInter[6]; i++) {
			Cell treating = this.game.getBoard().getBoardCells()[this.x - i][this.y + i];
			if (treating.isEnabled() && treating.equals(this.cell) == false && !treating.isLock()) {
				treating.setOwner(this.game.getCurrentPlayer());
				treatedCells.add(treating);
			}
		}
		// TOP-LEFT
		for (int i = 0; i <= this.countInter[7]; i++) {
			Cell treating = this.game.getBoard().getBoardCells()[this.x - i][this.y - i];
			if (treating.isEnabled() && treating.equals(this.cell) == false && !treating.isLock()) {
				treating.setOwner(this.game.getCurrentPlayer());
				treatedCells.add(treating);
			}
		}
		
		// Start Explosions And trap
		for(Cell cell : treatedCells) {
			if(cell instanceof BombCell) {
				((BombCell) cell).use();
			}
			else if(cell instanceof TrapCell) {
				((TrapCell) cell).use();
			}
			
			cell.updateState();
		}
	}
}

