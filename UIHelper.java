import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.DisplayMetrics;
import android.widget.Toast;

public abstract class UIHelper
{
	/* Permits to make toast notification */
	public static void toast(Context context, String message, boolean islong)
	{
		Toast.makeText(context, message, (islong) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
	}

	/* Permits to make toast notification without length argument */
	public static void toast(Context context, String message)
	{
		toast(context, message, true);
	}

	/* Allows to make status bar notification */
	public static void statusBarNotification(Context context, String title, String message, Intent intent,
			Class<?> parent)
	{
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(context)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle(title)
		        .setContentText(message).setOngoing(true);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(parent);
		stackBuilder.addNextIntent(intent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(1, mBuilder.build());
	}

	/* Allows to make status bar notification not clearable by user */
	public static <T> void statusBarNotificationRemaining(Context context, String title, String message)
	{
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
				.setSmallIcon(R.drawable.ic_launcher).setContentTitle(title).setContentText(message)
				.setAutoCancel(false).setOngoing(false);

		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);	
		Notification notification = builder.build();
		notification.flags = Notification.FLAG_NO_CLEAR;
		notificationManager.notify(0, notification);
	}

	/* Permits to display a classic alert dialog */
	public static void alertDialog(Context context, String message, String title)
	{
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.show();
	}

	/* Permits to display a classic alert dialog with default title */
	public static void alertDialog(Context context, String message)
	{
		alertDialog(context, message, "Message");
	}

	/* Permits to detect tablet */
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
