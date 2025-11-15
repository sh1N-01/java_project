import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;

public class LE52View {
	
	private Hangman game;
	private Label wordLabel;
	private Label statusLabel;
	private Label missesLabel;
	private Pane hangmanPane;
	
	public VBox getContent() {
		
		game = new Hangman();
		
		VBox le52Content = new VBox(10);
		le52Content.setAlignment(Pos.CENTER);
		
		Label label52 = new Label("Hangman");
		label52.setFont(Font.font("Times New Roman", 32));
		label52.setTextFill(Color.WHITE);
		
		wordLabel = new Label(game.getDisplayWord());
		wordLabel.setFont(Font.font("Consolas", 28));
        wordLabel.setTextFill(Color.web("#A78BFA"));
		
		TextField inputField = new TextField();
		inputField.setMaxWidth(56);
		
		Button guessBtn = new Button("Guess");
		ButtonStyler.styleActionButton(guessBtn);
		
		statusLabel = new Label();
        statusLabel.setTextFill(Color.YELLOW);
		
        missesLabel = new Label("Misses: 0");
        missesLabel.setTextFill(Color.RED);
		
        // Pane for hangman drawing
        hangmanPane = new Pane();
        hangmanPane.setPrefSize(200,250);
        hangmanPane.setStyle("-fx-background-color: #2E2E3E;");
        
		guessBtn.setOnAction(e -> 
		{
			String input = inputField.getText().trim();
			if(input.isEmpty())
			{
				statusLabel.setText("Please enter a single letter.");
				return;
			}

			else if(input.length() > 1)
			{
				statusLabel.setText("Please enter only one letter at a time.");
				return;
			}

			else if(!Character.isLetter(input.charAt(0)))
			{	
				statusLabel.setText("Invalid input. Please enter a letter (A-Z).");
				return;
			}

			else
			{
				statusLabel.setText("");
				
				char guess = inputField.getText().trim().charAt(0);
				String result = game.guessLetter(guess);

				wordLabel.setText(game.getDisplayWord());
				statusLabel.setText(result);
				missesLabel.setText("Misses: " + game.getMisses());
				inputField.clear();
				
				int n = game.getMisses();
				if(n != 0)
				{
					drawHangman(n);
				}

				if(n == 7)
				{
					inputField.setDisable(true);
					guessBtn.setDisable(true);

					statusLabel.setText("Game Over! The word was " + game.getWord() + ".");
				}
				
				if(game.isWordComplete()) 
				{
					statusLabel.setText("The word is " + game.getWord() + ". You missed " + game.getMisses() + " time(s).");
					
				}
			}
		});
		
		Button resetBtn = new Button("Play Again");
		ButtonStyler.styleActionButton(resetBtn);
		resetBtn.setOnAction(e -> 
		{
			game.startNewGame();
			inputField.setDisable(false);
			guessBtn.setDisable(false);
			wordLabel.setText(game.getDisplayWord());
			statusLabel.setText("");
			missesLabel.setText("Misses: 0");
			hangmanPane.getChildren().clear(); // clear drawing
		});
							
		le52Content.getChildren().addAll(label52, wordLabel, inputField, guessBtn, statusLabel, missesLabel, hangmanPane, resetBtn);
		return le52Content;
	}
	
	/** Draws hangman figure based on number of misses */
	private void drawHangman(int misses) {
        hangmanPane.getChildren().clear();

        // Gallows
        Line base = new Line(20, 230, 180, 230);
        Line pole = new Line(50, 230, 50, 50);
        Line beam = new Line(50, 50, 120, 50);
        Line rope = new Line(120, 50, 120, 80);
        hangmanPane.getChildren().addAll(base, pole, beam);

        if (misses >= 1) hangmanPane.getChildren().add(rope);
        if (misses >= 2) hangmanPane.getChildren().add(new Circle(120, 100, 20)); // head
        if (misses >= 3) hangmanPane.getChildren().add(new Line(120, 120, 120, 170)); // body
        if (misses >= 4) hangmanPane.getChildren().add(new Line(120, 130, 90, 150)); // left arm
        if (misses >= 5) hangmanPane.getChildren().add(new Line(120, 130, 150, 150)); // right arm
        if (misses >= 6) hangmanPane.getChildren().add(new Line(120, 170, 90, 200)); // left leg
        if (misses >= 7) hangmanPane.getChildren().add(new Line(120, 170, 150, 200)); // right leg
    }

}
