import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player 
{
    double x, y;
    int width = 60;  
    int height = 60; 
    int speed = 6;

    private Image neutral;
    private Image pLeft, fLeft;  
    private Image pRight, fRight; 
    private int tiltState = 0; 
    private int frameCounter = 0;
    private int tiltDelay = 3; 

    public Player(double startX, double startY) 
    {
        this.x = startX;
        this.y = startY;

        try 
        {
            neutral = new Image(getClass().getResourceAsStream("/assets/basket/basket_neutral.png"));
            pLeft = new Image(getClass().getResourceAsStream("/assets/basket/basket_pleft.png"));
            fLeft = new Image(getClass().getResourceAsStream("/assets/basket/basket_fleft.png"));
            pRight = new Image(getClass().getResourceAsStream("/assets/basket/basket_pright.png"));
            fRight = new Image(getClass().getResourceAsStream("/assets/basket/basket_fright.png"));
        } 
        
        catch (Exception e) 
        {
            System.out.println("Error loading basket images. Check filenames!");
            e.printStackTrace();
        }
    }

    public void moveLeft() 
    {
        if (x > 0) x -= speed;
    }

    public void moveRight(double screenWidth) 
    {
        if (x < screenWidth - width) x += speed;
    }

    public void updateAnimation(boolean isLeftHeld, boolean isRightHeld) 
    {
        int targetTilt = 0; 
        
        if(isLeftHeld) 
        {
            targetTilt = -2; 
        } 
        
        else if(isRightHeld) 
            {
            targetTilt = 2; 
        }

        frameCounter++;
        
        if (frameCounter >= tiltDelay) 
        {
            if(tiltState < targetTilt) 
                {
                tiltState++; 
            } 
            
            else if(tiltState > targetTilt) 
            {
                tiltState--; 
            }
            frameCounter = 0;
        }
    }

    public void draw(GraphicsContext gc) 
    {
        Image imgToDraw = neutral; 

        switch (tiltState) 
        {
            case -2: imgToDraw = fLeft; break;
            case -1: imgToDraw = pLeft; break;
            case 0:  imgToDraw = neutral; break;
            case 1:  imgToDraw = pRight; break;
            case 2:  imgToDraw = fRight; break;
        }

        if (imgToDraw != null) 
        {
            gc.drawImage(imgToDraw, x, y, width, height);
        } 
        
        else 
        {
            gc.fillRect(x, y, width, height);
        }
    }
}