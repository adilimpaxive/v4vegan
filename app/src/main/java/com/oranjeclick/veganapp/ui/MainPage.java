package com.oranjeclick.veganapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.oranjeclick.veganapp.R;
import com.oranjeclick.veganapp.adapters.AdapterRestaurantList;
import com.oranjeclick.veganapp.models.MenuItems;
import com.oranjeclick.veganapp.models.RestaurantDetails;
import com.oranjeclick.veganapp.others.NetworkController;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {

	ArrayList<RestaurantDetails> restaurantDetails=new ArrayList<>();
	ArrayList<String> restaurantDetailsName=new ArrayList<>();
	ArrayList<RestaurantDetails> restaurantDetailsRevised=new ArrayList<>();
	AutoCompleteTextView editSearchBar;

	AdapterRestaurantList adapterRestaurantList;
	AdapterRestaurantList adapterRestaurantListRevised;
	ArrayAdapter<String> adapter;
	RecyclerView recyclerView;
	int clickCount=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		editSearchBar=findViewById(R.id.searchbar);
		adapter = new ArrayAdapter<String>
				(this,android.R.layout.select_dialog_item,restaurantDetailsName);
		editSearchBar.setThreshold(1);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		functionNetworkController();
		recyclerView=findViewById(R.id.recycler_mainview);
		adapterRestaurantList=new AdapterRestaurantList(restaurantDetails);
		adapterRestaurantListRevised= new AdapterRestaurantList(restaurantDetailsRevised);
		recyclerView.setAdapter(adapterRestaurantList);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	public void functionNetworkController()
	{
		NetworkController.getRestaurants(this,new Response.Listener<String>() {
			@Override

			public void onResponse(String response)
			{
				Log.v("msg","Successful!");
				RestaurantDetails[] data=RestaurantDetails.fromJsonArray(response);
				//Toast.makeText(MainPage.this,"Successfully Done. Data is:" + "\n"+response, Toast.LENGTH_LONG).show();
				restaurantDetails.clear();
				restaurantDetailsName.clear();
				for(RestaurantDetails r: data)
				{
					restaurantDetails.add(r);
					restaurantDetailsName.add(r.getName());
					Log.v("Val","Review " +restaurantDetails.get(0).getReview());
				}

				//Log.v("Value Size", "Value:" +String.valueOf(restaurantDetails.size()));
				MainPage.this.restaurantDetails=restaurantDetails;
				adapterRestaurantList.notifyDataSetChanged();
				editSearchBar.setAdapter(adapter);
				Log.v("msg1","Count Restaurant List Is: "+String.valueOf(restaurantDetails.size()));

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.v("Error", "No Data Found!");
				Toast.makeText(MainPage.this, String.valueOf(error.getMessage()), Toast.LENGTH_LONG).show();
			}
		});
	}

	public void functionSearch(View v)
	{
		String valSearch=editSearchBar.getText().toString();
		Log.v("Val RD is: ",String.valueOf(restaurantDetails.size()));
		restaurantDetailsRevised.clear();
		for(int i=0;i<restaurantDetails.size();i++)
		{
			if(!restaurantDetails.get(i).getName().equals("") || !restaurantDetails.get(i).getLocation().equals(""))
			{
				if(restaurantDetails.get(i).getLocation().toLowerCase().contains(valSearch.toLowerCase()) ||
						restaurantDetails.get(i).getName().toLowerCase().contains(valSearch.toLowerCase()))
				{
					restaurantDetailsRevised.add(restaurantDetails.get(i));
				}

			}
		}
		Log.v("Val RDR is: ",String.valueOf(restaurantDetailsRevised.size()));
		if(clickCount==0)
		{
			Log.v("Val is: ","Test Initial Revised Array");
			recyclerView.setAdapter(adapterRestaurantListRevised);
			recyclerView.setLayoutManager(new LinearLayoutManager(this));
			clickCount++;
		}
		else
		{

			if(!(editSearchBar.getText().toString().equals("")))
			{
				Log.v("Val is: ","Test Revised Array List");
				adapterRestaurantListRevised.notifyDataSetChanged();
			}
			else if (editSearchBar.getText().toString().equals(""))
			{
				Log.v("Val is: ","Set Adapter Back To Original Array List");
				recyclerView.setAdapter(adapterRestaurantList);
				recyclerView.setLayoutManager(new LinearLayoutManager(this));
				clickCount=0;
			}

		}

	}
}
