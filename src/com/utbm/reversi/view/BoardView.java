package com.utbm.reversi.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import com.utbm.reversi.controller.BoardController;

public class BoardView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6742286281896774332L;

	public BoardView() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.BLACK);
		this.setTitle("Reversi");
		this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setResizable(true);
		BoardPanel boardPanel = new BoardPanel();
		this.getContentPane().add(BorderLayout.CENTER, boardPanel);
		this.addMouseListener(new BoardController());
		
		
		
		this.setVisible(true);
	}
	
}
