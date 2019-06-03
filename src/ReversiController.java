import java.awt.Color;

import javax.swing.JButton;

public class ReversiController 
{
	private final ReversiState reversiState;
	private final ReversiFrame reversiFrame;
	
	public ReversiController(ReversiState reversiState, ReversiFrame reversiFrame)
	{
		this.reversiState = reversiState;
		this.reversiFrame = reversiFrame;
	}
	
	public void onCellClicked(Cell cell) 
	{
		if (cell.getState() == 0)
		{
			cell.setText("NOIRS");
			cell.setState(1);
		}
		else
		{
			cell.setText("BLANCS");
			cell.setState(0);
		}
	}
}
