package com.utbm.reversi.model.cells;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class OldCell extends JButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 110484099740962045L;
	
	// Coordonnées de la Cell dans le tableau arrayCell
	private int coordX;
	private int coordY;
	
	// État de la Cell : 0 = vert (vide) ; 1 = noir ; 2 = blanc
	private int state;
	
	private Image img;
	private Image backgroundImg;
	private Image blackImg;
	private Image whiteImg;
	
	
	public OldCell() 
	{
		this.state = 0;
		
		try 
		{
		      whiteImg = ImageIO.read(new File("reversiBlanc.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}
		
		try 
		{
		      blackImg = ImageIO.read(new File("reversiNoir.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}
		
		try 
		{
		      backgroundImg = ImageIO.read(new File("reversiBackground.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}

		// Permet de ne pas actualiser juste en survolant
		this.setContentAreaFilled(false);
		
		//CHANGER L'APPARENCE
		//this.setForeground(Color.white);
		//this.setOpaque(false);
		//this.setFocusPainted(false);
		//this.setBorderPainted(false);
		//this.setBorder(null);
		
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
		this.setBackground(Color.green);
		
		if (this.state == 0) 
		{
			this.img = backgroundImg;
		}
		else if (this.state == 1) 
		{
			 this.img = this.blackImg;
		}
		else if (this.state == 2) 
		{
			 this.img = this.whiteImg;
		}
		this.repaint();
	}
	
	 public void paintComponent(Graphics g)
	 {
		  Graphics2D g2d = (Graphics2D)g;
		  g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	 }

}
