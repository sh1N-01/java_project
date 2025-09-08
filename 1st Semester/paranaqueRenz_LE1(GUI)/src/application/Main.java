package application;
/*
 * Name: Renz Joseph P. Parañaque
 * Project: Laboratory Exercise #1: Change Denomination, Multiply Digits, 
 * BMR Chocolate Calculator, Distance Formula Calculator
 * Creation date: September 9, 2025
 * 
 * Change Denomination: Determines the change to be dispensed from a vending machine. 
 * The user enters an amount between 1 cent and 99 cents. The program determines a 
 * combination of coins equal to  that amount Denominations is in a formed of: 
 * quarter, dime, nickel and penny.
 * 
 * Multiply the Digits: Reads an integer between 0 and 1000. Display and multiplies 
 * all the digits in the integer. Assume that the user follows directions.
 * (Hint: Use the division and remainder operators).
 * 
 * BMR Chocolate Calculator: A typical chocolate bar will contain around 230 calories. Write 
 * a program that allows the user to input their weight in pounds, height in inches, and 
 * age in years. The program should then output the number of chocolate bars that should 
 * be consumed to maintain one’s weight for both a woman and a man of the input weight, height, and age.
 * 
 * Distance Formula Calculator: Prompts the user to enter two points (x1, y1) and (x2, y2) 
 * and displays their distance. The formula for computing the distance is √(𝑥2 − 𝑥1)2 +
 *  (𝑦2 − 𝑦1)2. Note you can use Math.pow(a, 0.5) to compute square-root
 * 
 * Custom Functions:
 * changeDenomination() - calculates the number of coins for a given denomination
 * return type: int, parameters: int cents, int divisor
 * bmrMan() - Calculates the BMR for man
 * return type: double, parameters, double weight, double height, int age
 * bmrWoman() - Calculates the BMR for Woman
 * return type: double, parameters, double weight, double height, int age
 * chocolateBars() - Calculates the number of chocolate bars based on BMR
 * return type: double, parameters: double bmr
 * distanceFormula() - Calculates the distance between two points
 * return type: double, parameters: double m, double n, double o, double p	 
 * 
*/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application 
{
	//Function to clear all nodes below the buttons when a selection button is clicked
	private void clearBelowButtons(GridPane root) 
	{
		root.getChildren().removeIf(node -> {
			Integer row = GridPane.getRowIndex(node);
			return row != null && row > 0;
		});
	}
	
	@Override
	public void start(Stage stage) throws IOException 
	{
		//Create main scene layout
		GridPane root = new GridPane();
		Scene scene = new Scene(root, 700, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		//Set layout properties
		root.setAlignment(Pos.TOP_CENTER);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(20, 20, 20, 20));
		//Add buttons
		Button button1 = new Button("Change Denomination");
		Button button2 = new Button("Multiply Digits");
		Button button3 = new Button("BMR Chocolate Calculator");
		Button button4 = new Button("Distance Formula Calculator");
		//Set button properties
		button1.setMaxWidth(Double.MAX_VALUE);
		button2.setMaxWidth(Double.MAX_VALUE);
		button3.setMaxWidth(Double.MAX_VALUE);
		button4.setMaxWidth(Double.MAX_VALUE);
		button1.setWrapText(true);
		button2.setWrapText(true);
		button3.setWrapText(true);
		button4.setWrapText(true);
		//Add button on a grid, or on a specific coordinate
		root.add(button1, 0, 0);
		root.add(button2, 1, 0);
		root.add(button3, 2, 0);
		root.add(button4, 3, 0);
		//Set the scene
		stage.setScene(scene);
		stage.setTitle("Laboratory Exercise #1");
		stage.setResizable(false);
		stage.show();
		// Change Denomination Function
		button1.setOnAction(e -> {
			clearBelowButtons(root);
			//Create necessary ui elements for the function
			Label label = new Label("Input a cent value (1-99)");
			TextField inputField = new TextField();
			Button calcBtn = new Button("Calculate");
			Label result = new Label();
			//Set ui elements on a grid, or on a specific coordinate
			GridPane.setConstraints(label, 0, 1);
			GridPane.setConstraints(inputField, 1, 1);
			GridPane.setConstraints(calcBtn, 2, 1);
			GridPane.setConstraints(result, 0, 2, 3, 1);
			//Add ui elements to the layout
			root.getChildren().addAll(label, inputField, calcBtn, result);
			//Set button action
			calcBtn.setOnAction(ev -> {
				try 
				{
					int cents = Integer.parseInt(inputField.getText());
					if(cents < 1 || cents > 99) 
					{
						result.setText("Please enter a value between 1 and 99");
						return;
					}
					//Calculate denominations
					int quarters = Operations.changeDenomination(cents, 25);
					cents %= 25;
					int dimes =  Operations.changeDenomination(cents, 10);
					cents %= 10;
					int nickels = Operations.changeDenomination(cents, 5);
					cents %= 5;
					int pennies = cents;
					//Display result
					result.setText(String.format("Quarters: %d, Dimes: %d, Nickels: %d, Pennies: %d", quarters, dimes, nickels, pennies));
				} 
				
				catch(NumberFormatException ex) 
				{
					result.setText("Invalid input. Please enter an integer.");
				}
			});
		});
		// Multiply Digits
		button2.setOnAction(e -> {
			clearBelowButtons(root);
			//Create necessary ui elements for the function
			Label label = new Label("Enter a number between 1 - 1000");
			label.setWrapText(true);
			label.setPrefWidth(200);
			TextField inputField = new TextField();
			Button calcBtn = new Button("Calculate");
			Label result = new Label();
			//Set ui elements on a grid, or on a specific coordinate
			GridPane.setConstraints(label, 0, 1);
			GridPane.setConstraints(inputField, 1, 1);
			GridPane.setConstraints(calcBtn, 2, 1);
			GridPane.setConstraints(result, 0, 2, 3, 1);
			//Add ui elements to the layout
			root.getChildren().addAll(label, inputField, calcBtn, result);
			//Set button action
			calcBtn.setOnAction(ev -> {
				try 
				{
					//Error handling
					String text = inputField.getText().trim();
					if(text.isEmpty() || !text.matches("\\d+")) 
					{
						result.setText("Please enter a valid integer.");
						return;
					}
					//Convert to integer and check range
					int product = 1;
					for(char c : text.toCharArray()) 
					{
						product *= (c - '0');
					}
					result.setText("Product of digits: " + product);
				} 
				
				catch(Exception ex) 
				{
					result.setText("Error: " + ex.getMessage());
				}
			});
		});
		// BMR Chocolate Calculator
		button3.setOnAction(e -> {
			clearBelowButtons(root);
			//Create necessary ui elements for the function
			Label weightLabel = new Label("Weight (lbs):");
			TextField weightField = new TextField();
			Label heightLabel = new Label("Height (in):");
			TextField heightField = new TextField();
			Label ageLabel = new Label("Age:");
			TextField ageField = new TextField();
			Button calcBtn = new Button("Calculate");
			Label result = new Label();
			//Set ui elements on a grid, or on a specific coordinate
			GridPane.setConstraints(weightLabel, 0, 1);
			GridPane.setConstraints(weightField, 1, 1);
			GridPane.setConstraints(heightLabel, 0, 2);
			GridPane.setConstraints(heightField, 1, 2);
			GridPane.setConstraints(ageLabel, 0, 3);
			GridPane.setConstraints(ageField, 1, 3);
			GridPane.setConstraints(calcBtn, 0, 5);
			GridPane.setConstraints(result, 0, 6, 3, 1);
			//Add ui elements to the layout
			root.getChildren().addAll(weightLabel, weightField, heightLabel, heightField, ageLabel, ageField, calcBtn, result);
			//Set button action
			calcBtn.setOnAction(ev -> {
				try 
				{
					//Parse input values
					double weight = Double.parseDouble(weightField.getText());
					double height = Double.parseDouble(heightField.getText());
					int age = Integer.parseInt(ageField.getText());
					//Calculate BMR and chocolate bars
					double bmrMan = Operations.bmrMan(weight, height, age);
					double bmrWoman = Operations.bmrWoman(weight, height, age);
					int chocoBarsMan = (int) Operations.chocolateBars(bmrMan);
					int chocoBarsWoman = (int) Operations.chocolateBars(bmrWoman);
					//Display result
					result.setText(String.format("BMR for Man: %.2f kcal/day. That's about %d chocolate bars!\n"
							+ "BMR for Woman: %.2f kcal/day. That's about %d chocolate bars!", 
							bmrMan,chocoBarsMan,bmrWoman,chocoBarsWoman));
				} 
				
				catch(Exception ex) 
				
				{
					result.setText("Invalid input. Please check your entries.");
				}
			});
		});
		// Distance Formula Calculator
		button4.setOnAction(e -> {
			clearBelowButtons(root);
			//Create necessary ui elements for the function
			Label x1Label = new Label("X1:");
			TextField x1Field = new TextField();
			Label x2Label = new Label("X2:");
			TextField x2Field = new TextField();
			Label y1Label = new Label("Y1:");
			TextField y1Field = new TextField();
			Label y2Label = new Label("Y2:");
			TextField y2Field = new TextField();
			Button calcBtn = new Button("Calculate");
			Label result = new Label();
			//Set ui elements on a grid, or on a specific coordinate
			GridPane.setConstraints(x1Label, 0, 1);
			GridPane.setConstraints(x1Field, 1, 1);
			GridPane.setConstraints(x2Label, 0, 3);
			GridPane.setConstraints(x2Field, 1, 3);
			GridPane.setConstraints(y1Label, 0, 2);
			GridPane.setConstraints(y1Field, 1, 2);
			GridPane.setConstraints(y2Label, 0, 4);
			GridPane.setConstraints(y2Field, 1, 4);
			GridPane.setConstraints(calcBtn, 0, 5);
			GridPane.setConstraints(result, 0, 6, 3, 1);
			//Add ui elements to the layout
			root.getChildren().addAll(x1Label, x1Field, x2Label, x2Field, y1Label, y1Field, y2Label, y2Field, calcBtn, result);
			//Set button action
			calcBtn.setOnAction(ev -> {
				try 
				{
					//Parse input values
					double x1 = Double.parseDouble(x1Field.getText());
					double y1 = Double.parseDouble(y1Field.getText());
					double x2 = Double.parseDouble(x2Field.getText());
					double y2 = Double.parseDouble(y2Field.getText());
					//Calculate distance
					double dist = Operations.distanceFormula(x1, y1, x2, y2);
					result.setText(String.format("Distance: %.4f", dist));
				} 
				
				catch (Exception ex) 
				{
					result.setText("Invalid input. Please enter valid numbers.");
				}
			});
		});
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}