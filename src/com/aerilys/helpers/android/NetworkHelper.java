package com.aerilys.helpers.android;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Log;

public final class NetworkHelper
{
		/*
	 * Makes a GET http request
	 * returns the result
	 * You may put it in a thread
	 */
	public static String httpRequest(String url)
	{
		url = url.replace(" ", "%20");
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response = null;
		try
		{
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null)
			{
				try
				{
					String responseString = EntityUtils.toString(entity, "UTF-8");
					return responseString;
				}
				catch (ParseException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			else
				Log.d("debug", "Entity null");

		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * Makes a POST http request
	 */
	public static String httpRequest(String url, Map<String, String> postParameters)
	{
		url = url.replace(" ", "%20");
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		HttpResponse response = null;
		try
		{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(postParameters.size());
			Object[] ParametersNameArray = postParameters.keySet().toArray();
			Object[] ParametersValueArray = postParameters.values().toArray();
			for (int i = 0; i < postParameters.size(); i++)
			{
				nameValuePairs.add(new BasicNameValuePair(ParametersNameArray[i].toString(), ParametersValueArray[i]
						.toString()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = httpClient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			if (entity != null)
			{
				String responseString = EntityUtils.toString(entity);
				return responseString;
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

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
