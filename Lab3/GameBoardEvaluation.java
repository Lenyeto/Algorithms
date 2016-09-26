// Filename: GameBoardEvaluation.java
// By: Andrew Holbrook
// Date: 9/14/2016
// Description: The GameBoardEvaluation class represents the state of the board.

public class GameBoardEvaluation {
	public int opportunityX = 0;
	public int opportunityO = 0;
	public boolean winX = false;
	public boolean winO = false;
	public boolean boardFull = false;
	
	public String toString() {
		String tmpStr = "X Opportunity: " + opportunityX + ", X Win: " + winX;
		tmpStr += "\nO Opportunity: " + opportunityO + ", O Win: " + winO;
		return tmpStr;
	}
}
