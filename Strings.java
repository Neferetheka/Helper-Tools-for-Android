public abstract class Strings
{
	/*
	*	Universal method for detect a null or empty string
	* Better than isEmpty() because it works on older Java system and apply trim (the original method doesn't)
	*/
	public static boolean isNullOrEmpty(String string)
	{
		return string == null || string.trim().length() == 0; 												
	}
}
