package com.utbm.reversi;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.powers.Power;
import com.utbm.reversi.model.Board;
import com.utbm.reversi.view.BoardView;

public class Main {


	public static void main(String[] args) 
	{
		//new BoardView();
		final JFrame frame = new BoardView();
        frame.setTitle("Reversi Game");
        
        frame.setSize(500, 500);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
        
        
        
        
        
		Player player = new Player("Marco", new Color(0,255,25));
		
		System.out.println("Nom : " + player.getName());
		
		player.addRandomPower();
		player.addRandomPower();
		player.addRandomPower();
		player.addRandomPower();
		
		for(Power power : player.getPowers()) {
			System.out.println("Power file : " + power.getIcon());
		}
		
	}
}
