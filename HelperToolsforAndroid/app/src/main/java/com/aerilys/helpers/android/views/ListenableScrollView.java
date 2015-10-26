package com.aerilys.helpers.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Adapted from
 * http://cyrilmottier.com/2013/05/24/pushing-the-actionbar-to-the-next-level/
 * 
 * @author Galaad
 * 
 */
public class ListenableScrollView extends ScrollView
{
	private OnScrollChangedListener mOnScrollChangedListener;

	public ListenableScrollView(Context context)
	{
		super(context);
	}

	public ListenableScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public ListenableScrollView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public interface OnScrollChangedListener
	{
		void onScrollChanged(ScrollView source, int l, int t, int oldl, int oldt);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		super.onScrollChanged(l, t, oldl, oldt);
		if (mOnScrollChangedListener != null)
		{
			mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
		}
	}

	public void setOnScrollChangedListener(OnScrollChangedListener listener)
	{
		mOnScrollChangedListener = listener;
	}
}
