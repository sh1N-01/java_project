/*
 * Class: Testing
 * -> This class is only for testing the functionality of the methods
 * 
 * Exclusive Functions:
 * initToolkit() - Method to run before all test methods are executed
 * @param - none, @return - void
 * testNames() - test method to test class Person
 * @param - none, @return - void
 * testRectangle() - Test method to test class Rectangle
 * @param - none, @return - void
 * testGradeDistribution() - Text method to test class GradeDistribution
 * @param - none, @return - void
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import javafx.embed.swing.JFXPanel;

public class Testing
{
    @BeforeClass
    public static void initToolkit() 
    {
        new JFXPanel();
    }

    @Test
    public void testNames() 
    {
        Person p1 = new Person("Augusta", 10);
        Person p2 = new Person("augusta", 10);
        //Expect True
        assertTrue(p1.getName().equalsIgnoreCase(p2.getName()));
        assertTrue(p1.getAge() == p2.getAge());
        
        p1.set("SHaunnnnnnnnnnnnnnn", 19);
        p2.set("rabbit", 100);
        //Expect Error
        assertTrue(p1.getName().equalsIgnoreCase(p2.getName()));
        assertTrue(p1.getAge() == p2.getAge());
    }

    @Test
    public void testRectangle()
    {
        Rectangle r1 = new Rectangle(4, 40);
        Rectangle r2 = new Rectangle(3.5, 35.9);
        //Expect False
        assertTrue(r1 == r2);

        Rectangle r3 = new Rectangle();
        Rectangle r4 = new Rectangle();
        //Expect True
        assertTrue(r3.getHeight() == r4.getHeight());
        r3 = r4;
        assertEquals(r3, r4);;

    }

    @Test
    public void testGradeDistribution()
    {
        GradeDistribution g1 = new GradeDistribution(1,2,3,4,5);
        GradeDistribution g2 = new GradeDistribution(0,9,8,2,1);

        int total1 = 0;
        int total2 = 0;
        for(int i = 0; i < 5; i++)
        {
            total1 += g1.asterisks[i];
            total2 += g2.asterisks[i];
        }
        //Expect False
        assertTrue(total1 == total2);
        //Expect True
        g1 = g2;
        assertTrue(g1 == g2);
    }
}
