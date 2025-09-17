/*
 * Name: Renz Joseph P. Parañaque
 * Date Created: September 18, 2025
 * Project: LE2: Flow Control (GUI)
 * The following GUI consists of the following: Day of the week,
 * Coin flip simulation, Decimal to hexadecimal
 * 
 * Functions:
 * main() - Main function
 * return - void, parameters - none
 * intValidate() - Function to catch invalid inputs
 * return - user input, parameters - none
 * counterLogic() - Function to calculate counter to be used in a loop
 * return - count value, parameters - user input value
 * printOutput() - Function to print decimal to hexadecimal
 * return - void, parameters - array palindrome, integer input and count
 * has31Days() - Checks if month has 31 days, and if inputted month is February
 * return - -1, 0, 1 (February, false, true), parameters - user input month
 * leapYear() - Checks if year is a leap year
 * return - 0, 1 (false, truu), parameters - user input year
 * zellerCongruence() - Calculates the day of the week using Zeller's
 * Congruence Formula
 * return - h (day of the week value), parameters - m, q, 
 * y (month, day, year)
 */

import javax.swing.*;
import java.util.Random;
import java.awt.event.*;
import java.awt.*;

public class Main 
{
    public static void main(String[] args)
    {
        //Declare array of strings
        String[] week = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        //Create a GUI Frame
        JFrame frame = new JFrame();
        JPanel buttons = new JPanel();
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton calculate = new JButton();
        JButton calculate2 = new JButton();
        //Frame Properties
        frame.setTitle("Laboratory 2: Flow Control");
        frame.setSize(420,120);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        frame.setVisible(true);
        //Add Buttons
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        //Button Properties
        button1.setPreferredSize(new Dimension(120, 40));
        button2.setPreferredSize(new Dimension(120, 40));
        button3.setPreferredSize(new Dimension(120, 40));
        button1.setFont(new Font("Consolas", Font.PLAIN, 11));
        button2.setFont(new Font("Consolas", Font.PLAIN, 11));
        button3.setFont(new Font("Consolas", Font.PLAIN, 11));
        button1.setText("<html><center>Day of the Week</center></html>");
        button2.setText("<html><center>Coin Flip simulation</center></html>");
        button3.setText("<html><center>Decimal to Hex Converter</center></html>");
        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        //Adding Labels, Text fields and Panels
        JLabel outputLabel = new JLabel();
        JLabel label = new JLabel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel errorLabel = new JLabel();
        JTextField textField = new JTextField(13);
        JTextField textField1 = new JTextField(13);
        JTextField textField2 = new JTextField(13);
        JPanel content1 = new JPanel(new GridLayout(0, 2, 10, 15));
        JPanel content2 = new JPanel(new GridLayout(0, 1, 10, 15));
        JPanel content3 = new JPanel(new GridLayout(0, 2, 10, 15));
        JPanel calculatePanel = new JPanel(new GridLayout(0, 2, 10, 15));
        //Label, textfield, and some button properties
        outputLabel.setFont(new Font("Consolas", Font.BOLD, 12));
        outputLabel.setHorizontalAlignment(JLabel.LEFT);
        outputLabel.setPreferredSize(new Dimension(300, 30));

        label.setFont(new Font("Consolas", Font.BOLD, 12));
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setPreferredSize(new Dimension(120, 30));
        textField.setPreferredSize(new Dimension(300, 30));

        label1.setFont(new Font("Consolas", Font.BOLD, 12));
        label1.setHorizontalAlignment(JLabel.LEFT);
        label1.setPreferredSize(new Dimension(120, 30));
        textField1.setPreferredSize(new Dimension(300, 30));

        label2.setFont(new Font("Consolas", Font.BOLD, 12));
        label2.setHorizontalAlignment(JLabel.LEFT);
        label2.setPreferredSize(new Dimension(120, 30));
        textField2.setPreferredSize(new Dimension(300, 30));

        errorLabel.setFont(new Font("Consolas", Font.BOLD, 12));
        errorLabel.setHorizontalAlignment(JLabel.LEFT);
        errorLabel.setPreferredSize(new Dimension(120, 30));
        errorLabel.setForeground(Color.RED);
        
        calculate.setPreferredSize(new Dimension(120, 40));
        calculate.setFont(new Font("Consolas", Font.PLAIN, 12));
        calculate.setFocusable(false);

        calculate2.setPreferredSize(new Dimension(120, 40));
        calculate2.setFont(new Font("Consolas", Font.PLAIN, 12));
        calculate2.setFocusable(false);
        //Add components to frame
        frame.add(buttons);
        frame.add(content1);
        frame.add(content2);
        frame.add(content3);
        frame.add(errorLabel);
        frame.add(outputLabel);
        frame.add(calculatePanel);
        //Action for Zeller's COngruence formula
        button1.addActionListener
        (e -> 
        {   
            content2.removeAll();
            content3.removeAll();
            calculatePanel.removeAll();

            textField.setEditable(true);
            textField1.setEditable(true);
            textField2.setEditable(true);

            frame.setSize(420, 420);
            frame.setLocationRelativeTo(null);

            textField.setText("");
            textField1.setText("");
            textField2.setText("");
            errorLabel.setText("");
            outputLabel.setText("");

            content1.add(label);
            content1.add(textField);
            label.setText("<html>Enter month (1-12)</html>");
            textField.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String monthS = textField.getText(); 
                    int month = intValidate(monthS);
                    if(month < 1 || month > 12)
                    {
                        errorLabel.setText("Invalid Input!");
                        textField.setText("");
                    }

                    else
                    {
                        errorLabel.setText("");
                        textField.setEditable(false);

                        content1.add(label1);
                        content1.add(textField1);
                        label1.setText("<html>Enter year (>1500)</html>");
                        textField1.addActionListener(new ActionListener() 
                        {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                String yearS = textField1.getText();
                                int year = intValidate(yearS);

                                if(year < 1500)
                                {
                                    errorLabel.setText("Invalid Input!");
                                    textField1.setText("");
                                }

                                else
                                {
                                    errorLabel.setText("");
                                    textField1.setEditable(false);

                                    content1.add(label2);
                                    content1.add(textField2);

                                    int threeOne = has31Days(month);
                                    int leap = leapYear(year);
                                    
                                    switch(threeOne)
                                    {
                                        case -1:
                                            if(leap == 1)
                                            {
                                                label2.setText("<html>Enter a day of the month (1-29)</html>");
                                                textField2.addActionListener(new ActionListener() 
                                                {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e)
                                                    {
                                                        String dayS = textField2.getText();
                                                        int day = intValidate(dayS);

                                                        if(day < 1 || day > 29)
                                                        {
                                                            errorLabel.setText("Invalid Input!");
                                                            textField2.setText("");
                                                        }

                                                        else
                                                        {
                                                            errorLabel.setText("");
                                                            textField2.setEditable(false);
                                                            int h = zellerCongruence(day, month, year);
                                                            outputLabel.setText(month + "/"+ day + "/"+ year +" is "+ week[h]);
                                                        }
                                                    }
                                                });
                                            }

                                            else if(leap == 0)
                                            {
                                                label2.setText("<html>Enter a day of the month (1-28)</html>");
                                                textField2.addActionListener(new ActionListener() 
                                                {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e)
                                                    {
                                                        String dayS = textField2.getText();
                                                        int day = intValidate(dayS);

                                                        if(day < 1 || day > 28)
                                                        {
                                                            errorLabel.setText("Invalid Input!");
                                                            textField2.setText("");
                                                        }

                                                        else
                                                        {
                                                            errorLabel.setText("");
                                                            textField2.setEditable(false);
                                                            int h = zellerCongruence(day, month, year);
                                                            outputLabel.setText(month + "/"+ day + "/"+ year +" is "+ week[h]);
                                                        }
                                                    }
                                                });
                                            }
                                            break;

                                        case 0:
                                            label2.setText("<html>Enter a day of the month (1-30)</html>");
                                            textField2.addActionListener(new ActionListener() 
                                                {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e)
                                                    {
                                                        String dayS = textField2.getText();
                                                        int day = intValidate(dayS);

                                                        if(day < 1 || day > 30)
                                                        {
                                                            errorLabel.setText("Invalid Input!");
                                                            textField2.setText("");
                                                        }

                                                        else
                                                        {
                                                            errorLabel.setText("");
                                                            textField2.setEditable(false);
                                                            int h = zellerCongruence(day, month, year);
                                                            outputLabel.setText(month + "/"+ day + "/"+ year +" is "+ week[h]);
                                                        }
                                                    }
                                                });
                                            break;

                                        case 1:
                                            label2.setText("<html>Enter a day of the month (1-31)</html>");
                                            textField2.addActionListener(new ActionListener() 
                                            {
                                                @Override
                                                public void actionPerformed(ActionEvent e)
                                                {
                                                    String dayS = textField2.getText();
                                                    int day = intValidate(dayS);

                                                    if(day < 1 || day > 31)
                                                    {
                                                        errorLabel.setText("Invalid Input!");
                                                        textField2.setText("");
                                                    }

                                                    else
                                                    {
                                                        errorLabel.setText("");
                                                        textField2.setEditable(false);
                                                        int h = zellerCongruence(day, month, year);
                                                        outputLabel.setText(month + "/"+ day + "/"+ year +" is "+ week[h]);
                                                    }
                                                }
                                            });
                                        break;
                                    }
                                }
                            }
                        });
                    }
                }
            });
        });
        //Action for Coin Flip Simulator
        button2.addActionListener
        (e -> 
        {
            Random coin = new Random();
            final int NUMBER_OF_SIMULATIONS = 2000000;

            content1.removeAll();
            content3.removeAll();
            calculatePanel.removeAll();

            textField.setText("");
            textField1.setText("");
            textField2.setText("");
            label.setText("");
            label1.setText("");
            label2.setText("");
            errorLabel.setText("");
            outputLabel.setText("");
            
            frame.setSize(420, 420);
            frame.setLocationRelativeTo(null);
            
            content2.add(label);
            content2.setPreferredSize(new Dimension(400, 40));
            label.setText("Press the Button to Start the Simulation");

            calculatePanel.add(calculate);
            calculate.setText("Start");
            calculate.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    
                    int h = 0, t = 0;

                    for(int i = 0; i < NUMBER_OF_SIMULATIONS; i++)
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
                    content2.add(label1);
                    content2.setPreferredSize(new Dimension(350, 50));
                    label.setText("Total Heads: " + h + ", Total Tails: " + t);
                }
            });
            
        });
        //Action for converting decimal to hexadecimal
        button3.addActionListener
        (e -> 
        {
            content1.removeAll();
            content2.removeAll();
            calculatePanel.removeAll();

            frame.setSize(420, 420);
            frame.setLocationRelativeTo(null);

            textField.setText("");
            textField1.setText("");
            textField2.setText("");
            label.setText("");
            label1.setText("");
            label2.setText("");
            errorLabel.setText("");
            outputLabel.setText("");
            
            content3.add(label);
            content3.add(label1);
            content3.add(textField);
            label.setText("Enter a whole number");

            calculatePanel.add(calculate2);
            calculate2.setText("Calculate");
            calculate2.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String decimal = textField.getText();
                    int input = intValidate(decimal); 
                    int hex, count, tempVal = input;

                    if(input == -1)
                    {
                        errorLabel.setText("Invalid Input!");
                        textField.setText("");
                    }

                    else
                    {
                        errorLabel.setText("");
                        count = counterLogic(input);
                        int[] palindrome = new int[count];

                        for(int i = 0; i < count; i++)
                        {
                            hex = tempVal % 16;
                            palindrome[i] = hex;
                            tempVal /= 16;
                        }

                        label1.setText(input + " --> HEX: ");

                        for(int i = count - 1; i >= 0; i--)
                        {
                            //Value format %X = hex Value
                            label1.setText(label1.getText() + Integer.toHexString(palindrome[i]).toUpperCase());
                        }

                    }
                }
            });

        });
    }
    //Function to check if input is correct
    private static int intValidate(String mo)
    {
        try
        {
            int c = Integer.parseInt(mo);
            return c;
        }
        catch(NumberFormatException e)
        {
            return -1;
        }
    }
    //Function to check if the inputted year is a leap year
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
    //Function to check if inutted month has 31 days
    private static int has31Days(int month)
	{
		//Bunch of nested if-else statements hehe
		//222 = February, 0 = false, 1 = true
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
    //Function that uses zeller's congruence formula to calculate the day of the week
    private static int zellerCongruence(int q, int m, int year)
    {
        int J, K, h, y = year;

        if(m < 3)
        {
            m += 12;
            y -= 1;
        }

        K = y % 100;
		J = y / 100;

		h = (q + ((26*(m + 1))/10) + K + (K/4) + (J / 4) + (5 * J))% 7;

        return h;
    }
     //Function to calculate how many length of inputted integer
    private static int counterLogic(int i)
    {
        return (int) (Math.log10(i)/Math.log10(16)) + 1;
    }
}