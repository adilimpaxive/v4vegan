package com.oranjeclick.veganapp.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.oranjeclick.veganapp.R;
import com.oranjeclick.veganapp.others.NetworkController;

public class Review extends DialogFragment {

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		return super.onCreateDialog(savedInstanceState);
	}

	int count=0;

	String strreview, strName,strID;
	EditText name,review, review_title, review_description;
	RatingBar rating;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		strreview="123";
		strName="xyz";
		strID="-1";
		Bundle bundle=this.getArguments();
		if(bundle!=null)
		{
			strName=bundle.getString("Value Name");
			strreview=bundle.getString("Value review");
			strID=bundle.getString("Value ID");
		}

		View view = inflater.inflate(R.layout.fragment_review, container, false);

		name=view.findViewById(R.id.name);
		review=view.findViewById(R.id.review);
		rating=view.findViewById(R.id.reviewicons);
		rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				count=1;

			}
		});

		getDialog().getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroundaddreview));
		getDialog().setCanceledOnTouchOutside(false);
		Button btn_submit = view.findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (name.getText().toString().trim().equals("")) {
					name.setError("Name Field Is Required!");
					name.requestFocus();
					return;
				}
				strName = name.getText().toString();


				if (review.getText().toString().trim().equals("")) {
					review.setError("review Field Is Required!");
					review.requestFocus();
					return;
				}
				strreview = review.getText().toString();

				if(count==0)
				{
					Toast.makeText(getActivity(),"Rating Is Required",Toast.LENGTH_SHORT).show();
					return;
				}

				NetworkController.addReview(getActivity(), strID, name.getText().toString(), review.getText().toString(), String.valueOf(rating), new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
						Log.v("msg","Response: "+response);
						//Intent i = new Intent(getActivity(), Homepage.class);
						//i.putExtra("Value review", strreview);
						//i.putExtra("Value Name",strName);
						//Log.v("LocStr",strreview);
						Log.v("NameStr",strName);
						//i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
						//startActivity(i);
						dismiss();
					}

				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(getActivity(), String.valueOf(error.getMessage()), Toast.LENGTH_LONG).show();
						Log.v("msg_error","Response: "+error.toString());

					}
				});

			}
		});
		return  view;
	}
}
