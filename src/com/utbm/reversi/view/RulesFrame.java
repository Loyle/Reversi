package com.utbm.reversi.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.utbm.reversi.controller.RulesController;

public class RulesFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8461744358975503772L;
	
	private JPanel buttonPanel;
	private JPanel explanationPanel;
	private JPanel textRulesPanel;
	private JPanel imageRulesPanel;
	private JLabel textExplanation;
	
	private RulesPanel rulesPanel;
	
	

	private final RulesController rulesController = new RulesController(this);

	public RulesFrame() 
	{
		this.setTitle("Rules - Reversi Game");
		this.setMinimumSize(new Dimension(700,700));
		this.setSize(700, 700);
		this.setVisible(true);
		
		

		// BUTTON PANEL
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(7,1));
		
		JButton reversiRules = new JButton("Reversi");
		JButton colorBomb = new JButton("Color Bomb");
		JButton shield = new JButton("Shield");
		JButton switchOwner = new JButton("Switch");
		JButton yellowJacket = new JButton("Yellow Jacket");
		JButton flintnSteel = new JButton("Flint n Steel");
		JButton lightning = new JButton("Lightning");
		
		reversiRules.addActionListener(e -> rulesController.onReversiRulesClicked(reversiRules));
		colorBomb.addActionListener(e -> rulesController.onColorBombClicked(colorBomb));
		shield.addActionListener(e -> rulesController.onShieldClicked(shield));
		switchOwner.addActionListener(e -> rulesController.onSwitchOwnerClicked(switchOwner));
		yellowJacket.addActionListener(e -> rulesController.onYellowJacketClicked(yellowJacket));
		flintnSteel.addActionListener(e -> rulesController.onFlintnSteelClicked(flintnSteel));
		lightning.addActionListener(e -> rulesController.onLightningClicked(lightning));
		
		this.buttonPanel.add(reversiRules);
		this.buttonPanel.add(colorBomb);
		this.buttonPanel.add(shield);
		this.buttonPanel.add(switchOwner);
		this.buttonPanel.add(yellowJacket);
		this.buttonPanel.add(flintnSteel);
		this.buttonPanel.add(lightning);
		

		
		
		// EXPLANATION PANEL
		this.explanationPanel = new JPanel();
		this.explanationPanel.setLayout(new GridLayout(2,1));
		
		// TEXT EXPLANATION
		this.textRulesPanel = new JPanel();
		this.textRulesPanel.setBackground(Color.white);
		this.textRulesPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		this.textExplanation = new JLabel("Reversi rules");
		this.textRulesPanel.add(this.textExplanation,gbc);
		this.explanationPanel.add(this.textRulesPanel);
		
		// IMAGE EXPLANATION
		this.imageRulesPanel = new JPanel();
		this.imageRulesPanel.setBackground(Color.white);
		this.imageRulesPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx=0;
		gbc2.gridy=0;
		this.rulesPanel = new RulesPanel();
		this.rulesPanel.setMinimumSize(new Dimension(400,250));
		this.rulesPanel.setPreferredSize(new Dimension(400,250));
		this.imageRulesPanel.add(this.rulesPanel,gbc2);
		this.explanationPanel.add(this.imageRulesPanel);
		
		
		
		this.getContentPane().add(this.explanationPanel,BorderLayout.CENTER);
		this.getContentPane().add(this.buttonPanel,BorderLayout.WEST);
	}

	public JLabel getTextExplanation() {
		return textExplanation;
	}

	public RulesPanel getRulesPanel() {
		return rulesPanel;
	}

	
	
	
	
	
}
