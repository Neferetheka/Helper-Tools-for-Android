/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aerilys.helpers.android.io;

import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.widget.TextView;

/**
 * An assortment of UI helpers.
 */
public class UIUtils
{

	/**
	 * Factor applied to session color to derive the background color on panels
	 * and when a session photo could not be downloaded (or while it is being
	 * downloaded)
	 */
	public static final float SESSION_BG_COLOR_SCALE_FACTOR = 0.65f;
	public static final float SESSION_PHOTO_SCRIM_ALPHA = 0.75f;

	public static final String TARGET_FORM_FACTOR_ACTIVITY_METADATA = "com.google.samples.apps.iosched.meta.TARGET_FORM_FACTOR";

	public static final String TARGET_FORM_FACTOR_HANDSET = "handset";
	public static final String TARGET_FORM_FACTOR_TABLET = "tablet";

	/**
	 * Regex to search for HTML escape sequences.
	 *
	 * <p>
	 * </p>
	 * Searches for any continuous string of characters starting with an
	 * ampersand and ending with a semicolon. (Example: &amp;amp;)
	 */
	private static final Pattern REGEX_HTML_ESCAPE = Pattern.compile(".*&\\S;.*");
	public static final String GOOGLE_PLUS_PACKAGE_NAME = "com.google.android.apps.plus";
	public static final String YOUTUBE_PACKAGE_NAME = "com.google.android.youtube";

	public static final int ANIMATION_FADE_IN_TIME = 250;
	public static final String TRACK_ICONS_TAG = "tracks";

	/**
	 * Populate the given {@link TextView} with the requested text, formatting
	 * through {@link Html#fromHtml(String)} when applicable. Also sets
	 * {@link TextView#setMovementMethod} so inline links are handled.
	 */
	public static void setTextMaybeHtml(TextView view, String text)
	{
		if (TextUtils.isEmpty(text))
		{
			view.setText("");
			return;
		}
		if ((text.contains("<") && text.contains(">")) || REGEX_HTML_ESCAPE.matcher(text).find())
		{
			view.setText(Html.fromHtml(text));
			view.setMovementMethod(LinkMovementMethod.getInstance());
		}
		else
		{
			view.setText(text);
		}
	}

	public static long getCurrentTime()
	{
		return System.currentTimeMillis();
	}

	/**
	 * Given a snippet string with matching segments surrounded by curly braces,
	 * turn those areas into bold spans, removing the curly braces.
	 */
	public static Spannable buildStyledSnippet(String snippet)
	{
		final SpannableStringBuilder builder = new SpannableStringBuilder(snippet);

		// Walk through string, inserting bold snippet spans
		int startIndex, endIndex = -1, delta = 0;
		while ((startIndex = snippet.indexOf('{', endIndex)) != -1)
		{
			endIndex = snippet.indexOf('}', startIndex);

			// Remove braces from both sides
			builder.delete(startIndex - delta, startIndex - delta + 1);
			builder.delete(endIndex - delta - 1, endIndex - delta);

			// Insert bold style
			builder.setSpan(new StyleSpan(Typeface.BOLD), startIndex - delta, endIndex - delta - 1,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			// builder.setSpan(new ForegroundColorSpan(0xff111111),
			// startIndex - delta, endIndex - delta - 1,
			// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			delta += 2;
		}

		return builder;
	}

	public static void preferPackageForIntent(Context context, Intent intent, String packageName)
	{
		PackageManager pm = context.getPackageManager();
		for (ResolveInfo resolveInfo : pm.queryIntentActivities(intent, 0))
		{
			if (resolveInfo.activityInfo.packageName.equals(packageName))
			{
				intent.setPackage(packageName);
				break;
			}
		}
	}

	private static final int BRIGHTNESS_THRESHOLD = 130;

	/**
	 * Calculate whether a color is light or dark, based on a commonly known
	 * brightness formula.
	 *
	 * @see {@literal http://en.wikipedia.org/wiki/HSV_color_space%23Lightness}
	 */
	public static boolean isColorDark(int color)
	{
		return ((30 * Color.red(color) + 59 * Color.green(color) + 11 * Color.blue(color)) / 100) <= BRIGHTNESS_THRESHOLD;
	}

	public static boolean isTablet(Context context)
	{
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	private static final int[] RES_IDS_ACTION_BAR_SIZE =
	{ android.R.attr.actionBarSize };

	/** Calculates the Action Bar height in pixels. */
	public static int calculateActionBarSize(Context context)
	{
		if (context == null)
		{
			return 0;
		}

		Resources.Theme curTheme = context.getTheme();
		if (curTheme == null)
		{
			return 0;
		}

		TypedArray att = curTheme.obtainStyledAttributes(RES_IDS_ACTION_BAR_SIZE);
		if (att == null)
		{
			return 0;
		}

		float size = att.getDimension(0, 0);
		att.recycle();
		return (int) size;
	}

	public static int setColorAlpha(int color, float alpha)
	{
		int alpha_int = Math.min(Math.max((int) (alpha * 255.0f), 0), 255);
		return Color.argb(alpha_int, Color.red(color), Color.green(color), Color.blue(color));
	}

	public static int scaleColor(int color, float factor, boolean scaleAlpha)
	{
		return Color.argb(scaleAlpha ? (Math.round(Color.alpha(color) * factor)) : Color.alpha(color),
				Math.round(Color.red(color) * factor), Math.round(Color.green(color) * factor),
				Math.round(Color.blue(color) * factor));
	}

	public static int scaleSessionColorToDefaultBG(int color)
	{
		return scaleColor(color, SESSION_BG_COLOR_SCALE_FACTOR, false);
	}

	public static float getProgress(int value, int min, int max)
	{
		if (min == max)
		{
			throw new IllegalArgumentException("Max (" + max + ") cannot equal min (" + min + ")");
		}

		return (value - min) / (float) (max - min);
	}
}
