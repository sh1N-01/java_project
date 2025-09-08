package application;

public class Operations 
{
	//Change Denomination Operations
	public static int changeDenomination(int cents, int divisor) 
	{
		return cents / divisor;
	}
	
	public static double bmrMan(double weight, double height, int age) 
	{
		return 66 + (6.3 * weight) + (12.9 * height) - (6.8 * age);
	}
	
	public static double bmrWoman(double weight, double height, int age) 
	{
		return 655 + (4.3 * weight) + (4.7 * height) - (4.7 * age);
	}
	
	public static double chocolateBars(double bmr) 
	{
		return bmr / 230;
	}
	
	public static double distanceFormula(double m, double n, double o, double p)
	{
		return Math.sqrt(Math.pow((o - m), 2) + Math.pow((p - n), 2));
	}
}
