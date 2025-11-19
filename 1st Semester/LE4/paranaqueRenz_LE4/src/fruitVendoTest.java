import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class fruitVendoTest
{
    @Test
    public void testFruitVendo()
    {
        dispenserType appleDispenser = new dispenserType(75, 100);
        dispenserType orangeDispenser = new dispenserType(50, 150);
        dispenserType mangoDispenser = new dispenserType(10, 20);
        dispenserType fruitPunchDispenser = new dispenserType(50, 50);

        assertFalse(appleDispenser.getCost() == orangeDispenser.getCost());
        assertTrue(mangoDispenser.getNumberOfItems() < orangeDispenser.getNumberOfItems());
        assertEquals(50, fruitPunchDispenser.getCost());

        cashRegister register = new cashRegister(1000);
        assertEquals(1000, register.getCashOnHand());
    }
}