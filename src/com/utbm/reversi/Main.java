package com.utbm.reversi;

import javax.swing.JFrame;
import com.utbm.reversi.view.MenuFrame;

public class Main {


	public static void main(String[] args) 
	{
		
		//==========================================================================================================================
		// Ici, on cr�e un objet MenuFrame (c'est la fen�tre o� le menu est affich�)
		//==========================================================================================================================
		JFrame menuFrame = new MenuFrame();

		menuFrame.setVisible(true);		
	}
}
