package com.utbm.reversi.view;

import javax.swing.JFrame;

public class BoardView extends JFrame {
	
	public BoardView() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Reversi");
		this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		
		
		
		this.setVisible(true);
		
	}
	
}
