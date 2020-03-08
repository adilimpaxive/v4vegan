package com.oranjeclick.veganapp.others;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestCache {

	private static RequestCache mInstance;
	private RequestQueue requestQueue;
	private static Context mContext;

	private RequestCache(Context context)
	{
		mContext=context;
		requestQueue=getRequestQueue();
	}

	public RequestQueue getRequestQueue() {
		if(requestQueue==null)
		{
			Log.e("Request Queue"," Is NULL" );
			requestQueue= Volley.newRequestQueue(mContext.getApplicationContext());
		}
		return requestQueue;
	}

	public static synchronized RequestCache getInstance(Context context)
	{
		if(mInstance==null)
		{
			mInstance=new RequestCache(context);
		}
		return mInstance;
	}

	public <T> void addToRequestQueue(Request<T> request)
	{
		requestQueue.add(request);
	}
}
