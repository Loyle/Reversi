package com.utbm.reversi.controller;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import com.utbm.reversi.view.MenuFrame;
import com.utbm.reversi.view.ReversiFrame;

public class MenuController 
{
	// Le controller est associ� � une fen�tre
	private final MenuFrame menuFrame;
	// Taille de la grille de jeu
	private int gridSize = 8;
	
	// Le MenuController doit �tre li� � une MenuFrame
	public MenuController(MenuFrame menuFrame) 
	{
		this.menuFrame = menuFrame;
	}
	
	// Fonction appel� lorsque l'on clique sur le bouton play
	public void onPlayClicked(JButton play) 
	{
		// On supprime la fen�tre actuelle (le menu)
		this.menuFrame.dispose();
		
		// On lance une nouvelle partie
		@SuppressWarnings("unused")
		ReversiFrame reversi = new ReversiFrame(this.gridSize);
	}
	
	// Fonction appel�e lorsque l'on d�place le curseur pour modifier la taille de la grille
	public void onSliderStateChanged(JSlider gridSizeSlider, JLabel gridSizeLabel, int gridSize) 
	{

		// La taille de la grille ne peut �tre qu'un nombre pair (logique vu les 4 pi�ces de d�part)
    	if (gridSize%2 !=0)
    	{
    		gridSize=gridSize-1;
    	}
    	
    	// La condition permet d'ajouter un 0 aux nombres � un chiffre pour garder un label � 2 chiffres, qui ne se d�cale donc pas
    	if (gridSize < 10)
		{
        	gridSizeLabel.setText("     Valeur actuelle : 0" + gridSize);
		}
    	else
    	{
    		gridSizeLabel.setText("     Valeur actuelle : " + gridSize);
    	}
    	
    	// On enregistre la taille de la grille
    	this.gridSize = gridSize;
    	
    	
	}
	

}
