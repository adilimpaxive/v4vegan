package com.oranjeclick.veganapp.models;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuItems {

	@SerializedName("menu_id")
	@Expose
	private String menuId;

	@SerializedName("fk_restaurant_id")
	@Expose
	private String fk_restaurant_id;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("picture")
	@Expose
	private String picture;

	@SerializedName("ingredients")
	@Expose
	private String ingredients;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getFk_restaurant_id() {
		return fk_restaurant_id;
	}

	public void setFk_restaurant_id(String fk_restaurant_id) {
		this.fk_restaurant_id = fk_restaurant_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public static MenuItems[] fromJsonArray(String jsonArray) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		MenuItems[] data = gson.fromJson(jsonArray, MenuItems[].class);
		return data;
	}

	public String getImage(String id)
	{
		id="http://www.oranje-tech.com/demo/v4vegon/api"+"/getMenuItems"+getPicture();
		return id;
	}
}