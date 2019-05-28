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
		/*for (int j=0;j<8;j++) 
		{
			for (int i=0;i<8;i++) 
			{
				if((e.getX() > (-(8*(cellSize+5)))/2+i*(cellSize+5)+2) && (e.getX() < (this.getWidth()-(8*(cellSize+5)))/2+(i+1)*(cellSize+5)+2))
				{
					System.out.println(i);
				}	
			}
		}*/
		
		//System.out.println();
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
