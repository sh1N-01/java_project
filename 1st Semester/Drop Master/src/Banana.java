import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color; 

public class Banana extends GameObject 
{
    private static Image bananaImage;
    private double angle = 0; 

    public Banana(double x, double y, int size, double speed) 
    {
        super(x, y, size, speed);
        
        if (bananaImage == null) 
        {
            try 
            {
                bananaImage = new Image(getClass().getResourceAsStream("/assets/fruits/banana.png"));
            } 
            
            catch(Exception e) 
            {
                System.out.println("Error: Could not load banana.png");
            }
        }
    }

    @Override
    public void update()
    {
        super.update();
        angle += 2.0; 
    }

    @Override
    public void draw(GraphicsContext gc) 
    {
        if(bananaImage != null) 
        {
            gc.save();
            gc.translate(x + size / 2, y + size / 2);
            gc.rotate(angle);
            gc.drawImage(bananaImage, -size / 2, -size / 2, size, size);
            gc.restore();
        } 

        else 
        {
            gc.setFill(Color.YELLOW);
            gc.fillOval(x, y, size, size);
        }
    }
}