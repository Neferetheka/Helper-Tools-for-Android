package com.aerilys.helpers.android;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/*
* Special class to create and resize bitmaps on the go	
*/
public abstract class BitmapHelper
{
	public static int calculateSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
	{
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth)
		{
			if (width > height)
			{
				inSampleSize = Math.round((float) height / (float) reqHeight);
			}
			else
			{
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

	public static Bitmap decodeBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight)
	{
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		options.inSampleSize = calculateSize(options, reqWidth, reqHeight);

		options.inJustDecodeBounds = false;
		
		return BitmapFactory.decodeResource(res, resId, options);
	}
	
	public static Bitmap decodeBitmapFromResourceAndCropIt(Resources res, int resId, int reqWidth, int reqHeight, int x, int y)
	{
		Bitmap bmp = decodeBitmapFromResource(res, resId, reqWidth, reqHeight);
		return Bitmap.createBitmap(bmp, x, y, bmp.getWidth(), bmp.getHeight()-y);
	}
}
