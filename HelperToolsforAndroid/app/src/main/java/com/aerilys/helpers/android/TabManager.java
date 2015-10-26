package com.aerilys.helpers.android;
import android.app.Activity;
import android.content.Intent;

public final class TabManager
{
	/*
	* Launch an intent 
	*/
	public static <T> void navigate(Activity parent, Class<T> cible)
	{
		if (!parent.getClass().getCanonicalName().equals(cible.getCanonicalName()))
		{
			Intent intent = new Intent(parent, cible);
			parent.startActivity(intent);
		}
	}
}
