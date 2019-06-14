package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class GiletJaunePower extends Power {

	public GiletJaunePower(Player owner, ImageIcon icon, ImageIcon hoverIcon) {
		super(owner, icon, hoverIcon, 5);
	}

	public GiletJaunePower(Player owner, String icon, String hoverIcon) {
		super(owner, icon, hoverIcon, 5);
	}

	@Override
	public boolean use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 * Use -> block 5 case ne croix
		 */
		this.setOriginCell(cell);
		int xStart = cell.getCoordX();
		int yStart = cell.getCoordY();
		if (cell.getCoordX() > 0) {
			xStart--;
		}

		while (xStart <= cell.getCoordX() + 1 && xStart < game.getBoard().getSize()) {
			game.getBoard().getBoardCells()[xStart][yStart].setEnabled(false);
			if (game.getBoard().getBoardCells()[xStart][yStart].getOwner() != null) {
				game.getBoard().getBoardCells()[xStart][yStart].addHoverIcon(this.getHoverIcon());
			} else {
				game.getBoard().getBoardCells()[xStart][yStart].addHoverIcon(this.getIcon());
			}
			game.getBoard().getBoardCells()[xStart][yStart].updateState();

			xStart++;
		}

		if (cell.getCoordY() > 0) {
			yStart--;
		}
		xStart = cell.getCoordX();

		while (yStart <= cell.getCoordY() + 1 && yStart < game.getBoard().getSize()) {
			game.getBoard().getBoardCells()[xStart][yStart].setEnabled(false);
			if (game.getBoard().getBoardCells()[xStart][yStart].getOwner() != null) {
				game.getBoard().getBoardCells()[xStart][yStart].addHoverIcon(this.getHoverIcon());
			} else {
				game.getBoard().getBoardCells()[xStart][yStart].addHoverIcon(this.getIcon());
			}
			game.getBoard().getBoardCells()[xStart][yStart].updateState();
			yStart++;
		}
		return true;

	}

	@Override
	public void next(Game game) {
		this.setDuration(this.getDuration() - 1);
	}

	@Override
	public void stop(Game game) {

		int xStart = this.getOriginCell().getCoordX();
		int yStart = this.getOriginCell().getCoordY();
		if (this.getOriginCell().getCoordX() > 0) {
			xStart--;
		}

		while (xStart <= this.getOriginCell().getCoordX() + 1 && xStart < game.getBoard().getSize()) {
			game.getBoard().getBoardCells()[xStart][yStart].setEnabled(true);
			if (game.getBoard().getBoardCells()[xStart][yStart].getOwner() != null) {
				game.getBoard().getBoardCells()[xStart][yStart].removeHoverIcon(this.getHoverIcon());
			} else {
				game.getBoard().getBoardCells()[xStart][yStart].removeHoverIcon(this.getIcon());
			}
			game.getBoard().getBoardCells()[xStart][yStart].updateState();
			xStart++;
		}

		if (this.getOriginCell().getCoordY() > 0) {
			yStart--;
		}
		xStart = this.getOriginCell().getCoordX();

		while (yStart <= this.getOriginCell().getCoordY() + 1 && yStart < game.getBoard().getSize()) {
			game.getBoard().getBoardCells()[xStart][yStart].setEnabled(true);
			game.getBoard().getBoardCells()[xStart][yStart].removeHoverIcon(this.getHoverIcon());
			game.getBoard().getBoardCells()[xStart][yStart].removeHoverIcon(this.getIcon());
			game.getBoard().getBoardCells()[xStart][yStart].updateState();
			yStart++;
		}

	}
}
