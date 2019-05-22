package com.utbm.reversi.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel
{
	final static int cellSize = 70;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7610023389149808384L;
	public void paintComponent(Graphics g) 
	{
		g.setColor(Color.green);
		
		for (int i=0;i<8;i++) 
		{
			for (int j=0;j<8;j++) 
			{
				g.fillRect(100 + i*(cellSize+5), 80 + j*(cellSize+5), cellSize, cellSize);
			}
		}
		
	}
}
