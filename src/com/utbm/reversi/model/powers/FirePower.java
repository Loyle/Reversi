package com.utbm.reversi.model.powers;

import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import com.utbm.reversi.animation.PowerAnimation;
import com.utbm.reversi.animation.Sprite;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class FirePower extends Power {
	
	private ArrayList<Cell> burningCell;
	/**
	 * Create a new FirePower
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 */
	public FirePower(Player owner, ImageIcon icon, Sprite sprite) {
		super(owner,icon,sprite,3);
		this.burningCell = new ArrayList<Cell>();
	}
	/**
	 * Create a new FirePower
	 * @param owner cell owner
	 * @param icon PowerBar icon
	 * @param sprite Board icon
	 */
	public FirePower(Player owner, String icon, Sprite sprite) {
		super(owner,icon,sprite,3);
		this.burningCell = new ArrayList<Cell>();
	}
	
	@Override
	/**
	 * a cell in fire, sets a probability that one of the surrounding cells becomes in fire, during 3 turns after it is extinguished.
	 * @param game  
	 * @param cell clickedCell
	 * @return boolean
	 */
	public boolean use(Game game, Cell cell) {

		if(cell.isEnabled()) {
			this.setOriginCell(cell);
			this.burningCell.add(cell);
			cell.setLock(true);		
			cell.addHoverAnimation(this.getSprite());
			
			cell.updateState();
			game.getFrame().setCursor(Cursor.getDefaultCursor());
			return true;			
		}else {
			return false;
		}
	}

	
	@Override
	/**
	 * Decrease the duration number and apply power own effect
	 * @param game
	 * @return void 
	 */
	public void next(Game game) {
		this.setDuration(this.getDuration()-1);
		
		if(this.getDuration()>0) {
			
			ArrayList<Cell> toAdd = new ArrayList<Cell>();
			Random rand = new Random();
			int xStart = 0;
			int yStart = 0;
			
			for(Cell cell : burningCell) {
				xStart = cell.getCoordX();
				yStart = cell.getCoordY();
				if (this.getOriginCell().getCoordX()>0) {
					xStart--;
				}
				if (this.getOriginCell().getCoordY()>0) {
					yStart--;
				}
				
				int saveY = yStart;
				
				while ( xStart<=this.getOriginCell().getCoordX()+1 && xStart<game.getBoard().getSize()) {
					yStart= saveY;
					while ( yStart<=this.getOriginCell().getCoordY()+1 && yStart<game.getBoard().getSize()) {
						
						if(xStart != this.getOriginCell().getCoordX()|| yStart!=this.getOriginCell().getCoordY() ) {
							float r = rand.nextInt(100) ;
							if( r <= 10) {	
								if(xStart>=0 && yStart>=0 && game.getBoard().getBoardCells()[xStart][yStart].isEnabled()) {
									game.getBoard().getBoardCells()[xStart][yStart].setLock(true);
									game.getBoard().getBoardCells()[xStart][yStart].addHoverAnimation(this.getSprite());
									game.getBoard().getBoardCells()[xStart][yStart].updateState();
									toAdd.add(game.getBoard().getBoardCells()[xStart][yStart]);								
								}
							}
						}
						yStart++;
					}
					xStart++;
				}
			}
			
			this.burningCell.addAll(toAdd);
			toAdd.clear();
		}
	}
	
	
	@Override
	/**
	 * Stop the power effect 
	 * @param game
	 * @return void 
	 */
	public void stop(Game game) {
		ArrayList<PowerAnimation> toDelete = new ArrayList<PowerAnimation>();
		for(Cell cell : this.burningCell) {
			cell.setLock(false);
			cell.clearOwner();
			for(PowerAnimation animation : cell.getHoverAnimations()) {
				toDelete.add(animation);
			}
		}
		
		for(PowerAnimation animation : toDelete) {
			animation.stop();
		}
		
		for(Cell cell : this.burningCell) {
			cell.updateState();			
		}
		
		this.burningCell.clear();
	}
	
}
