package com.utbm.reversi.controller;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.utbm.reversi.view.RulesFrame;

public class RulesController 
{
	private RulesFrame rulesFrame;
	
	public RulesController(RulesFrame rulesFrame) 
	{
		this.rulesFrame = rulesFrame;
	}
	
	public void onReversiRulesClicked(JButton reversiRules) 
	{
		this.rulesFrame.getTextExplanation().setText("Reversi rules");
		this.rulesFrame.getRulesPanel().setImage("Reversi");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onColorBombClicked(JButton colorBomb) 
	{
		this.rulesFrame.getTextExplanation().setText("Color Bomb rules");
		this.rulesFrame.getRulesPanel().setImage("Color Bomb");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onShieldClicked(JButton shield) 
	{
		this.rulesFrame.getTextExplanation().setText("Shield rules");
		this.rulesFrame.getRulesPanel().setImage("Shield");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onSwitchOwnerClicked(JButton switchOwner) 
	{
		this.rulesFrame.getTextExplanation().setText("Switch owner rules");
		this.rulesFrame.getRulesPanel().setImage("Switch");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onYellowJacketClicked(JButton yellowJacket) 
	{
		this.rulesFrame.getTextExplanation().setText("Yellow jacket rules");
		this.rulesFrame.getRulesPanel().setImage("Yellow Jacket");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onFlintnSteelClicked(JButton flintnSteel) 
	{
		this.rulesFrame.getTextExplanation().setText("Flint n Steel rules");
		this.rulesFrame.getRulesPanel().setImage("Flint n Steel");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onLightningClicked(JButton lightning) 
	{
		this.rulesFrame.getTextExplanation().setText("Lightning rules");
		this.rulesFrame.getRulesPanel().setImage("Lightning");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	
}
