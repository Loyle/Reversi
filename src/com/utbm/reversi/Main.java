package com.utbm.reversi;

import java.awt.Color;

import com.utbm.reversi.model.Player;
import com.utbm.reversi.model.powers.Power;
//import com.utbm.reversi.model.Board;
import com.utbm.reversi.view.BoardView;

public class Main {


	public static void main(String[] args) 
	{
		//new BoardView();
		
		
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
