package com.utbm.reversi.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.utbm.reversi.controller.GameController;
import com.utbm.reversi.listeners.GameListener;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.model.powers.Power;

@SuppressWarnings("serial")
public class ReversiFrame extends JFrame {
	// Stocking Board class who manage the board
	// private Board board;

	// We create a first panel on which the buttons will be displayed (it is a parameter to manage the end of a game)
	private JPanel gamePanel;

	// We create a label and a panel associated with the player's turn (the panel is the box that changes color, the text is "round of:")
	private JLabel label;
	private JPanel whoPlayColor;
	private JLabel whoPlayName;

	// We create the button back to the menu
	private JButton backToMenu;

	// We create 2 constants (the size of a box when generating and the width of the strip on the side where the scores are displayed)
	private int cellSize;
	private int scoresSizeX;
	private int buttonSizeY;

	// The size of the grid is stored in an int, which changes with each generation of a new window
	private int gridSize;

	private GameListener listener;
	private Game game;
	private GameController controller;

	private ArrayList<PowerButton> powerListBtn;

	public ReversiFrame(int size, int powerNumber, int obstaclesNumber, int trapsNumber, ArrayList<Player> players) {
		this.game = new Game(this, size, powerNumber, obstaclesNumber, trapsNumber, players.size());


		for (Player player : players) {
			this.game.addPlayer(player);
		}

		this.controller = new GameController(this.game, this);
		this.listener = new GameListener(this.controller);

		this.gridSize = size;

		this.initWindow();
		this.game.run();

	}

	public void initWindow() {

		if (this.game.getPowerNumber() ==0) 
		{
			this.scoresSizeX = 918;
		}
		else
		{
			this.scoresSizeX = 768;
		}
		this.buttonSizeY=0;
		this.cellSize = 70;
		this.powerListBtn = new ArrayList<PowerButton>();

		// We're picking up the cells from the board.
		Cell [][] cells = this.game.getBoard().getBoardCells();


		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Reversi Game");

		//this.setSize(700, 700);
		this.setLocationRelativeTo(null);

		// ======================================================================================
		// PANEL "game" WHERE ARE CELLS
		// ======================================================================================

		this.gamePanel = new JPanel();
		this.gamePanel.setBackground(Color.black);
		this.gamePanel.setLayout(new GridLayout(this.gridSize,this.gridSize,0,0));

		
		
		// The first pawns are placed in the middle
		int startX = (this.game.getBoard().getSize() / 2) - (this.game.getPlayers().size() / 2);
		int startY = (this.game.getBoard().getSize() / 2) - (this.game.getPlayers().size() / 2) + this.game.getPlayers().size() - 1;
		
		int sizeX = (this.game.getBoard().getSize() / 2) - (this.game.getPlayers().size() / 2) + this.game.getPlayers().size();
		int sizeY = (this.game.getBoard().getSize() / 2) - (this.game.getPlayers().size() / 2);
		
		int pl = 0;
		for (int i = startX; i <= ((this.game.getBoard().getSize() / 2) + (this.game.getPlayers().size() - 2)); i++) {
			int x = i;
			int y = startY;
			
			while (x < sizeX && y >= sizeY) {
				cells[x][y].setOwner(this.game.getPlayers().get(pl));	
				if(cells[x][y].isEnabled()) {
					cells[x][y].setEnabled(true);
					cells[x][y].setDefaultBackground(new ImageIcon("./data/grass.png"));
				}
				x++;
				y--;
			}
			
			if(pl > 0) {
				x = startX;
				y = (this.game.getBoard().getSize() / 2) - (this.game.getPlayers().size() / 2) + (pl - 1);
				while (x < sizeX && y >= sizeY) {
					cells[x][y].setOwner(this.game.getPlayers().get(pl));	
					if(cells[x][y].isEnabled()) {
						cells[x][y].setEnabled(true);
						cells[x][y].setDefaultBackground(new ImageIcon("./data/grass.png"));
					}
					x++;
					y--;
				}
			}
			
			pl++;
			if (pl >= this.game.getPlayers().size()) {
				pl = 0;
			}
		}
		
		
		// We generate the Cell grid
		for (int j=0; j < this.gridSize ; j++) 
		{
			for (int i=0;i < this.gridSize;i++) 
			{
				// Clicking on the button is associated with a function in the ReversiController
				//cells[i][j].addActionListener(e -> reversiController.onCellClicked(cells[x][y]));
				cells[i][j].addActionListener(this.listener);
				// We store in the Cell its position in the table
				cells[i][j].setCoordX(i);
				cells[i][j].setCoordY(j);

				// The Cell is added to the panel
				this.gamePanel.add(cells[i][j]);

				// We update the appearance of the Cell
				cells[i][j].updateState();
			}
		}



		// ======================================================================================
		// PANEL "scores" WHERE ARE SCORES
		// ======================================================================================
		JPanel scores = new JPanel();
		scores.setBackground(Color.LIGHT_GRAY);
		// We determine the size of the band on the side where the scores are, and we associate it with the layout
		scores.setLayout(new GridBagLayout());
		scores.setPreferredSize(new Dimension(scoresSizeX,500));
		GridBagConstraints gbc = new GridBagConstraints();
		
		int decalage=0;

		// WHO WILL PLAY
		// We position the information on who will play

		gbc.gridx = 0;
		gbc.gridy = decalage;

		this.label = new JLabel("Tour de :");
		this.label.setFont(new Font("Arial",0,20));
		scores.add(this.label,gbc);


		gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
		this.whoPlayName= new JLabel();
		this.whoPlayName.setFont(new Font("Arial",0,20));
		//this.whoPlayName.setPreferredSize(new Dimension(20,100));
		scores.add(this.whoPlayName,gbc);

		gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
		this.whoPlayColor= new JPanel();
		this.whoPlayColor.setMinimumSize(new Dimension(50,50));
		this.whoPlayColor.setPreferredSize(new Dimension(50,50));
		this.whoPlayColor.setMaximumSize(new Dimension(50,50));
		scores.add(this.whoPlayColor,gbc);

		// ADD SPACE
		// We move down by adding empty labels
		for (int addSpace = 0 ; addSpace<13 ; addSpace++) 
		{
			gbc.gridx = 0;
            decalage++;
            gbc.gridy = decalage;
			scores.add(new JLabel(" "),gbc);
		}


		// SCORES LABELS
		// We create a new label per player
		for (Player player : this.game.getPlayers()) 
		{
			player.setScore(this.game.getPlayers().size());
			player.getScoreLabel().setText("Score de "+player.getName()+" : "+player.getScore());
			player.getScoreLabel().setFont(new Font("Arial",0,20));
			gbc.gridx = 0;
            decalage++;
            gbc.gridy = decalage;
			scores.add(player.getScoreLabel(),gbc);
		}

		// ADD SPACE
		for (int addSpace = 0 ; addSpace<4 ; addSpace++) 
		{
			gbc.gridx = 0;
            decalage++;
            gbc.gridy = decalage;
			scores.add(new JLabel(" "),gbc);
		}


		// RULES
		gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
		JButton rules = new JButton("Rules");
		rules.setPreferredSize(new Dimension(125,30));
		// This button is associated with a function in ReversiController
		rules.addActionListener(e -> listener.onRulesClicked());
		scores.add(rules,gbc);
		
		

		// ADD SPACE
		for (int addSpace = 0 ; addSpace<2 ; addSpace++) 
		{
			gbc.gridx = 0;
            decalage++;
            gbc.gridy = decalage;
			scores.add(new JLabel(" "),gbc);
		}

		
		// BACK TO MENU
		// We create a button that leads to the menu
		gbc.gridx = 0;
		decalage++;
        gbc.gridy = decalage;
        JButton replay = new JButton("Replay");
        replay.setPreferredSize(new Dimension(125,30));
		// On associe ce bouton � une fonction dans ReversiController
        replay.addActionListener(e -> listener.onReplayClicked(replay));
		scores.add(replay,gbc);
		
		// BACK TO MENU
		// We create a button that leads to the menu
		gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
		this.backToMenu = new JButton("Back to menu");
		this.backToMenu.setPreferredSize(new Dimension(125,30));
		// This button is associated with a function in ReversiController
		this.backToMenu.addActionListener(e -> listener.onBackToMenuClicked(this.backToMenu));
		scores.add(this.backToMenu,gbc);



		// Power Part
		JPanel powerPart = new JPanel(new GridLayout(this.game.getPowerNumber(),1));
		for(int i = 0; i < this.game.getPowerNumber(); i++) {
			PowerButton btn = new PowerButton();
			btn.addActionListener(e -> this.listener.onPowerClick(btn));
			this.setFixedSize(btn, 150, 150);
			powerPart.add(btn);
			this.powerListBtn.add(btn);

			this.buttonSizeY=150;
		}

		// We give a minimum size to the frame
		this.setMinimumSize(new Dimension(10+gridSize*(cellSize+5)+scoresSizeX+buttonSizeY, 39+gridSize*(cellSize+5)));

		//this.gamePanel.setSize(new Dimension(this.gamePanel.getHeight(),this.gamePanel.getHeight()));
		//scores.setPreferredSize(new Dimension(this.getWidth()-this.gamePanel.getWidth(),1500));
		//System.out.println(this.getWidth()+"-"+this.gamePanel.wid);
		// On ajoute les 2 panel � la fen�tre

		this.getContentPane().add(powerPart, BorderLayout.WEST);
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);
		this.getContentPane().add(scores, BorderLayout.EAST);
		this.pack();

		this.setVisible(true);
	}

	public void setFixedSize(Component c, int width, int height) {
		c.setPreferredSize(new Dimension(width, height));
		c.setMinimumSize(new Dimension(width, height));
		c.setMaximumSize(new Dimension(width, height));
		c.setSize(width, height);
	}

	// ======================================================================================
	// Function that updates the state of cores in labels
	// ======================================================================================
	public void updateScores(ArrayList<Player> players) {
		for (Player player : players) {
			player.getScoreLabel().setText("Score de " + player.getName() + " : " + player.getScore());
		}
	}

	public void setCurrentPlayer(Player player) {
		this.whoPlayColor.setBackground(player.getColor());
		this.whoPlayName.setText(player.getName());
	}

	public void updatePlayerPowers(Player player) {
		int i = 0;
		for (i = 0; i < this.game.getPowerNumber(); i++) {
			this.powerListBtn.get(i).setIcon(null);
			this.powerListBtn.get(i).setBackground(null);
			this.powerListBtn.get(i).setEnabled(false);
		}
		i = 0;
		for (Power power : player.getPowers()) {
			this.powerListBtn.get(i).setIcon(power.getIcon());
			this.powerListBtn.get(i).setPower(power);
			this.powerListBtn.get(i).setEnabled(true);
			i++;
		}
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}
	
	public ArrayList<PowerButton> getPowerListBtn() {
		return this.powerListBtn;
	}
	
	public void displayEndMessage() {
		
		this.whoPlayColor.setBackground(Color.LIGHT_GRAY);
		this.whoPlayName.setText(" ");
		
		this.label.setText("BLOCKED !");

	    
	    if (this.game.isEnded()) 
		{
	    	this.label.setText("END !");
		}
	    
	    
	    ArrayList<Integer> scores = new ArrayList<Integer>();
	    for (Player player : this.game.getPlayers()) 
	    {
	    	scores.add(player.getScore());
	    }
	    int max = Collections.max(scores);
	    for (Player player : this.game.getPlayers()) 
	    {
	    	if (max == player.getScore())
	    	{
				this.whoPlayName.setText(this.whoPlayName.getText()+" "+player.getName() + " wins !");
	    	}
	    }
	    
	}

}
