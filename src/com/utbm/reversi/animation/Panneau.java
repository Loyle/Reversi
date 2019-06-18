package com.utbm.reversi.animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	
	private int x;
	private int y;
	private Animation anim;
	
	public Panneau(Animation anim) {
		this.anim = anim;
		this.x = anim.getPosX();
		this.y = anim.getPosY();
		
	}
	
	 public void paintComponent(Graphics g ) {
		 	super.paintComponent(g);
		    g.drawImage(this.anim.getSprite(), x, y, null);
		    
		  }
	 

}

