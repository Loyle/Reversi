package com.utbm.reversi.controller;

import com.utbm.reversi.model.Game;
import com.utbm.reversi.model.cells.Cell;

public class FollowingRules {
	private Cell cell;
	private Game game;
	private int x;
	private int y;
	private int size;
	private int countInter[];

	public FollowingRules(Game game, Cell cell) {
		this.game = game;
		this.cell = cell;

		this.x = this.cell.getCoordX();
		this.y = this.cell.getCoordY();
		this.size = this.game.getBoard().getSize();
	}

	public int[] findInter() {
		int inter[] = { 0, 0, 0, 0, 0, 0, 0, 0 };

		// startX et startY sont les coordonn�es d'une Cell voisine
		int startX;
		int startY;

		int count;
		// Top
		startX = this.x;
		startY = this.y - 1;
		count = 0;

		// TANT QUE
		// Les coordonn�es du voisin sont dans la grille
		// ET QUE
		// Ce voisin a un pion pos� sur lui/a un propri�taire
		while ((startY >= 0) && (this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null)) {
			// SI
			// Le propri�taire de cette case est celui qui joue en ce moment
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {

				inter[0] = count;
				break;
			} else {
				count++;
			}

			startY--;
		}

		// Bottom
		startX = this.x;
		startY = this.y + 1;
		count = 0;
		while (startY < this.size && this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[1] = count;
				break;
			} else {
				count++;
			}

			startY++;
		}

		// Left
		startX = this.x - 1;
		startY = this.y;
		count = 0;
		while (startX >= 0 && this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[2] = count;
				break;
			} else {
				count++;
			}

			startX--;
		}

		// Right
		startX = this.x + 1;
		startY = this.y;
		count = 0;
		while (startX < this.size && this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[3] = count;
				break;
			} else {
				count++;
			}

			startX++;
		}

		// Top-Right
		startX = this.x + 1;
		startY = this.y - 1;
		count = 0;
		while (startX < this.size && startY >= 0
				&& this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[4] = count;
				break;
			} else {
				count++;
			}

			startX++;
			startY--;
		}

		// Bottom-Right
		startX = this.x + 1;
		startY = this.y + 1;
		count = 0;
		while (startX < this.size && startY < this.size
				&& this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[5] = count;
				break;
			} else {
				count++;
			}
			startX++;
			startY++;
		}

		// Bottom-Left
		startX = this.x - 1;
		startY = this.y + 1;
		count = 0;
		while (startX >= 0 && startY < this.size
				&& this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[6] = count;
				break;
			} else {
				count++;
			}
			startX--;
			startY++;
		}

		// Top-Left
		startX = this.x - 1;
		startY = this.y - 1;
		count = 0;
		while (startX >= 0 && startY >= 0 && this.game.getBoard().getBoardCells()[startX][startY].getOwner() != null) {
			if (this.game.getBoard().getBoardCells()[startX][startY].getOwner()
					.equals(this.game.getCurrentPlayer()) == true) {
				inter[7] = count;
				break;
			} else {
				count++;
			}
			startX--;
			startY--;
		}

		return inter;
	}

	// La case est jouable si on a pu compter un pion adverse entre le nouveau pion
	// et un autre pion de la m�me couleur
	public boolean isPlayable() {
		this.countInter = this.findInter();

		for (int i = 0; i < 8; i++) {
			if (this.countInter[i] > 0) {
				return true;
			}
		}
		return false;
	}

	// Permet de placer le nouveau pion et de retourner les pions impact�s
	public void replaceCell() {
		// TOP
		// Change le propri�taire de toutes les cases encadr�es, et actualise (ce qui
		// place le pion)
		
		this.cell.setOwner(this.game.getCurrentPlayer());

		for (int i = 0; i <= this.countInter[0]; i++) {
			if (this.game.getBoard().getBoardCells()[this.x][this.y - i].isEnabled()
					&& this.game.getBoard().getBoardCells()[this.x][this.y - i].equals(this.cell) == false) {
				this.game.getBoard().getBoardCells()[this.x][this.y - i].setOwner(this.game.getCurrentPlayer());
				this.game.getBoard().getBoardCells()[this.x][this.y - i].updateState();
			}
		}
		// BOTTOM
		for (int i = 0; i <= this.countInter[1]; i++) {
			if (this.game.getBoard().getBoardCells()[this.x][this.y + i].isEnabled()
					&& this.game.getBoard().getBoardCells()[this.x][this.y + i].equals(this.cell) == false) {
				this.game.getBoard().getBoardCells()[this.x][this.y + i].setOwner(this.game.getCurrentPlayer());
				this.game.getBoard().getBoardCells()[this.x][this.y + i].updateState();
			}
		}
		// LEFT
		for (int i = 0; i <= this.countInter[2]; i++) {
			if (this.game.getBoard().getBoardCells()[this.x - i][this.y].isEnabled()
					&& this.game.getBoard().getBoardCells()[this.x - i][this.y].equals(this.cell) == false) {
				this.game.getBoard().getBoardCells()[this.x - i][this.y].setOwner(this.game.getCurrentPlayer());
				this.game.getBoard().getBoardCells()[this.x - i][this.y].updateState();
			}
		}
		// RIGHT
		for (int i = 0; i <= this.countInter[3]; i++) {
			if (this.game.getBoard().getBoardCells()[this.x + i][this.y].isEnabled()
					&& this.game.getBoard().getBoardCells()[this.x + i][this.y].equals(this.cell) == false) {
				this.game.getBoard().getBoardCells()[this.x + i][this.y].setOwner(this.game.getCurrentPlayer());
				this.game.getBoard().getBoardCells()[this.x + i][this.y].updateState();
			}
		}

		// TOP-RIGHT
		for (int i = 0; i <= this.countInter[4]; i++) {
			if (this.game.getBoard().getBoardCells()[this.x + i][this.y - i].isEnabled()
					&& this.game.getBoard().getBoardCells()[this.x + i][this.y - i].equals(this.cell) == false) {
				this.game.getBoard().getBoardCells()[this.x + i][this.y - i].setOwner(this.game.getCurrentPlayer());
				this.game.getBoard().getBoardCells()[this.x + i][this.y - i].updateState();
			}
		}
		// BOTTOM-RIGHT
		for (int i = 0; i <= this.countInter[5]; i++) {
			if (this.game.getBoard().getBoardCells()[this.x + i][this.y + i].isEnabled()
					&& this.game.getBoard().getBoardCells()[this.x + i][this.y + i].equals(this.cell) == false) {
				this.game.getBoard().getBoardCells()[this.x + i][this.y + i].setOwner(this.game.getCurrentPlayer());
				this.game.getBoard().getBoardCells()[this.x + i][this.y + i].updateState();
			}
		}

		// BOTTOM-LEFT
		for (int i = 0; i <= this.countInter[6]; i++) {
			if (this.game.getBoard().getBoardCells()[this.x - i][this.y + i].isEnabled()
					&& this.game.getBoard().getBoardCells()[this.x - i][this.y + i].equals(this.cell) == false) {
				this.game.getBoard().getBoardCells()[this.x - i][this.y + i].setOwner(this.game.getCurrentPlayer());
				this.game.getBoard().getBoardCells()[this.x - i][this.y + i].updateState();
			}
		}
		// TOP-LEFT
		for (int i = 0; i <= this.countInter[7]; i++) {
			if (this.game.getBoard().getBoardCells()[this.x - i][this.y - i].isEnabled()
					&& this.game.getBoard().getBoardCells()[this.x - i][this.y - i].equals(this.cell) == false) {
				this.game.getBoard().getBoardCells()[this.x - i][this.y - i].setOwner(this.game.getCurrentPlayer());
				this.game.getBoard().getBoardCells()[this.x - i][this.y - i].updateState();
			}
		}
	}
}

// Fonction appel�e pour tester si la grille est pleine (et donc la partie
// termin�e)
/*
 * public boolean isEnded() { // On a besoin de la grille de Cell que l'on va
 * parcourir OldCell[][] cellArray = this.reversiFrame.getCellArray();
 * 
 * int idx=0;
 * 
 * // On parcourt la grille et on incr�mente � chaque case non vide/non verte
 * for (int i=0;i<reversiFrame.getGridSize();i++) { for (int
 * j=0;j<reversiFrame.getGridSize();j++) { if (cellArray[i][j].getState() != 0)
 * { idx=idx+1; } } }
 * 
 * // Si le nombre de case non vides correspond au nombre de cases de la grille,
 * la grilles est compl�te if (idx ==
 * reversiFrame.getGridSize()*reversiFrame.getGridSize()) { return true; } else
 * { return false; } }
 */

// On teste s'il y a encore des cases o� placer le futur pion (et donc si on
// peut continuer la partie ou pas)
/*
 * 
 * 
 * 
 * // Getter de l'alternance entre les 2 joueurs pour actualiser l'indicateur en
 * haut � droite public int getChange() { return change; }
 */

// Fonction appel�e lors du clic sur une Cell
/*
 * public void onCellClicked(OldCell cell) { // On autorise le clic sur la Cell
 * uniquement si elle est vierge (verte) et que les r�gles du Reversi sont
 * suivies if ((cell.getState() == 0) && (isFollowingRules(cell) == true)) { //
 * On rempli les Cell que l'on a encadr� fillCell(cell);
 * 
 * // On utilise l'integer change pour alterner entre la pose d'un pion blanc et
 * la pose d'un pion noir if (this.change == 1) { cell.setState(2); // 2 =
 * Blancs this.change = 0; } else if (this.change == 0) { cell.setState(1); // 1
 * = Noirs this.change = 1; } // On actualise l'apparence de la case
 * cell.updateState(); // On recompte le score des deux joueurs � chaque nouveau
 * pion pos� compterScores(); // On change l'indication sur la droite qui dit
 * quel joueur joue le prochain coup reversiFrame.changeWhoPlay();
 * 
 * // On teste si le jeu doit s'arr�ter suite � la pose d'un nouveau pion if
 * (isEnded() == true || isBlocked() == true) { // On d�truit le panel sur
 * lequel la grille de Cell est g�n�r�e
 * this.reversiFrame.remove(this.reversiFrame.getGame()); // On cr�e un nouveau
 * panel pour remplacer celui que l'on vient de supprimer JPanel end = new
 * JPanel(); end.setBackground(Color.lightGray); end.setLayout(new
 * GridBagLayout()); GridBagConstraints gbc = new GridBagConstraints(); // On
 * associe un message de fin � ce panel en fonction de la mani�re dont la partie
 * s'est termin�e et en fonction du score JLabel endMsg = new JLabel(); if
 * (isEnded() == true) { if (this.reversiFrame.getWhiteScore() ==
 * this.reversiFrame.getBlackScore()) {
 * endMsg.setText("The game ends in a tie !"); } else if
 * (this.reversiFrame.getWhiteScore() > this.reversiFrame.getBlackScore()) {
 * endMsg.setText("Whites win !"); } else { endMsg.setText("Blacks win !"); } }
 * else if (isBlocked() == true) { if (this.reversiFrame.getWhiteScore() ==
 * this.reversiFrame.getBlackScore()) {
 * endMsg.setText("No more possibilities ! It was a tie."); } else if
 * (this.reversiFrame.getWhiteScore() > this.reversiFrame.getBlackScore()) {
 * endMsg.setText("No more possibilities ! Whites were winning."); } else {
 * endMsg.setText("No more possibilities ! Blacks were winning."); } }
 * 
 * gbc.gridx=0; gbc.gridy=0; end.add(endMsg,gbc);
 * 
 * // ADD SPACE for (int addSpace = 1 ; addSpace<5 ; addSpace++) { gbc.gridx =
 * 0; gbc.gridy = addSpace; end.add(new JLabel(" "),gbc); }
 * 
 * // Cr�ation du bouton permettant de recommencer la partie avec la m�me taille
 * de grille JButton replay = new JButton("Replay"); gbc.gridx=0; gbc.gridy=5;
 * replay.addActionListener(e -> this.onReplayClicked(replay));
 * end.add(replay,gbc);
 * this.reversiFrame.getContentPane().add(end,BorderLayout.CENTER);
 * 
 * // Cr�ation du bouton pour revenir au menu JButton endBackToMenu = new
 * JButton("Back to Menu"); gbc.gridx=0; gbc.gridy=6;
 * endBackToMenu.addActionListener(e ->
 * this.onBackToMenuClicked(endBackToMenu)); end.add(endBackToMenu,gbc);
 * 
 * // On place le panel de fin l� o� se trouvait la grille
 * this.reversiFrame.getContentPane().add(end,BorderLayout.CENTER); }
 * 
 * }
 * 
 * }
 */