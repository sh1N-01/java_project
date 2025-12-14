import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.awt.Taskbar;
import java.awt.Toolkit;

public class helperClass 
{
    public static int flag = 0;
    final static Label highscoreLabel = new Label("HIGHSCORE: 0");

    public static void buttonSettings(Scene menuScene, Button creditsButton, Button exit, Button startButton, Button title)
    {
        menuScene.getStylesheets().add(helperClass.class.getResource("/css/style.css").toExternalForm());
        
        creditsButton.setId("credits-button");
        exit.setId("exit-button");
        highscoreLabel.setId("highscore-label");
        startButton.setId("start-button");
        title.setId("title-button");

        soundManager.attachClickSound(creditsButton, exit, startButton);
    }

    public static void mainMenuSetting(StackPane menu, GridPane m, Stage n, Scene o, VBox p, Button startButton, Button creditsButton, Button exit, Button title)
    {
        int currentHighscore = helperScore.loadHighscore();
        highscoreLabel.setText("HIGHSCORE: " + currentHighscore);

        GridPane hIscore = new GridPane();
        
        try { n.initStyle(StageStyle.UNDECORATED); } catch(Exception e) {}
        
        p.setAlignment(Pos.CENTER);
        p.getChildren().addAll(startButton, creditsButton, exit);

        m.setAlignment(Pos.CENTER);
        m.setVgap(40); 
        m.add(title, 0, 0);
        m.add(p, 0, 1);
        menu.getStyleClass().add("app-background");

        hIscore.setMaxSize(javafx.scene.layout.Region.USE_PREF_SIZE, javafx.scene.layout.Region.USE_PREF_SIZE);
        hIscore.getChildren().add(highscoreLabel);

        menu.getChildren().clear();
        StackPane.setAlignment(hIscore, Pos.BOTTOM_LEFT);
        menu.getChildren().addAll(m, hIscore);

        if(flag == 0)
        {
            menu.setOpacity(0); 
            fadeIN(menu);
        }

        else
        {
            menu.setOpacity(1);
        }

        n.centerOnScreen();
        n.setScene(o);
        n.show();
    }

    public static void returnToMenu(Stage stage)
    {
        new JXFrame().showMenu(stage);
    }

    private static void fadeIN(StackPane m)
    {
        FadeTransition fadeMenu = new FadeTransition();
        m.setMouseTransparent(true); 

        fadeMenu.setNode(m); 
        fadeMenu.setDuration(Duration.seconds(1.5)); 
        fadeMenu.setFromValue(0);
        fadeMenu.setToValue(1);
        
        fadeMenu.setOnFinished(e-> 
        {
            m.setMouseTransparent(false); 
        });
        
        fadeMenu.play();
        flag = 1;
    }

    public static void fadeOUT(StackPane m, Runnable afterFade)
    {
        FadeTransition fadeMenu = new FadeTransition();
        m.setMouseTransparent(true); 

        fadeMenu.setNode(m); 
        fadeMenu.setDuration(Duration.seconds(1)); 
        fadeMenu.setFromValue(1);
        fadeMenu.setToValue(0);
        
        fadeMenu.setOnFinished(e-> 
        {
            afterFade.run();
        });
        
        fadeMenu.play();
    }

    public static void pauseUI(VBox pauseMenu, HBox buttonsBox, Label pausedLabel, Button btnContinue, Button btnRetry, Button btnMenu)
    {
        pauseMenu.getStylesheets().add(helperClass.class.getResource("/css/style.css").toExternalForm());

        pauseMenu.setId("pause-menu");
        buttonsBox.setId("buttons-box");
        pausedLabel.setId("paused-label");
        btnContinue.setId("btn-continue");
        btnRetry.setId("btn-retry");
        btnMenu.setId("btn-menu");

        soundManager.attachClickSound(btnContinue, btnRetry, btnMenu);
        buttonsBox.getChildren().addAll(btnContinue, btnRetry, btnMenu);
        pauseMenu.getChildren().addAll(pausedLabel, buttonsBox);
    }

    public static void gameOverUI(VBox gameOverMenu, HBox gameOverBox, Button btnRetry1, Button btnMenu1, Label gameOverTitle, Label gameOverScore)
    {
        gameOverMenu.getStylesheets().add(helperClass.class.getResource("/css/style.css").toExternalForm());

        gameOverMenu.setAlignment(Pos.CENTER);
        gameOverBox.setAlignment(Pos.CENTER);
        gameOverMenu.setId("gameOver-menu");

        btnRetry1.setId("btn-retry");
        btnMenu1.setId("btn-menu");
        gameOverScore.setId("gameOver-score");
        gameOverTitle.setId("gameOver-title");

        soundManager.attachClickSound(btnRetry1, btnMenu1);
        gameOverBox.getChildren().addAll(btnRetry1, btnMenu1);
        gameOverMenu.getChildren().addAll(gameOverTitle, gameOverScore, gameOverBox);
    }

    public static void creditsUI(StackPane root, Stage stage)
    {
        StackPane creditsBox = new StackPane();
        StackPane.setMargin(creditsBox, new Insets(80)); 
        Label creditsText = new Label("Developed by:\n\nKurt Benedict Elumba\nJames Niño Jacalan\nRenz Joseph Parañaque\n\nCredits to the rightful owners of\nany third-party assets used.");
        creditsBox.setId("credits-box");
        creditsText.setId("credit-label");
        creditsBox.getChildren().add(creditsText);

        Button backBtn = new Button();
        backBtn.setId("back-button"); 

        backBtn.setOnAction(e -> 
        {
            returnToMenu(stage);
        });

        StackPane.setAlignment(backBtn, Pos.TOP_LEFT);
        StackPane.setMargin(backBtn, new Insets(80, 0, 0, 80)); 

        soundManager.attachClickSound(backBtn);
        root.getChildren().clear();
        root.getChildren().addAll(creditsBox, backBtn);
    }

    public static void setIcon(Stage Stage)
    {
        Image appIcon = new Image(helperClass.class.getResourceAsStream("/assets/icon.png"));

        Stage.getIcons().add(appIcon);

        if (Taskbar.isTaskbarSupported()) 
        {
            Taskbar taskbar = Taskbar.getTaskbar();
            if(taskbar.isSupported(Taskbar.Feature.ICON_IMAGE))
            {
                final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
                
                java.awt.Image dockIcon = defaultToolkit.getImage(helperClass.class.getResource("/images/icon.png"));
                taskbar.setIconImage(dockIcon);
            }
        }
    }
}