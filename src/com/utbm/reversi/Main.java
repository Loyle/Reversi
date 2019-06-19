package com.utbm.reversi;

import javax.swing.JFrame;
import com.utbm.reversi.view.MenuFrame;

public class Main {


	public static void main(String[] args) 
	{
		
		//==========================================================================================================================
		//Here, we create an object MenuFrame (it's the frame where menu appears)
		//==========================================================================================================================
		JFrame menuFrame = new MenuFrame();

		menuFrame.setVisible(true);		
	}
}