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
	
	public FirePower(Player owner, ImageIcon icon, Sprite sprite) {
		super(owner,icon,sprite,3);
		this.burningCell = new ArrayList<Cell>();
	}
	
	public FirePower(Player owner, String icon, Sprite sprite) {
		super(owner,icon,sprite,3);
		this.burningCell = new ArrayList<Cell>();
	}
	
	@Override
	public boolean use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 * Use -> une cell en fire , set proba pour que une des cells autour devient en fire 
		 * dure 3-5 tour apr�s s'�teint
		 */
		this.setOriginCell(cell);
		this.burningCell.add(cell);
		cell.setEnabled(false);
		
		cell.addHoverAnimation(this.getSprite());
		
		cell.updateState();
		
		// afficher flamme sur la cell
		game.getFrame().setCursor(Cursor.getDefaultCursor());
		return true;
	}
	@Override
	public void next(Game game) {
		this.setDuration(this.getDuration()-1);
		
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
						float r = rand.nextInt(20) ;
						if( r <= 10) {	
							if(xStart>=0 && yStart>=0) {
								game.getBoard().getBoardCells()[xStart][yStart].setEnabled(false);
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
	
	
	@Override
	public void stop(Game game) {
		for(Cell cell : this.burningCell) {
			cell.setEnabled(true);
			cell.clearOwner();
			for(PowerAnimation animation : cell.getHoverAnimations()) {
				animation.stop();
			}
			cell.getHoverAnimations().clear();
			cell.updateState();
		}
		this.burningCell.clear();
	}
	
}
