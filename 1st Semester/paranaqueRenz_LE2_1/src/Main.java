/*
 * Name: Renz Joseph P. Parañaque
 * Date Created: September 12, 2025
 * Project: LE2.11: Day of the Week
 * Zeller’s congruence is an algorithm developed by Christian Zeller to 
 * calculate the day of the week. Note all divisions in this exercise 
 * perform an integer division. Write a program that prompts the user to 
 * enter a year, month, and day of the month, and displays the name of 
 * the day of the week. (Hint: January and February are counted as 13 
 * and 14 in the formula, so you need to convert the user input 1 to 
 * 13 and 2 to 14 for the month and change the year to the previous 
 * year. For example, if the user enters 1 for m and 2015 for year, m 
 * will be 13 and year will be 2014 used in the formula.)
 * 
 * Functions:
 * main() - Main function
 * return - void, parameters - String[] args
 * integerValidation() - Checks if user input is a whole number
 * return - user input, parameters - none
 * has31Days() - Checks if month has 31 days, and if inputted month
 * 				 is February
 * return - -1, 0, 1 (February, false, true), parameters - user input month
 * leapYear() - Checks if year is a leap year
 * return - 0, 1 (false, truu), parameters - user input year
 * switchChoice() - Prompt for inputting day
 * return - day, parameters - has31days, leap
 * dayOfTheWeek() - Calculates the day of the week using Zeller's
 * Congruence Formula
 * return - h (day of the week value), parameters - m, q, 
 * y (month, day, year)
 * printDayOfTheWeek() - Prints the day of the week
 * return - void, parameters - day, m, d, y (day of the week value, 
 * month, day, year)
 * clearScreen() - Clears the Screen
 * return - void, parameters - none
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main 
{
	public static void main(String[] args) 
	{
		//Clears the screen when user wants to repeat
		clearScreen();

		int month, day, year, has31Days, isLeap, weekDay;
		//User prompt for input month with validation
		System.out.print("Enter month (1-12): ");
		while(true) 
		{
			month = integerValidation();
			if(month >= 1 && month <= 12) 
			{
				break;
			} 
			
			else 
			{
				System.out.print("Out of range! Try again: ");
			}
		}
		//Check if inputted month has 31 days
		has31Days = has31Days(month);
		//Ask user to input desired year with validation
		System.out.print("Enter year: ");
		while(true)
		{
			year = integerValidation();
			if(year > 1500)
			{
				break;
			}

			else
			{
				System.out.print
				("Out of range! Try again (Must be greater than 1500): ");
			}
		}
		//Check if year is a leap year
		isLeap = leapYear(year);
		//January and February adjustment 
		if(month == 1 || month == 2)
		{
			month += 12;
			year -= 1;
		}
		//User prompt for inputting desired day
		day = switchChoice(has31Days, isLeap);
		//Perform Zeller's ongruence Calculation
		weekDay = dayOfTheWeek(month, day, year);
		//Output
		printDayOfTheWeek(weekDay, month, day, year);
		
		System.exit(0);
	}
	//Checks if user input is a whole number
	private static int integerValidation()
	{
		try
		{
			int i;
			//I dont wanna put scanner in a try-catch so yeah
			//Suppress the warnings :D
			@SuppressWarnings("resource") 
			Scanner keyboard = new Scanner(System.in);
					
			i = keyboard.nextInt();
			
			return i;
		}
		
		catch(InputMismatchException e)
		{
			System.out.print("Invalid input. Try again: ");
			return integerValidation();
		}
	}
	//Function to check if month has 31 days
	private static int has31Days(int month)
	{
		//Bunch of nested if-else statements hehe
		//-1 = February, 0 = false, 1 = true
		if(month >= 1 && month <= 7)
		{
			if(month % 2 == 0)
			{
				if(month == 2)
				{
					return -1;
				}
				
				return 0;
			}
			
			else
			{
				return 1;
			}
		}
		
		else if(month >= 8 && month <= 12)
		{
			if(month % 2 == 0)
			{
				return 1;
			}
			
			else
			{
				return 0;
			}
		}
		
		return 0;
	}
	//Function to check if year is a leap year
	private static int leapYear(int year)
	{
		//Another nested if-else statements
		//0 = false, 1 = true
		if(year % 4 == 0)
		{
			if(year % 100 == 0)
			{
				if(year % 400 == 0)
				{
					return 1;
				}

				else
				{
					return 0;
				}
			}

			else
			{
				return 1;
			}
		}

		else
		{
			return 0;
		}
	}
	//Function for Day prompt
	private static int switchChoice(int has31Days, int leap)
	{
		int day; 
		switch(has31Days)
		{
			//For February leap year
			case -1:
				if(leap == 1)
				{
					System.out.print("Enter day of the month (1-29): ");
					while(true)
					{
						day = integerValidation();
						if(day >= 1 && day <= 29)
						{
							return day;
						}

						else
						{
							System.out.print("Out of range! Try again: ");
						}
					}
				}
				//February, but not a leap year
				else if(leap == 0)
				{
					System.out.print("Enter day of the month (1-28): ");
					while(true)
					{
						day = integerValidation();
						if(day >= 1 && day <= 28)
						{
							return day;
						}

						else
						{
							System.out.print("Out of range! Try again: ");
						}
					}
				}
			//Months that dont have the 31st day
			case 0:
				System.out.print("Enter day of the month (1-30): ");
				while(true)
				{
					day = integerValidation();
					if(day >= 1 && day <= 30)
					{
						return day;
					}

					else
					{
						System.out.print("Out of range! Try again: ");
					}
				}
			//Months with the 31st day
			case 1:
				System.out.print("Enter day of the month (1-31): ");
				while(true)
				{
					day = integerValidation();
					if(day >= 1 && day <= 31)
					{
						return day;
					}

					else
					{
						System.out.print("Out of range! Try again: ");
					}
				}
		}
		return 0;
	}
	//Function to calculate day of the week using Zeller's congruence
	//Formula
	private static int dayOfTheWeek(int m, int q, int y)
	{
		int J, K, h;
		K = y % 100;
		J = y / 100;

		h = (q + ((26*(m + 1))/10) + K + (K/4) + (J / 4) + (5 * J))% 7;
		
		return h;
	}
	//Function to print output
	private static void printDayOfTheWeek(int day, int m, int d, int y)
	{
		String weekDay[]  = {"Saturday", "Sunday", "Monday", "Tuesday", 
		"Wednesday", "Thursday", "Friday"};
		//Revert January and February adjustments
		if(m > 12) 
		{
			y += 1;
			m -= 12;
		}
		
		System.out.println("\nDate: " + m + "/" + d + "/" + y);
		System.out.print("Day of the week is " + weekDay[day]);
	}
	//Just to clear Screen
	public static void clearScreen() 
	{
	    System.out.print("\033[H\033[2J");
	    System.out.flush();
	}
}