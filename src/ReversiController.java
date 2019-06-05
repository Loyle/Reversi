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
		int compteurInter[] = {0,0,0,0,0,0,0,0};
		
		boolean conditions[] = new boolean[8];
		conditions[0] = hasSameColorLeftTop(cell, compteurInter);
		conditions[1] = hasSameColorTop(cell, compteurInter);
		conditions[2] = hasSameColorRightTop(cell, compteurInter);
		conditions[3] = hasSameColorLeftMiddle(cell, compteurInter);
		conditions[4] = hasSameColorRightMiddle(cell, compteurInter);
		conditions[5] = hasSameColorLeftBottom(cell, compteurInter);
		conditions[6] = hasSameColorBottom(cell, compteurInter);
		conditions[7] = hasSameColorRightBottom(cell, compteurInter);
		
		if (conditions[0] == true || conditions[1] == true || conditions[2] == true  || conditions[3] == true  || conditions[4] == true  || conditions[5] == true  || conditions[6] == true  || conditions[7] == true )
		{
			/*
			for (int i=0;i<8;i++) 
			{
				System.out.print("-"+compteurInter[i]);
			}
			System.out.println("");
			*/
			return true;	
		}
		else
		{
			return false;
		}
		
	}
	
	
	
	public boolean hasSameColorLeftTop(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		int tempState=0;
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if ((cell.getCoordX() > 0) && (cell.getCoordY() > 0))
        {
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].getState() == 0) 
            {
            	compteurInter[0] = 0;
                return false;
            }
            else if (cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].getState() != tempState)
            {
            	compteurInter[0] = compteurInter[0]+1;
            	return hasSameColorLeftTop(cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1], compteurInter);
            }
            else
            {
            	//cell.setState(tempState);
    			//cell.updateState();
    			//reversiFrame.changeWhoPlay();
            	return true;
            }
        }
		else 
		{
			return false;
		}
	}
	
	public boolean hasSameColorTop(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=1;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordY() > 0)
        {
			if (cellArray[cell.getCoordX()][cell.getCoordY() - 1].getState() == 0)
            {
				compteurInter[1] = 0;
                return false;
            }
            else if (cellArray[cell.getCoordX()][cell.getCoordY() - 1].getState() != tempState)
            {
            	compteurInter[1] = compteurInter[1]+1;
                return hasSameColorTop(cellArray[cell.getCoordX()][cell.getCoordY() - 1], compteurInter);
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
	
	public boolean hasSameColorRightTop(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if ((cell.getCoordX()+1 < reversiFrame.getGridSize()) && (cell.getCoordY() > 0))
        {
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].getState() == 0) 
            {
				compteurInter[2] = 0;
                return false;
            }
			else if (cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].getState() != tempState)
            {
            	compteurInter[2] = compteurInter[2]+1;
            	return hasSameColorRightTop(cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1], compteurInter);
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
	
	public boolean hasSameColorLeftMiddle(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordX() > 0)
        {
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY()].getState() == 0) 
            {
				compteurInter[3] = 0;
                return false;
            }
			else if (cellArray[cell.getCoordX() - 1][cell.getCoordY()].getState() != tempState)
            {
            	compteurInter[3] = compteurInter[3]+1;
            	return hasSameColorLeftMiddle(cellArray[cell.getCoordX() - 1][cell.getCoordY()], compteurInter);
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

	public boolean hasSameColorRightMiddle(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordX()+1 < reversiFrame.getGridSize())
        {
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY()].getState() == 0) 
            {
				compteurInter[4] = 0;
                return false;
            }
            else if (cellArray[cell.getCoordX() + 1][cell.getCoordY()].getState() != tempState)
            {
            	compteurInter[4] = compteurInter[4]+1;
            	return hasSameColorRightMiddle(cellArray[cell.getCoordX() + 1][cell.getCoordY()], compteurInter);
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

	public boolean hasSameColorLeftBottom(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if ((cell.getCoordX() > 0) && (cell.getCoordY()+1 < reversiFrame.getGridSize()))
        {
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].getState() == 0) 
            {
				compteurInter[5] = 0;
            	return false;
            }
            else if (cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].getState() != tempState)
            {
            	compteurInter[5] = compteurInter[5]+1;
                return hasSameColorLeftBottom(cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1], compteurInter);
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

	public boolean hasSameColorBottom(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if (cell.getCoordY()+1 < reversiFrame.getGridSize())
        {
			if (cellArray[cell.getCoordX()][cell.getCoordY() + 1].getState() == 0) 
            {
				compteurInter[6] = 0;
                return false;
            }
            else if (cellArray[cell.getCoordX()][cell.getCoordY() + 1].getState() != tempState)
            {
            	compteurInter[6] = compteurInter[6]+1;
            	return hasSameColorBottom(cellArray[cell.getCoordX()][cell.getCoordY() + 1], compteurInter);
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
	
	public boolean hasSameColorRightBottom(Cell cell, int[] compteurInter) 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int tempState=0;
		
		if (this.change == 0) 
		{
			tempState=1;
		}
		else
		{
			tempState=2;
		}
		
		if ((cell.getCoordX()+1 < reversiFrame.getGridSize()) && (cell.getCoordY()+1 < reversiFrame.getGridSize()))
        {
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].getState() == 0)
            {
				compteurInter[7] = 0;
                return false;
            }
            else if (cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].getState() != tempState)
            {
            	compteurInter[7] = compteurInter[7]+1;
            	return hasSameColorRightBottom(cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1], compteurInter);
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
