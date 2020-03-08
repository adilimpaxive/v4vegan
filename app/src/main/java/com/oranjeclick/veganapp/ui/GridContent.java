package com.oranjeclick.veganapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oranjeclick.veganapp.R;

public class GridContent extends BaseAdapter {

	private Context mContext;
	private final String[] gridViewString;
	private final int[] gridViewImageId;

	public GridContent(Context context, String[] gridViewString, int[] gridViewImageId) {
		mContext = context;
		this.gridViewImageId = gridViewImageId;
		this.gridViewString = gridViewString;
	}

	@Override
	public int getCount() {
		return gridViewString.length;
	}

	@Override
	public Object getItem(int i) {
		return null;
	}

	@Override
	public long getItemId(int i) {
		return 0;
	}

	@Override
	public View getView(int i, View convertView, ViewGroup parent) {
		View gridViewAndroid;
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {

			gridViewAndroid = new View(mContext);
			gridViewAndroid = inflater.inflate(R.layout.fragment_grid_content, null);
			TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
			ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image);
			textViewAndroid.setText(gridViewString[i]);
			imageViewAndroid.setImageResource(gridViewImageId[i]);
		} else {
			gridViewAndroid = (View) convertView;
		}
		return gridViewAndroid;
	}
}