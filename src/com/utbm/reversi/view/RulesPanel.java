package com.utbm.reversi.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RulesPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7688280593711264759L;
	

	private Image actualImage;
	
	private Image reversiRules;
	private Image colorBomb;
	private Image shield;
	private Image switchOwner;
	private Image yellowJacket;
	private Image flintnSteel;
	private Image lightning;
	

	public RulesPanel() 
	{
		try 
		{
			 this.reversiRules = ImageIO.read(new File("data\\reversiRulesImage.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}
		
		try 
		{
			 this.colorBomb = ImageIO.read(new File("data\\colorBombRulesImage.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}

		
		try 
		{
			 this.shield = ImageIO.read(new File("data\\shieldRulesImage.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}

		
		try 
		{
			 this.switchOwner = ImageIO.read(new File("data\\switchRulesImage.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}

		
		try 
		{
			 this.yellowJacket = ImageIO.read(new File("data\\yellowJacketRulesImage.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}

		
		try 
		{
			 this.flintnSteel = ImageIO.read(new File("data\\flintnSteelRulesImage.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}

		
		try 
		{
			 this.lightning = ImageIO.read(new File("data\\lightningRulesImage.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}
		
		
		
		
		this.actualImage = this.reversiRules;
	}
	
	public void paintComponent(Graphics g) 
	{
		g.drawImage(this.actualImage,0,0,null);
	}
	
	public void setImage(String name) 
	{
		switch (name) 
		{
			case "Reversi":
				this.actualImage = this.reversiRules;
				break;
			case "Color Bomb":
				this.actualImage = this.colorBomb;
				break;
			case "Shield":
				this.actualImage = this.shield;
				break;
			case "Switch":
				this.actualImage = this.switchOwner;
				break;
			case "Yellow Jacket":
				this.actualImage = this.yellowJacket;
				break;
			case "Flint n Steel":
				this.actualImage = this.flintnSteel;
				break;
			case "Lightning":
				this.actualImage = this.lightning;
				break;
		}
	}
}
