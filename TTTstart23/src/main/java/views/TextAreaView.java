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
import model.OurObserver;
import model.TicTacToeGame; 

public class TextAreaView extends BorderPane implements OurObserver {

	private TicTacToeGame theGame;
	private TextField textField = new TextField();
	private TextField textField2 = new TextField();
	private Label row = new Label("row");
	private Label col = new Label("column");
	private Button button = new Button("Make Move");
	private TextArea message = new TextArea();
	
	public TextAreaView(TicTacToeGame theModel) {
		theGame = theModel;
		initializePanel();
	}

	private void initializePanel() { 
		GridPane display = new GridPane();
		display.setStyle("-fx-border-color: blue; border-width: 10;");
		display.setHgap(10);
		display.setVgap(10); 
		display.add(row, 2, 1);
		display.add(col, 2, 2);
		display.add(textField, 3, 1);
		display.add(textField2, 3, 2);
		display.add(button, 3, 3); 
		message.setStyle("-fx-border-color: blue; border-width: 10;");
		Font font = new Font("Monospace", 50);
		message.setFont(font);
		message.setEditable(false);
		message.setText(theGame.toString());
		this.setCenter(display);
		this.setBottom(message);
		EventHandler<ActionEvent> buttonHandler = new buttonHandler();
		button.setOnAction(buttonHandler);
		
	}
	
	
	/**
	 * This class will get the text from the two textfields and make sure
	 * they are able to be parsed into a double. So, the user must enter 
	 * a valid number. It will also make sure the range is 0-2 inclusive.
	 * After this, update the game board if avaiable and check if the game is over.
	 * @author robby
	 *
	 */
	private class buttonHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			int row = 0;
			int col = 0;
			try {
				row = (int) Double.parseDouble(textField.getText());
				col = (int) Double.parseDouble(textField2.getText());
			} catch (Exception NumberFormatException) {
				button.setText("Invalid choice");
			}
			if (row > 2 || col > 2 || row < 0 || col < 0) {
				button.setText("Invalid choice");
			} else if (theGame.available(row, col) == false) {
				button.setText("Invalid choice");
			} else {
				theGame.humanMove(row, col, false);
				message.setText(theGame.toString());
				textField.clear();
				textField2.clear();
				button.setText("Make choice");
			}
			if (theGame.didWin('X')) {
				button.setText("X wins");
				textField.setEditable(false);
				textField2.setEditable(false);
			} else if (theGame.didWin('O')) {
				button.setText("O wins");
				textField.setEditable(false);
				textField2.setEditable(false);
			} else if (theGame.tied()) {
				button.setText("Tied");
				textField.setEditable(false);
				textField2.setEditable(false);
			}
		} 
	} 


	// This method is called by Observable's notifyObservers()
	@Override
	public void update(Object observable) {
		if (theGame.didWin('X')) {
			button.setText("X wins");
			textField.setEditable(false);
			textField2.setEditable(false);
		} else if (theGame.didWin('O')) {
			button.setText("O wins");
			textField.setEditable(false);
			textField2.setEditable(false);
		} else if (theGame.tied()) {
			button.setText("Tied");
			textField.setEditable(false);
			textField2.setEditable(false);
		} else {
			button.setText("Make choice");
			textField.setEditable(true);
			textField2.setEditable(true);
		}
		// print the board each time we update, especially if the brother class buttonView.java updates.
		message.setText(theGame.toString());
		System.out.println("update called from the Observable TicTacToeGame");
	}
}