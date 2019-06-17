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
	
	public void onReversiRulesClicked() 
	{
		this.rulesFrame.getTextExplanation1().setText("Reversi is a strategy board game for two players, played on an 8×8 uncheckered board.");
		this.rulesFrame.getTextExplanation2().setText("There are sixty-four identical game pieces called disks,  which are light on one side and");
		this.rulesFrame.getTextExplanation3().setText("dark on the other.  Players  take  turns  placing  disks  on  the  board with their assigned");
		this.rulesFrame.getTextExplanation4().setText("color facing up.  During  a  play,  any disks of the opponent's  color  that  are in a straight");
		this.rulesFrame.getTextExplanation5().setText("line and bounded by the disk just placed and  another  disk  of the current player's color");
		this.rulesFrame.getTextExplanation6().setText("are turned over to the current  player's  color.  The  object  of  the  game  is  to  have  the");
		this.rulesFrame.getTextExplanation7().setText("most of disks turned to display your color when the last playable empty square is filled. ");
		
		this.rulesFrame.getRulesPanel().setImage("Reversi");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onObstaclesClicked() 
	{
		this.rulesFrame.getTextExplanation1().setText(" ");
		this.rulesFrame.getTextExplanation2().setText("This feature does not exist in the basic Reversi game,  but  allows to vary the parts in");
		this.rulesFrame.getTextExplanation3().setText("adding    a    defined    number    of    obstacles    placed    randomly    on    the    board.");
		this.rulesFrame.getTextExplanation4().setText(" ");
		this.rulesFrame.getTextExplanation5().setText("You can't enclose pawns through an obstacle.");
		this.rulesFrame.getTextExplanation6().setText("You can't place a pawn on an obstacle.");
		this.rulesFrame.getTextExplanation7().setText(" ");
		
		this.rulesFrame.getRulesPanel().setImage("Obstacles");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onTreacherousPawnClicked() 
	{
		this.rulesFrame.getTextExplanation1().setText(" ");
		this.rulesFrame.getTextExplanation2().setText(" ");
		this.rulesFrame.getTextExplanation3().setText(" ");
		this.rulesFrame.getTextExplanation4().setText("A treacherous pawn is a trapped disk.");
		this.rulesFrame.getTextExplanation5().setText("When the opposite player turns it, it turns a line of his disks to your color.");
		this.rulesFrame.getTextExplanation6().setText(" ");
		this.rulesFrame.getTextExplanation7().setText(" ");
		
		this.rulesFrame.getRulesPanel().setImage("Treacherous Pawn");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onBombPawnClicked() 
	{
		this.rulesFrame.getTextExplanation1().setText(" ");
		this.rulesFrame.getTextExplanation2().setText(" ");
		this.rulesFrame.getTextExplanation3().setText(" ");
		this.rulesFrame.getTextExplanation4().setText("A bomb pawn is a trapped disk.");
		this.rulesFrame.getTextExplanation5().setText("When the opposite player turns it, it destroys a square of 3*3 of disks.");
		this.rulesFrame.getTextExplanation6().setText(" ");
		this.rulesFrame.getTextExplanation7().setText(" ");
		
		this.rulesFrame.getRulesPanel().setImage("Bomb Pawn");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onColorBombClicked() 
	{
		this.rulesFrame.getTextExplanation1().setText(" ");
		this.rulesFrame.getTextExplanation2().setText(" ");
		this.rulesFrame.getTextExplanation3().setText(" ");
		this.rulesFrame.getTextExplanation4().setText("When a player uses it, it creates a square of 3x3 pawns of his color.");
		this.rulesFrame.getTextExplanation5().setText(" ");
		this.rulesFrame.getTextExplanation6().setText(" ");
		this.rulesFrame.getTextExplanation7().setText(" ");
		
		this.rulesFrame.getRulesPanel().setImage("Color Bomb");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onShieldClicked() 
	{
		this.rulesFrame.getTextExplanation1().setText(" ");
		this.rulesFrame.getTextExplanation2().setText(" ");
		this.rulesFrame.getTextExplanation3().setText(" ");
		this.rulesFrame.getTextExplanation4().setText("A pawn with a shield on it can't turn over to another player's color.");
		this.rulesFrame.getTextExplanation5().setText(" ");
		this.rulesFrame.getTextExplanation6().setText(" ");
		this.rulesFrame.getTextExplanation7().setText(" ");
		
		this.rulesFrame.getRulesPanel().setImage("Shield");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onSwitchOwnerClicked() 
	{
		this.rulesFrame.getTextExplanation1().setText(" ");
		this.rulesFrame.getTextExplanation2().setText(" ");
		this.rulesFrame.getTextExplanation3().setText(" ");
		this.rulesFrame.getTextExplanation4().setText("A switch allows you to turn over one pawn into your color.");
		this.rulesFrame.getTextExplanation5().setText(" ");
		this.rulesFrame.getTextExplanation6().setText(" ");
		this.rulesFrame.getTextExplanation7().setText(" ");
		
		this.rulesFrame.getRulesPanel().setImage("Switch");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onYellowJacketClicked() 
	{
		this.rulesFrame.getTextExplanation1().setText(" ");
		this.rulesFrame.getTextExplanation2().setText(" ");
		this.rulesFrame.getTextExplanation3().setText(" ");
		this.rulesFrame.getTextExplanation4().setText("You can't place a pawn on a yellow jacket.");
		this.rulesFrame.getTextExplanation5().setText("A pawn with a yellow jacket on it can't turn over to another player's color.");
		this.rulesFrame.getTextExplanation6().setText(" ");
		this.rulesFrame.getTextExplanation7().setText(" ");
		
		this.rulesFrame.getRulesPanel().setImage("Yellow Jacket");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onFireClicked() 
	{
		this.rulesFrame.getTextExplanation1().setText(" ");
		this.rulesFrame.getTextExplanation2().setText(" ");
		this.rulesFrame.getTextExplanation3().setText(" ");
		this.rulesFrame.getTextExplanation4().setText("Pawns that are on fire are destoyed after a certain amount of time.");
		this.rulesFrame.getTextExplanation5().setText("The fire can expand from where it started.");
		this.rulesFrame.getTextExplanation6().setText(" ");
		this.rulesFrame.getTextExplanation7().setText(" ");
		
		this.rulesFrame.getRulesPanel().setImage("Fire");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	public void onLightningClicked() 
	{
		this.rulesFrame.getTextExplanation1().setText(" ");
		this.rulesFrame.getTextExplanation2().setText(" ");
		this.rulesFrame.getTextExplanation3().setText(" ");
		this.rulesFrame.getTextExplanation4().setText("The lightning places 5 pawns in a X formation.");
		this.rulesFrame.getTextExplanation5().setText("If there is already a pawn, the new pawn replaces it.");
		this.rulesFrame.getTextExplanation6().setText(" ");
		this.rulesFrame.getTextExplanation7().setText(" ");
		
		this.rulesFrame.getRulesPanel().setImage("Lightning");
		this.rulesFrame.getRulesPanel().repaint();
	}
	
	
}
