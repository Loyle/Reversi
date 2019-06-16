package com.utbm.reversi.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.WindowConstants;

import com.utbm.reversi.controller.GameController;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.view.MenuFrame;
import com.utbm.reversi.view.PowerButton;
import com.utbm.reversi.view.ReversiFrame;
import com.utbm.reversi.view.RulesFrame;

public class GameListener implements ActionListener {
	private GameController controller;

	public GameListener(GameController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source instanceof Cell) {
			this.controller.onCellClick((Cell) source);
		}
	}

	// Fonction associée au clic sur le bouton retour au menu
	public void onBackToMenuClicked(JButton backToMenu) {
		// Suppression de la fenêtre de jeu et création d'une fenêtre de menu
		this.controller.getFrame().dispose();

		MenuFrame menuFrame = new MenuFrame();

		menuFrame.setTitle("Menu - Reversi Game");

		menuFrame.setSize(700, 700);

		menuFrame.setVisible(true);
		menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void onRulesClicked() 
	{
		new RulesFrame();
	}

	// Fonction associée au clic sur le bouton rejouer
	public void onReplayClicked(JButton replay) {
		// Destruction de l'ancienne fenêtre et création d'un nouvelle

		this.controller.getFrame().dispose();
		
		
		ReversiFrame newFrame = new ReversiFrame(this.controller.getGame().getBoard().getSize(),this.controller.getFrame().getGame().getNumberPower(),this.controller.getFrame().getGame().getPlayers());
		newFrame.setTitle("Reversi Game");

		newFrame.setSize(700, 700);

		this.controller.getFrame().dispose();

		newFrame.setVisible(true);
		newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	
	public void onPowerClick(PowerButton btn) {
		if(this.controller.getGame().getCurrentPlayer().getUsingPower() == null || this.controller.getGame().getCurrentPlayer().getUsingPower().equals(btn.getPower()) == false) {
			this.controller.getGame().getCurrentPlayer().setUsingPower(btn.getPower());
			btn.setBackground(Color.RED);
		}
		else {
			this.controller.getGame().getCurrentPlayer().setUsingPower(null);
			btn.setBackground(null);
		}
	}
}
