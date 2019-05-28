package com.utbm.reversi.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.utbm.reversi.controller.BoardController;

public class BoardView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6742286281896774332L;
	final static int cellSize = 70;

	public BoardView() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.BLACK);
		this.setTitle("Reversi");
		this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setResizable(true);
		this.setMinimumSize(new Dimension(13+8*(cellSize+5), 42+8*(cellSize+5)));
		BoardPanel boardPanel = new BoardPanel();
		this.getContentPane().add(BorderLayout.CENTER, boardPanel);
		this.addMouseListener(new BoardController());
		
		
		
		this.setVisible(true);
	}
	
}
