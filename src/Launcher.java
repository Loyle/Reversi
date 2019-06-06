import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Launcher 
{

	public static void main(String[] args) 
	{
		
		final JFrame menuFrame = new MenuFrame();
		menuFrame.setTitle("Menu - Reversi Game");
        
		menuFrame.setSize(700, 700);

		menuFrame.setVisible(true);
		menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		
		

	}

}
