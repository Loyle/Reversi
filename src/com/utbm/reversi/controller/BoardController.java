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

	public void mouseClicked(MouseEvent e)
	{
		for (int j=0;j<8;j++) 
		{
			for (int i=0;i<8;i++) 
			{
				if( (e.getX() > 100 + j*50 + j*5) && (e.getX() < 100 + (j+1)*50 + j*5)  && (e.getY() > 100 + (i+1)*50 + i*5) && (e.getY() < 100 + (i+2)*50 + i*5) ) 	
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
