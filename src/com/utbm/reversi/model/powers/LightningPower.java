package com.utbm.reversi.model.powers;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class LightningPower extends Power {
	
	private ArrayList<Cell> lightningCell;

	public LightningPower(Player owner, ImageIcon icon) {
		super(owner,icon,3);
		this.lightningCell = new ArrayList<Cell>();
	}
	public LightningPower(Player owner, String icon) {
		super(owner,icon,3);
		this.lightningCell = new ArrayList<Cell>();
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
			game.getBoard().getBoardCells()[xStart][yStart].setOwner(this.getOwner());
			this.lightningCell.add(game.getBoard().getBoardCells()[xStart][yStart]);
			game.getBoard().getBoardCells()[xStart][yStart].updateState();
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
			game.getBoard().getBoardCells()[xStart][yStart].setOwner(this.getOwner());
			game.getBoard().getBoardCells()[xStart][yStart].updateState();
			this.lightningCell.add(game.getBoard().getBoardCells()[xStart][yStart]);
			xStart++;
			yStart--;
		}
		
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
					
					if(xStart != this.getOriginCell().getCoordX()|| yStart!=this.getOriginCell().getCoordY() ) {
						float r = rand.nextInt(200)/10 ;
						if( r <= 1) {	
							if(xStart>=0 && yStart>=0) {
								game.getBoard().getBoardCells()[xStart][yStart].setOwner(this.getOriginCell().getOwner());
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
		
		
	}
	@Override
	public void stop(Game game) {
		lightningCell.clear();
	}

}
