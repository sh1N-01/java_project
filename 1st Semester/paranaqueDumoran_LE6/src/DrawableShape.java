/*
 * Class: DrawableShape
 * -> This abstract class serves as a base for shapes, managing common attributes like position and color.
 * * Exclusive Functions:
 * DrawableShape(int, int, String) - Constructor for initializing the shape with specific coordinates and color.
 * @param - int, int, String
 * @return - none
 * * getCenterX() - Method for retrieving the X-coordinate of the shape's center.
 * @param - none
 * @return - int
 * * getCenterY() - Method for retrieving the Y-coordinate of the shape's center.
 * @param - none
 * @return - int
 * * getColor() - Method for retrieving the color of the shape.
 * @param - none
 * @return - String
 * * move() - Method for updating the shape's position by adding offsets to the coordinates.
 * @param - int, int
 * @return - void
 * * draw() - Abstract method that must be implemented by subclasses to return the specific JavaFX Shape.
 * @param - none
 * @return - Shape
 */
import javafx.scene.shape.Shape;

public abstract class DrawableShape {
	
	private int centerX;
	private int centerY;
	private String color;
	
	public DrawableShape(int centerX, int centerY, String color) {
		
		this.centerX = centerX;
		this.centerY = centerY;
		this.color = color;
	
	}
	
	// Accessors
	
	public int getCenterX() { return centerX; }
	public int getCenterY() { return centerY; }
	public String getColor() { return color; }
	
	// Mutators: move shape
	public void move (int dx, int dy) {
		centerX += dx;
		centerY += dy;
	}
	
	// Abstract method: must be implemented by subclasses
	public abstract Shape draw();

}
