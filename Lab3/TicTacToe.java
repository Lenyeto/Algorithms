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
	
	public void setPlayerTwo(PlayerI playerTwo) {
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
		
		// same as player one
		game.setPlayerTwo((gameBoard) -> {
			while (!gameBoard.mark((int)Math.floor(Math.random() * 3),
								   (int)Math.floor(Math.random() * 3),
							   	   GameBoard.O_VALUE));
		});
		
		game.play();
		
		System.out.println(game.gameBoard);
		System.out.println(game.gameBoard.evaluate());
	}
}
