package paranaqueRenz_LE1_14;
/*
 * Name: Renz Joseph P. Parañaque
 * Project: Laboratory Exercise 1.14: Distance between Two Points
 * Creation date: September 7, 2025
 * Prompts the user to enter two points (x1, y1) and (x2, y2) and displays
 * their distance. The formula for computing the distance is √(𝑥2 − 𝑥1)2 + (𝑦2 − 𝑦1)2. 
 * Note you can use Math.pow(a, 0.5) to compute square-root.
 * 
 * Functions:
 * tryCatchDouble() - handles invalid input when user is prompted to enter a number
 * return - a valid double number, parameters - none
 * pop1, pow2 - stores the values of (x2 - x1)^2 and (y2 - y1)^2 respectively
 * printDistance() - prints the distance between two points
 * return - void, parameters - distance (double)
*/

import java.util.Scanner;

public class Main 
{
	public static void main(String[] args)
	{
		//Initialization of variables
		double distance, pow1, pow2, x1, y1, x2, y2;
		//User input
		System.out.print("Enter point 1 (x1, y1): \n");
		x1 = tryCatchDouble();
		y1 = tryCatchDouble();
		System.out.print("Enter point 2 (x2, y2): \n");
		x2 = tryCatchDouble();
		y2 = tryCatchDouble();
		//Calculation
		pow1 = Math.pow((x2 - x1), 2);
		pow2 = Math.pow((y2 - y1), 2);
		distance = Math.pow(pow1 + pow2, 0.5);
		//Output
		printDistance(distance);
		System.out.println("\nProgram Terminated Successfully (Exit Code: 0).");
		
	}
	//Method to handle invalid input
	public static double tryCatchDouble()
	{
		double n;
		Scanner keyboard = new Scanner(System.in);
		try 
		{
			n = keyboard.nextDouble();
			return n;
		}
		catch (Exception e)
		{
			System.out.print("Invalid input. Please enter a number: ");
			return tryCatchDouble();
		}
	}
	
	public static void printDistance(double distance) 
	{
		System.out.printf("The distance between the two points is: %.2f\n", distance);
	}
 }
