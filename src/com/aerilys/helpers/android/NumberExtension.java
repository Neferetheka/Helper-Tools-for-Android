package com.aerilys.helpers.android;

/**
 * A class extension with some utils methods with numbers
 * @author Galaad
 *
 */
public abstract class NumberExtension
{
	/**
	 * Static method to compare two integers. From JDK7 Integer.java
	 */
	public static int compareTo(int x, int y)
	{
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}
	
	/**
	 * Static method to compare two doubles
	 */
	public static int compareTo(double x, double y)
	{
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}
}
