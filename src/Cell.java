import javax.swing.JButton;

public class Cell extends JButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 110484099740962045L;
	private int state;
	
	public Cell() 
	{
		this.state = 0;
	}

	public int getState() 
	{
		return state;
	}

	public void setState(int state) 
	{
		this.state = state;
	}
	
	
}
