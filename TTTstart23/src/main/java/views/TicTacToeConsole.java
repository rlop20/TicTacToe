/*
 * This class will run a console game of Tic Tac Toe. 
 * 
 * @author Robert Lopez
 */
package views;
import model.IntermediateAI;
import model.TicTacToeGame;
import java.util.Scanner; // import scanner class

public class TicTacToeConsole {

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		char[][] board = game.getTicTacToeBoard();
		Scanner input = new Scanner(System.in); // get user input
		int x = 0;
		int y = 0;
		game.getComputerPlayer().setStrategy(new IntermediateAI());
		while (game.stillRunning()) {
			// ask for user input
			System.out.print("Enter row and column: ");
			x = Integer.parseInt(input.next());
			y = Integer.parseInt(input.next());
			// check if location is available
			if (game.available(x, y)) {
				game.humanMove(x, y, false);
			}
			// print board
			System.out.println(game.toString());
			// check if either player won or tied
			if(game.didWin('X')) {
				System.out.println("X wins");
			} else if (game.didWin('O')) {
				System.out.println("O wins");
			} else if (game.tied()) {
				System.out.println("Tie");
			}
		}
	}
}
