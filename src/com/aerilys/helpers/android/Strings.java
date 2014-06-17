package com.aerilys.helpers.android;
import java.util.List;
import java.util.Locale;

public final class Strings
{
	/**
	* Universal method to detect a null or empty string
	* Better than isEmpty() because it works on older Java system and apply trim (the original method doesn't)
	*/
	public static boolean isNullOrEmpty(String string)
	{
		return string == null || string.trim().length() == 0; 												
	}
	
	/*
	* Permits to pluralize a String depending on a number
	*/
	public static String pluralize(String string, int count)
	{
		return (count > 1) ? string+"s" : string;											
	}
	
	/**
	 * Clone of the ucfirst function in PHP
	 * Transforms the first letter of the string in uppercase
	 */
	public static String ucfirst(String string)
	{
		return string.substring(0, 1).toUpperCase(Locale.US) + string.substring(1).toLowerCase(Locale.US);
	}
	
	/**
	 * Merges a string array into a single string with the specified separator
	 */
	public static String mergeArray(String[] strings, String separator)
	{
		StringBuilder sb = new StringBuilder();
		for (String string : strings)
		{
			sb.append(string);
			sb.append(separator);
		}
		String result = sb.toString();
		result = result.substring(0, result.length() - 1);
		return result;
	}
	
	/**
	 * Merges a string List into a single string with the specified separator
	 */
	public static String mergeArray(List<String> strings, String separator)
	{
		StringBuilder sb = new StringBuilder();
		for (String string : strings)
		{
			sb.append(string);
			sb.append(separator);
		}
		String result = sb.toString();
		result = result.substring(0, result.length() - 1);
		return result;
	}
	
	/**
	 * Compare the number of similar letters in a row between two strings.
	 * @param firstString : first string to compare
	 * @param secondString : second string to compare
	 * @return : number of similar letters in a row
	 */
	public static int getSimilarityLevel(String firstString, String secondString)
	{
		int max = (firstString.length() > secondString.length()) ? secondString.length() : firstString.length();
		
		int similarities = 0;
		for(int i = 0; i < max; i++) {
			if(firstString.charAt(i) == secondString.charAt(i))
			{
				similarities++;
			}
			else
			{
				return similarities;
			}
		}
		
		return 0;
	}
}
