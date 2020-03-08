package com.oranjeclick.veganapp.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewModel {

	@SerializedName("pk_review_id")
	@Expose
	private String pkReviewId;
	@SerializedName("rest_id")
	@Expose
	private String restId;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("location")
	@Expose
	private String location;
	@SerializedName("review_title")
	@Expose
	private String review;
	@SerializedName("rating")
	@Expose
	private String rating;

	public String getPkReviewId() {
		return pkReviewId;
	}

	public void setPkReviewId(String pkReviewId) {
		this.pkReviewId = pkReviewId;
	}

	public String getRestId() {
		return restId;
	}

	public void setRestId(String restId) {
		this.restId = restId;
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

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public static ReviewModel [] fromJsonArray(String jsonArray)
	{
		Gson gson = new GsonBuilder().serializeNulls().create();
		ReviewModel [] data = gson.fromJson(jsonArray,ReviewModel[].class);
		return  data;
	}

}
