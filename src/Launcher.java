import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Launcher 
{

	public static void main(String[] args) 
	{
		final JFrame frame = new ReversiFrame();
        frame.setTitle("Reversi Game");
        
        frame.setSize(500, 500);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

}
