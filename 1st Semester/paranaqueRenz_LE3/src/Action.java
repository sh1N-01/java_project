/*
 * Class: Action
 * -> This class is in charge of containing the executed methods being 
 *    listened by the buttons
 * 
 * Exclusive Functions:
 * eventListener() - Method for setting button functionalities on click
 * @param - Button, Button, Button, GridPane, @return - void
 * clearBelowButtons() - Static method for clearing existing elements 
 *                       when clicking one of the 3 main buttons
 * @param - GridPane, @return - void
 * 
 */
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Action 
{
    public Action()
    {

    }

    public void eventListener(Button one, Button two, Button three, GridPane root) 
    {
        one.setOnAction(e -> 
        {
            clearBelowButtons(root);

            Person opOne = new Person();

            opOne.personGUI(root);

        });

        two.setOnAction(e -> 
        {
            Rectangle opTwo = new Rectangle();
            
            opTwo.createRectangle(root);
        });

        three.setOnAction(e -> 
        {
            GradeDistribution opThree = new GradeDistribution();
            
            opThree.gradeUserInput(root);
        });
    }

    public static void clearBelowButtons(GridPane root) 
	{
		root.getChildren().removeIf(node -> 
        {
			Integer row = GridPane.getRowIndex(node);
			return row != null && row > 0;
		});
	}
}
