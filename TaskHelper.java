import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public abstract class TaskHelper
{
	/*
	 * Permit to launch a share intent
	 */
	public static void shareTask(Activity activity, String title, String content)
	{
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, title);
		intent.putExtra(Intent.EXTRA_TEXT, content
				+ " - via MyApp");
		activity.startActivity(Intent.createChooser(intent, title));
	}

	/*
	 * Permit to launch a webbrowser intent
	 */
	public static void browserTask(Activity activity, String lien)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(lien));
		activity.startActivity(intent);	
	}
}
