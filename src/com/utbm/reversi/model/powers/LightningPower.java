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



public class LightningPower extends Power {
	
	private ArrayList<Cell> lightningCell;
	private ArrayList<PowerAnimation> animations;

	public LightningPower(Player owner, ImageIcon icon, Sprite sprite) {
		super(owner,icon,sprite,3);
		this.lightningCell = new ArrayList<Cell>();
		this.animations = new ArrayList<PowerAnimation>();
	}
	public LightningPower(Player owner, String icon, Sprite sprite) {
		super(owner,icon,sprite,3);
		this.lightningCell = new ArrayList<Cell>();
		this.animations = new ArrayList<PowerAnimation>();
	}
	
	@Override
	public boolean use(Game game, Cell cell) {
		this.setOriginCell(cell);
		int xStart = cell.getCoordX();
		int yStart = cell.getCoordY();
		int saveY=yStart;
		int saveX=xStart;
		this.lightningCell.add(cell);
		if (cell.getCoordX()>0 && cell.getCoordY()>0) {
			xStart--;
			yStart--;
		}
		while(xStart<=cell.getCoordX()+1 && xStart<game.getBoard().getSize() && yStart<=cell.getCoordY()+1 && yStart<game.getBoard().getSize()) {
			if(game.getBoard().getBoardCells()[xStart][yStart].isEnabled()&&game.getBoard().getBoardCells()[xStart][yStart].isLock()) {
				game.getBoard().getBoardCells()[xStart][yStart].setOwner(this.getOwner());
				this.animations.add(game.getBoard().getBoardCells()[xStart][yStart].addHoverAnimation(this.getSprite()));
				game.getBoard().getBoardCells()[xStart][yStart].updateState();
				this.lightningCell.add(game.getBoard().getBoardCells()[xStart][yStart]);				
			}
			xStart++;
			yStart++;
		}
		
		xStart=saveX;
		yStart=saveY;
		if (cell.getCoordX()>0 && cell.getCoordY()<game.getBoard().getSize()-1) {
			xStart--;
		}
		if(cell.getCoordX()>0 && cell.getCoordY()<game.getBoard().getSize()-1) {
			yStart++;
		}
		
		while(xStart<=cell.getCoordX()+1 && xStart<game.getBoard().getSize() && yStart>=cell.getCoordY()-1 && yStart>=0){
			if(game.getBoard().getBoardCells()[xStart][yStart].isEnabled()&&game.getBoard().getBoardCells()[xStart][yStart].isLock()) {
				game.getBoard().getBoardCells()[xStart][yStart].setOwner(this.getOwner());
				this.animations.add(game.getBoard().getBoardCells()[xStart][yStart].addHoverAnimation(this.getSprite()));
				game.getBoard().getBoardCells()[xStart][yStart].updateState();
				this.lightningCell.add(game.getBoard().getBoardCells()[xStart][yStart]);				
			}
			xStart++;
			yStart--;
		}
		
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
		
		for(Cell cell : lightningCell) {
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
					
					if(xStart != this.getOriginCell().getCoordX() || yStart!=this.getOriginCell().getCoordY() ) {
						float r = rand.nextInt(20) ;
						if( r <= 10) {	
							if(xStart>=0 && yStart>=0) {
								if(game.getBoard().getBoardCells()[xStart][yStart].isEnabled()&&game.getBoard().getBoardCells()[xStart][yStart].isLock()) {
									game.getBoard().getBoardCells()[xStart][yStart].setOwner(this.getOwner());
									this.animations.add(game.getBoard().getBoardCells()[xStart][yStart].addHoverAnimation(this.getSprite()));										
									game.getBoard().getBoardCells()[xStart][yStart].updateState();
									toAdd.add(game.getBoard().getBoardCells()[xStart][yStart]);																					
								}
							}
						}
					}
					yStart++;
				}
				xStart++;
			}
		}
		this.lightningCell.addAll(toAdd);
		toAdd.clear();
		
		
	}
	@Override
	public void stop(Game game) {
		for(PowerAnimation animation : this.animations) {
			animation.stop();
		}
		for(Cell cell : this.lightningCell) {
			cell.updateState();
		}
		this.lightningCell.clear();
	}

}
