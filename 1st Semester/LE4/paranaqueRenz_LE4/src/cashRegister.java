/*
 * Class: cashRegister
 * -> This class manages the register's balance and payment processing.
 * 
 * Exclusive Functions:
 * cashRegister() - Constructor for initializing the register with a default balance.
 * @param - none
 * @return - none
 * 
 * cashRegister(int cashCents) - Constructor for initializing the register with a specific balance.
 * @param - int
 * @return - none
 * 
 * currentBalance() - Method for displaying the current register balance in the TextArea.
 * @param - TextArea
 * @return - void
 * 
 * acceptAmount() - Method for processing payments and updating the register balance.
 * @param - int
 * @return - int
 */
import javafx.scene.control.TextArea;
import javax.swing.JOptionPane;

public class cashRegister 
{
    private int cashOnHand;

    public cashRegister()
    {
        this.cashOnHand = 500;
    }

    public cashRegister(int cashCents)
    {
        this.cashOnHand = cashCents;
    }

    public void currentBalance(TextArea display)
    {
        display.appendText("\nCurrent Register Balance: " + this.cashOnHand + " cents");
    }

    public int acceptAmount(int total)
    {
        while (true) 
        {
            try
            {
                String toAdd = JOptionPane.showInputDialog("Pay the total amount of " + total + " cents");
                int amountPaid = Integer.parseInt(toAdd);

                if(amountPaid < total)
                {
                    JOptionPane.showMessageDialog(null, "Hey. You tryna scam me? That's not enough.");
                    continue; 
                }

                else
                {
                    int change = amountPaid - total;

                    if(change > this.cashOnHand)
                    {
                        JOptionPane.showMessageDialog(null, "Insufficient funds in register to provide change. Please contact the store manager.");
                        return -1; 
                    }

                    else
                    {
                        this.cashOnHand = this.cashOnHand + amountPaid - change;

                        JOptionPane.showMessageDialog(null, "You Paid: " + amountPaid + "\nHere's your change: " + change + " cents.");
                        return 1; 
                    }

                }
            }

            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
                continue; 
            }
        }
    }
}

