import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

public class AskForReviewHelper
{
	private Context context;
	private SharedPreferences prefs;
	
	public AskForReviewHelper(Context context, int title, int message, int negativeMessage, int neutralMessage, int positiveMessage)
	{
		this.context = context;

		getPrefs();
		if (canAskForReview())
		{
			int count = getLaunchCount();
			if (count >= 4)
			{
				askForReview(title, message, negativeMessage, neutralMessage, positiveMessage);
			}
			else
			{
				increaseCounter(count);
			}
		}
	}

	private void increaseCounter(int count)
	{
		count++;
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt("launchCount", count);
		editor.commit();
	}

	private void getPrefs()
	{
		this.prefs = context.getSharedPreferences("ReviewsHelper", Context.MODE_PRIVATE);
	}

	private boolean canAskForReview()
	{
		//return true;
	    return !prefs.getBoolean("cantAsk", false);
	}

	private int getLaunchCount()
	{
		return prefs.getInt("launchCount", 0);
	}
	
	private void askForReview(int title, int message, int negativeMessage, int neutralMessage, int positiveMessage)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title).setMessage(message);
		builder.setNegativeButton(negativeMessage, new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface p1, int p2)
				{
			dontAskAnymore();
				}
			}).setNeutralButton(neutralMessage, new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface p1, int p2)
				{
					;
				}
			}).setPositiveButton(positiveMessage, new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface p1, int p2)
				{
					dontAskAnymore();
					TaskHelper.marketplaceTask(context, context.getPackageName());
				}
		});
		builder.create().show();
		
	}
	
	private void dontAskAnymore()
	{
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("cantAsk", true);
		editor.commit();
	}
}
