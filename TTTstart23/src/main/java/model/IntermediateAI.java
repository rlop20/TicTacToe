/**
 * This strategy selects the corners if X is not on a winning move.
 * 
 * @throws 
 *    IGotNowhereToGoException whenever asked for a 
 *    move that is impossible to deliver.
 * 
 * @author Robert Lopez
 * 
 */
package model;

import java.util.Random;

public class IntermediateAI implements TicTacToeStrategy {
	private static Random generator;

	@Override
	public OurPoint desiredMove(TicTacToeGame theGame) {
		boolean set = false;
		while (!set) {
			if (theGame.maxMovesRemaining() == 0)
				throw new IGotNowhereToGoException(" -- Hey there programmer, the board is filled");
			// Otherwise, begin to find a spot that will win.
			// Check if we have any spots on the board
			char[][] board = theGame.getTicTacToeBoard();
			int counter = 0;
			int bestScore = -10;
			int[] bestMove = {0, 0};
			char dir = ' ';
		    generator = new Random();
//			 System.out.println(theGame.toString());
		    // check if corners are available and choose them
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					 if (board[0][0] == '_') {
						return new OurPoint(0, 0);
					} else if( board[0][2] == '_' ) {
						return new OurPoint(0, 2);
					} else if( board[2][0] == '_' ) {
						return new OurPoint(2, 0);
					} else if ( board[2][2] == '_') {
						return new OurPoint(2, 2);
					// if the middle is open then choose this next
					} else if (board[1][1] == '_'){
						return new OurPoint(1,1);
					} else {
						// Otherwise, try to randomly find an open spot 
					      int row = generator.nextInt(3);
					      int col = generator.nextInt(3);
					      if ( theGame.available(row, col) ) {
					        set = true;
					        return new OurPoint(row, col);
					      } 
					}
				}
			}
		}
		return null;
	}
}
