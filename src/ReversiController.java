import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ReversiController 
{
	private final ReversiState reversiState;
	private final ReversiFrame reversiFrame;
	private int change = 0;
	
	public ReversiController(ReversiState reversiState, ReversiFrame reversiFrame)
	{
		this.reversiState = reversiState;
		this.reversiFrame = reversiFrame;
	}
	
	public void onCellClicked(Cell cell) 
	{
		if ((cell.getState() == 0) && (hasNeighbours(cell) == true) && (isFollowingRules(cell) == true))
		{
			if (this.change == 0) 
			{
				cell.setState(1);
				this.change = 1;
			}
			else if (this.change == 1) 
			{
				cell.setState(2);
				this.change = 0;
			}
			cell.updateState();

			reversiFrame.changeWhoPlay();
		}
		
	}
	
	public boolean hasNeighbours(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int idx=0;
		
		// En haut à gauche
		if ((cell.getCoordX() > 0) && (cell.getCoordY() > 0))
        {
            if (cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].getState() == 0)
            {
                idx=idx+1;
            }
        }
		else
		{
			idx=idx+1;
		}
		
        // En haut
        if (cell.getCoordY() > 0)
        {
        	if (cellArray[cell.getCoordX()][cell.getCoordY() - 1].getState() == 0)
            {
        		idx=idx+1;
            }
        }
		else
		{
			idx=idx+1;
		}
        
        // En haut à droite
        if ((cell.getCoordX()+1 < reversiFrame.getGridSize()) && (cell.getCoordY() > 0))
        {
        	if (cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].getState() == 0)
            {
        		idx=idx+1;
            }
        }
		else
		{
			idx=idx+1;
		}
        
        // Au milieu à gauche
        if (cell.getCoordX() > 0)
        {
        	if (cellArray[cell.getCoordX() - 1][cell.getCoordY()].getState() == 0)
            {
        		idx=idx+1;
            }
            
        }
        else
		{
			idx=idx+1;
		}
        
        // Au milieu à droite
        if (cell.getCoordX()+1 < reversiFrame.getGridSize())
        {
        	if (cellArray[cell.getCoordX() + 1][cell.getCoordY()].getState() == 0)
            {
        		idx=idx+1;
            }
        }
        else
		{
			idx=idx+1;
		}
        
        // En bas à gauche
        if ((cell.getCoordX() > 0) && (cell.getCoordY()+1 < reversiFrame.getGridSize()))
        {
        	if (cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].getState() == 0)
            {
        		idx=idx+1;
            }
        }
        else
		{
			idx=idx+1;
		}
        
        // En bas
        if (cell.getCoordY()+1 < reversiFrame.getGridSize())
        {
        	if (cellArray[cell.getCoordX()][cell.getCoordY() + 1].getState() == 0)
            {
        		idx=idx+1;
            }
        }
        else
		{
			idx=idx+1;
		}
        
        // En bas à droite
        if ((cell.getCoordX()+1 < reversiFrame.getGridSize()) && (cell.getCoordY()+1 < reversiFrame.getGridSize()))
        {
        	if (cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].getState() == 0)
            {
        		idx=idx+1;
            }
        }
        else
		{
			idx=idx+1;
		}
        
        
        if (idx == 8) 
        {
        	return false;
        }
        else
        {
        	return true;
        }
		
		
	}
	
	public boolean isFollowingRules(Cell cell) 
	{
		
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		if (isFollowingRulesLeftTop(cell) == true || isFollowingRulesTop(cell) == true || isFollowingRulesRightTop(cell) == true || isFollowingRulesLeftMiddle(cell) == true || isFollowingRulesRightMiddle(cell) == true || isFollowingRulesLeftBottom(cell) == true || isFollowingRulesBottom(cell) == true || isFollowingRulesRightBottom(cell) == true) 
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean isFollowingRulesLeftTop(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else if (this.change == 1) 
		{
			tempState=2;
		}
		
		if ((cell.getCoordX() > 0) && (cell.getCoordY() > 0))
        {
            if (cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].getState() != tempState)
            {
                return isFollowingRulesLeftTop(cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1]);
            }
            else
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}
	
	public boolean isFollowingRulesTop(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else if (this.change == 1) 
		{
			tempState=2;
		}
		
		if (cell.getCoordY() > 0)
        {
            if (cellArray[cell.getCoordX()][cell.getCoordY() - 1].getState() != tempState)
            {
                return isFollowingRulesLeftTop(cellArray[cell.getCoordX()][cell.getCoordY() - 1]);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}
	
	public boolean isFollowingRulesRightTop(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else if (this.change == 1) 
		{
			tempState=2;
		}
		
		if ((cell.getCoordX()+1 < reversiFrame.getGridSize()) && (cell.getCoordY() > 0))
        {
            if (cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].getState() != tempState)
            {
                return isFollowingRulesLeftTop(cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1]);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}
	
	public boolean isFollowingRulesLeftMiddle(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else if (this.change == 1) 
		{
			tempState=2;
		}
		
		if (cell.getCoordX() > 0)
        {
            if (cellArray[cell.getCoordX() - 1][cell.getCoordY()].getState() != tempState)
            {
                return isFollowingRulesLeftTop(cellArray[cell.getCoordX() - 1][cell.getCoordY()]);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}

	public boolean isFollowingRulesRightMiddle(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else if (this.change == 1) 
		{
			tempState=2;
		}
		
		if (cell.getCoordX()+1 < reversiFrame.getGridSize())
        {
            if (cellArray[cell.getCoordX() + 1][cell.getCoordY()].getState() != tempState)
            {
                return isFollowingRulesLeftTop(cellArray[cell.getCoordX() + 1][cell.getCoordY()]);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}

	public boolean isFollowingRulesLeftBottom(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else if (this.change == 1) 
		{
			tempState=2;
		}
		
		if ((cell.getCoordX() > 0) && (cell.getCoordY()+1 < reversiFrame.getGridSize()))
        {
            if (cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].getState() != tempState)
            {
                return isFollowingRulesLeftTop(cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1]);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}

	public boolean isFollowingRulesBottom(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else if (this.change == 1) 
		{
			tempState=2;
		}
		
		if (cell.getCoordY()+1 < reversiFrame.getGridSize())
        {
            if (cellArray[cell.getCoordX()][cell.getCoordY() + 1].getState() != tempState)
            {
                return isFollowingRulesLeftTop(cellArray[cell.getCoordX()][cell.getCoordY() + 1]);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}
	
	public boolean isFollowingRulesRightBottom(Cell cell) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else if (this.change == 1) 
		{
			tempState=2;
		}
		
		if ((cell.getCoordX()+1 < reversiFrame.getGridSize()) && (cell.getCoordY()+1 < reversiFrame.getGridSize()))
        {
            if (cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].getState() != tempState)
            {
                return isFollowingRulesLeftTop(cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1]);
            }
            else 
            {
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}
	
	public int getChange() 
	{
		return change;
	}
	
	
	
	
}
