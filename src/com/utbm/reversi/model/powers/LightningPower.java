package com.utbm.reversi.model.powers;

import javax.swing.ImageIcon;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

public class LightningPower extends Power {

	public LightningPower(Player owner, ImageIcon icon) {
		super(owner,icon,3);
	}
	public LightningPower(Player owner, String icon) {
		super(owner,icon,3);
	}
	
	@Override
	public void use(Game game, Cell cell) {
		this.setOriginCell(cell);
		int xStart = cell.getCoordX(); //1
		int yStart = cell.getCoordY();//1
		int saveY=yStart;//1
		if (cell.getCoordX()>0) {
			xStart--;//0
		}
		if (cell.getCoordY()>0) {
			yStart--;//0
		}
		int saveX=xStart;//0
		while(xStart<=cell.getCoordX()+1 && xStart<game.getBoard().getSize() && yStart<=cell.getCoordY()+1 && yStart<game.getBoard().getSize()) {
			game.getBoard().getBoardCells()[xStart][yStart].setOwner(this.getOwner());
			game.getBoard().getBoardCells()[xStart][yStart].updateState();
			xStart++;
			yStart++;
		}
		// change x et y + check out array
		/*xStart=saveX;
		yStart=saveY+1;
		while(xStart<=cell.getCoordX()+1 && xStart<game.getBoard().getSize() && yStart>=cell.getCoordY()-1 && yStart>=0){
			game.getBoard().getBoardCells()[xStart][yStart].setOwner(this.getOwner());
			game.getBoard().getBoardCells()[xStart][yStart].updateState();
			xStart++;
			yStart--;
		}*/
		
	}
	@Override
	public void next(Game game) {
		this.setDuration(this.getDuration()-1);
		
	}
	@Override
	public void stop(Game game) {
		
		
	}

}
