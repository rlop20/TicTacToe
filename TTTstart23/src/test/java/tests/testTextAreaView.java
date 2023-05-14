package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.OurObservable;
import model.TicTacToeGame;
import views.TextAreaView;

class testTextAreaView {

	@Test
	void test() {
		TicTacToeGame game = new TicTacToeGame();
		OurObservable obs = new OurObservable();
		TextAreaView view = new TextAreaView(game);
	}
}
