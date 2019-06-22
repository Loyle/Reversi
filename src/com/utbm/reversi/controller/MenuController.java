package com.utbm.reversi.controller;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.utbm.reversi.model.Player;
import com.utbm.reversi.view.MenuFrame;
import com.utbm.reversi.view.ReversiFrame;
import com.utbm.reversi.view.RulesFrame;


/**
 * <b>MenuController is the control class of the frame "menu" (MVC pattern).</b>
 * <p>
 * MenuController is defined by :
 * <ul>
 * <li>A "menu" view/frame (MVC pattern).</li>
 * <li>The grid size, that the user can change.</li>
 * <li>Players who will play to the game.</li>
 * <li>The label with the new player name.</li>
 * <li>The panel with the new player color.</li>
 * <li>The color that has been chosen before a new player is added.</li>
 * </ul>
 * </p>
 * 
 * @see MenuFrame
 * @see Player
 */
public class MenuController 
{
	// Controller is affected to a Panel
	private final MenuFrame menuFrame;
	//Size of the board
	private int gridSize = 8;

	
	private ArrayList<Player> players = new ArrayList<Player>();
	private JLabel[] playersLabel = new JLabel[9];
	private JPanel[] playersPanel = new JPanel[9];
	private Color actualColor;
	
    /**
     * MenuController constructor.
     * <p>
     * At the construction, the controller has to be link to a view/frame (MVC pattern).
     * The chosen color is intialized.
     * </p>
     * 
     * @param menuFrame
     *            The frame linked to the controller.
     * @param actualColor
     *            The color that has been chosen before a new player is added.
     * 
     * @see MenuFrame
     */
	public MenuController(MenuFrame menuFrame) 
	{
		this.menuFrame = menuFrame;
		this.actualColor = Color.black;
	}
	
	/**
     * Function called when someone click on Play button.
     * <p>
     * The frame is close and a new game is launched if there is more than 2 players.
     * All the informations from the menu are given to the new frame where the game takes place.
     * vide.
     * </p>
     * 
     * @param play
     *            The "Play" button that has been clicked.
     * 
     * @see MenuFrame
     * @see ReversiFrame
     */
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
	
	/**
     * Function called when cursor is moving to change size of the board.
     * <p>
     * The value of the grid size changes when the state of the slider changes.
     * The scale depends on the number of players (even or odd).
     * vide.
     * </p>
     * 
     * @param gridSizeSlider
     *            The slider that has been changed.
     * @param gridSizeLabel
     *            The label that show the actual size of the grid.
     * @param gridSize
     *            The size of the grid.
     *            
     * @see MenuFrame
     */
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
	
	/**
     * Function called when the "Add" button is clicked.
     * <p>
     * A new player is added to the list if his name is between 1 and 9 characters.
     * A list of players is created with a label for their name and a panel for their color.
     * The slider size adapts its scale in function of the number of players.
     * The actual color is randomly changed after the new player is added.
     * </p>
     * 
     * @param playersButton
     *            The "Add" button that has been clicked.
     *            
     * @see MenuFrame
     */
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
		
		Random rando = new Random();
		this.setActualColor(new Color(rando.nextInt(255),rando.nextInt(255),rando.nextInt(255)));
		this.menuFrame.getPlayersTextField().requestFocusInWindow();
		
	}
	
	
	/**
     * Function called when the "Remove" button is clicked.
     * <p>
     * This function remove all the players from the player list.
     * It also erases the visual list with labels and panels.
     * It reinitialise the grid size slider.
     * </p>
     * 
     * @param playersButton
     *            The "Remove" button that has been clicked.
     *            
     * @see MenuFrame
     */
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

	/**
     * Function called when the "color chooser" button is clicked.
     * <p>
     * Show a dialog where the user can choose a color for the new player.
     * vide.
     * </p>
     * 
     * @param playersColor
     *            The "color chooser" button that has been clicked.
     *            
     * @see MenuFrame
     * @see MenuController#onAddClicked(JButton)
     */
	public void onColorClicked(JButton playersColor) 
	{
        this.actualColor = JColorChooser.showDialog(null,"JColorChooser Sample", Color.black);
        playersColor.setBackground(actualColor);
	}
	
	/**
     * Function called when the "rules" button is clicked.
     * <p>
     * Show a frame where the rules of the game are explained.
     * vide.
     * </p>
     * 
     * @see MenuFrame
     */
	public void onRulesClicked() 
	{
		new RulesFrame();
	}
	
	/**
     * Function called when a new color is randomly chosen.
     * <p>
     * Set the color.
     * vide.
     * </p>
     * 
     * @param color
     *            The new color.
     *            
     * @see MenuFrame
     * @see MenuFrame
     */
	public void setActualColor(Color color) {
		this.actualColor = color;
		this.menuFrame.getPlayerColorButton().setBackground(color);
	}
}
