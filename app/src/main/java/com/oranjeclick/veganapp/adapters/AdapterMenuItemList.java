package com.oranjeclick.veganapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.oranjeclick.veganapp.R;
import com.oranjeclick.veganapp.models.MenuItems;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class AdapterMenuItemList extends RecyclerView.Adapter<AdapterMenuItemList.MyViewHolder> {
	public class MyViewHolder extends RecyclerView.ViewHolder
	{
		public TextView tv_name,tv_list;
		private View itemView;
		public com.makeramen.roundedimageview.RoundedImageView img_list;

		Transformation transformation = new RoundedTransformationBuilder()
				.borderColor(Color.CYAN)
				.borderWidthDp(3)
				.cornerRadiusDp(30)
				.oval(false)
				.build();

		public MyViewHolder(View itemView)
		{
			super(itemView);
			tv_name=itemView.findViewById(R.id.title_menu_item);
			tv_list=itemView.findViewById(R.id.list_items);
			img_list=itemView.findViewById(R.id.img_menu_item);
			this.itemView = itemView;
		}

		public void onBind(MenuItems itemMenu){
			String temp = itemMenu.getPicture();
			Log.v("Picture id: ","Picture id: "+temp);

			Picasso.get()
					.load(temp)
					.placeholder(R.drawable.layer3copy)
					.error(R.drawable.layer3copy)
					.transform(transformation)
					.into(img_list);

			tv_name.setText(itemMenu.getName());
			tv_list.setText(itemMenu.getIngredients());

		}
	}

	View dataview;
	Context context;

	public AdapterMenuItemList(ArrayList<MenuItems> menuItems) {
		this.menuItems = menuItems;
	}

	private ArrayList<MenuItems> menuItems;

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup v, int viewType)
	{
		context=v.getContext();
		LayoutInflater inflater=LayoutInflater.from(context);
		dataview=inflater.inflate(R.layout.row_menu_item,v,false);
		MyViewHolder viewHolder=new MyViewHolder(dataview);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
		MenuItems item = menuItems.get(i);
		viewHolder.onBind(item);
	}

	@Override
	public int getItemCount() {
		return menuItems.size();
	}
}
