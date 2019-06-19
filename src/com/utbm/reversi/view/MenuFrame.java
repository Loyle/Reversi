package com.utbm.reversi.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.utbm.reversi.controller.MenuController;

public class MenuFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1328756640538025065L;

	// Associating controller to the frame
	private final MenuController menuController = new MenuController(this);
	// Declaring a panel is which one we will place Menu Buttons
	private JPanel menuBackground;
	private JPanel registeredPlayersPanel;
	private JLabel gridSizeLabel;
	private JSlider gridSizeSlider;
	private JLabel playersLabel1;
	private JLabel playersLabel2;
	private JLabel playersLabel3;
	private JLabel error;
	private JTextField playersTextField;
	private JComboBox<String> playersComboBox;
	private JComboBox<String> powersComboBox;
	private JComboBox<String> obstaclesComboBox;
	private JComboBox<String> trapsComboBox;
	private GridBagConstraints gbcRegisteredPlayers;
	
	public MenuFrame() 
	{

		this.setTitle("Menu - Reversi Game");

		this.setMinimumSize(new Dimension(900,900));

		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
        // ==========================================================================================================================
		// BACKGROUND
        // ==========================================================================================================================
		// Creating panel
		this.menuBackground = new JPanel();
		this.menuBackground.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//this.setLocationRelativeTo(null);
		menuBackground.setBackground(Color.white);

		// ==========================================================================================================================
		// PLAY BUTTON
		// ==========================================================================================================================
		final JButton play = new JButton("Play");
		final JButton rules = new JButton("Rules");
		int decalage=0;
		// Clicking on the play button is associated with this controller function
		play.addActionListener(e -> menuController.onPlayClicked(play));
        gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
        this.menuBackground.add(play,gbc);

        gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
        this.menuBackground.add(new JLabel("     "),gbc);

		rules.addActionListener(e -> menuController.onRulesClicked());
        gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
        this.menuBackground.add(rules,gbc);
		
        // ADD SPACE
        for (int addSpace = 0 ; addSpace<4 ; addSpace++) 
        {
            gbc.gridx = 0;
            decalage++;
            gbc.gridy = decalage;
            if (addSpace == 2) 
            {
            	this.error = new JLabel(" ");
                this.menuBackground.add(this.error,gbc);
            }
            else
            {
                this.menuBackground.add(new JLabel(" "),gbc);
            }
        }
        
        
        // ==========================================================================================================================
        // PLAYERS
        // ==========================================================================================================================
        JPanel playersPanel = new JPanel();
        playersPanel.setBackground(Color.white);
        playersPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbcPlayers = new GridBagConstraints();
        playersPanel.setBorder(BorderFactory.createTitledBorder("Players :"));
        playersPanel.setPreferredSize(new Dimension(400, 150));
        gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
        this.menuBackground.add(playersPanel,gbc);
        
        
        // SUB-PANEL : ADDING PLAYERS
        JPanel addPlayersPanel = new JPanel();
        addPlayersPanel.setBackground(Color.white);

        gbcPlayers.gridx = 0;
        gbcPlayers.gridy = 0;
        playersPanel.add(addPlayersPanel,gbcPlayers);
        
        addPlayersPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbcAddPlayers = new GridBagConstraints();
        
        this.playersTextField = new JTextField(10);
        
        gbcAddPlayers.gridx = 0;
        gbcAddPlayers.gridy = 0;
        addPlayersPanel.add(playersTextField,gbcAddPlayers);
        
        
        gbcAddPlayers.gridx = 1;
        gbcAddPlayers.gridy = 0;
        addPlayersPanel.add(new JLabel("     "),gbcAddPlayers);
        
        
        JButton playersColor = new JButton(" ");
        playersColor.setBackground(Color.black);
        gbcAddPlayers.gridx = 2;
        gbcAddPlayers.gridy = 0;
        addPlayersPanel.add(playersColor,gbcAddPlayers);
        playersColor.addActionListener(e -> menuController.onColorClicked(playersColor));

        gbcAddPlayers.gridx = 3;
        gbcAddPlayers.gridy = 0;
        addPlayersPanel.add(new JLabel("     "),gbcAddPlayers);
        

        JButton playersAddButton = new JButton("Add");
        gbcAddPlayers.gridx = 4;
        gbcAddPlayers.gridy = 0;
        addPlayersPanel.add(playersAddButton,gbcAddPlayers);
        playersAddButton.addActionListener(e -> menuController.onAddClicked(playersAddButton));

    	
        

        gbcAddPlayers.gridx = 5;
        gbcAddPlayers.gridy = 0;
        addPlayersPanel.add(new JLabel("     "),gbcAddPlayers);
        
        JButton playersRemoveButton = new JButton("Remove all");
        gbcAddPlayers.gridx = 6;
        gbcAddPlayers.gridy = 0;
        addPlayersPanel.add(playersRemoveButton,gbcAddPlayers);
        playersRemoveButton.addActionListener(e -> menuController.onRemoveClicked(playersRemoveButton));
        
        
        
        // SOUS-PANEL : REGISTERED PLAYERS
        JPanel msgRegistered = new JPanel();
        msgRegistered.setBackground(Color.white);
        msgRegistered.setLayout(new GridBagLayout());
		GridBagConstraints gbcMsgRegistered = new GridBagConstraints();
        gbcPlayers.gridx = 0;
        gbcPlayers.gridy = 1;
        playersPanel.add(new JLabel("     "),gbcPlayers);
        gbcPlayers.gridx = 0;
        gbcPlayers.gridy = 2;
        playersPanel.add(msgRegistered,gbcPlayers);
        gbcMsgRegistered.gridx=0;
        gbcMsgRegistered.gridy=0;
        msgRegistered.add(new JLabel("Registered players :"),gbcMsgRegistered);
        
        


        // SOUS-PANEL : JOUEURS ENREGISTRÉS
        this.registeredPlayersPanel = new JPanel();
        registeredPlayersPanel.setLayout(new GridBagLayout());
		this.gbcRegisteredPlayers = new GridBagConstraints();
        registeredPlayersPanel.setBackground(Color.white);

        
        gbcRegisteredPlayers.gridx = 0;
        gbcRegisteredPlayers.gridy = 1;
        registeredPlayersPanel.add(new JLabel(" "),gbcRegisteredPlayers);
        gbcRegisteredPlayers.gridx = 0;
        gbcRegisteredPlayers.gridy = 2;
        registeredPlayersPanel.add(new JLabel(" "),gbcRegisteredPlayers);
        gbcRegisteredPlayers.gridx = 0;
        gbcRegisteredPlayers.gridy = 3;
        registeredPlayersPanel.add(new JLabel(" "),gbcRegisteredPlayers);
        

        gbcPlayers.gridx = 0;
        gbcPlayers.gridy = 3;
        playersPanel.add(registeredPlayersPanel,gbcPlayers);
        
        
        
        
        
        

        // ADD SPACE
        for (int addSpace = 0 ; addSpace<3 ; addSpace++) 
        {
            gbc.gridx = 0;
            decalage++;
            gbc.gridy = decalage;
            this.menuBackground.add(new JLabel(" "),gbc);
        }
        
        

        // ==========================================================================================================================
        // POWERS
        // ==========================================================================================================================
        JPanel powersPanel = new JPanel();
        powersPanel.setBackground(Color.white);
        powersPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbcPowers = new GridBagConstraints();
		powersPanel.setBorder(BorderFactory.createTitledBorder("Powers :"));
		powersPanel.setPreferredSize(new Dimension(400, 80));
        gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
        this.menuBackground.add(powersPanel,gbc);
        
        
        JLabel powersLabel = new JLabel("Number of powers per player :");
        gbcPowers.gridx = 0;
        gbcPowers.gridy = 0;
        powersPanel.add(powersLabel,gbcPowers);
        

        gbcPowers.gridx = 1;
        gbcPowers.gridy = 0;
        powersPanel.add(new JLabel("               "),gbcPowers);
        
        String[] powerNumbers = {"0","1","2","3","4","5","6","7","8","9","10"};
        this.powersComboBox = new JComboBox<String>(powerNumbers); 
        this.powersComboBox.setSelectedIndex(0);
        gbcPowers.gridx = 2;
        gbcPowers.gridy = 0;
        powersPanel.add(powersComboBox,gbcPowers);
        
        // ADD SPACE
        for (int addSpace = 0 ; addSpace<3 ; addSpace++) 
        {
            gbc.gridx = 0;
            decalage++;
            gbc.gridy = decalage;
            this.menuBackground.add(new JLabel(" "),gbc);
        }
        
       
        
        
        
        
        
        // ==========================================================================================================================
        // OBSTACLES AND TRAPS
        // ==========================================================================================================================
        JPanel obstaclesAndTrapsPanel = new JPanel();
        obstaclesAndTrapsPanel.setBackground(Color.white);
        obstaclesAndTrapsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbcObstaclesAndTraps = new GridBagConstraints();
		obstaclesAndTrapsPanel.setBorder(BorderFactory.createTitledBorder("Obstacles and traps :"));
		obstaclesAndTrapsPanel.setPreferredSize(new Dimension(400, 120));
        gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
        this.menuBackground.add(obstaclesAndTrapsPanel,gbc);
        
        
        JLabel obstaclesLabel = new JLabel("Number of obstacles :");
        gbcObstaclesAndTraps.gridx = 0;
        gbcObstaclesAndTraps.gridy = 0;
        obstaclesAndTrapsPanel.add(obstaclesLabel,gbcObstaclesAndTraps);
        gbcObstaclesAndTraps.gridx = 1;
        gbcObstaclesAndTraps.gridy = 0;
        obstaclesAndTrapsPanel.add(new JLabel("               "),gbcObstaclesAndTraps);
        String[] obstaclesNumbers = {"0","1","2","3","4","5","6","7","8","9","10"};
        this.obstaclesComboBox = new JComboBox<String>(obstaclesNumbers); 
        this.obstaclesComboBox.setSelectedIndex(0);
        gbcObstaclesAndTraps.gridx = 2;
        gbcObstaclesAndTraps.gridy = 0;
        obstaclesAndTrapsPanel.add(this.obstaclesComboBox,gbcObstaclesAndTraps);

        gbcObstaclesAndTraps.gridx = 0;
        gbcObstaclesAndTraps.gridy = 2;
        obstaclesAndTrapsPanel.add(new JLabel(" "),gbcObstaclesAndTraps);
        
        JLabel trapsLabel = new JLabel("Number of traps :");
        gbcObstaclesAndTraps.gridx = 0;
        gbcObstaclesAndTraps.gridy = 3;
        obstaclesAndTrapsPanel.add(trapsLabel,gbcObstaclesAndTraps);
        gbcObstaclesAndTraps.gridx = 1;
        gbcObstaclesAndTraps.gridy = 3;
        obstaclesAndTrapsPanel.add(new JLabel("               "),gbcObstaclesAndTraps);
        String[] trapsNumbers = {"0","1","2","3","4","5","6","7","8","9","10"};
        this.trapsComboBox = new JComboBox<String>(trapsNumbers); 
        this.trapsComboBox.setSelectedIndex(0);
        gbcObstaclesAndTraps.gridx = 2;
        gbcObstaclesAndTraps.gridy = 3;
        obstaclesAndTrapsPanel.add(this.trapsComboBox,gbcObstaclesAndTraps);
        
        // ADD SPACE
        for (int addSpace = 0 ; addSpace<3 ; addSpace++) 
        {
            gbc.gridx = 0;
            decalage++;
            gbc.gridy = decalage;
            this.menuBackground.add(new JLabel(" "),gbc);
        }
        
        
        
        
        
        

        // ==========================================================================================================================
        // GRID SIZE
        // ==========================================================================================================================
        // We create a panel in which we will put the Slide Bar and the label that displays the size of the grid
        final JPanel gridSizePanel = new JPanel();
        gridSizePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbcGridSize = new GridBagConstraints();
		
        gridSizePanel.setBackground(Color.white);
        // We create the outline and title for this panel
        gridSizePanel.setBorder(BorderFactory.createTitledBorder("Grid Size :"));
        // It is given the required dimension
        gridSizePanel.setPreferredSize(new Dimension(400, 100));
        gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
        this.menuBackground.add(gridSizePanel,gbc);
        
        // Creating slide bar
        this.gridSizeSlider = new JSlider();
        // Minimum and maximum possible values in the slide bar
        this.gridSizeSlider.setMinimum(4);
        this.gridSizeSlider.setMaximum(20);
        // Display the scale
        this.gridSizeSlider.setPaintTicks(true);
        // Display numbers
        this.gridSizeSlider.setPaintLabels(true);
        // Size betwenn graduation
        this.gridSizeSlider.setMinorTickSpacing(2);
        // Difference between the minimum and maximum value
        this.gridSizeSlider.setMajorTickSpacing(16);
        // Valeur de base
        this.gridSizeSlider.setValue(8);
        this.gridSizeSlider.setBackground(Color.white);
        gbcGridSize.gridx = 0;
        gbcGridSize.gridy = 0;
        gridSizePanel.add(this.gridSizeSlider,gbcGridSize);


        gbcGridSize.gridx = 1;
        gbcGridSize.gridy = 0;
        gridSizePanel.add(new JLabel("               "),gbcGridSize);
        
        
        // Label which displays the size of the board
        this.gridSizeLabel = new JLabel();
        this.gridSizeLabel.setText("Value : 08");
        gbcGridSize.gridx = 2;
        gbcGridSize.gridy = 0;
        gridSizePanel.add(this.gridSizeLabel,gbcGridSize);
        
        // We associate the change of state of the slide bar with the controller function
        gridSizeSlider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent event)
            {
            	menuController.onSliderStateChanged(gridSizeSlider, gridSizeLabel, ((JSlider)event.getSource()).getValue());
            }
        });    
        

        
      
        
        
        
        
        
        
        
		this.getContentPane().add(menuBackground);
	}

	public JLabel getPlayersLabel1() {
		return playersLabel1;
	}
	public JLabel getPlayersLabel2() {
		return playersLabel2;
	}
	public JLabel getPlayersLabel3() {
		return playersLabel3;
	}

	public JTextField getPlayersTextField() {
		return playersTextField;
	}

	public JComboBox<String> getPlayersComboBox() {
		return playersComboBox;
	}


	public JLabel getError() {
		return error;
	}

	public JComboBox<String> getPowersComboBox() {
		return powersComboBox;
	}

	public JLabel getGridSizeLabel() {
		return gridSizeLabel;
	}

	public JSlider getGridSizeSlider() {
		return gridSizeSlider;
	}

	public JComboBox<String> getObstaclesComboBox() {
		return obstaclesComboBox;
	}

	public JComboBox<String> getTrapsComboBox() {
		return trapsComboBox;
	}

	public GridBagConstraints getGbcRegisteredPlayers() {
		return gbcRegisteredPlayers;
	}

	public JPanel getRegisteredPlayersPanel() {
		return registeredPlayersPanel;
	}


	
	
}
