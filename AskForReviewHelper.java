import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

public class AskForReviewHelper
{

	public AskForReviewHelper(Context context)
	{
		this.context = context;

		getPrefs();
		if (canAskForReview())
		{
			int count = getLaunchCount();
			if (count >= 4)
			{
				askForReview();
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
	
	private void askForReview()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.review_the_app).setMessage("If you like the application please review it on the Play Store!");
		builder.setNegativeButton("No thanks", new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface p1, int p2)
				{
			dontAskAnymore();
				}
			}).setNeutralButton("Remind me later", new DialogInterface.OnClickListener(){

				public void onClick(DialogInterface p1, int p2)
				{
					;
				}
			}).setPositiveButton("Yes sture", new DialogInterface.OnClickListener(){

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

	private Context context;
	private SharedPreferences prefs;
}
