package com.aerilys.helpers.android;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

@SuppressLint("SimpleDateFormat")
public final class Cache
{
	private static SharedPreferences prefs;

	public static String getItem(Context context, String key)
	{
		getPrefs(context);
		if(prefs.contains(key+"cache"))
		{
			try
			{
				Date date = new SimpleDateFormat().parse((prefs.getString(key+"cache", null)));
				if(date.before(new Date()))
				{
					SharedPreferences.Editor editor = prefs.edit();
					editor.remove(key);
					editor.remove(key+"cache");
					editor.commit();
					return null;
				}
			}
			catch (ParseException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		return prefs.getString(key, null);
	}

	public static boolean setitem(Context context, String key, String item, Date toExpire)
	{
		getPrefs(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, item);
		SimpleDateFormat format = new SimpleDateFormat();
		editor.putString(key+"cache", format.format(toExpire));
		return editor.commit();
	}

	private static void getPrefs(Context context)
	{
		if (prefs == null)
			prefs = context.getSharedPreferences("cache", Context.MODE_PRIVATE);
	}
}
