package com.aerilys.helpers.android;
public final class Converter
{
	/*
	* Generic method for parse strings
	*/
	public static int ctI(String toConvert)
	{
		try
		{
			return parseInt(toConvert);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	/* Permit to convert String to int Code from
	 * https://github.com/nasa/World-Wind
	 * -Java/blob/master/WorldWind/src/org/codehaus/jackson/io/NumberInput.java
	 * NASA Code \o/ "Houston, we've got a problem" */
	public final static int parseInt(String chaine)
	{
		char[] digitChars = chaine.toCharArray();
		int offset = 0, len = digitChars.length;
		int num = digitChars[offset] - '0';
		len += offset;
		// This looks ugly, but appears the fastest way:
		if (++offset < len)
		{
			num = (num * 10) + (digitChars[offset] - '0');
			if (++offset < len)
			{
				num = (num * 10) + (digitChars[offset] - '0');
				if (++offset < len)
				{
					num = (num * 10) + (digitChars[offset] - '0');
					if (++offset < len)
					{
						num = (num * 10) + (digitChars[offset] - '0');
						if (++offset < len)
						{
							num = (num * 10) + (digitChars[offset] - '0');
							if (++offset < len)
							{
								num = (num * 10) + (digitChars[offset] - '0');
								if (++offset < len)
								{
									num = (num * 10) + (digitChars[offset] - '0');
									if (++offset < len)
									{
										num = (num * 10) + (digitChars[offset] - '0');
									}
								}
							}
						}
					}
				}
			}
		}
		return num;
	}
	
	/*
	* Permits to convert String to boolean
	*/
	public static boolean convertStringToBool(String textContent)
	{
		if (textContent.equals("true"))
			return true;
		else
			return false;
	}
	
	/*
	*Permits to convert a time in seconds in minutes and seconds, as a String
	*/
	public static String formatSecondsCount(int duration)
	{
		StringBuilder sb = new StringBuilder();
		int minutes = duration/60;
		int seconds = duration % 60;
		sb.append(minutes);
		sb.append("m");
		sb.append(seconds);
		return sb.toString();
	}
}
