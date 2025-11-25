/*
 * Class: LE61View
 * -> This class provides the user interface for the "Drawable Shape - Square" exercise, managing user inputs and rendering the shape on a canvas.
 * * Exclusive Functions:
 * getContent() - Method for initializing and retrieving the main layout containing input fields, buttons, and the drawing canvas.
 * @param - none
 * @return - VBox
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LE61View {
	
	private Pane canvas;
    private Label infoLabel;
	
	public VBox getContent() {
	
		// VBox for LE51 Content
		VBox le61Content = new VBox(10);
		le61Content.setAlignment(Pos.CENTER);
					
		Label title = new Label("Drawable Shape - Square");
		title.setFont(Font.font("Times New Roman", 32));
		title.setTextFill(Color.WHITE);
		
		TextField sideInput = new TextField();
		sideInput.setPromptText("Enter side length");
		sideInput.setPrefWidth(200);
		sideInput.setStyle("-fx-background-color: #3E3E4E; -fx-text-fill: white; "
		                 + "-fx-font-size: 14px; -fx-padding: 5px; -fx-border-radius: 5px;");
		
		TextField colorInput = new TextField();
        colorInput.setPromptText("Enter color (e.g. red, blue, #A78BFA)");
        colorInput.setPrefWidth(200);
        colorInput.setStyle("-fx-background-color: #3E3E4E; -fx-text-fill: white; "
                          + "-fx-font-size: 14px; -fx-padding: 5px; -fx-border-radius: 5px;");
		
		Button drawBtn = new Button("Draw Square");
		ButtonStyler.styleActionButton(drawBtn);
		
		infoLabel = new Label();
		infoLabel.setTextFill(Color.YELLOW);
		
		canvas = new Pane();
		canvas.setPrefSize(300, 300);
		canvas.setStyle("-fx-background-color: #2E2E3E; -fx-border-color: #A78BFA; -fx-border-width: 2px;");
		VBox.setMargin(canvas, new Insets(10, 0, 10, 0));
		
		drawBtn.setOnAction(e -> {
			
			try {
				
				int side = Integer.parseInt(sideInput.getText());
				String color = colorInput.getText();

				if(color.isEmpty()) 
				{
				    color = "blue"; 
				}

				else
				{
					try 
					{
                        Color.web(color);
                    } 
					catch (IllegalArgumentException colorEx) 
					{
                        infoLabel.setText("Invalid color! Input a valid or basic color name.");
                        return; 
                    }
				}
				
				int maxSize = 250;
				if (side > maxSize) {
					infoLabel.setText("Sides too large. Max allowed: " + maxSize);
					return;
				}
				
				int x = (300 - side) / 2;
				int y = (300 - side) / 2;
				
				Square sq = new Square(x, y, color, side); // fixed position
				canvas.getChildren().clear();
				canvas.getChildren().add(sq.draw());
				
				infoLabel.setText("Area: " + sq.getArea() + " | Perimeter: " + sq.getPerimeter());
				
			} catch (NumberFormatException ex) {
				infoLabel.setText("Invalid input. Please enter as integer.");
			}
			
		});
		
		le61Content.getChildren().addAll(title, sideInput, colorInput, drawBtn, infoLabel, canvas);
		return le61Content;
	}
	
	

}
