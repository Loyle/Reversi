package com.utbm.reversi.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class BoardController extends JPanel implements MouseListener
{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -8620551383872692190L;
	final static int cellSize = 70;

	public void mouseClicked(MouseEvent e)
	{
		for (int j=0;j<8;j++) 
		{
			for (int i=0;i<8;i++) 
			{
				if( (e.getX() > 100 + j*cellSize + j*5) && (e.getX() < 100 + (j+1)*cellSize + j*5)  && (e.getY() > 100 + (i+1)*cellSize + i*5) && (e.getY() < 100 + (i+2)*cellSize + i*5) ) 	
				{
					System.out.println(i+"-"+j);
				}	
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
