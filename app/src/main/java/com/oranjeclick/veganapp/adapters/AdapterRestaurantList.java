package com.oranjeclick.veganapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.oranjeclick.veganapp.R;
import com.oranjeclick.veganapp.models.RestaurantDetails;
import com.oranjeclick.veganapp.ui.Homepage;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class AdapterRestaurantList extends RecyclerView.Adapter<AdapterRestaurantList.MyViewHolder> {
	//public ArrayList<String> arrayVals=new ArrayList<>();
public class MyViewHolder extends RecyclerView.ViewHolder
{
	private String base_url="http://www.oranje-tech.com/demo/v4vegon/api/getRestaurants/";
	public TextView tv_name,tv_location,tv_review;
	public RatingBar rating;
	private View itemView;

	public com.makeramen.roundedimageview.RoundedImageView img_icon;

	public MyViewHolder(View itemView)
	{
		super(itemView);
		this.itemView = itemView;
		tv_name=itemView.findViewById(R.id.tv_namerestaurant);
		tv_location=itemView.findViewById(R.id.tvLocation);
		img_icon=itemView.findViewById(R.id.img_icon);
		rating=itemView.findViewById(R.id.ratingicons);
		tv_review=itemView.findViewById(R.id.NoReviews);
	}

	public void onBind(RestaurantDetails restaurantDetails)
	{
		String temp = restaurantDetails.getPicture();
		Log.v("Picture id: ","Picture id: "+temp);

		Transformation transformation = new RoundedTransformationBuilder()
				.borderColor(Color.CYAN)
				.borderWidthDp(3)
				.cornerRadiusDp(30)
				.oval(false)
				.build();

		Picasso.get()
				.load(temp)
				.placeholder(R.drawable.layer3copy)
				.error(R.drawable.layer3copy)
				.transform(transformation)
				.into(img_icon);

		tv_name.setText(restaurantDetails.getName());
		tv_location.setText(restaurantDetails.getLocation());

		if(restaurantDetails.getRating().equals(""))
		{
			rating.setRating(0);
		}
		else
		{
			rating.setRating(Math.round(Float.parseFloat(restaurantDetails.getRating())));
		}

		if(restaurantDetails.getReview().toString().equals(""))
		{
			tv_review.setText("(0) Reviews");
		}
		else
		{
			tv_review.setText("("+restaurantDetails.getReview().toString()+") Reviews");
		}
		//arrayVals.add(temp);
	}
}

	View dataview;
	Context context;
	private ArrayList<RestaurantDetails> restaurantDetails;

	public AdapterRestaurantList(ArrayList<RestaurantDetails>r)
	{
		restaurantDetails=r;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup v, int viewType)
	{
		context=v.getContext();
		LayoutInflater inflater=LayoutInflater.from(context);
		dataview=inflater.inflate(R.layout.fragment_vegan_restaurants,v,false);
		MyViewHolder viewHolder=new MyViewHolder(dataview);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
		final RestaurantDetails rd = restaurantDetails.get(i);

		viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(context, Homepage.class);
				i.putExtra("Value Name", rd.getName());
				i.putExtra("Value Location", rd.getLocation());
				i.putExtra("Rating Value", rd.getRating());
				i.putExtra("Review Value", rd.getReview());
				i.putExtra("img", rd.getPicture());
				i.putExtra("Value ID", rd.getPkRestaurantId());
				Log.v("Values","Values Are: Name- "+rd.getName()+" \nLocation- "+rd.getLocation()+
						"\nRating- "+rd.getRating());
				//i.putExtra("String Array",arrayVals);

				context.startActivity(i);
			}
		});
		viewHolder.onBind(rd);
	}

	@Override
	public int getItemCount() {
		//Log.v("Array Val","Array Val: "+ String.valueOf(restaurantDetails.size()));
		return restaurantDetails.size();
	}
}
