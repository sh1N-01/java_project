import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class eventListener 
{

    public void eventListen(Button a, Button b, Button c, StackPane menu, Stage stage)
    {
        a.setOnAction(e->
        {
            helperClass.fadeOUT(menu, () -> 
            {
                helperClass.flag = 0;
                soundManager.stopBGM1();
                @SuppressWarnings("unused")
                dropMasterGame game = new dropMasterGame(stage);
                soundManager.bgMusic2();
            });
        });

        b.setOnAction(e -> 
        {   
            helperClass.creditsUI(menu, stage);
        });

        c.setOnAction(e->
        {
            helperClass.fadeOUT(menu, () -> 
            {
                System.exit(0);
            });
        }); 
    }
}