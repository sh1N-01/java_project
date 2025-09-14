/*
 * Name: Renz Joseph P. Parañaque
 * Date Created: September 13, 2025
 * Project: LE2.2: Simulation: Heads or Tails.
 * Write a program that simulates flipping a coin 
 * two millions times and displays the number of heads and tails
 * 
 * Functions:
 * main() - Main function
 * return - void, parameters - none
 * start() - Function to confirm program usage
 * return - void, parameters - none
 * randomizer() - Function to simulate coin flip 2 Million times
 * return - void, parameters - h, i, N (int heads, int tails, 
 * final int NUMBER_OF_SIMULATIONS)
 */
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main 
{
    public static void main(String[] args) 
    {
        int heads = 0, tails = 0;
        final int NUMBER_OF_SIMULATIONS = 2000000;

        start();
        randomizer(heads, tails, NUMBER_OF_SIMULATIONS);
        
    }
    //Just some function to confirm usage of program
    private static int start()
    {
        //Supressing warnings hehe
        @SuppressWarnings("unused")
        int c;
        @SuppressWarnings("resource")
        Scanner keyboard = new Scanner(System.in);
        
        try
        {
            System.out.print("Press [1] to start the simulation ");
            c = keyboard.nextInt();

            return 0;
        }

        catch(InputMismatchException e)
        {
            return start();
        }
        
    }
    //Function to simulate coin flip 2 Million times
    private static void randomizer(int h, int t, final int N)
    {
        Random coin = new Random();
        
        System.out.println("\n");
        for(int i = 1; i <= 5; i++)
        {
            System.out.println("Simulation #"+ i);
            for(int j = 1; j <= N; j++)
            {
                if(coin.nextInt(2) == 0)
                {
                    h++;
                }
                
                else
                {
                    t++;
                }
            }
            System.out.println("\nTotal Heads: "+ h);
            System.out.print("Total Tails: "+ t);
            System.out.println("\n\n");
        }
    }
}
