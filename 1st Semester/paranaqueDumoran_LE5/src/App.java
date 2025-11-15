import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * The {@code Main} class serves as the entry point for the Flow Control Lab Exercises application.
 * It sets up the main stage, scene, and navigation menu to access different exercises (LE31, LE32, LE33).
 */
public class App extends Application {

    /** The fixed window height for the application. */
    public static final int HEIGHT_WINDOW = 650;

    /** The fixed window width for the application. */
    public static final int WIDTH_WINDOW = 600;

    /**
     * Main entry point of the program. Launches the JavaFX application.
     *
     * @param args command-line arguments (unused).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the JavaFX stage, sets up the layout, 
     * navigation buttons, and default styling.
     *
     * @param stage the main application stage.
     */
    @Override
    public void start(Stage stage) {
        // Stage settings
        stage.setTitle("Lab Exercises 5 || Arrays ");
        stage.setWidth(WIDTH_WINDOW);
        stage.setHeight(HEIGHT_WINDOW);
        stage.setResizable(false);

        // Root layout
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH_WINDOW, HEIGHT_WINDOW);
        root.setBackground(new Background(
                new BackgroundFill(Color.web("#1E1E2E"), CornerRadii.EMPTY, Insets.EMPTY)
        ));

        // Title (Top)
        Label title = new Label("Arrays");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
        title.setTextFill(Color.web("#E0E0E0"));
        BorderPane.setAlignment(title, Pos.CENTER);

        VBox topBox = new VBox(5, title, new Line(0, 0, WIDTH_WINDOW, 0));
        topBox.setAlignment(Pos.CENTER);
        root.setTop(topBox);

        // Navigation Buttons (Bottom)
        HBox bottomBox = new HBox(30);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setBackground(new Background(
                new BackgroundFill(Color.web("#2E2E3E"), CornerRadii.EMPTY, Insets.EMPTY)
        ));

        Button btn1 = new Button("LE51");
        Button btn2 = new Button("LE52");
        Button btn3 = new Button("LE53");
        Button btn4 = new Button("Exit");

        // Apply styles
        Button[] buttons = {btn1, btn2, btn3};
        for (Button b : buttons) {
            ButtonStyler.styleNavButton(b);
        }
        ButtonStyler.styleExitButton(btn4);

        // Navigation actions
        btn1.setOnAction(e -> root.setCenter(new LE51View().getContent()));
        btn2.setOnAction(e -> root.setCenter(new LE52View().getContent()));
        btn3.setOnAction(e -> root.setCenter(new LE53View().getContent()));
        btn4.setOnAction(e -> stage.close());

        bottomBox.getChildren().addAll(btn1, btn2, btn3, btn4);
        root.setBottom(bottomBox);

        stage.setScene(scene);
        stage.show();
    }
}
