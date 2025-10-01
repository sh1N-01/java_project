/*
 * Class: Person
 * -> This class is in charge of displaying the elements needed for
 *    Laboratory Exercise 3.1
 * 
 * Exclusive Functions:
 * Person() - class constructor
 * @param - String, integer, @return - void
 * personGUI() - Method for displaying the elements on the scene and
 * extracting user input,
 * @param - GridPane, @return - void
 * setPrprts() - Method in charge of setting elements on specific coords
 * @param - GridPane, @return - void
 * displayElements() - Method just to display elements on the GUI
 * @param - GridPane, @return - void
 * rebooling() - Method in charge of concatinating the result string
 * @param - StringBuilder, Boolean, Boolean, Boolean, Boolean, Boolean, String, @return - void
 * displayResult() - Display resulting string built from StringBuilder
 * @param - GridPane, StringBuilder, @return - void
 * isValidName() - check if inputted name has no numbers
 * @param - String, @return - boolean
 * getName() - function to get name
 * @param - none, @return - String
 * getAge() - function to get age
 * @param - none, @return - int
 * set() - Method in charge of setting name and age. Primarily used for testing
 * @param - none, @return - void
 */

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Person
{
    private Label perL = new Label("Enter Person 1's Name & Age");
    private TextField perT = new TextField();
    private TextField perT2 = new TextField();
    private Label per1L = new Label("Enter Person 2's Name & Age");
    private TextField per1T = new TextField();
    private TextField per1T2 = new TextField();
    private Button test = new Button("Perform Testing");
    private Label results = new Label();

    private String personName;
    private int personAge;

    public Person()
    {

    }

    public Person(String n, int a)
    {
        this.personName = n;
        this.personAge = a;
    }

    public void personGUI(GridPane root)
    {
        Action.clearBelowButtons(root);

        setPrprts(root);
        displayElements(root);
        //Execute on click
        test.setOnAction(e -> 
        {
            String name1 = perT.getText();
            String name2 = per1T.getText();
            //Error Trap
            try 
            {
                if(!isValidName(name1) || !isValidName(name2)) 
                {
                    results.setText("Name must contain only letters!");
                    return; 
                }

                int age1 = Integer.parseInt(perT2.getText());
                int age2 = Integer.parseInt(per1T2.getText());

                Person p1 = new Person(name1, age1);
                Person p2 = new Person(name2, age2);

                boolean sameName = p1.personName.equalsIgnoreCase(p2.personName);
                boolean sameNameAge = (p1.personName.equals(p2.personName) && p1.personAge == p2.personAge);
                boolean sameAge = (p1.personAge == p2.personAge);
                boolean isOlder = (p1.personAge > p2.personAge);
                boolean isYounger = (p1.personAge < p2.personAge);

                StringBuilder reBool = new StringBuilder();
                rebooling(reBool, sameName, sameNameAge, sameAge, isOlder, isYounger, p1.personName);
                displayResult(root, reBool);

            } 

            catch(NumberFormatException ex) 
            {
                results.setText("Invalid Input! Age must be an integer.");
            }
         });
    }

    private void setPrprts(GridPane root)
    {
        GridPane.setConstraints(perL, 0, 1);
        GridPane.setConstraints(perT, 1, 1);
        GridPane.setConstraints(perT2, 2, 1);
        GridPane.setConstraints(per1L, 0, 2);
        GridPane.setConstraints(per1T, 1, 2);
        GridPane.setConstraints(per1T2, 2, 2);
        GridPane.setConstraints(results, 0, 5);
        GridPane.setConstraints(test, 0, 4);
    }

    private void displayElements(GridPane root)
    {
        root.getChildren().addAll(perL, perT, perT2, per1L, per1T, per1T2, test, results);
    }

    private void rebooling(StringBuilder reBool, Boolean m, Boolean n, Boolean o, Boolean p, Boolean q, String r)
    {
        reBool.append("Same Name & Age: " + n + "\n");
        reBool.append("Same Name: " + m + "\n");
        reBool.append("Same Age: " + o + "\n");
        reBool.append("Is " + r + " Older?: " + p + "\n");
        reBool.append("Is " + r + " Younger?: " + q + "\n");
    }

    public void displayResult(GridPane root, StringBuilder reBool)
    {
        if(!root.getChildren().contains(results)) 
        {
            root.getChildren().add(results);
        }
        results.setText(reBool.toString());
    }

    private boolean isValidName(String input) 
    {
        return input != null && input.matches("[a-zA-Z]+");
    }

    public String getName()
    {
        return this.personName;
    }

    public int getAge()
    {
        return this.personAge;
    }

    public void set(String m, int n)
    {
        this.personName = m;
        this.personAge = n;
    }
}
