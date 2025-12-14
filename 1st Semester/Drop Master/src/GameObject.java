import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject 
{
    double x, y;
    int size;
    double speed;

    public GameObject(double x, double y, int size, double speed) 
    {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
    }

    public void update() 
    {
        y += speed;
        
    }

    public abstract void draw(GraphicsContext gc);
}