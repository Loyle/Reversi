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

	private final MenuController menuController = new MenuController(this);
	private JPanel menuBackground;
	
	public MenuFrame() 
	{
		// BACKGROUND
		this.menuBackground = new JPanel();
		this.menuBackground.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		menuBackground.setBackground(Color.white);

		// BOUTON PLAY
		final JButton play = new JButton("Play");
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
        final JPanel gridSizePanel = new JPanel();
        gridSizePanel.setBackground(Color.white);
        gridSizePanel.setBorder(BorderFactory.createTitledBorder("Grid Size :"));
        gridSizePanel.setPreferredSize(new Dimension(400, 80));
        gbc.gridx = 0;
        gbc.gridy = 10;
        this.menuBackground.add(gridSizePanel,gbc);
        
        JSlider gridSizeSlider = new JSlider();
        gridSizeSlider.setMinimum(4);
        gridSizeSlider.setMaximum(20);
        gridSizeSlider.setPaintTicks(true);
        gridSizeSlider.setPaintLabels(true);
        gridSizeSlider.setMinorTickSpacing(2);
        gridSizeSlider.setMajorTickSpacing(16);
        gridSizeSlider.setValue(8);
        gridSizePanel.add(gridSizeSlider,BorderLayout.EAST);

        JLabel gridSizeLabel = new JLabel();
        gridSizeLabel.setText("     Valeur actuelle : 08");
        gridSizePanel.add(gridSizeLabel,BorderLayout.WEST);
        
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
