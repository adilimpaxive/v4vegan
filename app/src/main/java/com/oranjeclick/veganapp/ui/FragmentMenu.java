package com.oranjeclick.veganapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.oranjeclick.veganapp.R;
import com.oranjeclick.veganapp.models.MenuItems;
import com.oranjeclick.veganapp.models.ReviewModel;
import com.oranjeclick.veganapp.others.NetworkController;

import java.util.ArrayList;

public class FragmentMenu extends Fragment {

	GridView androidGridView;
	ArrayList<MenuItems> menuItems=new ArrayList<MenuItems>();
	String [] gridViewString;
	int [] gridViewImageId;
	GridContent adapter;
	View view;
	String strID="-1";
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Bundle bundle=this.getArguments();
		if(bundle!=null)
		{
			strID=bundle.getString("Value ID");
		}if(bundle!=null)
		{
			strID=bundle.getString("Value ID");
		}
		view= inflater.inflate(R.layout.fragment_menu,container,false);
		functionNetworkController();

		return view;
	}

	public void functionNetworkController()
	{
		NetworkController.getMenuItems(getActivity(),strID,new Response.Listener<String>() {
		@Override

		public void onResponse(String response)
		{
			Log.d("msg","Successful!");
			MenuItems [] data = MenuItems.fromJsonArray(response);
			menuItems.clear();
			for (MenuItems m:data)
			{
				menuItems.add(m);
			}
			gridViewString=new String[menuItems.size()];
			for(int i=0;i<menuItems.size();i++)
			{
				gridViewString[i]=menuItems.get(i).getName();
			}
			gridViewImageId=new int[menuItems.size()];
			for(int i=0;i<menuItems.size();i++)
			{
				gridViewImageId[i]=R.drawable.kfcoriginalchickenrecipe500x366;
			}

			adapter=new GridContent(getActivity(),gridViewString,gridViewImageId);
			androidGridView=(GridView)view.findViewById(R.id.grid_view_image_text);
			androidGridView.setAdapter(adapter);


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
