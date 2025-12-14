import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;

public class soundManager 
{
    private static AudioClip btnClick;
    private static AudioClip damage;
    private static AudioClip score;
    private static AudioClip gameOver;
    private static MediaPlayer bgm1;
    private static MediaPlayer bgm2;
    private static double volume = 0.3;

    public static void playClickSound() 
    {
        try 
        {
            String path = soundManager.class.getResource("/assets/sfx/button_click.wav").toExternalForm();
            btnClick = new AudioClip(path);
            btnClick.play(volume);
        } 
        
        catch (Exception e) { }
    }

    public static void attachClickSound(Button... buttons) 
    {
        for(Button btn : buttons) 
        {
            btn.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> playClickSound());
        }
    }

    public static void gameOver() 
    {
        try 
        {
            String path = soundManager.class.getResource("/assets/sfx/game_over.wav").toExternalForm();
            gameOver = new AudioClip(path);
            gameOver.play(volume - 0.1);
        } 
        
        catch (Exception e) { }
    }

    public static void stopGameOver()
    {
        if(gameOver != null)
        {
            gameOver.stop();
        }
    }

    public static void playScoreSound()
    {
        String path = soundManager.class.getResource("/assets/sfx/point_score.wav").toExternalForm();
        score = new AudioClip(path);
        score.play(volume);
    }

    public static void playDamageSound()
    {
        String path = soundManager.class.getResource("/assets/sfx/damage.wav").toExternalForm();
        damage = new AudioClip(path);
        damage.play(volume);
    }

    public static void bgMusic1()
    {
        try 
        {
            if(bgm1 == null) 
            {
                String path = soundManager.class.getResource("/assets/bgm/bgm1.mp3").toExternalForm();
                Media media = new Media(path);
                bgm1 = new MediaPlayer(media);
                
                bgm1.setCycleCount(MediaPlayer.INDEFINITE); 
            }

            bgm1.play();
        } 

        catch (Exception e) 
        {
            System.out.println("Music file not found or error loading.");
        }
    }

    public static void bgMusic2()
    {
        try 
        {
            if(bgm2 == null) 
            {
                String path = soundManager.class.getResource("/assets/bgm/bgm2.mp3").toExternalForm();
                Media media = new Media(path);
                bgm2 = new MediaPlayer(media);
                
                bgm2.setVolume(volume -.1);
                bgm2.setCycleCount(MediaPlayer.INDEFINITE); 
                
            }
            bgm2.play();
        } 

        catch (Exception e) 
        {
            System.out.println("Music file not found or error loading.");
        }
    }

    public static void stopBGM1()
    {
        if(bgm1 != null) 
        {
            bgm1.stop();
        }
    }

    public static void pauseBGM2()
    {
        if(bgm2 != null) 
        {
            bgm2.pause();
        }
    }

    public static void playBGM2()
    {
        if(bgm2 != null) 
        {
            bgm2.play();
        }
    }

    public static void stopBGM2()
    {
        if(bgm2 != null) 
        {
            bgm2.stop();
        }
    }

    public static void setVolume(double newVolume) 
    {
        volume = Math.max(0, Math.min(1, newVolume)); 
    }
}