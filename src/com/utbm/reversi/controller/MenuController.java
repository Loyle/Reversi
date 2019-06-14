package com.utbm.reversi.controller;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;

import com.utbm.reversi.model.Player;
import com.utbm.reversi.view.MenuFrame;
import com.utbm.reversi.view.ReversiFrame;

public class MenuController 
{
	// Le controller est associé à une fenêtre
	private final MenuFrame menuFrame;
	// Taille de la grille de jeu
	private int gridSize = 8;
	
	private ArrayList<Player> players = new ArrayList<Player>();
	
	// Le MenuController doit être lié à une MenuFrame
	public MenuController(MenuFrame menuFrame) 
	{
		this.menuFrame = menuFrame;
	}
	
	// Fonction appelé lorsque l'on clique sur le bouton play
	public void onPlayClicked(JButton play) 
	{
		if (this.players.size() < 2) 
		{
			this.menuFrame.getError().setText("There is not enough players to start the game !");
		}
		else
		{
			// On supprime la fenêtre actuelle (le menu)
			this.menuFrame.dispose();
			
			// On lance une nouvelle partie
			@SuppressWarnings("unused")
			ReversiFrame reversi = new ReversiFrame(this.gridSize);
		}
	}
	
	// Fonction appelée lorsque l'on déplace le curseur pour modifier la taille de la grille
	public void onSliderStateChanged(JSlider gridSizeSlider, JLabel gridSizeLabel, int gridSize) 
	{

		// La taille de la grille ne peut être qu'un nombre pair (logique vu les 4 pièces de départ)
    	if (gridSize%2 !=0)
    	{
    		gridSize=gridSize-1;
    	}
    	
    	// La condition permet d'ajouter un 0 aux nombres à un chiffre pour garder un label à 2 chiffres, qui ne se décale donc pas
    	if (gridSize < 10)
		{
        	gridSizeLabel.setText("     Value : 0" + gridSize);
		}
    	else
    	{
    		gridSizeLabel.setText("     Value : " + gridSize);
    	}
    	
    	// On enregistre la taille de la grille
    	this.gridSize = gridSize;
    	
    	
	}
	
	public void onAddClicked(JButton playersButton) 
	{
		String input = this.menuFrame.getPlayersTextField().getText();
		
		if (!input.equals("") && input.length() < 10 && this.players.size() < 9) 
		{
			String color = this.menuFrame.getPlayersComboBox().getSelectedItem().toString();
			this.menuFrame.getPlayersComboBox().removeItem(color);
			

			this.players.add(new Player(input));
			
			if (this.players.size() <= 3)
			{
		        this.menuFrame.getPlayersLabel1().setText(this.menuFrame.getPlayersLabel1().getText() + input + " (" + color + ")  ;  ");
			}
			else if (this.players.size() > 3 && this.players.size() <= 6) 
			{
		        this.menuFrame.getPlayersLabel2().setText(this.menuFrame.getPlayersLabel2().getText() + input + " (" + color + ")  ;  ");
			}
			else if (this.players.size() > 6 && this.players.size() <= 9) 
			{
				if (this.players.size()==9) 
				{
			        this.menuFrame.getPlayersLabel3().setText(this.menuFrame.getPlayersLabel3().getText() + input + " (" + color + ")");
					
				}
				else
				{
			        this.menuFrame.getPlayersLabel3().setText(this.menuFrame.getPlayersLabel3().getText() + input + " (" + color + ")  ;  ");
					
				}
			}
			this.menuFrame.getPlayersTextField().setText("");
			
			if (this.players.size() == 2) 
			{
				this.menuFrame.getError().setText(" ");
			}
		}
		else if (input.equals(""))
		{
			this.menuFrame.getError().setText("Not enough letters !");
		}
		else if (input.length() >= 10)
		{
			this.menuFrame.getError().setText("Too many letters !");
			this.menuFrame.getPlayersTextField().setText("");
		}
		else if (this.players.size() >= 9) 
		{
			this.menuFrame.getError().setText("The maximum of players is 9 !");
			this.menuFrame.getPlayersTextField().setText("");
		}
	}
	
	public void onRemoveClicked(JButton playersButton) 
	{
		this.menuFrame.getError().setText(" ");
		for (int i=0;i<this.menuFrame.getCouleurs().length;i++) 
		{
			this.menuFrame.getPlayersComboBox().removeItem(this.menuFrame.getCouleurs()[i]);
		}
		for (int j=0;j<this.menuFrame.getCouleurs().length;j++) 
		{
			this.menuFrame.getPlayersComboBox().addItem(this.menuFrame.getCouleurs()[j]);
		}
	    this.menuFrame.getPlayersLabel1().setText(" ");
	    this.menuFrame.getPlayersLabel2().setText(" ");
	    this.menuFrame.getPlayersLabel3().setText(" ");
	    this.menuFrame.getPlayersTextField().setText("");
	    
	    this.players.removeAll(players);
		
	}
	
}
