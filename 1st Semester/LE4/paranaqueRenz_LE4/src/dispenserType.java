/*
 * Class: dispenserType
 * -> This class manages the dispenser's stock and pricing details.
 * 
 * Exclusive Functions:
 * dispenserType() - Constructor for initializing the dispenser with default values.
 * @param - none
 * @return - none
 * 
 * getCost() - Method for retrieving the cost of the item.
 * @param - none
 * @return - int
 * 
 * getNumberOfItems() - Method for retrieving the current stock of the item.
 * @param - none
 * @return - int
 * 
 * updateNumberOfItems() - Method for updating the stock of the item.
 * @param - int
 * @return - void
 * 
 * popDialog() - Method for prompting the user to enter the quantity to remove.
 * @param - String, int
 * @return - int
 */
import javax.swing.JOptionPane;

public class dispenserType
{
    private int cost;
    private int numberOfItems;

    public dispenserType()
    {
        this.cost = 50;
        this.numberOfItems = 50;
    }

    public dispenserType(int setCost, int setNumOfItems)
    {
        this.cost = setCost;
        this.numberOfItems = setNumOfItems;
    }

    public int getNumberOfItems()
    {
        return this.numberOfItems;
    }

    public int getCost()
    {
        return this.cost;
    }

    public void updateNumberOfItems(int num)
    {
        this.numberOfItems -= num;
    }

    public int popDialog(String n, int o)
    {
        try
        {
            String toSubtract = JOptionPane.showInputDialog("Enter quantity of " + n + "(s) to remove: ");
            int quantity = Integer.parseInt(toSubtract);
            
            if(quantity > o)
            {
                JOptionPane.showMessageDialog(null, "Insufficient order quantity. You only have " + o + " " + n + "(s) in your order.");
                return popDialog(n, o);
            }

            else if(quantity < 0)
            {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a non-negative integer.");
                return popDialog(n, o);
            }

            else
            {
                return quantity;
            }
        }
        
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
            return popDialog(n, o);
        }
    }
}
