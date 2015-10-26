package com.aerilys.helpers.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public final class NetworkHelper
{
	/*
	 * Checks if device is connected
	 * Requires android.permission.ACCESS_NETWORK_STATE permission
	 */
	public static boolean isConnected(Context context)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null)
		{
			State networkState = networkInfo.getState();
			if (networkState.compareTo(State.CONNECTED) == 0)
				return true;
		}
		return false;
	}
}
