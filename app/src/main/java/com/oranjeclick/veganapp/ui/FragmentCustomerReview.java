package com.oranjeclick.veganapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oranjeclick.veganapp.R;
import com.oranjeclick.veganapp.adapters.AdapterCustomerReview;
import com.oranjeclick.veganapp.adapters.AdapterRestaurantList;
import com.oranjeclick.veganapp.models.ReviewModel;
import com.oranjeclick.veganapp.others.NetworkController;

import java.io.Reader;
import java.util.ArrayList;

public class FragmentCustomerReview extends Fragment
{
	AdapterCustomerReview customerReview;
	ArrayList<ReviewModel> reviews=new ArrayList<>();
	String strID="-1";
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		Bundle bundle=this.getArguments();
		if(bundle!=null)
		{
			strID=bundle.getString("Value ID");
		}
		View view = inflater.inflate(R.layout.customer_review, container, false);
		functionNetworkController();
		RecyclerView recyclerView=view.findViewById(R.id.recycler_mainview);
		recyclerView = recyclerView.findViewById(R.id.recycler_mainview);
		customerReview=new AdapterCustomerReview(reviews);
		recyclerView.setAdapter(customerReview);
		recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
		return view;
	}

	public void functionNetworkController()
	{
		NetworkController.getReview(getActivity(), strID, new Response.Listener<String>() {
			@Override

			public void onResponse(String response)
			{
				Log.d("msg","Successful!");


				ReviewModel []data = ReviewModel.fromJsonArray(response);
				//Toast.makeText(getActivity(),"Successfully Done. Data is:" + "\n"+response, Toast.LENGTH_LONG).show();
				reviews.clear();
				for(ReviewModel rm:data)
				{
					reviews.add(rm);
				}

				/*for(ReviewModel r:data)
				{
					reviews.add(r);

				}*/
				customerReview.notifyDataSetChanged();
				Log.v("msg2","Count Reviews Is: "+String.valueOf(reviews.size()));
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.d("Error", "No Data Found!");
				Toast.makeText(getActivity(), String.valueOf(error.getMessage()), Toast.LENGTH_LONG).show();
			}
		});
	}
}
