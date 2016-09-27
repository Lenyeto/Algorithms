// Filename: TicTacToe.java
// By: Andrew Holbrook
// Date: 9/14/2016
// Description: Represents a game of tic-tac-toe.

public class TicTacToe {
	public GameBoard gameBoard;
	public PlayerI playerOne;
	public PlayerII playerTwo;
	
	public TicTacToe() {
		gameBoard = new GameBoard();
	}
	
	public void setPlayerOne(PlayerI playerOne) {
		this.playerOne = playerOne;
	}
	
	public void setPlayerTwo(PlayerII playerTwo) {
		this.playerTwo = playerTwo;
	}
	
	public void resetBoard() {
		gameBoard = new GameBoard();
	}
	
	public void play() {
		while (true) {
			// make a move, evaluate board, exit if win or board full
			playerOne.makeMove(gameBoard);
			GameBoardEvaluation gbEval = gameBoard.evaluate();
			if (gbEval.winX || gbEval.boardFull) break;
			
			playerTwo.makeMove(gameBoard);
			gbEval = gameBoard.evaluate();
			if (gbEval.winO || gbEval.boardFull) break;
		}
	}
	
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		
		// player one will randomly select an empty space on the board to mark
		game.setPlayerOne((gameBoard) -> {
			while (!gameBoard.mark((int)Math.floor(Math.random() * 3),
								   (int)Math.floor(Math.random() * 3),
							   	   GameBoard.X_VALUE));
		});
		
		game.setPlayerTwo((gameBoard) -> {
			int tmpHighest = -5;
			
			int tmpX = 0;
			int tmpY = 0;
			
			int tmpLowest = 1000;
			
			int tmpXL = 0;
			int tmpYL = 0;
			
			for (int i = 1; i <= 3; i++) {
				for (int j = 1; j <= 3; j++) {
					if (gameBoard.mark(i, j, GameBoard.O_VALUE)) {
						if (gameBoard.evaluate().opportunityO*2 - gameBoard.evaluate().opportunityX > tmpHighest) {
							tmpHighest = gameBoard.evaluate().opportunityO*2 - gameBoard.evaluate().opportunityX;
							tmpX = i;
							tmpY = j;
						}
						
						if (gameBoard.evaluate().opportunityX < tmpLowest) {
							tmpLowest = gameBoard.evaluate().opportunityX;
							tmpXL = i;
							tmpYL = j;
						}
						gameBoard.undoLastMark();
					}
				}
			}
			
			if (tmpX == 0 && tmpY == 0) {
				while (!gameBoard.mark((int)Math.floor(Math.random() * 3), (int)Math.floor(Math.random() * 3), GameBoard.O_VALUE));
			} else {
				gameBoard.mark(tmpX, tmpY, GameBoard.O_VALUE);
			}
			
			
		});
		
		int PlayerOneWins = 0;
		int PlayerTwoWins = 0;
		
		for (int i = 0; i < 99; i++) {
			game.play();
			game.gameBoard.evaluate();
			if (game.gameBoard.evaluate().winO) {
				PlayerOneWins++;
			} else if (game.gameBoard.evaluate().winX) {
				PlayerTwoWins++;
			}
			game.resetBoard();
		}
		
		
		
		System.out.println("Player One Won X " + PlayerOneWins + "% of the Time.");
		System.out.println("Player Two Won O " + PlayerTwoWins + "% of the Time.");
	}
}
