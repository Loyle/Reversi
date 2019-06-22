package com.utbm.reversi.listeners;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.utbm.reversi.controller.GameController;
import com.utbm.reversi.model.Player;
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

	// Function associated with clicks on Back button
	public void onBackToMenuClicked(JButton backToMenu) {
		// Deleting game Panel and Creating a new Menupanel
		this.controller.getFrame().dispose();

		MenuFrame menuFrame = new MenuFrame();

		menuFrame.setVisible(true);
	}
	/**
	 * Create a new frame of rules if Rules's button is clicked
	 */
	public void onRulesClicked() 
	{
		new RulesFrame();
	}

	// Function associated with clicks on Replay button 
	/**
	 * Recreate a game frame if Replay button is clicked
	 * @param replay
	 * 		Replay button
	 */
	public void onReplayClicked(JButton replay) {
		// Deleting old game Panel and Creating a new one

		this.controller.getFrame().dispose();
		
		for(Player player : this.controller.getGame().getPlayers()) {
			player.setLose(false);
			player.getPowers().clear();
		}
		ReversiFrame newFrame = new ReversiFrame(this.controller.getGame().getBoard().getSize(),this.controller.getGame().getPowerNumber(),this.controller.getGame().getObstaclesNumber(),this.controller.getGame().getTrapsNumber(),this.controller.getGame().getPlayers());
		newFrame.setTitle("Reversi Game");

		newFrame.setSize(700, 700);

		this.controller.getFrame().dispose();

		newFrame.setVisible(true);
		newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * Launch if a Power button is clicked during the game (it's only a graphic method)
	 * @param btn
	 * 		Button Power clicked
	 */
	public void onPowerClick(PowerButton btn) {
		if(this.controller.getGame().getCurrentPlayer().getUsingPower() == null || this.controller.getGame().getCurrentPlayer().getUsingPower().equals(btn.getPower()) == false) {
			for(PowerButton power : this.controller.getFrame().getPowerListBtn()) {
				power.setBackground(null);
			}
			
			this.controller.getGame().getCurrentPlayer().setUsingPower(btn.getPower());
			btn.setBackground(Color.RED);
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image image = btn.getPower().getIcon().getImage();
			Cursor cursor = toolkit.createCustomCursor(image , new Point(0,0), "img");
			this.controller.getFrame().setCursor(cursor);
		}
		else {
			this.controller.getGame().getCurrentPlayer().setUsingPower(null);
			this.controller.getFrame().setCursor(Cursor.getDefaultCursor());
			btn.setBackground(null);
		}
	}
}
