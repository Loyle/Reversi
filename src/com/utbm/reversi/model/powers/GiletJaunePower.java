package com.utbm.reversi.model.powers;

import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.utbm.reversi.animation.PowerAnimation;
import com.utbm.reversi.animation.Sprite;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class GiletJaunePower extends Power {
	
	private ArrayList<PowerAnimation> animations;
	private Sprite fullJacketSprite = new Sprite("./data/GiletJaune_Logo.png",1,1,1000,100,100);
	
	/**
	 * Generate a new GiletJaunePower
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 */
	public GiletJaunePower(Player owner, ImageIcon icon, Sprite sprite) {
		super(owner, icon, sprite, 5);
		this.animations = new ArrayList<PowerAnimation>();
	}
	/**
	 * Generate a new GiletJaunePower
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 */
	public GiletJaunePower(Player owner, String icon, Sprite sprite) {
		super(owner, icon, sprite, 5);
		this.animations = new ArrayList<PowerAnimation>();
	}

	@Override
	/**
	 * Block 5 cells with a cross-design 
	 * @param game 
	 * @param cell clickedCell
	 * @return boolean
	 * 
	 */
	public boolean use(Game game, Cell cell) {

		if(cell.isLock()) {
			return false;
		}else {
			this.setOriginCell(cell);
			int xStart = cell.getCoordX();
			int yStart = cell.getCoordY();
			if (cell.getCoordX() > 0) {
				xStart--;
			}
			
			while (xStart <= cell.getCoordX() + 1 && xStart < game.getBoard().getSize()) {
				if (game.getBoard().getBoardCells()[xStart][yStart].isEnabled()&& !game.getBoard().getBoardCells()[xStart][yStart].isLock()) {
					game.getBoard().getBoardCells()[xStart][yStart].setLock(true);
					System.out.println(game.getBoard().getBoardCells()[xStart][yStart].isLock());
					if (game.getBoard().getBoardCells()[xStart][yStart].getOwner() != null) {
						this.animations.add(game.getBoard().getBoardCells()[xStart][yStart].addHoverAnimation(this.getSprite()));					
					} else {
						this.animations.add(game.getBoard().getBoardCells()[xStart][yStart].addHoverAnimation(this.fullJacketSprite));
					}
					game.getBoard().getBoardCells()[xStart][yStart].updateState();
				}
				xStart++;
			}
			
			if (cell.getCoordY() > 0) {
				yStart--;
			}
			xStart = cell.getCoordX();
			
			while (yStart <= cell.getCoordY() + 1 && yStart < game.getBoard().getSize()) {
				if (game.getBoard().getBoardCells()[xStart][yStart].isEnabled()&& !game.getBoard().getBoardCells()[xStart][yStart].isLock()) {
					game.getBoard().getBoardCells()[xStart][yStart].setLock(true);
					if (game.getBoard().getBoardCells()[xStart][yStart].getOwner() != null ) {
						this.animations.add(game.getBoard().getBoardCells()[xStart][yStart].addHoverAnimation(this.getSprite()));
					} else {
						this.animations.add(game.getBoard().getBoardCells()[xStart][yStart].addHoverAnimation(this.fullJacketSprite));
					}
					game.getBoard().getBoardCells()[xStart][yStart].updateState();

				}
				yStart++;
			}
			
			game.getFrame().setCursor(Cursor.getDefaultCursor());
			return true;
		}
	}

	@Override
	/**
	 * Decrease the duration 
	 * @param game
	 */
	public void next(Game game) {
		this.setDuration(this.getDuration() - 1);
	}

	@Override
	/**
	 * Unblock the previous blocked cell
	 * @param game
	 * 
	 */
	public void stop(Game game) {
		for(PowerAnimation animation : this.animations) {
			animation.stop();
		}
		
		int xStart = this.getOriginCell().getCoordX();
		int yStart = this.getOriginCell().getCoordY();
		if (this.getOriginCell().getCoordX() > 0) {
			xStart--;
		}

		while (xStart <= this.getOriginCell().getCoordX() + 1 && xStart < game.getBoard().getSize()) {
			game.getBoard().getBoardCells()[xStart][yStart].setLock(false);
			game.getBoard().getBoardCells()[xStart][yStart].updateState();
			xStart++;
		}

		if (this.getOriginCell().getCoordY() > 0) {
			yStart--;
		}
		xStart = this.getOriginCell().getCoordX();

		while (yStart <= this.getOriginCell().getCoordY() + 1 && yStart < game.getBoard().getSize()) {
			game.getBoard().getBoardCells()[xStart][yStart].setLock(false);
			game.getBoard().getBoardCells()[xStart][yStart].updateState();
			yStart++;
		}
	}
}
