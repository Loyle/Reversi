package com.utbm.reversi.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.utbm.reversi.controller.RulesController;

/**
 * <b>RulesPanel is the panel that displays rules explanations with images.</b>
 * <p>
 * RulesFrame is defined by :
 * <ul>
 * <li>An actual image (the image that is displayed).</li>
 * <li>All explanations images that are loaded here.</li>
 * </ul>
 * </p>
 * 
 * @see RulesController
 * @see RulesFrame
 */
public class RulesPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7688280593711264759L;
	

	private Image actualImage;
	
	private Image reversiRules;
	private Image obstacles;
	private Image treacherousPawn;
	private Image bombPawn;
	private Image colorBomb;
	private Image shield;
	private Image switchOwner;
	private Image yellowJacket;
	private Image fire;
	private Image lightning;
	
	/**
	    * RulesPanel constructor.
	    * <p>
	    * <li>At the construction, all explanation images are loaded.
	    * <li>The default image is set on "reversi rules image"
	    * </p>
	    * 
	    * @see RulesController
	    * @see RulesFrame
	    */
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
			 this.obstacles = ImageIO.read(new File("data\\obstaclesRulesImage.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}
		
		try 
		{
			 this.treacherousPawn = ImageIO.read(new File("data\\treacherousPawnRulesImage.jpg"));
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}
		
		try 
		{
			 this.bombPawn = ImageIO.read(new File("data\\bombPawnRulesImage.jpg"));
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
			 this.fire = ImageIO.read(new File("data\\fireRulesImage.jpg"));
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
	
	/**
     * Paint the image in the panel.
     * <p>
     * Function called when a new rules button is clicked.
     * </p>
     * 
     * @param g
     *            Graphics element used to draw the image.
     *        
     * @see RulesController    
     * @see RulesPanel
     */
	public void paintComponent(Graphics g) 
	{
		g.drawImage(this.actualImage,0,0,null);
	}
	
	/**
     * Set which image should be displayed.
     * <p>
     * Function called when a new rules button is clicked.
     * </p>
     * 
     * @param name
     *            A string that is used to select the image to display.
     *        
     * @see RulesController    
     * @see RulesPanel
     */
	public void setImage(String name) 
	{
		switch (name) 
		{
			case "Reversi":
				this.actualImage = this.reversiRules;
				break;
			case "Obstacles":
				this.actualImage = this.obstacles;
				break;
			case "Treacherous Pawn":
				this.actualImage = this.treacherousPawn;
				break;
			case "Bomb Pawn":
				this.actualImage = this.bombPawn;
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
			case "Fire":
				this.actualImage = this.fire;
				break;
			case "Lightning":
				this.actualImage = this.lightning;
				break;
		}
	}
}
