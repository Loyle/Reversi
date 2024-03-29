package com.utbm.reversi.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.utbm.reversi.controller.MenuController;
import com.utbm.reversi.controller.RulesController;


/**
 * <b>RulesFrame is the frame class of the "rules" (MVC pattern).</b>
 * <p>
 * RulesFrame is defined by :
 * <ul>
 * <li>A "rules" controller (MVC pattern).</li>
 * <li>A panel which display the rules with text.</li>
 * <li>A panel which display the rules with an image.</li>
 * <li>A panel to choose which rule the user wants to know.</li>
 * </ul>
 * </p>
 * 
 * @see RulesController
 * @see RulesPanel
 */
public class RulesFrame extends JFrame
{

	
	private static final long serialVersionUID = 8461744358975503772L;
	
	private JPanel buttonPanel;
	private JPanel explanationPanel;
	private JPanel textRulesPanel;
	private JPanel imageRulesPanel;
	
	private JLabel textExplanation1;
	private JLabel textExplanation2;
	private JLabel textExplanation3;
	private JLabel textExplanation4;
	private JLabel textExplanation5;
	private JLabel textExplanation6;
	private JLabel textExplanation7;
	
	private RulesPanel rulesPanel;
	
	

	private final RulesController rulesController = new RulesController(this);

	
	
	/**
	    * RulesFrame constructor.
	    * RulesFrame is defined by :
	    * <ul>
	    * <li>A panel which display the rules with text.</li>
	    * <li>A panel which display the rules with an image.</li>
	    * <li>A panel to choose which rule the user wants to know.</li>
	    * </ul>
	    * <p>
	    * At the construction, the window is first created.
	    * Then, all the choices are graphically set("reversi game rule" by default).
	    * All buttons are linked to the RulesController.
	    * </p>
	    * 
	    * @see MenuController
	    */
	public RulesFrame() 
	{
		this.setTitle("Rules - Reversi Game");
		this.setMinimumSize(new Dimension(700,700));
		this.setSize(700, 700);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		

		// BUTTON PANEL
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(12,1));
		
		JButton reversiRules = new JButton("Reversi");
		JPanel space1 = new JPanel();
		space1.setBackground(Color.white);
		JButton obstacles = new JButton("Obstacles");
		JButton treacherousPawn = new JButton("Treacherous Pawn");
		JButton bombPawn = new JButton("Bomb Pawn");
		JPanel space2 = new JPanel();
		space2.setBackground(Color.white);
		JButton colorBomb = new JButton("Color Bomb");
		JButton shield = new JButton("Shield");
		JButton switchOwner = new JButton("Switch");
		JButton yellowJacket = new JButton("Yellow Jacket");
		JButton fire = new JButton("Fire");
		JButton lightning = new JButton("Lightning");
		
		reversiRules.addActionListener(e -> rulesController.onReversiRulesClicked());
		obstacles.addActionListener(e -> rulesController.onObstaclesClicked());
		treacherousPawn.addActionListener(e -> rulesController.onTreacherousPawnClicked());
		bombPawn.addActionListener(e -> rulesController.onBombPawnClicked());
		reversiRules.addActionListener(e -> rulesController.onReversiRulesClicked());
		colorBomb.addActionListener(e -> rulesController.onColorBombClicked());
		shield.addActionListener(e -> rulesController.onShieldClicked());
		switchOwner.addActionListener(e -> rulesController.onSwitchOwnerClicked());
		yellowJacket.addActionListener(e -> rulesController.onYellowJacketClicked());
		fire.addActionListener(e -> rulesController.onFireClicked());
		lightning.addActionListener(e -> rulesController.onLightningClicked());
		
		this.buttonPanel.add(reversiRules);
		this.buttonPanel.add(space1);
		this.buttonPanel.add(obstacles);
		this.buttonPanel.add(treacherousPawn);
		this.buttonPanel.add(bombPawn);
		this.buttonPanel.add(space2);
		this.buttonPanel.add(colorBomb);
		this.buttonPanel.add(shield);
		this.buttonPanel.add(switchOwner);
		this.buttonPanel.add(yellowJacket);
		this.buttonPanel.add(fire);
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
		this.textExplanation1 = new JLabel(" ");
		this.textRulesPanel.add(this.textExplanation1,gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		this.textExplanation2 = new JLabel(" ");
		this.textRulesPanel.add(this.textExplanation2,gbc);
		gbc.gridx=0;
		gbc.gridy=2;
		this.textExplanation3 = new JLabel(" ");
		this.textRulesPanel.add(this.textExplanation3,gbc);
		gbc.gridx=0;
		gbc.gridy=3;
		this.textExplanation4 = new JLabel(" ");
		this.textRulesPanel.add(this.textExplanation4,gbc);
		gbc.gridx=0;
		gbc.gridy=4;
		this.textExplanation5 = new JLabel(" ");
		this.textRulesPanel.add(this.textExplanation5,gbc);
		gbc.gridx=0;
		gbc.gridy=5;
		this.textExplanation6 = new JLabel(" ");
		this.textRulesPanel.add(this.textExplanation6,gbc);
		gbc.gridx=0;
		gbc.gridy=6;
		this.textExplanation7 = new JLabel(" ");
		this.textRulesPanel.add(this.textExplanation7,gbc);
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
		
		this.rulesController.onReversiRulesClicked();
		
		
		
		this.getContentPane().add(this.explanationPanel,BorderLayout.CENTER);
		this.getContentPane().add(this.buttonPanel,BorderLayout.WEST);
	}

	/**
     * Get the 1st label which displays explanations.
     * <p>
     * Function called when an other rule button is clicked.
     * </p>
     *            
     * @see RulesController
     */
	public JLabel getTextExplanation1() {
		return textExplanation1;
	}

	/**
     * Get the 2nd label which displays explanations.
     * <p>
     * Function called when an other rule button is clicked.
     * </p>
     *            
     * @see RulesController
     */
	public JLabel getTextExplanation2() {
		return textExplanation2;
	}

	/**
     * Get the 3rd label which displays explanations.
     * <p>
     * Function called when an other rule button is clicked.
     * </p>
     *            
     * @see RulesController
     */
	public JLabel getTextExplanation3() {
		return textExplanation3;
	}

	/**
     * Get the 4th label which displays explanations.
     * <p>
     * Function called when an other rule button is clicked.
     * </p>
     *            
     * @see RulesController
     */
	public JLabel getTextExplanation4() {
		return textExplanation4;
	}

	/**
     * Get the 5th label which displays explanations.
     * <p>
     * Function called when an other rule button is clicked.
     * </p>
     *            
     * @see RulesController
     */
	public JLabel getTextExplanation5() {
		return textExplanation5;
	}

	/**
     * Get the 6th label which displays explanations.
     * <p>
     * Function called when an other rule button is clicked.
     * </p>
     *            
     * @see RulesController
     */
	public JLabel getTextExplanation6() {
		return textExplanation6;
	}

	/**
     * Get the 7th label which displays explanations.
     * <p>
     * Function called when an other rule button is clicked.
     * </p>
     *            
     * @see RulesController
     */
	public JLabel getTextExplanation7() {
		return textExplanation7;
	}

	/**
     * Get the panel which displays explanations with an image.
     * <p>
     * Function called when an other rule button is clicked.
     * </p>
     *            
     * @see RulesController
     * @see RulesPanel
     */
	public RulesPanel getRulesPanel() {
		return rulesPanel;
	}

	
	
	
	
	
}
