package com.utbm.reversi.model.cells;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.utbm.reversi.model.Player;

@SuppressWarnings("serial")
public class Cell extends JButton {
	private int value;
	
	private Player owner;
	private Color color;
	private Color defaultColor;
	private boolean isLock;
	private ImageIcon icon;
	private ImageIcon hoverIcon;
	
	//private Pawn pawn;
	
	private int coordX;
	private int coordY;
	
	public Cell() {
		this.value = 0;
		this.color = Color.GREEN;
		this.defaultColor = this.color;
		this.owner = null;
		this.isLock = true;
		this.setBackground(this.defaultColor);
		this.icon = null;
		this.hoverIcon = null;
				
		this.setContentAreaFilled(false);
	}
	public Cell(Color color) {
		this.value = 0;
		this.color = color;
		this.defaultColor = this.color;
		this.owner = null;
		this.isLock = true;
		this.setBackground(this.defaultColor);
		this.icon = null;
		this.hoverIcon = null;

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
	public ImageIcon getHoverIcon() {
		return hoverIcon;
	}
	/**
	 * @param hoverIcon the hoverIcon to set
	 */
	public void setHoverIcon(ImageIcon hoverIcon) {
		this.hoverIcon = hoverIcon;
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
		
		if(this.isEnabled()) {
			g2d.setColor(this.defaultColor);
			g2d.fillRect(0, 0, this.getWidth(), getHeight());
		}
		else {
			// Cell is disable
			g2d.setColor(new Color(120,120,120));
			g2d.fillRect(0, 0, this.getWidth(), getHeight());
		}
		if(this.owner != null) {			
			g2d.setColor(this.color);
			g2d.fillOval(10, 10, this.getWidth() - 20, this.getHeight() - 20);
		}
		
		if(this.hoverIcon != null) {
			g2d.drawImage(this.hoverIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
