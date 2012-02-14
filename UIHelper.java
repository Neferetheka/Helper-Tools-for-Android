import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.widget.Toast;

public abstract class UIHelper
{
	/* Permit to make toast notification */
	public static void toast(Context context, String message, boolean islong)
	{
		Toast.makeText(context, message, (islong) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
	}

	/* Permit to make status bar notification */
	public static void statusBarNotification(Context context, String title, String message, Intent intent)
	{
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(ns);

		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();

		Notification notification = new Notification(icon, message, when);

		CharSequence contentTitle = title;
		CharSequence contentText = message;
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);

		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		mNotificationManager.notify(1, notification);
	}

	/* Permit to display a classic alert dialog */
	public static void alertDialog(Context context, String message, String titre)
	{
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(titre);
		dialog.setMessage(message);
		dialog.show();
	}

	/* 
	 * Permit to display a classic alert dialog with default title
	 */
	public static void alertDialog(Context context, String message)
	{
		alertDialog(context, message, "Message");
	}

	/*
	 * Permit to detect tablet
	 */
	public static boolean isTablet(Context context)
	{
		try
		{
			DisplayMetrics dm = context.getResources().getDisplayMetrics();
			float screenWidth = dm.widthPixels / dm.xdpi;
			float screenHeight = dm.heightPixels / dm.ydpi;
			double size = Math.sqrt(Math.pow(screenWidth, 2) + Math.pow(screenHeight, 2));
			return size >= 6;
		}
		catch (Throwable t)
		{
			return false;
		}
	}
}
