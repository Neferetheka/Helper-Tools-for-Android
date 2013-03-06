import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;


public class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
{
	private ImageView imageToLoad;
	private Context context;

	public DownloadImageTask(Context context, ImageView imageToLoad)
	{
		super();
		this.context = context;
		this.imageToLoad = imageToLoad;
	}

	protected Bitmap doInBackground(String... urls)
	{
		try
		{
			String urlToLoad = urls[0];

			if (existsInCache(urlToLoad))
				return getFromCache(urlToLoad);
			
			final BitmapFactory.Options options = new BitmapFactory.Options();

			options.inJustDecodeBounds = false;

			InputStream imageStream = (InputStream) new URL(urlToLoad).getContent();
			
			Bitmap result = BitmapFactory
					.decodeStream(imageStream, null, options);
			
			imageStream.close();

			if (result != null)
				saveToCache(urlToLoad, result);

			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	protected void onPostExecute(Bitmap result)
	{
		if (imageToLoad != null)
		{
			if (result == null)
			{
				//Fallback image if an error occurs during loading
			}
			else
			{
				imageToLoad.setImageBitmap(result);
				imageToLoad.setScaleType(ScaleType.FIT_CENTER);
			}
		}
	}

	private void saveToCache(String urlToLoad, Bitmap bitmapToCache)
	{
		File cacheDirectory = context.getExternalCacheDir();
		File cacheFile = null;
		
		try
		{
			cacheFile = new File(cacheDirectory, urlToLoad.replace("/", "_"));

			FileOutputStream fileStream = new FileOutputStream(cacheFile);
			bitmapToCache.compress(Bitmap.CompressFormat.JPEG, 80, fileStream);
			fileStream.close();
		}
		catch (Exception e)
		{
			if(cacheFile != null)
				cacheFile.delete();
			e.printStackTrace();
		}
	}

	private boolean existsInCache(String urlToLoad)
	{
		try
		{
			File cacheDirectory = context.getExternalCacheDir();
			File cacheFile = new File(cacheDirectory, urlToLoad.replace("/", "_"));

			return cacheFile.exists();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	private Bitmap getFromCache(String urlToLoad)
	{
		try
		{
			File cacheDirectory = context.getExternalCacheDir();
			File cacheFile = new File(cacheDirectory, urlToLoad.replace("/", "_"));
			
			FileInputStream inputStream = new FileInputStream(cacheFile);
			Bitmap result = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
			
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void cancelTask()
	{
		try
		{
			if (this.getStatus() == Status.RUNNING || this.getStatus() == Status.PENDING)
				this.cancel(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}