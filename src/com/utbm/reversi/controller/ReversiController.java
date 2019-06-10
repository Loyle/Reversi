package com.utbm.reversi.controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.view.MenuFrame;
import com.utbm.reversi.view.ReversiFrame;

public class ReversiController 
{
	// On associe le controller à une fenêtre de jeu
	private final ReversiFrame reversiFrame;
	// Permet l'alternance entre le tour des blancs et le tour des noirs (0 : blancs, 1 : noirs)
	private int change = 1;
	
	public ReversiController(ReversiFrame reversiFrame)
	{
		this.reversiFrame = reversiFrame;
	}
	
	// Fonction appelée lors du clic sur une Cell
	public void onCellClicked(Cell cell) 
	{
		// On autorise le clic sur la Cell uniquement si elle est vierge (verte) et que les règles du Reversi sont suivies
		if ((cell.getState() == 0) && (isFollowingRules(cell) == true))
		{
			// On rempli les Cell que l'on a encadré
			fillCell(cell);
			
			// On utilise l'integer change pour alterner entre la pose d'un pion blanc et la pose d'un pion noir
			if (this.change == 1) 
			{
				cell.setState(2); // 2 = Blancs
				this.change = 0;
			}
			else if (this.change == 0) 
			{
				cell.setState(1); // 1 = Noirs
				this.change = 1;
			}
			// On actualise l'apparence de la case
			cell.updateState();
			// On recompte le score des deux joueurs à chaque nouveau pion posé
			compterScores();
			// On change l'indication sur la droite qui dit quel joueur joue le prochain coup
			reversiFrame.changeWhoPlay();
			
			// On teste si le jeu doit s'arrêter suite à la pose d'un nouveau pion
			if (isEnded() == true || isBlocked() == true) 
			{
				// On détruit le panel sur lequel la grille de Cell est générée
				this.reversiFrame.remove(this.reversiFrame.getGame());
				// On crée un nouveau panel pour remplacer celui que l'on vient de supprimer
				JPanel end = new JPanel();
				end.setBackground(Color.lightGray);
				end.setLayout(new GridBagLayout());
		        GridBagConstraints gbc = new GridBagConstraints();
		        // On associe un message de fin à ce panel en fonction de la manière dont la partie s'est terminée et en fonction du score
				JLabel endMsg = new JLabel();
				if (isEnded() == true) 
				{
					if (this.reversiFrame.getWhiteScore() == this.reversiFrame.getBlackScore()) 
					{
						endMsg.setText("The game ends in a tie !");
					}
					else if (this.reversiFrame.getWhiteScore() > this.reversiFrame.getBlackScore()) 
					{
						endMsg.setText("Whites win !");
					}
					else
					{
						endMsg.setText("Blacks win !");
					}
				}
				else if (isBlocked() == true) 
				{
					if (this.reversiFrame.getWhiteScore() == this.reversiFrame.getBlackScore()) 
					{
						endMsg.setText("No more possibilities ! It was a tie.");
					}
					else if (this.reversiFrame.getWhiteScore() > this.reversiFrame.getBlackScore()) 
					{
						endMsg.setText("No more possibilities ! Whites were winning.");
					}
					else
					{
						endMsg.setText("No more possibilities ! Blacks were winning.");
					}
				}
				
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
			    replay.addActionListener(e -> this.onReplayClicked(replay));
			    end.add(replay,gbc);
				this.reversiFrame.getContentPane().add(end,BorderLayout.CENTER);
				
				// Création du bouton pour revenir au menu
				JButton endBackToMenu = new JButton("Back to Menu");
				gbc.gridx=0;
			    gbc.gridy=6;
			    endBackToMenu.addActionListener(e -> this.onBackToMenuClicked(endBackToMenu));
			    end.add(endBackToMenu,gbc);
			    
			    // On place le panel de fin là où se trouvait la grille
				this.reversiFrame.getContentPane().add(end,BorderLayout.CENTER);
			}
			
		}
		
	}
	
	// Fonction associée au clic sur le bouton rejouer
	public void onReplayClicked(JButton replay) 
	{
		// Destruction de l'ancienne fenêtre et création d'un nouvelle
		ReversiFrame newFrame = new ReversiFrame(this.reversiFrame.getGridSize());
		newFrame.setTitle("Reversi Game");
        
		newFrame.setSize(700, 700);
		
		this.reversiFrame.dispose();
		

		newFrame.setVisible(true);
		newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	// Fonction associée au clic sur le bouton retour au menu
	public void onBackToMenuClicked(JButton backToMenu) 
	{
		// Suppression de la fenêtre de jeu et création d'une fenêtre de menu
		this.reversiFrame.dispose();
		
		MenuFrame menuFrame = new MenuFrame();
		
		menuFrame.setTitle("Menu - Reversi Game");
        
		menuFrame.setSize(700, 700);

		menuFrame.setVisible(true);
		menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	
	
	// Fonction permettant de compter le score actuel en parcourant toute la grille
	public void compterScores() 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		// On initialise les scores
		int whiteScore = 0;
		int blackScore = 0;
		
		// On parcourt toute la grille
		for (int i = 0; i < this.reversiFrame.getGridSize() ; i++) 
		{
			for (int j = 0; j < this.reversiFrame.getGridSize() ; j++) 
			{
				// On augmente le score de 1 pour chaque pion possédé par un joueur
				if (cellArray[j][i].getState() == 1) // State = 1   => White
				{
					whiteScore = whiteScore+1;
				}
				else if (cellArray[j][i].getState() == 2) // State= 2	=> Black
				{
					blackScore = blackScore+1;
				}
			}
		}
		
		// On associe le score à la fenêtre, pour pouvoir l'afficher sur le côté droit
		this.reversiFrame.setBlackScore(blackScore);
		this.reversiFrame.setWhiteScore(whiteScore);
		this.reversiFrame.updateScores();
		
	}
	
	
	
	// On appelle cette fonction pour voir si l'on peut place le pion ou nonà où on a cliqué
	public boolean isFollowingRules(Cell cell) 
	{
		// Ce tableau sert à compter, pour chaque direction (les 4 diagonales, le haut, le bas, la gauche, la droite), le nombre de pions placés entre le
		// nouveau pion et le pion de la même couleur le plus proche (ex :   N - B - newN    :   on aura un pion B à gauche, donc compteurIter[3]=1)
		// Indices du tableau compteurInter
		//	 0	 1 	 2
		//	 3   X   4
		//   5   6   7
		int compteurInter[] = {0,0,0,0,0,0,0,0};
		
		// On appelle toutes les fonctions permetant de tester, pour chaque direction, si on peut placer le pion
		// On appelle ces fonctions avant la boucle if pour réaliser tous les test et ne pas s'arrêter trop tôt en parcourant les "OU"
		// En effet, les fonctions ne s'exécutent plus dès qu'un "OU" est vrai
		boolean conditions[] = new boolean[8];
		conditions[0] = isFollowingRulesLeftTop(cell, compteurInter);
		conditions[1] = isFollowingRulesTop(cell, compteurInter);
		conditions[2] = isFollowingRulesRightTop(cell, compteurInter);
		conditions[3] = isFollowingRulesLeftMiddle(cell, compteurInter);
		conditions[4] = isFollowingRulesRightMiddle(cell, compteurInter);
		conditions[5] = isFollowingRulesLeftBottom(cell, compteurInter);
		conditions[6] = isFollowingRulesBottom(cell, compteurInter);
		conditions[7] = isFollowingRulesRightBottom(cell, compteurInter);
		
		// Si une seule des directions autorise la pose d'un pion, alors on autorise la pose d'un pion
		if ((conditions[0] == true && compteurInter[0]>0) || (conditions[1] == true && compteurInter[1]>0)  || (conditions[2] == true && compteurInter[2]>0)   || (conditions[3] == true && compteurInter[3]>0)   || (conditions[4] == true && compteurInter[4]>0)   || (conditions[5] == true && compteurInter[5]>0)   || (conditions[6] == true && compteurInter[6]>0)   || (conditions[7] == true && compteurInter[7]>0))
		{
			/*
			// Permet de voir à chaque pose l'état du tableau compterInter
			for (int i=0;i<8;i++) 
			{
				System.out.print("-"+compteurInter[i]);
			}
			System.out.println("");
			*/
			
			return true;	
		}
		else
		{
			return false;
		}
		
	}
	
	// Fonction permettant de changer la couleur et l'état des pions encadrés
	public void fillCell(Cell cell) 
	{
		// On appelle les mêmes foncitons de test que précédemment dans isFollowingRules
		int compteurInter[] = {0,0,0,0,0,0,0,0};
		
		boolean conditions[] = new boolean[8];
		conditions[0] = isFollowingRulesLeftTop(cell, compteurInter);
		conditions[1] = isFollowingRulesTop(cell, compteurInter);
		conditions[2] = isFollowingRulesRightTop(cell, compteurInter);
		conditions[3] = isFollowingRulesLeftMiddle(cell, compteurInter);
		conditions[4] = isFollowingRulesRightMiddle(cell, compteurInter);
		conditions[5] = isFollowingRulesLeftBottom(cell, compteurInter);
		conditions[6] = isFollowingRulesBottom(cell, compteurInter);
		conditions[7] = isFollowingRulesRightBottom(cell, compteurInter);
		
		// On teste une par une les conditions, pour réaliser l'encadrement
		if (conditions[0] == true) 
		{
			completeLeftTop(cell);
		}
		
		if (conditions[1] == true) 
		{
			completeTop(cell);
		}
		
		if (conditions[2] == true) 
		{
			completeRightTop(cell);
		}
		
		if (conditions[3] == true) 
		{
			completeLeftMiddle(cell);
		}
		
		if (conditions[4] == true) 
		{
			completeRightMiddle(cell);
		}
		
		if (conditions[5] == true) 
		{
			completeLeftBottom(cell);
		}
		
		if (conditions[6] == true) 
		{
			completeBottom(cell);
		}
		
		if (conditions[7] == true) 
		{
			completeRightBottom(cell);
		}
	}
	
	// =================================================================================================================================
	// Série de 8 fonctions permettant de tester, pour chaque direction, si la pose d'un pion est possible
	// =================================================================================================================================
	
	// En haut à gauche (seule celle-ci sera commentée)
	public boolean isFollowingRulesLeftTop(Cell cell, int[] compteurInter) 
	{
		// On va se servir du tableau de Cell pour se déplacer
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		// Servira à sauvegarder l'état du nouveau pion que l'on posera si la condition de isFollowingRules est remplie
		int tempState=0;
		// On sauvegarde donc l'état du futur pion
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		// Cette condition permet de ne pas fair un test qui sortirait du tableau et entrainerait une erreur (limites : de 0 à gridSize)
		if ((cell.getCoordX() > 0) && (cell.getCoordY() > 0))
        {
			// Si l'on rencontre une case vierge (verte), alors on ne peut pas poser de pion et il n'y a pas de pions intermédiaires
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].getState() == 0) 
            {
            	compteurInter[0] = 0;
                return false;
            }
			// Si l'on rencontre un pion de la couleur opposée à celle que l'on va poser, on rappelle la fonction en augmentant la valeur du compteur de pions
			// intermédiaires
            else if (cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].getState() != tempState)
            {
            	compteurInter[0] = compteurInter[0]+1;
            	return isFollowingRulesLeftTop(cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1], compteurInter);
            }
			// On peut poser le pion si l'on rencontre un pion de la même couleur (le problème d'un nombre de pions encadrés nul est résolu par le compteur)
            else
            {
            	return true;
            }
        }
		// Si l'on sort de la grille, on retourne évidemment faux pour dire que l'on nepeut rien poser
		else 
		{
			return false;
		}
	}
	
	// Les foncitons suivantes ne changent rien, à part les coordonnées
	
	public boolean isFollowingRulesTop(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=1;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordY() > 0)
        {
			if (cellArray[cell.getCoordX()][cell.getCoordY() - 1].getState() == 0)
            {
				compteurInter[1] = 0;
                return false;
            }
            else if (cellArray[cell.getCoordX()][cell.getCoordY() - 1].getState() != tempState)
            {
            	compteurInter[1] = compteurInter[1]+1;
                return isFollowingRulesTop(cellArray[cell.getCoordX()][cell.getCoordY() - 1], compteurInter);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}
	
	public boolean isFollowingRulesRightTop(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if ((cell.getCoordX()+1 < reversiFrame.getGridSize()) && (cell.getCoordY() > 0))
        {
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].getState() == 0) 
            {
				compteurInter[2] = 0;
                return false;
            }
			else if (cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].getState() != tempState)
            {
            	compteurInter[2] = compteurInter[2]+1;
            	return isFollowingRulesRightTop(cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1], compteurInter);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}
	
	public boolean isFollowingRulesLeftMiddle(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordX() > 0)
        {
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY()].getState() == 0) 
            {
				compteurInter[3] = 0;
                return false;
            }
			else if (cellArray[cell.getCoordX() - 1][cell.getCoordY()].getState() != tempState)
            {
            	compteurInter[3] = compteurInter[3]+1;
            	return isFollowingRulesLeftMiddle(cellArray[cell.getCoordX() - 1][cell.getCoordY()], compteurInter);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}

	public boolean isFollowingRulesRightMiddle(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordX()+1 < reversiFrame.getGridSize())
        {
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY()].getState() == 0) 
            {
				compteurInter[4] = 0;
                return false;
            }
            else if (cellArray[cell.getCoordX() + 1][cell.getCoordY()].getState() != tempState)
            {
            	compteurInter[4] = compteurInter[4]+1;
            	return isFollowingRulesRightMiddle(cellArray[cell.getCoordX() + 1][cell.getCoordY()], compteurInter);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}

	public boolean isFollowingRulesLeftBottom(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if ((cell.getCoordX() > 0) && (cell.getCoordY()+1 < reversiFrame.getGridSize()))
        {
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].getState() == 0) 
            {
				compteurInter[5] = 0;
            	return false;
            }
            else if (cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].getState() != tempState)
            {
            	compteurInter[5] = compteurInter[5]+1;
                return isFollowingRulesLeftBottom(cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1], compteurInter);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}

	public boolean isFollowingRulesBottom(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordY()+1 < reversiFrame.getGridSize())
        {
			if (cellArray[cell.getCoordX()][cell.getCoordY() + 1].getState() == 0) 
            {
				compteurInter[6] = 0;
                return false;
            }
            else if (cellArray[cell.getCoordX()][cell.getCoordY() + 1].getState() != tempState)
            {
            	compteurInter[6] = compteurInter[6]+1;
            	return isFollowingRulesBottom(cellArray[cell.getCoordX()][cell.getCoordY() + 1], compteurInter);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}
	
	public boolean isFollowingRulesRightBottom(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if ((cell.getCoordX()+1 < reversiFrame.getGridSize()) && (cell.getCoordY()+1 < reversiFrame.getGridSize()))
        {
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].getState() == 0)
            {
				compteurInter[7] = 0;
                return false;
            }
            else if (cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].getState() != tempState)
            {
            	compteurInter[7] = compteurInter[7]+1;
            	return isFollowingRulesRightBottom(cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1], compteurInter);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}
	
	

	// =================================================================================================================================
	// Série de 8 fonctions permettant de changer l'état et la couleur des pions encadrés
	// =================================================================================================================================
	
	// En haut à gauche
	// Seule celle-ci sera commentée
	public void completeLeftTop(Cell cell) 
	{
		// On aura besoin du tableau de Cell pour se déplacer
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		// On enregistre l'état du futur pion à poser
		int tempState=0;
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		// On prend garde à ne pas faire de test sur l'extérieur du tableau qi mènerait à une erreur
		if ((cell.getCoordX() > 0) && (cell.getCoordY() > 0))
        {
			// On change l'état et la couleur si le pion voisin est d'une couleur différente de celle du pion posé, et si il ya a un pion voisin (case non verte/vide)
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].getState() != tempState && cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].getState() != 0)
            {
				// On change l'état et on actualise la couleur
            	cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].setState(tempState);
            	cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].updateState();
            	// On rappelle la fonction pour retourner plusieurs pions
            	completeLeftTop(cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1]);
            }
        }
	}
	
	public void completeTop(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordY() > 0)
        {
			if (cellArray[cell.getCoordX()][cell.getCoordY() - 1].getState() != tempState && cellArray[cell.getCoordX()][cell.getCoordY() - 1].getState() != 0)
            {
            	cellArray[cell.getCoordX()][cell.getCoordY() - 1].setState(tempState);
            	cellArray[cell.getCoordX()][cell.getCoordY() - 1].updateState();
            	completeTop(cellArray[cell.getCoordX()][cell.getCoordY() - 1]);
            }
        }
	}
	
	public void completeRightTop(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if ((cell.getCoordX()+1 < reversiFrame.getGridSize()) && (cell.getCoordY() > 0))
        {
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].getState() != tempState && cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].getState() != 0)
            {
            	cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].setState(tempState);
            	cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].updateState();
            	completeRightTop(cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1]);
            }
        }
	}
	
	public void completeLeftMiddle(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordX() > 0)
        {
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY()].getState() != tempState && cellArray[cell.getCoordX() - 1][cell.getCoordY()].getState() != 0)
            {
            	cellArray[cell.getCoordX() - 1][cell.getCoordY()].setState(tempState);
            	cellArray[cell.getCoordX() - 1][cell.getCoordY()].updateState();
            	completeLeftMiddle(cellArray[cell.getCoordX() - 1][cell.getCoordY()]);
            }
        }
	}
	
	public void completeRightMiddle(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordX()+1 < reversiFrame.getGridSize())
        {
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY()].getState() != tempState && cellArray[cell.getCoordX() + 1][cell.getCoordY()].getState() != 0)
            {
            	cellArray[cell.getCoordX() + 1][cell.getCoordY()].setState(tempState);
            	cellArray[cell.getCoordX() + 1][cell.getCoordY()].updateState();
            	completeRightMiddle(cellArray[cell.getCoordX() + 1][cell.getCoordY()]);
            }
        }
	}
	
	public void completeLeftBottom(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if ((cell.getCoordX() > 0) && (cell.getCoordY()+1 < reversiFrame.getGridSize()))
        {
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].getState() != tempState && cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].getState() != 0)
            {
            	cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].setState(tempState);
            	cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].updateState();
            	completeLeftBottom(cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1]);
            }
        }
	}
	
	public void completeBottom(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordY()+1 < reversiFrame.getGridSize())
        {
			if (cellArray[cell.getCoordX()][cell.getCoordY() + 1].getState() != tempState && cellArray[cell.getCoordX()][cell.getCoordY() + 1].getState() != 0)
            {
            	cellArray[cell.getCoordX()][cell.getCoordY() + 1].setState(tempState);
            	cellArray[cell.getCoordX()][cell.getCoordY() + 1].updateState();
            	completeBottom(cellArray[cell.getCoordX()][cell.getCoordY() + 1]);
            }
        }
	}
	
	public void completeRightBottom(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if ((cell.getCoordX()+1 < reversiFrame.getGridSize()) && (cell.getCoordY()+1 < reversiFrame.getGridSize()))
        {
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].getState() != tempState && cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].getState() != 0)
            {
            	cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].setState(tempState);
            	cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].updateState();
            	completeRightBottom(cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1]);
            }
        }
	}
	
	
	
	
	
	
	
	
	
	// Fonction appelée pour tester si la grille est pleine (et donc la partie terminée)
	public boolean isEnded() 
	{
		// On a besoin de la grille de Cell que l'on va parcourir
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int idx=0;
		
		// On parcourt la grille et on incrémente à chaque case non vide/non verte
		for (int i=0;i<reversiFrame.getGridSize();i++) 
		{
			for (int j=0;j<reversiFrame.getGridSize();j++) 
			{
				if (cellArray[i][j].getState() != 0) 
				{
					idx=idx+1;
				}
			}
		}
		
		// Si le nombre de case non vides correspond au nombre de cases de la grille, la grilles est complète
		if (idx == reversiFrame.getGridSize()*reversiFrame.getGridSize()) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	// On teste s'il y a encore des cases où placer le futur pion (et donc si on peut continuer la partie ou pas)
	public boolean isBlocked() 
	{
		// On a besoin de la grille de Cell que l'on va parcourir
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int idx1=0;
		int idx2=0;
		
		// On parcourt toute la grille
		for (int i=0;i<reversiFrame.getGridSize();i++) 
		{
			for (int j=0;j<reversiFrame.getGridSize();j++) 
			{
				// Si la case est vide et que l'on ne peut rien y poser, on incrémente idx1
				if (cellArray[i][j].getState() == 0 && isFollowingRules(cellArray[i][j]) == false) 
				{
					idx1=idx1+1;
				}
				
				// Si la case est vide, on incrémente idx2
				if (cellArray[i][j].getState() == 0) 
				{
					idx2=idx2+1;
				}
					
			}
		}
		
		// S'il y a autant de case vide que de cases vides où on ne peut rien poser, alors le jeu est bien bloqué
		if (idx1 == idx2) 
		{
			return true;
		}
		// Sinon, on peut continuer à jouer
		else
		{
			return false;
		}
	}
	
	
	
	// Getter de l'alternance entre les 2 joueurs pour actualiser l'indicateur en haut à droite
	public int getChange() 
	{
		return change;
	}
	
	
	
	
}
