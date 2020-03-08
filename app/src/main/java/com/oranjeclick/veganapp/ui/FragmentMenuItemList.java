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
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.oranjeclick.veganapp.R;
import com.oranjeclick.veganapp.adapters.AdapterMenuItemList;
import com.oranjeclick.veganapp.models.MenuItems;
import com.oranjeclick.veganapp.others.NetworkController;

import java.util.ArrayList;

public class FragmentMenuItemList extends Fragment {

	ArrayList<MenuItems> menuItems=new ArrayList<MenuItems>();
	AdapterMenuItemList itemList;
	String strID="-1";
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Bundle bundle=this.getArguments();
		if(bundle!=null)
		{
			strID=bundle.getString("Value ID");
		}
		View view=inflater.inflate(R.layout.fragment_menu_items_list,container,false);
		RecyclerView recyclerView=view.findViewById(R.id.recycler_menu);
		itemList=new AdapterMenuItemList(menuItems);
		recyclerView.setAdapter(itemList);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
				MenuItems[] data = MenuItems.fromJsonArray(response);
				menuItems.clear();
				for (MenuItems m:data)
				{
					menuItems.add(m);
				}
				itemList.notifyDataSetChanged();
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
