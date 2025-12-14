import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bomb extends GameObject
{
    private static Image bombImage;
    private double angle = 0; 

    static
    {
        try
        {
            bombImage = new Image(Bomb.class.getResourceAsStream("/assets/bomb.png"));
        }
        catch (Exception e)
        {
            bombImage = null;
        }
    }

    public Bomb(double x, double y, int size, double speed)
    {
        super(x, y, size, speed);
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
        if (bombImage != null && !bombImage.isError())
        {
            gc.save();
            gc.translate(x + size / 2, y + size / 2);
            gc.rotate(angle);
            gc.drawImage(bombImage, -size / 2, -size / 2, size, size);
            gc.restore();
        }
        else
        {
            gc.setFill(Color.BLACK);
            gc.fillOval(x, y, size, size);
            gc.setFill(Color.WHITE);
            gc.fillText("B", x + size * 0.35, y + size * 0.65);
        }
    }
}
