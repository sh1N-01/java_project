import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Apple extends GameObject 
{
    private static Image appleImage;
    private double angle = 0; 

    public Apple(double x, double y, int size, double speed) 
    {
        super(x, y, size, speed);
        
        if (appleImage == null) 
        {
            try 
            {
                appleImage = new Image(getClass().getResourceAsStream("/assets/fruits/apple.png"));
            } 
            
            catch (Exception e) 
            {
                System.out.println("Error: Could not load apple.png");
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
        if (appleImage != null) 
        {
            gc.save();
            gc.translate(x + size / 2, y + size / 2);
            gc.rotate(angle);
            gc.drawImage(appleImage, -size / 2, -size / 2, size, size);
            gc.restore();
        } 

        else 
        {
            gc.setFill(javafx.scene.paint.Color.RED);
            gc.fillOval(x, y, size, size);
        }
    }
}