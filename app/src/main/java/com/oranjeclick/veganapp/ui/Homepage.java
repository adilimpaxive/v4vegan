package com.oranjeclick.veganapp.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.oranjeclick.veganapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class Homepage extends AppCompatActivity {

	FragmentTransaction fragmentTransaction;
	String userID;
	public class MyPagerAdapter extends FragmentPagerAdapter {
		private int NUM_ITEMS = 2;

		public MyPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		// Returns total number of pages
		@Override
		public int getCount() {
			return NUM_ITEMS;
		}

		// Returns the fragment to display for that page
		@Override
		public Fragment getItem(int position) {
			switch (position) {
				case 0: // Fragment # 0 - This will show FirstFragment
					Bundle bundle1=new Bundle();
					bundle1.putString("Value ID", userID);
					FragmentMenuItemList fragmentMenuItemList=new FragmentMenuItemList();
					fragmentMenuItemList.setArguments(bundle1);
					return fragmentMenuItemList;
				case 1: // Fragment # 0 - This will show FirstFragment different title
					Bundle bundle=new Bundle();
					bundle.putString("Value ID", userID);
					FragmentCustomerReview review=new FragmentCustomerReview();
					review.setArguments(bundle);
					return review;
				default:
					return null;
			}
		}

		public CharSequence getPageTitle(int position)
		{
			switch (position)
			{
				case 0:
					return "Menu";
				case 1:
					return "Customer Review";
					default:
						return null;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		TextView tv_location, tv_name,tv_reviews;
		RatingBar rating;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		ImageView imageView=findViewById(R.id.img_toolbar);
		tv_name=findViewById(R.id.tv_name);
		tv_location=findViewById(R.id.tv_location);
		rating=findViewById(R.id.ratingicons);
		tv_reviews=findViewById(R.id.NoReviews);
		Intent intent=getIntent();
		final String location=intent.getStringExtra("Value Location");
		final String name=intent.getStringExtra("Value Name");
		final String ratingVal=intent.getStringExtra("Rating Value");
		final String reviewVal=intent.getStringExtra("Review Value");
		userID=intent.getStringExtra("Value ID");
		String temp = intent.getStringExtra("img");

		Transformation transformation = new RoundedTransformationBuilder()
				.borderColor(Color.CYAN)
				.borderWidthDp(0)
				.cornerRadiusDp(0)
				.oval(false)
				.build();
		Picasso.get()
				.load(temp)
				.placeholder(R.drawable.layer3copy)
				.error(R.drawable.layer3copy)
				.transform(transformation)
				.into(imageView);

		Log.v("Values","Values1 Are: Name- "+name+" \nLocation- "+location+
				"\nRating- "+rating);
		tv_location.setText(location);
		tv_name.setText(name);
		if(ratingVal.equals(""))
		{
			rating.setRating(0);
		}
		else
		{
			rating.setRating(Math.round(Float.parseFloat(ratingVal)));
		}
		if(reviewVal.equals(""))
		{
			tv_reviews.setText("(0) Reviews");
		}
		else
		{
			tv_reviews.setText("("+reviewVal+") Reviews");
		}

		PagerSlidingTabStrip pagerTabStrip=findViewById(R.id.pagertabstrip);
		//pagerTabStrip.setShouldExpand(true);
		pagerTabStrip.setIndicatorColor(getResources().getColor(R.color.darkorange));
		pagerTabStrip.setDividerPadding(-45);
		pagerTabStrip.setDividerColor(getResources().getColor(R.color.transparent));
		pagerTabStrip.setIndicatorHeight(6);
		pagerTabStrip.setUnderlineColor(getResources().getColor(R.color.transparent));
		pagerTabStrip.setScrollOffset(10);

		ViewPager viewPager=findViewById(R.id.vpPager);
		FragmentPagerAdapter fragmentPagerAdapter=new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(fragmentPagerAdapter);
		pagerTabStrip.setViewPager(viewPager);

		Button btnAddReview=findViewById(R.id.btn_addreview);
		btnAddReview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
						/*final Dialog dialogue = new Dialog(Homepage.this);
						dialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
						dialogue.setContentView(R.layout.fragment_review);
						dialogue.setCancelable(true);
						dialogue.show();*/
						Bundle bundle=new Bundle();
						bundle.putString("Value Name", name);
						bundle.putString("Value Location", location);
						bundle.putString("Value ID",userID);
						fragmentTransaction=getSupportFragmentManager().beginTransaction();
						Fragment previous=getSupportFragmentManager().findFragmentByTag("dialog_review");
						if(previous!=null)
						{
							fragmentTransaction.remove(previous);
						}
						fragmentTransaction.addToBackStack(null);
						Review review=new Review();
						review.setArguments(bundle);
						review.show(fragmentTransaction,"dialog_review");
			}
		});
	}

	public void onClickBack(View v)
	{
		Intent i=new Intent(this, MainPage.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(i);
		finish();
	}
}
