/*
 * Class: Grade Distribution
 * -> This class is displays the percentage of total grades inputted as
 *    asterisks and drawn as a bar graph
 * 
 * Exclusive Functions:
 * GradeDistribution() - class constructor
 * @param - none, double, integer, @return - void
 * gradeUserInput() - GradeDistribution GUI and element display
 * @param - GridPane, @return - void
 * setConstraints() - Sets GridPane constraints of an element
 * @param - GridPane, @return - void
 * textFieldProperties() - TesxtField settings
 * @param - none, @reutnr - void
 * percentage() - calculates percent input equivalence and store in an array
 * @param - none, @return - void
 * clearer() - clears elements on enter
 * @param - none, @return - void
 * builder() - String builder for results
 * @param - StringBuilder, GridPane, @return - void
 * barGraph() - Builds a bar graph
 * @param - none, @return - void
 * newStage() - Creates a new window dedicated for the bar graph
 * @param - BarChart<Number, String>, @return - void
 */
import javafx.scene.layout.GridPane;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GradeDistribution 
{
    private float totalGrade;
    private float[] grades = new float[5];
    public int[] asterisks = new int[5];

    private Label aLabel = new Label("Enter A Grade");
    private Label bLabel = new Label("Enter B Grade");
    private Label cLabel = new Label("Enter C Grade");
    private Label dLabel = new Label("Enter D Grade");
    private Label fLabel = new Label("Enter F Grade");
    private Label result = new Label();
    private Label scaleLabel = new Label("0    10    20    30    40    50    60    70    80    90    100");

    private TextField aField = new TextField();
    private TextField bField = new TextField();
    private TextField cField = new TextField();
    private TextField dField = new TextField();
    private TextField fField = new TextField();

    private Button showResult = new Button("Calculate");
    private Stage chartStage = null;

    public GradeDistribution(int m, int n, int o, int p, int q) 
    {
        int[] val = {m, n, o, p, q};

        for(int i = 0; i < 5; i++)
        {
            this.asterisks[i] = val[i];
        }
    }

    public GradeDistribution()
    {

    }

    public void gradeUserInput(GridPane root)
    {
        Action.clearBelowButtons(root);  

        clearer();

        setConstraints(root);
        textFieldProperties();
        root.getChildren().addAll(aLabel, aField, bLabel, bField, cLabel,
        cField, dLabel, dField, fLabel, fField, showResult, result);
        
        showResult.setOnAction(e -> 
        {
            try
            {
                this.grades[0] = aField.getText().trim().isEmpty() ? 0 : Integer.parseInt(aField.getText().trim());
                this.grades[1] = bField.getText().trim().isEmpty() ? 0 : Integer.parseInt(bField.getText().trim());
                this.grades[2] = cField.getText().trim().isEmpty() ? 0 : Integer.parseInt(cField.getText().trim());
                this.grades[3] = dField.getText().trim().isEmpty() ? 0 : Integer.parseInt(dField.getText().trim());
                this.grades[4] = fField.getText().trim().isEmpty() ? 0 : Integer.parseInt(fField.getText().trim());


                GridPane.setColumnIndex(result, 2);
                GridPane.setRowIndex(result, 1);
                GridPane.setRowSpan(result, 5);

                result.setWrapText(true);
                result.setPrefWidth(200);

                GridPane.setConstraints(scaleLabel, 2, 1);
                if(!root.getChildren().contains(scaleLabel)) 
                {
                    root.getChildren().add(scaleLabel);
                }
                
                this.totalGrade = 0;
                for(int i = 0; i < 5; i++)
                {
                    this.totalGrade += this.grades[i];
                }
                percentage();

                StringBuilder graph = new StringBuilder();
                builder(graph, root);

                barGraph();
            }

            catch(NumberFormatException ex)
            {
                root.getChildren().remove(scaleLabel);
                if(chartStage != null)
                {
                    chartStage.close();
                }
                result.setText("Invalid Input detected! Try again.");
            }
        });
    }

    private void setConstraints(GridPane root)
    {
        GridPane.setConstraints(aLabel, 0, 1);
        GridPane.setConstraints(aField, 1, 1);
        GridPane.setConstraints(bLabel, 0, 2);
        GridPane.setConstraints(bField, 1, 2);
        GridPane.setConstraints(cLabel, 0, 3);
        GridPane.setConstraints(cField, 1, 3);
        GridPane.setConstraints(dLabel, 0, 4);
        GridPane.setConstraints(dField, 1, 4);
        GridPane.setConstraints(fLabel, 0, 5);
        GridPane.setConstraints(fField, 1, 5);
        GridPane.setConstraints(showResult, 0, 6);
        GridPane.setConstraints(result, 2, 1);
    }

    private void textFieldProperties()
    {
        aField.setPrefHeight(25);
        aField.setPrefWidth(40);
        bField.setPrefHeight(25);
        bField.setPrefWidth(40);
        cField.setPrefHeight(25);
        cField.setPrefWidth(40);
        dField.setPrefHeight(25);
        dField.setPrefWidth(40);
        fField.setPrefHeight(25);
        fField.setPrefWidth(40);
    }

    private void percentage()
    {
        for(int i = 0; i < 5; i++)
        {
            double a = this.grades[i] / this.totalGrade;
            this.asterisks[i] = (int) Math.round(a * 50);
        }
    }

    private void clearer()
    {
        aField.clear();
        bField.clear();
        cField.clear();
        dField.clear();
        fField.clear();
    }

    private void builder(StringBuilder r, GridPane root)
    {
        char[] gradeLabels = {'A', 'B', 'C', 'D', 'F'};

        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < asterisks[i]; j++)
            {
                r.append("*");
            }
            r.append(String.format("%c (%d%%)", gradeLabels[i], (Math.round((grades[i] / totalGrade) * 100)))).append("\n");
        }
        r.append("\nTotal Grades: " + this.totalGrade);

        if (!root.getChildren().contains(result)) 
        {
            root.getChildren().add(result);
        }
        result.setText(r.toString());
    }

    private void barGraph()
    {
        NumberAxis xAxis = new NumberAxis();
        CategoryAxis yAxis = new CategoryAxis();
        xAxis.setLabel("Percentage");
        yAxis.setLabel("Grade");

        BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Grade Distribution");
        barChart.setLegendVisible(false);
        barChart.setCategoryGap(15);
        barChart.setBarGap(5);

        barChart.setPrefSize(300, 300);

        char[] gradeLabels = {'A', 'B', 'C', 'D', 'F'};
        XYChart.Series<Number, String> dataSeries = new XYChart.Series<>();
        for (int i = 4; i >= 0; i--) 
        {
            dataSeries.getData().add(new XYChart.Data<>(grades[i], String.valueOf(gradeLabels[i])));
        }

        barChart.getData().clear();
        barChart.getData().add(dataSeries);
        barChart.setStyle("CHART_COLOR_1: black;");
        newStage(barChart);
    }

    private void newStage(BarChart<Number, String> barChart)
    {
        if(chartStage != null) 
        {
            chartStage.close();
        }

        chartStage = new Stage();
        Scene chartScene = new Scene(barChart, 300, 300);
        chartStage.setTitle("Grade Distribution Chart");
        chartStage.setScene(chartScene);
        chartStage.show();
        chartStage.setOnCloseRequest(ev -> chartStage = null);
    }
}