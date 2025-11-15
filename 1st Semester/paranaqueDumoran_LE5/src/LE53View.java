/*
 * Class: LE53View
 * -> This class is responsible for creating a GUI for encrypting and testing a 9-digit PIN.
 *    It provides functionality to input a PIN, encrypt it, display the true and encrypted PINs,
 *    and test the encrypted PIN for validation.
 * 
 * Exclusive Functions:
 * LE53View() - Default constructor for the class
 * @param - none, @return - none
 * 
 * getContent() - Builds and returns the main VBox layout containing all GUI elements
 * @param - none, @return - VBox
 * 
 * elementPrpts() - Sets properties for the GUI elements such as alignment, font, and styles
 * @param - VBox, HBox, Label, Label, Label, Label, Label, Button, Button, TextField
 * @return - void
 * 
 * displayElmnts() - Adds GUI elements to the VBox and HBox containers
 * @param - VBox, HBox, Label, TextField, Label, Button, Label, Label, Label, Button
 * @return - void
 * 
 * errorFunction() - Displays an error message when the input PIN is invalid
 * @param - Label, Label, Label, Button
 * @return - void
 * 
 * successFunction() - Encrypts the input PIN, displays the true and encrypted PINs, and enables the "Test PIN" button
 * @param - Label, Label, int, Label, Button, TextField
 * @return - void
 * 
 * testPinFunction() - Handles the testing of the encrypted PIN and displays success or failure messages
 * @param - Label, Label, TextField, Button, Button, Label, Label, Label
 * @return - void
 * 
 * arrayToInt() - Converts an array of integers into a single integer
 * @param - int[] array
 * @return - int
 */
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LE53View 
{
	private int[] encrypted = new int[9];
	private int[] truE = new int[9];

	public VBox getContent() 
	{
		VBox le53Content = new VBox(10);
		HBox leftElements = new HBox(10);

		Label label53 = new Label("Password Encrypter");
		Label truePIN = new Label();
		Label encryptedPIN = new Label();
		Label newPinLabel = new Label("Enter New PIN");
		Label errorMessage = new Label();
		TextField newPinField = new TextField();
		Button encrypt = new Button("Encrypt PIN");
		Button testPIN = new Button("Test PIN");
		
		elementPrpts(le53Content, leftElements, label53, truePIN, encryptedPIN, newPinLabel, errorMessage, encrypt, testPIN, newPinField);
		testPIN.setVisible(false);
		newPinField.setEditable(true);

		displayElmnts(le53Content, leftElements, newPinLabel, newPinField, label53, encrypt, errorMessage, truePIN, encryptedPIN, testPIN);

		encrypt.setOnAction(e -> 
		{
			int pin = newPinField.getText().matches("\\d{9}") ? Integer.parseInt(newPinField.getText()) : -1;
			if(pin == -1)
			{
				errorFunction(truePIN, encryptedPIN, errorMessage, testPIN);
			}

			else
			{
				newPinField.setEditable(false);
				successFunction(truePIN, encryptedPIN, pin, errorMessage, testPIN, newPinField);
			}
		});

		testPIN.setOnAction(e -> 
		{
			testPinFunction(label53, newPinLabel, newPinField, testPIN, encrypt, errorMessage, encryptedPIN, truePIN);
		});
		
		return le53Content;
	}

	private void elementPrpts(VBox le53Content, HBox leftElements, Label label53, Label truePIN, Label encryptedPIN, Label newPinLabel, Label errorMessage, Button encrypt, Button testPIN, TextField newPinField)
	{
		le53Content.setAlignment(Pos.CENTER);
		leftElements.setAlignment(Pos.CENTER);

		label53.setFont(Font.font("Times New Roman", 32));
		label53.setTextFill(Color.WHITE);
		truePIN.setFont(Font.font("Times New Roman", 16));
		truePIN.setTextFill(Color.WHITE);
		encryptedPIN.setFont(Font.font("Times New Roman", 16));
		encryptedPIN.setTextFill(Color.WHITE);
		newPinLabel.setFont(Font.font("Times New Roman", 16));
		newPinLabel.setTextFill(Color.WHITE);
		errorMessage.setFont(Font.font("Times New Roman", 14));
		errorMessage.setTextFill(Color.RED);

		newPinField.setMaxWidth(100);

		encrypt.setFont(Font.font("Times New Roman", 16));
		encrypt.setTextFill(Color.WHITE);
		encrypt.setStyle("-fx-background-color: #9b84ee;");
		testPIN.setFont(Font.font("Times New Roman", 16));
		testPIN.setTextFill(Color.WHITE);
		testPIN.setStyle("-fx-background-color: #9b84ee;");
	}

	private void displayElmnts(VBox le53Content, HBox leftElements, Label newPinLabel, TextField newPinField, Label label53, Button encrypt, Label errorMessage, Label truePIN, Label encryptedPIN, Button testPIN)
	{
		leftElements.getChildren().addAll(newPinLabel, newPinField);
		le53Content.getChildren().addAll(label53, leftElements, encrypt, errorMessage, truePIN, encryptedPIN, testPIN);
	}

	private void errorFunction(Label truePIN, Label encryptedPIN, Label errorMessage, Button testPIN)
	{
		truePIN.setText("");
		encryptedPIN.setText("");
		errorMessage.setText("Error: Please enter a valid 9-digit PIN.");
		testPIN.setVisible(false);
	}

	private void successFunction(Label truePIN, Label encryptedPIN, int pin, Label errorMessage, Button testPIN, TextField newPinField)
	{
		errorMessage.setText("");

		Password PIN = new Password(pin);
		encrypted = PIN.getEncryptedPIN();
		truE = PIN.getTruePIN();

		StringBuilder truePinStr = new StringBuilder("True PIN: ");
		for(int digit : truE)
		{
			truePinStr.append(digit);
		}
		truePIN.setText(truePinStr.toString());

		StringBuilder encryptedPinStr = new StringBuilder("Encrypted PIN: ");
		for(int digit : encrypted)
		{
			encryptedPinStr.append(digit);
		}
		encryptedPIN.setText(encryptedPinStr.toString());

		testPIN.setVisible(true);
	}

	private void testPinFunction(Label label53, Label newPinLabel, TextField newPinField, Button testPIN, Button encrypt, Label errorMessage, Label encryptedPIN, Label truePIN) 
	{
		label53.setText("Encrypted PIN Testing");
		newPinField.setText("");
		newPinLabel.setText("Enter Encrypted PIN (Press [ENTER] if done)");
		encryptedPIN.setText("");
		truePIN.setText("");
		
		newPinField.setEditable(true);
		testPIN.setVisible(false);
		encrypt.setVisible(false);

		newPinField.setOnAction(e -> 
		{
			int enteredPin = newPinField.getText().matches("\\d{9}") ? Integer.parseInt(newPinField.getText()) : -1;
			if(enteredPin == -1)
			{
				errorMessage.setTextFill(Color.RED);
				errorMessage.setText("Error: Please enter a valid 9-digit PIN.");
			}

			else
			{
				if(enteredPin == arrayToInt(encrypted))
				{
					errorMessage.setTextFill(Color.GREEN);
					errorMessage.setText("Success: Encrypted PIN matches!");
				}
				else
				{
					errorMessage.setTextFill(Color.RED);
					errorMessage.setText("Failure: Encrypted PIN does not match.");
				}
			}
		});
	}

	private int arrayToInt(int[] array) 
	{
		int result = 0;
		for (int digit : array) 
		{
			result = result * 10 + digit;
		}
		return result;
	}
}
