package com.aerilys.helpers.android;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;

public final class TaskHelper
{
	/*
	 * Allow to launch a share intent
	 */
	public static void shareTask(Context context, String title, String content)
	{
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, title);
		intent.putExtra(Intent.EXTRA_TEXT, content);
		context.startActivity(Intent.createChooser(intent, title));
	}

	/*
	 * Allow to launch a webbrowser intent
	 */
	public static void browserTask(Context context, String link)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
		context.startActivity(intent);	
	}
	
	/*
	 * Allow to launch market at app page
	 */
	public static void marketplaceTask(Context context)
	{
		marketplaceTask(context, context.getPackageName());
	}
	
	/*
	 * Allow to launch market on a specific app
	 */
	public static void marketplaceTask(Context context, String packageName)
	{
		try
		{
			if (!android.os.Build.MANUFACTURER.equals("Amazon"))
			{
				Uri uri = Uri.parse("market://details?id="+packageName);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				context.startActivity(intent);
			}
			else
			{
				TaskHelper.browserTask(context, "http://www.amazon.com/gp/mas/dl/android?p=" + packageName);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Launch an app with the provided package name
	 * If the app is not present on the device, the Play store is opened
	 */
	public static void launchApp(Context context, String packageName)
	{
		Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
		if (launchIntent != null)
			context.startActivity(launchIntent);
		else
			TaskHelper.marketplaceTask(context, packageName);
	}
}
