package com.utbm.reversi.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.WindowConstants;

import com.utbm.reversi.controller.GameController;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.view.MenuFrame;

public class GameListener implements ActionListener {
	private GameController controller;
	
	public GameListener(GameController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source instanceof Cell) {
			this.controller.onCellClick((Cell) source);
		}
	}	
	
	
	
	// Fonction associée au clic sur le bouton retour au menu
	public void onBackToMenuClicked(JButton backToMenu) 
	{
			// Suppression de la fenêtre de jeu et création d'une fenêtre de menu
			this.controller.getFrame().dispose();
				
			MenuFrame menuFrame = new MenuFrame();
				
			menuFrame.setTitle("Menu - Reversi Game");
		       
			menuFrame.setSize(700, 700);

			menuFrame.setVisible(true);
			menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
