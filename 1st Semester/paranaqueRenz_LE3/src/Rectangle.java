/*
 * Class: Rectangle
 * -> This class is in charge of displaying the elements needed for
 *    Laboratory Exercise 3.1
 * 
 * Exclusive Functions:
 * Rectangle() - class constructor, set instance variables to specific variables
 * @param - dobule, double, integer, @return - void
 * createRectangle()- Mehtod to display elements for Rectangle GUI
 * @param - GridPane, @return - void
 * buttonTwoProperties() - Tweaks button properties
 * @param - none, @return - void
 * display() - Displays output
 * @param - GridPane, StringBuilder, @result - void
 * result() - String builder concatinator
 * @param - StringBuilder, double, double, double, double, double, double, double, double, @return - void
 * propertySetting() - sets element properties to specific values
 * @param - none, @return - void
 * rectGrid1() - Arrange elements inside rectGrid
 * @param - GridPane, @return - void
 * rectGrid2() - Arrange elements inside rectGrid
 * @param - GridPane, @return - void
 * isInputInvalid() - Check for empty input fields
 * @param - none, @return - boolean
 * getHeight() - Get height method, for testing
 * @param - none, @return - boolean
 */
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class Rectangle 
{
    public double width, height;
    private double area, perimeter;

    private Label widtH = new Label("Enter width value");
    private Label heighT = new Label("Enter height value");
    private Label widtH2 = new Label("Enter width value");
    private Label heighT2 = new Label("Enter height value");
    private Label results = new Label();

    private TextField widthField = new TextField();
    private TextField heightField = new TextField();
    private TextField widthField1 = new TextField();
    private TextField heightField1 = new TextField();

    private Button calculate = new Button("Calculate");

    public Rectangle()
    {
        this.width = this.height = 1.0;
    }

    public Rectangle(double m, double n)
    {
        this.width = m;
        this.height = n;
    }


    public void createRectangle(GridPane root)
    {
        Action.clearBelowButtons(root);

        widthField.clear();
        heightField.clear();

        VBox rectangleBox = new VBox(15);
        rectangleBox.setAlignment(Pos.CENTER);

        propertySetting();

        GridPane rectGrid = new GridPane();
        rectGrid1(rectGrid);
        rectGrid2(rectGrid);

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(calculate);

        rectangleBox.getChildren().addAll(rectGrid, buttonBox);

        GridPane.setConstraints(results, 0, 2);

        root.add(rectangleBox, 0, 1, root.getColumnCount(), 1);
        root.getChildren().add(results);

        buttonTwoProperties();

        calculate.setOnAction(e -> 
        {
            try 
            {
                if(isInputInvalid())
                {
                    throw new NumberFormatException();
                }

                double w1 = Double.parseDouble(widthField.getText());
                double w2 = Double.parseDouble(widthField1.getText());
                double h1 = Double.parseDouble(heightField.getText());
                double h2 = Double.parseDouble(heightField1.getText());

                Rectangle r1 = new Rectangle(w1, h1);
                Rectangle r2 = new Rectangle(w2, h2);

                r1.area = r1.width * r1.height;
                r1.perimeter = 2 * (r1.width + r1.height);

                r2.area = r2.width * r2.height;
                r2.perimeter = 2 * (r2.width + r2.height);

                StringBuilder resultBuilder = new StringBuilder();
                result(resultBuilder, r1.area, r1.perimeter, r2.area, r2.perimeter,
                    r1.height, r1.width, r2.height, r2.width);

                display(root, resultBuilder);
            } 
            
            catch(NumberFormatException ex) 
            {
                results.setText("Invalid Input Detected! Try again.");
            } 
        });
    }

    private void buttonTwoProperties()
    {
        calculate.setMaxWidth(Double.MAX_VALUE);
        calculate.setWrapText(true);
    }

    private void result(StringBuilder result, double m, double n, double o, double p, double q, double r, double s, double t)
    {
        result.append("Rectangle #1\n");
        result.append(String.format("Height: %.2f, Width: %.2f\n", q, r));
        result.append(String.format("Area: %.2f, Perimeter: %.2f\n", m, n));
        result.append("\nRectangle #2\n");
        result.append(String.format("Height: %.2f, Width: %.2f\n", s, t));
        result.append(String.format("Area: %.2f, Perimeter: %.2f\n", o, p));
    }

    private void display(GridPane root, StringBuilder resultBuilder)
    {
        if(!root.getChildren().contains(results)) 
        {
            root.getChildren().add(results);
        }
        results.setText(resultBuilder.toString());
    }
    
    private void propertySetting()
    {
        widtH.setPrefWidth(120);
        heighT.setPrefWidth(120);
        widtH2.setPrefWidth(120);
        heighT2.setPrefWidth(120);
        widthField.setPrefWidth(100);
        heightField.setPrefWidth(100);
        widthField1.setPrefWidth(100);
        heightField1.setPrefWidth(100);
    }

    private void rectGrid1(GridPane rectGrid)
    {
        rectGrid.setHgap(15);
        rectGrid.setVgap(10);

        rectGrid.add(widtH, 0, 0);
        rectGrid.add(widthField, 1, 0);
        rectGrid.add(heighT, 0, 1);
        rectGrid.add(heightField, 1, 1);
    }

    private void rectGrid2(GridPane rectGrid)
    {
        rectGrid.add(widtH2, 3, 0);
        rectGrid.add(widthField1, 4, 0);
        rectGrid.add(heighT2, 3, 1);
        rectGrid.add(heightField1, 4, 1);
    }

    private boolean isInputInvalid() 
    {
        return widthField.getText().trim().isEmpty() || heightField.getText().trim().isEmpty() ||
           widthField1.getText().trim().isEmpty() || heightField1.getText().trim().isEmpty();
    }

    public double getHeight()
    {
        return this.height;
    }
}
