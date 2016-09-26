// Filename: GameBoard.java
// By: Andrew Holbrook
// Date: 9/14/2016
// Description: The GameBoard class represents a 3x3 tic-tac-toe board, and
// includes methods for marking, unmarking, and evaluating the board.

public class GameBoard {
	public static final int EMPTY_VALUE = 0;
	public static final int O_VALUE = 1;
	public static final int X_VALUE = 2;
	public static final String[] SYMBOL_TABLE = new String[]{" ", "O", "X"};
	
	private int[][] state;
	private int prevMarkedRow = -1;
	private int prevMarkedColumn = -1;
	
	public GameBoard() {
		state = new int[3][3];
	}
	
	// Mark board with an X or O.
	// SYMBOL_VALUE must be either O_VALUE or X_VALUE
	public boolean mark(int row, int column, int SYMBOL_VALUE) {
		if (row < 0 || row > 2 || column < 0 || column > 2) return false;
		if (state[row][column] != EMPTY_VALUE) return false;
		
		state[row][column] = SYMBOL_VALUE;
		prevMarkedRow = row;
		prevMarkedColumn = column;
		
		return true;
	}
	
	public void undoLastMark() {
		if (prevMarkedRow == -1) return;
		state[prevMarkedRow][prevMarkedColumn] = EMPTY_VALUE;
	}
	
	public String toString() {
		String tmpStr = "";
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 3; ++column) {
				if (row < 2) {
					if (state[row][column] == EMPTY_VALUE) tmpStr += "_";
					else tmpStr += SYMBOL_TABLE[state[row][column]];
				} else {
					tmpStr += SYMBOL_TABLE[state[row][column]];
				}
				
				if (column < 2) tmpStr += "|";
			}
			
			if (row < 2) tmpStr += "\n";
		}
		
		return tmpStr;
	}
	
	private int checkRows(int SYMBOL_VALUE) {
		int opportunity = 0;
		for (int row = 0; row < 3; ++row) {
			
			int currentRowOpportunity = 0;
			for (int column = 0; column < 3; ++column) {
				currentRowOpportunity += state[row][column] ^ SYMBOL_VALUE;
			}
			
			// winning row if 0
			if (currentRowOpportunity == 0) return -1;
			
			// found 1 opportunity to win
			if (currentRowOpportunity == SYMBOL_VALUE) opportunity += 1;
		}
		
		return opportunity;
	}
	
	private int checkColumns(int SYMBOL_VALUE) {
		int opportunity = 0;
		for (int column = 0; column < 3; ++column) {
			
			int currentColumnOpportunity = 0;
			for (int row = 0; row < 3; ++row) {
				currentColumnOpportunity += state[row][column] ^ SYMBOL_VALUE;
			}
			
			// same as in checkRows method
			if (currentColumnOpportunity == 0) return -1;
			if (currentColumnOpportunity == SYMBOL_VALUE) opportunity += 1;
		}
		
		return opportunity;
	}
	
	private int checkDiagonals(int SYMBOL_VALUE) {
		int opportunity = 0;
		int currentForewardDiagOpportunity = 0;
		int currentBackwardDiagOpportunity = 0;
		for (int i = 0; i < 3; ++i) {
			currentForewardDiagOpportunity += state[i][2 - i] ^ SYMBOL_VALUE;
			currentBackwardDiagOpportunity += state[i][i] ^ SYMBOL_VALUE;
		}
		
		// same as in checkRows method
		if (currentForewardDiagOpportunity == 0) return -1;
		if (currentForewardDiagOpportunity == SYMBOL_VALUE) opportunity += 1;
		if (currentBackwardDiagOpportunity == 0) return -1;
		if (currentBackwardDiagOpportunity == SYMBOL_VALUE) opportunity += 1;
		
		return opportunity;
	}
	
	private boolean isFull() {
		for (int row = 0; row < 3; ++row)
			for (int column = 0; column < 3; ++column)
				if (state[row][column] == EMPTY_VALUE)
					return false;
		
		return true;
	}
	
	public GameBoardEvaluation evaluate() {
		GameBoardEvaluation gbEval = new GameBoardEvaluation();
		
		int rowOpp = this.checkRows(O_VALUE);
		int colOpp = this.checkColumns(O_VALUE);
		int diagOpp = this.checkDiagonals(O_VALUE);
		gbEval.opportunityO = rowOpp + colOpp + diagOpp;
		if (rowOpp == -1 || colOpp == -1 || diagOpp == -1)
			gbEval.winO = true;
		
		rowOpp = this.checkRows(X_VALUE);
		colOpp = this.checkColumns(X_VALUE);
		diagOpp = this.checkDiagonals(X_VALUE);
		gbEval.opportunityX = rowOpp + colOpp + diagOpp;
		if (rowOpp == -1 || colOpp == -1 || diagOpp == -1)
			gbEval.winX = true;
		
		gbEval.boardFull = this.isFull();
		
		return gbEval;
	}
}
