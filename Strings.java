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
		return (count == 0 || count == 1) ? string+"s" : string;											
	}
}
