import java.util.List;

public abstract class Strings
{
	/*
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
	
	/*
	 * Clone of the ucfirst function in PHP
	 * Transforms the first letter of the string in uppercase
	 */
	public static String ucfirst(String string)
	{
		return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
	}
	
	/*
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
		result = result.substring(0, result.length() - 2);
		return result;
	}
	
	/*
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
		result = result.substring(0, result.length() - 2);
		return result;
	}
}
