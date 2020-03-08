package com.oranjeclick.veganapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.oranjeclick.veganapp.R;
import com.oranjeclick.veganapp.models.ReviewModel;
import com.oranjeclick.veganapp.ui.Homepage;

import java.util.ArrayList;

public class AdapterCustomerReview extends RecyclerView.Adapter<AdapterCustomerReview.MyViewHolder> {
	public class MyViewHolder extends RecyclerView.ViewHolder
	{
		public TextView tv_name,tv_review;
		private View itemView;
		public RatingBar rating;

		public MyViewHolder(View itemView)
		{
			super(itemView);
			tv_name=itemView.findViewById(R.id.cname);
			tv_review=itemView.findViewById(R.id.tv_review);
			rating=itemView.findViewById(R.id.ratingicons);
			this.itemView = itemView;
		}

		public void onBind(ReviewModel reviewModel){
			tv_review.setText(reviewModel.getReview());
			tv_name.setText(reviewModel.getName());
			if(reviewModel.getRating().equals(""))
			{
				rating.setRating(0);
			}
			else
			{
				rating.setRating(Math.round(Float.parseFloat(reviewModel.getRating())));
			}
		}
	}

	View dataview;
	Context context;
	ArrayList<ReviewModel>reviewModels;
	public AdapterCustomerReview(ArrayList<ReviewModel> rm)
	{
		reviewModels=rm;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup v, int viewType)
	{
		context=v.getContext();
		LayoutInflater inflater=LayoutInflater.from(context);
		dataview=inflater.inflate(R.layout.fragment_customer_review,v,false);
		MyViewHolder viewHolder=new MyViewHolder(dataview);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
		final ReviewModel rm=reviewModels.get(i);
		viewHolder.onBind(rm);
	}

	@Override
	public int getItemCount() {
		return reviewModels.size();
	}
}
