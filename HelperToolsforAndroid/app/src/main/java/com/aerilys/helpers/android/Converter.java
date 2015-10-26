package com.aerilys.helpers.android;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class Converter
{
	/* Generic method to parse strings */
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

	/* Allows to convert String to int Code from
	 * https://github.com/nasa/World-Wind
	 * -Java/blob/master/WorldWind/src/org/codehaus/jackson/io/NumberInput.java */
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

	/* Allows to convert String to boolean */
	public static boolean convertStringToBool(String textContent)
	{
		if (textContent.equals("true"))
			return true;
		else
			return false;
	}

	/*Allows to convert a time in seconds in minutes and seconds, as a String */
	public static String formatSecondsCount(int duration)
	{
		StringBuilder sb = new StringBuilder();
		int minutes = duration / 60;
		int seconds = duration % 60;
		sb.append(String.format("%02d", minutes));
		sb.append("m");
		sb.append(String.format("%02d", seconds));
		return sb.toString();
	}

	public static byte[] serialize(Object obj) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}
}
