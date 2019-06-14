package com.utbm.reversi.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
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
	private JLabel playersLabel;
	private JTextField playersTextField;
	private JComboBox playersComboBox;
    private String[] couleurs = {"Blanc","Noir","Rouge","Bleu","Jaune"};
	
	public MenuFrame() 
	{
		// BACKGROUND
		// Cr�ation du panel
		this.menuBackground = new JPanel();
		this.menuBackground.setLayout(new GridBagLayout());
		//this.setLocationRelativeTo(null);
		GridBagConstraints gbc = new GridBagConstraints();
		menuBackground.setBackground(Color.white);

		// BOUTON PLAY
		final JButton play = new JButton("Play");
		// On associe le clic sur le bouton play � cette fonction du controller
		play.addActionListener(e -> menuController.onPlayClicked(play));
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.menuBackground.add(play,gbc);
		
        // ADD SPACE
        for (int addSpace = 1 ; addSpace<10 ; addSpace++) 
        {
            gbc.gridx = 0;
            gbc.gridy = addSpace;
            this.menuBackground.add(new JLabel(" "),gbc);
        }
        
        
        
        // ==========================================================================================================================
        // TAILLE GRILLE
        // On cr�e un panel dans lequel on mettra la Slide Bar et la label qui affiche la taille de la grille
        final JPanel gridSizePanel = new JPanel();
        gridSizePanel.setBackground(Color.white);
        // On cr�e la un contour et un titre pour ce panel
        gridSizePanel.setBorder(BorderFactory.createTitledBorder("Grid Size :"));
        // On lui donne la dimension voulue
        gridSizePanel.setPreferredSize(new Dimension(400, 80));
        gbc.gridx = 0;
        gbc.gridy = 10;
        this.menuBackground.add(gridSizePanel,gbc);
        
        // On cr�e la slide bar
        JSlider gridSizeSlider = new JSlider();
        // Minimum et maximum des valeurs possibles dans la slide bar
        gridSizeSlider.setMinimum(4);
        gridSizeSlider.setMaximum(20);
        // Afficher la graduation
        gridSizeSlider.setPaintTicks(true);
        // Afficher les nombres
        gridSizeSlider.setPaintLabels(true);
        // Taille graduation
        gridSizeSlider.setMinorTickSpacing(2);
        // �cart entre la valeur minimum et la valeur maximum
        gridSizeSlider.setMajorTickSpacing(16);
        // Valeur de base
        gridSizeSlider.setValue(8);
        gridSizeSlider.setBackground(Color.white);
        gridSizePanel.add(gridSizeSlider,BorderLayout.EAST);

        // Label qui affiche la taille de la grille
        JLabel gridSizeLabel = new JLabel();
        gridSizeLabel.setText("     Valeur actuelle : 08");
        gridSizePanel.add(gridSizeLabel,BorderLayout.WEST);
        
        // On associe le changement d'�tat de la slide bar � la fonction du controller
        gridSizeSlider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent event)
            {
            	menuController.onSliderStateChanged(gridSizeSlider, gridSizeLabel, ((JSlider)event.getSource()).getValue());
            }
        });    
        
        // ADD SPACE
        for (int addSpace = 11 ; addSpace<15 ; addSpace++) 
        {
            gbc.gridx = 0;
            gbc.gridy = addSpace;
            this.menuBackground.add(new JLabel(" "),gbc);
        }
        
        

        // ==========================================================================================================================
        // PLAYERS
        // ==========================================================================================================================
        JPanel playersPanel = new JPanel();
        playersPanel.setBackground(Color.white);
        playersPanel.setBorder(BorderFactory.createTitledBorder("Players :"));
        playersPanel.setPreferredSize(new Dimension(400, 200));
        gbc.gridx = 0;
        gbc.gridy = 15;
        this.menuBackground.add(playersPanel,gbc);
        
        
        // SOUS-PANEL : AJOUT DES JOUEURS
        JPanel addPlayersPanel = new JPanel();
        addPlayersPanel.setBackground(Color.white);
        playersPanel.add(addPlayersPanel,BorderLayout.CENTER);
        
        addPlayersPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbcPlayers = new GridBagConstraints();
        
        this.playersTextField = new JTextField(10);
        gbcPlayers.gridx = 0;
        gbcPlayers.gridy = 0;
        addPlayersPanel.add(playersTextField,gbcPlayers);   
        
        gbcPlayers.gridx = 1;
        gbcPlayers.gridy = 0;
        addPlayersPanel.add(new JLabel("     "),gbcPlayers);
        
        this.playersComboBox = new JComboBox(couleurs); 
        gbcPlayers.gridx = 2;
        gbcPlayers.gridy = 0;
        addPlayersPanel.add(playersComboBox,gbcPlayers);

        gbcPlayers.gridx = 3;
        gbcPlayers.gridy = 0;
        addPlayersPanel.add(new JLabel("     "),gbcPlayers);
        

        JButton playersAddButton = new JButton("Add");
        gbcPlayers.gridx = 4;
        gbcPlayers.gridy = 0;
        addPlayersPanel.add(playersAddButton,gbcPlayers);
        playersAddButton.addActionListener(e -> menuController.onAddClicked(playersAddButton));
        

        gbcPlayers.gridx = 5;
        gbcPlayers.gridy = 0;
        addPlayersPanel.add(new JLabel("     "),gbcPlayers);
        
        JButton playersRemoveButton = new JButton("Remove all");
        gbcPlayers.gridx = 6;
        gbcPlayers.gridy = 0;
        addPlayersPanel.add(playersRemoveButton,gbcPlayers);
        playersRemoveButton.addActionListener(e -> menuController.onRemoveClicked(playersRemoveButton));
        
        
        
        
        

        // SOUS-PANEL : JOUEURS ENREGISTR�S
        JPanel registeredPlayersPanel = new JPanel();
        registeredPlayersPanel.setBackground(Color.white);
        playersPanel.add(registeredPlayersPanel,BorderLayout.SOUTH);
        
        JLabel label = new JLabel("Registered players :");
        this.playersLabel = new JLabel("");
        registeredPlayersPanel.add(label,BorderLayout.NORTH);
        registeredPlayersPanel.add(this.playersLabel ,BorderLayout.SOUTH);
        
		this.getContentPane().add(menuBackground);
	}

	public JLabel getPlayersLabel() {
		return playersLabel;
	}

	public JTextField getPlayersTextField() {
		return playersTextField;
	}

	public JComboBox getPlayersComboBox() {
		return playersComboBox;
	}

	public String[] getCouleurs() {
		return couleurs;
	}

	
	
	
	
	
	
	
}
