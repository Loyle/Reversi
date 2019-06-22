package com.utbm.reversi;

import javax.swing.JFrame;
import com.utbm.reversi.view.MenuFrame;

public class Main {

	/**
	 * Main function of the project automatically call
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//==========================================================================================================================
		//Here, we create an object MenuFrame (it's the frame where menu appears)
		//==========================================================================================================================
		JFrame menuFrame = new MenuFrame();

		menuFrame.setVisible(true);		
	}
}