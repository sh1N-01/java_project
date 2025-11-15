import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ButtonStyler {
	
	/**
     * Styles a navigation button (purple theme).
     *
     * @param b The Button to style.
     */
	public static void styleNavButton(Button b) {
		
		b.setFont(Font.font("Roboto", FontWeight.NORMAL, 16));
		b.setPrefHeight(30);
		b.setPrefWidth(90);
		b.setBackground(new Background(
		        new BackgroundFill(Color.web("#5865F2"), new CornerRadii(6), Insets.EMPTY)
		    ));
		    b.setTextFill(Color.WHITE);
	}
	
	/**
     * Styles the Exit button (red theme).
     *
     * @param b The Button to style.
     */
	public static void styleExitButton(Button b) {
		b.setFont(Font.font("Roboto", FontWeight.NORMAL, 16));
	    b.setPrefHeight(30);
	    b.setPrefWidth(75);
	    b.setBackground(new Background(
	        new BackgroundFill(Color.web("#ED4245"), new CornerRadii(6), Insets.EMPTY)
	    ));
	    b.setTextFill(Color.WHITE);
	}
	
	/**
     * Styles an action button (for exercise operations).
     *
     * @param b The Button to style.
     */
	public static void styleActionButton(Button b) {
		
		b.setFont(Font.font("Roboto", FontWeight.NORMAL, 16));
		b.setPrefHeight(30);
		b.setBackground(new Background(
		        new BackgroundFill(Color.web("#9B84EE"), new CornerRadii(6), Insets.EMPTY)
		    ));
		    b.setTextFill(Color.WHITE);
	}

}
