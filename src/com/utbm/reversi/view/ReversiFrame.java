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
	// On stock la class board qui g�re le board
	//private Board board;

	// On cr�e un premier panel sur lequel seront affich�s les boutons (il est en param�tre pour g�rer la fin d'une partie)
	private JPanel gamePanel;

	// On cr�e un label et un panel associ�s au tour du joueur (le panel est la case qui change de couleur, le texte est "tour des :")
	private JLabel label;
	private JPanel whoPlayColor;
	private JLabel whoPlayName;



	// On cr�e le bouton de retour vers le menu
	private JButton backToMenu;

	// On cr�e 2 constantes (la taille d'une case � la g�n�ration et la largeur de la bande sur le c�t� o� les scores sont affich�s)
	private int cellSize;
	private int scoresSizeX;
	private int buttonSizeY;

	// La taille de la grille est stock�e dans un int, qui change � chaque g�n�ration d'une nouvelle fen�tre
	private int gridSize;

	private GameListener listener;
	private Game game;
	private GameController controller;

	private ArrayList<PowerButton> powerListBtn;


	public ReversiFrame(int size , int powerNumber, int obstaclesNumber, int trapsNumber, ArrayList<Player> players) 
	{
		this.game = new Game(this, size, powerNumber, obstaclesNumber, trapsNumber);

		for (Player player : players) 
		{
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

		// On recup�re les cellules du board
		Cell [][] cells = this.game.getBoard().getBoardCells();


		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Reversi Game");

		//this.setSize(700, 700);
		this.setLocationRelativeTo(null);

		// ======================================================================================
		// PANEL "game" O� SONT LES CELL
		// ======================================================================================

		this.gamePanel = new JPanel();
		this.gamePanel.setBackground(Color.black);
		this.gamePanel.setLayout(new GridLayout(this.gridSize,this.gridSize,0,0));


		// On g�n�re la grille de Cell
		for (int j=0; j < this.gridSize ; j++) 
		{
			for (int i=0;i < this.gridSize;i++) 
			{
				// On associe le clic sur le bouton � une fonction pr�sente dans le ReversiController
				//cells[i][j].addActionListener(e -> reversiController.onCellClicked(cells[x][y]));
				cells[i][j].addActionListener(this.listener);
				// On stocke dans la Cell sa position dans le tableau
				cells[i][j].setCoordX(i);
				cells[i][j].setCoordY(j);

				// On place les premiers jetons au milieu

				switch (this.game.getPlayers().size()) 
				{
				case 2:
					if ((i > -2 + this.gridSize/2) && (i < 1 + this.gridSize/2) && (j > -2 + this.gridSize/2) && (j < 1 + this.gridSize/2)) 
					{
						if (((i == -1 + this.gridSize/2) && (j == -1 + this.gridSize/2)) || ((i == this.gridSize/2) && (j == this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(0));
						}
						else 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(1));
						}
					}
					break;
				case 3:
					if ((i > -2 + this.gridSize/2) && (i < 2 + this.gridSize/2) && (j > -2 + this.gridSize/2) && (j < 2 + this.gridSize/2)) 
					{
						if (((i == -1 + this.gridSize/2) && (j == -1 + this.gridSize/2)) || ((i == this.gridSize/2) && (j == this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == 1+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(0));
						}
						else if (((i == this.gridSize/2) && (j == -1 + this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 1+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(1));
						}
						else
						{
							cells[i][j].setOwner(this.game.getPlayers().get(2));
						}
					}
					break;
				case 4 :
					if ((i > -3 + this.gridSize/2) && (i < 2 + this.gridSize/2) && (j > -3 + this.gridSize/2) && (j < 2 + this.gridSize/2)) 
					{
						if (((i == -2 + this.gridSize/2) && (j == -2 + this.gridSize/2)) || ((i == -1 +this.gridSize/2) && (j == -1 +this.gridSize/2)) || ((i == this.gridSize/2) && (j == this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == 1+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(0));
						}
						else if (((i == -1 + this.gridSize/2) && (j == -2 + this.gridSize/2)) || ((i == this.gridSize/2) && (j == -1 +this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 1+this.gridSize/2)))    
						{
							cells[i][j].setOwner(this.game.getPlayers().get(1));
						}
						else if (((i == this.gridSize/2) && (j == -2 + this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == -1 +this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 1+this.gridSize/2)))
						{
							cells[i][j].setOwner(this.game.getPlayers().get(2));
						}
						else
						{
							cells[i][j].setOwner(this.game.getPlayers().get(3));
						}
					}
					break;
				case 5 :
					if ((i > -3 + this.gridSize/2) && (i < 3 + this.gridSize/2) && (j > -3 + this.gridSize/2) && (j < 3 + this.gridSize/2)) 
					{
						if (((i == -2 + this.gridSize/2) && (j == -2 + this.gridSize/2)) || ((i == -1 +this.gridSize/2) && (j == -1 +this.gridSize/2)) || ((i == this.gridSize/2) && (j == this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == 2+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(0));
						}
						else if (((i == -1 + this.gridSize/2) && (j == -2 + this.gridSize/2)) || ((i == this.gridSize/2) && (j == -1 +this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 2+this.gridSize/2)))    
						{
							cells[i][j].setOwner(this.game.getPlayers().get(1));
						}
						else if(((i == this.gridSize/2) && (j == -2 + this.gridSize/2)) || ((i == 1 +this.gridSize/2) && (j == -1 +this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 2+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(2));
						}
						else if(((i == 1+this.gridSize/2) && (j == -2 + this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == -1 +this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == this.gridSize/2) && (j == 2+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(3));
						}
						else
						{
							cells[i][j].setOwner(this.game.getPlayers().get(4));
						}
					}
					break;
				case 6 :
					if ((i > -4 + this.gridSize/2) && (i < 3 + this.gridSize/2) && (j > -4 + this.gridSize/2) && (j < 3 + this.gridSize/2)) 
					{
						if (((i == -3 + this.gridSize/2) && (j == -3 + this.gridSize/2)) || ((i == -2 +this.gridSize/2) && (j == -2 +this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == this.gridSize/2) && (j == this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == 2+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(0));
						}
						else if (((i == -2 + this.gridSize/2) && (j == -3 + this.gridSize/2)) || ((i == -1 +this.gridSize/2) && (j == -2 +this.gridSize/2)) || ((i == this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 2+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(1));
						}
						else if (((i == -1 + this.gridSize/2) && (j == -3 + this.gridSize/2)) || ((i == this.gridSize/2) && (j == -2 +this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 2+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(2));
						}
						else if (((i == this.gridSize/2) && (j == -3 + this.gridSize/2)) || ((i == 1 + this.gridSize/2) && (j == -2 +this.gridSize/2)) || ((i == 2 + this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 2+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(3));
						}
						else if (((i == 1+this.gridSize/2) && (j == -3 + this.gridSize/2)) || ((i == 2 + this.gridSize/2) && (j == -2 +this.gridSize/2)) || ((i == -3 + this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == this.gridSize/2) && (j == 2+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(4));
						}
						else
						{
							cells[i][j].setOwner(this.game.getPlayers().get(5));
						}
					}
					break;
				case 7 :
					if ((i > -4 + this.gridSize/2) && (i < 4 + this.gridSize/2) && (j > -4 + this.gridSize/2) && (j < 4 + this.gridSize/2)) 
					{
						if (((i == -3 + this.gridSize/2) && (j == -3 + this.gridSize/2)) || ((i == -2 +this.gridSize/2) && (j == -2 +this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == this.gridSize/2) && (j == this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(0));
						}
						else if (((i == -2 + this.gridSize/2) && (j == -3 + this.gridSize/2)) || ((i == -1 +this.gridSize/2) && (j == -2 +this.gridSize/2)) || ((i == this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 1 + this.gridSize/2) && (j == this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(1));
						}
						else if (((i == -1 + this.gridSize/2) && (j == -3 + this.gridSize/2)) || ((i == this.gridSize/2) && (j == -2 +this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 2 + this.gridSize/2) && (j == this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(2));
						}
						else if (((i == this.gridSize/2) && (j == -3 + this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == -2 +this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 3 + this.gridSize/2) && (j == this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(3));
						}
						else if (((i == 1 + this.gridSize/2) && (j == -3 + this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == -2 +this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == -3 + this.gridSize/2) && (j == this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(4));
						}
						else if (((i == 2 + this.gridSize/2) && (j == -3 + this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == -2 +this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == -2 + this.gridSize/2) && (j == this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(5));
						}
						else
						{
							cells[i][j].setOwner(this.game.getPlayers().get(6));
						}
					}
					break;
				case 8 :
					if ((i > -5 + this.gridSize/2) && (i < 4 + this.gridSize/2) && (j > -5 + this.gridSize/2) && (j < 4 + this.gridSize/2)) 
					{
						if (((i == -4 + this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == -3 +this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == this.gridSize/2) && (j == this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(0));
						}
						else if (((i == -3 + this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == -2 +this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(1));
						}
						else if (((i == -2 + this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == -1 +this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(2));
						}
						else if (((i == -1 + this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(3));
						}
						else if (((i == this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(4));
						}
						else if (((i == 1+this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(5));
						}
						else if (((i == 2+this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == 3+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(6));
						}
						else
						{
							cells[i][j].setOwner(this.game.getPlayers().get(7));
						}
					}
					break;
				case 9 :
					if ((i > -5 + this.gridSize/2) && (i < 5 + this.gridSize/2) && (j > -5 + this.gridSize/2) && (j < 5 + this.gridSize/2)) 
					{
						if (((i == -4 + this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == -3 +this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == this.gridSize/2) && (j == this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == 3+this.gridSize/2)) || ((i == 4+this.gridSize/2) && (j == 4+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(0));
						}
						else if (((i == -3 + this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == -2 +this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == 4+this.gridSize/2) && (j == 3+this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == 4+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(1));
						}
						else if (((i == -2 + this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == -1 +this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == 4+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == 3+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 4+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(2));
						}
						else if (((i == -1 + this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == this.gridSize/2)) || ((i == 4+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 3+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 4+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(3));
						}
						else if (((i == this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == 4+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 3+this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 4+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(4));
						}
						else if (((i == 1+this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == 4+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 3+this.gridSize/2)) || ((i == this.gridSize/2) && (j == 4+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(5));
						}
						else if (((i == 2+this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == 3+this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == 4+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == this.gridSize/2) && (j == 3+this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == 4+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(6));
						}
						else if (((i == 3+this.gridSize/2) && (j == -4 + this.gridSize/2)) || ((i == 4+this.gridSize/2) && (j == -3 +this.gridSize/2)) || ((i == -4+this.gridSize/2) && (j == -2+this.gridSize/2)) || ((i == -3+this.gridSize/2) && (j == -1+this.gridSize/2)) || ((i == -2+this.gridSize/2) && (j == this.gridSize/2)) || ((i == -1+this.gridSize/2) && (j == 1+this.gridSize/2)) || ((i == this.gridSize/2) && (j == 2+this.gridSize/2)) || ((i == 1+this.gridSize/2) && (j == 3+this.gridSize/2)) || ((i == 2+this.gridSize/2) && (j == 4+this.gridSize/2))) 
						{
							cells[i][j].setOwner(this.game.getPlayers().get(7));
						}
						else
						{
							cells[i][j].setOwner(this.game.getPlayers().get(8));
						}
					}
					break;
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
		scores.setBackground(Color.LIGHT_GRAY);
		// On d�termine la taille de la band ede c�t� o� sont les scores, et on lui associe le layout
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
		// On se d�place vers le bas en ajoutant des label vides
		for (int addSpace = 0 ; addSpace<13 ; addSpace++) 
		{
			gbc.gridx = 0;
            decalage++;
            gbc.gridy = decalage;
			scores.add(new JLabel(" "),gbc);
		}


		// SCORES LABELS
		// On cr�e un nouveau label par joueur
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
		// On associe ce bouton � une fonction dans ReversiController
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
		// On cr�e un bouton qui renvoie vers le menu
		gbc.gridx = 0;
		decalage++;
        gbc.gridy = decalage;
        JButton replay = new JButton("Replay");
        replay.setPreferredSize(new Dimension(125,30));
		// On associe ce bouton � une fonction dans ReversiController
        replay.addActionListener(e -> listener.onReplayClicked(replay));
		scores.add(replay,gbc);
		
		// BACK TO MENU
		// On cr�e un bouton qui renvoie vers le menu
		gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
		this.backToMenu = new JButton("Back to menu");
		this.backToMenu.setPreferredSize(new Dimension(125,30));
		// On associe ce bouton � une fonction dans ReversiController
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

		// On donne une taille minimale � la fen�tre
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
		c.setPreferredSize(new Dimension(width,height));
		c.setMinimumSize(new Dimension(width,height));
		c.setMaximumSize(new Dimension(width,height));
		c.setSize(width, height);
	}

	// ======================================================================================
	// Fonction qui actualise l'�tat des cores dans les label
	// ======================================================================================
	public void updateScores(ArrayList<Player> players) 
	{
		for (Player player : players) 
		{
			player.getScoreLabel().setText("Score de "+player.getName()+" : "+player.getScore());
		}
	}

	public void setCurrentPlayer(Player player) {
		this.whoPlayColor.setBackground(player.getColor());
		this.whoPlayName.setText(player.getName());
	}

	public void updatePlayerPowers(Player player) {
		int i = 0;
		for(i = 0; i < this.game.getPowerNumber(); i++) {
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

	public GameListener getListener() {
		return listener;
	}

	public Game getGame() {
		return game;
	}




}
