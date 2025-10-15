/*
 * Class: MainFrame
 * -> This class is responsible for creating the GUI and initializing the main components.
 * 
 * Exclusive Functions:
 * start() - Method for setting up the GUI stage and initializing components.
 * @param - Stage
 * @return - void
 * 
 * frameSettings() - Method for configuring the scene and stage properties.
 * @param - GridPane, Scene, Stage
 * @return - void
 * 
 * juiProperties() - Method for setting properties for juice buttons.
 * @param - none
 * @return - void
 * 
 * commandButtonProperties() - Method for setting properties for command buttons.
 * @param - none
 * @return - void
 * 
 * Placement() - Method for arranging components in the GridPane.
 * @param - VBox, VBox, VBox, HBox, VBox
 * @return - void
 * 
 * addElements() - Method for adding components to the GridPane.
 * @param - GridPane, VBox, VBox, VBox, HBox, VBox
 * @return - void
 * */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainFrame extends Application
{
    private Button orange = new Button("Orange Juice");
    private Button apple = new Button("Apple Juice");
    private Button mango = new Button("Mango Lassi");
    private Button fruit = new Button("Fruit Punch");

    private Button add = new Button("+");
    private Button sub = new Button("-");
    private Button registerBalance = new Button("Register Balance");
    private Button dispense = new Button("Dispense");
    private Button addToOrder = new Button("Add to Order");
    private Button removeOrder = new Button("Remove an Order");
    private Button itemCounter = new Button("0");

    private TextArea display = new TextArea("Hello World!\nHover over the buttons to see the stock and price of the juice.");

    @Override
    public void start(Stage Stage)
    {
        GridPane root = new GridPane();
        Scene Scene = new Scene(root, 350, 500);
        buttonActions act = new buttonActions();

        VBox juiElement = new VBox(20);
        VBox juiElement2 = new VBox(20);
        VBox commands = new VBox(5);
        VBox commandPanel = new VBox(10);
        HBox addSub = new HBox(5);
        
        frameSettings(root, Scene, Stage);
        Placement(juiElement, juiElement2, commands, addSub, commandPanel);

        juiProperties();
        commandButtonProperties();

        addElements(root, juiElement, juiElement2, commands, addSub, commandPanel);
        act.actionListener(orange, apple, mango, fruit, add, sub, registerBalance, 
                            dispense, itemCounter, display, addToOrder, removeOrder);
    }

    private void frameSettings(GridPane root, Scene Scene, Stage Stage)
    {
        Scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        Stage.setTitle("Fruit Juice Vending Machine");
        Stage.setScene(Scene);

        Stage.setResizable(false);
        Stage.centerOnScreen();
        Stage.show();

        root.setVgap(50);
        root.setHgap(10);
    }

    private void juiProperties()
    {
        orange.setMaxWidth(Double.MAX_VALUE);
        apple.setMaxWidth(Double.MAX_VALUE);
        mango.setMaxWidth(Double.MAX_VALUE);
        fruit.setMaxWidth(Double.MAX_VALUE);

        orange.setWrapText(true);
        apple.setWrapText(true);
        mango.setWrapText(true);
        fruit.setWrapText(true);

        orange.setPrefSize(100, 100);
        apple.setPrefSize(100, 100);
        mango.setPrefSize(100, 100);
        fruit.setPrefSize(100, 100);
    }

    private void commandButtonProperties()
    {
        add.setMaxWidth(Double.MAX_VALUE);
        sub.setMaxWidth(Double.MAX_VALUE);
        registerBalance.setMaxWidth(Double.MAX_VALUE);
        dispense.setMaxWidth(Double.MAX_VALUE);
        removeOrder.setMaxWidth(Double.MAX_VALUE);
        addToOrder.setMaxWidth(Double.MAX_VALUE);

        add.setWrapText(true);
        sub.setWrapText(true);
        registerBalance.setWrapText(true);
        dispense.setWrapText(true);
        removeOrder.setWrapText(true);
        addToOrder.setWrapText(true);

        add.setDisable(true);
        sub.setDisable(true);
        dispense.setDisable(true);
        display.setEditable(false);
        removeOrder.setDisable(true);
        addToOrder.setDisable(true);

        itemCounter.setStyle("-fx-border-color: transparent;");
    }

    private void Placement(VBox jui, VBox jui2, VBox commands, HBox addSub, VBox commandPanel)
    {
        jui.setAlignment(Pos.CENTER);
        jui.getChildren().addAll(orange, mango);

        jui2.setAlignment(Pos.CENTER);
        jui2.getChildren().addAll(apple, fruit);
        
        addSub.setAlignment(Pos.CENTER);
        addSub.getChildren().addAll(add, sub);

        commands.setAlignment(Pos.CENTER);
        commands.getChildren().addAll(registerBalance, addToOrder, removeOrder, dispense);

        commandPanel.setAlignment(Pos.CENTER);
        commandPanel.getChildren().addAll(itemCounter, addSub, commands);
        
        GridPane.setVgrow(display, javafx.scene.layout.Priority.ALWAYS);
        GridPane.setHgrow(display, javafx.scene.layout.Priority.ALWAYS);

        display.setPrefHeight(100); 
        display.setPrefWidth(350); 
        display.setWrapText(true); 

    }

    private void addElements(GridPane root, VBox juiElement, VBox juiElement2, VBox commands, HBox addSub, VBox commandPanel)
    {
        root.add(juiElement, 0, 1);
        root.add(juiElement2, 1, 1);
        root.add(commandPanel, 2, 1);
        root.add(display, 0, 2, 3, 1); 
    }
}