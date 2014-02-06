package com.aerilys.helpers.android;

import android.content.Context;
import android.content.SharedPreferences;

public final class Cache
{
	private static SharedPreferences prefs;

	/**
	 * Get an item from the cache
	 * @param context : app context
	 * @param key : key of the item we want to retrieve
	 * @return : request string from cache, or null
	 */
	public static String getItem(Context context, String key)
	{
		getPrefs(context);
		if (prefs.contains(key + "cache"))
		{
			long expirationTime = prefs.getLong(key + "cache", System.currentTimeMillis());
			if (expirationTime < System.currentTimeMillis())
			{
				SharedPreferences.Editor editor = prefs.edit();
				editor.remove(key);
				editor.remove(key + "cache");
				editor.commit();
				return null;
			}
		}

		return prefs.getString(key, null);
	}

	/**
	 * Set an item in cache with an expiration date.
	 * 
	 * @param context
	 *            : app context
	 * @param key
	 *            : cache key
	 * @param item
	 *            : item to put in cache
	 * @param timeBeforeExpiration
	 *            : time before expiration in milliseconds.
	 * @return : a success boolean
	 */
	public static boolean setitem(Context context, String key, String item, long timeBeforeExpiration)
	{
		getPrefs(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, item);
		long expirationTime = System.currentTimeMillis() + timeBeforeExpiration;
		editor.putLong(key + "cache", expirationTime);
		return editor.commit();
	}

	/**
	 * Get cache preferences
	 * @param context : app context
	 */
	private static void getPrefs(Context context)
	{
		if (prefs == null)
		{
			prefs = context.getSharedPreferences("cache", Context.MODE_PRIVATE);
		}
	}
}
