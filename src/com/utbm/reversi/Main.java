package com.utbm.reversi;

import javax.swing.JFrame;

import com.utbm.reversi.animations.TestAnimation;
import com.utbm.reversi.view.MenuFrame;

public class Main {


	public static void main(String[] args) 
	{
		
		//==========================================================================================================================
		// Ici, on crée un objet MenuFrame (c'est la fenêtre où le menu est affiché)
		//==========================================================================================================================
		
		Thread ani = new Thread(new TestAnimation(3));
		ani.start();
		
		final JFrame menuFrame = new MenuFrame();

		menuFrame.setVisible(true);		
	}
}
