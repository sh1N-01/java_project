/*
 * Class: Square
 * -> This class represents a square, inheriting from DrawableShape, and includes logic for calculating geometric properties and rendering the shape.
 * * Exclusive Functions:
 * Square(int, int, String, int) - Constructor for initializing the square with position, color, and side length.
 * @param - int, int, String, int
 * @return - none
 * * getSideLength() - Method for retrieving the length of the square's side.
 * @param - none
 * @return - int
 * * setSideLength() - Method for updating the length of the square's side.
 * @param - int
 * @return - void
 * * getArea() - Method for calculating the area of the square.
 * @param - none
 * @return - int
 * * getPerimeter() - Method for calculating the perimeter of the square.
 * @param - none
 * @return - int
 * * draw() - Method for generating the specific JavaFX Rectangle object for this square.
 * @param - none
 * @return - Rectangle
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends DrawableShape {
	
	private int sideLength;
	
	public Square(int centerX, int centerY, String color, int sideLength) {
		super(centerX, centerY, color);
		this.sideLength = sideLength;
	}
	
	public int getSideLength() { return sideLength;	}
	public void setSideLength(int sideLength) { this.sideLength = sideLength; }
	
	public int getArea() { return sideLength * sideLength; }
	public int getPerimeter() { return 4 * sideLength; }
	
	@Override
	public Rectangle draw() {
		
		Rectangle rect = new Rectangle(getCenterX(), getCenterY(), sideLength, sideLength);
		rect.setFill(Color.web(getColor()));
		rect.setStroke(Color.BLACK);
		return rect;
		
	}

}
