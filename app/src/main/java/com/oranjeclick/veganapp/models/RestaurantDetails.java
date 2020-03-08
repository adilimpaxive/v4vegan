package com.oranjeclick.veganapp.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantDetails {

	@SerializedName("pk_restaurant_id")
	@Expose
	private String pkRestaurantId;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("location")
	@Expose
	private String location;
	@SerializedName("picture")
	@Expose
	private String picture;
	@SerializedName("created_date")
	@Expose
	private String createdDate;

	@SerializedName("rating")
	@Expose
	private String rating;

	@SerializedName("review")
	@Expose
	private String review;


	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}



	public String getPkRestaurantId() {
		return pkRestaurantId;
	}

	public void setPkRestaurantId(String pkRestaurantId) {
		this.pkRestaurantId = pkRestaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getRating()
	{
		return rating;
	}

	public  void setRating(String rating)
	{
		this.rating=rating;
	}


	//
	public static RestaurantDetails[] fromJsonArray(String jsonArray)
	{
		Gson gson = new GsonBuilder().serializeNulls().create();
		RestaurantDetails[] data  = gson.fromJson(jsonArray, RestaurantDetails[].class);
		return  data;
	}


}