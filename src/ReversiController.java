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
		if ((cell.getState() == 0) && (isFollowingRules(cell) == true))
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
			compterScores();
			reversiFrame.changeWhoPlay();
		}
		
	}
	
	
	
	
	public void compterScores() 
	{
		Cell[][] cellArray = this.reversiFrame.getCellArray();
		
		int whiteScore = 0;
		int blackScore = 0;
		
		for (int i = 0; i < this.reversiFrame.getGridSize() ; i++) 
		{
			for (int j = 0; j < this.reversiFrame.getGridSize() ; j++) 
			{
				if (cellArray[j][i].getState() == 1) // State = 1   => White
				{
					whiteScore = whiteScore+1;
				}
				else if (cellArray[j][i].getState() == 2) // State= 2	=> Black
				{
					blackScore = blackScore+1;
				}
			}
		}
		
		this.reversiFrame.setBlackScore(blackScore);
		this.reversiFrame.setWhiteScore(whiteScore);
		this.reversiFrame.updateScores();
		
	}
	
	
	
	
	public boolean isFollowingRules(Cell cell) 
	{
		int compteurInter[] = {0,0,0,0,0,0,0,0};
		
		boolean conditions[] = new boolean[8];
		conditions[0] = isFollowingRulesLeftTop(cell, compteurInter);
		conditions[1] = isFollowingRulesTop(cell, compteurInter);
		conditions[2] = isFollowingRulesRightTop(cell, compteurInter);
		conditions[3] = isFollowingRulesLeftMiddle(cell, compteurInter);
		conditions[4] = isFollowingRulesRightMiddle(cell, compteurInter);
		conditions[5] = isFollowingRulesLeftBottom(cell, compteurInter);
		conditions[6] = isFollowingRulesBottom(cell, compteurInter);
		conditions[7] = isFollowingRulesRightBottom(cell, compteurInter);
		
		if ((conditions[0] == true && compteurInter[0]>0) || (conditions[1] == true && compteurInter[1]>0)  || (conditions[2] == true && compteurInter[2]>0)   || (conditions[3] == true && compteurInter[3]>0)   || (conditions[4] == true && compteurInter[4]>0)   || (conditions[5] == true && compteurInter[5]>0)   || (conditions[6] == true && compteurInter[6]>0)   || (conditions[7] == true && compteurInter[7]>0))
		{
			/*
			for (int i=0;i<8;i++) 
			{
				System.out.print("-"+compteurInter[i]);
			}
			System.out.println("");
			*/
			
			if (conditions[0] == true) 
			{
				completeLeftTop(cell);
			}
			
			if (conditions[1] == true) 
			{
				completeTop(cell);
			}
			
			if (conditions[2] == true) 
			{
				completeRightTop(cell);
			}
			
			if (conditions[3] == true) 
			{
				completeLeftMiddle(cell);
			}
			
			if (conditions[4] == true) 
			{
				completeRightMiddle(cell);
			}
			
			if (conditions[5] == true) 
			{
				completeLeftBottom(cell);
			}
			
			if (conditions[6] == true) 
			{
				completeBottom(cell);
			}
			
			if (conditions[7] == true) 
			{
				completeRightBottom(cell);
			}
			return true;	
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean isFollowingRulesLeftTop(Cell cell, int[] compteurInter) 
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
            	return isFollowingRulesLeftTop(cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1], compteurInter);
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
	
	public boolean isFollowingRulesTop(Cell cell, int[] compteurInter) 
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
                return isFollowingRulesTop(cellArray[cell.getCoordX()][cell.getCoordY() - 1], compteurInter);
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
	
	public boolean isFollowingRulesRightTop(Cell cell, int[] compteurInter) 
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
            	return isFollowingRulesRightTop(cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1], compteurInter);
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
	
	public boolean isFollowingRulesLeftMiddle(Cell cell, int[] compteurInter) 
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
            	return isFollowingRulesLeftMiddle(cellArray[cell.getCoordX() - 1][cell.getCoordY()], compteurInter);
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

	public boolean isFollowingRulesRightMiddle(Cell cell, int[] compteurInter) 
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
            	return isFollowingRulesRightMiddle(cellArray[cell.getCoordX() + 1][cell.getCoordY()], compteurInter);
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

	public boolean isFollowingRulesLeftBottom(Cell cell, int[] compteurInter) 
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
                return isFollowingRulesLeftBottom(cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1], compteurInter);
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

	public boolean isFollowingRulesBottom(Cell cell, int[] compteurInter) 
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
            	return isFollowingRulesBottom(cellArray[cell.getCoordX()][cell.getCoordY() + 1], compteurInter);
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
	
	public boolean isFollowingRulesRightBottom(Cell cell, int[] compteurInter) 
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
            	return isFollowingRulesRightBottom(cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1], compteurInter);
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
	
	
	
	
	public void completeLeftTop(Cell cell) 
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
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].getState() != tempState && cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].getState() != 0)
            {
            	cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].setState(tempState);
            	cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1].updateState();
            	completeLeftTop(cellArray[cell.getCoordX() - 1][cell.getCoordY() - 1]);
            }
        }
	}
	
	public void completeTop(Cell cell) 
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
		
		if (cell.getCoordY() > 0)
        {
			if (cellArray[cell.getCoordX()][cell.getCoordY() - 1].getState() != tempState && cellArray[cell.getCoordX()][cell.getCoordY() - 1].getState() != 0)
            {
            	cellArray[cell.getCoordX()][cell.getCoordY() - 1].setState(tempState);
            	cellArray[cell.getCoordX()][cell.getCoordY() - 1].updateState();
            	completeTop(cellArray[cell.getCoordX()][cell.getCoordY() - 1]);
            }
        }
	}
	
	public void completeRightTop(Cell cell) 
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
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].getState() != tempState && cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].getState() != 0)
            {
            	cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].setState(tempState);
            	cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1].updateState();
            	completeRightTop(cellArray[cell.getCoordX() + 1][cell.getCoordY() - 1]);
            }
        }
	}
	
	public void completeLeftMiddle(Cell cell) 
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
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY()].getState() != tempState && cellArray[cell.getCoordX() - 1][cell.getCoordY()].getState() != 0)
            {
            	cellArray[cell.getCoordX() - 1][cell.getCoordY()].setState(tempState);
            	cellArray[cell.getCoordX() - 1][cell.getCoordY()].updateState();
            	completeLeftMiddle(cellArray[cell.getCoordX() - 1][cell.getCoordY()]);
            }
        }
	}
	
	public void completeRightMiddle(Cell cell) 
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
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY()].getState() != tempState && cellArray[cell.getCoordX() + 1][cell.getCoordY()].getState() != 0)
            {
            	cellArray[cell.getCoordX() + 1][cell.getCoordY()].setState(tempState);
            	cellArray[cell.getCoordX() + 1][cell.getCoordY()].updateState();
            	completeRightMiddle(cellArray[cell.getCoordX() + 1][cell.getCoordY()]);
            }
        }
	}
	
	public void completeLeftBottom(Cell cell) 
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
			if (cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].getState() != tempState && cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].getState() != 0)
            {
            	cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].setState(tempState);
            	cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1].updateState();
            	completeLeftBottom(cellArray[cell.getCoordX() - 1][cell.getCoordY() + 1]);
            }
        }
	}
	
	public void completeBottom(Cell cell) 
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
			if (cellArray[cell.getCoordX()][cell.getCoordY() + 1].getState() != tempState && cellArray[cell.getCoordX()][cell.getCoordY() + 1].getState() != 0)
            {
            	cellArray[cell.getCoordX()][cell.getCoordY() + 1].setState(tempState);
            	cellArray[cell.getCoordX()][cell.getCoordY() + 1].updateState();
            	completeBottom(cellArray[cell.getCoordX()][cell.getCoordY() + 1]);
            }
        }
	}
	
	public void completeRightBottom(Cell cell) 
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
			if (cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].getState() != tempState && cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].getState() != 0)
            {
            	cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].setState(tempState);
            	cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1].updateState();
            	completeRightBottom(cellArray[cell.getCoordX() + 1][cell.getCoordY() + 1]);
            }
        }
	}
	
	
	public int getChange() 
	{
		return change;
	}
	
	
	
	
}
