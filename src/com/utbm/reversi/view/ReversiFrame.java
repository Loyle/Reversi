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

import com.utbm.reversi.controller.ReversiController;
import com.utbm.reversi.model.cells.Cell;

public class ReversiFrame extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8514403723481387196L;
	
	// On associe la fenêtre de jeu ReversiController à la fenêtre
	private final ReversiController reversiController = new ReversiController(this);
	
	// On crée un tableau de Cell où on stockera nos cases (la taille du tableau change en fonction de la taille de la grille)
	private Cell cellArray[][];
	
	// On crée un premier panel sur lequel seront affichés les boutons (il est en paramètre pour gérer la fin d'une partie)
	final JPanel game = new JPanel();
	
	// On crée un label et un panel associés au tour du joueur (le panel est la case qui change de couleur, le texte est "tour des :")
	private final JLabel label1 = new JLabel("Tour des :");
	private final JPanel whoPlay = new JPanel();
	
	// On crée un label et un panel comme avant pour afficher le score (couleur + texte). Le int est associé au score. On le fait pour chaque couleur.
	private JLabel whiteScoreLabel = new JLabel("Score : 2");
	private int whiteScore=2;
	private final JPanel whiteScorePanel = new JPanel();
	private JLabel blackScoreLabel = new JLabel("Score : 2");
	private int blackScore=2;
	private final JPanel blackScorePanel = new JPanel();
	
	// On crée le bouton de retour vers le menu
	private JButton backToMenu = new JButton("<=");
	
	// On crée 2 constantes (la taille d'une case à la génération et la largeur de la bande sur le côté où les scores sont affichés)
	private final int cellSize = 70;
	private final int scoresSizeX = 100;
	
	// La taille de la grille est stockée dans un int, qui change à chaque génération d'une nouvelle fenêtre
	private int gridSize;
	
	
	public ReversiFrame(int size) 
	{
		// On redétermine la taille de la grille quand on crée la fenêtre
		this.gridSize = size;
		
		// ======================================================================================
		// PANEL "game" OÙ SONT LES CELL
		// ======================================================================================
        this.game.setBackground(Color.black);
        // On affiche les cell dans une GridLayout
        this.game.setLayout(new GridLayout(this.gridSize,this.gridSize,1,1));
        
        // On donne la taille du tableau
        cellArray = new Cell[this.gridSize][this.gridSize];
        
        // On génère la grille de Cell
        for (int i=0; i<this.gridSize ; i++) 
        {
        	for (int j=0;j<this.gridSize;j++) 
        	{
        		// Création de la Cell
        		final Cell cell = new Cell();
        		cell.setBackground(Color.green);
        		// On associe le clic sur le bouton à une fonction présente dans le ReversiController
        		cell.addActionListener(e -> reversiController.onCellClicked(cell));
        		// On stocke dans la Cell sa position dans le tableau
        		cell.setCoordX(j);
        		cell.setCoordY(i);
        		// On stocke la Cell dans le tableau de Cell
        		cellArray[j][i] = cell;
        		
        		// On place les premiers jetons au milieu (2 noirs, 2 blancs)
        		if ((i > -2 + this.gridSize/2) && (i < 1 + this.gridSize/2) && (j > -2 + this.gridSize/2) && (j < 1 + this.gridSize/2)) 
        		{
        			if ((i == -1 + this.gridSize/2) && (j == -1 + this.gridSize/2)) 
        			{
            			cell.setState(2); // 2 = noir
        			}
        			else if ((i == this.gridSize/2) && (j == this.gridSize/2)) 
        			{
            			cell.setState(2); // 2 = noir
        			}
        			else 
        			{
            			cell.setState(1); // 1 = blanc
        			}
        		}
        		// On ajoute la Cell au panel
        		this.game.add(cell);
        		// On actualise l'apparence de la Cell
        		cell.updateState();
        	}
        }
        
        
        
        // ======================================================================================
     	// PANEL "scores" OÙ SONT LES SCORES
        // ======================================================================================
        final JPanel scores = new JPanel();
        scores.setBackground(Color.gray);
        // On détermine la taille de la band ede côté où sont les scores, et on lui associe le layout
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
        // On se déplace vers le bas en ajoutant des label vides
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
        // On crée un bouton qui renvoie vers le menu
        gbc.gridx = 0;
        gbc.gridy = 26;
        this.backToMenu.setPreferredSize(new Dimension(50,40));
        // On associe ce bouton à une fonction dans ReversiController
        this.backToMenu.addActionListener(e -> reversiController.onBackToMenuClicked(this.backToMenu));
        scores.add(this.backToMenu,gbc);
        
        // On donne une taille minimale à la fenêtre
        this.setMinimumSize(new Dimension(13+gridSize*(cellSize+5)+scoresSizeX, 42+gridSize*(cellSize+5)));
        // On ajoute les 2 panel à la fenêtre
        this.getContentPane().add(game, BorderLayout.CENTER);
        this.getContentPane().add(scores, BorderLayout.EAST);
        this.pack();
	}
	

    
    // ======================================================================================
 	// Fonction qui permet de changer l'apparence du carré de couleur indiquant qui va jouer
    // ======================================================================================
	public void changeWhoPlay()
	{
		if (this.reversiController.getChange() == 1) 
		{
			this.whoPlay.setBackground(Color.black);
		}
		else if (this.reversiController.getChange() == 0) 
		{
			this.whoPlay.setBackground(Color.white);
		}
	}

	// ======================================================================================
 	// Fonction qui actualise l'état des cores dans les label
    // ======================================================================================
	public void updateScores() 
	{
		this.blackScoreLabel.setText("Score : "+this.blackScore);
		this.whiteScoreLabel.setText("Score : "+this.whiteScore);
	}

	
	
	
	

	// ======================================================================================
 	// Getters et setters
    // ======================================================================================
	public Cell[][] getCellArray() 
	{
		return cellArray;
	}


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



	public JPanel getGame() {
		return game;
	}
	
	
	
	
	
	
	
}
