package com.utbm.reversi.model.cells;

import java.awt.Color;

//import java.awt.Image;

import javax.swing.JButton;



public class Cell extends JButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 110484099740962045L;
	
	private int coordX;
	private int coordY;
	
	private int state;
	//private Image img;
	
	public Cell() 
	{
		this.state = 0;
		
		/*
		//CHANGER L'APPARENCE
		this.setForeground(Color.white);
		this.setOpaque(false);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setBorder(null);
		*/
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	
	public int getState() 
	{
		return state;
	}

	public void setState(int state) 
	{
		this.state = state;
	}
	
	public void updateState() 
	{
		if (this.state == 1) 
		{
			this.setBackground(Color.white);
		}
		else if (this.state == 2) 
		{
			this.setBackground(Color.black);
		}
		
		
		/*
		if (this.state == 0) 
		{
			try 
			{
			     this.img = ImageIO.read(new File("reversiBlanc.png"));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			try 
			{
			     this.img = ImageIO.read(new File("reversiNoir.png"));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		this.setIcon(new ImageIcon(img));
		*/
	}
	
	
	
	
}
