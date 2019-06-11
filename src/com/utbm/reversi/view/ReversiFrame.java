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
import javax.swing.WindowConstants;

import com.utbm.reversi.controller.GameController;
import com.utbm.reversi.listeners.GameListener;
import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.cells.Cell;

@SuppressWarnings("serial")
public class ReversiFrame extends JFrame 
{
	// On stock la class board qui g�re le board
	//private Board board;
	
	// On cr�e un premier panel sur lequel seront affich�s les boutons (il est en param�tre pour g�rer la fin d'une partie)
	final JPanel gamePanel = new JPanel();
	
	// On cr�e un label et un panel associ�s au tour du joueur (le panel est la case qui change de couleur, le texte est "tour des :")
	private final JLabel label1 = new JLabel("Tour des :");
	private final JPanel whoPlay = new JPanel();
	
	// On cr�e un label et un panel comme avant pour afficher le score (couleur + texte). Le int est associ� au score. On le fait pour chaque couleur.
	private JLabel whiteScoreLabel = new JLabel("Score : 2");
	private int whiteScore=2;
	private final JPanel whiteScorePanel = new JPanel();
	private JLabel blackScoreLabel = new JLabel("Score : 2");
	private int blackScore=2;
	private final JPanel blackScorePanel = new JPanel();
	
	// On cr�e le bouton de retour vers le menu
	private JButton backToMenu = new JButton("<=");
	
	// On cr�e 2 constantes (la taille d'une case � la g�n�ration et la largeur de la bande sur le c�t� o� les scores sont affich�s)
	private final int cellSize = 70;
	private final int scoresSizeX = 100;
	
	// La taille de la grille est stock�e dans un int, qui change � chaque g�n�ration d'une nouvelle fen�tre
	private int gridSize;
	
	private GameListener listener;
	private Game game;
	private GameController controller;
	
	public ReversiFrame(int size) 
	{
		this.game = new Game(this, size);
		this.controller = new GameController(this.game, this);
		this.listener = new GameListener(this.controller);
		
		this.gridSize = this.game.getBoard().getSize();
		
		this.game.addPlayer(new Player("Comar", Color.BLACK));
		this.game.addPlayer(new Player("Toinou", Color.WHITE));
		
		this.initWindow();
		this.game.run();
	}
	
	public void initWindow() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Reversi Game");
        
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		
		// On red�termine la taille de la grille quand on cr�e la fen�tre
		
		// On recup�re les cellules du board
        Cell [][] cells = this.game.getBoard().getBoardCells();
		
		// ======================================================================================
		// PANEL "game" O� SONT LES CELL
		// ======================================================================================
        this.gamePanel.setBackground(Color.black);
        // On affiche les cell dans une GridLayout
        this.gamePanel.setLayout(new GridLayout(this.gridSize,this.gridSize,0,0));
        
        
        // On g�n�re la grille de Cell
        for (int i=0; i < this.gridSize ; i++) 
        {
        	for (int j=0;j < this.gridSize;j++) 
        	{
        		cells[i][j].setBackground(Color.green);
        		// On associe le clic sur le bouton � une fonction pr�sente dans le ReversiController
        		//final int x = i;
        		//final int y = j;
        		//cells[i][j].addActionListener(e -> reversiController.onCellClicked(cells[x][y]));
        		cells[i][j].addActionListener(this.listener);
        		// On stocke dans la Cell sa position dans le tableau
        		cells[i][j].setCoordX(i);
        		cells[i][j].setCoordY(j);
        		
        		// On place les premiers jetons au milieu (2 noirs, 2 blancs)
        		if ((i > -2 + this.gridSize/2) && (i < 1 + this.gridSize/2) && (j > -2 + this.gridSize/2) && (j < 1 + this.gridSize/2)) 
        		{
        			if ((i == -1 + this.gridSize/2) && (j == -1 + this.gridSize/2)) 
        			{
        				cells[i][j].setOwner(this.game.getPlayers().get(0));
        			}
        			else if ((i == this.gridSize/2) && (j == this.gridSize/2)) 
        			{
        				cells[i][j].setOwner(this.game.getPlayers().get(0));
        			}
        			else 
        			{
        				cells[i][j].setOwner(this.game.getPlayers().get(1));
        			}
        		}
        		
        		// On ajoute la Cell au panel
        		this.gamePanel.add(cells[i][j]);
        		
        		// On actualise l'apparence de la Cell
        		cells[i][j].updateState();
        	}
        }
        
        
        
        // ======================================================================================
     	// PANEL "scores" O� SONT LES SCORES
        // ======================================================================================
        final JPanel scores = new JPanel();
        scores.setBackground(Color.gray);
        // On d�termine la taille de la band ede c�t� o� sont les scores, et on lui associe le layout
        scores.setLayout(new GridBagLayout());
        scores.setPreferredSize(new Dimension(scoresSizeX,500));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // WHO WILL PLAY
        // On positionne les informations sur qui va jouer
        gbc.gridx = 0;
        gbc.gridy = 0;
        scores.add(label1,gbc);
        this.whoPlay.setPreferredSize(new Dimension(50,50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        scores.add(whoPlay,gbc);
        
        // ADD SPACE
        // On se d�place vers le bas en ajoutant des label vides
        for (int addSpace = 2 ; addSpace<15 ; addSpace++) 
        {
            gbc.gridx = 0;
            gbc.gridy = addSpace;
            scores.add(new JLabel(" "),gbc);
        }
        
        
        // WHITE PLAYER SCORE
        // On affiche les informations sur les scores des joueurs
        gbc.gridx = 0;
        gbc.gridy = 15;
        scores.add(whiteScorePanel,gbc);
        gbc.gridx = 0;
        gbc.gridy = 16;
        scores.add(whiteScoreLabel,gbc);
        this.whiteScorePanel.setPreferredSize(new Dimension(40,40));
        this.whiteScorePanel.setBackground(Color.white);
        
        // ADD SPACE
        gbc.gridx = 0;
        gbc.gridy = 17;
        scores.add(new JLabel(" "),gbc);
        gbc.gridx = 0;
        gbc.gridy = 18;
        scores.add(new JLabel(" "),gbc);
        

        // BLACK PLAYER SCORE
        gbc.gridx = 0;
        gbc.gridy = 19;
        scores.add(blackScorePanel,gbc);
        gbc.gridx = 0;
        gbc.gridy = 20;
        scores.add(blackScoreLabel,gbc);
        this.blackScorePanel.setPreferredSize(new Dimension(40,40));
        this.blackScorePanel.setBackground(Color.black);
        
        
        // ADD SPACE
        for (int addSpace = 21 ; addSpace<25 ; addSpace++) 
        {
            gbc.gridx = 0;
            gbc.gridy = addSpace;
            scores.add(new JLabel(" "),gbc);
        }
        
        // BACK TO MENU
        // On cr�e un bouton qui renvoie vers le menu
        gbc.gridx = 0;
        gbc.gridy = 26;
        this.backToMenu.setPreferredSize(new Dimension(50,40));
        // On associe ce bouton � une fonction dans ReversiController
        
        
        //this.backToMenu.addActionListener(e -> reversiController.onBackToMenuClicked(this.backToMenu));
        
        
        scores.add(this.backToMenu,gbc);
        
        // On donne une taille minimale � la fen�tre
        this.setMinimumSize(new Dimension(13+gridSize*(cellSize+5)+scoresSizeX, 42+gridSize*(cellSize+5)));
        // On ajoute les 2 panel � la fen�tre
        this.getContentPane().add(gamePanel, BorderLayout.CENTER);
        this.getContentPane().add(scores, BorderLayout.EAST);
        this.pack();
        
        this.setVisible(true);
	}
	

    
    // ======================================================================================
 	// Fonction qui permet de changer l'apparence du carr� de couleur indiquant qui va jouer
    // ======================================================================================
	public void changeWhoPlay()
	{
		/*if (this.reversiController.getChange() == 1) 
		{
			this.whoPlay.setBackground(Color.white);
		}
		else if (this.reversiController.getChange() == 0) 
		{
			this.whoPlay.setBackground(Color.black);
		}*/
	}

	// ======================================================================================
 	// Fonction qui actualise l'�tat des cores dans les label
    // ======================================================================================
	public void updateScores() 
	{
		this.blackScoreLabel.setText("Score : "+this.blackScore);
		this.whiteScoreLabel.setText("Score : "+this.whiteScore);
	}

	
	
	
	

	// ======================================================================================
 	// Getters et setters
    // ======================================================================================


	public int getGridSize() 
	{
		return gridSize;
	}


	public void setWhiteScore(int whiteScore) {
		this.whiteScore = whiteScore;
	}


	public void setBlackScore(int blackScore) {
		this.blackScore = blackScore;
	}


	
	public int getWhiteScore() {
		return whiteScore;
	}


	public int getBlackScore() {
		return blackScore;
	}



	public JPanel getGamePanel() {
		return gamePanel;
	}
	
	
	
	
	
	
	
}
