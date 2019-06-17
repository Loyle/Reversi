package com.utbm.reversi;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.utbm.reversi.view.MenuFrame;

public class Main {


	public static void main(String[] args) 
	{
		//new BoardView();
		
		//==========================================================================================================================
		// Ici, on crée un objet MenuFrame (c'est la fenêtre où le menu est affiché)
		//==========================================================================================================================
		final JFrame menuFrame = new MenuFrame();

		menuFrame.setVisible(true);
		
        
        
        
        
        
		/*Player player = new Player("Marco", new Color(0,255,25));
		
		System.out.println("Nom : " + player.getName());
		
		player.addRandomPower();
		player.addRandomPower();
		player.addRandomPower();
		player.addRandomPower();
		
		for(Power power : player.getPowers()) {
			System.out.println("Power file : " + power.getIcon());
		}*/
		
	}
}
