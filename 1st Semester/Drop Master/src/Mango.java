import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color; 

public class Mango extends GameObject 
{
    private static Image mangoImage;
    private double angle = 0; 

    public Mango(double x, double y, int size, double speed) 
    {
        super(x, y, size, speed);
        
        if (mangoImage == null) 
        {
            try 
            {
                mangoImage = new Image(getClass().getResourceAsStream("/assets/fruits/mango.png"));
            } 
            
            catch(Exception e) 
            {
                System.out.println("Error: Could not load mango.png");
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
        if(mangoImage != null) 
        {
            gc.save();
            gc.translate(x + size / 2, y + size / 2);
            gc.rotate(angle);
            gc.drawImage(mangoImage, -size / 2, -size / 2, size, size);
            gc.restore();
        } 

        else 
        {
            gc.setFill(Color.RED);
            gc.fillOval(x, y, size, size);
        }
    }
}