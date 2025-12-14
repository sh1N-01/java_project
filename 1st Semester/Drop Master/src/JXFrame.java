import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane; 
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JXFrame extends Application
{
    private Button creditsButton = new Button();
    private Button exit = new Button();
    private Button startButton = new Button();
    private Button title = new Button();

    @Override
    public void start(Stage Stage) 
    {
        showMenu(Stage);
    }
    
    public void showMenu(Stage Stage)
    {
        eventListener event = new eventListener();
        GridPane menuGrid = new GridPane(); 
        StackPane menu = new StackPane(); 
        Scene menuScene = new Scene(menu, 600, 550);
        VBox menuBox = new VBox(10);
        
        helperClass.setIcon(Stage);
        helperClass.buttonSettings(menuScene, creditsButton, exit, startButton, title);
        helperClass.mainMenuSetting(menu, menuGrid, Stage, menuScene, menuBox, startButton, creditsButton, exit, title);
        event.eventListen(startButton, creditsButton, exit, menu, Stage);
        soundManager.bgMusic1();
    }
}