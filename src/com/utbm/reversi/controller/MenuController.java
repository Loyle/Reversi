package com.utbm.reversi.controller;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	
	private ArrayList<Player> players = new ArrayList<Player>();
	
	//MenuController has to be linked with a menuFrame
	public MenuController(MenuFrame menuFrame) 
	{
		this.menuFrame = menuFrame;
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
			
			
			
			
			
			
			String color = this.menuFrame.getPlayersComboBox().getSelectedItem().toString();
			this.menuFrame.getPlayersComboBox().removeItem(color);
			
			switch (color) {
				case "White":
					this.players.add(new Player(input,Color.white));
					break;
				case "Black":
					this.players.add(new Player(input,Color.black));
					break;
				case "Red":
					this.players.add(new Player(input,Color.red));
					break;
				case "Blue":
					this.players.add(new Player(input,Color.blue));
					break;
				case "Yellow":
					this.players.add(new Player(input,Color.yellow));
					break;
				case "Green":
					this.players.add(new Player(input,Color.green));
					break;
				case "Gray":
					this.players.add(new Player(input,Color.gray));
					break;
				case "Pink":
					this.players.add(new Player(input,Color.pink));
					break;
				case "Cyan":
					this.players.add(new Player(input,Color.cyan));
					break;
				case "Orange":
					this.players.add(new Player(input,Color.orange));
					break;
			}

			
			
			if (this.players.size() <= 3)
			{
				if (this.players.size()==1) 
				{
			        this.menuFrame.getPlayersLabel1().setText(this.menuFrame.getPlayersLabel1().getText() + input + " (" + color + ")");
				}
				else
				{
			        this.menuFrame.getPlayersLabel1().setText(this.menuFrame.getPlayersLabel1().getText() + "  ;  " + input + " (" + color + ")");
				}
			}
			else if (this.players.size() > 3 && this.players.size() <= 6) 
			{
				if (this.players.size()==4) 
				{
			        this.menuFrame.getPlayersLabel2().setText(this.menuFrame.getPlayersLabel2().getText() + input + " (" + color + ")");
				}
				else
				{
			        this.menuFrame.getPlayersLabel2().setText(this.menuFrame.getPlayersLabel2().getText() + "  ;  " +  input + " (" + color + ")");
				}
			}
			else if (this.players.size() > 6 && this.players.size() <= 9) 
			{
				if (this.players.size()==7) 
				{
			        this.menuFrame.getPlayersLabel3().setText(this.menuFrame.getPlayersLabel3().getText() + input + " (" + color + ")");
				}
				else
				{
			        this.menuFrame.getPlayersLabel3().setText(this.menuFrame.getPlayersLabel3().getText() + "  ;  " +  input + " (" + color + ")");
				}
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
	}
	
	public void onRemoveClicked(JButton playersButton) 
	{
		this.menuFrame.getError().setText(" ");
		for (int i=0;i<this.menuFrame.getCouleurs().length;i++) 
		{
			this.menuFrame.getPlayersComboBox().removeItem(this.menuFrame.getCouleurs()[i]);
		}
		for (int j=0;j<this.menuFrame.getCouleurs().length;j++) 
		{
			this.menuFrame.getPlayersComboBox().addItem(this.menuFrame.getCouleurs()[j]);
		}
	    this.menuFrame.getPlayersLabel1().setText(" ");
	    this.menuFrame.getPlayersLabel2().setText(" ");
	    this.menuFrame.getPlayersLabel3().setText(" ");
	    this.menuFrame.getPlayersTextField().setText("");
	    

		this.menuFrame.getGridSizeSlider().setMinimum(4);
		this.menuFrame.getGridSizeSlider().setMaximum(20);
		this.gridSize=8;
		this.menuFrame.getGridSizeSlider().setValue(8);
		this.menuFrame.getGridSizeLabel().setText("Value : 08");
		
	    
	    this.players.removeAll(players);
		
	}

	public void onRulesClicked() 
	{
		new RulesFrame();
	}
	
}
