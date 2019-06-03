import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReversiFrame extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8514403723481387196L;
	
	private final ReversiState reversiState = new ReversiState();
	private final ReversiController reversiController = new ReversiController(this.reversiState,this);
	private final int cellSize = 70;
	private final int scoresSizeX = 10;
	
	
	public ReversiFrame() 
	{
		final JPanel game = new JPanel();
        game.setBackground(Color.black);
        game.setLayout(new GridLayout(8,8,10,10));
        for (int i=0; i<8 ; i++) 
        {
        	for (int j=0;j<8;j++) 
        	{
        		final Cell cell = new Cell();
        		cell.setBackground(Color.green);
        		cell.addActionListener(e -> reversiController.onCellClicked(cell));
        		game.add(cell);
        	}
        }
        
        
        final JPanel scores = new JPanel();
        scores.setBackground(Color.gray);
        final JLabel scoreLabel = new JLabel("0");
        scores.add(scoreLabel);
        
        
        
        this.setMinimumSize(new Dimension(13+8*(cellSize+5)+scoresSizeX, 42+8*(cellSize+5)));
        this.getContentPane().add(game, BorderLayout.CENTER);
        this.getContentPane().add(scores, BorderLayout.EAST);
        this.pack();
	}

}
