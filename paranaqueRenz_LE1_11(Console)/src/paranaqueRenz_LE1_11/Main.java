package paranaqueRenz_LE1_11;
/*
 * Name: Renz Joseph P. Parañaque
 * Project: Laboratory Exercise 1.11: Change Denomination
 * Creation date: September 7, 2025
 * Determines the change to be dispensed from a vending machine. The user enters an amount 
 * between 1 cent and 99 cents. The program determines a combination of coins equal to 
 * that amount Denominations is in a formed of: quarter, dime, nickel and penny.
 * 
 * Functions:
 * userInput() - Handles user input and validates it
 * return - void, parameters - none
 * calculateRemainder() - Calculates the remainder after dispensing a certain denomination.
 * return - tempVal(new value), parameters - tempVal, modulus
 * calculateQuarters() - Calculates the number of quarters to be dispensed.
 * return - Expected quarters value, parameters - tempVal
 * calculateDimes() - Calculates the number of dimes to be dispensed.
 * return - Expected dimes value, parameters - tempVal
 * calculateNickels() - Calculates the number of nickels to be dispensed.
 * return - Expected nickels value, parameters - tempVal
 * printChange() - Prints the change to be dispensed.
 * return - void, parameters - origVal, quarters, dimes, nickels, pennies
 * 
*/

import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		//Initializations
		int origVal, quarters, dimes, nickels, pennies, tempVal;
		//User input
		System.out.print("Input a cent value (1-99): ");
		origVal = tempVal = userInput();
		//Calculations
		quarters = calculateQuarters(tempVal);
		tempVal = calculateRemainder(tempVal, 25);
		dimes = calculateDimes(tempVal);
		tempVal = calculateRemainder(tempVal, 10);
		nickels = calculateNickels(tempVal);
		tempVal = calculateRemainder(tempVal, 5);
		pennies = tempVal;
		//Output
		printChange(origVal, quarters, dimes, nickels, pennies);
		//Exit Successful
		System.out.println("\nProgram Terminated Successfully (Exit Code: 0).");
	}
	
	public static int userInput()
	{
	    int amount = 0;
	    boolean valid = false;
	    Scanner keyboard = new Scanner(System.in);
	    
	    while (!valid) 
	    {
	        try
	        {
	            amount = keyboard.nextInt();
	            if (amount < 1 || amount > 99) 
	            {
	                System.out.print("Please enter an integer between 1 and 99: ");
	                continue; // prompt user again
	            }
	            
	            else
	            {
	            	valid = true;
	            }
	        } 
	        
	        catch (java.util.InputMismatchException e) 
	        {
	            System.out.print("Invalid input. Please enter an integer: ");
	            keyboard.next(); // clear invalid input
	        }
	    }
	    return amount;
	}
	
	public static int calculateRemainder(int tempVal, int modulus)
	{
		tempVal = tempVal % modulus;
		return tempVal;
	}
	
	public static int calculateQuarters(int tempVal) 
	{
		int quarters = tempVal / 25;
		return quarters;
	}
	
	public static int calculateDimes(int tempVal) 
	{
		int dimes = tempVal / 10;
		return dimes;
	}
	
	public static int calculateNickels(int tempVal) 
	{
		int nickels = tempVal / 5;
		return nickels;
	}
	
	public static void printChange(int origVal, int quarters, int dimes, int nickels, int pennies) 
	{
		System.out.println("\nYour change for " + origVal + " cents is:");
		//Conditional outputs
		if(quarters != 0)
		{
			System.out.println(quarters + " quarter(s)");
		}
		
		if(dimes != 0)
		{
			System.out.println(dimes + " dime(s)");
		}
		
		if(nickels != 0)
		{
			System.out.println(nickels + " nickel(s)");
		}
		
		if(pennies != 0)
		{
			System.out.println(pennies + " penny(pennies)");
		}
		
	}
		
}