package com.utbm.reversi.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.utbm.reversi.controller.MenuController;

public class MenuFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1328756640538025065L;

	// On associe un controller à la fenêtre
	private final MenuController menuController = new MenuController(this);
	// On déclare un panel dans lequel on placera les boutons du menu
	private JPanel menuBackground;
	
	public MenuFrame() 
	{
		// BACKGROUND
		// Création du panel
		this.menuBackground = new JPanel();
		this.menuBackground.setLayout(new GridBagLayout());
		//this.setLocationRelativeTo(null);
		GridBagConstraints gbc = new GridBagConstraints();
		menuBackground.setBackground(Color.white);

		// BOUTON PLAY
		final JButton play = new JButton("Play");
		// On associe le clic sur le bouton play à cette fonction du controller
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
        
        // TAILLE GRILLE
        // On crée un panel dans lequel on mettra la Slide Bar et la label qui affiche la taille de la grille
        final JPanel gridSizePanel = new JPanel();
        gridSizePanel.setBackground(Color.white);
        // On crée la un contour et un titre pour ce panel
        gridSizePanel.setBorder(BorderFactory.createTitledBorder("Grid Size :"));
        // On lui donne la dimension voulue
        gridSizePanel.setPreferredSize(new Dimension(400, 80));
        gbc.gridx = 0;
        gbc.gridy = 10;
        this.menuBackground.add(gridSizePanel,gbc);
        
        // On crée la slide bar
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
        // Écart entre la valeur minimum et la valeur maximum
        gridSizeSlider.setMajorTickSpacing(16);
        // Valeur de base
        gridSizeSlider.setValue(8);
        gridSizePanel.add(gridSizeSlider,BorderLayout.EAST);

        // Label qui affiche la taille de la grille
        JLabel gridSizeLabel = new JLabel();
        gridSizeLabel.setText("     Valeur actuelle : 08");
        gridSizePanel.add(gridSizeLabel,BorderLayout.WEST);
        
        // On associe le changement d'état de la slide bar à la fonction du controller
        gridSizeSlider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent event)
            {
            	menuController.onSliderStateChanged(gridSizeSlider, gridSizeLabel, ((JSlider)event.getSource()).getValue());
            }
        });    
        
        
		this.getContentPane().add(menuBackground);
	}
}
