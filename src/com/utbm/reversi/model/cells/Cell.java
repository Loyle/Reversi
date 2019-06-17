package com.utbm.reversi.model.cells;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.utbm.reversi.model.Player;

@SuppressWarnings("serial")
public class Cell extends JButton {
	private int value;
	
	private Player owner;
	private Color color;
	private Color defaultColor;
	private ImageIcon background;
	private boolean isLock;
	private ArrayList<ImageIcon> hoverIcons;
	
	private int coordX;
	private int coordY;
	
	public Cell() {
		this.value = 0;
		this.color = Color.GREEN;
		this.defaultColor = this.color;
		this.owner = null;
		this.isLock = true;
		this.background = null;
		this.hoverIcons = new ArrayList<ImageIcon>();		
		this.setContentAreaFilled(false);
	}
	public Cell(Color color) {
		this.value = 0;
		this.color = color;
		this.defaultColor = this.color;
		this.owner = null;
		this.isLock = true;
		this.background = null;
		this.hoverIcons = new ArrayList<ImageIcon>();
		this.setContentAreaFilled(false);
	}
	public Cell(ImageIcon background) {
		this.value = 0;
		this.color = null;
		this.background = background;
		this.defaultColor = this.color;
		this.owner = null;
		this.isLock = true;
		this.hoverIcons = new ArrayList<ImageIcon>();
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
		this.color = this.owner.getColor();
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
	public ArrayList<ImageIcon> getHoverIcon() {
		return this.hoverIcons;
	}
	/**
	 * @param hoverIcon the hoverIcon to set
	 */
	public void addHoverIcon(ImageIcon hoverIcon) {
		this.hoverIcons.add(hoverIcon);
	}
	public void removeHoverIcon(ImageIcon hoverIcon) {
		this.hoverIcons.remove(hoverIcon);
	}
	
	public void updateState() {
		this.setBackground(this.defaultColor);
		repaint();
	}
	public void paintComponent(Graphics g) {
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
		
		for(ImageIcon hoverIcon : this.hoverIcons) {
			g2d.drawImage(hoverIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
