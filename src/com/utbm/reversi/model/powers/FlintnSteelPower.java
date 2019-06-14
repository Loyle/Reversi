package com.utbm.reversi.model.powers;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class FlintnSteelPower extends Power {
	
	private ArrayList<Cell> burningCell;
	
	public FlintnSteelPower(Player owner, ImageIcon icon, ImageIcon hoverIcon) {
		super(owner,icon,hoverIcon,3);
		this.burningCell = new ArrayList<Cell>();
	}
	
	public FlintnSteelPower(Player owner, String icon, String hoverIcon) {
		super(owner,icon,hoverIcon,3);
		this.burningCell = new ArrayList<Cell>();
	}
	
	@Override
	public boolean use(Game game, Cell cell) {
		// TODO Auto-generated method stub
		/*
		 * Use -> une cell en fire , set proba pour que une des cells autour devient en fire 
		 * dure 3-5 tour après s'éteint
		 */
		this.setOriginCell(cell);
		this.burningCell.add(cell);
		cell.setEnabled(false);
		return true;
		// afficher flamme sur la cell
		
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
		for(Cell cell : burningCell) {
			cell.setEnabled(true);
			cell.clearOwner();
		}
		burningCell.clear();
	}
	
}
