package com.utbm.reversi.model;
import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.cells.BombCell;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.model.cells.TrapCell;

public class Board {
		private int size;
		private Cell [][] board;
		
		public Board(int size, int obstaclesNumber, int trapsNumber, int playerNumber) {
			this.board = new Cell[size][size];
			this.size=size;
			for (int j=0;j<size;j++ ) {
				for(int i=0;i<size;i++) {
					this.board[i][j]= new Cell(new ImageIcon("./data/grass.png"));	
				}
			}
			

			int incrObstacles = 0;
			int incrTraps = 0;
			
			switch (playerNumber) 
			{
			case 2:
				while (incrObstacles<obstaclesNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && ((randoX <= -2 + size/2 || randoX >= 1 + size/2) || (randoY <= -2 + size/2 || randoY >= 1 + size/2))) 
					{
						this.board[randoX][randoY] = new Cell(new Color(50,50,50));
						this.board[randoX][randoY].addHoverIcon(new ImageIcon("./data/wall.png"));
						this.board[randoX][randoY].setObstacle(true);
						this.board[randoX][randoY].setEnabled(false);
						incrObstacles++;
					}
				}	
				while (incrTraps<trapsNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && this.board[randoX][randoY].isObstacle() == false && ((randoX <= -2 + size/2 || randoX >= 1 + size/2) || (randoY <= -2 + size/2 || randoY >= 1 + size/2))) 
					{
						int whatTrap = new Random().nextInt(1);
						if (whatTrap==0) 
						{
							this.board[randoX][randoY]= new TrapCell(new ImageIcon("./data/grass.png"));
						}
						else 
						{
							this.board[randoX][randoY]= new BombCell(new ImageIcon("./data/grass.png"));
						}
						incrTraps++;
					}
				}
				break;
			case 3:
				while (incrObstacles<obstaclesNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && ((randoX <= -2 + size/2 || randoX >= 2 + size/2) || (randoY <= -2 + size/2 || randoY >= 2 + size/2))) 
					{
						this.board[randoX][randoY] = new Cell(new Color(50,50,50));
						this.board[randoX][randoY].addHoverIcon(new ImageIcon("./data/wall.png"));
						this.board[randoX][randoY].setObstacle(true);
						this.board[randoX][randoY].setEnabled(false);
						incrObstacles++;
					}
				}	
				while (incrTraps<trapsNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && this.board[randoX][randoY].isObstacle() == false && ((randoX <= -2 + size/2 || randoX >= 2 + size/2) || (randoY <= -2 + size/2 || randoY >= 2 + size/2))) 
					{
						int whatTrap = new Random().nextInt(1);
						if (whatTrap==0) 
						{
							this.board[randoX][randoY]= new TrapCell(new ImageIcon("./data/grass.png"));
						}
						else 
						{
							this.board[randoX][randoY]= new BombCell(new ImageIcon("./data/grass.png"));
						}
						incrTraps++;
					}
				}
				break;
				
			case 4 :
				while (incrObstacles<obstaclesNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && ((randoX <= -3 + size/2 || randoX >= 2 + size/2) || (randoY <= -3 + size/2 || randoY >= 2 + size/2))) 
					{
						this.board[randoX][randoY] = new Cell(new Color(50,50,50));
						this.board[randoX][randoY].addHoverIcon(new ImageIcon("./data/wall.png"));
						this.board[randoX][randoY].setObstacle(true);
						this.board[randoX][randoY].setEnabled(false);
						incrObstacles++;
					}
				}	
				while (incrTraps<trapsNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && this.board[randoX][randoY].isObstacle() == false && ((randoX <= -3 + size/2 || randoX >= 2 + size/2) || (randoY <= -3 + size/2 || randoY >= 2 + size/2))) 
					{
						int whatTrap = new Random().nextInt(1);
						if (whatTrap==0) 
						{
							this.board[randoX][randoY]= new TrapCell(new ImageIcon("./data/grass.png"));
						}
						else 
						{
							this.board[randoX][randoY]= new BombCell(new ImageIcon("./data/grass.png"));
						}
						incrTraps++;
					}
				}
				break;
				
			case 5 :
				while (incrObstacles<obstaclesNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && ((randoX <= -3 + size/2 || randoX >= 3 + size/2) || (randoY <= -3 + size/2 || randoY >= 3 + size/2))) 
					{
						this.board[randoX][randoY] = new Cell(new Color(50,50,50));
						this.board[randoX][randoY].addHoverIcon(new ImageIcon("./data/wall.png"));
						this.board[randoX][randoY].setObstacle(true);
						this.board[randoX][randoY].setEnabled(false);
						incrObstacles++;
					}
				}	
				while (incrTraps<trapsNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && this.board[randoX][randoY].isObstacle() == false && ((randoX <= -3 + size/2 || randoX >= 3 + size/2) || (randoY <= -3 + size/2 || randoY >= 3 + size/2))) 
					{
						int whatTrap = new Random().nextInt(1);
						if (whatTrap==0) 
						{
							this.board[randoX][randoY]= new TrapCell(new ImageIcon("./data/grass.png"));
						}
						else 
						{
							this.board[randoX][randoY]= new BombCell(new ImageIcon("./data/grass.png"));
						}
						incrTraps++;
					}
				}
				break;
			case 6 :
				while (incrObstacles<obstaclesNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && ((randoX <= -4 + size/2 || randoX >= 3 + size/2) || (randoY <= -4 + size/2 || randoY >= 3 + size/2))) 
					{
						this.board[randoX][randoY] = new Cell(new Color(50,50,50));
						this.board[randoX][randoY].addHoverIcon(new ImageIcon("./data/wall.png"));
						this.board[randoX][randoY].setObstacle(true);
						this.board[randoX][randoY].setEnabled(false);
						incrObstacles++;
					}
				}	
				while (incrTraps<trapsNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && this.board[randoX][randoY].isObstacle() == false && ((randoX <= -4 + size/2 || randoX >= 3 + size/2) || (randoY <= -4 + size/2 || randoY >= 3 + size/2))) 
					{
						int whatTrap = new Random().nextInt(1);
						if (whatTrap==0) 
						{
							this.board[randoX][randoY]= new TrapCell(new ImageIcon("./data/grass.png"));
						}
						else 
						{
							this.board[randoX][randoY]= new BombCell(new ImageIcon("./data/grass.png"));
						}
						incrTraps++;
					}
				}
			case 7 :
				while (incrObstacles<obstaclesNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && ((randoX <= -4 + size/2 || randoX >= 4 + size/2) || (randoY <= -4 + size/2 || randoY >= 4 + size/2))) 
					{
						this.board[randoX][randoY] = new Cell(new Color(50,50,50));
						this.board[randoX][randoY].addHoverIcon(new ImageIcon("./data/wall.png"));
						this.board[randoX][randoY].setObstacle(true);
						this.board[randoX][randoY].setEnabled(false);
						incrObstacles++;
					}
				}	
				while (incrTraps<trapsNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && this.board[randoX][randoY].isObstacle() == false && ((randoX <= -4 + size/2 || randoX >= 4 + size/2) || (randoY <= -4 + size/2 || randoY >= 4 + size/2))) 
					{
						int whatTrap = new Random().nextInt(1);
						if (whatTrap==0) 
						{
							this.board[randoX][randoY]= new TrapCell(new ImageIcon("./data/grass.png"));
						}
						else 
						{
							this.board[randoX][randoY]= new BombCell(new ImageIcon("./data/grass.png"));
						}
						incrTraps++;
					}
				}
				break;
			case 8 :
				while (incrObstacles<obstaclesNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && ((randoX <= -5 + size/2 || randoX >= 4 + size/2) || (randoY <= -5 + size/2 || randoY >= 4 + size/2))) 
					{
						this.board[randoX][randoY] = new Cell(new Color(50,50,50));
						this.board[randoX][randoY].addHoverIcon(new ImageIcon("./data/wall.png"));
						this.board[randoX][randoY].setObstacle(true);
						this.board[randoX][randoY].setEnabled(false);
						incrObstacles++;
					}
				}	
				while (incrTraps<trapsNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && this.board[randoX][randoY].isObstacle() == false && ((randoX <= -5 + size/2 || randoX >= 4 + size/2) || (randoY <= -5 + size/2 || randoY >= 4 + size/2))) 
					{
						int whatTrap = new Random().nextInt(1);
						if (whatTrap==0) 
						{
							this.board[randoX][randoY]= new TrapCell(new ImageIcon("./data/grass.png"));
						}
						else 
						{
							this.board[randoX][randoY]= new BombCell(new ImageIcon("./data/grass.png"));
						}
						incrTraps++;
					}
				}
				break;
			case 9 :
				while (incrObstacles<obstaclesNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && ((randoX <= -5 + size/2 || randoX >= 5 + size/2) || (randoY <= -5 + size/2 || randoY >= 5 + size/2))) 
					{
						this.board[randoX][randoY] = new Cell(new Color(50,50,50));
						this.board[randoX][randoY].addHoverIcon(new ImageIcon("./data/wall.png"));
						this.board[randoX][randoY].setObstacle(true);
						this.board[randoX][randoY].setEnabled(false);
						incrObstacles++;
					}
				}	
				while (incrTraps<trapsNumber) 
				{
					int randoX = new Random().nextInt(size);
					int randoY = new Random().nextInt(size);

					if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner()==null && this.board[randoX][randoY].isObstacle() == false && ((randoX <= -5 + size/2 || randoX >= 5 + size/2) || (randoY <= -5 + size/2 || randoY >= 5 + size/2))) 
					{
						int whatTrap = new Random().nextInt(1);
						if (whatTrap==0) 
						{
							this.board[randoX][randoY]= new TrapCell(new ImageIcon("./data/grass.png"));
						}
						else 
						{
							this.board[randoX][randoY]= new BombCell(new ImageIcon("./data/grass.png"));
						}
						incrTraps++;
					}
				}
				break;
			}	
		}
		
		public int getSize() {
			return this.size;
		}
		
		public void setSize(int size) {
			this.size=size;
		}
		
		public Cell[][] getBoardCells() {
			return this.board;
		}
			
}
