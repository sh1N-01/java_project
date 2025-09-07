package paranaqueRenz_LE1_12;

/*
 * Name: Renz Joseph P. Parañaque
 * Project: Laboratory Exercise 1.12: Multiply the Digits
 * Creation date: September 7, 2025
 * Reads an integer between 0 and 1000. Display and multiplies 
 * all the digits in the integer. Assume that the user follows directions.
 * (Hint: Use the division and remainder operators).
 * 
 * Functions:
 * counTer - counts the number of digits in the integer
 * return - integer count, parameter - string value of the integer
 * userInput - gets the user input
 * return - integer value of user input, no parameters
 * multiplyDigits - multiplies all the digits in the integer
 * return - integer product of all digits, parameters - int origVal, int count
 * displayOutput - displays the output of the program
 * return - void, parameters - int origVal, int product, int count
 * 
*/

import java.util.Scanner;

public class Main 
{
	public static void main(String[] args)
	{
		int origVal, product, count;
		String countVal;
		//User input
		System.out.print("Enter an integer between 0 and 1000: ");
		origVal = userInput();
		countVal = Integer.toString(origVal);
		//Counting the digits
		count = counTer(countVal);
		//Calculation
		product = multiplyDigits(origVal, count);
		//Output
		displayOutput(origVal, product, count);
		System.out.print("\nProgram Executed Succesfully (Exit code 0).");
		
	}
	
	public static int counTer(String tempVal)
	{
		int count = 0;
		for(int i = 0; i < tempVal.length(); i++)
		{
			count++;
		}
		return count;
	}
	
	public static int userInput()
	{
		Scanner input = new Scanner(System.in);
		int amount = input.nextInt();
		return amount;
	}
	
	public static int multiplyDigits(int tempVal, int n)
	{
		int digit, product = 1;
		
		for(int i = 0; i < n; i++)
		{
			digit = tempVal % 10;
			if(digit != 0)
			{
				product *= digit;
				tempVal /= 10;
			}
			
			else
			{
				product = 0;
				return product;
			}
		}
		return product;
	}
	
	public static void displayOutput(int origVal, int product, int count)
	{
		int tempVal = origVal;
		//Printing the digits one by one
		for(int i = 0; i < count; i++)
		{
			int digit = tempVal % 10;
			System.out.println(digit);
			tempVal /= 10;
		}
		//Overall Output
		System.out.println("The product of all the digits in " + origVal + " is " + product);
	}
}
