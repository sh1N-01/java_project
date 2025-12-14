import javafx.animation.AnimationTimer;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.text.Text;

public class dropMasterGame
{
    private static final long INVULN_DURATION = 900_000_000L;
    private ArrayList<PopText> pops = new ArrayList<>();

    private AnimationTimer gameLoop;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean isPaused = false;
    private boolean isGameOver = false;
    private int score = 0;
    private int lives = 3;
    private Rectangle hitFlash;
    private long hitFlashEndNanos = 0;
    private long shakeEndNanos = 0;
    private double shakeMagnitude = 6;
    private double shakeX = 0;
    private double shakeY = 0;
    private long invulnerableUntilNanos = 0;

    public dropMasterGame(Stage Stage)
    {
        ArrayList<GameObject> objects = new ArrayList<>();

        Button btnContinue = new Button();
        Button btnRetry = new Button();
        Button btnMenu = new Button();
        Button btnRetry1 = new Button();
        Button btnMenu1 = new Button();
        Font myFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Monocraft.ttf"), 20);
        Label pausedLabel = new Label("PAUSED");
        Player player = new Player(250, 450);

        StackPane gameRoot = new StackPane();
        Canvas canvas = new Canvas(600, 550);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        HBox gameOverBox = new HBox(15);
        HBox buttonsBox = new HBox(15);
        Label gameOverTitle = new Label("GAME OVER");
        Label gameOverScore = new Label();
        VBox pauseMenu = new VBox(10);
        VBox gameOverMenu = new VBox(10);
        Scene gameScene = new Scene(gameRoot, canvas.getWidth(), canvas.getHeight());
        gameScene.setCursor(Cursor.NONE);

        hitFlash = new Rectangle(canvas.getWidth(), canvas.getHeight());
        hitFlash.setMouseTransparent(true);
        hitFlash.setVisible(false);
        hitFlash.setFill(Color.rgb(255, 50, 50, 0.35));

        gameOverMenu.setPrefWidth(canvas.getWidth());
        gameOverMenu.setPrefHeight(canvas.getHeight());
        gameOverMenu.setVisible(false);
        pauseMenu.setVisible(false);
        
        helperClass.pauseUI(pauseMenu, buttonsBox, pausedLabel, btnContinue, btnRetry, btnMenu);
        helperClass.gameOverUI(gameOverMenu, gameOverBox, btnRetry1, btnMenu1, gameOverTitle, gameOverScore);

        gameRoot.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        gameRoot.getStyleClass().add("app-background");
        gameRoot.getChildren().addAll(canvas, hitFlash, pauseMenu, gameOverMenu);

        btnContinue.setOnAction(e ->
        {
            pauseMenu.setVisible(false);
            isPaused = false;
            gameScene.setCursor(Cursor.NONE);
            soundManager.playBGM2();
            gameLoop.start();
            gameScene.getRoot().requestFocus();
        });

        btnRetry.setOnAction(e ->
        {
            helperClass.fadeOUT(gameRoot, () ->
            {
                soundManager.stopBGM2();
                Stage.setScene(null);
                soundManager.stopGameOver();
                new dropMasterGame(Stage);
                soundManager.bgMusic2();
            });
        });

        btnRetry1.setOnAction(e ->
        {
            helperClass.fadeOUT(gameRoot, () ->
            {
                soundManager.stopBGM2();
                Stage.setScene(null);
                soundManager.stopGameOver();
                new dropMasterGame(Stage);
                soundManager.bgMusic2();
            });
        });

        btnMenu.setOnAction(e -> 
        {
            helperClass.returnToMenu(Stage);
            soundManager.stopBGM2();
            soundManager.stopGameOver();
            soundManager.bgMusic1();
        });

        btnMenu1.setOnAction(e -> 
        {
            helperClass.returnToMenu(Stage);
            soundManager.stopBGM2();
            soundManager.stopGameOver();
            soundManager.bgMusic1();
        });

        gameScene.setOnKeyPressed(e ->
        {
            if (e.getCode() == KeyCode.ESCAPE)
            {
                if(isGameOver)
                {
                    return;
                }

                if(!isPaused)
                {
                    isPaused = true;
                    soundManager.pauseBGM2();
                    gameLoop.stop();
                    gameScene.setCursor(Cursor.DEFAULT);
                    pauseMenu.setVisible(true);
                }

                else
                {
                    pauseMenu.setVisible(false);
                    isPaused = false;
                    soundManager.playBGM2();
                    gameScene.setCursor(Cursor.NONE);
                    gameLoop.start();
                }
                return;
            }

            if(!isPaused && !isGameOver)
            {
                if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) leftPressed = true;
                if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) rightPressed = true;
            }
        });

        gameScene.setOnKeyReleased(e ->
        {
            if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) leftPressed = false;
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) rightPressed = false;
        });

        gameLoop = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                if(isPaused || isGameOver)
                {
                    return;
                }

                updateEffects(now, gameRoot);

                if(leftPressed) player.moveLeft();
                if(rightPressed) player.moveRight(canvas.getWidth());
                player.updateAnimation(leftPressed, rightPressed);

                double baseSpawn = 0.015;
                double spawnRamp = Math.min(0.030, score * 0.00012);
                double spawnChance = baseSpawn + spawnRamp;

                if (Math.random() < spawnChance)
                {
                    int size = 24 + (int) (Math.random() * 18);
                    int x = (int) (Math.random() * (canvas.getWidth() - size));

                    double baseSpeed = 1.2;
                    double speedRamp = Math.min(2.2, score * 0.012);
                    double speed = baseSpeed + (Math.random() * 1.0) + speedRamp;

                    double r = Math.random();

                    double bombRate = Math.min(1, .5 + score * 0.0006);
                    if (r < bombRate)
                    {
                        objects.add(new Bomb(x, 0, size, speed));
                    }
                    else if (r < bombRate + 0.30)
                    {
                        objects.add(new Banana(x, 0, size, speed));
                    }
                    else if (r < bombRate + 0.60)
                    {
                        objects.add(new Mango(x, 0, size, speed));
                    }
                    else
                    {
                        objects.add(new Apple(x, 0, size, speed));
                    }
                }

                for (int i = objects.size() - 1; i >= 0; i--)
                {
                    GameObject obj = objects.get(i);
                    obj.update();

                    if (obj.y > canvas.getHeight())
                    {
                        objects.remove(i);
                    }
                }

                int padding = 5;
                boolean isInvulnerable = System.nanoTime() < invulnerableUntilNanos;
                boolean caughtBombThisFrame = false;
                boolean caughtFruitThisFrame = false;
                boolean deadThisFrame = false;

                for (int i = objects.size() - 1; i >= 0; i--)
                {
                    GameObject obj = objects.get(i);

                    boolean caught =
                        obj.x < player.x + player.width + padding &&
                        obj.x + obj.size > player.x - padding &&
                        obj.y < player.y + player.height &&
                        obj.y + obj.size > player.y;

                    if (!caught)
                    {
                        continue;
                    }

                    if (obj instanceof Bomb)
                    {
                        objects.remove(i);

                        if(!isInvulnerable)
                        {
                            if(score > 0) score--;
                            lives--;
                            invulnerableUntilNanos = System.nanoTime() + INVULN_DURATION;

                            pops.add(new PopText(player.x + player.width * 0.40, player.y - 10, "-1", 650_000_000L));
                            triggerBombFeedback();
                            caughtBombThisFrame = true;

                            if(lives <= 0)
                            {
                                deadThisFrame = true;
                            }
                        }
                    }
                    
                    else
                    {
                        score++;
                        objects.remove(i);
                        pops.add(new PopText(player.x + player.width * 0.45, player.y - 10, "+1", 450_000_000L));
                        if(lives != 0)
                        {
                            caughtFruitThisFrame = true;
                        }
                    }
                }

                if(caughtBombThisFrame)
                {
                    soundManager.playDamageSound();
                }

                else if(caughtFruitThisFrame)
                {
                    soundManager.playScoreSound();
                }

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                for (GameObject obj : objects)
                {
                    obj.draw(gc);
                }

                gc.setFont(myFont);
                gc.setFill(Color.WHITE);
                gc.fillText("Score: " + score, 20, 30);

                String labelText = "Lives: ";
                gc.fillText(labelText, 20, 55);

                Text measure = new Text(labelText);
                measure.setFont(myFont);
                double labelWidth = measure.getLayoutBounds().getWidth();

                gc.setFill(Color.RED); 
                String hearts = "♥".repeat(Math.max(0, lives));
                gc.fillText(hearts, 20 + labelWidth, 55);
                gc.setFill(Color.WHITE);

                if(lives == 1)
                {
                    if((System.nanoTime() / 250_000_000L) % 2 == 0)
                    {
                        gc.setFill(Color.RED);
                        gc.fillText("LOW LIFE!", 20, 80);
                        gc.setFill(Color.WHITE);
                    }
                }

                long tNow = System.nanoTime();
                for (int p = pops.size() - 1; p >= 0; p--)
                {
                    PopText pop = pops.get(p);
                    if (tNow > pop.endNanos)
                    {
                        pops.remove(p);
                        continue;
                    }

                    pop.y -= 0.8;
                    if(pop.text.contains("-")) 
                    {
                        gc.setFill(Color.RED);
                    } 
                    
                    else 
                    {
                        gc.setFill(Color.WHITE);
                    }

                    gc.fillText(pop.text, pop.x, pop.y);
                }

                boolean invulnNow = System.nanoTime() < invulnerableUntilNanos;
                if (invulnNow)
                {
                    if ((System.nanoTime() / 120_000_000L) % 2 == 0)
                    {
                        player.draw(gc);
                    }
                }

                else
                {
                    player.draw(gc);
                }

                if(deadThisFrame)
                {
                    soundManager.stopBGM2();
                    soundManager.gameOver();
                    endGame(Stage, gameScene, gameOverMenu, gameOverScore);
                }
            }
        };

        Stage.setScene(gameScene);
        gameLoop.start();
        gameScene.getRoot().requestFocus();
    }

    private void triggerBombFeedback()
    {
        hitFlash.setVisible(true);
        hitFlashEndNanos = System.nanoTime() + 120_000_000L;

        shakeEndNanos = System.nanoTime() + 180_000_000L;
        shakeX = (Math.random() * 2 - 1) * shakeMagnitude;
        shakeY = (Math.random() * 2 - 1) * shakeMagnitude;
    }

    private void updateEffects(long now, StackPane gameRoot)
    {
        if (hitFlashEndNanos != 0 && now > hitFlashEndNanos)
        {
            hitFlash.setVisible(false);
            hitFlashEndNanos = 0;
        }

        if (shakeEndNanos != 0 && now < shakeEndNanos)
        {
            double t = (shakeEndNanos - now) / 180_000_000.0;
            gameRoot.setTranslateX(shakeX * t * (Math.random() > 0.5 ? 1 : -1));
            gameRoot.setTranslateY(shakeY * t * (Math.random() > 0.5 ? 1 : -1));
        }
        else
        {
            gameRoot.setTranslateX(0);
            gameRoot.setTranslateY(0);
            shakeEndNanos = 0;
        }
    }

    private void endGame(Stage Stage, Scene gameScene, VBox gameOverMenu, Label gameOverScore)
    {
        isGameOver = true;
        leftPressed = false;
        rightPressed = false;

        gameLoop.stop();
        gameScene.setCursor(Cursor.DEFAULT);

        int hs = helperScore.loadHighscore();
        if(score > hs)
        {
            helperScore.saveHighscore(score);
        }

        gameOverScore.setText("Score: " + score);
        gameOverMenu.setVisible(true);

        gameScene.getRoot().requestFocus();
    }
}
