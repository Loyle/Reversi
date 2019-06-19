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

	// On associe un controller � la fen�tre
	private final MenuController menuController = new MenuController(this);
	// On d�clare un panel dans lequel on placera les boutons du menu
	private JPanel menuBackground;
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
    private String[] couleurs = {"White","Black","Red","Blue","Yellow","Green","Gray","Pink","Cyan","Orange"};
	
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
		// Cr�ation du panel
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
		// On associe le clic sur le bouton play � cette fonction du controller
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
        
        
        // SOUS-PANEL : AJOUT DES JOUEURS
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
        
        
        
        
        

        // SOUS-PANEL : JOUEURS ENREGISTR�S
        JPanel registeredPlayersPanel = new JPanel();
        registeredPlayersPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbcRegisteredPlayers = new GridBagConstraints();
        registeredPlayersPanel.setBackground(Color.white);

        gbcPlayers.gridx = 0;
        gbcPlayers.gridy = 1;
        playersPanel.add(new JLabel("     "),gbcPlayers);
        
        gbcPlayers.gridx = 0;
        gbcPlayers.gridy = 2;
        playersPanel.add(registeredPlayersPanel,gbcPlayers);
        
        JLabel label = new JLabel("Registered players :");
        gbcRegisteredPlayers.gridx = 0;
        gbcRegisteredPlayers.gridy = 0;
        registeredPlayersPanel.add(label,gbcRegisteredPlayers);
        this.playersLabel1 = new JLabel(" ");
        gbcRegisteredPlayers.gridx = 0;
        gbcRegisteredPlayers.gridy = 1;
        registeredPlayersPanel.add(this.playersLabel1,gbcRegisteredPlayers);
        this.playersLabel2 = new JLabel(" ");
        gbcRegisteredPlayers.gridx = 0;
        gbcRegisteredPlayers.gridy = 2;
        registeredPlayersPanel.add(this.playersLabel2,gbcRegisteredPlayers);
        this.playersLabel3 = new JLabel(" ");
        gbcRegisteredPlayers.gridx = 0;
        gbcRegisteredPlayers.gridy = 3;
        registeredPlayersPanel.add(this.playersLabel3,gbcRegisteredPlayers);
        
        
        
        
        
        

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
        // On cr�e un panel dans lequel on mettra la Slide Bar et la label qui affiche la taille de la grille
        final JPanel gridSizePanel = new JPanel();
        gridSizePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbcGridSize = new GridBagConstraints();
		
        gridSizePanel.setBackground(Color.white);
        // On cr�e la un contour et un titre pour ce panel
        gridSizePanel.setBorder(BorderFactory.createTitledBorder("Grid Size :"));
        // On lui donne la dimension voulue
        gridSizePanel.setPreferredSize(new Dimension(400, 100));
        gbc.gridx = 0;
        decalage++;
        gbc.gridy = decalage;
        this.menuBackground.add(gridSizePanel,gbc);
        
        // On cr�e la slide bar
        this.gridSizeSlider = new JSlider();
        // Minimum et maximum des valeurs possibles dans la slide bar
        this.gridSizeSlider.setMinimum(4);
        this.gridSizeSlider.setMaximum(20);
        // Afficher la graduation
        this.gridSizeSlider.setPaintTicks(true);
        // Afficher les nombres
        this.gridSizeSlider.setPaintLabels(true);
        // Taille graduation
        this.gridSizeSlider.setMinorTickSpacing(2);
        // �cart entre la valeur minimum et la valeur maximum
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
        
        
        // Label qui affiche la taille de la grille
        this.gridSizeLabel = new JLabel();
        this.gridSizeLabel.setText("Value : 08");
        gbcGridSize.gridx = 2;
        gbcGridSize.gridy = 0;
        gridSizePanel.add(this.gridSizeLabel,gbcGridSize);
        
        // On associe le changement d'�tat de la slide bar � la fonction du controller
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

	public String[] getCouleurs() {
		return couleurs;
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
	
	

	
	
	
	
	
	
	
}
