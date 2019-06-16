package com.utbm.reversi.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.utbm.reversi.controller.MenuController;
import com.utbm.reversi.controller.RulesController;

public class RulesFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8461744358975503772L;
	private JPanel buttonPanel;
	private JPanel rulesPanel;

	private final RulesController menuController = new RulesController(this);

	public RulesFrame() 
	{
		this.setTitle("Rules - Reversi Game");
		this.setMinimumSize(new Dimension(400,400));
		this.setSize(400, 400);
		this.setVisible(true);
		
		
		
		this.buttonPanel = new JPanel();
		this.rulesPanel = new JPanel();
		
		this.getContentPane().add(this.buttonPanel,BorderLayout.EAST);
		this.getContentPane().add(this.rulesPanel,BorderLayout.CENTER);
	}
}
