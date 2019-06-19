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
	// On stock la class board qui gère le board
	// private Board board;

	// On crée un premier panel sur lequel seront affichés les boutons (il est en
	// paramètre pour gérer la fin d'une partie)
	private JPanel gamePanel;

	// On crée un label et un panel associés au tour du joueur (le panel est la case
	// qui change de couleur, le texte est "tour des :")
	private JLabel label;
	private JPanel whoPlayColor;
	private JLabel whoPlayName;

	// On crée le bouton de retour vers le menu
	private JButton backToMenu;

	// On crée 2 constantes (la taille d'une case à la génération et la largeur de
	// la bande sur le côté où les scores sont affichés)
	private int cellSize;
	private int scoresSizeX;
	private int buttonSizeY;

	// La taille de la grille est stockée dans un int, qui change à chaque
	// génération d'une nouvelle fenêtre
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

		// On recupère les cellules du board
		Cell [][] cells = this.game.getBoard().getBoardCells();


		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Reversi Game");

		//this.setSize(700, 700);
		this.setLocationRelativeTo(null);

		// ======================================================================================
		// PANEL "game" OÙ SONT LES CELL
		// ======================================================================================

		this.gamePanel = new JPanel();
		this.gamePanel.setBackground(Color.black);
		this.gamePanel.setLayout(new GridLayout(this.gridSize,this.gridSize,0,0));

		
		
		// On place les premiers jetons au milieu
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
		
		
		// On génère la grille de Cell
		for (int j=0; j < this.gridSize ; j++) 
		{
			for (int i=0;i < this.gridSize;i++) 
			{
				// On associe le clic sur le bouton à une fonction présente dans le ReversiController
				//cells[i][j].addActionListener(e -> reversiController.onCellClicked(cells[x][y]));
				cells[i][j].addActionListener(this.listener);
				// On stocke dans la Cell sa position dans le tableau
				cells[i][j].setCoordX(i);
				cells[i][j].setCoordY(j);

				// On ajoute la Cell au panel
				this.gamePanel.add(cells[i][j]);

				// On actualise l'apparence de la Cell
				cells[i][j].updateState();
			}
		}



		// ======================================================================================
		// PANEL "scores" OÙ SONT LES SCORES
		// ======================================================================================
		final JPanel scores = new JPanel();
		scores.setBackground(Color.LIGHT_GRAY);
		// On détermine la taille de la band ede côté où sont les scores, et on lui associe le layout
		scores.setLayout(new GridBagLayout());
		scores.setPreferredSize(new Dimension(scoresSizeX,500));
		GridBagConstraints gbc = new GridBagConstraints();
		
		int decalage=0;

		// WHO WILL PLAY
		// On positionne les informations sur qui va jouer

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
		// On se déplace vers le bas en ajoutant des label vides
		for (int addSpace = 0 ; addSpace<13 ; addSpace++) 
		{
			gbc.gridx = 0;
            decalage++;
            gbc.gridy = decalage;
			scores.add(new JLabel(" "),gbc);
		}


		// SCORES LABELS
		// On crée un nouveau label par joueur
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
		// On associe ce bouton à une fonction dans ReversiController
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
		// On crée un bouton qui renvoie vers le menu
		gbc.gridx = 0;
		decalage++;
        gbc.gridy = decalage;
        JButton replay = new JButton("Replay");
        replay.setPreferredSize(new Dimension(125,30));
		// On associe ce bouton à une fonction dans ReversiController
        replay.addActionListener(e -> listener.onReplayClicked(replay));
		scores.add(replay,gbc);
		
		// BACK TO MENU
		// On crée un bouton qui renvoie vers le menu
		gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
		this.backToMenu = new JButton("Back to menu");
		this.backToMenu.setPreferredSize(new Dimension(125,30));
		// On associe ce bouton à une fonction dans ReversiController
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

		// On donne une taille minimale à la fenêtre
		this.setMinimumSize(new Dimension(10+gridSize*(cellSize+5)+scoresSizeX+buttonSizeY, 39+gridSize*(cellSize+5)));

		//this.gamePanel.setSize(new Dimension(this.gamePanel.getHeight(),this.gamePanel.getHeight()));
		//scores.setPreferredSize(new Dimension(this.getWidth()-this.gamePanel.getWidth(),1500));
		//System.out.println(this.getWidth()+"-"+this.gamePanel.wid);
		// On ajoute les 2 panel à la fenêtre

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
	// Fonction qui actualise l'état des cores dans les label
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
		// On détruit le panel sur lequel la grille de Cell est générée
		this.remove(this.getGamePanel());
		// On crée un nouveau panel pour remplacer celui que l'on vient de supprimer
		JPanel end = new JPanel();
		end.setBackground(Color.lightGray);
		end.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // On associe un message de fin à ce panel en fonction de la manière dont la partie s'est terminée et en fonction du score
		JLabel endMsg = new JLabel();
        gbc.gridx=0;
        gbc.gridy=0;
		end.add(endMsg,gbc);
		
		// ADD SPACE
		for (int addSpace = 1 ; addSpace<5 ; addSpace++) 
        {
            gbc.gridx = 0;
            gbc.gridy = addSpace;
            end.add(new JLabel(" "),gbc);
        }
		
		// Création du bouton permettant de recommencer la partie avec la même taille de grille
		JButton replay = new JButton("Replay");
		gbc.gridx=0;
	    gbc.gridy=5;
	    replay.addActionListener(e -> this.listener.onReplayClicked(replay));
	    end.add(replay,gbc);
		this.getContentPane().add(end,BorderLayout.CENTER);
		
		// Création du bouton pour revenir au menu
		JButton endBackToMenu = new JButton("Back to Menu");
		gbc.gridx=0;
	    gbc.gridy=6;
	    endBackToMenu.addActionListener(e -> this.listener.onBackToMenuClicked(endBackToMenu));
	    end.add(endBackToMenu,gbc);
	    
	    

		/*endMsg.setText("BLOCKED !");
	    
	    if (isEnded()) 
		{
			endMsg.setText("END !");
		}*/
	    
	    
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
				endMsg.setText(endMsg.getText()+" "+player.getName() + " wins !");
	    	}
	    }
	    
	    // On place le panel de fin là où se trouvait la grille
		this.getContentPane().add(end,BorderLayout.CENTER);
	}

}
