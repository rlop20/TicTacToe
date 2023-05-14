package views;

/**
 * Play TicTacToe the computer that can have different AIs to beat you. 
 * Select the Options menus to begin a new game, switch strategies for 
 * the computer player (BOT or AI), and to switch between the two views.
 * 
 * This class represents an event-driven program with a graphical user 
 * interface as a controller between the view and the model. It has 
 * event handlers to mediate between the view and the model.
 * 
 * This controller employs the Observer design pattern that updates two 
 * views every time the state of the tic tac toe game changes:
 * 
 *    1) whenever you make a move by clicking a button or an area of either view
 *    2) whenever the computer AI makes a move
 *    3) whenever there is a win or a tie
 *    
 * You can also select two different strategies to play against from the menus
 * 
 * @author Rick Mercer
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.IntermediateAI;
import model.OurObserver;
import model.RandomAI;
import model.TicTacToeGame;

public class TicTacToeGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private TicTacToeGame theGame;

	private MenuBar menuBar;

	private OurObserver currentView;
	private OurObserver buttonView;
	private OurObserver textAreaView;
//  private OurObserver drawingView;
	private MenuButton menuButton = new MenuButton("Options");

	private BorderPane window;
	public static final int width = 254;
	public static final int height = 360;

	public void start(Stage stage) {
		MenuItem restart = new MenuItem("New Game");
		MenuItem random = new MenuItem("RandomAI");
		MenuItem inter = new MenuItem("IntermediateAI");
		MenuItem buttonMode = new MenuItem("Button");
		MenuItem text = new MenuItem("TextArea");
		menuButton.getItems().addAll(restart, random, inter, buttonMode, text);
		stage.setTitle("Tic Tac Toe");
		window = new BorderPane();
		Scene scene = new Scene(window, width, height);
		initializeGameForTheFirstTime();
		
		// if user presses new game, restart game 
		EventHandler<ActionEvent> newGame = new newGame(); 
		restart.setOnAction(newGame);
		
		// if user presses intermediateAI, switch the AI
		EventHandler<ActionEvent> interAI = new interAI();
		inter.setOnAction(interAI);
		
		// if user presses random, switch the AI
		EventHandler<ActionEvent> randomAI = new randomAI();
		random.setOnAction(randomAI);

		// if user presses textArea, switch the view
		EventHandler<ActionEvent> textView = new textMode();
		text.setOnAction(textView);

		// if user presses button, switch the view
		EventHandler<ActionEvent> buttonModebutton = new buttonMode();
		buttonMode.setOnAction(buttonModebutton);

		// Set up the views
		buttonView = new ButtonView(theGame);
		textAreaView = new TextAreaView(theGame);

//    drawingView = new DrawingView(theGame);
		theGame.addObserver(buttonView);
		theGame.addObserver(textAreaView);
//    theGame.addObserver(drawingView);
		setViewTo(textAreaView);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Set the game to the default of an empty board and the random AI.
	 */
	public void initializeGameForTheFirstTime() {
		theGame = new TicTacToeGame();
		// This event driven program will always have
		// a computer player who takes the second turn
		theGame.setComputerPlayerStrategy(new IntermediateAI());
	}

	private void setViewTo(OurObserver newView) {
		window.setTop(menuButton);
		currentView = newView;
		window.setCenter((Node) currentView);
	}

	private class newGame implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			theGame.restart();
		}
	}
	
	private class textMode implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			setViewTo(textAreaView);
		}
	}

	private class buttonMode implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			setViewTo(buttonView);
		}
	}

	private class interAI implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			theGame.setComputerPlayerStrategy(new IntermediateAI());
		}
	}

	private class randomAI implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			theGame.setComputerPlayerStrategy(new RandomAI());
		}
	}
}