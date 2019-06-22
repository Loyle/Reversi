package com.utbm.reversi.model.cells;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.utbm.reversi.animation.PowerAnimation;
import com.utbm.reversi.animation.Sprite;
import com.utbm.reversi.model.Player;

/**
 * 
 * extends @see JButton
 *
 */
@SuppressWarnings("serial")
public class Cell extends JButton {
	private int value;
	
	private Player owner;
	private Color color;
	private Color defaultColor;
	private ImageIcon background;
	private boolean isLock;
	private ArrayList<PowerAnimation> hoverAnimations;
	
	private int coordX;
	private int coordY;
	/**
	 * Generate a new Cell
	 */
	public Cell() {
		this.value = 0;
		this.color = Color.GREEN;
		this.defaultColor = this.color;
		this.owner = null;
		this.isLock = false;
		this.background = null;
		this.hoverAnimations = new ArrayList<PowerAnimation>();		
		this.setContentAreaFilled(false);
	}
	/**
	 * Generate a new Cell with a specific color
	 * @param color cell's color
	 */
	public Cell(Color color) {
		this.value = 0;
		this.color = color;
		this.defaultColor = this.color;
		this.owner = null;
		this.isLock = false;
		this.background = null;
		this.hoverAnimations = new ArrayList<PowerAnimation>();
		this.setContentAreaFilled(false);
	}
	
	/**
	 * Generate a new Cell with a image
	 * @param background image
	 */
	public Cell(ImageIcon background) {
		this.value = 0;
		this.color = null;
		this.background = background;
		this.defaultColor = this.color;
		this.owner = null;
		this.isLock = false;
		this.hoverAnimations = new ArrayList<PowerAnimation>();
		//this.setBorder(BorderFactory.createLineBorder(Color.white,1));
		this.setContentAreaFilled(false);
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @return
	 */
	public Color getDefaultColor() {
		return this.defaultColor;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
		if (this.owner != null) 
		{
			this.color = this.owner.getColor();
		}
	}
	
	public void clearOwner() {
		this.owner = null;
		this.color = this.defaultColor;
	}
	
	public void setCoordX(int x) {
		this.coordX = x;
	}
	public int getCoordX() {
		return this.coordX;
	}
	public void setCoordY(int y) {
		this.coordY = y;
	}
	public int getCoordY() {
		return this.coordY;
	}
	public boolean isLock() {
		return isLock;
	}
	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}
	/**
	 * @return the hoverIcon
	 */
	public ArrayList<PowerAnimation> getHoverAnimations() {
		return this.hoverAnimations;
	}
	/**
	 * Create a thread animation with animation 
	 * @param hoverAnimations power's animation
	 */
	public void addHoverAnimation(PowerAnimation hoverAnimations) {
		Thread thread = new Thread(hoverAnimations);
		thread.start();
		this.hoverAnimations.add(hoverAnimations);
	}
	/**
	 * Create a thread animation with a sprite sheet 
	 * @param sprite image
	 * @return PowerAnimation 
	 */
	public PowerAnimation addHoverAnimation(Sprite sprite) {
		int sizeX = sprite.getSpriteSizeX();
		int sizeY = sprite.getSpriteSizeY();
		BufferedImage[] buffer = new BufferedImage[sizeX * sizeY];
		int i = 0;
		for(int x = 0; x < sizeX; x++) {
			for(int y = 0; y < sizeY; y++) {
				buffer[i] = sprite.getSprite(x, y);
				i++;
			}
		}
		
		PowerAnimation animation = new PowerAnimation(buffer,this,sprite.getDuration(),true);
		Thread thread = new Thread(animation);
		thread.start();
		this.hoverAnimations.add(animation);
		return animation;
	}
	/**
	 * Create a thread repeatable animation with a sprite sheet
	 * @param sprite image 
	 * @param repeat boolean 
	 * @return PowerAnimation
	 */
	public PowerAnimation addHoverAnimation(Sprite sprite, boolean repeat) {
		int sizeX = sprite.getSpriteSizeX();
		int sizeY = sprite.getSpriteSizeY();
		BufferedImage[] buffer = new BufferedImage[sizeX * sizeY];
		int i = 0;
		for(int x = 0; x < sizeY; x++) {
			for(int y = 0; y < sizeX; y++) {
				buffer[i] = sprite.getSprite(y,x);
				i++;
			}
		}
		
		PowerAnimation animation = new PowerAnimation(buffer,this,sprite.getDuration(),repeat);
		Thread thread = new Thread(animation);
		thread.start();
		this.hoverAnimations.add(animation);
		return animation;
	}
	public void removeHoverAnimation(PowerAnimation hoverAnimations) {
		this.hoverAnimations.remove(hoverAnimations);
	}
	
	public void updateState() {
		repaint();
	}
	
	public void setDefaultBackground(ImageIcon img) {
		this.background = img;
	}
	/**
	 * Draw the animation
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g2d.setRenderingHints(rh);
		
		if(this.background != null) {
			g2d.drawImage(this.background.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		}
		else {			
			g2d.setColor(this.defaultColor);
			g2d.fillRect(0, 0, this.getWidth(), getHeight());			
		}

		if(this.owner != null) {			
			g2d.setColor(this.color);
			g2d.fillOval((this.getWidth()- (int)(this.getWidth() * 0.7)) / 2, (this.getHeight()- (int)(this.getHeight() * 0.7)) / 2, (int)(this.getWidth() * 0.7), (int)(this.getHeight() * 0.7));
		}
		
		for(PowerAnimation hoverAnimations : this.hoverAnimations) {
			g2d.drawImage(hoverAnimations.getSprite(), 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
