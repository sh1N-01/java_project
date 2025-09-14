/*
 * Name: Renz Joseph P. Parañaque
 * Date Created: September 14, 2025
 * Project: LE2.2: Dec to Hex
 * Create a program that will convert inputted decimal 
 * number into hexadecimal number. (Hint: use successive division 
 * and remainder; string to append character value. 
 * 
 * Functions:
 * main() - Main function
 * return - void, parameters - none
 * integerValidation() - Function to catch invalid inputs
 * return - user input, parameters - none
 * counterLogic() - Function to calculate counter to be used in a loop
 * return - count value, parameters - user input value
 * printOutput() - Function to print decimal to hexadecimal
 * return - void, parameters - array palindrome, integer input and count
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        int input, hex, count, tempVal;
        //User input
        System.out.print("DEC to HEX Converter\n\n");
        System.out.print("Input a whole number: ");
        input = tempVal = integerValidation();
        //Calculation of count to initialize palindrome size
        count = counterLogic(input);
        int[] palindrome = new int[count];
        //Loop for storing values to an array
        for(int i = 0; i < count; i++)
        {
            hex = tempVal % 16;
            palindrome[i] = hex;
            tempVal /= 16;
        }
        //Print output
        printOutput(palindrome, input, count);
        
    }
    //Function for catching invalid inputs
    private static int integerValidation()
    {
        int c;
        @SuppressWarnings("resource")
        Scanner keyboard = new Scanner(System.in);

        try
        {
            c = keyboard.nextInt();
            return c;
        }

        catch(InputMismatchException e)
        {
            System.out.print("Invalid Input! Try again. ");
            return integerValidation();
        }
    }
    //Function to calculate how many length of inputted integer
    private static int counterLogic(int i)
    {
        return (int) (Math.log10(i)/Math.log10(16)) + 1;
    }
    //Function to print values inside an array to hexadecimal values
    private static void printOutput(int[] p, int in, int c)
    {
        System.out.print("\n"+ in + " --> HEX (Base 16) = ");
        for(int i = c - 1; i >= 0; i--)
        {
            //Value format %X = hex Value
            System.out.printf("%X", p[i]);
        }
    }
}
