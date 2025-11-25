/*
 * Class: SquareTest
 * -> This class contains unit tests to verify the functionality of the Square class, covering geometry calculations, movement logic, and attribute management.
 * * Exclusive Functions:
 * setUp() - Method that runs before each test to initialize a standard Square object.
 * @param - none
 * @return - void
 * * testGetCenterX() - Method for testing the retrieval of the X-coordinate.
 * @param - none
 * @return - void
 * * testGetCenterY() - Method for testing the retrieval of the Y-coordinate.
 * @param - none
 * @return - void
 * * testGetColor() - Method for testing the retrieval of the shape's color.
 * @param - none
 * @return - void
 * * testMove() - Method for testing the logic that updates the shape's position.
 * @param - none
 * @return - void
 * * testGetSideLength() - Method for testing the retrieval of the side length.
 * @param - none
 * @return - void
 * * testSetSideLength() - Method for testing the update functionality for side length.
 * @param - none
 * @return - void
 * * testGetArea() - Method for verifying the accuracy of the area calculation.
 * @param - none
 * @return - void
 * * testGetPerimeter() - Method for verifying the accuracy of the perimeter calculation.
 * @param - none
 * @return - void
 * * testDrawReturnsRectangle() - Method for verifying that the draw method returns a valid Rectangle object with correct dimensions.
 * @param - none
 * @return - void
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SquareTest 
{
	private Square square;
	
	@BeforeEach
    void setUp() {
        square = new Square(100, 150, "blue", 50);
    }
	
	@Test
    void testGetCenterX() {
        assertEquals(100, square.getCenterX());
    }

    @Test
    void testGetCenterY() {
        assertEquals(150, square.getCenterY());
    }

    @Test
    void testGetColor() {
        assertEquals("blue", square.getColor());
    }

    @Test
    void testMove() {
        square.move(10, -20);
        assertEquals(110, square.getCenterX());
        assertEquals(130, square.getCenterY());
    }

    @Test
    void testGetSideLength() {
        assertEquals(50, square.getSideLength());
    }

    @Test
    void testSetSideLength() {
        square.setSideLength(80);
        assertEquals(80, square.getSideLength());
    }

    @Test
    void testGetArea() {
        assertEquals(2500, square.getArea());
    }

    @Test
    void testGetPerimeter() {
        assertEquals(200, square.getPerimeter());
    }

    @Test
    void testDrawReturnsRectangle() {
        assertNotNull(square.draw());
        assertEquals(50, square.draw().getWidth());
        assertEquals(50, square.draw().getHeight());
    }

}
