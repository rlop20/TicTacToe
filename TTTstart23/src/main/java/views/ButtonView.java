/**
 * 
 * This class is a text area view for the Tic Tac Toe GUI class.
 * 
 * @author Robert Lopez
 */
package views;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.OurObservable;
import model.OurObserver;
import model.TicTacToeGame;

public class ButtonView extends BorderPane implements OurObserver {
	private TicTacToeGame theGame;
	private TextArea text = new TextArea("Click to make a move");
	private Button button1 = new Button("_");
	private Button button2 = new Button("_");
	private Button button3 = new Button("_");
	private Button button4 = new Button("_");
	private Button button5 = new Button("_");
	private Button button6 = new Button("_");
	private Button button7 = new Button("_");
	private Button button8 = new Button("_");
	private Button button9 = new Button("_");

	public ButtonView(TicTacToeGame theModel) {
		theGame = theModel;
		initializePanel();
	}

	private void initializePanel() {
		// Set font of each button
		Font font = new Font("Monospace", 25);
		button1.setFont(font);
		button2.setFont(font);
		button3.setFont(font);
		button4.setFont(font);
		button5.setFont(font);
		button6.setFont(font);
		button7.setFont(font);
		button8.setFont(font);
		button9.setFont(font);
		
		// set up grid pane and add the buttons to it
		GridPane display = new GridPane();
		display.setHgap(10);
		display.setVgap(10);
		display.add(button1, 0, 0);
		display.add(button2, 1, 0);
		display.add(button3, 2, 0);
		display.add(button4, 0, 1);
		display.add(button5, 1, 1);
		display.add(button6, 2, 1);
		display.add(button7, 0, 2);
		display.add(button8, 1, 2);
		display.add(button9, 2, 2);
		
		// set the center of the window to the grid pane
		this.setCenter(display);
		
		// set the text message font and make sure it can not be edited and put it to bottom of window
		text.setFont(font);
		text.setEditable(false);
		this.setBottom(text);
		
		// set up an event handler that can be applied to all buttons
		EventHandler<ActionEvent> buttonPressed = new buttonPress();
		
		// if any of the buttons are pressed, run the event handler it calls
		button1.setOnAction(buttonPressed);
		button2.setOnAction(buttonPressed);
		button3.setOnAction(buttonPressed);
		button4.setOnAction(buttonPressed);
		button5.setOnAction(buttonPressed);
		button6.setOnAction(buttonPressed);
		button7.setOnAction(buttonPressed);
		button8.setOnAction(buttonPressed);
		button9.setOnAction(buttonPressed);
	}

	/**
	 * 
	 * This event handler will keep track of the button that it was given.
	 * Then it will set the game board piece associated with that button.
	 * For example, button1 is the 0,0 game board piece and button9 is 2,2.
	 * Then it will check if it is even a valid choice (has not been pressed) 
	 * and if the game is over or not.
	 * @author robert Lopez
	 *
	 */
	private class buttonPress implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent button) {
			Button buttonClicked = (Button) button.getSource();
			if (buttonClicked.equals(button1)) {
				if (theGame.available(0, 0)) {
					button1.setText("X");
					theGame.humanMove(0, 0, false);
				} else {
					text.setText("Invaid choice");
				}
			} else if (buttonClicked.equals(button2)) {
				if (theGame.available(0, 1)) {
					button1.setText("X");
					theGame.humanMove(0, 1, false);
				} else {
					text.setText("Invaid choice");
				}
			} else if (buttonClicked.equals(button3)) {
				if (theGame.available(0, 2)) {
					button1.setText("X");
					theGame.humanMove(0, 2, false);
				} else {
					text.setText("Invaid choice");
				}
			} else if (buttonClicked.equals(button4)) {
				if (theGame.available(1, 0)) {
					button1.setText("X");
					theGame.humanMove(1, 0, false);
				} else {
					text.setText("Invaid choice");
				}
			} else if (buttonClicked.equals(button5)) {
				if (theGame.available(1, 1)) {
					button1.setText("X");
					theGame.humanMove(1, 1, false);
				} else {
					text.setText("Invaid choice");
				}
			} else if (buttonClicked.equals(button6)) {
				if (theGame.available(1, 2)) {
					button1.setText("X");
					theGame.humanMove(1, 2, false);
				} else {
					text.setText("Invaid choice");
				}
			} else if (buttonClicked.equals(button7)) {
				if (theGame.available(2, 0)) {
					button1.setText("X");
					theGame.humanMove(2, 0, false);
				} else {
					text.setText("Invaid choice");
				}
			} else if (buttonClicked.equals(button8)) {
				if (theGame.available(2, 1)) {
					button1.setText("X");
					theGame.humanMove(2, 1, false);
				} else {
					text.setText("Invaid choice");
				}
			} else if (buttonClicked.equals(button9)) {
				if (theGame.available(2, 2)) {
					button1.setText("X");
					theGame.humanMove(2, 2, false);
				} else {
					text.setText("Invaid choice");
				}
			}
			if (theGame.stillRunning() == false) {
				button1.setDisable(true);
				button2.setDisable(true);
				button3.setDisable(true);
				button4.setDisable(true);
				button5.setDisable(true);
				button6.setDisable(true);
				button7.setDisable(true);
				button8.setDisable(true);
				button9.setDisable(true);
			}
			if (theGame.didWin('X')) {
				text.setText("X wins");
			} else if (theGame.didWin('O')) {
				text.setText("O wins");
			} else if (theGame.tied()) {
				text.setText("Tied");
			}
		}
	}
	
	// This method is called by Observable's notifyObservers()
	@Override
	public void update(Object observable) {
		if(theGame.stillRunning()) {
			button1.setDisable(false);
			button2.setDisable(false);
			button3.setDisable(false);
			button4.setDisable(false);
			button5.setDisable(false);
			button6.setDisable(false);
			button7.setDisable(false);
			button8.setDisable(false);
			button9.setDisable(false);
		}
		// set each button to whatever is already on the board whenever this is updated!
		button1.setText(theGame.getTicTacToeBoard()[0][0] + "");
		button2.setText(theGame.getTicTacToeBoard()[0][1] + "");
		button3.setText(theGame.getTicTacToeBoard()[0][2] + "");
		button4.setText(theGame.getTicTacToeBoard()[1][0] + "");
		button5.setText(theGame.getTicTacToeBoard()[1][1] + "");
		button6.setText(theGame.getTicTacToeBoard()[1][2] + "");
		button7.setText(theGame.getTicTacToeBoard()[2][0] + "");
		button8.setText(theGame.getTicTacToeBoard()[2][1] + "");
		button9.setText(theGame.getTicTacToeBoard()[2][2] + "");
		System.out.println("update called from the Observable TicTacToeGame");
	}
}
