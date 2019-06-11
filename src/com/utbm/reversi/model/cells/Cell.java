package com.utbm.reversi.model.cells;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import com.utbm.reversi.model.Player;

@SuppressWarnings("serial")
public class Cell extends JButton {
	private int value;
	
	private Player owner;
	private Color color;
	private Color defaultColor;
	
	//private Pawn pawn;
	
	private int coordX;
	private int coordY;
	
	public Cell() {
		this.value = 0;
		this.color = Color.GREEN;
		this.defaultColor = this.color;
		this.owner = null;
		this.setBackground(this.defaultColor);
		//this.pawn = null;
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
	}
	public Cell(Color color) {
		this.value = 0;
		this.color = color;
		this.defaultColor = this.color;
		this.owner = null;
		this.setBackground(this.defaultColor);
		//this.pawn = null;
		//this.setBorderPainted(false);
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
	
	public void setCoordX(int x) {
		this.coordX = x;
	}
	public int getCoordX() {
		return this.coordX;
	}
	public void setCoordY(int y) {
		this.coordX = y;
	}
	public int getCoordY() {
		return this.coordY;
	}
	
	public void updateState() {
		this.setBackground(this.defaultColor);
		repaint();
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(this.defaultColor);
		g2d.fillRect(0, 0, this.getWidth(), getHeight());
		if(this.owner != null) {			
			g2d.setColor(this.color);
			g2d.fillOval(10, 10, this.getWidth() - 20, this.getHeight() - 20);
		}
	}

	
	
	
	/*public Pawn getPawn() {
	return this.pawn;
	}

	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}*/
}
