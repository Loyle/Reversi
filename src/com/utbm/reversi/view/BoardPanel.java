package com.utbm.reversi.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel
{

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
				g.fillRect(100 + i*(50+5), 100 + j*(50+5), 50, 50);
			}
		}
		
	}
}
