/*
 * Class: LE62View
 * -> This class provides the user interface for the "Clinic Billing System," handling the generation and display of random billing records and total income calculations.
 * * Exclusive Functions:
 * getContent() - Method for initializing the view, setting up the generation logic, and returning the main layout.
 * @param - none
 * @return - VBox
 * * settings() - Helper method for configuring the visual properties, styles, and alignment of the UI components.
 * @param - VBox, TextArea, Label, Button
 * @return - void
 * * outputRecords() - Helper method that performs the core logic: generates random entities, calculates totals, formats the output, and checks for duplicates.
 * @param - StringBuilder, String[], String[], String[], double[], double, Doctor[], Random
 * @return - void
 */
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.Random;

public class LE62View 
{
    public VBox getContent() 
    {
        Button generate = new Button("Generate Records");
        Label label52 = new Label("Clinic Billing System");
    	TextArea display = new TextArea("Click [Generate] to create billing records...");
        VBox le62Content = new VBox(10);

		settings(le62Content, display, label52, generate);
       
        generate.setOnAction(e -> 
        {
            String[] docNames = {"Dr. Smith", "Dr. Jones", "Dr. Strange", "Dr. House", "Dr. Grey"};
            String[] specialties = {"Pediatrician", "Cardiologist", "Dermatologist", "Neurologist", "Orthopedist"};
            String[] patNames = {"John Doe", "Jane Smith", "Alice Johnson", "Bob Brown", "Charlie Davis"};
            double[] fees = {150.00, 200.50, 175.75, 225.00, 300.00};
            double totalIncome = 0.0;
            
			Doctor[] doctorList = new Doctor[3];

            Random rand = new Random();
            StringBuilder sb = new StringBuilder();
            
            sb.append("--- GENERATED BILLING REPORT ---\n\n");
            outputRecords(sb, docNames, specialties, patNames, 
						fees, totalIncome, doctorList, rand);
            display.setText(sb.toString());
        });
        
        le62Content.getChildren().addAll(label52, generate, display);
        return le62Content;
    }

	private void settings(VBox le62Content, TextArea display, Label label52, Button generate)
	{
		le62Content.setAlignment(Pos.CENTER);
		
		display.setWrapText(true);
        display.setEditable(false);
        display.setPrefHeight(400); 
        display.setPrefWidth(350);

        display.setStyle("-fx-control-inner-background: #3E3E4E; " 
                        + "-fx-text-fill: white; "
                        + "-fx-highlight-fill: #00ADB5; " 
                        + "-fx-highlight-text-fill: black;");
		
		label52.setFont(Font.font("Times New Roman", 32));
        label52.setTextFill(Color.WHITE);

		ButtonStyler.styleActionButton(generate); 
        VBox.setVgrow(display, Priority.ALWAYS);
	}

	private void outputRecords(StringBuilder sb, String[] docNames, String[] specialties, String[] patNames, double[] fees, double totalIncome, Doctor[] doctorList, Random rand)
	{
		for(int i = 0; i < 3; i++) 
            {
				int r = rand.nextInt(5);
                Doctor doc = new Doctor
				(
                    docNames[r], 
                    specialties[r], 
                    fees[r]
                );
                
                doctorList[i] = doc;

                String randomId = "P-" + (100 + rand.nextInt(900));
                Patient pat = new Patient
				(
                    patNames[rand.nextInt(patNames.length)], 
                    randomId
                );

                Billing bill = new Billing(pat, doc);

                sb.append("Record #").append(i + 1).append("\n");
                sb.append(bill.toString()); 
                sb.append("\n\n");

                totalIncome += bill.getBillingAmount();
            }

            sb.append("--------------------------------\n");
            sb.append("TOTAL INCOME: $").append(String.format("%.2f", totalIncome));
            sb.append("\n\n");

            sb.append("--- DUPLICATE DOCTOR CHECK ---\n");
            boolean foundDuplicate = false;

            for(int i = 0; i < doctorList.length; i++) 
			{
                for(int j = i + 1; j < doctorList.length; j++) 
				{
                    if(doctorList[i].equals(doctorList[j])) 
						{
                        sb.append("• Doctor in Record #").append(i + 1)
                          .append(" is same as Record #").append(j + 1).append("\n");
                        foundDuplicate = true;
                    }
                }
            }

            if(!foundDuplicate) 
			{
                sb.append("No duplicate doctors found.");
            }
	}
}