package com.utbm.reversi.controller;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.utbm.reversi.model.Player;
import com.utbm.reversi.view.MenuFrame;
import com.utbm.reversi.view.ReversiFrame;
import com.utbm.reversi.view.RulesFrame;

public class MenuController 
{
	// Controller is affected to a Panel
	private final MenuFrame menuFrame;
	//Size of the board
	private int gridSize = 8;
	
	private Color actualColor;
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private JLabel[] playersLabel = new JLabel[9];
	private JPanel[] playersPanel = new JPanel[9];
	
	//MenuController has to be linked with a menuFrame
	public MenuController(MenuFrame menuFrame) 
	{
		this.menuFrame = menuFrame;
		this.actualColor = Color.black;
	}
	
	// Function called when someone click on Play button
	public void onPlayClicked(JButton play) 
	{
		int powerNumber = this.menuFrame.getPowersComboBox().getSelectedIndex();
		int obstaclesNumber = this.menuFrame.getObstaclesComboBox().getSelectedIndex();
		int trapsNumber = this.menuFrame.getTrapsComboBox().getSelectedIndex();		
		
		if (this.players.size() < 2) 
		{
			this.menuFrame.getError().setText("There is not enough players to start the game !");
		}
		else
		{
			// Deleting menuFrame
			this.menuFrame.dispose();
			
			//Launch a new game
			new ReversiFrame(this.gridSize, powerNumber,obstaclesNumber,trapsNumber,players);
		}
	}
	
	// Function called when cursor is moving to change size of the board
	public void onSliderStateChanged(JSlider gridSizeSlider, JLabel gridSizeLabel, int gridSize) 
	{

		if (this.players.size()%2 !=0) // If number of player is odd
		{
			if (gridSize%2 ==0) // If size of the board is even
	    	{
	    		gridSize=gridSize+1;
	    	}
		}
		else // If number of player is even
		{
			if (gridSize%2 !=0) // IF size of the board is odd
	    	{
	    		gridSize=gridSize-1;
	    	}
		}
    	
    	
    	// Condition permits to add a "0" at numbers with one digit to keep a 2-digit-design
    	if (gridSize < 10)
		{
        	gridSizeLabel.setText("Value : 0" + gridSize);
		}
    	else
    	{
    		gridSizeLabel.setText("Value : " + gridSize);
    	}
    	
    	// Saving size of the board
    	this.gridSize = gridSize;
    	
    	
	}
	
	public void onAddClicked(JButton playersButton) 
	{
		String input = this.menuFrame.getPlayersTextField().getText();
		
		if (!input.equals("") && input.length() < 10 && this.players.size() < 9) 
		{
			switch (this.players.size()) 
			{
				case 0:
					this.menuFrame.getGridSizeSlider().setMinimum(5);
					this.menuFrame.getGridSizeSlider().setMaximum(21);
					this.gridSize++;
					break;
				case 1:
					this.menuFrame.getGridSizeSlider().setMinimum(4);
					this.menuFrame.getGridSizeSlider().setMaximum(20);
					this.gridSize--;
					break;
				case 2:
					this.menuFrame.getGridSizeSlider().setMinimum(5);
					this.menuFrame.getGridSizeSlider().setMaximum(21);
					this.gridSize++;
					break;
				case 3:
					this.menuFrame.getGridSizeSlider().setMinimum(6);
					this.menuFrame.getGridSizeSlider().setMaximum(22);
					if (gridSize<=6) 
					{
						this.gridSize=6;
					}
					else
					{
						this.gridSize--;
					}
					break;
				case 4:
					this.menuFrame.getGridSizeSlider().setMinimum(7);
					this.menuFrame.getGridSizeSlider().setMaximum(23);
					this.gridSize++;
					break;
				case 5:
					this.menuFrame.getGridSizeSlider().setMinimum(8);
					this.menuFrame.getGridSizeSlider().setMaximum(24);
					if (gridSize<=8) 
					{
						this.gridSize=8;
					}
					else
					{
						this.gridSize--;
					}
					break;
				case 6:
					this.menuFrame.getGridSizeSlider().setMinimum(9);
					this.menuFrame.getGridSizeSlider().setMaximum(25);
					this.gridSize++;
					break;
				case 7:
					this.menuFrame.getGridSizeSlider().setMinimum(10);
					this.menuFrame.getGridSizeSlider().setMaximum(26);
					if (gridSize<=10) 
					{
						this.gridSize=10;
					}
					else
					{
						this.gridSize--;
					}
					break;
				case 8:
					this.menuFrame.getGridSizeSlider().setMinimum(11);
					this.menuFrame.getGridSizeSlider().setMaximum(27);
					this.gridSize++;
					break;
				case 9:
					this.menuFrame.getGridSizeSlider().setMinimum(12);
					this.menuFrame.getGridSizeSlider().setMaximum(28);
					if (gridSize<=12) 
					{
						this.gridSize=12;
					}
					else
					{
						this.gridSize--;
					}
					break;
			}
			
			if (this.gridSize < 10)
			{
	        	this.menuFrame.getGridSizeLabel().setText("Value : 0" + this.gridSize);
			}
	    	else
	    	{
	    		this.menuFrame.getGridSizeLabel().setText("Value : " + this.gridSize);
	    	}
			
			
			
			
			this.players.add(new Player(input,this.actualColor));
			Random rando = new Random();
			this.actualColor = new Color(rando.nextInt(255),rando.nextInt(255),rando.nextInt(255));
			this.menuFrame.getPlayerColorButton().setBackground(this.actualColor);
			
			if (this.players.size() > 0)
			{
				this.playersPanel[this.players.size()-1] = new JPanel();
				this.playersPanel[this.players.size()-1].setMinimumSize(new Dimension(10,10));
				this.playersPanel[this.players.size()-1].setMaximumSize(new Dimension(10,10));
				this.playersPanel[this.players.size()-1].setBackground(this.actualColor);
				switch (this.players.size())
				{
					case 1:
						this.menuFrame.getGbcRegisteredPlayers().gridx=0;
						this.menuFrame.getGbcRegisteredPlayers().gridy=1;
						break;
					case 2:
						this.menuFrame.getGbcRegisteredPlayers().gridx=3;
						this.menuFrame.getGbcRegisteredPlayers().gridy=1;
						break;
					case 3:
						this.menuFrame.getGbcRegisteredPlayers().gridx=6;
						this.menuFrame.getGbcRegisteredPlayers().gridy=1;
						break;
					case 4:
						this.menuFrame.getGbcRegisteredPlayers().gridx=0;
						this.menuFrame.getGbcRegisteredPlayers().gridy=2;
						break;
					case 5:
						this.menuFrame.getGbcRegisteredPlayers().gridx=3;
						this.menuFrame.getGbcRegisteredPlayers().gridy=2;
						break;
					case 6:
						this.menuFrame.getGbcRegisteredPlayers().gridx=6;
						this.menuFrame.getGbcRegisteredPlayers().gridy=2;
						break;
					case 7:
						this.menuFrame.getGbcRegisteredPlayers().gridx=0;
						this.menuFrame.getGbcRegisteredPlayers().gridy=3;
						break;
					case 8:
						this.menuFrame.getGbcRegisteredPlayers().gridx=3;
						this.menuFrame.getGbcRegisteredPlayers().gridy=3;
						break;
					case 9:
						this.menuFrame.getGbcRegisteredPlayers().gridx=6;
						this.menuFrame.getGbcRegisteredPlayers().gridy=3;
						break;
				}
				this.menuFrame.getRegisteredPlayersPanel().add(this.playersPanel[this.players.size()-1],this.menuFrame.getGbcRegisteredPlayers());
				switch (this.players.size())
				{
					case 1:
						this.menuFrame.getGbcRegisteredPlayers().gridx=1;
						this.menuFrame.getGbcRegisteredPlayers().gridy=1;
						break;
					case 2:
						this.menuFrame.getGbcRegisteredPlayers().gridx=4;
						this.menuFrame.getGbcRegisteredPlayers().gridy=1;
						break;
					case 3:
						this.menuFrame.getGbcRegisteredPlayers().gridx=7;
						this.menuFrame.getGbcRegisteredPlayers().gridy=1;
						break;
					case 4:
						this.menuFrame.getGbcRegisteredPlayers().gridx=1;
						this.menuFrame.getGbcRegisteredPlayers().gridy=2;
						break;
					case 5:
						this.menuFrame.getGbcRegisteredPlayers().gridx=4;
						this.menuFrame.getGbcRegisteredPlayers().gridy=2;
						break;
					case 6:
						this.menuFrame.getGbcRegisteredPlayers().gridx=7;
						this.menuFrame.getGbcRegisteredPlayers().gridy=2;
						break;
					case 7:
						this.menuFrame.getGbcRegisteredPlayers().gridx=1;
						this.menuFrame.getGbcRegisteredPlayers().gridy=3;
						break;
					case 8:
						this.menuFrame.getGbcRegisteredPlayers().gridx=4;
						this.menuFrame.getGbcRegisteredPlayers().gridy=3;
						break;
					case 9:
						this.menuFrame.getGbcRegisteredPlayers().gridx=7;
						this.menuFrame.getGbcRegisteredPlayers().gridy=3;
						break;
				}
				this.playersLabel[this.players.size()-1] = new JLabel(" "+input);
				this.menuFrame.getRegisteredPlayersPanel().add(this.playersLabel[this.players.size()-1],this.menuFrame.getGbcRegisteredPlayers());
				switch (this.players.size())
				{
					case 1:
						this.menuFrame.getGbcRegisteredPlayers().gridx=2;
						this.menuFrame.getGbcRegisteredPlayers().gridy=1;
						break;
					case 2:
						this.menuFrame.getGbcRegisteredPlayers().gridx=5;
						this.menuFrame.getGbcRegisteredPlayers().gridy=1;
						break;
					case 4:
						this.menuFrame.getGbcRegisteredPlayers().gridx=2;
						this.menuFrame.getGbcRegisteredPlayers().gridy=2;
						break;
					case 5:
						this.menuFrame.getGbcRegisteredPlayers().gridx=5;
						this.menuFrame.getGbcRegisteredPlayers().gridy=2;
						break;
					case 7:
						this.menuFrame.getGbcRegisteredPlayers().gridx=2;
						this.menuFrame.getGbcRegisteredPlayers().gridy=3;
						break;
					case 8:
						this.menuFrame.getGbcRegisteredPlayers().gridx=5;
						this.menuFrame.getGbcRegisteredPlayers().gridy=3;
						break;
				}
				this.menuFrame.getRegisteredPlayersPanel().add(new JLabel("                 "),this.menuFrame.getGbcRegisteredPlayers());
				
			}
			
			this.menuFrame.getPlayersTextField().setText("");
			
			if (this.players.size() == 2) 
			{
				this.menuFrame.getError().setText(" ");
			}
		}
		else if (input.equals(""))
		{
			this.menuFrame.getError().setText("Not enough letters !");
		}
		else if (input.length() >= 10)
		{
			this.menuFrame.getError().setText("Too many letters !");
			this.menuFrame.getPlayersTextField().setText("");
		}
		else if (this.players.size() >= 9) 
		{
			this.menuFrame.getError().setText("The maximum of players is 9 !");
			this.menuFrame.getPlayersTextField().setText("");
		}
		
		this.menuFrame.getPlayersTextField().requestFocusInWindow();
		
	}
	
	public void onRemoveClicked(JButton playersButton) 
	{
		this.menuFrame.getError().setText(" ");
	    this.menuFrame.getPlayersTextField().setText("");

		for (int i=0;i<9;i++) 
		{
			if (this.playersLabel[i] != null)
			{
				this.menuFrame.getRegisteredPlayersPanel().remove(this.playersLabel[i]);
			}
			
			if (this.playersPanel[i] != null) 
			{
				this.menuFrame.getRegisteredPlayersPanel().remove(this.playersPanel[i]);
			}
		}
		onAddClicked(playersButton);
	    
	   
		this.menuFrame.getGridSizeSlider().setMinimum(4);
		this.menuFrame.getGridSizeSlider().setMaximum(20);
		this.gridSize=8;
		this.menuFrame.getGridSizeSlider().setValue(8);
		this.menuFrame.getGridSizeLabel().setText("Value : 08");
		
	    
	    this.players.removeAll(players);
		
	}

	public void onColorClicked(JButton playersColor) 
	{
        this.actualColor = JColorChooser.showDialog(null,"JColorChooser Sample", Color.black);
        playersColor.setBackground(actualColor);
	}
	
	public void onRulesClicked() 
	{
		new RulesFrame();
	}
	
}
