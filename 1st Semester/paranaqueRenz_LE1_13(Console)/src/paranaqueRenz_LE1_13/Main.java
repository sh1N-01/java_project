package paranaqueRenz_LE1_13;
/*
 * Name: Renz Joseph P. Parañaque
 * Project: Laboratory Exercise 1.13: Chocolate Bars
 * Creation date: September 7, 2025
 * The Harris-Benedict equation estimates the number of calories your body needs to 
 * maintain your weight if you do no exercise. This is called your basal 
 * metabolic rate, or BMR.
 * The calories needed for a woman to maintain her weight is:
 * BMR = 655 + (4.3 x weight in pounds) + (4.7 x height in inches) – (4.7 x age in years)
 * The calories needed for a man to maintain his weight is:
 * BMR = 66 + (6.3 x weight in pounds) + (12.9 x height in inches) – (6.8 x age in years)
 * 
 * 
 * A typical chocolate bar will contain around 230 calories. Write a program that allows 
 * the user to input their weight in pounds, height in inches, and age in years. 
 * The program should then output the number of chocolate bars that should be consumed 
 * to maintain one’s weight for both a woman and a man of the input weight, height, and age.

 * Functions:	
 * tryCatchInt() - handles invalid input for integer values
 * return - integer value, parameter - none
 * calculateBMRWoman(int weight, int height, int age) - calculates BMR for Woman
 * return - double value, parameters - weight, height, age
 * calculateBMRMan(int weight, int height, int age) - calculates BMR for Man
 * return - double value, parameters - weight, height, age
 * calculateChocolateBars(double bmr) - calculates number of chocolate bars needed
 * return - double value, parameter - bmr(both woman and man)
 * displayResults(double bmrMan, double bmrWoman, double chocolateBarsWoman, double chocolateBarsMan) - displays results
 * return - void, parameters - bmrMan, bmrWoman, chocolateBarsWoman, chocolateBarsMan
 * 
*/

import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		//Initialization
		double bmrWoman, bmrMan, chocolateBarsWoman, chocolateBarsMan;
		int age, weight, height;
		//User inputs
		System.out.print("Enter weight in pounds: ");
		weight = tryCatchInt();
		System.out.print("Enter height in inches: ");
		height = tryCatchInt();
		System.out.print("Enter age in years: ");
		age = tryCatchInt();
		//Calculations
		bmrWoman = calculateBMRWoman(weight, height, age);
		bmrMan = calculateBMRMan(weight, height, age);
		chocolateBarsWoman = calculateChocolateBars(bmrWoman);
		chocolateBarsMan = calculateChocolateBars(bmrMan);
		//Outputs
		displayResults(bmrMan, bmrWoman, chocolateBarsWoman, chocolateBarsMan);
		//Exit Successful
		System.out.println("\nProgram Terminated Successfully (Exit Code: 0).");
	}
	//Error handling for integer inputs
	public static int tryCatchInt()
	{
		int temp;
		Scanner keyboard = new Scanner(System.in);
		
		try
		{
			temp = keyboard.nextInt();
			return temp;
		}
		
		catch(Exception e)
		{
			System.out.print("Invalid Input. Please enter an integer value: ");
			return tryCatchInt();
		}
	}
	
	public static double calculateBMRWoman(int  weight, int height, int age) 
	{
		return 655 + (4.3 * weight) + (4.7 * height) - (4.7 * age);
	}
	
	public static double calculateBMRMan(int weight, int height, int age) 
	{
		return 66 + (6.3 * weight) + (12.9 * height) - (6.8 * age);
	}
	
	public static double calculateChocolateBars(double bmr) 
	{
		return bmr / 230;
	}
	
	public static void displayResults(double bmrMan, double bmrWoman, double chocolateBarsWoman, double chocolateBarsMan) 
	{
		System.out.printf("\nBMR for Man: %.2f%n", bmrMan);
		System.out.printf("Chocolate bars needed: %.2f%n", chocolateBarsMan);
		System.out.printf("\nBMR for Woman: %.2f%n", bmrWoman);
		System.out.printf("Chocolate bars needed: %.2f%n", chocolateBarsWoman);
	}
}
