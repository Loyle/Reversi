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
public class ReversiFrame extends JFrame 
{
	// On stock la class board qui gère le board
	//private Board board;
	
	// On crée un premier panel sur lequel seront affichés les boutons (il est en paramètre pour gérer la fin d'une partie)
	private JPanel gamePanel;
	
	// On crée un label et un panel associés au tour du joueur (le panel est la case qui change de couleur, le texte est "tour des :")
	private JLabel label;
	private JPanel whoPlayColor;
	private JLabel whoPlayName;
	
	
	// On crée un label et un panel comme avant pour afficher le score (couleur + texte). Le int est associé au score. On le fait pour chaque couleur.
	private JLabel whiteScoreLabel;
	private JLabel blackScoreLabel;
	
	// On crée le bouton de retour vers le menu
	private JButton backToMenu;
	
	// On crée 2 constantes (la taille d'une case à la génération et la largeur de la bande sur le côté où les scores sont affichés)
	private int cellSize;
	private int scoresSizeX;
	
	// La taille de la grille est stockée dans un int, qui change à chaque génération d'une nouvelle fenêtre
	private int gridSize;
	
	private GameListener listener;
	private Game game;
	private GameController controller;
	
	private ArrayList<PowerButton> powerListBtn;
	
	public ReversiFrame(int size) 
	{
		this.game = new Game(this, size);
		this.controller = new GameController(this.game, this);
		this.listener = new GameListener(this.controller);
		
		this.gridSize = size;
		
		this.game.addPlayer(new Player("Comar", Color.BLACK));
		this.game.addPlayer(new Player("Toinou", Color.WHITE));
		
		this.initWindow();
		this.game.run();
	}
	
	public void initWindow() {
		
		this.scoresSizeX = 100;
		this.cellSize = 70;
		this.powerListBtn = new ArrayList<PowerButton>();
		
		// On recupère les cellules du board
        Cell [][] cells = this.game.getBoard().getBoardCells();
		
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Reversi Game");
        
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		
		// ======================================================================================
		// PANEL "game" OÙ SONT LES CELL
		// ======================================================================================
        
        this.gamePanel = new JPanel();
        this.gamePanel.setBackground(Color.black);
        this.gamePanel.setLayout(new GridLayout(this.gridSize,this.gridSize,0,0));
        
        
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
     	// PANEL "scores" OÙ SONT LES SCORES
        // ======================================================================================
        final JPanel scores = new JPanel();
        scores.setBackground(Color.LIGHT_GRAY);
        // On détermine la taille de la band ede côté où sont les scores, et on lui associe le layout
        scores.setLayout(new GridBagLayout());
        scores.setPreferredSize(new Dimension(scoresSizeX,500));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // WHO WILL PLAY
        // On positionne les informations sur qui va jouer
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        this.label = new JLabel("Tour de :");
        this.label.setFont(new Font("Arial",0,20));
        scores.add(this.label,gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.whoPlayName= new JLabel();
        this.whoPlayName.setFont(new Font("Arial",0,20));
        //this.whoPlayName.setPreferredSize(new Dimension(20,100));
        scores.add(this.whoPlayName,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.whoPlayColor= new JPanel();
        this.whoPlayColor.setPreferredSize(new Dimension(50,50));
        scores.add(this.whoPlayColor,gbc);
        
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
        JPanel whiteScorePanel = new JPanel();
        whiteScorePanel.setPreferredSize(new Dimension(40,40));
        whiteScorePanel.setBackground(Color.white);
        scores.add(whiteScorePanel,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 16;
        this.whiteScoreLabel = new JLabel();
        scores.add(whiteScoreLabel,gbc);
        
        
        
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
        
        JPanel blackScorePanel = new JPanel();
        blackScorePanel.setPreferredSize(new Dimension(40,40));
        blackScorePanel.setBackground(Color.black);
        scores.add(blackScorePanel,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 20;
        
        this.blackScoreLabel =new JLabel();
        scores.add(this.blackScoreLabel,gbc);
        
        
        
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
        this.backToMenu = new JButton("<=");
        this.backToMenu.setPreferredSize(new Dimension(50,40));
        // On associe ce bouton à une fonction dans ReversiController
        this.backToMenu.addActionListener(e -> listener.onBackToMenuClicked(this.backToMenu));
        scores.add(this.backToMenu,gbc);
        
        
        
        // Power Part
        JPanel powerPart = new JPanel(new GridLayout(1,this.game.getNumberPower()));
        for(int i = 0; i < this.game.getNumberPower(); i++) {
        	PowerButton btn = new PowerButton();
        	btn.addActionListener(e -> this.listener.onPowerClick(btn));
        	this.setFixedSize(btn, 150, 150);
        	powerPart.add(btn);
        	this.powerListBtn.add(btn);
        }
        
        // On donne une taille minimale à la fenêtre
        this.setMinimumSize(new Dimension(13+gridSize*(cellSize+5)+scoresSizeX, 42+gridSize*(cellSize+5)));
        // On ajoute les 2 panel à la fenêtre
        
        this.getContentPane().add(powerPart, BorderLayout.SOUTH);
        this.getContentPane().add(gamePanel, BorderLayout.CENTER);
        this.getContentPane().add(scores, BorderLayout.EAST);
        this.pack();
        
        this.setVisible(true);
	}
	
	public void setFixedSize(Component c, int width, int height) {
		c.setPreferredSize(new Dimension(width,height));
		c.setMinimumSize(new Dimension(width,height));
		c.setMaximumSize(new Dimension(width,height));
		c.setSize(width, height);
	}

	// ======================================================================================
 	// Fonction qui actualise l'état des cores dans les label
    // ======================================================================================
	public void updateScores(Player p1, Player p2) 
	{
		this.blackScoreLabel.setText("Score : " + p1.getScore());
		this.whiteScoreLabel.setText("Score : " + p2.getScore());
	}

	public void setCurrentPlayer(Player player) {
		this.whoPlayColor.setBackground(player.getColor());
		this.whoPlayName.setText(player.getName());
	}
	
	public void updatePlayerPowers(Player player) {
		int i = 0;
		for(i = 0; i < this.game.getNumberPower(); i++) {
			this.powerListBtn.get(i).setIcon(null);
			this.powerListBtn.get(i).setBackground(null);
			this.powerListBtn.get(i).setEnabled(false);
		}
		i = 0;
		for(Power power : player.getPowers()) {
			this.powerListBtn.get(i).setIcon(power.getIcon());
			this.powerListBtn.get(i).setPower(power);
			this.powerListBtn.get(i).setEnabled(true);
			i++;
		}
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}

}
