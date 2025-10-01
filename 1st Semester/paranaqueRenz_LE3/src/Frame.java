/*
 * Class: Frame
 * -> This class is in charge of creating the GUI and displaying the
 *    main buttons. 
 * 
 * Exclusive Functions:
 * start() - Method for making the GUI stage.
 * @param - Stage, @return - void
 * rootAlignment() - Method in charge of setting proper GridPane alignment
 * @param - GridPane, @return - void
 * buttonProperties() - Method for tweaking button properties
 * @param - GridPane, @return - void
 * addButtons() - Method in charge of displaying the buttons on the GUI
 * @param - Button, Button, Button, GridPane, @return - void
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Frame extends Application
{
    private Button button1 = new Button("LE 3.1 - Person");
    private Button button2 = new Button("LE 3.2 - Rectangle");
    private Button button3 = new Button("LE 3.3 - Grade Distribution");

    @Override
    public void start(Stage Stage)
    {
        GridPane root = new GridPane();
        Action listener = new Action();
        Scene Scene = new Scene(root, 600, 300);

        Scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        Stage.setTitle("Lab Exercise #3");
        Stage.setScene(Scene);

        Stage.setResizable(false);
        Stage.centerOnScreen();
        Stage.show();

        rootAlignment(root);
        buttonProperties(root);

        listener.eventListener(button1, button2, button3, root);
    }

    private void rootAlignment(GridPane root)
    {
        root.setAlignment(Pos.TOP_CENTER);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(20, 20, 20, 20));
    }

    private void buttonProperties(GridPane root)
    {
        button1.setMaxWidth(Double.MAX_VALUE);
		button2.setMaxWidth(Double.MAX_VALUE);
		button3.setMaxWidth(Double.MAX_VALUE);
		button1.setWrapText(true);
		button2.setWrapText(true);
		button3.setWrapText(true);

        addButtons(button1, button2, button3, root);
    }

    private void addButtons(Button one, Button two, Button three, GridPane root)
    {
        root.add(one, 0, 0);
		root.add(two, 1, 0);
		root.add(three, 2, 0);
    }
}
