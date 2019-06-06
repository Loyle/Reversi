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
	
	private JLabel whiteScoreLabel = new JLabel("Score : 2");
	private int whiteScore=2;
	private final JPanel whiteScorePanel = new JPanel();
	private JLabel blackScoreLabel = new JLabel("Score : 2");
	private int blackScore=2;
	private final JPanel blackScorePanel = new JPanel();
	
	private final int cellSize = 70;
	private final int scoresSizeX = 100;
	private int gridSize;
	
	
	public BoardView(int size) 
	{
		this.gridSize = size;
		
		final JPanel game = new JPanel();
        game.setBackground(Color.black);
        game.setLayout(new GridLayout(this.gridSize,this.gridSize,1,1));
        
        cellArray = new Cell[this.gridSize][this.gridSize];
        
        for (int i=0; i<this.gridSize ; i++) 
        {
        	for (int j=0;j<this.gridSize;j++) 
        	{
        		final Cell cell = new Cell();
        		cell.setBackground(Color.green);
        		cell.addActionListener(e -> reversiController.onCellClicked(cell));
        		cell.setCoordX(j);
        		cell.setCoordY(i);
        		cellArray[j][i] = cell;
        		
        		if ((i > -2 + this.gridSize/2) && (i < 1 + this.gridSize/2) && (j > -2 + this.gridSize/2) && (j < 1 + this.gridSize/2)) 
        		{
        			if ((i == -1 + this.gridSize/2) && (j == -1 + this.gridSize/2)) 
        			{
            			cell.setState(2);
        			}
        			else if ((i == this.gridSize/2) && (j == this.gridSize/2)) 
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
        
        
        
        // WHO WILL PLAY
        gbc.gridx = 0;
        gbc.gridy = 0;
        scores.add(label1,gbc);
        this.whoPlay.setPreferredSize(new Dimension(50,50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        scores.add(whoPlay,gbc);
        
        // ADD SPACE
        for (int addSpace = 2 ; addSpace<15 ; addSpace++) 
        {
            gbc.gridx = 0;
            gbc.gridy = addSpace;
            scores.add(new JLabel(" "),gbc);
        }
        
        
        // WHITE PLAYER SCORE
        gbc.gridx = 0;
        gbc.gridy = 15;
        scores.add(whiteScorePanel,gbc);
        gbc.gridx = 0;
        gbc.gridy = 16;
        scores.add(whiteScoreLabel,gbc);
        this.whiteScorePanel.setPreferredSize(new Dimension(40,40));
        this.whiteScorePanel.setBackground(Color.white);
        
        
        gbc.gridx = 0;
        gbc.gridy = 17;
        scores.add(new JLabel(" "),gbc);
        gbc.gridx = 0;
        gbc.gridy = 18;
        scores.add(new JLabel(" "),gbc);
        

        // BLACK PLAYER SCORE
        gbc.gridx = 0;
        gbc.gridy = 19;
        scores.add(blackScorePanel,gbc);
        gbc.gridx = 0;
        gbc.gridy = 20;
        scores.add(blackScoreLabel,gbc);
        this.blackScorePanel.setPreferredSize(new Dimension(40,40));
        this.blackScorePanel.setBackground(Color.black);
        
        
        
        
        
        this.setMinimumSize(new Dimension(13+this.gridSize*(cellSize+5)+scoresSizeX, 42+this.gridSize*(cellSize+5)));
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
	
	public void setWhiteScore(int whiteScore) {
		this.whiteScore = whiteScore;
	}


	public void setBlackScore(int blackScore) {
		this.blackScore = blackScore;
	}


	public void updateScores() 
	{
		this.blackScoreLabel.setText("Score : "+this.blackScore);
		this.whiteScoreLabel.setText("Score : "+this.whiteScore);
	}
	

	public void setGridSize(int gridSize) 
	{
		this.gridSize = gridSize;
	}
	
}
