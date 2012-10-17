import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;

public abstract class TaskHelper
{
	/*
	 * Permit to launch a share intent
	 */
	public static void shareTask(Context context, String title, String content)
	{
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, title);
		intent.putExtra(Intent.EXTRA_TEXT, content
				+ " - via MyApp");
		context.startActivity(Intent.createChooser(intent, title));
	}

	/*
	 * Permit to launch a webbrowser intent
	 */
	public static void browserTask(Context context, String link)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
		context.startActivity(intent);	
	}
	
	/*
	 * Permit to launch market at app page
	 */
	public static void marketplaceTask(Context context)
	{
		Uri uri = Uri.parse("market://details?id="+context.getPackageName());
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(intent);
	}
}
