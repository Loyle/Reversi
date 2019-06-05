package com.utbm.reversi.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.utbm.reversi.controller.BoardController;
import com.utbm.reversi.model.cells.Cell;

public class BoardView extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8514403723481387196L;
	
	private final BoardController reversiController = new BoardController(this);
	private Cell cellArray[][];
	
	private final JLabel label1 = new JLabel("Tour des :");
	private final JPanel whoPlay = new JPanel();
	
	private final int cellSize = 70;
	private final int scoresSizeX = 100;
	private final int gridSize = 8;
	
	
	public BoardView() 
	{
		final JPanel game = new JPanel();
        game.setBackground(Color.black);
        game.setLayout(new GridLayout(gridSize,gridSize,1,1));
        
        cellArray = new Cell[gridSize][gridSize];
        
        for (int i=0; i<gridSize ; i++) 
        {
        	for (int j=0;j<gridSize;j++) 
        	{
        		final Cell cell = new Cell();
        		cell.setBackground(Color.green);
        		cell.addActionListener(e -> reversiController.onCellClicked(cell));
        		cell.setCoordX(j);
        		cell.setCoordY(i);
        		cellArray[j][i] = cell;
        		
        		if ((i > -2 + gridSize/2) && (i < 1 + gridSize/2) && (j > -2 + gridSize/2) && (j < 1 + gridSize/2)) 
        		{
        			if ((i == -1 + gridSize/2) && (j == -1 + gridSize/2)) 
        			{
            			cell.setState(2);
        			}
        			else if ((i == gridSize/2) && (j == gridSize/2)) 
        			{
            			cell.setState(2);
        			}
        			else 
        			{
            			cell.setState(1);
        			}
        		}
        		game.add(cell);
        		cell.updateState();
        	}
        }
        
        
        
        
        
        final JPanel scores = new JPanel();
        scores.setBackground(Color.gray);
        scores.setLayout(new GridBagLayout());
        scores.setPreferredSize(new Dimension(scoresSizeX,500));
        GridBagConstraints gbc = new GridBagConstraints();
        
        this.whoPlay.setPreferredSize(new Dimension(50,50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        scores.add(whoPlay,gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        scores.add(label1,gbc);
        
        
        
        this.setMinimumSize(new Dimension(13+gridSize*(cellSize+5)+scoresSizeX, 42+gridSize*(cellSize+5)));
        this.getContentPane().add(game, BorderLayout.CENTER);
        this.getContentPane().add(scores, BorderLayout.EAST);
        this.pack();
	}
	

	public void changeWhoPlay()
	{
		if (this.reversiController.getChange() == 1) 
		{
			this.whoPlay.setBackground(Color.black);
		}
		else if (this.reversiController.getChange() == 0) 
		{
			this.whoPlay.setBackground(Color.white);
		}
	}


	public Cell[][] getCellArray() 
	{
		return cellArray;
	}


	public int getGridSize() 
	{
		return gridSize;
	}
	
	
	
	
}
