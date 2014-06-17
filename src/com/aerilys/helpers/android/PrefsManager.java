package com.aerilys.helpers.android;

import android.content.Context;
import android.content.SharedPreferences;

public final class PrefsManager
{
	public static boolean sIsInit = false;

	private static SharedPreferences sPrefs;


	public static void init(Context context)
	{
		sPrefs = context.getSharedPreferences("appPrefs", Context.MODE_PRIVATE);

		sIsInit = true;
	}
	
	public static String getString(String key)
	{
		return getString(key, null);
	}

	public static String getString(String key, String defaultValue)
	{
		try
		{
			return sPrefs.getString(key, defaultValue);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static int getInteger(String key)
	{
		try
		{
			return sPrefs.getInt(key, -1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public static boolean getBoolean(String key)
	{
		try
		{
			return sPrefs.getBoolean(key, false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static boolean edit(String key, String value)
	{
		try
		{
			SharedPreferences.Editor editor = sPrefs.edit();
			editor.putString(key, value);
			return editor.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
