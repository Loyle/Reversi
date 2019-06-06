import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;

public class MenuController 
{
	private final MenuFrame menuFrame;
	private int gridSize = 8;
	
	public MenuController(MenuFrame menuFrame) 
	{
		this.menuFrame = menuFrame;
	}
	
	public void onPlayClicked(JButton play) 
	{
		this.menuFrame.dispose();
		
		ReversiFrame reversiFrame = new ReversiFrame(this.gridSize);
		
		reversiFrame.setTitle("Reversi Game");
        
		reversiFrame.setSize(500, 500);
		reversiFrame.setGridSize(this.gridSize);

		reversiFrame.setVisible(true);
		reversiFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void onSliderStateChanged(JSlider gridSizeSlider, JLabel gridSizeLabel, int gridSize) 
	{

    	if (gridSize%2 !=0)
    	{
    		gridSize=gridSize-1;
    	}
    	
    	if (gridSize < 10)
		{
        	gridSizeLabel.setText("     Valeur actuelle : 0" + gridSize);
		}
    	else
    	{
    		gridSizeLabel.setText("     Valeur actuelle : " + gridSize);
    	}
    	
    	this.gridSize = gridSize;
    	
    	
	}
	

}
