package com.aerilys.helpers.android;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Random class extension with some nice utils methods
 */
public abstract class RandomExtension extends Random
{
	private static final long serialVersionUID = 8914228484090011478L;

	public static int sNextInt(int max)
	{
		return (int)(Math.random()*(max));
	}
	
	/*
	* Extension method of nextInt and able to generate a random number between two numbers
	*/
	public static int nextInt(int min, int max)
	{
		return min + (int)(Math.random()*(max - min));
	}
	
	public static double nextDouble(double min, double max)
	{
		return min + (double)(Math.random()*(max - min));
	}
	
	public static String generateRandomString(int length)
	{
		String letters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(alphabet.charAt(sNextInt(letters.length())));
		return sb.toString();
	}
	
	public static <T> T getRandomElementOfList(List<T> list)
	{
		if(list == null || list.isEmpty())
		{
			return null;
		}
		return list.get(nextInt(0, list.size()-1));
	}
	
	public static <T> T getRandomElementOfList(List<T>... lists)
	{
		List<T> mergedList = new ArrayList<T>();
		for(List<T> list : lists)
		{
			mergedList.addAll(list);
		}
		
		if(mergedList.size() == 0)
		{
			return null;
		}
		
		return mergedList.get(nextInt(0, mergedList.size()-1));
	}
	
	public static boolean getRandomBoolean()
	{
		if(nextInt(0, 1) == 1)
		{
			return true;
		}
		
		return false;
	}
}
